package Path2GCode.library;

import processing.core.*;

class Settings {
 float path_width = 0.4f; //mm
 float layer_height = 0.2f; //mm
 float filament_diameter = 1.75f; //mm
 float default_speed = 1500f; //mm/minute
 float travel_speed = 3000f; //mm/minute
 float extrusion_multiplier = 3f;
 float retraction_amount = 4.5f; //mm
 float retraction_speed = 5000f; //mm/minute
 
 float getExtrudedPathSection(){
	 return path_width * layer_height; //mm^2
 }
 
 float getFilamentSection(){
	 return PConstants.PI * PApplet.sq(filament_diameter/2.0f); //mm^2
 }
}