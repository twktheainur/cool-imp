package Image;

public class WriterFactory {

    public WriterFactory () {
    }

    public static Writer getWriter (String file) {
        return new ImageIoWriter(file);
    }

}

