/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import java.awt.image.BufferedImage;
import Image.RGBColor;
import Image.YUVColor;
/**
 *
 * @author twk
 */
import Components.ImageCanvas;

import java.beans.PropertyChangeListener;

public abstract class GradientFilter extends Filter {

    private boolean grayscale;
    private double[][] kernel_y;
    private int threshold;

    public GradientFilter(ImageCanvas canvas, int size, PropertyChangeListener plc) {
        super(canvas, size, size);
        addPropertyChangeListener(plc);
        threshold = 150;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    protected String getGeneratedImageString() {
        return "UnsavedGradientFiltered";
    }

    public boolean isGrayscale() {
        return grayscale;
    }

    public void setGrayscale(boolean grayscale) {
        this.grayscale = grayscale;
    }

    @Override
    abstract protected void generateFilter();

    @Override
    public BufferedImage applyFilter() {
        System.out.println(threshold);
        BufferedImage gX;
        BufferedImage gY;
        int imgW = getCanvas().getImage().getWidth();
        int imgH = getCanvas().getImage().getHeight();
        int type = (isGrayscale())?BufferedImage.TYPE_BYTE_GRAY:BufferedImage.TYPE_INT_RGB;
        
        BufferedImage result = new BufferedImage(imgW, imgH, type);
        setProgressFactor(0.3333);
        gX = convolve(getKernel(), getCanvas().getImage(), type);
        setProgressOffset(getProgress());
        gY = convolve(kernel_y, getCanvas().getImage(),type);
        setProgressOffset(getProgress());
        for (int x = 0; x < imgW; x++) {
            for (int y = 0; y < imgH; y++) {
                int xr = RGBColor.extractR(gX.getRGB(x, y));
                int xg = 0;
                int xb = 0;
                if (!isGrayscale()) {
                    xg = RGBColor.extractG(gX.getRGB(x, y));
                    xb = RGBColor.extractB(gX.getRGB(x, y));
                }
                int yr = RGBColor.extractR(gY.getRGB(x, y));
                int yg = 0, yb = 0;
                if (!isGrayscale()) {
                    yg = RGBColor.extractG(gY.getRGB(x, y));
                    yb = RGBColor.extractB(gY.getRGB(x, y));
                }

                //int result_r = Math.abs(yr) + Math.abs(xr);
                int result_r = (int)Math.sqrt(xr*xr + yr*yr);
                int result_g = result_r, result_b = result_r;
                if (!isGrayscale()) {
                    //result_g = Math.abs(yg) + Math.abs(xg);
                    result_g = (int)Math.sqrt(xg*xg + yg*yg);
                    result_b = (int)Math.sqrt(xb*xb + yb*yb);
                    //result_b = Math.abs(yb) + Math.abs(xb);
                }
                result_r = (result_r > 255) ? 255 : ((result_r < 0) ? 0 : result_r);
                if (!isGrayscale()) {
                    result_g = (result_g > 255) ? 255 : ((result_g < 0) ? 0 : result_g);
                    result_b = (result_b > 255) ? 255 : ((result_b < 0) ? 0 : result_b);
                }
                if (!isGrayscale()) {
                    result.setRGB(x, y, RGBColor.combineRGB(result_r, result_g, result_b));
                } else {
                    result.setRGB(x, y, RGBColor.combineRGB(result_r, result_r, result_r));
                }
            }
            setProgress((int) (getProgressFactor() * ((double) x / (double) imgW) * 100) + getProgressOffset());
        }
        return result;
    }

    public void setKernel_y(double[][] kernel_y) {
        this.kernel_y = kernel_y;
    }
}
