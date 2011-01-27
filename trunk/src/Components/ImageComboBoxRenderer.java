/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

/**
 *
 * @author twk
 */
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class ImageComboBoxRenderer extends JLabel
        implements ListCellRenderer {

    private List<ImageIcon> images;
    private List<ImageCanvas> canvases;
    private List<String> names;

    public ImageComboBoxRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
        this.images = new ArrayList<ImageIcon>();
        this.names = new ArrayList<String>();
        this.canvases = new ArrayList<ImageCanvas>();
    }

    public void addItem(String name, Image img, ImageCanvas canvas) {
        ImageIcon imic = new ImageIcon(img);
        images.add(imic);
        names.add(name);
        canvases.add(canvas);
    }

    public ImageCanvas getCanvasAt(int i){
        return canvases.get(i);
    }

    

    /*
     * This method finds the image and text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     */
    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
            
        //Get the selected index. (The index param isn't
        //always valid, so just use the value.)
        int selectedIndex = ((Integer) value).intValue();
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        //Set the icon and text.  If icon was null, say so.
        if (selectedIndex<images.size()) {
            setIcon(images.get(selectedIndex));
            setText(names.get(selectedIndex));
        }
        setFont(list.getFont());
        return this;
    }
}
