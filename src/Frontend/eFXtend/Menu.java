package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import Frontend.*;

;public class Menu 
{
	protected ArrayList<Shape> components;
	protected ArrayList<Control> buttons;
	protected Boolean vertical;
	protected int N;
	protected boolean visible;
	
	/**
	 * Default constructor: Makes a blank Menu object
	 */
	public Menu()
	{
		components = new ArrayList<Shape>();
		buttons = new ArrayList<Control>();
		vertical = true;
		visible = true;
	}
	
	/**
	 * Parameterized  constructor: Makes a Menu object
	 * @param list of component objects
	 * @param buttonList list of component shapes
	 * @param isVert Direction of menu: true if vertical, false if horizontal
	 */
	public Menu(ArrayList<Shape> list, ArrayList<Control> buttonList, boolean isVert)
	{
		components = list;
		buttons = buttonList;
		vertical = isVert;
		N = components.size();
		visible = true;
	}
	
	/**
	 * @return true if dropdown moves up/down, false if left/right
	 */
	protected boolean isVertical()
	{
		return vertical;
	}
	
	protected void setVertical(boolean bool)
	{
		vertical = bool;
	}
	
	/**
	 * Adds the all components and buttons to a group
	 * @param root group to add menu to
	 */
	public void addToGroup(Group root)
	{
		if(components != null)
		{
			for(int i = 0; i < components.size(); i++)
			{
				Shape shape = components.get(i);
				if((root.getChildren().contains(shape)) == false)
					root.getChildren().add(shape);
			}
		}
		
		if(buttons != null)
		{
			for(int i = 0; i < buttons.size(); i++)
			{
				Control btn = buttons.get(i);
				if((root.getChildren().contains(btn)) == false)
					root.getChildren().add(btn);
			}
		}
	}
	
	/*
	 * Returns all component objects in an array list
	 */
	public ArrayList<Shape> getComponents()
	{
		return components;
	}
	
	/*
	 * Returns all buttons in an array list
	 */
	public ArrayList<Control> getButtons()
	{
		return buttons;
	}
	
	public int size()
	{
		if(N == 0 && components != null)
			N = components.size();
		return N;
	}
	
	public void addToArrays(ArrayList<Shape> list, ArrayList<Control> buttonList)
	{
		if(list != null && components != null)
		{
			list.addAll(components);
		}
		
		if(buttonList != null && buttons != null)
		{
			buttonList.addAll(buttons);
		}
	}
	
	//Static methods
	/**
	 * Makes an icon from a rectangle w/o an image
	 * @param width width of icon
	 * @param height height of icon
	 * @param x coordinate
	 * @param y coordinate
	 * @param arc height & width of rectangle
	 * @param fillColor of icon
	 * @param outline color
	 * @return Instance of Rectangle
	 */
	public static Rectangle icon(int width, int height, int x, int y, int arc, Color fillColor, Color strokeColor)
	{
		Rectangle rect = new Rectangle();
		rect.setWidth(width);
		rect.setHeight(height);
		rect.setX(x);
		rect.setY(y);
		rect.setFill(fillColor);
		
		if(strokeColor != null)
		{
			rect.setStroke(strokeColor);
			rect.setStrokeWidth(2);
			rect.setStrokeType(StrokeType.INSIDE);
		}
		
		rect.setArcHeight(arc);
		rect.setArcWidth(arc);


		return rect;
	}
	/**
	 * Adds an image on top of a rectangle backdrop
	 * @param width of image
	 * @param height of image
	 * @param rectangle in back of image
	 * @param path to image source
	 * @return Instance of Rectangle
	 */
	public static Rectangle icon(int width, int height, Rectangle back, String imgPath)
	{
		Rectangle rect = new Rectangle();
		Image img = new Image(imgPath, width, height, false, false);
		ImageInput imgIn = new ImageInput();  
		imgIn.setSource(img);
		
		imgIn.setX(back.getX() + (back.getWidth() - width) / 2); 
		imgIn.setY(back.getY() + (back.getHeight() - height) / 2);
		rect.setEffect(imgIn);  
		return rect;
	}
	
	/**
	 * Adds an image to a rectangle
	 * @param width of image
	 * @param height of image
	 * @param  path to image source
	 * @return Instance of Rectangle
	 */
	public static Rectangle icon(int width, int height, int x, int y, String imgPath)
	{
		Rectangle rect = new Rectangle();
		Image img = new Image(imgPath, width, height, false, false);
		ImageInput imgIn = new ImageInput();  
		imgIn.setSource(img);	
		
		imgIn.setX(x); 
		imgIn.setY(y);
		rect.setEffect(imgIn);  
		return rect;
	}
	
	
	/**
	 * Adds shared elements to a scene
	 * @param Group of new scene
	 * @param List of objects to add to scene
	 */
	public static void changeScene(Group newRoot, ArrayList<javafx.scene.Node> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(!newRoot.getChildren().contains(list.get(i)))
				newRoot.getChildren().add(list.get(i));
		}
	}
	
	/**
	 * Makes an invisible button from a rectangle
	 * @param rect target rectangle
	 * @param c color to flash when button pressed
	 * @param list of all button flash colors
	 * @return
	 */
	public static Button makeButton(Rectangle rect, Color c, ArrayList<FillTransition> list)
	{
		Button btn = new Button();
		btn.setLayoutX(rect.getX());
		btn.setLayoutY(rect.getY());
		btn.setPrefWidth(rect.getWidth());
		btn.setPrefHeight(rect.getHeight());
		btn.setOpacity(0);
		
		if(c != null && list != null)
		{	
		    FillTransition fill = new FillTransition();  
		    fill.setAutoReverse(true);  	      
		    fill.setCycleCount(2);  
		    fill.setDuration(Duration.millis(100));  
		    fill.setToValue(c);  
		    fill.setShape(rect);  
			
		    list.add(fill);
		
		
		    btn.setOnAction(new EventHandler<ActionEvent>() 
		    {  
	            @Override  
	            public void handle(ActionEvent arg0) 
	            {  
	            	fill.play();
	            }  
		    } ); 
		    
		}
		return btn;
	}
	
	/**
	 * Adds the all components and buttons to a group
	 * @param root group to add menu to
	 * @param list of components
	 */
	public static void addToGroup(Group root, ArrayList<javafx.scene.Node> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			javafx.scene.Node node = list.get(i);
			if((root.getChildren().contains(node)) == false)
				root.getChildren().add(node);
		}
	}
	
	/**
	 * Makes a button with a label and event
	 * @param rect rectangle to put button on
	 * @param c color to flash
	 * @param list of all button flashes
	 * @param event to happen when button is clicked
	 * @param str text to place on button
	 * @param font for text
	 * @return list containing the back rectangle, text, and button in that order
	 */
	public static ArrayList<javafx.scene.Node> makeButton(Rectangle rect, Color c, ArrayList<FillTransition> list, EventHandler<MouseEvent> event, String str, Font font)
	{
		ArrayList<javafx.scene.Node> returnList = new ArrayList<javafx.scene.Node>();
		returnList.add(rect);
		
		Text txt = new Text(str);
		txt.setFont(font);
		centerText(rect, txt);
		txt.setFill(rect.getStroke());
		txt.setWrappingWidth(rect.getWidth());
		txt.setTextAlignment(TextAlignment.CENTER);
		returnList.add(txt);
		
		Button btn = new Button();
		btn.setLayoutX(rect.getX());
		btn.setLayoutY(rect.getY());
		btn.setPrefWidth(rect.getWidth());
		btn.setPrefHeight(rect.getHeight());
		btn.setOpacity(0);
		returnList.add(btn);
		
		if(c != null && list != null)
		{	
		    FillTransition fill = new FillTransition();  
		    fill.setAutoReverse(true);  	      
		    fill.setCycleCount(2);  
		    fill.setDuration(Duration.millis(100));  
		    fill.setToValue(c);  
		    fill.setShape(rect);  
			
		    list.add(fill);
		
		
		    btn.setOnAction(new EventHandler<ActionEvent>() 
		    {  
	            
	            @Override  
	            public void handle(ActionEvent arg0) 
	            {  
	            	fill.play();
	            }  
		    } ); 
		    
		    btn.setOnMouseClicked(event);
		    
		}
		return returnList;
	}
	
	/**
	 * Makes text field from a rectangle
	 * @param target rectangle
	 * @param list of all button flash colors
	 * @return text field
	 */
	public static TextField makeTextField(Rectangle rect, Font f)
	{
		TextField field = new TextField();
		field.setLayoutX(rect.getX());
		field.setLayoutY(rect.getY());
		field.setPrefHeight(rect.getHeight());
		field.setPrefWidth(rect.getWidth());
		
		CornerRadii arc = new CornerRadii(5);
		
		Background fill = new Background( new BackgroundFill(rect.getFill(), arc, null) );
		Border stroke = new Border(new BorderStroke(rect.getStroke(), BorderStrokeStyle.SOLID, arc, new BorderWidths(2)));
		
		field.setBackground(fill);	
		field.setBorder(stroke);
		field.setFont(f);
		field.setStyle("-fx-text-fill: #" + (rect.getStroke()).toString().substring(2, 8));
		
		rect.setVisible(false);
		
		return field;
	}
	
	/**
	 * Makes text field from a rectangle
	 * @param target rectangle
	 * @param list of all button flash colors
	 * @return text field
	 */
	public static PasswordField makePwdField(Rectangle rect, Font f)
	{
		PasswordField field = new PasswordField();
		field.setLayoutX(rect.getX());
		field.setLayoutY(rect.getY());
		field.setPrefHeight(rect.getHeight());
		field.setPrefWidth(rect.getWidth());
		
		CornerRadii arc = new CornerRadii(5);
		
		Background fill = new Background( new BackgroundFill(rect.getFill(), arc, null) );
		Border stroke = new Border(new BorderStroke(rect.getStroke(), BorderStrokeStyle.SOLID, arc, new BorderWidths(2)));
		
		field.setBackground(fill);	
		field.setBorder(stroke);
		field.setFont(f);
		field.setStyle("-fx-text-fill: #" + (rect.getStroke()).toString().substring(2, 8));		
		
		rect.setVisible(false);
		
		return field;
	}
	
	/**
	 * @return the x position of the first component
	 */
	public double getX()
	{
		return components.get(0).getLayoutX();
	}
	
	/**
	 * @return the Y position of the first component
	 */
	public double getY()
	{
		return components.get(0).getLayoutY();
	}
	
	/**
	 * @return the width of the first component
	 */
	public double getWidth()
	{
		if(components.get(0).getClass() == Rectangle.class)
		{
			Rectangle bg = (Rectangle)components.get(0);
			return bg.getWidth();
		}
		else
			return -1;
		
	}
	
	/**
	 * @return the height of the first component
	 */
	public double getHeight()
	{
		if(components.get(0).getClass() == Rectangle.class)
		{
			Rectangle bg = (Rectangle)components.get(0);
			return bg.getHeight();
		}
		else
			return -1;
		
	}
	
	/**
	 * Centers text on a rectangle
	 * @param rect target rectangle
	 * @param txt text to center
	 */
	public static void centerText(Rectangle rect, Text txt)
	{
		txt.setX(rect.getX());
		txt.setWrappingWidth(rect.getWidth());
		txt.setTextAlignment(TextAlignment.CENTER);
		txt.setY(rect.getY() + txt.getFont().getSize() + (int)((rect.getHeight() - txt.getFont().getSize())*0.8 / 2));
	}
	
	/**
	 * Centers text in a region
	 * @param w width of region
	 * @param h height of region
	 * @param x position of region
	 * @param y position of region
	 * @param txt text to center
	 */
	public static void centerText(int w, int h, int x, int y, Text txt)
	{
		txt.setX(x);
		txt.setWrappingWidth(w);
		txt.setTextAlignment(TextAlignment.CENTER);
		txt.setY(y + txt.getFont().getSize() + (int)((h - txt.getFont().getSize())*0.8 / 2));
	}
	
	/**
	 * Sets the x position of the menu
	 * @param x position
	 */
	public void setX(int x)
	{
		if(getX() == x)
			return;
		
		for(int i = 0; i < components.size(); i++)
		{
			Shape shape = components.get(i);
			shape.setTranslateX(x - shape.getLayoutX());
		}		
	
		for(int i = 0; i < buttons.size(); i++)
		{
			Control btn = buttons.get(i);
			btn.setTranslateX(x - btn.getLayoutX());
		}
	}
	
	/**
	 * Sets the y position of the menu
	 * @param y position
	 */
	public void setY(int y)
	{
		if(getY() == y)
			return;
		
		for(int i = 0; i < components.size(); i++)
		{
			Shape shape = components.get(i);
			shape.setTranslateY(shape.getLayoutY() + y);
		}
		
		for(int i = 0; i < buttons.size(); i++)
		{
			Control btn = buttons.get(i);
			btn.setTranslateY(y - btn.getLayoutX());
		}
	}
	
	/**
	 * Adds a new component or button
	 * @param obj to be added
	 */
	public void add(javafx.scene.Node obj)
	{
		if(Shape.class.isAssignableFrom(obj.getClass()))
		{
			if(components.add((Shape)obj));
				N++;
		}
		else if(Control.class.isAssignableFrom(obj.getClass()))
		{
			buttons.add((Control)obj);
		}
		else
			System.out.println("Error adding " + obj + " to " + this);
	}
	
	/**
	 * Removes a component or button
	 * @param obj to be removed
	 */
	public void remove(javafx.scene.Node obj)
	{
		if(Shape.class.isAssignableFrom(obj.getClass()))
		{
			if(components.remove((Shape)obj));
				N--;
		}
		else if(Control.class.isAssignableFrom(obj.getClass()))
		{
			buttons.remove((Control)obj);
		}
		else
			System.out.println("Error removing " + obj + " from " + this);
	}
	
	public void setVisible(boolean bool)
	{
		if(visible == bool)
			return;
		
		visible = bool;
		for(int i = 0; i < size(); i++)
		{
			components.get(i).setVisible(visible);
		}
		
		for(int i = 0; i < buttons.size(); i++)
		{
			buttons.get(i).setVisible(visible);
		}
	}
	
	public boolean isVisible()
	{
		return visible;
	}
}
