package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ButtonX extends Menu
{
	private Button btn;
	private Color flash;
	private ArrayList<EventHandler<Event>> events;
	private Rectangle bgRect;
	private Text txt;
	
	/**
	 * Makes a button x from a button
	 * @param b
	 */
	public ButtonX(Button b)
	{
		super();
		events = new ArrayList<EventHandler<Event>>();
		btn = b;
		buttons.add(btn);
		setAction();
	}
	
	/**
	 * Makes a button x from a rectangle
	 * @param rect Target rectangle to put button over
	 * @param c Color to flash when pressed
	 * @param list ArrayList of all button color flashes
	 */
	public ButtonX(Rectangle rect, Color c, ArrayList<FillTransition> list)
	{
		super();
		events = new ArrayList<EventHandler<Event>>();
		btn = makeButtonX(rect, c, list);
		buttons.add(btn);
		bgRect = rect;
		components.add(bgRect);
		setAction();
	}
	
	/**
	 * Makes a button x from a rectangle
	 * @param rect Target rectangle to put button over
	 * @param c Color to flash when pressed
	 * @param list ArrayList of all button color flashes
	 */
	public ButtonX(Rectangle rect, Color c, Text t, ArrayList<FillTransition> list)
	{
		super();
		events = new ArrayList<EventHandler<Event>>();
		btn = makeButtonX(rect, c, list);
		bgRect = rect;
		
		txt = t;
		centerText(bgRect, txt);		
		
		buttons.add(btn);
		bgRect = rect;
		components.add(bgRect);
		components.add(txt);
		
		setAction();
	}
	
	/**
	 * Makes an invisible button from a rectangle
	 * @param target rectangle
	 * @param color to flash when button pressed
	 * @param list of all button flash colors
	 * @return resulting button
	 */
	private Button makeButtonX(Rectangle rect, Color c, ArrayList<FillTransition> list)
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

		    EventHandler<Event> fillEvent = new EventHandler<Event>() 
			  {
				  @Override  
			      public void handle(Event event) 
				  {  
					  fill.play();
				  }
			  };
		
			 events.add(fillEvent);
		}
		
		return btn;
	}
	
	/**
	 * Creates the button event that loops through all events
	 */
	private void setAction()
	{
		EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() 
		  {
			  @Override  
		      public void handle(ActionEvent event) 
			  {  
				  if(events != null)
				  {
					  for(int i = 0; i < events.size(); i++)
					  {
						  events.get(i).handle(event);
					  }
				  }
				  event.consume();
			  }
		  };
		  btn.setOnAction(action);
	}
	
	/**
	 * Adds an event to the button
	 * @param event to be added
	 */
	public void addAction(EventHandler<Event> event)
	{
		events.add(event);
	}
	
	/**
	 * Removes an event from the button
	 * @param event event to be removed
	 */
	public void removeAction(EventHandler<Event> event)
	{
		events.remove(event);
	}
	
	/**
	 * @return ArrayList of all button events
	 */
	public ArrayList<EventHandler<Event>> getActions()
	{
		return events;
	}
	
	/**
	 * Returns the button
	 * @return
	 */
	public Button getButton()
	{
		return btn;
	}
	
	/*
	 * Returns the background rectangle
	 */
	public Rectangle getRectangle()
	{
		return bgRect;
	}
	
	/**
	 * @return the x position of the button
	 */
	public double getX()
	{
		return btn.getLayoutX();
	}
	
	/**
	 * @return the Y position of the button
	 */
	public double getY()
	{
		return btn.getLayoutY();
	}
	
	/**
	 * @return the width of the button
	 */
	public double getWidth()
	{
		return btn.getPrefWidth();
	}
	
	/**
	 * @return the height of the button
	 */
	public double getHeight()
	{
		return btn.getPrefWidth();
	}
	
	/*
	 * Returns the button text
	 */
	public Text getText()
	{
		return txt;
	}
	
}
