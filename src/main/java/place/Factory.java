package place;

import org.sikuli.script.Region;

public class Factory extends BaseClass {
    public Factory(Region region, String pic) {
        super(region, pic);
    }

    public Factory() {
        super(new Region(1074, 29, 526, 268), "factory.png");
    }
}
