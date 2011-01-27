/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author twk
 */
import java.awt.image.BufferedImage;
import Processing.SobelFilter;
import Processing.PrewittFilter;
import Processing.Filter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class GradientController implements PropertyChangeListener {

    private GradientView view;
    private BufferedImage original;
    private MainController mainController;

    public GradientController(GradientView view, MainController mc) {
        this.view = view;
        view.setController(this);
        mainController = mc;
    }

    public void cancelGradient() {
        if (original != null) {
            view.getCanvas().setImage(original);
            view.getCanvas().repaint();
        }
        view.dispose();
    }

    private Filter getFilter(int mode) {
        if (mode == GradientView.METHOD_SOBEL) {
            SobelFilter sf = new SobelFilter(view.getCanvas(), 3, this);
            sf.setGrayscale(view.isGrayscale());
            sf.setThreshold(150);
            return sf;
        } else if (mode == GradientView.METHOD_PREWITT) {
            PrewittFilter pf = new PrewittFilter(view.getCanvas(), 3, this);
            pf.setGrayscale(view.isGrayscale());
            pf.setThreshold(150);
            return pf;
        } else {
            return null;
        }
    }

    public void previewGradient() {
        if (original == null) {
            original = view.getCanvas().getImage();
        } else {
            view.getCanvas().setImage(original);
        }
        Filter f = getFilter(view.getSelectedMethod());
        mainController.observe(f.getResultObservable());
        f.startProcess(true);
    }

    public void applyGradient() {
        if (original != null) {
            view.getCanvas().setImage(original);
        }
        Filter f = getFilter(view.getSelectedMethod());
        mainController.observe(f.getResultObservable());
        f.startProcess(false);
        view.dispose();
    }

    public void displayGradientWindow() {
        view.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            view.getMainView().getProgressBar().setValue(progress);
        }
    }
}
