package digitals;

import helper.NumbersFromFiles;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleNumbers {
    private Map<String, List<BufferedImage>> map = new HashMap<>();

    public void initMap() {
        map.put("zero", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\0").getAllImageInFolder());
        map.put("one", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\1").getAllImageInFolder());
        map.put("two", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\2").getAllImageInFolder());
        map.put("three", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\3").getAllImageInFolder());
        map.put("four", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\4").getAllImageInFolder());
        map.put("five", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\5").getAllImageInFolder());
        map.put("six", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\6").getAllImageInFolder());
        map.put("seven", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\7").getAllImageInFolder());
        map.put("eight", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\8").getAllImageInFolder());
        map.put("nine", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\9").getAllImageInFolder());
        map.put("cap", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\cap").getAllImageInFolder());
        map.put("k", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\k").getAllImageInFolder());
        map.put("m", new NumbersFromFiles("D:\\java\\eveOnline2022\\src\\main\\resources\\images\\R1980x1080\\digitals\\m").getAllImageInFolder());
    }

    public Map<String, List<BufferedImage>> getMap() {
        return map;
    }
}