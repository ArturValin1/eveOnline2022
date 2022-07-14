import place.*;

import java.util.ArrayList;
import java.util.List;

public class Belts {
    private List<BaseClass> belts;

    public void initPlace(){
        List<BaseClass> list = new ArrayList<>();
        list.add(new Belt());
        list.add(new Belt1());
        list.add(new Belt2());
        list.add(new Belt2_1());
    }

    public List<BaseClass> getBelts() {
        return belts;
    }
}
