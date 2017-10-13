package controller;

import model.ImageX;
import model.Pixel;

import java.awt.*;
import java.util.Stack;

public class SeedFill {

    public ImageX image;

    public SeedFill(ImageX imageEntree){
        this.image = imageEntree;
    }


    public void floodFill(int coorX, int coorY, Pixel clickedPixel, Pixel newColor) {

        if (image.getPixel(coorX,coorY).getARGB() == clickedPixel.getARGB()) {

            image.setPixel(coorX, coorY, newColor);

            floodFill(coorX + 1, coorY, clickedPixel, newColor);
            floodFill(coorX - 1, coorY, clickedPixel, newColor);
            floodFill(coorX, coorY + 1, clickedPixel, newColor);
            floodFill(coorX, coorY - 1, clickedPixel, newColor);
        }
    }


    public void boundaryFill(int coorX, int coorY, Pixel boundaryColor, Pixel newColor) {

        int currentPixelColor = image.getPixel(coorX, coorY).getARGB();

        if((currentPixelColor != boundaryColor.getARGB()) && ( currentPixelColor != newColor.getARGB())){
            image.setPixel(coorX, coorY, newColor);

            boundaryFill(coorX+1,coorY, boundaryColor, newColor);
            boundaryFill(coorX-1,coorY, boundaryColor, newColor);
            boundaryFill(coorX,coorY+1, boundaryColor, newColor);
            boundaryFill(coorX,coorY-1, boundaryColor, newColor);
        }

    }
}
