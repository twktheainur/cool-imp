/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import java.awt.image.BufferedImage;
/**
 *
 * @author twk
 */
import Components.ImageCanvas;
import java.beans.PropertyChangeListener;

public class LaplacianFilter extends Filter {

    private boolean grayscale;

    public LaplacianFilter(ImageCanvas canvas, int size,PropertyChangeListener plc) {
        super(canvas, size, size);
        addPropertyChangeListener(plc);
    }

    protected String getGeneratedImageString(){
        return "UnsavedGaussianFiltered";
    }

    public boolean isGrayscale() {
        return grayscale;
    }

    public void setGrayscale(boolean grayscale) {
        this.grayscale = grayscale;
    }
    

    protected void generateFilter() {
        double[][] kernel;
        if (getKernelWidth() == 3) {
            kernel = new double[3][3];
            kernel[0][0] = 0;
            kernel[0][1] = 1;
            kernel[0][2] = 0;
            kernel[1][0] = 1;
            kernel[1][1] = -4;
            kernel[1][2] = 1;
            kernel[2][0] = 0;
            kernel[2][1] = 1;
            kernel[2][2] = 0;

        } else {
            kernel = new double[5][5];
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    kernel[x][y] = -1;
                }
            }
            kernel[2][2] = 24;
        }
        setKernel(kernel);
    }

    

    public BufferedImage applyFilter() {
        if(isGrayscale()){
            return convolveY();
        } else {
            return convolveRGB();
        }
    }
}
