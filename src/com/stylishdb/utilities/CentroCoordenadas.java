package com.stylishdb.utilities;

import com.stylishdb.StylishDB;

/**
 *
 ** @author StylishDB
 */
public class CentroCoordenadas {
    public static int getXCentrada(int widthVentanaModal) {
        int left = StylishDB.getLeftVentana();
        int width = StylishDB.getWidthVentana();
        
        return left + (width - widthVentanaModal) / 2;
    }
    
    public static int getYCentrada(int heightVentanaModal) {
        int top = StylishDB.getTopVentana();
        int height = StylishDB.getHeightVentana();
        
        return top + (height  - heightVentanaModal) / 2;
    }
    
    public static int getYCentradaArriba() {
        int top = StylishDB.getTopVentana();
        int constanteTop = 80;
        
        return top + constanteTop;
    }
}
