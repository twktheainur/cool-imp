package Components;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.*;
import java.io.File;

public class ImageCanvas extends JComponent {

    private Container parent;
    private BufferedImage image;
    private boolean trueSizeKnown = false;
    private Dimension minSize;
    private Dimension maxSize;
    java.io.File file;
    private boolean changed;
    private boolean saved;
    int w, h;
    private Rectangle selection;
    private Stroke selectionStroke;
    private Color selectionColor;

    public ImageCanvas(BufferedImage image, Container parent,
            int initialWidth, int initialHeight) {
        if (image == null) {
            System.err.println("Canvas got invalid image object!");
            return;
        }

        this.image = image;
        this.parent = parent;

        w = initialWidth;
        h = initialHeight;
        minSize = new Dimension(w, h);
        maxSize = new Dimension(w, h);
        changed = false;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Dimension preferredSize() {
        return minimumSize();
    }

    public synchronized Dimension minimumSize() {
        return minSize;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Rectangle getSelection() {
        return selection;
    }

    public void setSelection(Rectangle selection, Stroke stroke, Color color) {
        this.selection = selection;
        selectionColor = color;
        selectionStroke = stroke;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (image != null) {
            if (!trueSizeKnown) {
                int imageWidth = image.getWidth(this);
                int imageHeight = image.getHeight(this);

                if ((imageWidth > 0) && (imageHeight > 0)) {
                    trueSizeKnown = true;

                    //Ooh... component-initiated resizing.
                    w = imageWidth;
                    h = imageHeight;
                    minSize = new Dimension(w, h);
                    setSize(w, h);
                    parent.doLayout();
                    parent.repaint();
                }
            }
            g.drawRect(0, 0, w - 1, h - 1);
            g.drawImage(image, 0, 0, this);
            if (selection != null) {
                g2.setColor(selectionColor);
                g2.setStroke(selectionStroke);
                g2.draw(selection);
            }
        }
    }
}
