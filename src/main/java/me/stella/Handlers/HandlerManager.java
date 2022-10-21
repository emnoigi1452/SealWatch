package me.stella.Handlers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.Event;

public class HandlerManager {
	
	public static HandlerManager instance;
	
	private Map<Class<? extends Event>, FunctionWrapper> listeners; 
	
	public HandlerManager() {
		this.listeners = new HashMap<Class<? extends Event>, FunctionWrapper>();
	}
	
	public void registerEvent(Class<? extends Event> event, FunctionWrapper function) {
		this.listeners.put(event, function);
	}
	
	public void unregister(Class<? extends Event> event) {
		this.listeners.remove(event);
	}
	
	public FunctionWrapper getWrapper(Event e) {
		return getWrapper(e.getClass());
	}
	
	public FunctionWrapper getWrapper(Class<? extends Event> event) {
		return this.listeners.getOrDefault(event, null);
	}
	
	public boolean connected(Event event) {
		return connected(event.getClass());
	}
	
	public boolean connected(Class<? extends Event> event) {
		return this.listeners.containsKey(event);
	}
	
	public void close() {
		this.listeners.clear();
	}

}
