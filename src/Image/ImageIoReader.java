package Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageIoReader extends Reader {

    public ImageIoReader(String filename) {
        super(filename);
    }

    public BufferedImage read() throws IOException {
        try {
            File f = new File(getFileName());
            BufferedImage image = ImageIO.read(f);
            /*ImageWrapper iwr = new ImageWrapper(image.getColorModel(),
            image.getRaster(),false,null);*/
            return image;
        } catch (IOException e) {
            throw e;
        }
    }
}

