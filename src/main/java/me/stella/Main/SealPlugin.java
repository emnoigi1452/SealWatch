package me.stella.Main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

import me.stella.Handlers.HandlerManager;
import me.stella.Handlers.WrapperTools;
import me.stella.Minecraft.GameWatcher;

public class SealPlugin extends JavaPlugin {
	
	public static Logger console = Logger.getLogger("Minecraft");
	public static SealPlugin instance;
	
	public final WrapperTools toolbox = new WrapperTools();
	
	@Override
	public void onEnable() {
		instance = this;
		HandlerManager.instance = new HandlerManager();
		GameWatcher.instance = new GameWatcher();
		console.log(Level.INFO, "Creating handler lists...");
		HandlerList tamper = getHandshakeTampering(instance.getServer());
	    RegisteredListener registeredListener = new RegisteredListener(GameWatcher.instance, (listener, event) -> GameWatcher.instance.onAnything(event), EventPriority.NORMAL, SealPlugin.instance, false);
	    for (HandlerList handler : HandlerList.getHandlerLists()) {
	       if(!(handler.equals(tamper)))
	    	   handler.register(registeredListener);
	    }
		console.log(Level.INFO, "API has been initialized - SealWatch");
	}
	
	@Override
	public void onDisable() {
		HandlerManager.instance.close();
		console.log(Level.INFO, "API has shutdown!");
	}
	
	public HandlerManager getHandlers() {
		return HandlerManager.instance;
	}
	
	private HandlerList getHandshakeTampering(Server server) {
		String tamper = "com.destroystokyo.paper.event.player.PlayerHandshakeEvent";
		ClassLoader serverLoader = server.getClass().getClassLoader();
		try {
			Class<?> parse = Class.forName(tamper, true, serverLoader);
			Object node = parse.getMethod("getHandlerList", (Class<?>[])null).invoke(null, new Object[] {});
			return (HandlerList)node;
		} catch(Exception e) { return null; }
	}
}
