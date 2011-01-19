/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import Components.ImageCanvas;

public class GaussianFilter extends Filter {

    public GaussianFilter(ImageCanvas canvas, int size) {
        super(canvas, size, size);
    }

    public void done(){
        try{
        getResultObservable().setImage(get(),"UnsavedGaussianFiltered");
        } catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            e.fillInStackTrace().printStackTrace();
            System.out.println("Error, Gaussien filtering failed");
        }
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
