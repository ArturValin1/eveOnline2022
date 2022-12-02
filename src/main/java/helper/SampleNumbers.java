package helper;

import helper.NumbersFromFiles;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleNumbers {
    private Map<String, List<BufferedImage>> map = new HashMap<>();

    public void initMap() {
        map.put("0", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\0").getAllImageInFolder());
        map.put("1", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\1").getAllImageInFolder());
        map.put("2", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\2").getAllImageInFolder());
        map.put("3", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\3").getAllImageInFolder());
        map.put("4", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\4").getAllImageInFolder());
        map.put("5", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\5").getAllImageInFolder());
        map.put("6", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\6").getAllImageInFolder());
        map.put("7", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\7").getAllImageInFolder());
        map.put("8", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\8").getAllImageInFolder());
        map.put("9", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\9").getAllImageInFolder());
        map.put("^", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\cap").getAllImageInFolder());
        map.put("k", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\k").getAllImageInFolder());
        map.put("m", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\m").getAllImageInFolder());
    }

    public Map<String, List<BufferedImage>> getMap() {
        return map;
    }
}