/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
package es.miguelgonzalezgomez.dataBaseFun.bd.formatter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author Hibernate, Relational Persistence for Idiomatic Java
 */
public class Formmater {

    public static final String WHITESPACE = " \n\r\f\t";
    private static final Set BEGIN_CLAUSES = new HashSet();
    private static final Set END_CLAUSES = new HashSet();
    private static final Set LOGICAL = new HashSet();
    private static final Set QUANTIFIERS = new HashSet();
    private static final Set DML = new HashSet();
    private static final Set MISC = new HashSet();

    static {
        BEGIN_CLAUSES.add("left");
        BEGIN_CLAUSES.add("right");
        BEGIN_CLAUSES.add("inner");
        BEGIN_CLAUSES.add("outer");
        BEGIN_CLAUSES.add("group");
        BEGIN_CLAUSES.add("order");

        END_CLAUSES.add("where");
        END_CLAUSES.add("set");
        END_CLAUSES.add("having");
        END_CLAUSES.add("join");
        END_CLAUSES.add("from");
        END_CLAUSES.add("by");
        END_CLAUSES.add("join");
        END_CLAUSES.add("into");
        END_CLAUSES.add("union");

        LOGICAL.add("and");
        LOGICAL.add("or");
        LOGICAL.add("when");
        LOGICAL.add("else");
        LOGICAL.add("end");

        QUANTIFIERS.add("in");
        QUANTIFIERS.add("all");
        QUANTIFIERS.add("exists");
        QUANTIFIERS.add("some");
        QUANTIFIERS.add("any");

        DML.add("insert");
        DML.add("update");
        DML.add("delete");

        MISC.add("select");
        MISC.add("on");
    }
    static final String indentString = "    ";
    static final String initial = "";

    public String format(String source) {
        return new FormatProcess(source).perform();
    }

    private static class FormatProcess {

        boolean beginLine = true;
        boolean afterBeginBeforeEnd = false;
        boolean afterByOrSetOrFromOrSelect = false;
        boolean afterValues = false;
        boolean afterOn = false;
        boolean afterBetween = false;
        boolean afterInsert = false;
        boolean afterWhiteSpace = false;
        int inFunction = 0;
        int parensSinceSelect = 0;
        private LinkedList parenCounts = new LinkedList();
        private LinkedList afterByOrFromOrSelects = new LinkedList();
        int indent = 0;
        StringBuffer result = new StringBuffer();
        StringTokenizer tokens;
        String lastToken;
        String token;
        String lcToken;

        public FormatProcess(String sql) {
            tokens = new StringTokenizer(
                    sql,
                    "()+*/-=<>'`\"[]," + WHITESPACE,
                    true);
        }

        public String perform() {

            result.append(initial);

            while (tokens.hasMoreTokens()) {
                token = tokens.nextToken();
                lcToken = token.toLowerCase();

                if ("'".equals(token)) {
                    String t;
                    do {
                        t = tokens.nextToken();
                        token += t;
                    } while (!"'".equals(t) && tokens.hasMoreTokens()); // cannot handle single quotes
                } else if ("\"".equals(token)) {
                    String t;
                    do {
                        t = tokens.nextToken();
                        token += t;
                    } while (!"\"".equals(t));
                }

                if (afterByOrSetOrFromOrSelect && ",".equals(token)) {
                    commaAfterByOrFromOrSelect();
                } else if (afterOn && ",".equals(token)) {
                    commaAfterOn();
                } else if ("(".equals(token)) {
                    openParen();
                } else if (")".equals(token)) {
                    closeParen();
                } else if (BEGIN_CLAUSES.contains(lcToken)) {
                    beginNewClause();
                } else if (END_CLAUSES.contains(lcToken)) {
                    endNewClause();
                } else if ("select".equals(lcToken)) {
                    select();
                } else if (DML.contains(lcToken)) {
                    updateOrInsertOrDelete();
                } else if ("values".equals(lcToken)) {
                    values();
                } else if ("on".equals(lcToken)) {
                    on();
                } else if (afterBetween && lcToken.equals("and")) {
                    misc();
                    afterBetween = false;
                } else if (LOGICAL.contains(lcToken)) {
                    logical();
                } else if (isWhitespace(token)) {
                    white();
                } else {
                    misc();
                }

                if (!isWhitespace(token)) {
                    lastToken = lcToken;
                    afterWhiteSpace = false;
                } else {
                    afterWhiteSpace = true;
                }

            }
            return result.toString();
        }

