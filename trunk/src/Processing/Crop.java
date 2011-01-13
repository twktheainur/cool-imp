/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import Components.ImageCanvas;
import java.awt.image.BufferedImage;

/**
 *
 * @author twk
 */
public class Crop {

    private ImageCanvas canvas;

    public Crop(ImageCanvas canvas) {
        this.canvas = canvas;
    }

    public BufferedImage doCrop(int ox, int oy, int fx, int fy) {
        BufferedImage cropped = new BufferedImage(Math.abs(ox - fx), Math.abs(oy - fy), canvas.getImage().getType());
        for (int x = ox; x < fx; x++) {
            for (int y = oy; y < fy; y++) {
                cropped.setRGB(x - ox, y - oy, canvas.getImage().getRGB(x, y));
            }
        }
        return cropped;
    }
}
