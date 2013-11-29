package com.stylishdb.domain;

/**
 *
 ** @author StylishDB
 */
public class MApplication {
    
    public AllMConnections mConexionesGuardadas;
    public AllMTabs mPestanasEditorAbiertas;
    
    private static MApplication INSTANCE = null;
    
    private MApplication() {
        inicializarModelos();
    }
    
    private void inicializarModelos() {
        mConexionesGuardadas = new AllMConnections();
        mPestanasEditorAbiertas = new AllMTabs();
    }
    
    public static MApplication getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MApplication();
        }
        return INSTANCE;
    }
}
