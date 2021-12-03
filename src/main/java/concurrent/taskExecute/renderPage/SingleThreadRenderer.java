package concurrent.taskExecute.renderPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleThreadRenderer {
    public void renderPage(CharSequence source){
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for( ImageInfo imageInfo : scanForImageInfo(source) ){
            imageData.add(imageInfo.downloadImage());
        }
        for( ImageData data : imageData ){
            renderImage(data);
        }
        int a = Arrays.stream(new int[]{}).min().getAsInt();
    }

    private void renderImage(ImageData data) {
    }

    private ImageInfo[] scanForImageInfo(CharSequence source) {
        return null;
    }

    private void renderText(CharSequence source) {
    }
}
