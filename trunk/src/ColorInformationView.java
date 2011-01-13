
import Image.RGBColor;
import Image.YUVColor;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ColorInformationWindow.java
 *
 * Created on 11-Jan-2011, 14:45:31
 */
/**
 *
 * @author twk
 */
public class ColorInformationView extends JFrame {

    private MainView mainView;
    private int mode;
    public static int YUV_MODE = 0;
    public static int RGB_MODE = 1;
    private CanvasListener canvasListener;

    /** Creates new form ColorInformationWindow */
    public ColorInformationView(MainView v, int mode) {
        initComponents();
        mainView = v;
        this.mode = mode;
        canvasListener = new CanvasListener(mainView.getCurrentTabCanvas(), this);
        if (mode == YUV_MODE) {
            lColorSpace.setText("YUV ");
            lComponent1.setText("Y: ");
            lComponent2.setText("U: ");
            lComponent3.setText("V: ");
        } else if (mode == RGB_MODE) {
            lColorSpace.setText("RGB ");
            lComponent1.setText("R: ");
            lComponent2.setText("G: ");
            lComponent3.setText("B: ");
        }
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void setColorInfo(int rgb) {
        int c1, c2, c3;
        if (mode == RGB_MODE) {
            c1 = RGBColor.extractR(rgb);
            c2 = RGBColor.extractG(rgb);
            c3 = RGBColor.extractB(rgb);
        } else {
            c1 = YUVColor.extractY(rgb);
            c2 = YUVColor.extractU(rgb);
            c3 = YUVColor.extractV(rgb);
        }
        tfComponent1.setText(Integer.toString(c1));
        tfComponent2.setText(Integer.toString(c2));
        tfComponent3.setText(Integer.toString(c3));
        pColor.setBackground(new Color(rgb));
    }

    public CanvasListener getCanvasListener() {
        return canvasListener;
    }

    private class CanvasListener implements MouseMotionListener {

        private int w;
        private int h;
        private Components.ImageCanvas imageCanvas;
        private ColorInformationView colorInformationWindow;

        public CanvasListener(Components.ImageCanvas ic, ColorInformationView ciw) {
            imageCanvas = ic;
            colorInformationWindow = ciw;
            this.w = ic.getImage().getWidth();
            this.h = ic.getImage().getHeight();
        }

        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x < w && y < h) {
                colorInformationWindow.setColorInfo(imageCanvas.getImage().getRGB(x, y));
            }
        }

        public void mouseDragged(MouseEvent e) {
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pColor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lCInfo = new javax.swing.JLabel();
        lColorSpace = new javax.swing.JLabel();
        tfComponent1 = new javax.swing.JTextField();
        tfComponent2 = new javax.swing.JTextField();
        tfComponent3 = new javax.swing.JTextField();
        lComponent1 = new javax.swing.JLabel();
        lComponent2 = new javax.swing.JLabel();
        lComponent3 = new javax.swing.JLabel();
        bClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Main.class).getContext().getResourceMap(ColorInformationView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setName("Form"); // NOI18N

        pColor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pColor.setName("pColor"); // NOI18N

        javax.swing.GroupLayout pColorLayout = new javax.swing.GroupLayout(pColor);
        pColor.setLayout(pColorLayout);
        pColorLayout.setHorizontalGroup(
            pColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pColorLayout.setVerticalGroup(
            pColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N

        lCInfo.setText(resourceMap.getString("lCInfo.text")); // NOI18N
        lCInfo.setName("lCInfo"); // NOI18N

        lColorSpace.setText(resourceMap.getString("lColorSpace.text")); // NOI18N
        lColorSpace.setName("lColorSpace"); // NOI18N

        tfComponent1.setText(resourceMap.getString("tfComponent1.text")); // NOI18N
        tfComponent1.setName("tfComponent1"); // NOI18N

        tfComponent2.setText(resourceMap.getString("tfComponent2.text")); // NOI18N
        tfComponent2.setName("tfComponent2"); // NOI18N

        tfComponent3.setText(resourceMap.getString("tfComponent3.text")); // NOI18N
        tfComponent3.setName("tfComponent3"); // NOI18N

        lComponent1.setName("lComponent1"); // NOI18N

        lComponent2.setName("lComponent2"); // NOI18N

        lComponent3.setName("lComponent3"); // NOI18N

        bClose.setIcon(resourceMap.getIcon("bClose.icon")); // NOI18N
        bClose.setMnemonic('C');
        bClose.setText(resourceMap.getString("bClose.text")); // NOI18N
        bClose.setName("bClose"); // NOI18N
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(pColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lColorSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lCInfo)
                            .addComponent(tfComponent2)
                            .addComponent(tfComponent3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(tfComponent1))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bClose)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lCInfo)
                    .addComponent(lColorSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(bClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        mainView.getCurrentTabCanvas().removeMouseMotionListener(canvasListener);
        mainView.setExclusiveActionInProgess(false);
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lCInfo;
    private javax.swing.JLabel lColorSpace;
    private javax.swing.JLabel lComponent1;
    private javax.swing.JLabel lComponent2;
    private javax.swing.JLabel lComponent3;
    private javax.swing.JPanel pColor;
    private javax.swing.JTextField tfComponent1;
    private javax.swing.JTextField tfComponent2;
    private javax.swing.JTextField tfComponent3;
    // End of variables declaration//GEN-END:variables
}
