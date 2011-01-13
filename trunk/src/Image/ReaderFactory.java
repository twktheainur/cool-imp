package Image;

public class ReaderFactory {

    public ReaderFactory () {
    }

    public static Reader getReader (String filename) {
        String exp;
        if(java.io.File.separator.equals("\\")){
            exp = "\\\\";
        }
        else{
            exp = java.io.File.separator;
        }
        String[] path_components = filename.split(exp);
        String file = path_components[path_components.length-1];
        String[] file_components = file.split("\\.");
        String extention = file_components[file_components.length-1];
        return new ImageIoReader(filename);
    }

}

