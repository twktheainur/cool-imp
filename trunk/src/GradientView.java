
import Components.ImageCanvas;

public class GradientView extends javax.swing.JFrame {

    private MainView mainView;
    private ImageCanvas canvas;
    private GradientController controller;

    public static int METHOD_SOBEL = 0;
    public static int METHOD_PREWITT = 1;

    public GradientView(MainView mv) {
        initComponents();
        this.mainView = mv;
        canvas = mv.getCurrentTabCanvas();
        buttonGroup1.add(rb33kernel);
        buttonGroup1.add(rb55kernel);
        buttonGroup1.setSelected(rb33kernel.getModel(),true);
    }

    public void setController(GradientController controller) {
        this.controller = controller;
    }

    public ImageCanvas getCanvas() {
        return canvas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        rb33kernel = new javax.swing.JRadioButton();
        rb55kernel = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        bCancel = new javax.swing.JButton();
        bOk = new javax.swing.JButton();
        bPreview = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        cbGrayscale = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Main.class).getContext().getResourceMap(GradientView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        rb33kernel.setText(resourceMap.getString("rb33kernel.text")); // NOI18N
        rb33kernel.setName("rb33kernel"); // NOI18N

        rb55kernel.setText(resourceMap.getString("rb55kernel.text")); // NOI18N
        rb55kernel.setName("rb55kernel"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        bCancel.setMnemonic('C');
        bCancel.setText(resourceMap.getString("bCancel.text")); // NOI18N
        bCancel.setName("bCancel"); // NOI18N
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        bOk.setMnemonic('O');
        bOk.setText(resourceMap.getString("bOk.text")); // NOI18N
        bOk.setName("bOk"); // NOI18N
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });

        bPreview.setMnemonic('P');
        bPreview.setText(resourceMap.getString("bPreview.text")); // NOI18N
        bPreview.setName("bPreview"); // NOI18N
        bPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPreviewActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N

        cbGrayscale.setText(resourceMap.getString("cbGrayscale.text")); // NOI18N
        cbGrayscale.setName("cbGrayscale"); // NOI18N
        cbGrayscale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGrayscaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bPreview)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rb55kernel)
                                    .addComponent(rb33kernel))
                                .addGap(22, 22, 22)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbGrayscale)))
                .addContainerGap(16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb33kernel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb55kernel))
                    .addComponent(cbGrayscale)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPreview)
                    .addComponent(bOk, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
    controller.cancelGradient();
}//GEN-LAST:event_bCancelActionPerformed

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkActionPerformed
    controller.applyGradient();
}//GEN-LAST:event_bOkActionPerformed

    public int getSelectedMethod(){
        if(buttonGroup1.getSelection().equals(rb33kernel.getModel())){
            return METHOD_SOBEL;
        }
        else{
            return METHOD_PREWITT;
        }
    }

    public boolean isGrayscale(){
        return cbGrayscale.isSelected();
    }

    private void bPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPreviewActionPerformed
    controller.previewGradient();
}//GEN-LAST:event_bPreviewActionPerformed

    private void cbGrayscaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGrayscaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGrayscaleActionPerformed

    public MainView getMainView() {
        return mainView;
    }

    public boolean isGrayscaleSelected(){
        return cbGrayscale.isSelected();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bOk;
    private javax.swing.JButton bPreview;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbGrayscale;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rb33kernel;
    private javax.swing.JRadioButton rb55kernel;
    // End of variables declaration//GEN-END:variables
}
