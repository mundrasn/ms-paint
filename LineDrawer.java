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


/** <description of class LineDrawer>
 * 
 * Snigdha
 * 15/04/2021
 */
public class LineDrawer{
    //fields
    private double startX, startY; // fields to remember the pressed position
    private Color currentColor = Color.black;
    
    /**
     * Color Chooser
     */
    public void doChooseColor(){
        this.currentColor = JColorChooser.showDialog(null, "Choose Color", this.currentColor);
        UI.setColor(this.currentColor);
    }
    
    /**
     * Constructer for objects 
     */
    public LineDrawer(){
        UI.initialise();
        UI.addButton("Quit", UI::quit);
        //UI.addButton("Choose Color", this::toggleRandCol);
    }
    
    public void chooseWidth(double input){
        UI.setLineWidth(input);
    }   
    
    /**
     * Make a random color
     */
    private void changeColor(){
        Color col = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        UI.setColor(col);
    }
    
    /**
     * Mouse actioner
     */
    private void doMouse(String action, double x, double y){
        if (action.equals("pressed")){
            this.startX = x; 
            this.startY = y; 
        } else if(action.equals("released")){
            UI.drawLine(this.startX, this.startY, x, y);
        }
    }

    /**
     * Main routine
     */

    public static void main(String[] args){
        LineDrawer obj = new LineDrawer();
        UI.setLineWidth(5);
        UI.setMouseListener(obj::doMouse);
        UI.addButton("Choose Color", obj::doChooseColor);
        
        UI.addSlider("Line Width", 1, 100, 5, obj::chooseWidth);
        //UI.addSlider("X size", 1, 300, 20, obj::);
        UI.addButton("Random Color", obj::changeColor);
        UI.addButton("Clear", UI::clearGraphics);
        
    }

}