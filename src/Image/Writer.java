package Image;
import java.awt.image.BufferedImage;

public abstract class Writer {
    private String fileName;
    Writer(String filename){
        fileName = filename;
    }
    public abstract void write (BufferedImage img) throws java.io.IOException;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
