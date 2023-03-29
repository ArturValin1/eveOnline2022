package helper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SliceToSymbol {
    //отделяем первый символ из изображения
    public BufferedImage[] sliceToSymbols(BufferedImage bi) {
        BufferedImage[] image = new BufferedImage[2];
        image[0] = null;
        image[1] = null;
        if (bi != null) {
            boolean isColor = false;
            boolean noZero = true;
            for (int x = 0; x < bi.getWidth(); x++) {
                for (int y = 0; y < bi.getHeight(); y++) {
                    Color color = new Color(bi.getRGB(x, y));
                    if (color.getBlue() > 0) {
                        isColor = true;
                        break;
                    }
                }
                if (isColor) {
                    isColor = false;
                } else {
                    image[0] = bi.getSubimage(0, 0, x, bi.getHeight());
                    image[1] = bi.getSubimage(x, 0, bi.getWidth() - x, bi.getHeight());
                    noZero = false;
                    break;
                }
            }
            if (noZero) {
                image[0] = bi;
            }
        }
        return image;
    }

    //все символы из строки
    public List<BufferedImage> getSymbolsFromStringImage(BufferedImage bi) {
        ImageUtils utils = new ImageUtils();
        List<BufferedImage> symbols = new ArrayList<>();
        BufferedImage[] im = sliceToSymbols(utils.cutPicture(bi));
        do {
            symbols.add(im[0]);
            im = sliceToSymbols(utils.cutPicture(im[1]));
        }
        while (im[0] != null);
        return symbols;
    }

    /**
     * Нарезаем строку на символы и удаляем повторяющиеся.
     * На выходе Список с уникальными образцами изображения.
     * Не всегда получается получить чистое изображение (остаются черные строки).
     * Необходимо прогнать метод два раза и обрезкой изображения.
     */
    public List<BufferedImage> getUniqueSymbolsFromImageString(BufferedImage bi, List<BufferedImage> imageList) {
        ImageUtils imageUtils = new ImageUtils();
        List<BufferedImage> list = getSymbolsFromStringImage(bi);
        list.forEach(v -> {
            AtomicInteger count = new AtomicInteger(0);
            if (imageList.size() > 0) {
                imageList.forEach(x -> {
                    Point p = imageUtils.findSubImageInBigImage(x, v);
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