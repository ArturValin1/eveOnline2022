import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualProgram {
    JFrame jFrame;
    public static void main(String[] args) {
        VisualProgram vp = new VisualProgram();
        vp.prog();
    }

    public void prog() {
        try {
            Robot r = new Robot();
            r.delay(2_000);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        jFrame = new JFrame("example");
        JPanel jPanel = new JPanel();
        jPanel.setBounds(40, 40, 40, 40);
//        JButton jButton = new JButton("click me");
//        ActionListener al = new TestLis();
//        jButton.addActionListener(al);
//        jButton.setBounds(40, 40, 60, 60);
//        jPanel.add(jButton);

        jFrame.add(jPanel);
        jFrame.setBounds(400, 40, 60, 60);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        Start start = new Start(jFrame);
        try {
            start.main();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }

    public class TestLis implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          Start  start = new Start(jFrame);
            try {
                start.main();
            } catch (AWTException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
