package application.eFXtend;

import java.util.ArrayList;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class DropDown extends AnimatedMenu
{
	private Rectangle rect;
	private int distance;
	private int angle;
	
	/**
	 * Constructor of DropDown object
	 * @param button graphic
	 * @param distance for menu to move
	 * @param list of DropDown 
	 * @param list of button components
	 * @return text field
	 */
	public DropDown(Rectangle r, int dist, int dur, ArrayList<Shape> list, ArrayList<Control> buttonList)
	{
		rect = r;
		distance = dist;
		duration = dur;
		vertical = true;
		buttons = buttonList;
		components = list;
		animationsOn = true;
		angle = 180;
		
		
        RotateTransition rotate = new RotateTransition();
        rotate.setByAngle(180);  
        rotate.setDuration(Duration.millis(duration));  
        rotate.setAutoReverse(true);
        rotate.setAxis(Rotate.Z_AXIS);  
        rotate.setNode(rect);
        
        Button btn = new Button();
        
        triggerBtn = new ButtonX(btn);
        btn.setLayoutX(rect.getX());
        btn.setLayoutY(rect.getY());
        btn.setPrefWidth(rect.getWidth());
        btn.setPrefHeight(rect.getHeight());
                
        ArrayList<TranslateTransition> animations = new ArrayList<TranslateTransition>();
        
        for(int i = 0; i < components.size(); i++)
        {
        	 TranslateTransition roll = new TranslateTransition();  
             roll.setByY(distance);  
             roll.setDuration(Duration.millis(duration));  
             roll.setAutoReverse(true);
             roll.setNode(components.get(i));
             animations.add(roll);
        }
        
        //triggerBtn.setOnAction(new EventHandler<ActionEvent>() 
        EventHandler<Event> open = new EventHandler<Event>()
	    {  
            
            @Override  
            public void handle(Event event) 
            {  

            	distance = -1 * distance;
            	
            	if(animationsOn)
            	{
                	triggerBtn.setVisible(false);

	            	rotate.play();
	            	
	            	for(int j = 0; j < animations.size(); j++)
	            	{
	            		TranslateTransition roll = animations.get(j);
	            		
	            		roll.play();
	            			
	            		if(vertical)
	            			roll.setByY(distance);
	            		else
	            			roll.setByX(distance);
	            	}
            	}
            	else
            	{
            		rect.setRotate(angle);
            		
            		if(angle == 180)
            			angle = 0;
            		else
            			angle = 180;
            		
            		for(int j = 0; j < components.size(); j++)
            		{
            			Shape shape = components.get(j);
            			
            			if(vertical)
            				shape.setLayoutY(shape.getLayoutY() + -1*distance);
            			else
                			shape.setLayoutX(shape.getLayoutX() + -1*distance);
            		}
            	}
            	
            	
            	if(buttons != null)
        	    {
        	    	for(int k = 0; k < buttons.size(); k++)
        	    	{
        	    		buttons.get(k).setVisible(!(buttons.get(k)).isVisible());
        	    	}
        	    }
            	event.consume();
            }  
	    };  
       
        rotate.setOnFinished((finish) -> triggerBtn.setVisible(true));
        triggerBtn.addAction(open);
        
        triggerBtn.getButton().setOpacity(0);        
	}
	
	/**
	 * Sets the direction of the DropDown
	 * @param if the direction is up/down 
	 */
	public void makeVertical(Boolean isVert)
	{
		if(vertical == isVert)
			return;
		
		vertical = isVert;
		
		if(vertical)
		{
			for(int i = 0; i < animations.size(); i++)
			{
				TranslateTransition roll= (TranslateTransition)animations.get(i);
				roll.setByX(distance);
				roll.setByY(0);
			}
			
			for(int i = 0; i < animations.size(); i++)
			{
				TranslateTransition roll= (TranslateTransition)animations.get(i);
				roll.setByY(distance);
				roll.setByX(0);
			}
		}
	}

	
	/**
	 * Flips the direction the menu moves
	 */
	public void flipDirection()
	{
		distance = -distance;
	}
	
}
