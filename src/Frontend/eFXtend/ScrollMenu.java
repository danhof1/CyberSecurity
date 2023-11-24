package Frontend.eFXtend;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.DirectoryChooser;

public class ScrollMenu extends Menu
{
	//constructor params
	private int width;			//visible width
	private int height;			//visible height
	private int xPos;			//x position
	private int yPos;			//y position
	private int columns;		//number of columns
	
	//params
	private int compWidth;		//width of individual menus
	private int compHeight;		//height of individual menus
	private String path;
	private ArrayList<Menu[]> menuComponents; //each object is a row

	//calculated
	private int[] vBorders;		//vertical borders
	private int [] hBorders;	//horizontal borders
	private int visRows;		//number of visible rows
	private int rows;			//number of total rows
	private int hBuffer;
	private int vBuffer;
	private int topRow;			//index denoting the top visible row
	
	private EventHandler<Event> scrollUp;
	private EventHandler<Event> scrollDown;
	
	private Menu scrollBar;
	
	
	
	/**
	 * 
	 * @param w Visible width
	 * @param h Visible height
	 * @param cw components width
	 * @param ch components height
	 * @param x position
	 * @param y position
	 * @param col number of columns
	 */
	public ScrollMenu(int w, int h, int cw, int ch, int x, int y, int col, String imgPath)
	{
		super();
		menuComponents = new ArrayList<Menu[]>();
		
		//Initialize
		N = 0;
		width = w;
		height = h;
		compWidth = cw;
		compHeight = ch;
		xPos = x;
		yPos = y;
		columns = col;
		rows = 0;
		path = imgPath;
		topRow = 0;
		
		compHeight = ch;
		compWidth = cw;

		//Calculations
		
		//Calculate visible rows
		visRows = height / compHeight;
		//if(visRows < compHeight/10.0)
			//visRows;
		
		//visRows = 2;
		
		vBorders = new int[col];
		hBorders = new int[visRows];
		
		//Calculate buffers
		vBuffer = (height - (compHeight * visRows) ) / (visRows+1);
		hBuffer = (width - (compWidth * columns)) / (columns+1);
		
		//Vertical borders
		for(int i = 0; i < columns; i++)
		{
			vBorders[i] = xPos + hBuffer + (hBuffer + compWidth) * i;
		}
		
		//Horizontal borders
		for(int i = 0; i < visRows; i++)
		{
			hBorders[i] = yPos + vBuffer + (vBuffer + compHeight) * i;
		}
		makeScrollBar();
	}
	
	/**
	 * Adds a menu component
	 * @param obj menu component
	 */
	public void addComponent(Menu obj)
	{
		if(components.contains(obj))
			return;
		
		//menuComponents empty OR last row is full
		int x = vBorders[0];
		int y;
		if(menuComponents.isEmpty() || menuComponents.get(rows-1)[columns-1] != null)
		{
			Menu[] newRow = new Menu[columns];
			newRow[0] = obj;
			menuComponents.add(newRow);
			rows++;
			getScrollHandle().setHeight((height - 40) * ((double)visRows / rows));
		}
		
		//last row not full
		else if(menuComponents.get(rows-1)[columns-1] == null)
		{
			Menu[] curRow = menuComponents.get(rows-1);
			
			for(int i = 0; i < columns; i++)
			{
				if(curRow[i] == null)
				{
					curRow[i] = obj;
					x = vBorders[i];
					break;
				}
			}
		}
		else
		{
			System.out.println("Error adding " + obj +" to " + this);
			return;
		}
		
		if(rows <= visRows)
			y = hBorders[rows-1];
		else
		{
			y = hBorders[visRows-1];
			obj.setVisible(false);
			
		}
		
		obj.setX(x);
		obj.setY(y);
		
		obj.addToArrays(components, buttons);
		
		if(rows > visRows)
		{
			scrollBar.setVisible(true);
		}
	}
	
	/**
	 * Gets a specific row of Menus
	 * @param r row number
	 * @return Menu[] of row r
	 */
	public Menu[] getRow(int r)
	{
		if(r > rows-1)
			return null;
		else
			return menuComponents.get(r);
	}
	
	/**
	 * Gets a specific Menu
	 * @param r row number
	 * @param c column number
	 * @return Menu at row r, column c
	 */
	public Menu getMenu(int r, int c)
	{
		if(r > rows-1 || c > columns-1)
			return null;
		else
			return menuComponents.get(r)[c];
	}
	
