package place;

import org.sikuli.script.Region;

import java.util.ArrayList;
import java.util.List;

//Класс для задания базовых элементов поиска.
//Включает регион для поиска и изображения в этом регионе, последовательность кнопок для нажатия.
public abstract class BaseClass {
    private Region region;
    private String pic;
    private List<Integer> keys = new ArrayList<>();

    public BaseClass(Region region, String pic) {
        this.region = region;
        this.pic = pic;
    }

    //кнопки для нажатия
    public void fillKeys(Integer integer) {
        keys.add(integer);
    }

    public Region getRegion() {
        return region;
    }

    public String getPic() {
        return pic;
    }

    public List<Integer> getKeys() {
        return keys;
    }
}