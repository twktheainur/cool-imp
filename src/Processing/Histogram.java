/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import Components.ImageCanvas;
import Image.RGBColor;
import Image.YUVColor;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Histogram {

    private ImageCanvas canvas;
    public static int YUV_MODE = 0;
    public static int RGB_MODE = 1;
    int mode;

    public Histogram(ImageCanvas canvas, int mode) {
        this.canvas = canvas;
        this.mode = mode;
    }

    public int getComponent1(int x, int y) {
        int rgb = canvas.getImage().getRGB(x, y);
        if (mode == RGB_MODE) {
            return RGBColor.extractR(rgb);
        } else {
            return YUVColor.extractY(rgb);
        }
    }

    public int getComponent2(int x, int y) {
        int rgb = canvas.getImage().getRGB(x, y);
        if (mode == RGB_MODE) {
            return RGBColor.extractG(rgb);
        } else {
            return YUVColor.extractU(rgb);
        }
    }

    int getComponent3(int x, int y) {
        int rgb = canvas.getImage().getRGB(x, y);
        if (mode == RGB_MODE) {
            return RGBColor.extractR(rgb);
        } else {
            return YUVColor.extractV(rgb);
        }
    }

    public int[] getComponent1Histogram() {
        int w = canvas.getImage().getWidth();
        int h = canvas.getImage().getHeight();
        int[] hist = new int[256];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                hist[getComponent1(x, y)]++;
            }
        }
        return hist;
    }

    public int[] getComponent2Histogram() {
        int w = canvas.getImage().getWidth();
        int h = canvas.getImage().getHeight();
        int[] hist = new int[256];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                hist[getComponent2(x, y)]++;
            }
        }
        return hist;
    }

    public int[] getComponent3Histogram() {
        int w = canvas.getImage().getWidth();
        int h = canvas.getImage().getHeight();
        int[] hist = new int[256];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                hist[getComponent3(x, y)]++;
            }
        }
        return hist;
    }

    public int histMax(int[] hist) {
        int max = 0;
        for (int i = 0; i < 256; i++) {
            max = (max < hist[i]) ? hist[i] : max;
        }
        return max;
    }

    public BufferedImage getHistogramImage(int[] hist) {
        int max = histMax(hist);
        Color c = Color.black;
        int color = c.getRGB();
        BufferedImage histImage = new BufferedImage(256, 256, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = histImage.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 256, max);
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                if (y * (max / 255) < (hist[x])) {
                    histImage.setRGB(x, 255 - y, color);
                }
            }
        }
        return histImage;
    }
}
