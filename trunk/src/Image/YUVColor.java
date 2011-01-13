/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Image;

import java.awt.Color;

public class YUVColor {

    private int y;
    private int u;
    private int v;

    public YUVColor(Color c) {
        convertRGB(c.getRed(), c.getGreen(), c.getBlue());
    }

    public YUVColor(int rgb) {
        convertRGB(RGBColor.extractR(rgb),
                RGBColor.extractG(rgb),
                RGBColor.extractB(rgb));
    }

    public YUVColor(int r, int g, int b) {
        convertRGB(r, g, b);
    }

    private void convertRGB(int r, int g, int b) {
        y = YUVColor.extractY(r, g, b);
        u = YUVColor.extractU(r, g, b);
        v = YUVColor.extractV(r, g, b);
    }

    public static int extractY(int r, int g, int b) {
        return 16 + (int) ((double) ((r * 65.738) / 256) +
                (double) ((g * 129.057) / 256) +
                (double) ((b * 25.064) / 256));
    }

    public static int extractY(int rgb) {
        int r = RGBColor.extractR(rgb);
        int g = RGBColor.extractG(rgb);
        int b = RGBColor.extractB(rgb);
        return YUVColor.extractY(r, g, b);
    }

    public static int extractU(int r, int g, int b) {
        return 128 + (int) (((double) (r * -37.945) / 256) +
                ((double) (g * -74.494) / 256) +
                ((double) (b * 112.139) / 256));
    }

    public static int extractU(int rgb) {
        int r = RGBColor.extractR(rgb);
        int g = RGBColor.extractG(rgb);
        int b = RGBColor.extractB(rgb);
        return YUVColor.extractU(r, g, b);

    }

    public static int extractV(int r, int g, int b) {
        return 128 + (int) (((double) (r * 112.439) / 256) +
                ((double) (g * -94.154) / 256) +
                ((double) (b * -18.285) / 256));
    }

    public static int extractV(int rgb) {
        int r = RGBColor.extractR(rgb);
        int g = RGBColor.extractG(rgb);
        int b = RGBColor.extractB(rgb);
        return YUVColor.extractV(r, g, b);
    }

    public Color getRGB() {
        int r = (int) ((double) y +
                (double) v * 1.13983);
        int g = (int) ((double) y +
                (double) u * -0.39465 +
                (double) v * -0.58060);
        int b = (int) ((double) y +
                (double) u * 2.03211);
        Color rgb = new Color(r, g, b);
        return rgb;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
