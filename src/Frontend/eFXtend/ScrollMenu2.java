package Frontend.eFXtend;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ProgressBar;
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

public class ScrollMenu2 extends Menu
{
	//constructor params
	private int width;			//visible width
	private int height;			//visible height
	private int xPos;			//x position
	private int yPos;			//y position
	
	//params
	private String path;

	//calculated
	
	private EventHandler<Event> scrollUp;
	private EventHandler<Event> scrollDown;
	
	private Menu scrollBar;
	
	int totHeight;
	int minY;
	int maxY;
	
	int topNode;
	int btmNode;
	
	int scrollValue;
	//(handleY / barHeight-40) * totHeight
	//move 1/10 at a time
	
	
	
	ArrayList<Object[]> list;
	
	/**
	 * 
	 * @param w Visible width
	 * @param h Visible height
	 * @param x position
	 * @param y position
	 * @param imgPath
	 */
	public ScrollMenu2(int w, int h, int x, int y, String imgPath)
	{
		super();
		
		width = w;
		height = h;
		xPos = x;
		yPos = y;
		path = imgPath;
		totHeight = 0;
		makeScrollBar();
	}
	
	/**
	 * Adds a menu component
	 * @param obj menu component
	 */
	public void add(Node obj, int y, int h)
	{
		if(components.contains(obj) | buttons.contains(obj))
				return;
		
		obj.setLayoutY(y);
		
		if(Shape.class.isAssignableFrom(obj.getClass()))
		{
			components.add((Shape)obj);
		}
		else if(Control.class.isAssignableFrom(obj.getClass()))
		{
			buttons.add((Control)obj);
		}
		else
			return;
		
		Rectangle handle = this.getScrollHandle();
		Object[] ray = {obj, y, h};
		list.add(ray);
		int index = list.size() - 1;
		handle.setHeight((height / totHeight) * (this.getScrollBarBg().getHeight() - 40));
		
		/*obj.layoutYProperty().addListener(new ChangeListener<Object>()
		{
			@Override public void changed(ObservableValue o,Object oldVal, Object newVal)
			{
	        	  if(((Integer)newVal > yPos + height) || (Integer)newVal + h < yPos) //below or above menu
	        	  {
	        		  obj.setVisible(false);
	        	  }
	        	  else
	        		  obj.setVisible(true);
			}
		});*/
		
		
		handle.yProperty().addListener(new ChangeListener<Object>()
		{
			@Override public void changed(ObservableValue o,Object oldVal, Object newVal)
			{
				int newY = (int)(obj.getLayoutY() - scrollValue);
				
				obj.setLayoutY(newY);
				
				if(newY + h > yPos && newY < y+height)
				{
					obj.setVisible(true);
				}
				else
					obj.setVisible(false);
				
			}
		});
		
		//set height
		
		//if first element
		if(components.isEmpty() && buttons.isEmpty())
		{
			minY = (int)y;
			maxY = (int)(y + h);
			
			topNode = index;
			btmNode = index;
		}
		else
		{
			if(y < minY)
			{
				topNode = index;
				minY = (int)y;
			}
			
			if(y + h > maxY)
			{
				btmNode = index;
				maxY = (int)y;
			}
		}
		
		totHeight = Math.abs(minY - maxY);
		
		if(height > totHeight)
			scrollBar.setVisible(false);
		else
		{
			scrollBar.setVisible(true);
			this.getScrollHandle().setHeight((height / totHeight) * (this.getScrollBarBg().getHeight() - 40));
		}
		
		
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
		
		//if(rows > 0)
		//	handle.setHeight(visRows / rows);
		
		scrollBar.add(handle);
		
		//Buttons
		Button downBtn = makeButton(downArrow, null, null);
		
		//bg = 0
		//handle = 6
		
		scrollDown = new EventHandler<Event>() 
		{	  
			  @Override  
		      public void handle(Event event) 
			  { 
				 if(scrollValue + height >= totHeight)
					 return;
				 
				 else
				 {
					 scrollValue += height/20;
					 if(scrollValue + height > totHeight)
						 scrollValue = totHeight - height;
				 }
				 
				 //move handle down
				 Rectangle handle = (Rectangle)scrollBar.getComponents().get(6);
				 handle.setY(handle.getY() + handle.getHeight()/20);
				 
				  System.out.println("Scroll down TEST");
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
		 //if(rows < visRows)
		//	 scrollBar.setVisible(false);
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
	//	if(topRow == 0)
			return;
				
		/*for(int i = rows-1; i >= 0; i--)
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
		}*/
	}
	
	/**
	 * Adds the all components and buttons to a group
	 * @param root group to add menu to
	 */
	/*@Override
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
		 
	}*/
	 
}
