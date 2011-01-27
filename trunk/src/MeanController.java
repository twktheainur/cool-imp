
import java.awt.image.BufferedImage;
import Processing.MeanFilter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MeanController implements PropertyChangeListener {

    private MeanView view;
    private MainController mainController;
    private BufferedImage original;

    public MeanController(MeanView view, MainController mc) {
        this.view = view;
        view.setController(this);
        mainController = mc;
    }

    public void previewMean() {
        if (original == null) {
            original = view.getCanvas().getImage();
        } else {
            view.getCanvas().setImage(original);
        }
        MeanFilter mf = new MeanFilter(view.getCanvas(), view.getSizeValue(), this);
        mainController.observe(mf.getResultObservable());
        mf.startProcess(true);

    }

    public void cancelMean() {
        if (original != null) {
            view.getCanvas().setImage(original);
            view.getCanvas().repaint();
        }
        view.dispose();
    }

    public void applyMean() {
        if (original != null) {
            view.getCanvas().setImage(original);
        }
        MeanFilter mf = new MeanFilter(view.getCanvas(), view.getSizeValue(), this);
        mainController.observe(mf.getResultObservable());
        mf.startProcess(false);
        view.dispose();
    }

    public void displayMeanWindow() {
        view.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            view.getMainView().getProgressBar().setValue(progress);
        }
    }
}
