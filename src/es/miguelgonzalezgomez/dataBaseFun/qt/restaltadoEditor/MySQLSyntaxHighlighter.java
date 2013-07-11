package es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor;

import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.core.Qt.CaseSensitivity;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QSyntaxHighlighter;
import com.trolltech.qt.gui.QTextCharFormat;
import com.trolltech.qt.gui.QTextDocument;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class MySQLSyntaxHighlighter extends QSyntaxHighlighter {

    private List<HighlightingRule> highlightingRules;
    private QTextCharFormat mySQLFormat;
    private QTextCharFormat comentariosFormat;
    
    private QRegExp commentStartExpression;
    private QRegExp commentEndExpression;
    
    String[] palabrasClavesReservadasMySQL = {
        "ADD","ALL","ALTER","ANALYZE","AND","AS","ASC","ASENSITIVE",
        "BEFORE","BETWEEN","BIGINT","BINARY","BLOB","BOTH","BY","CALL",
        "CASCADE","CASE","CHANGE","CHAR","CHARACTER","CHECK","COLLATE",
        "COLUMN","CONDITION","CONSTRAINT","CONTINUE","CONVERT","CREATE",
        "CROSS","CURRENT_DATE","CURRENT_TIME","CURRENT_TIMESTAMP",
        "CURRENT_USER","CURSOR","DATABASE","DATABASES","DAY_HOUR",
        "DAY_MICROSECOND","DAY_MINUTE","DAY_SECOND","DEC","DECIMAL",
        "DECLARE","DEFAULT","DELAYED","DELETE","DESC","DESCRIBE",
        "DETERMINISTIC","DISTINCT","DISTINCTROW","DIV","DOUBLE","DROP",
        "DUAL","EACH","ELSE","ELSEIF","ENCLOSED","ESCAPED","EXISTS",
        "EXIT","EXPLAIN","FALSE","FETCH","FLOAT","FLOAT4","FLOAT8",
        "FOR","FORCE","FOREIGN","FROM","FULLTEXT","GRANT","GROUP",
        "HAVING","HIGH_PRIORITY","HOUR_MICROSECOND","HOUR_MINUTE",
        "HOUR_SECOND","IF","IGNORE","IN","INDEX","INFILE","INNER",
        "INOUT","INSENSITIVE","INSERT","INT","INT1","INT2","INT3",
        "INT4","INT8","INTEGER","INTERVAL","INTO","IS","ITERATE",
        "JOIN","KEY","KEYS","KILL","LEADING","LEAVE","LEFT","LIKE",
        "LIMIT","LINES","LOAD","LOCALTIME","LOCALTIMESTAMP","LOCK",
        "LONG","LONGBLOB","LONGTEXT","LOOP","LOW_PRIORITY","MATCH",
        "MEDIUMBLOB","MEDIUMINT","MEDIUMTEXT","MIDDLEINT",
        "MINUTE_MICROSECOND","MINUTE_SECOND","MOD","MODIFIES",
        "NATURAL","NOT","NO_WRITE_TO_BINLOG","NULL","NUMERIC","ON",
        "OPTIMIZE","OPTION","OPTIONALLY","OR","ORDER","OUT","OUTER",
        "OUTFILE","PRECISION","PRIMARY","PROCEDURE","PURGE","READ",
        "READS","REAL","REFERENCES","REGEXP","RELEASE","RENAME",
        "REPEAT","REPLACE","REQUIRE","RESTRICT","RETURN","REVOKE",
        "RIGHT","RLIKE","SCHEMA","SCHEMAS","SECOND_MICROSECOND",
        "SELECT","SENSITIVE","SEPARATOR","SET","SHOW","SMALLINT","SONAME",
        "SPATIAL","SPECIFIC","SQL","SQLEXCEPTION","SQLSTATE","SQLWARNING",
        "SQL_BIG_RESULT","SQL_CALC_FOUND_ROWS","SQL_SMALL_RESULT","SSL",
        "STARTING","STRAIGHT_JOIN","TABLE","TERMINATED","THEN","TINYBLOB",
        "TINYINT","TINYTEXT","TO","TRAILING","TRIGGER","TRUE","UNDO",
        "UNION","UNIQUE","UNLOCK","UNSIGNED","UPDATE","USAGE","USE",
        "USING","UTC_DATE","UTC_TIME","UTC_TIMESTAMP","VALUES","VARBINARY",
        "VARCHAR","VARCHARACTER","VARYING","WHEN","WHERE","WHILE","WITH",
        "WRITE","XOR","YEAR_MONTH","ZEROFILL",
    };
    
    public MySQLSyntaxHighlighter(QTextDocument parent) {
        super(parent);
        
        highlightingRules = new ArrayList<>();
        
        mySQLFormat= new QTextCharFormat();
        comentariosFormat = new QTextCharFormat();
        
        construirResaltadoPalabrasClavesMySQL();
        construirResaltadoComentarios();
    }
    
    @Override
    protected void highlightBlock(String text) {
        for (HighlightingRule rule : highlightingRules) {
            QRegExp expression = rule.pattern;
            int index = expression.indexIn(text);
            while (index >= 0) {
                int length = expression.matchedLength();
                setFormat(index, length, rule.format);
                index = expression.indexIn(text, index + length);
            }
        }

        setCurrentBlockState(0);
        
        int startIndex = 0;
            if (previousBlockState() != 1)
                startIndex = commentStartExpression.indexIn(text);


            while (startIndex >= 0) {
                int endIndex = commentEndExpression.indexIn(text, startIndex);
                int commentLength;
                if (endIndex == -1) {
                    setCurrentBlockState(1);
                    commentLength = text.length() - startIndex;
                } else {
                    commentLength = endIndex - startIndex + commentEndExpression.matchedLength();
                }
                setFormat(startIndex, commentLength, comentariosFormat);
                startIndex = commentStartExpression.indexIn(text, startIndex + commentLength);
            }
    }
    
    private void construirResaltadoPalabrasClavesMySQL() {
        QBrush brush = new QBrush(QColor.darkRed);
        mySQLFormat.setForeground(brush);
        mySQLFormat.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : palabrasClavesReservadasMySQL) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b",
                    CaseSensitivity.CaseInsensitive);
            HighlightingRule rule = new HighlightingRule(pattern, mySQLFormat);
            highlightingRules.add(rule);
        }
                
    }

    private void construirResaltadoComentarios() {
        QBrush brush = new QBrush(QColor.lightGray);
        comentariosFormat.setForeground(brush);
        comentariosFormat.setFontWeight(QFont.Weight.Bold.value());
        
        QRegExp patronDobleLinea = new QRegExp("--[^\n]*");
        QRegExp patronComentarioLinea = new QRegExp("/\\*[^\n]*\\*/");
        
        HighlightingRule reglaComentarioDobleLinea = new HighlightingRule(
                patronDobleLinea,
                comentariosFormat);
        highlightingRules.add(reglaComentarioDobleLinea);
        
        HighlightingRule reglaComentarioLinea = new HighlightingRule(
                patronComentarioLinea,
                comentariosFormat);
        highlightingRules.add(reglaComentarioLinea);
        
        commentStartExpression = new QRegExp("/\\*");
        commentEndExpression = new QRegExp("\\*/");
    }
}
