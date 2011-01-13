/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Processing;
import java.awt.*;
import java.awt.image.*;
public class BufferedImageConverter {

  // default version of createBufferedImage
  static public BufferedImage createBufferedImage(Image imageIn,
      Component comp) {
    return createBufferedImage(imageIn, BufferedImage.TYPE_INT_ARGB, comp);
  }

  static public BufferedImage createBufferedImage(Image imageIn,
      int imageType, Component comp) {
    MediaTracker mt = new MediaTracker(comp);
    mt.addImage(imageIn, 0);
    try {
      mt.waitForID(0);
    } catch (InterruptedException ie) {
    }
    BufferedImage bufferedImageOut = new BufferedImage(imageIn
        .getWidth(null), imageIn.getHeight(null), imageType);
    Graphics g = bufferedImageOut.getGraphics();
    g.drawImage(imageIn, 0, 0, null);

    return bufferedImageOut;
  }
}
