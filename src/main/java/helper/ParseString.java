package helper;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParseString {
    //Сопоставляем картинке цифру
    public String parseImageString(BufferedImage image) {
        SampleNumbers sm = new SampleNumbers();
        ImageUtils imageUtils = new ImageUtils();
        SliceToLine line = new SliceToLine();
        SliceToSymbol symbol = new SliceToSymbol();
        sm.initMap();
        StringBuilder sb = new StringBuilder();
        BufferedImage test = imageUtils.convertToBlackAndWhite1(image);
        List<BufferedImage> list =  line.getAllString(test);
        list.forEach(zz -> {
            List<BufferedImage> numbers = symbol.getSymbolsFromStringImage(zz);
            Set set = sm.getMap().keySet();
            Map<String, List<BufferedImage>> map = sm.getMap();
            numbers.forEach(w -> {
                set.forEach(v -> {
                    map.get(v).forEach(a -> {
                        if (imageUtils.findSubImageInBigImage(a, w) != null) {
                            sb.append(v);
                        }
                    });
                });
            });
            sb.append("\n");
        });
        System.out.println(sb);
        return sb.toString();
    }
}
