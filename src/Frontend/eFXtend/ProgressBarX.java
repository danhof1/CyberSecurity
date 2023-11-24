package Frontend.eFXtend;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;

public class ProgressBarX extends Menu
{
	private ProgressBar prog;
	private Scene scene;
	
	/**
	 * Makes text field from a rectangle
	 * @param w width
	 * @param h height
	 * @param x position
	 * @param y position
	 * @return progress bar
	 */
	public ProgressBarX (int w, int h, int x, int y)
	{
		super();
		prog = new ProgressBar();
		prog.setLayoutX(x);
		prog.setLayoutY(y);
		prog.setPrefHeight(h);
		prog.setPrefWidth(w);
	}
	
	/**
	 * Sets progress
	 * @param p progress
	 */
	public void setProgress(double p)
	{
		prog.setProgress(p);
	}
	
	public double getProgress()
	{
		return prog.getProgress();
	}
	
	@Override
	public void addToGroup(Group root)
	{
		scene = root.getScene();
		root.getChildren().add(prog);
	}
	
	
	
}
