
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

public class FileSaveView {

    private boolean selected;
    private String fileName;

    public FileSaveView(java.awt.Frame parent, String title) {
        selected = false;
        jOpenFileChooser = new javax.swing.JFileChooser();
        jOpenFileChooser.setDialogTitle(title);

        FileFilter allImageFilter = new FileNameExtensionFilter("Image files","jpeg","jpg","png","gif","bmp");
        FileFilter jpgFilter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
        FileFilter pngFilter = new FileNameExtensionFilter("PNG file", "png");
        FileFilter gifFilter = new FileNameExtensionFilter("GIF file", "gif");
        FileFilter bmpFilter = new FileNameExtensionFilter("BMP file", "bmp");
        jOpenFileChooser.setAcceptAllFileFilterUsed(false);
        jOpenFileChooser.addChoosableFileFilter(jpgFilter);
        jOpenFileChooser.addChoosableFileFilter(pngFilter);
        jOpenFileChooser.addChoosableFileFilter(gifFilter);
        jOpenFileChooser.addChoosableFileFilter(bmpFilter);
        jOpenFileChooser.addChoosableFileFilter(allImageFilter);

        int result = jOpenFileChooser.showSaveDialog(parent);
        if(result == JFileChooser.APPROVE_OPTION){
            selected = true;
            java.io.File sfile = jOpenFileChooser.getSelectedFile();
            fileName = sfile.getPath();
        }

    }

    public String getFileName() {
        return fileName;
    }

    public boolean isSelected() {
        return selected;
    }

    public String extractName(){
        String exp;
        if(java.io.File.separator.equals("\\")){
            exp = "\\\\";
        }
        else{
            exp = java.io.File.separator;
        }
        String[] path_components = fileName.split(exp);
        return path_components[path_components.length-1];
    }
    private javax.swing.JFileChooser jOpenFileChooser;

}