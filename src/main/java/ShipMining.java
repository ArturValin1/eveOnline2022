public class ShipMining {
    private int volumeLaserMining;
    private int hangarMining;
    private int rangeLaser;

    public ShipMining(int volumeLaserMining, int hangarMining, int rangeLaser) {
        this.volumeLaserMining = volumeLaserMining;
        this.hangarMining = hangarMining;
        this.rangeLaser = rangeLaser;
    }

    public int getVolumeLaserMining() {
        return volumeLaserMining;
    }

    public int getHangarMining() {
        return hangarMining;
    }

    public int getRangeLaser() {
        return rangeLaser;
    }
}
