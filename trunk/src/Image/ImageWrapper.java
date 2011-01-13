package Image;
import java.awt.image.*;
import java.util.Hashtable;

public class ImageWrapper extends BufferedImage{

    private boolean modified;
    private boolean yuv;
    private ImageWrapper undoImage;

    public ImageWrapper(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?,?> properties){
        super(cm,raster, isRasterPremultiplied,properties);
        initData();
    }

    public ImageWrapper(int width, int height, int imageType){
        super(width,height,imageType);
        initData();
    }

    public ImageWrapper(int width, int height, int imageType,IndexColorModel cm){
        super(width,height,imageType);
        initData();
    }

    private void initData(){
        modified=false;
        yuv=false;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public ImageWrapper getUndoImage() {
        return undoImage;
    }

    public void setUndoImage(ImageWrapper undoImage) {
        this.undoImage = undoImage;
    }

    public boolean isYuv() {
        return yuv;
    }

    public void setYuv(boolean yuv) {
        this.yuv = yuv;
    }

}

