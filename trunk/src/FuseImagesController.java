/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author twk
 */
import Components.ImageComboBoxRenderer;
import java.awt.image.BufferedImage;
import Processing.GaussianFilter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import Processing.FuseImages;
import javax.swing.JComboBox;

public class FuseImagesController implements PropertyChangeListener {

    private FuseImagesView view;
    private MainController mainController;
    private BufferedImage original;

    public FuseImagesController(FuseImagesView view, MainController mc) {
        this.view = view;
        view.setController(this);
        mainController = mc;
    }

    public void previewFuseImages() {
        if (original == null) {
            original = view.getCanvas().getImage();
        } else {
            view.getCanvas().setImage(original);
        }
        double ratio = (double) view.getSlBlendRatio().getValue() / 100.0;
        JComboBox box = view.getCmbImageTwo();
        ImageComboBoxRenderer icbr = (ImageComboBoxRenderer) box.getRenderer();
        FuseImages fi = new FuseImages(view.getCanvas(), icbr.getCanvasAt(box.getSelectedIndex()), 1 - ratio, ratio, this);
        mainController.observe(fi.getResultObservable());
        fi.startProcess(true);
    }

    public void cancelFuseImages() {
        if (original != null) {
            view.getCanvas().setImage(original);
            view.getCanvas().repaint();
        }
        view.dispose();
    }

    public void applyFuseImages() {
        if (original != null) {
            view.getCanvas().setImage(original);
        }
        double ratio = (double) view.getSlBlendRatio().getValue() / 100.0;
        JComboBox box = view.getCmbImageTwo();
        ImageComboBoxRenderer icbr = (ImageComboBoxRenderer) box.getRenderer();
        FuseImages fi = new FuseImages(view.getCanvas(), icbr.getCanvasAt(box.getSelectedIndex()), 1 - ratio, ratio, this);
        mainController.observe(fi.getResultObservable());
        fi.startProcess(false);
        view.dispose();
    }

    public void displayFuseImageWindow() {
        view.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            view.getMainView().getProgressBar().setValue(progress);
        }
    }
}
