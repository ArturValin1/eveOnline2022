import org.sikuli.script.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StartGame {
    public static void main(String[] args) {
        ImagePath.add("D:\\java\\eveOnline2022\\src\\main\\resources\\images"); //задаем папку с изображениями.
        ShipMining venture = new ShipMining(145, 5000, 16);
        ClickTo click = new ClickTo();
//        Region screen = new Screen().selectRegion();
        Region regionForUnload = new Region(1407, 27, 513, 815);
        String warpTorefery = "refenery_for_compress.png";
        String warpToAB1P1 = "agra_ab_1_p1.png";
        String redForge = "redForge.png";
        String openCargoDeposit = "openCargoDeposit.png";
        try {
            Robot robot = new Robot();
            robot.delay(1_400);
            Mining mining = new Mining(venture.getRangeLaser(), robot);
            Move move = new Move();
            move.warpToFromAltE(robot, warpTorefery);
            

//            int volumeAsteroid = mining.run();
//            float volumeLaser = (float) venture.getVolumeLaserMining() / 30;
//            float time = volumeAsteroid / volumeLaser;
//            double ceil = Math.ceil(time);
//            long timeStart = System.currentTimeMillis();
//            long timeEndMining = timeStart + (long) ceil * 1000;
//            System.out.println(ceil);
//            System.out.println(timeStart);
//            System.out.println(timeEndMining);
//            unLoadCargo(robot);
//            survey(robot);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

    public static void survey(Robot robot) {
        robot.keyPress(KeyEvent.VK_ALT);
        robot.delay(ThreadLocalRandom.current().nextInt(60, 206));
        robot.keyPress(KeyEvent.VK_F1);
        robot.delay(ThreadLocalRandom.current().nextInt(60, 206));
        robot.keyRelease(KeyEvent.VK_F1);
        robot.delay(ThreadLocalRandom.current().nextInt(60, 206));
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.delay(ThreadLocalRandom.current().nextInt(60, 206));
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
}
