package helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Из директории добавляем в список все файлы, а после и картинки
 */
public class NumbersFromFiles {
    private String path;

    public NumbersFromFiles(String path) {
        this.path = path;
    }

    public List<File> getListFile() {
        File file = new File(path);
        List<File> list = new ArrayList<>();
        Arrays.stream(file.listFiles()).forEach(w -> {
            if (w.isFile()) {
                list.add(w);
            }
        });
        return list;
    }

    public List<BufferedImage> getAllImageInFolder(List<File> fileList) {
        List<BufferedImage> imageList = new ArrayList<>();
        fileList.forEach(w -> {
            try {
                imageList.add(ImageIO.read(w));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return imageList;
    }
    public List<BufferedImage> getAllImageInFolder() {

        List<BufferedImage> imageList = new ArrayList<>();
        getListFile().forEach(w -> {
            try {
                imageList.add(ImageIO.read(w));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return imageList;
    }
}