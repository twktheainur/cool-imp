
import Components.ImageCanvas;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ResizeView extends javax.swing.JFrame {

    private ResizeController controller;
    private ImageCanvas canvas;
    private MainView mainView;
    private boolean userChanged;

    /** Creates new form ResizeView */
    public ResizeView(MainView mv) {
        canvas = mv.getCurrentTabCanvas();
        mainView = mv;
        initComponents();
        setWidthValue(canvas.getImage().getWidth());
        setHeightValue(canvas.getImage().getHeight());
        setUserChanged(true);

        spWidth.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if (isLockOn() && isUserChanged()) {
                    double ratio = (double) getCanvas().getImage().getHeight() /
                            (double) getCanvas().getImage().getWidth();
                    int w = getWidthValue();
                    synchronized (this) {
                        setUserChanged(false);
                        setHeightValue((int) ((double) w * ratio));
                        setUserChanged(true);
                    }
                }
            }
        });

        spHeight.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if (isLockOn()) {
                    double ratio = (double) getCanvas().getImage().getHeight() /
                            (double) getCanvas().getImage().getWidth();
                    int h = getHeightValue();
                    synchronized (this) {
                        setUserChanged(false);
                        setWidthValue((int) ((double) h / ratio));
                        setUserChanged(true);
                    }
                }
            }
        });
    }

    public JToggleButton getTbLock() {
        return tbLock;
    }

    public void setController(ResizeController controller) {
        this.controller = controller;
    }

    public ImageCanvas getCanvas() {
        return canvas;
    }

    public MainView getMainView() {
        return mainView;
    }

    public boolean isUserChanged() {
        return userChanged;
    }

    public void setUserChanged(boolean userChange) {
        this.userChanged = userChange;
    }

    public void setWidthValue(int value) {
        SpinnerNumberModel widthSNM = (SpinnerNumberModel) spWidth.getModel();
        widthSNM.setValue(value);
    }

    public void setHeightValue(int value) {
        SpinnerNumberModel heightSNM = (SpinnerNumberModel) spHeight.getModel();
        heightSNM.setValue(value);
    }

    public int getWidthValue() {
        SpinnerNumberModel widthSNM = (SpinnerNumberModel) spWidth.getModel();
        return widthSNM.getNumber().intValue();
    }

    public int getHeightValue() {
        SpinnerNumberModel heightSNM = (SpinnerNumberModel) spHeight.getModel();
        return heightSNM.getNumber().intValue();
    }

    public boolean isLockOn() {
        return tbLock.isSelected();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spWidth = new javax.swing.JSpinner();
        spHeight = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        bCancel = new javax.swing.JButton();
        bOk = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        tbLock = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Main.class).getContext().getResourceMap(ResizeView.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        spWidth.setModel(new javax.swing.SpinnerNumberModel());
        spWidth.setName("spWidth"); // NOI18N

        spHeight.setModel(new javax.swing.SpinnerNumberModel());
        spHeight.setName("spHeight"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        bCancel.setIcon(resourceMap.getIcon("bCancel.icon")); // NOI18N
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

        bReset.setMnemonic('R');
        bReset.setText(resourceMap.getString("bReset.text")); // NOI18N
        bReset.setName("bReset"); // NOI18N
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        tbLock.setIcon(resourceMap.getIcon("tbLock.icon")); // NOI18N
        tbLock.setText(resourceMap.getString("tbLock.text")); // NOI18N
        tbLock.setName("tbLock"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spHeight)
                    .addComponent(spWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbLock, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(bReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCancel)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(spWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(tbLock)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancel)
                    .addComponent(bOk, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bReset))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkActionPerformed
        controller.applyResize();
    }//GEN-LAST:event_bOkActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        controller.resetResize();
    }//GEN-LAST:event_bResetActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        controller.cancelResize();
    }//GEN-LAST:event_bCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bOk;
    private javax.swing.JButton bReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner spHeight;
    private javax.swing.JSpinner spWidth;
    private javax.swing.JToggleButton tbLock;
    // End of variables declaration//GEN-END:variables
}
