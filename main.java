import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

class ControlBar extends JPanel {
  public ControlBar(
      Consumer<Integer> omega_cb,
      Consumer<Integer> plus_cb,
      Consumer<Integer> cross_cb
      ) {
    super(new FlowLayout(FlowLayout.LEFT));
    setBackground(new Color(255, 255, 255));

    JSlider omega_slider = new JSlider(0, 100, 0);
    add (new JLabel("ω"));
    add (omega_slider);

    JSlider plus_slider = new JSlider(0, 100, 0);
    add (new JLabel("A+"));
    add (plus_slider);

    JSlider cross_slider = new JSlider(0, 100, 0);
    add (new JLabel("A×"));
    add (cross_slider);


    omega_slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        omega_cb.accept((Integer)omega_slider.getValue());
      }
    });
    plus_slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        plus_cb.accept((Integer)plus_slider.getValue());
      }
    });
    cross_slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        cross_cb.accept((Integer)cross_slider.getValue());
      }
    });
  }
}

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
        (Integer n) -> p.cross_cb(n)
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
