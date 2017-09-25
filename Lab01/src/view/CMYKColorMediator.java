/*
   This file is part of j2dcg.
   j2dcg is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.
   j2dcg is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   You should have received a copy of the GNU General Public License
   along with j2dcg; if not, write to the Free Software
   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

/*
 * source consultée
 * http://www.rapidtables.com/convert/color/cmyk-to-rgb.htm
 * 
 * 
 */
package view;

import java.awt.image.BufferedImage;

import model.ObserverIF;
import model.Pixel;

class CMYKColorMediator extends Object implements SliderObserver, ObserverIF {
	ColorSlider cyanCS;
	ColorSlider magentaCS;
	ColorSlider yellowCS;
	ColorSlider keyCS;
	
	double cyan;
	double magenta;
	double yellow;
	double key;
	
	BufferedImage cyanImage;
	BufferedImage magentaImage;
	BufferedImage yellowImage;
	BufferedImage keyImage;
	
	int imagesWidth;
	int imagesHeight;
	ColorDialogResult result;
	
	CMYKColorMediator(ColorDialogResult result, int imagesWidth, int imagesHeight) {
		System.out.println("result" + result);
		this.imagesWidth = imagesWidth;
		this.imagesHeight = imagesHeight;
		
		double[] cmykColors = rgbTocmyk(result.getPixel().getRed(), result.getPixel().getGreen(), result.getPixel().getBlue());
		
		this.cyan = cmykColors[0];
		this.magenta = cmykColors[1];
		this.yellow = cmykColors[2];
		this.key = cmykColors[3];
		this.result = result;
		
		result.addObserver(this);
		
		cyanImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		magentaImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		yellowImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		keyImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		computeCyanImage(cyan, magenta, yellow, key);
		computeMagentaImage(cyan, magenta, yellow, key);
		computeYellowImage(cyan, magenta, yellow, key); 	
	}
	
	
	/*
	 * @see View.SliderObserver#update(double)
	 */
	public void update(ColorSlider s, int v) {
		boolean updateCyan = false;
		boolean updateMagenta = false;
		boolean updateYellow = false;
		if (s == cyanCS && v != cyan) {
			cyan = v;
			updateMagenta = true;
			updateYellow = true;
		}
		if (s == magentaCS && v != magenta) {
			magenta = v;
			updateCyan = true;
			updateYellow = true;
		}
		if (s == yellowCS && v != yellow) {
			yellow = v;
			updateCyan = true;
			updateMagenta = true;
		}
		if (updateCyan) {
			computeCyanImage(cyan, magenta, yellow, key);
		}
		if (updateMagenta) {
			computeMagentaImage(cyan, magenta, yellow, key);
		}
		if (updateYellow) {
			computeYellowImage(cyan, magenta, yellow, key);
		}
		
		int[] rgbColors = cmykTorgb(cyan, magenta, yellow, key);		
		Pixel pixel = new Pixel(rgbColors[0], rgbColors[1], rgbColors[2], 255);
		result.setPixel(pixel);
	}
	
	
	public void computeCyanImage(double cyan, double magenta, double yellow, double key) { 
		// passer cmyk et convertir pour utiliser rgb
		int[] rgbColors = cmykTorgb(cyan, magenta, yellow, key);		
		Pixel p = new Pixel(rgbColors[0], rgbColors[1], rgbColors[2], 255); 
		
		for (int i = 0; i<imagesWidth; ++i) {
			p.setRed((int)(((double)i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				cyanImage.setRGB(i, j, rgb);
			}
		}
		if (cyanCS != null) {
			cyanCS.update(cyanImage);
		}
	}
	
	public void computeMagentaImage(double cyan, double magenta, double yellow, double key) { 
		// passer cmyk et convertir pour utiliser rgb
		int[] rgbColors = cmykTorgb(cyan, magenta, yellow, key);		
		Pixel p = new Pixel(rgbColors[0], rgbColors[1], rgbColors[2], 255); 
		
		for (int i = 0; i<imagesWidth; ++i) {
			p.setGreen((int)(((double)i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				magentaImage.setRGB(i, j, rgb);
			}
		}
		if (magentaCS != null) {
			magentaCS.update(magentaImage);
		}
	}
	
	public void computeYellowImage(double cyan, double magenta, double yellow, double key) { 
		// passer cmyk et convertir pour utiliser rgb
		int[] rgbColors = cmykTorgb(cyan, magenta, yellow, key);		
		Pixel p = new Pixel(rgbColors[0], rgbColors[1], rgbColors[2], 255); 
		
		for (int i = 0; i<imagesWidth; ++i) {
			p.setBlue((int)(((double)i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				yellowImage.setRGB(i, j, rgb);
			}
		}
		if (yellowCS != null) {
			yellowCS.update(yellowImage);
		}
	}
	
	/**
	 * @return
	 */
	public BufferedImage getYellowImage() {
		return yellowImage;
	}

	/**
	 * @return
	 */
	public BufferedImage getMagentaImage() {
		return magentaImage;
	}

	/**
	 * @return
	 */
	public BufferedImage getCyanImage() {
		return cyanImage;
	}

	/**
	 * @param slider
	 */
	public void setCyanCS(ColorSlider slider) {
		cyanCS = slider;
		slider.addObserver(this);
	}

	/**
	 * @param slider
	 */
	public void setMagentaCS(ColorSlider slider) {
		magentaCS = slider;
		slider.addObserver(this);
	}

	/**
	 * @param slider
	 */
	public void setYellowCS(ColorSlider slider) {
		yellowCS = slider;
		slider.addObserver(this);
	}
	/**
	 * @return
	 */
	public double getYellow() {
		return yellow;
	}

	/**
	 * @return
	 */
	public double getMagenta() {
		return magenta;
	}

	/**
	 * @return
	 */
	public double getCyan() {
		return cyan;
	}


	/* (non-Javadoc)
	 * @see model.ObserverIF#update()
	 */
	public void update() {
		// When updated with the new "result" color, if the "currentColor"
		// is aready properly set, there is no need to recompute the images.
		
		int[] rgbColors = cmykTorgb(cyan, magenta, yellow, key);		
		Pixel currentColor = new Pixel(rgbColors[0], rgbColors[1], rgbColors[2], 255); 
		
		if(currentColor.getARGB() == result.getPixel().getARGB()) return;
		
		cyan = result.getPixel().getRed();
		magenta = result.getPixel().getGreen();
		yellow = result.getPixel().getBlue();
		
		cyanCS.setValue(rgbColors[0]);
		magentaCS.setValue(rgbColors[1]);
		yellowCS.setValue(rgbColors[2]);
		computeCyanImage(cyan, magenta, yellow, key);
		computeMagentaImage(cyan, magenta, yellow, key);
		computeYellowImage(cyan, magenta, yellow, key);
		
		// Efficiency issue: When the color is adjusted on a tab in the 
		// user interface, the sliders color of the other tabs are recomputed,
		// even though they are invisible. For an increased efficiency, the 
		// other tabs (mediators) should be notified when there is a tab 
		// change in the user interface. This solution was not implemented
		// here since it would increase the complexity of the code, making it
		// harder to understand.
	}
	
	/*
	The R,G,B values are divided by 255 to change the range from 0..255 to 0..1:
		R' = R/255
		G' = G/255
		B' = B/255
		The black key (K) color is calculated from the red (R'), green (G') and blue (B') colors:
		K = 1-max(R', G', B')
		The cyan color (C) is calculated from the red (R') and black (K) colors:
		C = (1-R'-K) / (1-K)
		The magenta color (M) is calculated from the green (G') and black (K) colors:
		M = (1-G'-K) / (1-K)
		The yellow color (Y) is calculated from the blue (B') and black (K) colors:
		Y = (1-B'-K) / (1-K)
	*/	
	
	//https://stackoverflow.com/questions/4982210/find-the-max-of-3-numbers-in-java-with-different-data-types
	public double[] rgbTocmyk(int r, int g,int b) {
		double R = r/255;
		double G = g/255;
		double B = b/255;

		double K = 1-Math.max(R, Math.max(G, B));		
		double C = (1-R-K) / (1-K);
		double M = (1-G-K) / (1-K);
		double Y = (1-B-K) / (1-K);
						
		return (new double[] {C,M,Y,K});
	}
	
	/*
	  	The R,G,B values are given in the range of 0..255.
		The red (R) color is calculated from the cyan (C) and black (K) colors:
		R = 255 × (1-C) × (1-K)
		The green color (G) is calculated from the magenta (M) and black (K) colors:
		G = 255 × (1-M) × (1-K)
		The blue color (B) is calculated from the yellow (Y) and black (K) colors:
		B = 255 × (1-Y) × (1-K)
	 */
	
	public int[] cmykTorgb(double c, double m, double y, double k) {

		int R = (int) Math.ceil(255*(1-c)*(1-k));
		int G = (int) Math.ceil(255*(1-m)*(1-k));
		int B = (int) Math.ceil(255*(1-y)*(1-k));
		
		return new int[] {R,G,B};
	}
}

