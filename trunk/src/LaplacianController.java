/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author twk
 */
import java.awt.image.BufferedImage;
import Processing.LaplacianFilter;
import Processing.SobelFilter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class LaplacianController implements PropertyChangeListener {

    private LaplacianView view;
    private BufferedImage original;
    private MainController mainController;

    public LaplacianController(LaplacianView view, MainController mc) {
        this.view = view;
        view.setController(this);
        mainController = mc;
    }

    public void cancelLaplacian() {
        if (original != null) {
            view.getCanvas().setImage(original);
            view.getCanvas().repaint();
            view.dispose();
        }
    }

    public void previewLaplacian() {
        if (original == null) {
            original = view.getCanvas().getImage();
        } else {
            view.getCanvas().setImage(original);
        }
        LaplacianFilter lf = new LaplacianFilter(view.getCanvas(), view.getSelectedSize(),this);
        lf.setGrayscale(view.isGrayscaleSelected());
        mainController.observe(lf.getResultObservable());
        lf.startProcess(true);
    }

    public void applyLapacian() {
        if (original != null) {
            view.getCanvas().setImage(original);
        }
        LaplacianFilter lf = new LaplacianFilter(view.getCanvas(),view.getSelectedSize(),this);
       // SobelFilter lf = new SobelFilter(view.getCanvas(),3,this);
        mainController.observe(lf.getResultObservable());
        lf.setGrayscale(view.isGrayscaleSelected());
        lf.startProcess(false);
        view.dispose();
    }

    public void displayLaplacianWindow() {
        view.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            view.getMainView().getProgressBar().setValue(progress);
        }
    }
}
