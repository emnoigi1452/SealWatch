package me.stella.Handlers;

import java.util.function.Consumer;

import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import me.stella.Main.SealPlugin;

public class FunctionWrapper {
	
	public static FunctionWrapper wrapper;
	public BukkitScheduler scheduler;
	
	private Consumer<Event> function;
	private boolean async;
	
	public FunctionWrapper(Consumer<Event> expression) {
		this(expression, false);
	}
	
	public FunctionWrapper(Consumer<Event> expression, boolean isAsync) {
		this.function = expression;
		this.async = isAsync;
		this.scheduler = SealPlugin.instance.getServer().getScheduler();
	}
	
	public void execute(final Event event) throws Exception {
		final Consumer<Event> reference = this.function;
		BukkitRunnable run = new BukkitRunnable() {
			public void run() {
				reference.accept(event);
			}
		};
		if(this.async)
			run.runTaskAsynchronously(SealPlugin.instance);
		else
			run.runTask(SealPlugin.instance);
	}
}
