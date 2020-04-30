/*
Copyright (C) 2016-2020 PRISM Development Team

PRISM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PRISM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PRISM. If not, see <http://www.gnu.org/licenses/>.
*/

public class Processing_Prism { // do not use processing now. If use later then activate the codes in comments
	
}


//import java.awt.EventQueue;
//
//import processing.core.PApplet;
//import processing.core.PImage;
//import processing.core.PShape;
//
//public class Processing_Prism extends PApplet {
//
//	public void settings() {
//		size(900, 360, P3D);
//	}
//
//	PImage starfield;
//
//	PShape sun;
//	PImage suntex;
//
//	PShape planet1;
//	PImage surftex1;
//	PImage cloudtex;
//
//	PShape planet2;
//	PImage surftex2;
//
//	public void setup() {
//
//		starfield = loadImage("spectrumlite1.png");
//		suntex = loadImage("spectrumlite2.png");
//		surftex1 = loadImage("icon_question.png");
//
//		// We need trilinear sampling for this texture so it looks good
//		// even when rendered very small.
//		// PTexture.Parameters params1 = PTexture.newParameters(ARGB,
//		// TRILINEAR);
//		surftex2 = loadImage("icon_main.png");
//
//		/*
//		 * // The clouds texture will "move" having the values of its u //
//		 * texture coordinates displaced by adding a constant increment // in
//		 * each frame. This requires REPEAT wrapping mode so texture //
//		 * coordinates can be larger than 1. //PTexture.Parameters params2 =
//		 * PTexture.newParameters(); //params2.wrapU = REPEAT; cloudtex =
//		 * createImage(512, 256);
//		 * 
//		 * // Using 3D Perlin noise to generate a clouds texture that is
//		 * seamless on // its edges so it can be applied on a sphere.
//		 * cloudtex.loadPixels(); Perlin perlin = new Perlin(); for (int j = 0;
//		 * j < cloudtex.height; j++) { for (int i = 0; i < cloudtex.width; i++)
//		 * { // The angle values corresponding to each u,v pair: float u =
//		 * float(i) / cloudtex.width; float v = float(j) / cloudtex.height;
//		 * float phi = map(u, 0, 1, TWO_PI, 0); float theta = map(v, 0, 1,
//		 * -HALF_PI, HALF_PI); // The x, y, z point corresponding to these
//		 * angles: float x = cos(phi) * cos(theta); float y = sin(theta); float
//		 * z = sin(phi) * cos(theta); float n = perlin.noise3D(x, y, z, 1.2, 2,
//		 * 8); cloudtex.pixels[j * cloudtex.width + i] = color(255, 255, 255,
//		 * 255 * n * n); } } cloudtex.updatePixels();
//		 */
//
//		noStroke();
//		fill(255);
//		sphereDetail(40);
//
//		sun = createShape(SPHERE, 150);
//		sun.setTexture(suntex);
//
////		planet1 = createShape(SPHERE, 150);
////		planet1.setTexture(surftex1);
//
//		planet2 = createShape(SPHERE, 50);
//		planet2.setTexture(surftex2);
//	}
//
//	public void draw() {
//		// Even we draw a full screen image after this, it is recommended to use
//		// background to clear the screen anyways, otherwise A3D will think
//		// you want to keep each drawn frame in the framebuffer, which results
//		// in
//		// slower rendering.
//		background(0);
//
//		// Disabling writing to the depth mask so the
//		// background image doesn't occludes any 3D object.
//		hint(DISABLE_DEPTH_MASK);
//		image(starfield, 0, 0, width, height);
//		hint(ENABLE_DEPTH_MASK);
//
//		pushMatrix();
//		translate(width / 2, height / 2, -300);
//
//		pushMatrix();
////		rotateX(-PI * frameCount / 300);
//		rotateY(-PI * frameCount / 300);
//		shape(sun);
//		popMatrix();
//
//		pointLight(255, 255, 255, 0, 0, 0);
////		rotateX(-PI * frameCount / 500);
//		rotateY(PI * frameCount / 500);
//		translate(0, 0, 300);
//
//		shape(planet2);
//
//		popMatrix();
//
//		noLights();
//		pointLight(255, 255, 255, 0, 0, -150);
//
//		translate((float) 0.75 * width, (float) 0.6 * height, 50);
////		shape(planet1);
//	}
//	
//	public void init() {
//		EventQueue.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				PApplet.main("Processing_SpectrumLite");
//			}
//		});
//	}
//
//	public void mousePressed() {
//		// exit();
//		if (looping) {
//			noLoop();
//		} else {
//			loop();
//		}
//	}
//	
//}
