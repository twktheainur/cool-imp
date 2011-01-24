/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import Components.ImageCanvas;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GaussianFilter extends Filter {

    public GaussianFilter(ImageCanvas canvas, int size,PropertyChangeListener pcl) {
        super(canvas, size, size);
        this.addPropertyChangeListener(pcl);
    }

    protected String getGeneratedImageString(){
        return "UnsavedGaussianFiltered";
    }

    protected void generateFilter() {
        double[][] kernel = new double[getKernelWidth()][getKernelHeight()];
        int radius = getKernelWidth() / 2;
        double a = -2.0 * radius * radius / Math.log(0.16);
        for (int x = 0; x < getKernelWidth(); x++) {
            for (int y = 0; y < getKernelHeight(); y++) {
                double dist = Math.sqrt((x-radius)*(x-radius) + (y-radius)*(y-radius));
                kernel[x][y] = Math.exp(-dist*dist/a);
            }
        }
        setKernel(kernel);
    }
}
