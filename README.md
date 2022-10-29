## SealWatch - An API to control custom event listeners

***What is this?*** This is a plugin I decided to code while I was bored the other day. It helps to register custom event listeners through the use of external scripts.

For instance, you could use <a href="https://github.com/PlaceholderAPI/Javascript-Expansion">Javascript-Expansion</a> by clip to create custom event listeners with this plugin.

## Installation
- There's nothing special, just run it like a normal plugin and go wild with it.

***P/S: All the code samples are made with Javascript-Expansion***

## Methods you could use:
- *To get the Main class*:
```javascript
var Main = BukkitServer.getPluginManager().getPlugin("SealWatch");
```
- *To get the HandlerManager*:
```javascript
var Handlers = Main.getHandlers();
```
- *To unregister a listener*
```javascript
// Unregister a specific listener
Handlers.unregister(eventClass, "listener_name", wrapper);
// Unregister the entire event
Handlers.unregisterAll(eventClass);
```

- *To check if a listener is registered*
```javascript
// Check if the event listener is on
Handlers.connected(event);
// Check if a certain listener is on
Handlers.profileExisted(event, listenerName);
```

## ***Code samples***
- *To create a wrapper* - Main.toolbox.createWrapper(function(event), isAsync)
  - function(event): A function to handle the event that takes in 1 argument - The event itself
  - isAsync: A boolean value to decide if the event should be handled asynchronously
```javascript
var Main = BukkitServer.getPluginManager().getPlugin("SealWatch");
// This wrapper is handled asynchronously
var Wrapper_Async = Main.toolbox.createWrapper(function(e) {
  print("This is an event!");
}, true);
// This wrapper is not handled asynchronously
var Wrapper_Not_Async = Main.toolbox.createWrapper(function(e) {
  print("This is another event")
}, false);
```
- *How to register an event* - HandlerManager.registerEvent(eventClass, String listenerName, wrapper);
  - eventClass: The event class (Ex: org.bukkit.event.player.PlayerMoveEvent.class)
  - listenerName: A custom name for this listener (Like a unique id of sort)
  - wrapper: A wrapper to handle the event
```javascript
var eventClass = org.bukkit.event.player.PlayerMoveEvent.class;
// Registers a listener to handler a PlayerMoveEvent
Handlers.registerEvent(eventClass, "event-name", wrapper);
```
***Examples***

###### To create/remove a listener to handle PlayerMoveEvent
```javascript
var API = BukkitServer.getPluginManager().getPlugin("SealWatch");
// Event is handled asynchronously
var Wrapper = API.toolbox.createWrapper(function(e) {
  e.getPlayer().sendMessage("You moved!");
}, true);
// Register the listener with the name "move-test"
API.getHandlers().registerEvent(org.bukkit.event.player.PlayerMoveEvent.class, "move-test", Wrapper);
// Unregister the listener
API.getHandlers().unregister(org.bukkit.event.player.PlayerMoveEvent.class, "move-test");
```

###### To create/remove a listener to handle PlayerQuitEvent
```javascript
var API = BukkitServer.getPluginManager().getPlugin("SealWatch");
// Event is not handled asynchronously
var Wrapper = API.toolbox.createWrapper(function(e) {
  var Message = e.getPlayer().getName() + " has left the server!";
  BukkitServer.broadcastMessage(Message);
}, false);
// Register the listener with the name "quit-test"
API.getHandlers().registerEvent(org.bukkit.event.player.PlayerQuitEvent.class, "quit-test", Wrapper);
// Unregister the listener
API.getHandlers().unregister(org.bukkit.event.player.PlayerQuitEvent.class, "quit-test");
```

###### To create/remove a listener to handle AsyncPlayerChatEvent
```javascript
var API = BukkitServer.getPluginManager().getPlugin("SealWatch");
// Event is handled asynchronously
var Wrapper = API.toolbox.createWrapper(function(e) {
  print(e.getPlayer().getName() + " said " + e.getMessage());
}, true);
// Register the listener with the name "talk-log"
API.getHandlers().registerEvent(org.bukkit.event.player.AsyncPlayerChatEvent.class, "talk-log", Wrapper);
// Unregister the listener
API.getHandlers().unregister(org.bukkit.event.player.AsyncPlayerChatEvent.class, "talk-log");
```
