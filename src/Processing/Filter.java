/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import java.awt.image.BufferedImage;
import Components.ImageCanvas;
import Image.RGBColor;
import Image.YUVColor;

abstract public class Filter extends ImageProcessor{

    private double[][] kernel;
    private int kernelWidth;
    private int kernelHeight;
    private double progressFactor;
    private int progressOffset;

    public Filter(ImageCanvas canvas, int width, int height) {
        super(canvas);
        kernelHeight = height;
        kernelWidth = width;
        progressFactor = 1.0;
        progressOffset = 0;
        generateFilter();
    }

    protected BufferedImage convolveRGB() {
        return convolve(getKernel(), getCanvas().getImage(), BufferedImage.TYPE_INT_RGB);
    }

    protected BufferedImage convolveY() {
        return convolve(getKernel(), getCanvas().getImage(), BufferedImage.TYPE_BYTE_GRAY);
    }

    protected BufferedImage convolve(double[][] kernel, BufferedImage source, int type) {
        int x, y;
        int imX, imY;
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();
        BufferedImage result = new BufferedImage(sourceWidth, sourceHeight, type);
        int krX, krY;
        double rval = 0.0, gval = 0.0, bval = 0.0;
        double yval;

        double kernelTotal = 0.0;
        for (krX = 0; krX < kernelWidth; krX++) {
            for (krY = 0; krY < kernelHeight; krY++) {
                kernelTotal += kernel[krX][krY];
            }
        }

        for (x = 0; x < sourceWidth; x++) {
            for (y = 0; y < sourceHeight; y++) {
                rval = 0.0;
                gval = 0.0;
                bval = 0.0;
                yval = 0.0;

                for (krX = 0; krX < kernelWidth; krX++) {
                    for (krY = 0; krY < kernelHeight; krY++) {
                        imX = (x - kernelWidth / 2 + krX + sourceWidth) % sourceWidth;
                        imY = (y - kernelHeight / 2 + krY + sourceHeight) % sourceHeight;
                        int rgb = source.getRGB(imX, imY);
                        if (type == BufferedImage.TYPE_BYTE_GRAY) {
                            yval += YUVColor.extractY(rgb) * kernel[krX][krY];
                        } else {
                            rval += RGBColor.extractR(rgb) * kernel[krX][krY];
                            gval += RGBColor.extractG(rgb) * kernel[krX][krY];
                            bval += RGBColor.extractB(rgb) * kernel[krX][krY];
                        }
                    }
                    if (type == BufferedImage.TYPE_BYTE_GRAY) {
                        int gs = (int) ((double) yval / kernelTotal);
                        result.setRGB(x, y, RGBColor.combineRGB(gs, gs, gs));
                    } else {
                        int r = (int) ((double) rval / kernelTotal);
                        int g = (int) ((double) gval / kernelTotal);
                        int b = (int) ((double) bval / kernelTotal);
                        result.setRGB(x, y, RGBColor.combineRGB(r, g, b));
                    }
                }
            }
            setProgress((int)(progressFactor*((double)x/(double)sourceWidth)*100)+progressOffset);
        }

        return result;
    }

    public double getProgressFactor() {
        return progressFactor;
    }

    public void setProgressFactor(double progressFactor) {
        this.progressFactor = progressFactor;
    }

    public int getProgressOffset() {
        return progressOffset;
    }

    public void setProgressOffset(int progressOffset) {
        this.progressOffset = progressOffset;
    }

    

    public BufferedImage applyFilter() {
        return convolveRGB();
    }

    public BufferedImage process(){
        return applyFilter();
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
