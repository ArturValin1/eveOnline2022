package helper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {
    //печатаем изображение.
    public void print(BufferedImage bi){
        System.out.println();
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        int width = bi.getWidth();
        int height = bi.getHeight();
        Color color = null;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                color = new Color(bi.getRGB(j, i));
                if (color.getBlue() > 0) {
                    System.out.print(ANSI_RED
                            + "2"
                            + ANSI_RESET);
                } else System.out.print(0);
            }
            System.out.println();
        }
    }

    //обрезаем рамку из черных пикселеё (незначащих) вокруг изображения
    public BufferedImage cutPicture(BufferedImage bi) {
        if (bi != null) {
            int width = bi.getWidth();
            int height = bi.getHeight();
            Color color;
            int x1 = -1;
            int x2 = -1;
            int y1 = -1;
            int y2 = -1;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    color = new Color(bi.getRGB(j, i));
                    if (x1 < 0 && color.getBlue() > 0) {
                        x1 = i;
                    }
                }
            }
            for (int i = height - 1; i > 0; i--) {
                for (int j = 0; j < width; j++) {
                    color = new Color(bi.getRGB(j, i));
                    if (x2 < 0 && color.getBlue() > 0) {
                        x2 = i;
                    }
                }
            }
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    color = new Color(bi.getRGB(i, j));
                    if (y1 < 0 && color.getBlue() > 0) {
                        y1 = i;
                    }
                }
            }
            for (int i = width - 1; i > 0; i--) {
                for (int j = 0; j < height; j++) {
                    color = new Color(bi.getRGB(i, j));
                    if (y2 < 0 && color.getBlue() > 0) {
                        y2 = i;
                    }
                }
            }
            return bi.getSubimage(y1, x1, y2 - y1 + 1, x2 - x1 + 1);
        } else return null;
    }
    //Конвертируем в черно-белое изображение
    public BufferedImage convertToBlackAndWhite1(BufferedImage orginalImage) {
        BufferedImage blackAndWhiteImg = new BufferedImage(
                orginalImage.getWidth(), orginalImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);
        return blackAndWhiteImg;
    }

    /*
     * Take from
     * https://github.com/jfalkner/find_image/blob/master/src/falkner/jayson/findimage/FindImage.java
     * remake for my purposes.
     * ищем в изображение в большом изображении.
     */
    public Point findSubImageInBigImage(BufferedImage subImage, BufferedImage image) {
        // brute force N^2 check all places in the image
        for (int i = 0; i <= image.getWidth() - subImage.getWidth(); i++) {
            check_subimage:
            for (int j = 0; j <= image.getHeight() - subImage.getHeight(); j++) {
                for (int ii = 0; ii < subImage.getWidth(); ii++) {
                    for (int jj = 0; jj < subImage.getHeight(); jj++) {
                        if (subImage.getRGB(ii, jj) != image.getRGB(i + ii, j + jj)) {
                            continue check_subimage;
                        }
                    }
                }
                // if here, all pixels matched
                return new Point(i, j);
            }
        }
        return null;
    }

}
