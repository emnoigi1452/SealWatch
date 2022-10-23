package me.stella.Handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.event.Event;

public class HandlerManager {
	
	public static HandlerManager instance;
	
	private Map<Class<? extends Event>, Map<String, FunctionWrapper>> listeners; 
	
	public HandlerManager() {
		this.listeners = new HashMap<Class<? extends Event>, Map<String, FunctionWrapper>>();
	}
	
	public void registerEvent(Class<? extends Event> event, String profile, FunctionWrapper function) {
		if(!(connected(event)))
			this.listeners.put(event, new HashMap<String, FunctionWrapper>());
		this.listeners.get(event).put(profile, function);
	}
	
	public void unregister(Class<? extends Event> event, String profile) {
		if(connected(event))
			this.listeners.get(event).remove(profile);
	}
	
	public void unregisterAll(Class<? extends Event> event) {
		this.listeners.remove(event);
	}
	
	public Set<Entry<String, FunctionWrapper>> getWrappers(Class<? extends Event> event) {
		if(!(connected(event)))
			return null;
		return this.listeners.get(event).entrySet();
	}
	
	public boolean connected(Event event) {
		return connected(event.getClass());
	}
	
	public boolean connected(Class<? extends Event> event) {
		return this.listeners.containsKey(event);
	}
	
	public boolean profileExisted(Event event, String profile) {
		return profileExisted(event.getClass(), profile);
	}
	
	public boolean profileExisted(Class<? extends Event> event, String profile) {
		if(!connected(event))
			return false;
		return this.listeners.get(event).containsKey(profile);
	}
	
	public void close() {
		this.listeners.clear();
	}

}
