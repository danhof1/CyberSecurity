package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class SceneControl
{
	ArrayList<Scene> scenes;
	ArrayList<EventHandler<MouseEvent>> events;
	
	public SceneControl()
	{
		scenes = new ArrayList<Scene>();
		events = new ArrayList<EventHandler<MouseEvent>>();
	}
	
	public void addScene(Scene scene, EventHandler<? super MouseEvent> eventHandler)
	{
		scenes.add(scene);
		events.add((EventHandler<MouseEvent>) eventHandler);
	}
	
	public EventHandler<MouseEvent> toScene(Scene scene)
	{
		int index = scenes.indexOf(scene);
		if(index != -1)
			return events.get(index);
		
		else
			return null;
	}
	
	public ArrayList<Scene> toArray()
	{
		return scenes;
	}
	
}
