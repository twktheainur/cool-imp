/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author twk
 */
import Image.*;

public class TestPRog {

    public static void main(String[] args) {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    int rgb = RGBColor.combineRGB(i, j, k);
                    int y = YUVColor.extractY(rgb);
                    int u = YUVColor.extractU(rgb);
                    int v = YUVColor.extractV(rgb);
                    System.out.print(i);
                    System.out.print("|");
                    System.out.print(j);
                    System.out.print("|");
                    System.out.print(k);
                    System.out.print("<==>");
                    System.out.print(y);
                    System.out.print("|");
                    System.out.print(u);
                    System.out.print("|");
                    System.out.print(v);
                    System.out.print("<==>");
                    int rgb2 = YUVColor.getRGB(y, u, v);

                }
            }
        }
    }
}