        private void commaAfterOn() {
            out();
            indent--;
            newline();
            afterOn = false;
            afterByOrSetOrFromOrSelect = true;
        }

        private void commaAfterByOrFromOrSelect() {
            out();
            newline();
        }

        private void logical() {
            if ("end".equals(lcToken)) {
                indent--;
            }
            out();
            newline();
            beginLine = true;
        }

        private void on() {
            indent++;
            afterOn = true;
            newline();
            out();
            beginLine = false;
        }

        private void removeLastLetter() {
            result.deleteCharAt(
                    result.length() - 1
            );
        }
        
        private void misc() {
            if(",".equals(token.trim()) && afterWhiteSpace) {
                removeLastLetter();
            }
            out();
            if ("between".equals(lcToken)) {
                afterBetween = true;
            }
            if (afterInsert) {
                beginLine = false;
                afterWhiteSpace = false;
                white();
                afterWhiteSpace = true;
                beginLine = true;
                afterInsert = false;
                afterValues = false;
            } else {
                if(",".equals(token.trim())) {
                    beginLine = true;
                    newline();
                } else {
                    beginLine = false;
                }
                if ("case".equals(lcToken)) {
                    indent++;
                }
            }
        }

        private void white() {
            if (!beginLine && !afterWhiteSpace) {
                result.append(" ");
            }
        }

        private void updateOrInsertOrDelete() {
            out();
            indent++;
            beginLine = false;
            if ("update".equals(lcToken)) {
                newline();
            }
            if ("insert".equals(lcToken)) {
                afterInsert = true;
            }
        }

        private void select() {
            out();
            indent++;
            newline();
            parenCounts.addLast(new Integer(parensSinceSelect));
            afterByOrFromOrSelects.addLast(Boolean.valueOf(afterByOrSetOrFromOrSelect));
            parensSinceSelect = 0;
            afterByOrSetOrFromOrSelect = true;
        }

        private void out() {
            result.append(token);
        }
        
        private void endNewClause() {
            if (!afterBeginBeforeEnd) {
                indent--;
                if (afterOn) {
                    indent--;
                    afterOn = false;
                }
                if(!"INTO".equals(lcToken.toUpperCase())) {
                    newline();
                }
            }
            out();
            if (!"union".equals(lcToken)) {
                indent++;
            }
            newline();
            afterBeginBeforeEnd = false;
            afterByOrSetOrFromOrSelect = "by".equals(lcToken)
                    || "set".equals(lcToken)
                    || "from".equals(lcToken);
        }

        private void beginNewClause() {
            if (!afterBeginBeforeEnd) {
                if (afterOn) {
                    indent--;
                    afterOn = false;
                }
                indent--;
                newline();
            }
            out();
            beginLine = false;
            afterBeginBeforeEnd = true;
        }

        private void values() {
            indent--;
            newline();
            out();
            result.append(" ");
            white();
            afterValues = true;
        }

        private void closeParen() {
            parensSinceSelect--;
            indent--;
            if(afterInsert || afterValues) {
                newline();
            }
            out();
            beginLine = false;
        }

        private void openParen() {
            if(afterInsert) {
                beginLine = false;
                afterInsert = false;
                white();
                afterInsert = true;
            }
            out();
            indent++;
            if(afterInsert || afterValues) {
                newline();
            }
            beginLine = true;
            parensSinceSelect++;
        }

        private static boolean isFunctionName(String tok) {
            final char begin = tok.charAt(0);
            final boolean isIdentifier = Character.isJavaIdentifierStart(begin) || '"' == begin;
            return isIdentifier
                    && !LOGICAL.contains(tok)
                    && !END_CLAUSES.contains(tok)
                    && !QUANTIFIERS.contains(tok)
                    && !DML.contains(tok)
                    && !MISC.contains(tok);
        }

        private static boolean isWhitespace(String token) {
            return WHITESPACE.indexOf(token) >= 0;
        }

        private void newline() {
            result.append("\n");
            for (int i = 0; i < indent; i++) {
                result.append(indentString);
            }
            beginLine = true;
        }
    }
}
