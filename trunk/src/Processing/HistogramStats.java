package Processing;

public class HistogramStats {

    private int currentMin;
    private int currentMax;
    private int newMin;
    private int newMax;
    private int [] histogram;
    private int [] cfd;
    private boolean toBeEqualized;
    private int width;
    private int height;

    public HistogramStats(int [] hist,int w, int h) {
        currentMin = minIndex(hist);
        currentMax = maxIndex(hist);
        newMin = currentMin;
        newMax = currentMax;
        histogram = hist;
        width = w;
        height = h;
        generateCfd();
    }

    public void display() {
        System.out.println("Min:" + String.valueOf(currentMin));
        System.out.println("Max:" + String.valueOf(currentMax));
        System.out.println("NewMin:" + String.valueOf(newMin));
        System.out.println("NewMax:" + String.valueOf(newMax));
        System.out.println(toBeEqualized);
    }

    public boolean isToBeEqualized(){
        return toBeEqualized;
    }

    public void setToBeEqualized(boolean equalize){
        toBeEqualized = equalize;
    }

    public boolean isChanged() {
        return currentMin != newMin || currentMax != newMax;
    }

    public int getCurrentMax() {
        return currentMax;
    }

    public void setCurrentMax(int currentMax) {
        this.currentMax = currentMax;
    }

    public int getCurrentMin() {
        return currentMin;
    }

    public void setCurrentMin(int currentMin) {
        this.currentMin = currentMin;
    }

    public int getNewMax() {
        return newMax;
    }

    public void setNewMax(int newMax) {
        this.newMax = newMax;
    }

    public int getNewMin() {
        return newMin;
    }

    public void setNewMin(int newMin) {
        this.newMin = newMin;
    }

    private int minIndex(int[] hist) {
        int i;
        for (i = 0; hist[i] == 0 && i < 256; i++) {
        }
        return i;
    }

    private int maxIndex(int[] hist) {
        int i;
        for (i = 255; hist[i] == 0 && i >= 0; i--) {
        }
        return i;
    }

    private void generateCfd(){
        cfd = new int[256];
        int sum = 0;
        
        

        for(int i=0;i<256;i++){
            sum+=histogram[i];
            cfd[i]=sum;
        }
    }

    public int[] getCfd() {
        return cfd;
    }

    public int getCfdValue(int i){
        return cfd[i];
    }
}
