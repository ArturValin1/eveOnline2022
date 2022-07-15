import place.*;

import java.util.ArrayList;
import java.util.List;

public class Belts {
    private List<BaseClass> belts = new ArrayList<>();

    public void initPlace() {

        belts.add(new Belt());
        belts.add(new Belt1());
        belts.add(new Belt2());
        belts.add(new Belt2_1());
    }

    public List<BaseClass> getBelts() {
        return belts;
    }
}
