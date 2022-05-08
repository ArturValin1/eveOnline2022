import org.sikuli.script.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StartGame {
    static String warpTorefery = "refenery_for_compress.png";
    static String warpToAB1P1 = "agra_ab_1_p1.png";
    static String redForge = "redForge.png";
    static String openCargoDeposit = "openCargoDeposit1.png";
    static ClickTo click = new ClickTo();
    static Move move = new Move();
    static Mining mining;

    public static void main(String[] args) {
        ImagePath.add("D:\\java\\eveOnline2022\\src\\main\\resources\\images"); //задаем папку с изображениями.
        ShipMining venture = new ShipMining(145, 5000, 16);

        try {
            Robot robot = new Robot();
            mining = new Mining(venture.getRangeLaser(), robot);
            robot.delay(1_400);
            Region region = new Screen();

            int volumeAsteroid = mining.run();
            System.out.println(volumeAsteroid);
            float volumeLaser = (float) 4.8;
            float time = (volumeAsteroid / volumeLaser);
            double timeMining = Math.ceil(time) * 1_000;
            long timeStart = System.currentTimeMillis();
            long timeEndMining = timeStart + (long) timeMining;
            System.out.println(timeMining);
            float timeFillCargo = (float) (300 / (2 * 2.4)) * 1000;

            while (true) {
                if (System.currentTimeMillis() - timeStart >= timeFillCargo) {
                    unloadToRefenery(robot, region);
                    break;
                }
                if (System.currentTimeMillis() - timeStart >= timeEndMining) {
                    mining.run();
                    break;
                }
                robot.delay(3_000);
            }
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

    public static int moveToAsteroidAndStartMinig(Robot robot, Region region) {
        move.warpToFromAltE(robot, warpToAB1P1);
        robot.delay(28_000);
        move.pressReleaseTwoKeys(robot, KeyEvent.VK_ALT, KeyEvent.VK_F1);
        robot.delay(5_500);
        int ore = mining.run();
        robot.delay(3_000);
        move.pressReleaseOneKeys(robot, KeyEvent.VK_F1);
        robot.delay(333);
        move.pressReleaseOneKeys(robot, KeyEvent.VK_F1);
        return ore;
    }

    public static void unloadToRefenery(Robot robot, Region region) {
        move.warpToFromAltE(robot, warpTorefery);
        move.pressReleaseOneKeys(robot, KeyEvent.VK_F1);
        robot.delay(100);
        move.pressReleaseOneKeys(robot, KeyEvent.VK_F2);
        robot.delay(28_000);
        moveOreToDeposit(robot, region);
    }

    public static void moveOreToDeposit(Robot robot, Region region) {
        click.rightClick(region, redForge);
        robot.delay(600);
        click.leftClick(region, openCargoDeposit);
        robot.delay(500);
        try {
            Match matchOre = region.find("azure.png");
            Match matchDeposit = region.find("dropItems.png");
            region.dragDrop(matchOre, matchDeposit);
            robot.delay(100);
            click.leftClick(region, "transfer.png");
        } catch (FindFailed e) {
            e.printStackTrace();
        }
    }

    public static void unLoadCargo(Robot robot) {
        Region region = new Screen().selectRegion();
        ArrayList<String> listNameOre = new ArrayList<>(); //список с наименованиями картинок различных руд
        listNameOre.add("veldspar.png");
        listNameOre.add("scordite.png");
        listNameOre.add("plagioclase.png");
        for (String s : listNameOre) {
            try {
                Match match = region.exists(s);
                if (match != null) {
                    region.rightClick(s);
                    robot.delay(300);
                    region.click("selectAll");
                    region.rightClick(s);
                    robot.delay(300);
                    region.click("jettison");
                    break;
                }
            } catch (FindFailed e) {
                e.printStackTrace();
            }
        }
    }

    public static String isResolutionScreen() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        String result = String.format("%sx%s", (int) d.getWidth(), (int) d.getHeight());
        System.out.println(String.format("Resolution screen is %s", result));
        return result;
    }
}