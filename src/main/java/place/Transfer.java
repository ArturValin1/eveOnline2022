package place;

import org.sikuli.script.Region;

public class Transfer extends BaseClass{
    public Transfer(Region region, String pic) {
        super(region, pic);
    }

    public Transfer() {
        super(new Region(71,52,1015,673), "transfer.png");
    }
}