package helper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SliceToLine {
    //отделяем строку с изображением
    public BufferedImage[] sliceDownString(BufferedImage bi) {
        BufferedImage[] imageReturn = new BufferedImage[2];
        imageReturn[0] = null;
        imageReturn[1] = null;
        if (bi != null) {
            int width = bi.getWidth();
            int height = bi.getHeight();
            Color color = null;
            int cutHeight = 1;
            boolean isWidht;
            for (int i = 0; i < height; i++) {
                isWidht = false;
                for (int j = 0; j < width; j++) {
                    color = new Color(bi.getRGB(j, i));
                    if (color.getBlue() == 0) {

                    } else {
                        isWidht = true;
                        break;
                    }
                }
                if (!isWidht) {
                    cutHeight = i;
                    break;
                }
            }
            if (cutHeight > 1) {
                imageReturn[0] = bi.getSubimage(0, 0, width, cutHeight);
                boolean checkHeight = imageReturn[0].getHeight() == bi.getHeight();
                if (!checkHeight) {
                    imageReturn[1] = bi.getSubimage(0, cutHeight, width, height - cutHeight);
                }
            } else {
                imageReturn[0] = bi;
            }
        }
        return imageReturn;
    }

    //все строки из изображения
    public List<BufferedImage> getAllString(BufferedImage bi) {
        List<BufferedImage> list = new ArrayList<>();
        ImageUtils imageUtils = new ImageUtils();
        BufferedImage[] images = sliceDownString(imageUtils.cutPicture(bi));
        while (true) {
            if (images[1] == null) {
                if (list.size() == 0) {
                    list.add(imageUtils.cutPicture(bi));
                } else { //костыль для добавления последней строки
                    BufferedImage b1 = list.get(list.size() - 1);
                    if (!b1.equals(images[0])) {
                        list.add(images[0]);
                    }
                }
                break;
            }
            list.add(images[0]);
            images = sliceDownString(imageUtils.cutPicture(images[1]));
        }
        return list;
    }
}