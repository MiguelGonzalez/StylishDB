/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas;

import es.miguelgonzalezgomez.dataBaseFun.DataBaseFun;

/**
 *
 * @author paracaidista
 */
public class CentroCoordenadas {
    public static int getXCentrada(int widthVentanaModal) {
        int left = DataBaseFun.getLeftVentana();
        int width = DataBaseFun.getWidthVentana();
        
        return left + (width - widthVentanaModal) / 2;
    }
    
    public static int getYCentrada(int heightVentanaModal) {
        int top = DataBaseFun.getTopVentana();
        int height = DataBaseFun.getHeightVentana();
        
        return top + (height  - heightVentanaModal) / 2;
    }
    
    public static int getYCentradaArriba() {
        int top = DataBaseFun.getTopVentana();
        int constanteTop = 75;
        
        return top + constanteTop;
    }
}
