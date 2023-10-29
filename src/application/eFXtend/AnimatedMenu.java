package application.eFXtend;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class AnimatedMenu extends Menu
{
	protected ButtonX triggerBtn;
	protected int duration;
	protected Boolean animationsOn;
	protected ArrayList<Animation> animations;
	
	public AnimatedMenu()
	{
		super();
	}
	
	/**
	 * Toggles if the menu has animations
	 */
	public void toggleAnimations()
	{
		animationsOn = !animationsOn;
	}
	
	/**
	 * @return Returns if the dropdowns animations are on
	 */
	public boolean isAnimated()
	{
		return animationsOn;
	}

	/**
	 * @return Returns the trigger button
	 */
	public Button getButton()
	{
		return triggerBtn.getButton();
	}
	
	/**
	 * @return Returns the trigger button (ButtonX)
	 */
	public ButtonX getButtonX()
	{
		return triggerBtn;
	}
	
}
