package Frontend.eFXtend;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sfxtend
{
	//sound name, sound player
	private HashMap<String, MediaPlayer> sounds;
	private HashMap<String, Double> volumes;
	private double volume;
	private boolean mute;
	
	public Sfxtend()
	{
		sounds = new HashMap<String, MediaPlayer>();
		volumes = new HashMap<String, Double>();
		volume = 1;
		mute = false;
	}
	
	/**
	 * Adds a sound at full volume
	 * @param name of sound
	 * @param path to sound file
	 */
	public void addSound(String name, String path)
	{
		Media sound = new Media(new File(path).toURI().toString());
		MediaPlayer player = new MediaPlayer(sound);  
		
		//set volume, mute property
		player.setVolume(volume);
		player.setMute(false);
		
		//add to hashmap
		sounds.put(name, player);
		volumes.put(name, 1.0);
	}
	
	/**
	 * Removes a sound
	 * @param name of sound
	 */
	public void removeSound(String name)
	{
		sounds.remove(name);
	}
	
	/**
	 * Adds a sound with specific volume
	 * @param name of sound
	 * @param path to sound file
	 * @param vol volume of sound
	 */
	public void addSound(String name, String path, double vol)
	{
		Media sound = new Media(new File(path).toURI().toString());
		MediaPlayer player = new MediaPlayer(sound);  
		
		//set volume, mute property
		player.setVolume(vol * volume);
		player.setMute(false);
		
		//add to hashmap
		sounds.put(name, player);
		volumes.put(name, vol);
	}
	
	/**
	 * Plays a sound
	 * @param name of sound to play
	 * @return true if success, false if failed
	 */
	public boolean playSound(String name)
	{
		MediaPlayer player = sounds.get(name);
		if(player != null)
		{
			player.seek(player.getStartTime());
			player.play();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Gets a sound
	 * @param name of sound
	 * @return MediaPlayer for sound
	 */
	public MediaPlayer getSound(String name)
	{
		return sounds.get(name);
	}
	
	/**
	 * Sets volume of all sounds
	 * @param vol volume to set sounds to
	 */
	public void setVolume(double vol)
	{
		if(volume == vol)
			return;
		
		volume = vol;
		for(String name: sounds.keySet())
		{
			double curVol = volumes.get(name);
			MediaPlayer player = sounds.get(name);
			player.setVolume(vol * curVol);
			if(player.getVolume() == 0)
				player.setMute(true);
			else
				player.setMute(false);
		}
	}
	/**
	 * Sets volume of specific sound
	 * @param name of sound
	 * @param vol volume to set sound to
	 */
	public void setVolume(String name, double vol)
	{	
		if(volumes.get(name) == vol)
			return;
		
		MediaPlayer player = sounds.get(name);
		player.setVolume(volume * vol);
		volumes.put(name, vol);
		
		if(player.getVolume() == 0)
			player.setMute(true);
		else
			player.setMute(false);
	}
	
	/**
	 * Sets volume of all sounds, even if they have custom volumes
	 * @param vol volume to set sounds to
	 */
	public void forceVolume(double vol)
	{
		volume = vol;
		for(String name: sounds.keySet())
		{
			MediaPlayer player = sounds.get(name);
			player.setVolume(volume);
			volumes.put(name, 1.0);
			
			if(player.getVolume() == 0)
				player.setMute(true);
			else
				player.setMute(false);
		}
	}
	
	public void setMute(boolean bool)
	{
		if(mute == bool)
			return;
		
		mute = bool;
		for(String name: sounds.keySet())
		{
			MediaPlayer player = sounds.get(name);
			
			if(player.getVolume() != 0)
				player.setMute(mute);
		}
	}
	
	public Collection<MediaPlayer> getSounds()
	{
		return sounds.values();
	}
	
	public boolean isMute()
	{
		return mute;
	}
	
	public double getVolume()
	{
		return volume;
	}
	
	public double getVolume(String name)
	{
		return volumes.get(name) * volume;
	}
}
