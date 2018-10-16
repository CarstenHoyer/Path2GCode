package Path2GCode.library;

import java.util.ArrayList;
import processing.core.*;

public class Path {
  protected PMatrix2D matrix = new PMatrix2D();
  ArrayList<PVector> vertices;
  public Path() {
    vertices = new ArrayList<PVector>();
  }

  public void add(PVector p) {
    vertices.add(p);
  }

  public void rotate(float angle, float c_x, float c_y) {
    matrix.translate(c_x, c_y);
    matrix.print();
    matrix.rotate(PApplet.radians(angle));
    matrix.translate(-1 * c_x, -1 * c_y);
    ArrayList<PVector> rotatedPath = new ArrayList<PVector>();
    for (int i = 0; i < vertices.size(); i++) {
      PVector rotated = matrix.mult(vertices.get(i), null);
      rotatedPath.add(rotated);
    }
    vertices = rotatedPath;
  }
 
  public PVector getCenter() {
    float mean_X = 0, mean_Y = 0;
    for (PVector p : vertices) {
      mean_X += p.x;
      mean_Y += p.y;
    }
    mean_X = mean_X / vertices.size();
    mean_Y = mean_Y / vertices.size();
    PVector center = new PVector(mean_X, mean_Y);
    return center;
  }
}
