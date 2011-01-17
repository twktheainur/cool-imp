/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ColorHistogramView.java
 *
 * Created on 11-Jan-2011, 21:49:09
 */
/**
 *
 * @author twk
 */
import Components.ImageCanvas;
import Processing.Histogram;
import java.awt.image.BufferedImage;

public class ColorHistogramView extends javax.swing.JFrame {

    public static int YUV_MODE = 0;
    public static int RGB_MODE = 1;
    private MainView mainView;
    private int mode;
    private ColorHistogramController controller;
    private ImageCanvas canvas;

    /** Creates new form ColorHistogramView */
    public ColorHistogramView(MainView v, int mode) {
        initComponents();
        this.mode = mode;
        mainView = v;
        canvas = v.getCurrentTabCanvas();
  
        if (mode == YUV_MODE) {
            lComponent1.setText("Y Histogram:");
            lComponent2.setText("U Histogram:");
            lComponent3.setText("V Histogram:");
        } else {
            lComponent1.setText("R Histogram:");
            lComponent2.setText("G Histogram:");
            lComponent3.setText("B Histogram:");
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

        pComponent1 = new javax.swing.JPanel();
        pComponent2 = new javax.swing.JPanel();
        pComponent3 = new javax.swing.JPanel();
        lComponent1 = new javax.swing.JLabel();
        lComponent2 = new javax.swing.JLabel();
        lComponent3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfMaxComponent1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfMinComponent1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfMaxComponent2 = new javax.swing.JTextField();
        tfMinComponent2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfMaxComponent3 = new javax.swing.JTextField();
        tfMinComponent3 = new javax.swing.JTextField();
        bClose = new javax.swing.JButton();
        bApply = new javax.swing.JButton();
        bOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Main.class).getContext().getResourceMap(ColorHistogramView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        pComponent1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pComponent1.setName("pComponent1"); // NOI18N
        pComponent1.setPreferredSize(new java.awt.Dimension(256, 256));

        javax.swing.GroupLayout pComponent1Layout = new javax.swing.GroupLayout(pComponent1);
        pComponent1.setLayout(pComponent1Layout);
        pComponent1Layout.setHorizontalGroup(
            pComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );
        pComponent1Layout.setVerticalGroup(
            pComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        pComponent2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pComponent2.setName("pComponent2"); // NOI18N
        pComponent2.setPreferredSize(new java.awt.Dimension(256, 256));

        javax.swing.GroupLayout pComponent2Layout = new javax.swing.GroupLayout(pComponent2);
        pComponent2.setLayout(pComponent2Layout);
        pComponent2Layout.setHorizontalGroup(
            pComponent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );
        pComponent2Layout.setVerticalGroup(
            pComponent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        pComponent3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pComponent3.setName("pComponent3"); // NOI18N
        pComponent3.setPreferredSize(new java.awt.Dimension(256, 256));

        javax.swing.GroupLayout pComponent3Layout = new javax.swing.GroupLayout(pComponent3);
        pComponent3.setLayout(pComponent3Layout);
        pComponent3Layout.setHorizontalGroup(
            pComponent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );
        pComponent3Layout.setVerticalGroup(
            pComponent3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        lComponent1.setText(resourceMap.getString("lComponent1.text")); // NOI18N
        lComponent1.setName("lComponent1"); // NOI18N

        lComponent2.setName("lComponent2"); // NOI18N

        lComponent3.setName("lComponent3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        tfMaxComponent1.setText(resourceMap.getString("tfMaxComponent1.text")); // NOI18N
        tfMaxComponent1.setName("tfMaxComponent1"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        tfMinComponent1.setText(resourceMap.getString("tfMinComponent1.text")); // NOI18N
        tfMinComponent1.setName("tfMinComponent1"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        tfMaxComponent2.setText(resourceMap.getString("tfMaxComponent2.text")); // NOI18N
        tfMaxComponent2.setName("tfMaxComponent2"); // NOI18N

        tfMinComponent2.setText(resourceMap.getString("tfMinComponent2.text")); // NOI18N
        tfMinComponent2.setName("tfMinComponent2"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        tfMaxComponent3.setText(resourceMap.getString("tfMaxComponent3.text")); // NOI18N
        tfMaxComponent3.setName("tfMaxComponent3"); // NOI18N

        tfMinComponent3.setText(resourceMap.getString("tfMinComponent3.text")); // NOI18N
        tfMinComponent3.setName("tfMinComponent3"); // NOI18N

        bClose.setMnemonic('C');
        bClose.setText(resourceMap.getString("bClose.text")); // NOI18N
        bClose.setName("bClose"); // NOI18N
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        bApply.setMnemonic('A');
        bApply.setText(resourceMap.getString("bApply.text")); // NOI18N
        bApply.setName("bApply"); // NOI18N

        bOK.setMnemonic('O');
        bOK.setText(resourceMap.getString("bOK.text")); // NOI18N
        bOK.setName("bOK"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMinComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMaxComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMinComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMaxComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMinComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMaxComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(599, Short.MAX_VALUE)
                .addComponent(bOK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bApply)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bClose)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lComponent3, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                    .addComponent(lComponent2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                    .addComponent(lComponent1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(tfMaxComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMinComponent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(tfMaxComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMinComponent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(tfMaxComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMinComponent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bClose)
                    .addComponent(bApply, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bOK, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void drawComponent1Histogram(BufferedImage img) {
        javax.swing.GroupLayout gl1 = (javax.swing.GroupLayout) pComponent1.getLayout();
        ImageCanvas ic1 = new ImageCanvas(img, pComponent1, 256, 256);
        gl1.setHorizontalGroup(
                gl1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ic1, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        gl1.setVerticalGroup(
                gl1.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ic1, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        ic1.setVisible(true);
    }

    public void drawComponent2Histogram(BufferedImage img) {
        ImageCanvas ic2 = new ImageCanvas(img, pComponent2, 256, 256);
        javax.swing.GroupLayout gl2 = (javax.swing.GroupLayout) pComponent2.getLayout();
        gl2.setHorizontalGroup(
                gl2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ic2, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        gl2.setVerticalGroup(
                gl2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ic2, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        ic2.setVisible(true);
    }

    public void drawComponent3Histogram(BufferedImage img) {
        ImageCanvas ic3 = new ImageCanvas(img, pComponent3, 256, 256);
        javax.swing.GroupLayout gl3 = (javax.swing.GroupLayout) pComponent3.getLayout();
        gl3.setHorizontalGroup(
                gl3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ic3, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        gl3.setVerticalGroup(
                gl3.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ic3, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        ic3.setVisible(true);
    }

    public int getMode() {
        return mode;
    }

    public ImageCanvas getCanvas() {
        return canvas;
    }
    
    
    public ColorHistogramController getController() {
        return controller;
    }

    public void setController(ColorHistogramController controller) {
        this.controller = controller;
    }

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
        mainView.setExclusiveActionInProgess(false);
    }//GEN-LAST:event_bCloseActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bApply;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bOK;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lComponent1;
    private javax.swing.JLabel lComponent2;
    private javax.swing.JLabel lComponent3;
    private javax.swing.JPanel pComponent1;
    private javax.swing.JPanel pComponent2;
    private javax.swing.JPanel pComponent3;
    private javax.swing.JTextField tfMaxComponent1;
    private javax.swing.JTextField tfMaxComponent2;
    private javax.swing.JTextField tfMaxComponent3;
    private javax.swing.JTextField tfMinComponent1;
    private javax.swing.JTextField tfMinComponent2;
    private javax.swing.JTextField tfMinComponent3;
    // End of variables declaration//GEN-END:variables
}
