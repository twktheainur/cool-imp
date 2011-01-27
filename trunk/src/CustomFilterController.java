/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author twk
 */
import java.awt.image.BufferedImage;
import Processing.CustomFilter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class CustomFilterController implements PropertyChangeListener {

    private CustomFilterView view;
    private BufferedImage original;
    private MainController mainController;

    public CustomFilterController(CustomFilterView view, MainController mc) {
        this.view = view;
        view.setController(this);
        mainController = mc;
    }

    public void cancelCustomFilter() {
        if (original != null) {
            view.getCanvas().setImage(original);
            view.getCanvas().repaint();
        }
        view.dispose();
    }

    public void previewCustomFilter() {
        if (original == null) {
            original = view.getCanvas().getImage();
        } else {
            view.getCanvas().setImage(original);
        }
        CustomFilter cf = new CustomFilter(view.getCanvas(),
                view.getKernel(),
                view.getMatrixWidth(),
                view.getMatirxHeight(), this);
        mainController.observe(cf.getResultObservable());
        cf.startProcess(true);
    }

    public void applyCustomFilter() {
        if (original != null) {
            view.getCanvas().setImage(original);
        }
        CustomFilter cf = new CustomFilter(view.getCanvas(),
                view.getKernel(),
                view.getMatrixWidth(),
                view.getMatirxHeight(), this);
        mainController.observe(cf.getResultObservable());
        cf.startProcess(true);
    }

    public void displayCustomFilterWindow() {
        view.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            view.getMainView().getProgressBar().setValue(progress);
        }
    }
}
