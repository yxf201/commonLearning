package designPattern.factory;

public interface ImageReader {
    Object read(String path);

    public static void main(String[] args) {
        ImageReaderFactory factory = new JPGReaderFactory();
        String image = (String) factory.createImageReader().read("path");
        System.out.println(image);
    }
}

class GIFReader implements ImageReader {

    @Override
    public Object read(String path) {
        return "GIF";
    }
}

class JPGReader implements ImageReader {

    @Override
    public Object read(String path) {
        return "JPG";
    }
}

interface ImageReaderFactory {
    ImageReader createImageReader();
}

class GIFReaderFactory implements ImageReaderFactory {

    @Override
    public ImageReader createImageReader() {
        return new GIFReader();
    }
}

class JPGReaderFactory implements ImageReaderFactory {

    @Override
    public ImageReader createImageReader() {
        return new JPGReader();
    }
}




