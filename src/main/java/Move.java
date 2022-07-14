import org.sikuli.script.FindFailed;
import place.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Move {
    private Factory factory = new Factory();
    private Inventory inv = new Inventory();
    private Athanor athanor = new Athanor();
    private BaseClass belt;
    private ClickTo clickTo = new ClickTo();
    private Transfer transfer = new Transfer();
    private CloseOpenWindow cow = new CloseOpenWindow();
    private List<String> listAsteroids = new ArrayList<>();
    private Robot robot;
    private Control control = null;

    public Move(Robot robot) {
        this.robot = robot;
        addAsteroids();
        control = new Control(robot);
    }


    public void addAsteroids() {
        listAsteroids.add("veldspar.png");
        listAsteroids.add("scordite.png");
        listAsteroids.add("plagioclase.png");
    }

    public boolean moveToUnloadAndReturned() {
        boolean result = false;
        if (warpTo(athanor)) {
            robot.delay(5_000);
            while (control.inWarp()) {
                robot.delay(3_000);
            }
        }
        if (unload()) {
            robot.delay(1_000);
            result = warpTo(belt);
            robot.delay(5_000);
            while (control.inWarp()) {
                robot.delay(3_000);
            }
        }
        return result;
    }

    public boolean warpTo(BaseClass base) {
        boolean result = cow.isOpenWindow(base, robot);
        if (result) {
            clickTo.rightClick(base.getRegion(), base.getPic());
            robot.delay(500);
            result = clickTo.leftClick(base.getRegion(), "warpTo.png"); //контрольная проверка
            robot.delay(100);
        }
        cow.closedWindow(base, robot);
        return result;
    }

    public boolean unload() {
        boolean result = false;
        if (clickTo.leftClick(factory.getRegion(), factory.getPic())) {
            robot.delay(1_300);
            if (clickTo.leftClick(factory.getRegion(), "openFactoryHangar.png")) {
                if (cow.isOpenWindow(inv, robot)) {
                    clickTo.leftClick(inv.getRegion(), inv.getPic());
                    robot.delay(200);
                    for (String s : listAsteroids) {
                        if (clickTo.rightClick(inv.getRegion(), s)) {
                            robot.delay(300);
                            clickTo.leftClick(inv.getRegion(), "selectAll.png");
                            try {
                                transfer.getRegion().dragDrop(s, transfer.getRegion().find("dropItems.png"));
                                result = clickTo.leftClick(transfer.getRegion(), transfer.getPic());

                            } catch (FindFailed e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    robot.delay(200);
                    cow.closedWindow(inv, robot);
                }
            }
        }
        return result;
    }

    public void setBelt(BaseClass belt) {
        this.belt = belt;
    }
}