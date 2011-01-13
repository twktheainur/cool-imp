/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Components.ImageCanvas;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.util.Observable;
import javax.swing.KeyStroke;
import javax.swing.JButton;

/**
 *
 * @author twk
 */
public class CropListener extends Observable implements MouseMotionListener, MouseListener{

    private int width;
    private int height;
    private int origX;
    private int origY;
    private int finalX;
    private int finalY;
    private ImageCanvas canvas;
    private boolean cropStarted;
    private MainView view;
    private JButton cropButton;

    public CropListener(ImageCanvas can, MainView v) {
        width = can.getImage().getWidth();
        height = can.getImage().getHeight();
        origX = -1;
        origY = -1;
        finalX = -1;
        finalY = -1;
        canvas = can;
        cropStarted = false;
        view = v;
        cropButton= v.getbCrop();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (origX == -1 || origY == -1) {
            origX = x;
            origY = y;
            cropStarted=true;
        }
        float dash1[] = {10.0f};
        BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        if (x < width -1 && y < height-1) {
            Graphics2D cg = (Graphics2D) canvas.getGraphics();
            canvas.setSelection(new Rectangle(origX, origY, x - origX, y - origY), dashed, Color.red);
            canvas.repaint();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if (cropStarted) {
            System.out.println("Finished crop!!!");
            finalX = e.getX();
            finalY = e.getY();
        }
    }

    private void clear_canvas() {
        canvas.setSelection(null, null, null);
        canvas.repaint();
        deleteObservers();
        canvas.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        canvas.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        canvas.removeMouseListener(this);
        canvas.removeMouseMotionListener(this);
        cropButton.setEnabled(true);
        view.setExclusiveActionInProgess(false);
    }

    public int getOrigX() {
        return origX;
    }

    public int getOrigY() {
        return origY;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public EnterBindingListener getEnterBindingListener() {
        return new EnterBindingListener(this);
    }
    public EscapeBindingListener getEscapeBindingListener() {
        return new EscapeBindingListener(this);
    }

    private class EnterBindingListener implements ActionListener {

        private CropListener cropListener;

        public EnterBindingListener(CropListener cropListener) {
            this.cropListener = cropListener;
        }

        public void actionPerformed(ActionEvent e) {
            cropListener.setChanged();
            cropListener.notifyObservers();
            cropListener.clear_canvas();
        }
    }

    private class EscapeBindingListener implements ActionListener {

        private CropListener cropListener;

        public EscapeBindingListener(CropListener cropListener) {
            this.cropListener = cropListener;
        }

        public void actionPerformed(ActionEvent e) {
            cropListener.clear_canvas();
        }
    }
}
