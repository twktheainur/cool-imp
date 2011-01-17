/*
 * DesktopApplication2View.java
 */

import Components.ImageCanvas;
import Image.ReaderFactory;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;
import Image.Reader;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * The application's main frame.
 */
public class MainView extends FrameView {

    public MainView(SingleFrameApplication app, MainController ctr) {
        super(app);

        initComponents();

        controller = ctr;
        exclusiveActionInProgess = false;

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    public ImageCanvas getCurrentTabCanvas() {
        JScrollPane pane = (JScrollPane) imagesTab.getSelectedComponent();
        return (ImageCanvas) pane.getViewport().getComponents()[0];
    }

    public JTabbedPane getImagesTab() {
        return imagesTab;
    }

    public JButton getbCrop() {
        return bCrop;
    }

    public void setExclusiveActionInProgess(boolean exclusiveActionInProgess) {
        this.exclusiveActionInProgess = exclusiveActionInProgess;
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Main.getApplication().getMainFrame();
            aboutBox = new AboutBoxView(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Main.getApplication().show(aboutBox);
    }

    @Action
    public void addImage(String name, java.io.File file, BufferedImage image, boolean fromFile) {
        final int w = image.getWidth();
        final int h = image.getHeight();
        final ImageCanvas can = new ImageCanvas(image, imagesTab, w, h);
        can.setFocusable(true);
        can.setVisible(true);
        if (fromFile) {
            can.setFile(file);
        }
        can.setSaved(fromFile);
        can.setChanged(!fromFile);

        can.addMouseMotionListener(new MouseMotionListener() {

            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x < w && y < h) {
                    String coord = Integer.toString(x) +
                            "x" +
                            Integer.toString(y);
                    statusMessageLabel.setText(coord);
                }
            }

            public void mouseDragged(MouseEvent e) {
            }
        });

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(can);

        imagesTab.addTab(name, pane);
    }

    @Action
    public void displayStatusMessage(String message) {
        statusMessageLabel.setText(message);
    }

