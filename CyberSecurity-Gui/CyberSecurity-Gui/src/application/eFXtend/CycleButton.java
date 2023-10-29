package application.eFXtend;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CycleButton extends ButtonSwitch
{
	
	/**
	 * A button that cycles through different texts
	 * @param x position
	 * @param y position
	 * @param strList list of strings to cycle through
	 * @param c text color
	 * @param f
	 */
	public CycleButton(int w, int x, int y, ArrayList<String> strList, Color c, Font f)
	{
		super();
		
		xPos = x;
		yPos = y;
		labels = strList;
		defColor = c;
		font = f;
		N = labels.size();
		width = w;
		selIndex = 0;
		makeButtons();
	}
	
	@Override
	public void addToGroup(Group root)
	{
		root.getChildren().add(components.get(0));
		
		for(int i = 0; i < N; i++)
		{
			root.getChildren().add(buttons.get(i));
		}
	}
	
	@Override
	public void makeButtons()
	{
		Text txt = new Text();
		txt.setFill(defColor);
		txt.setX(xPos);
		txt.setY(yPos);
		txt.setUnderline(true);
		txt.setText(labels.get(0));
		txt.setFont(font);
		txt.setWrappingWidth(width);
		txt.setTextAlignment(TextAlignment.CENTER);
		components.add(txt);
		
		Button btn = new Button();
		btn.setPrefWidth(width);
		btn.setPrefHeight(font.getSize());
		btn.setLayoutX(xPos);
		btn.setLayoutY(yPos - (int)(font.getSize()*1.2));
		btn.setOpacity(0);
		buttons.add(btn);
		
		ButtonX btnX = new ButtonX(btn);
		
		EventHandler<Event> doSomething = new EventHandler<Event>() 
	    {  
            @Override  
            public void handle(Event event) 
            {  
            	if(!defColor.equals(txt.getFill()))
            			defColor = (Color)txt.getFill();
            	
            	if(selIndex == N-1)
            		selIndex = 0;
            	else
            		selIndex++;
            	
            	txt.setText(labels.get(selIndex));
                event.consume();
            }
	    };
	    btnX.addAction(doSomething);
	}

	public void setWidth(int w)
	{
		width = w;
		
		Text txt = (Text)components.get(0);
		txt.setWrappingWidth(width);
		
		for(int i = 0; i < N; i++)
		{
			Button btn = (Button)buttons.get(i);
			btn.setPrefWidth(width);
		}
	}
	
	public double getWidth()
	{
		return width;
	}
}
	

