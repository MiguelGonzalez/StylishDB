package com.stylishdb.utilities;

import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QStyleOption;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.gui.QWindowsStyle;

/**
 
 * @author Miguel Gonzalez
 */
public class StyleWithoutFocus extends QWindowsStyle {
    @Override
    public void drawPrimitive(PrimitiveElement pe, QStyleOption opt, QPainter p, QWidget w) {
        if(pe.equals(PrimitiveElement.PE_FrameFocusRect)) {
            return;
        }
        super.drawPrimitive(pe, opt, p, w);
    }
}
