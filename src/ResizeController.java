
import Processing.Resize;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class ResizeController implements PropertyChangeListener {

    private ResizeView view;
    private MainController mainController;
    public ResizeController(ResizeView view, MainController mc) {
        this.view = view;
        mainController = mc;
        view.setController(this);
    }

    public void applyResize() {
        int newWidth = view.getWidthValue();
        int newHeight = view.getHeightValue();
        Resize resizer = new Resize(view.getCanvas(),newWidth,newHeight,this);
        mainController.observe(resizer.getResultObservable());
        resizer.startProcess(false);
        view.dispose();
    }

    public void resetResize() {
        view.setWidthValue(view.getCanvas().getImage().getWidth());
        view.setHeightValue(view.getCanvas().getImage().getHeight());
        view.getTbLock().setSelected(false);
    }

    public void cancelResize() {
        view.dispose();
    }

    public void displayResizeWindow() {
        view.setVisible(true);
    }

    public ResizeView getView() {
        return view;
    }

     public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            view.getMainView().getProgressBar().setValue(progress);
        }
    }
}
