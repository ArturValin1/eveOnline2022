package dao;

import org.sikuli.script.Match;

public class RegionDigital {
    private Match match;
    private int digital;

    public RegionDigital(Match match, int digital) {
        this.match = match;
        this.digital = digital;
    }

    public Match getMatch() {
        return match;
    }

    public int getDigital() {
        return digital;
    }
}
