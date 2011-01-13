
import Processing.Histogram;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author twk
 */
public class ColorHistogramController {
    private ColorHistogramView view;
    private Histogram histogram;
    
    public ColorHistogramController(ColorHistogramView view) {
        this.view = view;
        view.setController(this);
        histogram = new Histogram(view.getCanvas(), view.getMode());
        int [] hist1 = histogram.getComponent1Histogram();
        int [] hist2 = histogram.getComponent2Histogram();
        int [] hist3 = histogram.getComponent3Histogram();
        view.drawComponent1Histogram(histogram.getHistogramImage(hist1));
        view.drawComponent2Histogram(histogram.getHistogramImage(hist2));
        view.drawComponent3Histogram(histogram.getHistogramImage(hist3));
    }

    public void displayHistogramWindow(){
        view.setVisible(true);
    }

}
