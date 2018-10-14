package Path2GCode.library;

import java.util.ArrayList;

public class Creator {
  ArrayList<Path> paths = new ArrayList<Path>();
  Device printer;
  Settings settings;
  public Creator(Device t_printer, Settings t_settings) {
    printer = t_printer;
    settings = t_settings;
  }
}