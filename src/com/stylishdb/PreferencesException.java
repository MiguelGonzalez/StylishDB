package com.stylishdb;

/**
 *
 ** @author StylishDB
 */
public class PreferencesException extends Exception {
    public PreferencesException(String error) {
        super(error);
    }
    
    public PreferencesException(String error, Exception ex) {
        super(error, ex);
    }
}