    @Action
    public void changeCurrentTabTitle(String title) {
//        imagesTab. imagesTab.getSelectedIndex();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        imagesTab = new javax.swing.JTabbedPane();
        toolbar = new javax.swing.JToolBar();
        bOpen = new javax.swing.JButton();
        bSave = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        bCrop = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        mOpen = new javax.swing.JMenuItem();
        mSave = new javax.swing.JMenuItem();
        mSaveAs = new javax.swing.JMenuItem();
        mClose = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        imageMenu = new javax.swing.JMenu();
        mCrop = new javax.swing.JMenuItem();
        mResize = new javax.swing.JMenuItem();
        mFuse = new javax.swing.JMenuItem();
        filterMenu = new javax.swing.JMenu();
        mGaussBlur = new javax.swing.JMenuItem();
        colorsMenu = new javax.swing.JMenu();
        mPixelInf = new javax.swing.JMenuItem();
        mColorHistogram = new javax.swing.JMenuItem();
        mConvertToGrayscale = new javax.swing.JMenuItem();
        mYUVMode = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        mAbout = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        imagesTab.setName("imagesTab"); // NOI18N

        toolbar.setRollover(true);
        toolbar.setBorderPainted(false);
        toolbar.setName("toolbar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Main.class).getContext().getResourceMap(MainView.class);
        bOpen.setIcon(resourceMap.getIcon("bOpen.icon")); // NOI18N
        bOpen.setMnemonic('O');
        bOpen.setText(resourceMap.getString("bOpen.text")); // NOI18N
        bOpen.setToolTipText(resourceMap.getString("bOpen.toolTipText")); // NOI18N
        bOpen.setFocusable(false);
        bOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bOpen.setName("bOpen"); // NOI18N
        bOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOpenActionPerformed(evt);
            }
        });
        toolbar.add(bOpen);

        bSave.setIcon(resourceMap.getIcon("bSave.icon")); // NOI18N
        bSave.setMnemonic('S');
        bSave.setText(resourceMap.getString("bSave.text")); // NOI18N
        bSave.setToolTipText(resourceMap.getString("bSave.toolTipText")); // NOI18N
        bSave.setFocusable(false);
        bSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSave.setName("bSave"); // NOI18N
        bSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });
        toolbar.add(bSave);

        bClose.setIcon(resourceMap.getIcon("bClose.icon")); // NOI18N
        bClose.setMnemonic('C');
        bClose.setText(resourceMap.getString("bClose.text")); // NOI18N
        bClose.setToolTipText(resourceMap.getString("bClose.toolTipText")); // NOI18N
        bClose.setFocusable(false);
        bClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bClose.setName("bClose"); // NOI18N
        bClose.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });
        toolbar.add(bClose);

        jSeparator1.setName("jSeparator1"); // NOI18N
        toolbar.add(jSeparator1);

        bCrop.setIcon(resourceMap.getIcon("bCrop.icon")); // NOI18N
        bCrop.setText(resourceMap.getString("bCrop.text")); // NOI18N
        bCrop.setToolTipText(resourceMap.getString("bCrop.toolTipText")); // NOI18N
        bCrop.setFocusable(false);
        bCrop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bCrop.setName("bCrop"); // NOI18N
        bCrop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bCrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCropActionPerformed(evt);
            }
        });
        toolbar.add(bCrop);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(imagesTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagesTab, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('F');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        mOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mOpen.setIcon(resourceMap.getIcon("mOpen.icon")); // NOI18N
        mOpen.setMnemonic('O');
        mOpen.setText(resourceMap.getString("mOpen.text")); // NOI18N
        mOpen.setToolTipText(resourceMap.getString("mOpen.toolTipText")); // NOI18N
        mOpen.setName("mOpen"); // NOI18N
        mOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mOpenActionPerformed(evt);
            }
        });
        fileMenu.add(mOpen);

        mSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mSave.setIcon(resourceMap.getIcon("mSave.icon")); // NOI18N
        mSave.setText(resourceMap.getString("mSave.text")); // NOI18N
        mSave.setToolTipText(resourceMap.getString("mSave.toolTipText")); // NOI18N
        mSave.setName("mSave"); // NOI18N
        mSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSaveActionPerformed(evt);
            }
        });
        fileMenu.add(mSave);

        mSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mSaveAs.setIcon(resourceMap.getIcon("mSaveAs.icon")); // NOI18N
        mSaveAs.setText(resourceMap.getString("mSaveAs.text")); // NOI18N
        mSaveAs.setToolTipText(resourceMap.getString("mSaveAs.toolTipText")); // NOI18N
        mSaveAs.setName("mSaveAs"); // NOI18N
        mSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSaveAsActionPerformed(evt);
            }
        });
        fileMenu.add(mSaveAs);

        mClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        mClose.setIcon(resourceMap.getIcon("mClose.icon")); // NOI18N
        mClose.setText(resourceMap.getString("mClose.text")); // NOI18N
        mClose.setToolTipText(resourceMap.getString("mClose.toolTipText")); // NOI18N
        mClose.setName("mClose"); // NOI18N
        mClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCloseActionPerformed(evt);
            }
        });
        fileMenu.add(mClose);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Main.class).getContext().getActionMap(MainView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        imageMenu.setMnemonic('I');
        imageMenu.setText(resourceMap.getString("imageMenu.text")); // NOI18N
        imageMenu.setName("imageMenu"); // NOI18N

        mCrop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mCrop.setIcon(resourceMap.getIcon("mCrop.icon")); // NOI18N
        mCrop.setText(resourceMap.getString("mCrop.text")); // NOI18N
        mCrop.setToolTipText(resourceMap.getString("mCrop.toolTipText")); // NOI18N
        mCrop.setName("mCrop"); // NOI18N
        mCrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCropActionPerformed(evt);
            }
        });
        imageMenu.add(mCrop);

        mResize.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mResize.setText(resourceMap.getString("mResize.text")); // NOI18N
        mResize.setToolTipText(resourceMap.getString("mResize.toolTipText")); // NOI18N
        mResize.setName("mResize"); // NOI18N
        mResize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mResizeActionPerformed(evt);
            }
        });
        imageMenu.add(mResize);

        mFuse.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mFuse.setText(resourceMap.getString("mFuse.text")); // NOI18N
        mFuse.setToolTipText(resourceMap.getString("mFuse.toolTipText")); // NOI18N
        mFuse.setName("mFuse"); // NOI18N
        imageMenu.add(mFuse);

        menuBar.add(imageMenu);

        filterMenu.setText(resourceMap.getString("filterMenu.text")); // NOI18N
        filterMenu.setName("filterMenu"); // NOI18N

        mGaussBlur.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mGaussBlur.setText(resourceMap.getString("mGaussBlur.text")); // NOI18N
        mGaussBlur.setToolTipText(resourceMap.getString("mGaussBlur.toolTipText")); // NOI18N
        mGaussBlur.setName("mGaussBlur"); // NOI18N
        mGaussBlur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mGaussBlurActionPerformed(evt);
            }
        });
        filterMenu.add(mGaussBlur);

        menuBar.add(filterMenu);

        colorsMenu.setText(resourceMap.getString("colorsMenu.text")); // NOI18N
        colorsMenu.setName("colorsMenu"); // NOI18N

        mPixelInf.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mPixelInf.setText(resourceMap.getString("mPixelInf.text")); // NOI18N
        mPixelInf.setToolTipText(resourceMap.getString("mPixelInf.toolTipText")); // NOI18N
        mPixelInf.setName("mPixelInf"); // NOI18N
        mPixelInf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPixelInfActionPerformed(evt);
            }
        });
        colorsMenu.add(mPixelInf);

        mColorHistogram.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mColorHistogram.setText(resourceMap.getString("mColorHistogram.text")); // NOI18N
        mColorHistogram.setToolTipText(resourceMap.getString("mColorHistogram.toolTipText")); // NOI18N
        mColorHistogram.setName("mColorHistogram"); // NOI18N
        mColorHistogram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mColorHistogramActionPerformed(evt);
            }
        });
        colorsMenu.add(mColorHistogram);

        mConvertToGrayscale.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mConvertToGrayscale.setText(resourceMap.getString("mConvertToGrayscale.text")); // NOI18N
        mConvertToGrayscale.setName("mConvertToGrayscale"); // NOI18N
        mConvertToGrayscale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mConvertToGrayscaleActionPerformed(evt);
            }
        });
        colorsMenu.add(mConvertToGrayscale);

        mYUVMode.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mYUVMode.setText(resourceMap.getString("mYUVMode.text")); // NOI18N
        mYUVMode.setName("mYUVMode"); // NOI18N
        colorsMenu.add(mYUVMode);

        menuBar.add(colorsMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        mAbout.setMnemonic('A');
        mAbout.setText(resourceMap.getString("mAbout.text")); // NOI18N
        mAbout.setName("mAbout"); // NOI18N
        mAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAboutActionPerformed(evt);
            }
        });
        helpMenu.add(mAbout);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 500, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void mAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAboutActionPerformed
        if (!exclusiveActionInProgess) {
            controller.displayAboutDialog();
        }
    }//GEN-LAST:event_mAboutActionPerformed

    private void mOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mOpenActionPerformed
        if (!exclusiveActionInProgess) {
            controller.openFile();
        }
    }//GEN-LAST:event_mOpenActionPerformed

    private void mSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSaveActionPerformed
        if (getCurrentTabCanvas() != null && !exclusiveActionInProgess) {
            controller.saveFile(getCurrentTabCanvas());
        }
    }//GEN-LAST:event_mSaveActionPerformed

    private void mSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSaveAsActionPerformed
        if (getCurrentTabCanvas() != null && !exclusiveActionInProgess) {
            controller.saveFileAs(getCurrentTabCanvas());
        }
    }//GEN-LAST:event_mSaveAsActionPerformed

    private void mCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCloseActionPerformed
        if (getCurrentTabCanvas() != null && !exclusiveActionInProgess) {
            controller.closeImage(getCurrentTabCanvas());
        }
    }//GEN-LAST:event_mCloseActionPerformed

    private void mCropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCropActionPerformed
        if (getCurrentTabCanvas() != null || !exclusiveActionInProgess) {
            exclusiveActionInProgess = true;
            bCrop.setEnabled(false);
            ImageCanvas current_canvas = getCurrentTabCanvas();
            CropListener cl = new CropListener(current_canvas, this);
            controller.observe(cl);
            current_canvas.registerKeyboardAction(cl.getEnterBindingListener(),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                    JComponent.WHEN_IN_FOCUSED_WINDOW);
            current_canvas.registerKeyboardAction(cl.getEscapeBindingListener(),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                    JComponent.WHEN_IN_FOCUSED_WINDOW);
            current_canvas.addMouseMotionListener(cl);
            current_canvas.addMouseListener(cl);
        }
    }//GEN-LAST:event_mCropActionPerformed

    private void bCropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCropActionPerformed
        if (!exclusiveActionInProgess && getCurrentTabCanvas() != null) {
            mCropActionPerformed(evt);
        }
    }//GEN-LAST:event_bCropActionPerformed

    private void bOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOpenActionPerformed
        mOpenActionPerformed(evt);
    }//GEN-LAST:event_bOpenActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        mSaveActionPerformed(evt);
    }//GEN-LAST:event_bSaveActionPerformed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        mCloseActionPerformed(evt);
    }//GEN-LAST:event_bCloseActionPerformed

    private void mPixelInfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPixelInfActionPerformed
        if (getCurrentTabCanvas() != null || !exclusiveActionInProgess) {
            exclusiveActionInProgess = true;
            controller.pixelColorInformation(getCurrentTabCanvas(), mYUVMode.isSelected());
        }
    }//GEN-LAST:event_mPixelInfActionPerformed

    private void mConvertToGrayscaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mConvertToGrayscaleActionPerformed
        if (getCurrentTabCanvas() != null || !exclusiveActionInProgess) {
            controller.convertToGrayscale(getCurrentTabCanvas());
        }
    }//GEN-LAST:event_mConvertToGrayscaleActionPerformed

    private void mColorHistogramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mColorHistogramActionPerformed
        if (getCurrentTabCanvas() != null || !exclusiveActionInProgess) {
            exclusiveActionInProgess = true;
            controller.colorHistogram(getCurrentTabCanvas(), mYUVMode.isSelected());
        }
    }//GEN-LAST:event_mColorHistogramActionPerformed

    private void mResizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mResizeActionPerformed
        controller.resize();
    }//GEN-LAST:event_mResizeActionPerformed

    private void mGaussBlurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mGaussBlurActionPerformed
        controller.applyGaussianBlur(getCurrentTabCanvas());
    }//GEN-LAST:event_mGaussBlurActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bCrop;
    private javax.swing.JButton bOpen;
    private javax.swing.JButton bSave;
    private javax.swing.JMenu colorsMenu;
    private javax.swing.JMenu filterMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu imageMenu;
    private javax.swing.JTabbedPane imagesTab;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JMenuItem mAbout;
    private javax.swing.JMenuItem mClose;
    private javax.swing.JMenuItem mColorHistogram;
    private javax.swing.JMenuItem mConvertToGrayscale;
    private javax.swing.JMenuItem mCrop;
    private javax.swing.JMenuItem mFuse;
    private javax.swing.JMenuItem mGaussBlur;
    private javax.swing.JMenuItem mOpen;
    private javax.swing.JMenuItem mPixelInf;
    private javax.swing.JMenuItem mResize;
    private javax.swing.JMenuItem mSave;
    private javax.swing.JMenuItem mSaveAs;
    private javax.swing.JCheckBoxMenuItem mYUVMode;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private MainController controller;
    private JDialog aboutBox;
    private boolean exclusiveActionInProgess;
}
