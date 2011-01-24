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
        BufferedImage result = new BufferedImage(imgW, imgH, BufferedImage.TYPE_BYTE_GRAY);
        setProgressFactor(0.3333);
        gX = convolve(getKernel(), getCanvas().getImage(), BufferedImage.TYPE_BYTE_GRAY);
        setProgressOffset(getProgress());
        gY = convolve(kernel_y, getCanvas().getImage(), BufferedImage.TYPE_BYTE_GRAY);
        setProgressOffset(getProgress());
        for (int x = 0; x < imgW; x++) {
            for (int y = 0; y < imgH; y++) {
                int xgs = YUVColor.extractY(gX.getRGB(x, y));
                int ygs = YUVColor.extractY(gY.getRGB(x, y));
                int result_gs = (int)Math.sqrt(ygs*ygs - xgs*xgs);
                //int result_gs = Math.abs(ygs * ygs) + Math.abs(xgs * xgs);
                if (result_gs > threshold) {
                    result_gs = 255;
                } else {
                    result_gs = 0;
                }
                result.setRGB(x, y, RGBColor.combineRGB(result_gs, result_gs, result_gs));
            }
            setProgress((int) (getProgressFactor() * ((double) x / (double) imgW) * 100) + getProgressOffset());
        }
        return gX;
    }

    public void setKernel_y(double[][] kernel_y) {
        this.kernel_y = kernel_y;
    }
}
