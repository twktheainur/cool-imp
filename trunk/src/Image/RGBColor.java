/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Image;
import java.awt.Color;
/**
 *
 * @author twk
 */
public class RGBColor {

    public static int extractR(int rgb) {
        //return (rgb & 0x00FF0000) >> 16;
        Color c = new Color(rgb);
        return c.getRed();
    }

    public static int extractG(int rgb) {
        //return (rgb & 0x0000FF00) >> 8;
        Color c = new Color(rgb);
        return c.getGreen();
    }

    public static int extractB(int rgb) {
        //return (rgb & 0x000000FF);
        Color c = new Color(rgb);
        return c.getBlue();
    }

    public static int combineRGB(int r, int g, int b){
        Color c = new Color(r,g,b);
        return c.getRGB();
        //return 0xFF000000 + (r << 16) + (g << 8) + b;
    }
}
