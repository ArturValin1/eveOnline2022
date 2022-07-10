package temp;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Dd {
    public static void main(String[] args) {
        File file = new File("src/main/resources/images/R1900x600/digitals/bw");
        List<File> list = Arrays.stream(Objects.requireNonNull(file.listFiles())).toList();
        list.forEach(System.out::println);


    }
}
