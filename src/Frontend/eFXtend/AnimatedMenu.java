package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.scene.control.Button;

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
