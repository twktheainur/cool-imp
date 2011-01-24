/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import javax.swing.SwingWorker;
import java.awt.image.BufferedImage;
import Components.ImageCanvas;
import java.util.Observable;

public abstract class ImageProcessor extends SwingWorker<BufferedImage, Integer> {

    private ImageCanvas canvas;
    private ResultObservable result;
    private boolean preview;

    public ImageProcessor(ImageCanvas canvas) {
        super();
        this.canvas = canvas;
        result = new ResultObservable();
    }

    public void startProcess(boolean preview) {
        this.preview = preview;
        execute();
    }

    public abstract BufferedImage process();

    @Override
    protected BufferedImage doInBackground() {
        return process();
    }

    public ImageCanvas getCanvas() {
        return canvas;
    }

    public ResultObservable getResultObservable() {
        return result;
    }

    public boolean isPreview() {
        return preview;
    }


    protected abstract String getGeneratedImageString();

    public class ResultObservable extends Observable {

        private boolean preview;
        private BufferedImage image;
        private String name;
        public ResultObservable(){
            super();
        }

        public void setImage(BufferedImage image, String name,boolean preview) {
            this.image = image;
            this.name = name;
            this.preview = preview;
            setChanged();
            notifyObservers();
        }

        public BufferedImage getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public boolean isPreview() {
            return preview;
        }

        
    }
    @Override
    public void done() {
        try {
            setProgress(100);
            getResultObservable().setImage(get(), getGeneratedImageString(),isPreview());
            setProgress(0);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.fillInStackTrace().printStackTrace();
            System.out.println("Error, processing failed");
        }
    }
}
