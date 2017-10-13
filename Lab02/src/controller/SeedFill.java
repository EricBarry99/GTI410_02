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


    public void floodFillerw(Point ptClicked, Pixel clickedPixel, Pixel newColor){
        Stack<Point> stack = new Stack<>();
        stack.push(ptClicked);

        while (!stack.empty()) {
            Point current = stack.pop();
            if (current.x >= 0 && current.x < image.getImageWidth() && current.y >= 0 && current.y < image.getImageHeight()) {

                int pixelARGB = image.getPixel(current.x, current.y).getARGB();

                if (pixelARGB == clickedPixel.getARGB()) {
                    image.setPixel(current.x, current.y, newColor);
                    // Next points to fill.
                    Point nextLeft = new Point(current.x - 1, current.y);
                    Point nextRight = new Point(current.x + 1, current.y);
                    Point nextUp = new Point(current.x, current.y + 1);
                    Point nextDown = new Point(current.x, current.y - 1);

                    stack.push(nextUp);
                    stack.push(nextRight);
                    stack.push(nextLeft);
                    stack.push(nextDown);
                }
            }
        }
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


    public void boundaryFillw(Point ptClicked, Pixel boundaryColor, Pixel newColor){
        Stack<Point> stack = new Stack<>();
        stack.push(ptClicked);

        while (!stack.empty()) {
            Point current = stack.pop();
            if(current.x >= 0  && current.x < image.getImageWidth() && current.y >= 0 && current.y < image.getImageHeight()){
                int pixelARGB = image.getPixel(current.x, current.y).getARGB();

                if((pixelARGB != boundaryColor.getARGB()) && ( pixelARGB != newColor.getARGB())){

                    image.setPixel(current.x, current.y, newColor);
                    // Next points to fill.
                    Point nextLeft = new Point(current.x - 1, current.y);
                    Point nextRight = new Point(current.x + 1, current.y);
                    Point nextUp = new Point(current.x, current.y + 1);
                    Point nextDown = new Point(current.x, current.y - 1);

                    stack.push(nextUp);
                    stack.push(nextRight);
                    stack.push(nextLeft);
                    stack.push(nextDown);
                }
            }
        }
    }


}
