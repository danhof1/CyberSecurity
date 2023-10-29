package application.eFXtend;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;

public class ButtonSwitch extends Menu
{
	protected int width;
	private int height;
	protected int xPos;
	protected int yPos;
	protected ArrayList<String> labels;
	protected ArrayList<ButtonX> buttonXs;
	
	protected Color defColor;
	private Color selColor;
	protected Font font;
	protected int N;
	protected int selIndex;	
	
	ButtonSwitch()
	{
		super();
		buttonXs = new ArrayList<ButtonX>();
	}
	
	/**
	 * @param width of region
	 * @param height of region
	 * @param x position
	 * @param y position
	 * @param isVert true if menu goes up/down, false if left/right
	 * @param components
	 * @param text strings
	 * @param default color of text
	 * @param color of text when selected
	 * @param font of text
	 */
	public ButtonSwitch(int w, int h, int x, int y, Boolean isVert, ArrayList<String> strList, Color c1, Color c2, Font f)
	{
		super();
		width = w;
		height = h;
		xPos = x;
		yPos = y;
		vertical = isVert;
		labels = strList;
		defColor = c1;
		selColor = c2;
		font = f;
		N = labels.size();
		buttonXs = new ArrayList<ButtonX>();
		
		makeButtons();
	}
	
	protected void makeButtons()
	{
		for(int i = 0; i < N; i++)
		{
			String str = labels.get(i);
			int staPos;
			int dynPos;
			selIndex = 0;
			
			//Setup text object
			Text txt = new Text();
			txt.setTextAlignment(TextAlignment.CENTER);
			txt.setText(str);
			txt.setFont(font);
			components.add(txt);

			//Setup button
			Button btn = new Button();
			btn.setOpacity(0);
			btn.toFront();
			
			buttons.add(btn);
			
			ButtonX btnX = new ButtonX(btn);
			buttonXs.add(btnX);
			
			//Set color
			if(i == 0)
			{
				txt.setFill(selColor);
				txt.setUnderline(true);
			}
			else
				txt.setFill(defColor);
			
			if(vertical)
			{
				//Coordinates
				staPos = xPos;
				dynPos = yPos + height/N * (i+1);
				
				//Text
				txt.setWrappingWidth(width);
				txt.setX(staPos);
				txt.setY(dynPos - height/(N*N));
				
				//Button
				btn.setLayoutX(staPos);
				btn.setLayoutY((int)(dynPos) - height/N);
				btn.setPrefWidth(width);
				btn.setPrefHeight(height/N);
								 
			} //end vertical
			else //horizontal
			{
				//Coordinates
				staPos = yPos;
				dynPos = xPos + width/N * (i+1);
				
				//Text
				txt.setWrappingWidth(width/N);
				txt.setY(staPos);
				txt.setX(dynPos - width/(N)); //changed N*N --> N
				
				//BButton
				btn.setLayoutY(staPos - height);
				btn.setLayoutX((int)(dynPos) - width/N);
				btn.setPrefWidth(width/N);
				btn.setPrefHeight(height);
				
			} //end horizontal
			
			//Button functions
			//btn.setOnAction(new EventHandler<ActionEvent>() 
		    EventHandler<Event> doSomething = new EventHandler<Event>()
			{  
	            @Override  
	            public void handle(Event event) 
	            {
	            	//detect color changes
	            	if(!components.get(0).getFill().equals(defColor) && !components.get(0).getFill().equals(selColor))
	            	{
	            		Color s = (Color)components.get(selIndex).getFill();
	            	
	            		int temp = 0;
	            		if(selIndex == 0)
	            			temp++;
	            		
	            		Color d = (Color)components.get(temp).getFill();
	            		
	            		setColors(d, s);
	            	}
	            	
	            	selIndex = buttons.indexOf(btn);
	            	txt.setFill(selColor);
	            	txt.setUnderline(true);
	            	for(int j = 0; j < N; j++)
	            	{
	            		Shape curTxt = components.get(j);
	            		if(!curTxt.equals(txt))
	            		{
	            			curTxt.setFill(defColor);
	            			
	            			if(curTxt.getClass().toString().contains("Text"))
	            				((Text)curTxt).setUnderline(false);
	            		}
	            	}
	            	event.consume();
	            } 
		    };
			btnX.addAction(doSomething);
		}
	}
	
	/**
	 * Adds the popup menu and all it's components to a group
	 * @param root group to add popup to
	 */
	public void addToGroup(Group root)
	{
		for(int i = 0; i < labels.size(); i++)
		{
			root.getChildren().add(components.get(i));
			root.getChildren().add(buttons.get(i));

		}
	}
	
	public void setColors(Color d, Color s)
	{
		for(int i = 0; i < components.size(); i++)
		{
			if(i == selIndex)
			{
				components.get(i).setFill(s);
			}
			else
				components.get(i).setFill(d);
		}
		
		defColor = d;
		selColor = s;
	}
	
	public int size()
	{
		return N;
	}
	
	@Override
	public double getX()
	{
		return xPos;
	}
	
	@Override
	public double getY()
	{
		return yPos;
	}	
	
	public ArrayList<String> getLabels()
	{
		return labels;
	}
	
	public String getValue()
	{
		return labels.get(selIndex);
	}
	
	public ArrayList<ButtonX> getButtonXs()
	{
		return buttonXs;
	}
	
	public ButtonX getButton(String str)
	{
		int index = labels.indexOf(str);
		if(index == -1)
			return null;
		
		return buttonXs.get(index);
	}
	
}