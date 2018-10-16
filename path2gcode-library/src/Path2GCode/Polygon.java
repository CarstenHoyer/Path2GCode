package Path2GCode.library;

import processing.core.*;
import java.util.ArrayList;

public class Polygon extends Creator {
  public Polygon(Device t_printer, Settings t_settings) {
    super(t_printer, t_settings);
  }
  
  public void generate (float c_x, float c_y, float base_radius, float tip_radius, int corners) {
    generate(c_x, c_y, base_radius, tip_radius, corners, 0);
  }
  
  public void generate(float c_x, float c_y, float base_radius, float tip_radius, int corners, float rotation) {
    float rotator = (360f / corners);
    float side_length = (2 * base_radius) * PApplet.tan(PApplet.PI / corners);
    PVector p1 = new PVector(c_x - (side_length / 2), c_y + base_radius);
    PVector p2 = new PVector(c_x + (side_length / 2), c_y + base_radius);
    for (int n = 1; n <= corners; n++) {
      Path path = new Path();
      path.add(p1);
      path.add(p2);
      path.rotate((rotator * n) + rotation, c_x, c_y);
      paths.add(path);
    }
  }
}