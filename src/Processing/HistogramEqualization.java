package Processing;

import java.awt.image.BufferedImage;
import Components.ImageCanvas;
import Image.*;
import java.beans.PropertyChangeListener;

public class HistogramEqualization extends ImageProcessor {

    private HistogramStats channelOneRange;
    private HistogramStats channelTwoRange;
    private HistogramStats channelThreeRange;
    private int mode;

    public HistogramEqualization(ImageCanvas canvas, HistogramStats channel1Range,
            HistogramStats channel2Range,
            HistogramStats channel3Range, int mode, PropertyChangeListener plc) {
        super(canvas);
        channelOneRange = channel1Range;
        channelTwoRange = channel2Range;
        channelThreeRange = channel3Range;
        this.mode = mode;
        addPropertyChangeListener(plc);

    }

    @Override
    public String getGeneratedImageString() {
        return "UnsavedContrastStretched";
    }

    public int getComponent1(int rgb) {
        if (mode == Histogram.RGB_MODE) {
            return RGBColor.extractR(rgb);
        } else {
            return YUVColor.extractY(rgb);
        }
    }

    public int getComponent2(int rgb) {
        if (mode == Histogram.RGB_MODE) {
            return RGBColor.extractG(rgb);
        } else {
            return YUVColor.extractU(rgb);
        }
    }

    public int getComponent3(int rgb) {
        if (mode == Histogram.RGB_MODE) {
            return RGBColor.extractB(rgb);
        } else {
            return YUVColor.extractV(rgb);
        }
    }

    @Override
    public BufferedImage process() {
        BufferedImage source = getCanvas().getImage();
        int sW = source.getWidth();
        int sH = source.getHeight();
        BufferedImage result = new BufferedImage(sW, sH, source.getType());
        boolean processC1 = channelOneRange.isChanged();
        boolean processC2 = channelTwoRange.isChanged();
        boolean processC3 = channelThreeRange.isChanged();
        // double eqFactor = 255 / sW * sH;

        if (processC1 || processC2 || processC3 ||
                channelOneRange.isToBeEqualized() ||
                channelTwoRange.isToBeEqualized() ||
                channelThreeRange.isToBeEqualized()) {
            for (int x = 0; x < sW; x++) {
                for (int y = 0; y < sH; y++) {
                    int rgb = source.getRGB(x, y);
                    int c1 = getComponent1(rgb);
                    int c2 = getComponent2(rgb);
                    int c3 = getComponent3(rgb);
                    if (!channelOneRange.isToBeEqualized()) {
                        if (processC1) {
                            c1 = channelOneRange.getNewMin() +
                                    (int)(((double) (channelOneRange.getNewMax() - channelOneRange.getNewMin()) /
                                    (double) (channelOneRange.getCurrentMax() - channelOneRange.getCurrentMin() + 0.000001)) *
                                    (double) (c1 - channelOneRange.getCurrentMin()));
                        }
                    } else {
                        //System.out.println(c1);
                        c1 = (int) (255 * ((double) (channelOneRange.getCfdValue(c1) - channelOneRange.getCfdValue(0)) /
                                (double) (sW * sH - channelOneRange.getCfdValue(0))));
                    }
                    if (!channelTwoRange.isToBeEqualized()) {
                        if (processC2) {
                            c2 = channelTwoRange.getNewMin() +
                                    (int) (((double) (channelTwoRange.getNewMax() - channelTwoRange.getNewMin()) /
                                    (double) (channelTwoRange.getCurrentMax() - channelTwoRange.getCurrentMin() + 0.000001)) *
                                    (double) (c2 - channelTwoRange.getCurrentMin()));
                        }
                    } else {
                        c2 = (int) (255 * ((double) (channelTwoRange.getCfdValue(c1) - channelTwoRange.getCfdValue(0)) /
                                (double) (sW * sH - channelTwoRange.getCfdValue(0))));
                    }
                    if (!channelThreeRange.isToBeEqualized()) {
                        if (processC3) {
                            c3 = channelThreeRange.getNewMin() +
                                    (int) (((double) (channelThreeRange.getNewMax() - channelThreeRange.getNewMin()) /
                                    (channelThreeRange.getCurrentMax() - channelThreeRange.getCurrentMin() + 0.000001)) *
                                    (c3 - channelThreeRange.getCurrentMin()));
                        }
                    } else {
                        c3 = (int) (255 * ((double) (channelThreeRange.getCfdValue(c1) - channelThreeRange.getCfdValue(0)) /
                                (double) (sW * sH - channelThreeRange.getCfdValue(0))));
                    }
                    if (mode == Histogram.RGB_MODE) {
                        result.setRGB(x, y, RGBColor.combineRGB(c1, c2, c3));
                    } else if (mode == Histogram.YUV_MODE) {
                        result.setRGB(x, y, YUVColor.getRGB(c1, c2, c3));
                    }
                }
                setProgress((int) (((double) x / (double) sW) * 100));
            }
            return result;
        } else {
            return source;
        }
    }
}
