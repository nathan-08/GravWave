import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class ControlBar extends JPanel {
  public ControlBar(
      Consumer<Integer> omega_cb,
      Consumer<Integer> plus_cb,
      Consumer<Integer> cross_cb,
      Consumer<Integer> radius_cb
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

    JSlider radius_slider = new JSlider(10, 300, 200);
    add (new JLabel("Radius"));
    add (radius_slider);


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
    radius_slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        radius_cb.accept((Integer)radius_slider.getValue());
      }
    });
  }
}
