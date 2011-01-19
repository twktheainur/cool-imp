/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import Components.ImageCanvas;

/**
 *
 * @author twk
 */
public class MeanFilter extends Filter {

    public MeanFilter(ImageCanvas canvas, int size) {
        super(canvas, size, size);
    }

    public void done() {
        try {
            getResultObservable().setImage(get(), "UnsavedMeanFiltered");
        } catch (Exception e) {
            System.out.println("Error, filtering failed");
        }
    }

    protected void generateFilter() {
        double[][] kernel = new double[getKernelWidth()][getKernelHeight()];
        int div = getKernelHeight() * getKernelWidth();
        for (int x = 0; x < getKernelWidth(); x++) {
            for (int y = 0; y < getKernelHeight(); y++) {
                kernel[x][y] = 1.0 / (double) div;
            }
        }
        setKernel(kernel);
    }
}
