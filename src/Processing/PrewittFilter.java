/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

/**
 *
 * @author twk
 */
import Components.ImageCanvas;
import java.beans.PropertyChangeListener;

public class PrewittFilter extends GradientFilter {

    public PrewittFilter(ImageCanvas canvas, int size, PropertyChangeListener plc) {
        super(canvas, size, plc);
    }


    @Override
    protected void generateFilter() {
        double[][] kernel_x;
        double[][] kernel_y;
        kernel_x = new double[3][3];
        kernel_y = new double[3][3];
        kernel_y[0][0] = -1;
        kernel_y[0][1] = 0;
        kernel_y[0][2] = 1;
        kernel_y[1][0] = -1;
        kernel_y[1][1] = 0;
        kernel_y[1][2] = 1;
        kernel_y[2][0] = -1;
        kernel_y[2][1] = 0;
        kernel_y[2][2] = 1;

        kernel_x[0][0] = -1;
        kernel_x[0][1] = -1;
        kernel_x[0][2] = -1;
        kernel_x[1][0] = 0;
        kernel_x[1][1] = 0;
        kernel_x[1][2] = 0;
        kernel_x[2][0] = 1;
        kernel_x[2][1] = 1;
        kernel_x[2][2] = 1;
        setKernel(kernel_x);
        setKernel_y(kernel_y);

    }
}
