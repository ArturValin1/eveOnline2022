package helper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class BlackAndWhite {


    public int[] volumeAndRange(BufferedImage[] bufferedImages) {
        String km = "src/main/resources/images/R1900x600/kmBlack.png";
        BufferedImage kmImage = null;
        try {
            kmImage = cutPicture(ImageIO.read(new File(km)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] result = new int[2];
        if (bufferedImages[0] != null) {
            try {
                result[0] = Integer.parseInt(checkDigit(cutPicture(bufferedImages[0])));
                int pixelCutKm = findImage(bufferedImages[1], kmImage);
                if (pixelCutKm >= 0) {
                    result[1] = Integer.parseInt(checkDigit(cutPicture(bufferedImages[1].getSubimage(0, 0, pixelCutKm, bufferedImages[1].getHeight()))));
                } else
                    result[1] = 1;
            } catch (NumberFormatException e) {
                printImage(cutPicture(bufferedImages[0]));
                try {
                    System.out.println(checkDigit(cutPicture(bufferedImages[0])));
                    printImage(cutPicture(bufferedImages[0]));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Number not found.");
                result[0] = 1;
                result[1] = 111;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }


    public BufferedImage[] splitImageToVolumeAndRange(BufferedImage original, BufferedImage divide) {
        BufferedImage[] result = new BufferedImage[2];
        BufferedImage cutOriginal = cutPicture(original);
        BufferedImage cutDivide = cutPicture(divide);
        try {
            int pixelDivide = findImage(cutOriginal, cutDivide);
            if (pixelDivide >= 0) {
                result[0] = cutOriginal.getSubimage(0, 0, pixelDivide, cutOriginal.getHeight());
                result[1] = cutOriginal.getSubimage(pixelDivide + divide.getWidth(), 0, cutOriginal.getWidth() - pixelDivide - cutDivide.getWidth(), cutOriginal.getHeight());//
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String checkDigit(BufferedImage bi) throws IOException {
        StringBuilder sb = new StringBuilder();
        String folder = "src/main/resources/images/R1900x600/digitals/bw/";
        List<BufferedImage> images = new ArrayList<>();
        Map<BufferedImage, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(ImageIO.read(new File(String.format("%s%s.png", folder, i))), i);
        }
        bi = cutPicture(bi);
        BufferedImage[] im = sliceToDigital(bi);
        List<BufferedImage> imageList = new ArrayList<>();
        while (im[0] != null) {
            imageList.add(im[0]);
            im = sliceToDigital(cutPicture(im[1]));
        }
        imageList.forEach(e -> {
            boolean flag = true;
            for (BufferedImage bb : map.keySet()) {
                if (compareImage(e, bb)) {
                    sb.append(map.get(bb));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append("_");
            }
        });
        return sb.toString();
    }

    public BufferedImage[] sliceToDigital(BufferedImage bi) {
        BufferedImage[] im1 = new BufferedImage[2];
        if (bi != null) {
            int width = bi.getWidth();
            int height = bi.getHeight();
            boolean flag = false;
            Color color = null;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    color = new Color(bi.getRGB(i, j));
                    if (color.getBlue() > 0) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    flag = false;
                } else {
                    im1[0] = bi.getSubimage(0, 0, i, height);
                    im1[1] = bi.getSubimage(i, 0, width - (i), height);
                    break;
                }
            }
            if (im1[0] == null) {
                im1[0] = bi;
            }
        }
        return im1;
    }

    public void printImage(BufferedImage bi) {
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

    public boolean compareImage(BufferedImage im1, BufferedImage im2) {
        boolean result = false;
        boolean flag = true;
        int width1 = im1.getWidth();
        int width2 = im2.getWidth();
        int height1 = im1.getHeight();
        int height2 = im2.getHeight();
        Color color1 = null;
        Color color2 = null;
        if (width1 == width2 && height1 == height2) {
            for (int i = 0; i < height1; i++) {
                for (int j = 0; j < width1; j++) {
                    color1 = new Color(im1.getRGB(j, i));
                    color2 = new Color(im2.getRGB(j, i));
                    if (color1.equals(color2)) {
                        result = true;
                    } else {
                        result = false;
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
        }
        return result;
    }

    @Deprecated
    public int findImage(BufferedImage original, BufferedImage find) throws IOException {
        int result = -1;
        for (int i = 0; i < original.getWidth() - find.getWidth() + 1; i++) {
            if (compareImage(original.getSubimage(i, 0, find.getWidth(), find.getHeight()), find)) {
                result = i;
                break;
            }
        }
        return result;
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

    //Конвертируем в черно-белое изображение
    public BufferedImage convertToBlackAndWhite1(BufferedImage orginalImage) {
        BufferedImage blackAndWhiteImg = new BufferedImage(
                orginalImage.getWidth(), orginalImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);
        return blackAndWhiteImg;
    }

    //Обрезаем черные пиксели сверху изображения
    public BufferedImage sliceUpString(BufferedImage bi) {
        BufferedImage imageReturn = null;
        if (bi != null) {
            int width = bi.getWidth();
            int height = bi.getHeight();
            Color color = null;
            int cutHeight = 0;
            boolean flag = false;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    color = new Color(bi.getRGB(j, i));
                    if (color.getBlue() > 0) {
                        flag = true;
                        cutHeight = i;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            imageReturn = bi.getSubimage(0, cutHeight, width, height - cutHeight);
        }
        return imageReturn;
    }

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
                imageReturn[1] = bi.getSubimage(0, cutHeight, width, height - cutHeight);
            }
        }
        return imageReturn;
    }

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

    //все строки из изображения
    public List<BufferedImage> getStringPicsFromImage(BufferedImage bi) {
        List<BufferedImage> list = new ArrayList<>();
        BufferedImage[] images = sliceDownString(sliceUpString(bi));
        while (images[0] != null) {
            list.add(images[0]);
            images = sliceDownString(sliceUpString(images[1]));
        }
        return list;
    }

    //все символы из строки
    public List<BufferedImage> getSybolsFromStringImage(BufferedImage bi) {
        List<BufferedImage> symbols = new ArrayList<>();
        BufferedImage[] im = sliceToSymbols(cutPicture(bi));
        do {
            symbols.add(im[0]);
            im = sliceToSymbols(cutPicture(im[1]));
        }
        while (im[0] != null);
        return symbols;
    }
}