	/**
	 * Creates the scroll bar
	 * Component order:
	 * 0. background
	 * 1. border
	 * 2, 3: up and down arrow
	 * 4, 5: up and down arrow icons
	 */
	private void makeScrollBar()
	{
		scrollBar = new Menu();
		
		//0
		Rectangle bg = icon(20, height, xPos + width - 22, yPos, 0, Color.GRAY, Color.BLACK);
		bg.setStrokeWidth(0);
		bg.setOpacity(0.25);
		scrollBar.add(bg);
		
		//1
		Rectangle outline = icon(20, height, xPos + width - 22, yPos, 0, Color.GRAY, Color.BLACK);
		outline.setFill(null);
		scrollBar.add(outline);
		
		//2
		Rectangle upArrow = icon(20, 20, xPos + width - 22, yPos, 0, Color.DARKGRAY, Color.BLACK);
		scrollBar.add(upArrow);
		
		//3
		Rectangle downArrow = icon(20, 20, xPos + width - 22, yPos + height - 20, 0, Color.DARKGRAY, Color.BLACK);
		scrollBar.add(downArrow);
		
		//4
		Rectangle upArrowIcon = icon(15, 15, upArrow, path);
	    
		Rotate rotate = new Rotate(); 
	    rotate.setAxis(Rotate.Z_AXIS);
	    rotate.setPivotX(xPos + width - 12);
	    rotate.setPivotY(yPos + 10);
	    rotate.setAngle(180); 

		upArrowIcon.getTransforms().add(rotate);
		scrollBar.add(upArrowIcon);
	
		//5
		Rectangle downArrowIcon = icon(15, 15, downArrow, path);
		scrollBar.add(downArrowIcon);
		
		//6		ADJUST HEIGHT
		Rectangle handle = icon(20, 60, xPos + width - 22, yPos + 20, 0, Color.DARKGRAY, Color.BLACK);
		
		if(rows > 0)
			handle.setHeight(visRows / rows);
		
		scrollBar.add(handle);
		
		//Buttons
		Button downBtn = makeButton(downArrow, null, null);
		
		scrollDown = new EventHandler<Event>() 
		{	  
			  @Override  
		      public void handle(Event event) 
			  {  			
				 //if not at the bottom, scroll down
				 
				 if((topRow + visRows-1) < rows-1)
				 {
					 int num = (int)((height-40) * (1.0/rows));
					 getScrollHandle().setY(getScrollHandle().getY() + num);
					 
					 Menu[] oldTop = menuComponents.get(topRow);
					 //1. Hide top row
					 for(int i = topRow; i < columns; i++)
					 {
						 oldTop[i].setVisible(false);
					 }
					 
					 //2. Move rows up
					 topRow++;
					 for(int i = topRow; i < visRows; i++)
					 {
						 Menu[] curRow = menuComponents.get(i);
						 for(int j = 0; j < columns; j++)
						 {
							 Menu curMenu = curRow[j];
							 curMenu.setY(hBorders[i-1]);
						 }
					 }
					 
					 //3. show bottom row
					 Menu[] bottomRow = menuComponents.get(topRow + visRows-1);
					 
					 for(int i = 0; i < columns; i++)
					 {
						 bottomRow[i].setVisible(true);
					 }
				 }
			  }
		};
		 downBtn.setOnMouseClicked(scrollDown);
		 buttons.add(downBtn);
		 
		Button upBtn = makeButton(upArrow, null, null);
		
		scrollUp = new EventHandler<Event>() 
		{	  
			  @Override  
		      public void handle(Event event) 
			  {  			
				 //if not at the top, scroll up
				 
				 if(topRow  > 0)
				 {
					 int num = (int)((height-40) * (1.0/rows));
					 getScrollHandle().setY(getScrollHandle().getY() - num);
					 
					 Menu[] oldBottom = menuComponents.get(topRow + visRows-1);
					 //1. Hide bottom row
					 for(int i = 0; i < columns; i++)
					 {
						 oldBottom[i].setVisible(false);
					 }
					 
					 
					 //2. Move rows down
					 for(int i = topRow; i < visRows; i++)
					 {
						 Menu[] curRow = menuComponents.get(i);
						 
						 for(int j = 0; j < columns; j++)
						 {
							 Menu curMenu = curRow[j];
							 curMenu.setY(hBorders[i - topRow+1]);
						 }
					 }
					 topRow--;
					 
					 //3. show top row
					 Menu[] newTop = menuComponents.get(topRow);
					 for(int i = 0; i < columns; i++)
					 {
						 newTop[i].setVisible(true);
					 }
				 }
			  }
		};
		 upBtn.setOnMouseClicked(scrollUp);
		 buttons.add(upBtn);
		 
		 EventHandler<MouseEvent> mouseScroll = new EventHandler<MouseEvent>() 
		 {	  
			  @Override  
		      public void handle(MouseEvent event) 
			  {  
				  Rectangle sHandle = getScrollHandle();
				  
				  sHandle.setOnMouseDragged((event2) -> 
				  {
					  int mouseY = (int)event2.getY();
					  if(mouseY < sHandle.getY())
					  {
						  scrollUp.handle(event);
					  }
					  else if (mouseY > sHandle.getY() + sHandle.getHeight())
					  {
						  scrollDown.handle(event);
					  }
				  });
			  }
		 };
		 getScrollHandle().setOnDragDetected(mouseScroll);
		 Scene scene = getScrollHandle().getScene();
		 
		 scrollBar.addToArrays(components, buttons);
		 if(rows < visRows)
			 scrollBar.setVisible(false);
	}
	
