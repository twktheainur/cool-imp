/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author twk
 */
import java.awt.image.BufferedImage;
import Processing.GaussianFilter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class GaussianBlurController implements PropertyChangeListener {

    private GaussianBlurView view;
    private MainController mainController;
    private BufferedImage original;

    public GaussianBlurController(GaussianBlurView view, MainController mc) {
        this.view = view;
        view.setController(this);
        mainController = mc;
    }

    public void previewGaussianBlur() {
        if (original == null) {
            original = view.getCanvas().getImage();
        } else {
            view.getCanvas().setImage(original);
        }
        GaussianFilter gbf = new GaussianFilter(view.getCanvas(), view.getSizeValue(), this);
        mainController.observe(gbf.getResultObservable());
        gbf.startProcess(true);

    }

    public void cancelGaussianBlur() {
        if (original != null) {
            view.getCanvas().setImage(original);
            view.getCanvas().repaint();
        }
        view.dispose();
    }

    public void applyGaussianBlur() {
        if (original != null) {
            view.getCanvas().setImage(original);
        }
        GaussianFilter gbf = new GaussianFilter(view.getCanvas(), view.getSizeValue(), this);
        mainController.observe(gbf.getResultObservable());
        gbf.startProcess(false);
        view.dispose();
    }

    public void displayBlurWindow() {
        view.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            view.getMainView().getProgressBar().setValue(progress);
        }
    }
}
