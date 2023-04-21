package place;

import org.sikuli.script.Region;

public class Transfer extends BaseClass{
    public Transfer(Region region, String pic) {
        super(region, pic);
    }

    public Transfer() {
        super(new Region(62,35,1090,800), "transfer.png");
    }
}