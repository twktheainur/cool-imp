/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import Components.ImageCanvas;
import Image.RGBColor;
import java.awt.image.BufferedImage;

/**
 *
 * @author twk
 */
public class FuseImages {

    private ImageCanvas canvas_1;
    private ImageCanvas canvas_2;
    private int weight_1;
    private int weight_2;

    public FuseImages(ImageCanvas canvas_1, ImageCanvas canvas_2, int weight_1, int weight_2) {
        this.canvas_1 = canvas_1;
        this.canvas_2 = canvas_2;
        this.weight_1 = weight_1;
        this.weight_2 = weight_2;
    }

    public BufferedImage doFuseImages() {
        int w_1 = canvas_1.getImage().getWidth();
        int h_1 = canvas_1.getImage().getHeight();
        int w_2 = canvas_2.getImage().getWidth();
        int h_2 = canvas_2.getImage().getHeight();
        BufferedImage img_1 = canvas_1.getImage();
        BufferedImage img_2 = canvas_2.getImage();
        int area1 = w_1 * h_1;
        int area2 = w_2 * h_2;

        int fw = w_1;
        int fh = h_1;

        if (area1 > area2) {
            Resize resizer = new Resize(canvas_2);
            img_2 = resizer.doResize(w_1, h_1);
        } else if (area1 < area2) {
            Resize resizer = new Resize(canvas_2);
            img_1 = resizer.doResize(w_2, h_2);
            fw = w_2;
            fh = h_2;
        }


        BufferedImage fused = new BufferedImage(fw, fh, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < fw; x++) {
            for (int y = 0; y < fh; y++) {
                int rgb_1 = img_1.getRGB(x, y);
                int rgb_2 = img_2.getRGB(x, y);

                int fr = weight_1 * RGBColor.extractR(rgb_1) +
                        weight_2 * RGBColor.extractR(rgb_2);
                int fg = weight_1 * RGBColor.extractG(rgb_1) +
                        weight_2 * RGBColor.extractG(rgb_2);
                int fb = weight_1 * RGBColor.extractB(rgb_1) +
                        weight_2 * RGBColor.extractB(rgb_2);

                fused.setRGB(x, y, RGBColor.combineRGB(fr, fg, fb));
            }
        }
        return fused;
    }
}
