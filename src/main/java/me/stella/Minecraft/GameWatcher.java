package me.stella.Minecraft;

import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.stella.Handlers.FunctionWrapper;
import me.stella.Handlers.HandlerManager;
import me.stella.Main.SealPlugin;

public class GameWatcher implements Listener {
	
	public static GameWatcher instance;
	
	public void onAnything(final Event e) {
		if(HandlerManager.instance.connected(e)) {
			new BukkitRunnable() {
				public void run() {
					Set<Entry<String, FunctionWrapper>> regs = HandlerManager.instance.getWrappers(e.getClass());
					String name = ""; FunctionWrapper wrapper = null;
					try {
						for(Entry<String, FunctionWrapper> ent: regs) {
							name = ent.getKey();
							wrapper = ent.getValue();
							wrapper.execute(e);
						}
					} catch(Exception e1) {
						SealPlugin.console.log(Level.WARNING, "Error upon trying to handle Event-[" + e.getEventName() + "]");
						SealPlugin.console.log(Level.WARNING, "Error caused by listener with id " + name);
						e1.printStackTrace();
						HandlerManager.instance.unregister(e.getClass(), name); return;
					}
				}
			}.runTask(SealPlugin.instance);
		}
	}
} 
