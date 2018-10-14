package Path2GCode.library;

import processing.core.*;

public class Drawer {
  Processor processor;
  Device device;
  PApplet ctx;
  /*int margin;
   float scale;
   float device_scaled_width;
   float device_scaled_height;
   float device_x;
   float device_y;
   float zoom_x;
   float zoom_y;
   float zoom_width;
   float zoom_height;*/
  float zoom, pan_x, pan_y;
  boolean zoom_in, zoom_out, pan_up, pan_down, pan_left, pan_right;
  float scale;

  public Drawer(Processor t_processor, Device t_device, PApplet t_ctx) {
    processor = t_processor;
    device = t_device;
    ctx = t_ctx;
    zoom = 1.0f;
    pan_x = 0; //950;
    pan_y = 0; //950;
    zoom_in = false;
    zoom_out = false;
    pan_up = false;
    pan_down = false;
    pan_left = false;
    pan_right = false;

    //margin = 50;
    //scale = min(width / device.d_width, height / device.d_height);
    /*device_scaled_width = device.d_width * scale;
     device_scaled_height = device.d_height * scale;
     device_x = (width - device_scaled_width) / 2;
     device_y = (height - device_scaled_height) / 2;*/
  }

  public void zoomIn(boolean toggle) {
    zoom_in = toggle;
  }

  public void zoomOut(boolean toggle) {
    zoom_out = toggle;
  }

  public void panUp(boolean toggle) {
    pan_up = toggle;
  }

  public void panDown(boolean toggle) {
    pan_down = toggle;
  }

  public void panRight(boolean toggle) {
    pan_right = toggle;
  }

  public void panLeft(boolean toggle) {
    pan_left = toggle;
  }

  public void zoomAndPan() {
    if (zoom_out) {
      zoom -= 0.03;
    }
    if (zoom_in) {
      zoom += 0.03;
    }
    if (pan_left) {
      pan_x += 10;
    }
    if (pan_right) {
      pan_x -= 10;
    }
    if (pan_up) {
      pan_y += 10;
    }
    if (pan_down) {
      pan_y -= 10;
    }
  }

  public void display() {
    setDefaultZoomAndPan();
    zoomAndPan();
    ctx.translate(ctx.width / 2, ctx.height / 2, 0);
    ctx.scale(zoom);
    ctx.translate(pan_x, pan_y, 0);
    showDevice();
    for (Canvas canvas : processor.canvases) {
      showCanvas(canvas);
    }
    for (int i = 0; i < processor.paths.size(); i++) {
      showPath(processor.paths.get(i));
    }
  }

  void setDefaultZoomAndPan() {
    Float minX = null;
    Float minY = null;
    Float maxX = null;
    Float maxY = null;
    for (Canvas canvas : processor.canvases) {
      minX = (minX != null) ? PApplet.min(minX, canvas.x) : canvas.x;
      minX = (minY != null) ? PApplet.min(minY, canvas.y) : canvas.y;
      maxX = (maxX != null) ? PApplet.min(minX, canvas.x + canvas.c_width) : canvas.x + canvas.c_width;
      maxY = (maxY != null) ? PApplet.min(minY, canvas.y + canvas.c_height) : canvas.y + canvas.c_height;
    }
    if (minX != null && maxX != null && minY != null && maxY != null) {
      
    }
  }

  void showPath(Path path) { //<>// //<>//
    for (int i = 0; i < path.vertices.size() - 1; i++) {
      PVector p1 = path.vertices.get(i);
      PVector p2 = path.vertices.get(i + 1);
      ctx.pushMatrix();
      ctx.stroke(0);
      ctx.strokeWeight(0.5f);
      ctx.line(
        (-1 * device.d_width / 2) + (p1.x), 
        (-1 * device.d_height / 2) + (p1.y), 
        (-1 * device.d_width / 2) + (p2.x), 
        (-1 * device.d_height / 2) + (p2.y)
        );
      //line(p1.x * scale, p1.y * scale, p2.x * scale, p2.y * scale);
      ctx.popMatrix();
    }
  }

  void showCanvas(Canvas canvas) {
    ctx.pushMatrix();
    ctx.fill(255);
    ctx.noStroke();
    //rect(canvas.x * scale, canvas.y * scale, canvas.c_width * scale, canvas.c_height * scale);
    ctx.rect(
      (-1 * device.d_width / 2) + (canvas.x), 
      (-1 * device.d_height / 2) + (canvas.y), 
      canvas.c_width, 
      canvas.c_height
      );
    ctx.popMatrix();
  }

  void showDevice() {
	ctx.pushMatrix();
	ctx.fill(71, 0, 0);
	ctx.noStroke();
	ctx.rect(-1 * device.d_width / 2, -1 * device.d_height / 2, device.d_width, device.d_height);
    //rect(0, 0, device.d_width * zoom_factor, device.d_height * zoom_factor);
	ctx.popMatrix();
  }
}
