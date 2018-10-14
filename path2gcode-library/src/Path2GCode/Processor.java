package Path2GCode.library;

import java.util.ArrayList;

public class Processor {
  ArrayList<Creator> objects = new ArrayList<Creator>();
  ArrayList<Canvas> canvases = new ArrayList<Canvas>();
  ArrayList<Path> paths;
  
  public Processor addObject(Creator object) {
    objects.add(object);
    return this;
  }
  
  public Processor addCanvas(Canvas canvas) {
    canvases.add(canvas);
    return this;
  }
  
  public void sortPaths() {
    paths = new ArrayList<Path>();
    for (int j = 0; j < objects.size(); j++) {
      Creator obj = objects.get(j);
      for (int i = 0; i < obj.paths.size(); i++) {
        paths.add(obj.paths.get(i));
      }
    }
      /*for (PShape path : obj.paths) {
        paths.add(path);
      }*/
    
    //Sort them from bottom to top layer
    /*Collections.sort(paths, new Comparator<Path>() {
      public int compare(Path o1, Path o2) {      
        return Float.compare(o1.getCenter().z, o2.getCenter().z);
      }
    }
    );*/
  }
  
}