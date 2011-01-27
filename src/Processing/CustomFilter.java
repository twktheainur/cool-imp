/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Processing;

import Components.ImageCanvas;
import java.beans.PropertyChangeListener;

public class CustomFilter extends Filter {

    public CustomFilter(ImageCanvas canvas, double [][] kernel,int width, int height,PropertyChangeListener plc) {
        super(canvas,width,height);
        addPropertyChangeListener(plc);
        setKernel(kernel);
    }

    @Override
    protected String getGeneratedImageString(){
        return "UnsavedCustomFiltered";
    }
    
    @Override
    protected void generateFilter() {
    }
}
