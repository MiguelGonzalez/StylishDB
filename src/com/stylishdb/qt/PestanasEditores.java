package com.stylishdb.qt;

import com.trolltech.qt.core.QByteArray;
import com.trolltech.qt.core.QMimeData;
import com.trolltech.qt.core.QPoint;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QDrag;
import com.trolltech.qt.gui.QDragEnterEvent;
import com.trolltech.qt.gui.QDropEvent;
import com.trolltech.qt.gui.QMouseEvent;
import com.stylishdb.qt.controllers.CPestanasEditores;
import com.trolltech.qt.gui.QTabBar;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class PestanasEditores extends QTabBar {
    
    private CPestanasEditores controlador;
    
    public PestanasEditores(CPestanasEditores controlador) {
        this.controlador = controlador;
        
        setAcceptDrops(true);        
    }
    
    private QPoint m_dragStartPos;
    
    @Override
    protected void mousePressEvent(QMouseEvent event) {
        if (event.button() == Qt.MouseButton.LeftButton) {
            m_dragStartPos = event.pos();
        }
        
        super.mousePressEvent(event);
    }

    @Override
    protected void mouseMoveEvent(QMouseEvent event) {
        if (!(event.buttons().value() == event.button().LeftButton.value())) {
            super.mouseMoveEvent(event);
            return;
        }
            

        
        if (event.pos().subtract(m_dragStartPos).manhattanLength()
                < QApplication.startDragDistance()) {
            super.mouseMoveEvent(event);
            
            return;
        }
        
        // initiate Drag
        QDrag drag = new QDrag(this);
        QMimeData mimeData = new QMimeData();
        QByteArray qByteArrayData = new QByteArray("tab-reordering");
        
        mimeData.setData("action", qByteArrayData);
        drag.setMimeData(mimeData);
        drag.exec();
    }

    @Override
    protected void dragEnterEvent(QDragEnterEvent event) {
        // Only accept if it's an tab-reordering request
        QMimeData m = event.mimeData();
        List<String> formats = m.formats();
        
        QByteArray qByteArrayData = new QByteArray("tab-reordering");
        if (formats.contains("action") &&
                qByteArrayData.equals(m.data("action"))) {
            event.acceptProposedAction();
        } else {
            super.dragEnterEvent(event);
        }
    }

    @Override
    protected void dropEvent(QDropEvent event) {
        int fromIndex = tabAt(m_dragStartPos);
        int toIndex = tabAt(event.pos());

        if (fromIndex != toIndex) {
            controlador.tabMoveRequested(fromIndex, toIndex);
        } else {
            event.acceptProposedAction();
        }
    }
    
    
    
}
