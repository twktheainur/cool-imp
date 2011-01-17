/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import java.awt.image.BufferedImage;
import Components.ImageCanvas;
import Image.RGBColor;

abstract public class Filter {

    private double[][] kernel;
    private int kernelWidth;
    private int kernelHeight;
    private ImageCanvas canvas;
    private double bias;
    private double factor;

    public Filter(ImageCanvas canvas, int width, int height) {
        this.canvas = canvas;
        bias = 0.0;
        factor = 1.0;
        kernelHeight = height;
        kernelWidth = width;
        generateFilter();
    }

    private BufferedImage convolve() {
        int x, y;
        int imX, imY;
        BufferedImage source = canvas.getImage();
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();
        BufferedImage result = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_INT_RGB);
        int krX, krY;
        double rval = 0.0, gval = 0.0, bval = 0.0;

        double kernelTotal = 0.0;
        for (krX = 0; krX < kernelWidth; krX++) {
            for (krY = 0; krY < kernelHeight; krY++) {
                kernelTotal +=kernel[krX][krY];
            }
        }

        for (x = 0; x < sourceWidth; x++) {
            for (y = 0; y < sourceHeight; y++) {
                rval = 0.0;
                gval = 0.0;
                bval = 0.0;

                for (krX = 0; krX < kernelWidth; krX++) {
                    for (krY = 0; krY < kernelHeight; krY++) {
                        imX = (x - kernelWidth / 2 + krX + sourceWidth) % sourceWidth;
                        imY = (y - kernelHeight / 2 + krY + sourceHeight) % sourceHeight;
                        int rgb = source.getRGB(imX, imY);
                        rval += RGBColor.extractR(rgb) * kernel[krX][krY];
                        gval += RGBColor.extractG(rgb) * kernel[krX][krY];
                        bval += RGBColor.extractB(rgb) * kernel[krX][krY];
                    }
                    int r = (int)(factor*(double)rval/kernelTotal + bias) ;
                    int g = (int)(factor*(double)gval/kernelTotal + bias);
                    int b = (int)(factor*(double)bval/kernelTotal + bias);
                    result.setRGB(x, y, RGBColor.combineRGB(r, g, b));
                }
            }
        }

        return result;
    }

    public BufferedImage applyFilter() {
        return convolve();
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public double[][] getKernel() {
        return kernel;
    }

    public void setKernel(double[][] kernel) {
        this.kernel = kernel;
    }

    public int getKernelHeight() {
        return kernelHeight;
    }

    public void setKernelHeight(int kernelHeight) {
        this.kernelHeight = kernelHeight;
    }

    public int getKernelWidth() {
        return kernelWidth;
    }

    public void setKernelWidth(int kernelWidth) {
        this.kernelWidth = kernelWidth;
    }

    protected abstract void generateFilter();
}
