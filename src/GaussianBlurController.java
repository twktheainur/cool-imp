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
import javax.swing.SpinnerNumberModel;

public class GaussianBlurController {

    private GaussianBlurView view;
    private BufferedImage original;

    public GaussianBlurController(GaussianBlurView view) {
        this.view = view;
        view.setController(this);
    }

    public void previewGaussianBlur() {
        if (original == null) {
            original = view.getCanvas().getImage();
        }
        else{
            view.getCanvas().setImage(original);
        }
        GaussianFilter gbf = new GaussianFilter(view.getCanvas(), view.getSizeValue());
        gbf.setBias(view.getBiasValue());
        gbf.setFactor(view.getFactorValue());
        BufferedImage filtered = gbf.applyFilter();
        view.getCanvas().setImage(filtered);
        view.getCanvas().repaint();
    }

    public void cancelGaussianBlur() {
        view.getCanvas().setImage(original);
        view.getCanvas().repaint();
        view.dispose();
    }

    public void applyGaussianBlur() {
        view.getCanvas().setImage(original);
        GaussianFilter gbf = new GaussianFilter(view.getCanvas(), view.getSizeValue());
        gbf.setBias(view.getBiasValue());
        gbf.setFactor(view.getFactorValue());
        BufferedImage filtered = gbf.applyFilter();
        view.getMainView().addImage("UnsavedBlurredImage", null, filtered, false);
        view.dispose();
    }

    public void displayBlurWindow(){
        view.setVisible(true);
    }
}