	/**
	 * Sets the color of the scroll bar, can also be done by calling getScrollBar() and editing manually.
	 * @param bgColor Color of the scroll bar background
	 * @param scColor Color of the handle
	 * @param arColor Color of the arrow buttons
	 * @param imgPath Path to arrow icons
	 */
	public void setColors(Color bgColor, Color haColor, Color arColor, Color stColor, String imgPath)
	{
		//Background
		getScrollBarBg().setFill(bgColor);
		getScrollBarBg().setStroke(stColor);
		
		//Border
		getScrollBarBorder().setStroke(stColor);;
		
		//Up, down arrows
		getScrollArrows()[0].setFill(arColor);
		getScrollArrows()[0].setStroke(stColor);

		getScrollArrows()[1].setFill(arColor);
		getScrollArrows()[1].setStroke(stColor);
		
		//Handle
		getScrollHandle().setFill(haColor);
		getScrollHandle().setStroke(stColor);
	}
	
	/**
	 * Gets the scroll bar
	 * @return Menu containing all scroll bar components.
	 */
	public Menu getScrollBar()
	{
		return scrollBar;
	}
	
	/**
	 * Gets the scroll bar background
	 * @return scroll bar background as a rectangle
	 */
	public Rectangle getScrollBarBg()
	{
		return (Rectangle)scrollBar.getComponents().get(0);
	}
	
	/**
	 * Gets the scroll bar border
	 * @return scroll bar border as a rectangle
	 */
	public Rectangle getScrollBarBorder()
	{
		return (Rectangle)scrollBar.getComponents().get(1);
	}
	
	/**
	 * Gets the up and down arrows
	 * @return up and down arrows as rectangle array
	 */
	public Rectangle[] getScrollArrows()
	{
		Rectangle[] arrows = {(Rectangle) scrollBar.getComponents().get(2), (Rectangle) scrollBar.getComponents().get(3)};
		return arrows;
	}
	
	/**
	 * Gets the up and down arrows
	 * @return up and down arrows as rectangle array
	 */
	public Rectangle[] getScrollArrowIcons()
	{
		Rectangle[] arrows = {(Rectangle) scrollBar.getComponents().get(4), (Rectangle) scrollBar.getComponents().get(5)};
		return arrows;
	}
	
	/**
	 * Gets the scroll bar handle
	 * @return scroll bar handle as a rectangle
	 */
	public Rectangle getScrollHandle()
	{
		return (Rectangle)scrollBar.getComponents().get(6);
	}
	
	public void reset()
	{
		if(topRow == 0)
			return;
				
		for(int i = rows-1; i >= 0; i--)
		{
			Menu[] curRow = menuComponents.get(i);
			
			int y = 0;
			if(i < visRows)
			{
				y = hBorders[i];
				
				for(int j = 0; j < columns; j++)
				{
					Menu curMenu = curRow[j];
					curMenu.setY(y);
					curMenu.setVisible(true);
				}
			}
			else //not visible rows
			{
				y = hBorders[visRows-1];
				
				for(int j = 0; j < columns; j++)
				{
					Menu curMenu = curRow[j];
					curMenu.setY(y);
					curMenu.setVisible(false);
				}
				
			}
			
			getScrollHandle().setY(yPos + 20);
		}
	}
	
	/**
	 * Adds the all components and buttons to a group
	 * @param root group to add menu to
	 */
	@Override
	public void addToGroup(Group root)
	{
		super.addToGroup(root);
				
		 EventHandler<ScrollEvent> sceneScroll = new EventHandler<ScrollEvent>() 
		 {	  
			  @Override  
		      public void handle(ScrollEvent event) 
			  {  
				  double x = event.getX();
				  double y = event.getY();
				  
				  if(x < xPos || x > xPos + width || y < yPos || y > yPos + height)
					  return;
					  
				  double deltaY = event.getDeltaY();
				  if(deltaY > 0)
				  {
					  scrollUp.handle(event);
				  }
				  else
				  {
					  scrollDown.handle(event);
				  }
			  }
		 };
		 
		 root.getScene().setOnScroll(sceneScroll);
		 
		 root.getScene().setOnKeyPressed((event) ->
		 {
			if(event.getCode() == KeyCode.UP)
			{
				scrollUp.handle(event);
			}
			else if(event.getCode() == KeyCode.DOWN)
			{
				scrollDown.handle(event);
			}
		 });
		 
	}
	 
}
