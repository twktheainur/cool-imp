package Image;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageIoWriter extends Writer {

    public ImageIoWriter (String file) {
        super(file);
    }

    public void write (BufferedImage img) throws IOException{
        String exp;
        if(java.io.File.separator.equals("\\")){
            exp = "\\\\";
        }
        else{
            exp = java.io.File.separator;
        }
        String[] path_components = getFileName().split(exp);
        String fname = path_components[path_components.length-1];
        String type = fname.split("\\.")[1];
        try{
            ImageIO.write(img,type, new java.io.File(getFileName()));
        } catch (IOException e){
            throw e;
        }
    }

}

