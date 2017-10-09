package controller;

import model.Pixel;

public class SeedFill {

    public void floodFill(int coorX, int coorY, Pixel currentPixel, Pixel fillColor) {
        /*
        FloodFill(x, y, interiorColor, newColor)
        if (getPixel(x,y) == interiorColor)
            setpixel(x,y,newColor)
        FloodFill(x+1,y,interiorColor, newColor)
        FloodFill(x-1,y,interiorColor, newColor)
        FloodFill(x,y+1,interiorColor, newColor)
        FloodFill(x,y-1,interiorColor, newColor)
        */

        if (currentPixel.getARGB() == fillColor.getARGB()) {
            currentPixel.setColor(fillColor);
        }
        floodFill(coorX + 1, coorY, interiorColor, fillColor);
        floodFill(coorX - 1, coorY, interiorColor, fillColor);
        floodFill(coorX, coorY + 1, interiorColor, fillColor);
        floodFill(coorX, coorY - 1, interiorColor, fillColor);

    }


    public void boundaryFill() {

        // depuis les notes de cours
        /*
        BoundaryFill(x, y, boundaryColor, newColor)
        if (getPixel(x,y) <> boundaryColor &&
                getPixel(x,y) <> newColor)
        setpixel(x,y,newColor)
        BoundaryFill(x+1,y, boundaryColor, newColor)
        BoundaryFill(x-1,y, boundaryColor, newColor)
        BoundaryFill(x,y+1, boundaryColor, newColor)
        BoundaryFill(x,y-1, boundaryColor, newColor)
    */
    }

}
