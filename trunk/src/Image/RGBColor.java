/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Image;

/**
 *
 * @author twk
 */
public class RGBColor {

    public static int extractR(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }

    public static int extractG(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    public static int extractB(int rgb) {
        return (rgb & 0x000000FF);
    }
}
