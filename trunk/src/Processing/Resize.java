package Processing;

import Components.ImageCanvas;
import Image.RGBColor;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Image;

public class Resize {

    private ImageCanvas canvas;

    public Resize(ImageCanvas canvas) {
        this.canvas = canvas;
    }

    private Color getRGBBilinear(int x, int y) {
        int x0, y0;
        int dx, dy;
        int r0, g0, b0, r1, b1, g1, r2, b2, g2, r3, b3, g3;
        int r, g, b;
        BufferedImage img = canvas.getImage();
        int w = img.getWidth();
        int h = img.getHeight();
        x0 = (int) Math.floor(x);
        y0 = (int) Math.floor(y);

        dx = x - x0;
        dy = y - y0;

        Color c0 = new Color(img.getRGB(x0, y0));
        r0 = c0.getRed();
        g0 = c0.getGreen();
        b0 = c0.getBlue();
        Color c1;
        if (x0 + 1 < w) {
            c1 = new Color(img.getRGB(x0 + 1, y0));
        } else {
            c1 = new Color(img.getRGB(x0, y0));
        }
        r1 = c1.getRed();
        g1 = c1.getGreen();
        b1 = c1.getBlue();
        Color c2;

        if (x0 + 1 < w && y0 + 1 < h) {
            c2 = new Color(img.getRGB(x0 + 1, y0 + 1));
        } else if (x0 + 1 >= w && y0 + 1 < h) {
            c2 = new Color(img.getRGB(x0, y0 + 1));
        } else if (x0 + 1 < w && y0 + 1 >= h) {
            c2 = new Color(img.getRGB(x0 + 1, y0));
        } else {
            c2 = new Color(img.getRGB(x0, y0));
        }
        r2 = c2.getRed();
        g2 = c2.getGreen();
        b2 = c2.getBlue();

        Color c3;
        if (y0 + 1 < h) {
            c3 = new Color(img.getRGB(x0, y0 + 1));
        } else {
            c3 = new Color(img.getRGB(x0, y0));
        }
        r3 = c3.getRed();
        g3 = c3.getGreen();
        b3 = c3.getBlue();

        r = lerp(lerp(r0, r1, dx), lerp(r3, r2, dx), dy);
        g = lerp(lerp(g0, g1, dx), lerp(g3, g2, dx), dy);
        b = lerp(lerp(b0, b1, dx), lerp(b3, b2, dx), dy);
        return new Color(r, g, b);
    }

    public Color getRGBBox(int x0, int y0, int x1, int y1) {
        int area = 0;
        int rsum = 0, bsum = 0, gsum = 0;
        int x;
        int y;
        double xsize = 1.0, ysize = 1.0;
        BufferedImage image = canvas.getImage();

        for (y = (int) Math.floor(y0); y < (int) Math.ceil(y1); y++) {
            double size = xsize;
            if (y < y0) {
                size = size * (1.0 - (y0 - y));
            }
            if (y < y1) {
                size = size * (1.0 - (y1 - y));
            }
            for (x = (int) Math.floor(x0); x < (int) Math.ceil(x1); x++) {
                size = ysize;
                if (x < x0) {
                    size = size * (1.0 - (x0 - x));
                }
                if (x < x1) {
                    size = size * (1.0 - (x1 - x));
                }
            }
            rsum = rsum + RGBColor.extractR(image.getRGB(x, y)) * (int) size;
            gsum = gsum + RGBColor.extractG(image.getRGB(x, y)) * (int) size;
            bsum = bsum + RGBColor.extractB(image.getRGB(x, y)) * (int) size;
            System.out.println(size);
            System.out.println(area);
            area = area+(int)size;
        }
        return new Color(rsum / area, gsum / area, bsum / area);
    }

    private int lerp(int v1, int v2, double ratio) {
        return (int) ((double) v1 * (1.0 - ratio) + (double) v2 * ratio);
    }

    public BufferedImage doResize(int neww, int newh) {
        int w = canvas.getImage().getWidth();
        int h = canvas.getImage().getHeight();
        Image img = canvas.getImage().getScaledInstance(neww, newh,Image.SCALE_AREA_AVERAGING);
        return BufferedImageConverter.createBufferedImage(img, canvas);
        /*if (newh < h && neww < w) {
            return doReduce(neww, newh);
        } else if (neww < w) {
            BufferedImage tmp = doBilinearStrech(w, newh);
            return doReduce(neww, newh);
        } else if (newh < h) {
            BufferedImage tmp = doBilinearStrech(neww, h);
            return doReduce(neww, newh);
        } else {
            return doBilinearStrech(neww, newh);
        }*/


    }

    public BufferedImage doReduce(int neww, int newh) {
        int w = canvas.getImage().getWidth();
        int h = canvas.getImage().getHeight();
        double x_ratio = neww / (double) w;
        double y_ratio = newh / (double) h;
        BufferedImage original = canvas.getImage();
        BufferedImage resized = new BufferedImage(neww, newh, canvas.getImage().getType());
        System.out.println(Double.toString(x_ratio) + "::" + Double.toString(y_ratio));
        for (int x = 0; x < neww; x++) {
            for (int y = 0; y < newh; y++) {
                int u = (int) ((double) x * (1.0 / x_ratio));
                int v = (int) ((double) y * (1.0 / y_ratio));
                if (u < w && v < h) {
                    int u0 = x * (int)(1.0 / x_ratio);
                    int v0 = y * (int)(1.0 / y_ratio);
                    int u1 = (x + 1) * (int)(1.0 /x_ratio);
                    int v1 = (y + 1) * (int)(1.0 /y_ratio);
                    Color c = getRGBBox(u0, v0, u1, v1);
                    resized.setRGB(x, y, c.getRGB());
                }
            }
        }
        return resized;
    }

    public BufferedImage doBilinearStrech(int neww, int newh) {
        int w = canvas.getImage().getWidth();
        int h = canvas.getImage().getHeight();
        double x_ratio = neww / (double) w;
        double y_ratio = newh / (double) h;
        double px, py;
        BufferedImage original = canvas.getImage();
        BufferedImage resized = new BufferedImage(neww, newh, canvas.getImage().getType());
        System.out.println(Double.toString(x_ratio) + "::" + Double.toString(y_ratio));
        for (int x = 0; x < neww; x++) {
            for (int y = 0; y < newh; y++) {
                int u = (int) ((double) x * (1.0 / x_ratio));
                int v = (int) ((double) y * (1.0 / y_ratio));
                if (u < w && v < h) {
                    Color c = getRGBBilinear(u, v);
                    resized.setRGB(x, y, c.getRGB());
                }

            }
        }
        return resized;
    }
}
