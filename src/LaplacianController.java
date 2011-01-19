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

public class LaplacianController {

    private LaplacianView view;
    private BufferedImage original;

    public LaplacianController(LaplacianView view) {
        this.view = view;
        view.setController(this);
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
        LaplacianFilter lf = new LaplacianFilter(view.getCanvas(), view.getSelectedSize());
        BufferedImage filtered = lf.applyFilter();
        view.getCanvas().setImage(filtered);
        view.getCanvas().repaint();
    }

    public void applyLapacian() {
        if(original!=null){
            view.getCanvas().setImage(original);
        }
        LaplacianFilter lf = new LaplacianFilter(view.getCanvas(),view.getSelectedSize());
        BufferedImage filtered = lf.applyFilter();
        view.getMainView().addImage("UnsavedLaplacian", null, filtered, false);
        view.dispose();
    }

    public void displayLaplacianWindow() {
        view.setVisible(true);
    }
}
