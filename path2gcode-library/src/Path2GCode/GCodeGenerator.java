package Path2GCode.library;

import processing.core.*;
import java.util.ArrayList;
import static java.lang.System.out;

class GcodeGenerator {
  
  ArrayList<String> gcode;
  Device printer;
  Settings settings;
  Processor processor;
  PApplet ctx;
  
  GcodeGenerator(Device t_printer, Settings t_settings, Processor t_processor, PApplet t_ctx) {
    printer = t_printer;
    settings = t_settings;
    processor = t_processor;
    ctx = t_ctx;
  }
  
  GcodeGenerator generate() {
    gcode = new ArrayList<String>();
    startPrint();
    for (Path path : processor.paths) {
      moveTo(path.vertices.get(0));
      write("G1 Z-50"); //Go to height 0
      for (int i = 0; i < path.vertices.size() - 1; i++) {
        PVector p1 = path.vertices.get(i);
        PVector p2 = path.vertices.get(i+1);
        moveTo(p1, p2);
      }
      write("G1 Z-49"); //Go to height 0
    }
    endPrint();
    return this;
  }
  
  void write(String command) {
    gcode.add(command);
  }
  
  void moveTo(PVector p) {
    write("G1 " + "X-" + p.x + " Y-" + p.y + " F" + settings.travel_speed);
  }
  
  void moveTo(PVector p1, PVector p2) {
    write("G1 " + "X-" + p2.x + " Y-" + p2.y);
  }
  
  void startPrint() {
    write("G90"); //Absolute mode
    write("G1 Z0 F600"); //Home Z
    write("G28"); //Home X and Y axes
    //write("G90"); //Absolute mode
  }
  
  void endPrint() {
    write("G1 Z0 F600"); //Up one millimeter
    write("G28");
  }
  
  void export() {
    //Create a unique name for the exported file
    String name_save = "gcode_"+PApplet.day()+""+PApplet.hour()+""+PApplet.minute()+"_"+PApplet.second()+".g";
    //Convert from ArrayList to array (required by saveString function)
    String[] arr_gcode = gcode.toArray(new String[gcode.size()]);
    // Export GCODE
    for (String line : arr_gcode) {
        System.out.println(line);
    }
    ctx.saveStrings(name_save, arr_gcode);
  }
}