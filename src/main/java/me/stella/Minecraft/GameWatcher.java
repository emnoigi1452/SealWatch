package me.stella.Minecraft;

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
		if(!(HandlerManager.instance.connected(e)))
			return;
		new BukkitRunnable() {
			public void run() {
				FunctionWrapper wrapper = HandlerManager.instance.getWrapper(e);
				if(wrapper == null) return;
				try { wrapper.execute(e); } 
				catch(Exception e1) {
					SealPlugin.console.log(Level.WARNING, "Error upon trying to handle Event-[" + e.getEventName() + "]");
					e1.printStackTrace();
					HandlerManager.instance.unregister(e.getClass());
				}
			}
		}.runTask(SealPlugin.instance);
	}
} 
