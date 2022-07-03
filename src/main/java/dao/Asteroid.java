package dao;

import org.sikuli.script.Region;

public class Asteroid {
    private Region region;
    private int volume;
    private int range;

    public Asteroid(Region region, int volume, int range) {
        this.region = region;
        this.volume = volume;
        this.range = range;
    }

    public Region getRegion() {
        return region;
    }

    public int getVolume() {
        return volume;
    }

    public int getRange() {
        return range;
    }
}
