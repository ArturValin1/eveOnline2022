package helper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class SymbolsFromString {
    /**
     * Нарезаем строку на символы и удаляем повторяющиеся.
     * На выходе Список с образцами изображения.
     * Не всегда получается получить чистое изображение (остаются черные строки).
     * Необходимо прогнать метод два раза и обрезкой изображения.
     */


    public List<BufferedImage> getSetSymbolsFromImageString(BufferedImage bi, List<BufferedImage> imageList) {
        BlackAndWhite bw = new BlackAndWhite();
        List<BufferedImage> list = bw.getSybolsFromStringImage(bi);
        list.forEach(v -> {
            AtomicInteger count = new AtomicInteger(0);
            if (imageList.size() > 0) {
                imageList.forEach(x -> {
                    Point p = bw.findSubImageInBigImage(x, v);
                    if (p != null) {
                        count.incrementAndGet();
                    }
                });
                if (count.get() == 0) {
                    imageList.add(v);
                }
            } else {
                imageList.add(v);
            }
        });
        return imageList;
    }
}