package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Toggle extends Menu
{
	private Rectangle background;
	private Circle knob;
	
	private Color offColor;
	private Color onColor;
	private Color knobColor;
	private Color lineColor;
	
	private Text displayText;
	private String onStr;
	private String offStr;
	
	private Boolean value;
	private ArrayList<EventHandler<Event>> events;
	private Circle saveColor;
	
	private int width;
	private int height;
	private int xPos;
	private int yPos;
	
	/**
	 * 
	 * @param w
	 * @param h
	 * @param x
	 * @param y
	 */
	public Toggle(int w, int h, int x, int y)
	{
		super();
		value = false;
		offColor = Color.GRAY;
		onColor = Color.SKYBLUE;
		knobColor = Color.WHITE;
		lineColor = Color.BLACK;
		events = new ArrayList<EventHandler<Event>>();
		saveColor = new Circle();
		
		width = w;
		height = h;
		xPos = x;
		yPos = y;
		
		//Saves on/off colors
		saveColor.setFill(onColor);
		saveColor.setStroke(offColor);
		components.add(saveColor);
		saveColor.setOpacity(0);
		
		//Background
		background = Menu.icon(w, h, x, y, h, offColor, lineColor);
		InnerShadow is = new InnerShadow();
		is.setBlurType(BlurType.GAUSSIAN);
		is.setColor(Color.BLACK);
		is.setHeight(h/3);
		is.setRadius(h/3);
		is.setWidth(h/10);
		is.setChoke(0.2);
		background.setEffect(is);
		components.add(background);
		
		//Knob		
		knob = new Circle();
		knob.setRadius((int)(h*0.8)/2);
		knob.setCenterX(x + h/2);
		knob.setCenterY(y+h/2);
		knob.setFill(knobColor);
		knob.setStroke(lineColor);
		knob.setStrokeWidth(2);
		Light l = new Light.Distant();
		Lighting light = new Lighting(l);
		light.setSurfaceScale(5);
		light.setDiffuseConstant(1.25);
		knob.setEffect(light);
		components.add(knob);
		offStr = new String("Off");
		displayText = new Text();
		Menu.centerText(background, displayText);
		onStr = new String("On");
		setText("Off", "On", null);
		displayText.setVisible(false);
		displayText.setWrappingWidth(w - (int)(h*1.5));
		displayText.setX(x + h);
		displayText.setOpacity(0.7);
		
		//Goes through all events
		EventHandler<MouseEvent> action = new EventHandler<MouseEvent>() 
		{
			@Override  
			public void handle(MouseEvent event) 
			{
				//move slider
				value = !value;
				
				//Theme change detected
				boolean themeChanged = !(((Color)knob.getFill()).equals(knobColor));
				if(themeChanged)
				{					
					knobColor = (Color)knob.getFill();
					lineColor = (Color)knob.getStroke();
					onColor = (Color)saveColor.getFill();
					
					offColor = (Color)saveColor.getStroke();
				}
				
				if(value) //on
				{
					knob.setCenterX(x + w - h/2);
					background.setFill(onColor);
					displayText.setText(onStr);
					displayText.setX(x + h/2);
				}
				else //off
				{
					knob.setCenterX(x + h/2);
					background.setFill(offColor);
					displayText.setText(offStr);
					displayText.setX(x + h);
				}
				
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
		knob.setOnMouseClicked(action);
		background.setOnMouseClicked(action);
		
	}
	public Rectangle getBg()
	{
		return background;
	}
	
	public Circle getKnob()
	{
		return knob;
	}
	
	public boolean getValue()
	{
		return value;
	}
	
	public void setColors(Color off, Color on, Color kn, Color st)
	{
		offColor = off;
		onColor = on;
		knobColor = kn;
		lineColor = st;
		
		saveColor.setFill(onColor);
		
		if(value)
			background.setFill(onColor);
		else
			background.setFill(offColor);
		
		knob.setFill(kn);
		
		saveColor.setStroke(offColor);
		background.setStroke(lineColor);
		knob.setStroke(lineColor);

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
	
	public void setText(String off, String on, Font f)
	{
		displayText.setOnMouseClicked(knob.getOnMouseClicked());
		components.add(displayText);
		displayText.setVisible(true);
		displayText.setFill(lineColor);
		
		if(f != null) //set font
			displayText.setFont(f);
		
		if(offStr == null)
			offStr = "";
		else
			offStr = off;
		
		if(onStr == null)
			onStr = "";
		else
			onStr = on;
		
		if(value) //on
		{
			displayText.setText(onStr);
		}
		else //off
		{
			displayText.setText(offStr);
		}
	}
	
	public Text getText()
	{
		return displayText;
	}
	
	public void move()
	{
		value = !value;

		if(value) //on
		{
			knob.setCenterX(xPos + width - height/2);
			background.setFill(onColor);
			displayText.setText(onStr);
			displayText.setX(xPos + height/2);
		}
		else //off
		{
			knob.setCenterX(xPos + height/2);
			background.setFill(offColor);
			displayText.setText(offStr);
			displayText.setX(xPos + height);
		}
		
	}
}
