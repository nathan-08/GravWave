import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

class Pair {
  public double x;
  public double y;
  public Pair(double x_, double y_) {
    x = x_;
    y = y_;
  }
  public int x_int() { return Math.round((float)x); }
  public int y_int() { return Math.round((float)y); }
}
public class Panel extends JPanel {
  private Vector<Pair> pairs = new Vector<Pair>();
  private double R = 200.0;
  private double t = 0;
  private double A_plus = 0.0;
  private double A_cross = 0.0;
  private double omega = 0.1;
  private int N = 1000;

  public Panel() {
    setBackground(new Color(20,20,20));
    // generate points of circle
    for (int i = 0; i < N; ++i) {
      double x = R * Math.cos(i*2*Math.PI/N);
      double y = R * Math.sin(i*2*Math.PI/N);
      pairs.add(new Pair(x, y));
    }
  }
  public Dimension getPreferredSize() {
    return new Dimension(880,480);
  }
  public void omega_cb(int o) {
    double new_omega = (double)o / 20.0;
    if (new_omega > 0.0) // smooth frequency update
      t = t * omega / new_omega;
    omega = new_omega;
  }
  public void plus_cb(int i) {
    A_plus = (double)i / N;
  }
  public void cross_cb(int i) {
    A_cross = (double)i / N;
  }
  public void radius_cb(int r) {
    R = (double)r;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
    int w = getWidth();
    int h = getHeight();
    g2d.translate(w/2, h/2); // Translate to cartesian coordinates
    g2d.scale(1, -1);

    g2d.setColor(new Color(0xf2,0xee,0xe6));

    for (int i = 0; i < pairs.size() - 1; ++i) {
      Pair p1 = pairs.get(i);
      Pair p2 = pairs.get(i+1);
      g2d.drawLine(p1.x_int(), p1.y_int(), p2.x_int(), p2.y_int());
    }
  }
  public void update() {
    t += 0.1;
    for (int i = 0; i < pairs.size(); ++i) {
      double A = 0.1;
      double theta = 2 * Math.PI * (i / (double)N);
      double new_R = R * (1 + A_plus  * Math.cos(omega * t) * Math.cos(2 * theta))
                       * (1 + A_cross * Math.cos(omega * t) * Math.sin(2 * theta));
      pairs.get(i).x = new_R * Math.cos(theta);
      pairs.get(i).y = new_R * Math.sin(theta);
    }
    repaint();
  }
}

