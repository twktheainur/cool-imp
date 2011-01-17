
import Components.ImageCanvas;
import org.jdesktop.application.SingleFrameApplication;
import Image.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Observer;
import java.util.Observable;
import Processing.ConvertToGrayscale;
import Processing.GaussianFilter;
import Processing.Resize;


public class MainController {

    MainView view;

    public MainController(SingleFrameApplication app) {
        view = new MainView(app, this);
    }

    public MainView getView() {
        return view;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void displayAboutDialog() {
        AboutBoxView v = new AboutBoxView(view.getFrame());
        v.setVisible(true);
    }

    public void openFile() {
        FileOpenView fov = new FileOpenView(view.getFrame());
        if (fov.isSelected()) {
            Reader r = ReaderFactory.getReader(fov.getFileName());
            try {
                view.addImage(fov.extractName(), new java.io.File(fov.getFileName()), r.read(), true);
            } catch (IOException e) {
                view.displayStatusMessage(e.getLocalizedMessage());
            }
        }
    }

    public void saveFile(ImageCanvas current_canvas) {
        String file;
        if (current_canvas != null) {
            if (current_canvas.isChanged()) {
                if (!current_canvas.isSaved()) {
                    FileSaveView fsv = new FileSaveView(view.getFrame(), "Save...");
                    file = fsv.getFileName();
                    current_canvas.setFile(new java.io.File(file));
                    view.getImagesTab().setTitleAt(view.getImagesTab().getSelectedIndex(),
                            fsv.extractName());
                } else {
                    file = current_canvas.getFile().getName();
                }
                Image.Writer w = WriterFactory.getWriter(file);
                try {
                    w.write(current_canvas.getImage());
                    current_canvas.setSaved(true);
                    current_canvas.setChanged(false);
                } catch (IOException e) {
                    view.displayStatusMessage(e.getLocalizedMessage());
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
    }

    public void saveFileAs(ImageCanvas current_canvas) {
        if (current_canvas != null) {
            String file;
            FileSaveView fsv = new FileSaveView(view.getFrame(), "Save As...");
            file = fsv.getFileName();
            current_canvas.setFile(new java.io.File(file));
            view.getImagesTab().setTitleAt(view.getImagesTab().getSelectedIndex(),
                    fsv.extractName());
            Image.Writer w = WriterFactory.getWriter(file);
            try {
                w.write(current_canvas.getImage());
                current_canvas.setSaved(true);
                current_canvas.setChanged(false);
            } catch (IOException e) {
                view.displayStatusMessage(e.getLocalizedMessage());
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    public void closeImage(ImageCanvas current_canvas) {
        if (current_canvas.isChanged() || !current_canvas.isSaved()) {
            SaveConfirm sc = new SaveConfirm(view.getFrame(), true, this, current_canvas);
            sc.setVisible(true);
        }
        view.getImagesTab().remove(view.getImagesTab().getSelectedIndex());
    }

    public void pixelColorInformation(ImageCanvas current_canvas,boolean isYUV) {
        int mode = ColorInformationView.RGB_MODE;
        if (isYUV) {
            mode = ColorInformationView.YUV_MODE;
        }
        ColorInformationView ciw = new ColorInformationView(view, mode);
        ciw.setVisible(true);
        current_canvas.addMouseMotionListener(ciw.getCanvasListener());
    }

    public void colorHistogram(ImageCanvas current_canvas,boolean isYUV){
        int mode = (isYUV)?ColorHistogramView.YUV_MODE:ColorHistogramView.RGB_MODE;
        ColorHistogramView chv = new ColorHistogramView(view, mode);
        ColorHistogramController chc = new ColorHistogramController(chv);
        chc.displayHistogramWindow();
    }

    public void resize(){
        ResizeView resizeView = new ResizeView(view);
        ResizeController resizeController = new ResizeController(resizeView);
        resizeController.displayResizeWindow();
    }

    public void convertToGrayscale(ImageCanvas current_canvas){
        ConvertToGrayscale ctgs = new ConvertToGrayscale(current_canvas);
        BufferedImage result = ctgs.doConvertToGrayscale();
        view.addImage("UnsavedGrayscale", null, result, false);
    }

    public void applyGaussianBlur(ImageCanvas current_canvas){
        GaussianBlurView gbv = new GaussianBlurView(view);
        GaussianBlurController gbc = new GaussianBlurController(gbv);
        gbc.displayBlurWindow();
    }

    public void observe(CropListener cl) {
        cl.addObserver(new CropObserver());
    }

    private class CropObserver implements Observer {

        public void update(Observable obs, Object obj) {
            CropListener cl = (CropListener) obs;
            ImageCanvas cur_canvas = view.getCurrentTabCanvas();
            Processing.Crop c = new Processing.Crop(cur_canvas);
            BufferedImage new_image = c.doCrop(cl.getOrigX(), cl.getOrigY(), cl.getFinalX(), cl.getFinalY());
            view.addImage("UnsavedCrop", null, new_image, false);
        }
    }
}
