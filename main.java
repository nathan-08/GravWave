import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        runGUI();
      }
    });
  }
  private static void runGUI() {
    JFrame f = new JFrame ("GravWave");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Panel p = new Panel();
    f.add(p, BorderLayout.CENTER);

    ControlBar controlBar = new ControlBar(
        (Integer n) -> p.omega_cb(n),
        (Integer n) -> p.plus_cb(n),
        (Integer n) -> p.cross_cb(n),
        (Integer n) -> p.radius_cb(n)
        );
    f.add(controlBar, BorderLayout.SOUTH);

    f.pack();
    f.setVisible (true);

    new Timer(40, new ActionListener() {
      public void actionPerformed (ActionEvent e) {
        p.update();
      }
    }).start();
  }
}
