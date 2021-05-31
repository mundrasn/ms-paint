/* Based on the ecs 100 template
 * Code for ??
 * Name:
 * Date:
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import javax.swing.JColorChooser;


/** <description of class DrawShape>
 */
public class DrawShape{
    
    private double xValue = 100;
    private double yValue = 100;
    private Color currentColor = Color.black;
    private boolean circle; 
    private boolean rectangle; 
    private boolean line;
    
    private double startX, startY; // fields to remember the pressed position
   
    /**      */
    public DrawShape(){
        UI.initialise();
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Color Chooser
     */
    public void doChooseColor(){
        this.currentColor = JColorChooser.showDialog(null, "Choose Color", this.currentColor);
        UI.setColor(this.currentColor);
    }
    
    /**
     * Mouse actioner
     */
    public void doMouse(String action, double x, double y){
        if(action.equals("clicked")){
           if (circle == true){
               UI.fillOval((x - (xValue/2)),(y - (yValue/2)), xValue, yValue);
           } else if (rectangle == true){
               UI.fillRect (x, y, xValue, yValue);
           } else if (line == true){
               UI.drawLine(this.startX, this.startY, x, y);
           }
        } else if (action.equals("pressed")){
            this.startX = x; 
            this.startY = y;
        }else if(action.equals("released")){
            if (rectangle == true){
                if ((x > startX) && (y > startY)){
                    UI.fillRect(startX, startY, (x - startX), (y - startY));    
                } else if ((startX > x) && (startY > y)){
                    UI.fillRect(x, y, (startX - x),  (startY - y));
                } else if ((startX < x) && (startY > y)){
                    UI.fillRect(startX, y, (x - startX), (startY - y));
                } else if ((startX > x) && (startY < y)){
                    UI.fillRect(x, startY, (startX - x), (y - startY));
                }
            } else if ((line == true) || (circle == true)) {
                UI.drawLine(this.startX, this.startY, x, y);
            }
                  
        }  
    }
    
    /**
     * Choose Width (for slider)
     */
    public void chooseWid(double width){
        xValue = width;
    }  
    
    /**
     * Choose Height (for slider)
     */
    public void chooseHei(double height){
        yValue = height;
    }
    
    /**
     * Choose Line Width
     */
    public void chooseWidth(double input){
        UI.setLineWidth(input);
    }
    
    /**
     * When Circle button is clicked set true
     */    
    public void doCircle(){ //(left, t, w, h)
        circle = true;
        rectangle = false;
        line = false;
    }
     
    /**
      * When Rectangle button is clicked set true
      */
    public void doRectangle(){ //(left, t, w, h)
        circle = false;
        rectangle = true;
        line = false;
    }
    
    /**
      * When Line button is clicked set true
      */
    public void doLine(){ //(left, t, w, h)
        circle = false;
        rectangle = false;
        line = true;
    }
    
    /**
     * Make a random color
     */
    private void changeColor(){
        Color col = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        UI.setColor(col);
    }
    
    private void doSetSize(double size){
        UI.setLineWidth(size);
    }

    public static void main(String[] args){
        DrawShape obj = new DrawShape();
        UI.setMouseListener(obj::doMouse);
        //UI.addButton("Toggle Shape", obj::changeShape);
        
        UI.addButton("Choose Color", obj::doChooseColor);
        UI.addButton("Random Color", obj::changeColor);
        UI.addButton("Circle", obj::doCircle);
        UI.addButton("Rectangle", obj::doRectangle);
        UI.addButton("Line", obj::doLine);
        UI.addSlider("X Value", 1, 200, 50, obj::chooseWid);
        UI.addSlider("Y Value", 1, 200, 50, obj::chooseHei);
        UI.addSlider("Line Width", 1, 100, 5, obj::chooseWidth);
        UI.addButton("Clear", UI::clearGraphics);
    }

}

