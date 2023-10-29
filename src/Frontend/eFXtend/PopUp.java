package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.animation.Animation;

public class PopUp extends AnimatedMenu
{
	double[] maxOpacities;
	
	/**
	 * Makes a new pop up menu
	 * @param btn button to open pop up
	 * @param dur duration of animation
	 * @param list of component shapes
	 * @param buttonList list of button components
	 */
	public PopUp(ButtonX btn, int dur, ArrayList<Shape> list, ArrayList<Control> buttonList)
	{        
		triggerBtn = btn;
		duration = dur;
		components = list;
		buttons = buttonList;
		animationsOn = true;
		maxOpacities = new double[components.size()];
		
        animations = new ArrayList<Animation>();
        
        for(int i = 0; i < list.size(); i++)
        {
        	Shape curShape = components.get(i);
        	double maxOpacity = curShape.getOpacity();
        	
        	maxOpacities[i] = maxOpacity;
        	
        	FadeTransition fade = new FadeTransition();  
            fade.setFromValue(0);  
            fade.setToValue(maxOpacity);
            fade.setDuration(Duration.millis(duration));  
            fade.setAutoReverse(true);
            fade.setNode(components.get(i));
            animations.add(fade);
            
            curShape.setOpacity(0);
            curShape.setVisible(false);
            
        }
        
        if(buttons != null)
        {
	        for(int i = 0; i < buttons.size(); i++)
	        {
	        	buttons.get(i).setVisible(false);
	        }
        }
        
		EventHandler<Event> popUpOpen = new EventHandler<Event>() 
	    {  
            
            @Override  
            public void handle(Event event) 
            { //popup open  
            	if(animationsOn)
            	{
	            	for(int j = 0; j < animations.size(); j++)
	            	{
	            		FadeTransition fade = (FadeTransition)animations.get(j);
	            		fade.play();
	            		
	            		double temp = fade.getFromValue();
	            		fade.setFromValue(fade.getToValue());
	            		fade.setToValue(temp);
	            	}
	            	
	            	for(int k = 0; k < list.size(); k++)
	            	{
	            		components.get(k).setVisible(true);
	            		components.get(k).toFront();
	            	}
	            	
            	} //end animations on
            	else //animations off
            	{
            		for(int j = 0; j < components.size(); j++)
            		{
            			Shape shape = components.get(j);
            			double maxOpacity = maxOpacities[j];
            			
            			if(shape.getOpacity() > 0)
            			{
            				shape.setVisible(false);
            				shape.setOpacity(0);
            			}
            			
            			else
            			{
            				shape.setVisible(true);
            				shape.setOpacity(maxOpacity);
            				shape.toFront();
            			}
            		}
            	} //end animations off
            	
            	if(buttons != null)
        	    {
        	    	for(int k = 0; k < buttons.size(); k++)
        	    	{
        	    		Control btn = buttons.get(k);
        	    		btn.setVisible(!(buttons.get(k)).isVisible());
        	    		btn.toFront();
        	    	}
        	    }
            	
            	
				event.consume();
            }  //end popup open
	    };  
	    triggerBtn.addAction(popUpOpen);
               
        //btn.getButton().setOpacity(0);        
	}
}
	
