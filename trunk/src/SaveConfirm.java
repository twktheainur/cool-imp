
import Components.ImageCanvas;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QuitConfirm.java
 *
 * Created on 3 mars 2009, 08:14:42
 */


/**
 *
 */
public class SaveConfirm extends javax.swing.JDialog {
    private MainController controller;
    private ImageCanvas canvas;
    public SaveConfirm(java.awt.Frame parent, boolean modal, MainController ctr, ImageCanvas current_canvas) {
        super(parent, modal);
        initComponents();
        controller = ctr;
        canvas = current_canvas;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bYes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bNo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Main.class).getContext().getResourceMap(SaveConfirm.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setModal(true);
        setName("Form"); // NOI18N
        setResizable(false);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Main.class).getContext().getActionMap(SaveConfirm.class, this);
        bYes.setAction(actionMap.get("quit")); // NOI18N
        bYes.setText(resourceMap.getString("bYes.text")); // NOI18N
        bYes.setName("bYes"); // NOI18N
        bYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bYesActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        bNo.setMnemonic('n');
        bNo.setText(resourceMap.getString("bNo.text")); // NOI18N
        bNo.setName("bNo"); // NOI18N
        bNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(bNo))
                .addGap(18, 18, 18)
                .addComponent(bYes)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNo)
                    .addComponent(bYes))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bYesActionPerformed
        controller.saveFile(canvas);
        dispose();
    }//GEN-LAST:event_bYesActionPerformed

    private void bNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNoActionPerformed
        dispose();        
    }//GEN-LAST:event_bNoActionPerformed
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
    QuitConfirm dialog = new QuitConfirm(new javax.swing.JFrame(), true);
    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
    public void windowClosing(java.awt.event.WindowEvent e) {
    System.exit(0);
    }
    });
    dialog.setVisible(true);
    }
    });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bNo;
    private javax.swing.JButton bYes;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}