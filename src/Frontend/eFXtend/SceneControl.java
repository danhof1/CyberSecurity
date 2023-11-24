package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class SceneControl
{
	ArrayList<Scene> scenes;
	ArrayList<EventHandler<Event>> events;
	
	public SceneControl()
	{
		scenes = new ArrayList<Scene>();
		events = new ArrayList<EventHandler<Event>>();
	}
	
	public void addScene(Scene scene, EventHandler<Event> eventHandler)
	{
		scenes.add(scene);
		events.add((EventHandler<Event>) eventHandler);
	}
	
	public EventHandler<Event> toScene(Scene scene)
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
