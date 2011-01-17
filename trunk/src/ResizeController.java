
import Processing.Resize;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ResizeController {

    private ResizeView view;

    public ResizeController(ResizeView view) {
        this.view = view;
        view.setController(this);
    }

    public void applyResize() {
        int newWidth = view.getWidthValue();
        int newHeight = view.getHeightValue();
        Resize resizer = new Resize(view.getCanvas());
        BufferedImage newImage = resizer.doResize(newWidth, newHeight);
        view.getMainView().addImage("UnsavedResizedImage", new File(""), newImage, true);
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
}
