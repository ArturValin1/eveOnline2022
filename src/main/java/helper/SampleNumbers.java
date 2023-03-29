package helper;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleNumbers {
    private Map<String, List<BufferedImage>> map = new HashMap<>();

    public void initMap() {
        String path = "src/main/resources/images/R1080/digit/";
        map.put("0", new NumbersFromFiles(path + "0").getAllImageInFolder());
        map.put("1", new NumbersFromFiles(path + "1").getAllImageInFolder());
        map.put("2", new NumbersFromFiles(path + "2").getAllImageInFolder());
        map.put("3", new NumbersFromFiles(path + "3").getAllImageInFolder());
        map.put("4", new NumbersFromFiles(path + "4").getAllImageInFolder());
        map.put("5", new NumbersFromFiles(path + "5").getAllImageInFolder());
        map.put("6", new NumbersFromFiles(path + "6").getAllImageInFolder());
        map.put("7", new NumbersFromFiles(path + "7").getAllImageInFolder());
        map.put("8", new NumbersFromFiles(path + "8").getAllImageInFolder());
        map.put("9", new NumbersFromFiles(path + "9").getAllImageInFolder());
        map.put("^", new NumbersFromFiles(path + "cap").getAllImageInFolder());
        map.put("k", new NumbersFromFiles(path + "kRus").getAllImageInFolder());
        map.put("m", new NumbersFromFiles(path + "mRus").getAllImageInFolder());


//        map.put("0", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\0").getAllImageInFolder());
//        map.put("1", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\1").getAllImageInFolder());
//        map.put("2", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\2").getAllImageInFolder());
//        map.put("3", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\3").getAllImageInFolder());
//        map.put("4", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\4").getAllImageInFolder());
//        map.put("5", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\5").getAllImageInFolder());
//        map.put("6", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\6").getAllImageInFolder());
//        map.put("7", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\7").getAllImageInFolder());
//        map.put("8", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\8").getAllImageInFolder());
//        map.put("9", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\9").getAllImageInFolder());
////        map.put("^", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\cap").getAllImageInFolder()); //epsent
//        map.put("km", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\km").getAllImageInFolder());
//        map.put("m", new NumbersFromFiles("src/main/resources/images/R1900x600/testDigits\\m").getAllImageInFolder());


    }

    public Map<String, List<BufferedImage>> getMap() {
        return map;
    }
}