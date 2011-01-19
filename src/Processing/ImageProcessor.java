/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Processing;
import javax.swing.SwingWorker;
import java.awt.image.BufferedImage;
import Components.ImageCanvas;
import java.util.Observable;
public abstract class ImageProcessor extends SwingWorker<BufferedImage,Integer>{

    private ImageCanvas canvas;
    private ResultObservable result;

    public ImageProcessor(ImageCanvas canvas){
        super();
        this.canvas = canvas;
        result = new ResultObservable();
    }

    @Override
    public abstract void done();

    public void startProcess(){
        execute();
    }

    public abstract BufferedImage process();

    @Override
    protected BufferedImage doInBackground(){
        return process();
    }

    public ImageCanvas getCanvas() {
        return canvas;
    }

    public ResultObservable getResultObservable(){
        return result;
    }

    public class ResultObservable extends Observable{
        private BufferedImage image;
        private String name;
        public void setImage(BufferedImage image, String name){
            this.image = image;
            this.name = name;
            setChanged();
            notifyObservers();
        }
        public BufferedImage getImage(){
            return image;
        }
        public String getName() {
            return name;
        }
    }
    
}
