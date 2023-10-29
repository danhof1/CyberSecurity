package Frontend.eFXtend;
import java.util.ArrayList;

import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
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

public class TimeSelect extends Menu
{
	private int width;
	private int height;
	private int xPos;
	private int yPos;
	private int arc;
	private Color fill;
	private Color stroke;
	
	private int hr;
	private int min;
	private boolean isAm;
	private Text time;
	
	private Rectangle box;
	
	Button hrBtn;
	Button minBtn;
	Button amBtn;
	private int hrInc;
	private int minInc;
	
	/**
	 * Makes a time select menu item
	 * @param x position of object
	 * @param y position of object
	 * @param a arc size (rounded edges)
	 * @param fillC fill color
	 * @param strokeC stroke color
	 * @param font font of text
	 */
	TimeSelect(int x, int y, int a, Color fillC, Color strokeC, Font font)
	{
		super();
		//instantiate variables
		width = (int)font.getSize()*6;
		height = (int)(font.getSize() * 2);
		xPos = x;
		yPos = y;
		arc = a;
		fill = fillC;
		stroke = strokeC;
		isAm = false;
		
		hr = 12;
		min = 0;
		hrInc = 1;
		minInc = 1;
	
		//make text
		time = new Text(toString());
		if(font != null)
			time.setFont(font);
		time.setX(xPos);
		time.setY(yPos + (height - (int)(time.getFont().getSize()/1.5)));
		time.setWrappingWidth(width);
		time.setTextAlignment(TextAlignment.CENTER);
		time.setFill(strokeC);
		
		//make box
		box = icon(width, height, xPos, yPos, arc, fill, stroke);

		//add to components
		components.add(box);
		components.add(time);
		
		//make buttons
		hrBtn = new Button();
		hrBtn.setPrefHeight(height);
		hrBtn.setPrefWidth(height*0.8);
		hrBtn.setLayoutX(xPos + (width - (height*0.8)*3)/2);
		hrBtn.setLayoutY(yPos);
		hrBtn.setOpacity(0);
		
		minBtn = new Button();
		minBtn.setPrefHeight(height);
		minBtn.setPrefWidth(height*0.8);
		minBtn.setLayoutX(hrBtn.getLayoutX() + height*0.8);
		minBtn.setLayoutY(yPos);
		minBtn.setOpacity(0);
		
		amBtn = new Button();
		amBtn.setPrefHeight(height);
		amBtn.setPrefWidth(height*0.8);
		amBtn.setLayoutX(minBtn.getLayoutX() + height*0.8);
		amBtn.setLayoutY(yPos);
		amBtn.setOpacity(0);
		
		//add buttons to components
		buttons.add(hrBtn);
		buttons.add(minBtn);
		buttons.add(amBtn);
		
		//button effects
		//increments hr by hrInc
		hrBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
            public void handle(ActionEvent arg0) 
            {
				updateHr(hrInc);
            }
		});
		
		//increments min by minInc
		minBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
            public void handle(ActionEvent arg0) 
            {
				updateMin(minInc);
            }
		});
				
		//toggles am/pm
		amBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override 
            public void handle(ActionEvent arg0) 
            {
				isAm = !isAm;
					
				String str = time.getText();
				if(isAm)
					str = str.replace("P", "A");
				else
					str = str.replace("A", "P");
				time.setText(str);
            }
		});
	}
	
	/**
	 * Makes a time select menu item
	 * @param a arc size (rounded edges)
	 * @param fillC fill color
	 * @param strokeC stroke color
	 * @param font font of text
	 */
	public TimeSelect(int a, Color fillC, Color strokeC, Font font)
	{
		super();
		//instantiate variables
		width = (int)font.getSize()*6;
		height = (int)(font.getSize() * 2);
		xPos = 0;
		yPos = 0;
		arc = a;
		fill = fillC;
		stroke = strokeC;
		isAm = false;
		
		hr = 12;
		min = 0;
		hrInc = 1;
		minInc = 1;
	
		//make text
		time = new Text(toString());
		if(font != null)
			time.setFont(font);
		time.setX(xPos);
		time.setY(yPos + (height - (int)(time.getFont().getSize()/1.5)));
		time.setWrappingWidth(width);
		time.setTextAlignment(TextAlignment.CENTER);
		time.setFill(strokeC);
		
		//make box
		box = icon(width, height, 0, 0, arc, fill, stroke);

		//add to components
		components.add(box);
		components.add(time);
		
		//make buttons
		hrBtn = new Button();
		hrBtn.setPrefHeight(height);
		hrBtn.setPrefWidth(height*0.8);
		hrBtn.setLayoutX(xPos + (width - (height*0.8)*3)/2);
		hrBtn.setLayoutY(yPos);
		hrBtn.setOpacity(0);
		
		minBtn = new Button();
		minBtn.setPrefHeight(height);
		minBtn.setPrefWidth(height*0.8);
		minBtn.setLayoutX(hrBtn.getLayoutX() + height*0.8);
		minBtn.setLayoutY(yPos);
		minBtn.setOpacity(0);
		
		amBtn = new Button();
		amBtn.setPrefHeight(height);
		amBtn.setPrefWidth(height*0.8);
		amBtn.setLayoutX(minBtn.getLayoutX() + height*0.8);
		amBtn.setLayoutY(yPos);
		amBtn.setOpacity(0);
		
		//add buttons to components
		buttons.add(hrBtn);
		buttons.add(minBtn);
		buttons.add(amBtn);
		
		//button effects
				//increments hr by hrInc
				hrBtn.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
		            public void handle(ActionEvent arg0) 
		            {
						updateHr(hrInc);
		            }
				});
				
				//increments min by minInc
				minBtn.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
		            public void handle(ActionEvent arg0) 
		            {
						updateMin(minInc);
		            }
				});
						
				//toggles am/pm
				amBtn.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override 
		            public void handle(ActionEvent arg0) 
		            {
						isAm = !isAm;
							
						String str = time.getText();
						if(isAm)
							str = str.replace("P", "A");
						else
							str = str.replace("A", "P");
						time.setText(str);
		            }
				});
			}
	
	/**
	 * sets the displayed time
	 * @param newHr new hour to be displayed, 0 to not change
	 * @param newMin new minute to be displlayed, 0 to not change
	 * @param am true if am, false if pm
	 */
	public void setTime(int newHr, int newMin, boolean am)
	{
		if(newHr != 0)
			hr = newHr;
		
		if(newMin != 0)
			min = newMin;
		
		isAm = am;
		//time.setText(toString());
	}
	
	/**
	 * sets the displayed time
	 * @param newHr new hour to be displayed, 0 to not change
	 * @param newMin new minute to be displlayed, 0 to not change
	 */
	public void setTime(int newHr, int newMin)
	{
		if(newHr != 0)
			hr = newHr;
		
		if(newMin != 0)
			min = newMin;	
		//time.setText(toString());
	}
	
	/**
	 * @return the hour as an int
	 */
	public int getHour()
	{
		return hr;
	}
	
	/**
	 * @return the minutes as an int
	 */
	public int getMin()
	{
		return hr;
	}
	
	/**
	 * @param inc value to increment minutes by
	 */
	public void setMinInc(int inc)
	{
		if(inc < 60)
			minInc = inc;
	}
	
	/**
	 * @param inc value to increment minutes by
	 */
	public void setHrInc(int inc)
	{
		if(inc < 60)
			hrInc = inc;
	}
	
	/**
	
	 * @return true if am, false if pm
	 */
	public boolean isAM()
	{
		return isAm;
	}
	
	/**
	 * Updates hour value
	 * @param num value to increment by
	 */
	private void updateHr(int num)
	{
		if(hr < 12)
			hr += num;
		else
		{
			hr = 1;
		}
		
		String str = time.getText();
		str = str.substring(2);
		str = hr + str;
		if(hr < 10)
			str = 0 + str;
		time.setText(str);
	}
	
	/**
	 * Updates min value
	 * @param num value to increment by
	 */
	private void updateMin(int num)
	{
		if(min < 60)
		{
			min += num;
			if(min > 59)
			{
				min -= 60;
			}
		}
		else
			min = 0;
		
		String str = time.getText().substring(0, 5);
		if(min < 10)
			str = str + 0;
		str =  str + min + time.getText().substring(7);
		time.setText(str);
	}
	
	/**
	 * Toggles am/pm and updates text
	 */
	private void toggleAm()
	{
		isAm = !isAm;
		
		String str = time.getText();
		if(isAm)
			str = str.replace("P", "A");
		else
			str = str.replace("A", "P");
		time.setText(str);
	}
	
	/**
	 * @return Returns the displayed time as a string in hr:min XM form
	 */
	@Override
	public String toString()
	{
		String str = new String();
		if(hr < 10)
			str += "0";
		
		str += hr + " : ";
		
		if(min < 10)
			str += "0";
		
		str += min + " ";
		
		if(isAm)
			str += "AM";
		
		else
			str += "PM";
		
		return str;
	}

	/**
	 * Sets width of object
	 * @param w new width
	 */
	
	/**
	 * @return width of object
	 */
	@Override
	public double getWidth()
	{
		return width;
	}
	
	/**
	 * @return height of object
	 */
	@Override
	public double getHeight()
	{
		return height;
	}
	
	/**
	 * Sets x position of object
	 * @param x position
	 */
	public void setX(int x)
	{
		xPos = x;
		box.setX(xPos);
		time.setX(xPos);
		
		hrBtn.setLayoutX(box.getX() + (width - (height*0.8)*3)/2);
		minBtn.setLayoutX(hrBtn.getLayoutX() + height*0.8);
		amBtn.setLayoutX(minBtn.getLayoutX() + height*0.8);
	}
	
	@Override
	public double getX()
	{
		return xPos;
	}
	
	/**
	 * Sets x position of object
	 * @param x position
	 */
	public void setY(int y)
	{
		yPos = y;
		box.setY(yPos);
		time.setY(yPos + (height - (int)(time.getFont().getSize()/1.5)));
		hrBtn.setLayoutY(yPos);
		minBtn.setLayoutY(yPos);
		amBtn.setLayoutY(yPos);

	}
	
	@Override
	public double getY()
	{
		return yPos;
	}
	
	/**
	 * Changes the font of the box, adjusts the size and position accordingly
	 * @param font new font
	 */
	public void setFont(Font font)
	{
		time.setFont(font);
		
		int oldWidth = width;
		int oldHeight = height;
		
		width = (int)font.getSize()*6;
		height = (int)(font.getSize() * 2);
		
		box.setWidth(width);
		box.setHeight(height);
		
		setX((oldWidth - width)/2);
		setY((oldHeight - height)/2);
		
	}
	
	public String getValue()
	{		
		String val = new String();
		
		if(isAm)
		{
			if(hr != 12)
				val = hr + "-";
			else
				val = "0-";
		}
		else
		{
			if(hr != 12)
				val = (hr + 12) + "-";
			else
				val = hr + "-";
		}
		
		val = val + min;
		
		return val;
	
	}

}
