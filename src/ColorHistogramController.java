
import Processing.Histogram;
import Processing.HistogramStats;
import Processing.HistogramEqualization;
import java.awt.image.BufferedImage;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author twk
 */
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ColorHistogramController implements PropertyChangeListener {

    private ColorHistogramView view;
    private MainController mainController;
    private Histogram histogram;
    private HistogramStats channelOneRange;
    private HistogramStats channelTwoRange;
    private HistogramStats channelThreeRange;
    private BufferedImage original;

    public ColorHistogramController(ColorHistogramView view, MainController mc) {
        this.view = view;
        view.setController(this);
        mainController = mc;
        histogram = new Histogram(view.getCanvas(), view.getMode());
        int w = view.getCanvas().getImage().getWidth();
        int h = view.getCanvas().getImage().getWidth();
        int[] hist1 = histogram.getComponent1Histogram();
        int[] hist2 = histogram.getComponent2Histogram();
        int[] hist3 = histogram.getComponent3Histogram();
        channelOneRange = new HistogramStats(hist1,w,h);
        channelTwoRange = new HistogramStats(hist2,w,h);
        channelThreeRange = new HistogramStats(hist3,w,h);
        view.getTfMinComponent1().setText(String.valueOf(channelOneRange.getCurrentMin()));
        view.getTfMinComponent2().setText(String.valueOf(channelTwoRange.getCurrentMin()));
        view.getTfMinComponent3().setText(String.valueOf(channelThreeRange.getCurrentMin()));
        view.getTfMaxComponent1().setText(String.valueOf(channelOneRange.getCurrentMax()));
        view.getTfMaxComponent2().setText(String.valueOf(channelTwoRange.getCurrentMax()));
        view.getTfMaxComponent3().setText(String.valueOf(channelThreeRange.getCurrentMax()));
        view.drawComponent1Histogram(histogram.getHistogramImage(hist1));
        view.drawComponent2Histogram(histogram.getHistogramImage(hist2));
        view.drawComponent3Histogram(histogram.getHistogramImage(hist3));
    }

    private void updateRanges() {
        if (!view.getCbEq1().isSelected()) {
            channelOneRange.setNewMin(Integer.valueOf(view.getTfMinComponent1().getText()));
            channelOneRange.setNewMax(Integer.valueOf(view.getTfMaxComponent1().getText()));
        } else {
            channelOneRange.setToBeEqualized(true);
        }
        if (!view.getCbEq2().isSelected()) {
            channelTwoRange.setNewMin(Integer.valueOf(view.getTfMinComponent2().getText()));
            channelTwoRange.setNewMax(Integer.valueOf(view.getTfMaxComponent2().getText()));
        } else {
            channelTwoRange.setToBeEqualized(true);
        }
        if (!view.getCbEq3().isSelected()) {
            channelThreeRange.setNewMin(Integer.valueOf(view.getTfMinComponent3().getText()));
            channelThreeRange.setNewMax(Integer.valueOf(view.getTfMaxComponent3().getText()));
        }
        else{
            channelThreeRange.setToBeEqualized(true);
        }
    }

    public void previewEquilization() {
        if (original == null) {
            original = view.getCanvas().getImage();
        } else {
            view.getCanvas().setImage(original);
        }
        updateRanges();
        HistogramEqualization he = new HistogramEqualization(view.getCanvas(),
                channelOneRange,
                channelTwoRange,
                channelThreeRange,
                view.getMode(), this);
        System.out.println(view.getMode());
        mainController.observe(he.getResultObservable());
        //channelOneRange.display();
        he.startProcess(true);

    }

    public void cancelEquilization() {
        if (original != null) {
            view.getCanvas().setImage(original);
            view.getCanvas().repaint();
        }
        view.dispose();
    }

    public void applyEquilization() {
        if (original != null) {
            view.getCanvas().setImage(original);
        }
        updateRanges();
        HistogramEqualization he = new HistogramEqualization(view.getCanvas(), channelOneRange, channelTwoRange, channelThreeRange, view.getMode(), this);
        mainController.observe(he.getResultObservable());
        he.startProcess(false);
        view.dispose();
    }

    public void displayHistogramWindow() {
        view.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            view.getMainView().getProgressBar().setValue(progress);
        }
    }

    public void done() {
    }
}
