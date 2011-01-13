/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import Components.ImageCanvas;
import Image.YUVColor;
import java.awt.image.BufferedImage;

public class ConvertToGrayscale {

    private ImageCanvas canvas;

    public ConvertToGrayscale(ImageCanvas canvas) {
        this.canvas = canvas;
    }

    public BufferedImage doConvertToGrayscale() {
        int w = canvas.getImage().getWidth();
        int h = canvas.getImage().getHeight();
        BufferedImage original = canvas.getImage();
        BufferedImage converted = new BufferedImage(w, h,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int rgb = original.getRGB(x, y);
                int gs = YUVColor.extractY(rgb);
                int nrgb = 0xFF000000 + (gs << 16) + (gs << 8) + gs;
                converted.setRGB(x, y,nrgb);
            }
        }
        return converted;
    }
}
