package Image;
import java.awt.image.BufferedImage;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.C2752A60-7672-2527-049F-74BBD1135F84]
// </editor-fold> 
public abstract class Reader {
    private String fileName;
    Reader(String filename){
        fileName = filename;
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1585E05B-D94C-9261-3FB8-F71679367A14]
    // </editor-fold> 
    public abstract BufferedImage read () throws java.io.IOException;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
}

