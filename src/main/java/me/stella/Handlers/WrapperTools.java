package me.stella.Handlers;

import java.util.function.Consumer;

import org.bukkit.event.Event;

public class WrapperTools {
	
	public FunctionWrapper createWrapper(Consumer<Event> expression) {
		return (new FunctionWrapper(expression));
	}
	
	public FunctionWrapper createWrapper(Consumer<Event> expression, boolean async) {
		return (new FunctionWrapper(expression, async));
	}
}
