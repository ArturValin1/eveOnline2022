package place;

import org.sikuli.script.Region;

public class Factory extends BaseClass {
    public Factory(Region region, String pic) {
        super(region, pic);
    }

    public Factory() {
        super(new Region(1402, 130, 496, 309), "Athanor.png");
    }
}
