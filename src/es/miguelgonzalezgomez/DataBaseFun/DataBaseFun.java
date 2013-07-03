package es.miguelgonzalezgomez.DataBaseFun;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;

/**
 *
 * @author Miguel González
 */
public class DataBaseFun extends QWidget {
    public DataBaseFun() {
        setWindowTitle("Tooltip");

        setToolTip("This is QWidget");
        
        resize(250, 150);
        move(300, 300);
        show();
    }


    public static void main(String args[])
    {
        QApplication.initialize(args);
        new DataBaseFun();
        QApplication.exec();
    }
}
