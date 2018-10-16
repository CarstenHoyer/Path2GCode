package Path2GCode.library;

import processing.core.*;
import java.util.ArrayList;

public class Triangle extends Creator {
  public Triangle(Device t_printer, Settings t_settings) {
    super(t_printer, t_settings);
  }
  
  public void generate (float c_x, float c_y, float length_side_cube) {
    generate(c_x, c_y, length_side_cube, 0);
  }
  
  public void generate(float c_x, float c_y, float length_side_cube, float rotation) {
    float half_length = length_side_cube / 2;
    paths = new ArrayList<Path>();
    Path path = new Path();
    PVector p1 = new PVector(c_x, c_y - half_length);
    path.add(p1);
    path.add(new PVector(c_x + half_length, c_y + half_length));
    path.add(new PVector(c_x - half_length, c_y + half_length));
    path.add(p1);
    paths.add(path);
    if (PApplet.abs(rotation) > 0) {
      path.rotate(rotation, c_x, c_y);
    }
  }
}