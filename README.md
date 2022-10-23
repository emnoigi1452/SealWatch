## SealWatch - An API to control custom event listeners

***What is this?*** This is a plugin I decided to code while I was bored the other day. It helps to register custom event listeners without having to use Java code.
For instance, you could use <a href="https://github.com/PlaceholderAPI/Javascript-Expansion">Javascript-Expansion</a> by clip to create custom event listeners

## ***Code samples***
- *To create a wrapper* - Toolbox.createWrapper(function(event), isAsync)
  - function(event): A function to handle the event that takes in 1 argument - The event itself\n
  - isAsync: A boolean value to decide if the event should be handled asynchronously
- *How to register an event* - HandlerManager.register(eventClass, String listenerName, wrapper);
  - eventClass: The event class (Ex: org.bukkit.event.player.PlayerMoveEvent.class)
  - listenerName: A custom name for this listener
  - wrapper: A wrapper to handle the event
###### A listener to handle PlayerMoveEvent
```javascript
var API = BukkitServer.getPluginManager().getPlugin("SealWatch");
// Event is handled asynchronously
var Wrapper = API.toolbox.createWrapper(function(e) {
  e.getPlayer().sendMessage("You moved!");
}, true);
API.getHandlers().register(org.bukkit.event.player.PlayerMoveEvent.class, "move-test", Wrapper);
```

###### A listener to handle PlayerQuitEvent
```javascript
var API = BukkitServer.getPluginManager().getPlugin("SealWatch");
// Event is not handled asynchronously
var Wrapper = API.toolbox.createWrapper(function(e) {
  var Message = e.getPlayer().getName() + " has left the server!";
  BukkitServer.broadcastMessage(Message);
}, false);
API.getHandlers().register(org.bukkit.event.player.PlayerQuitEvent.class, "quit-test", Wrapper);
```

###### A listener to handle AsyncPlayerChatEvent
```javascript
var API = BukkitServer.getPluginManager().getPlugin("SealWatch");
// Event is handled asynchronously
var Wrapper = API.toolbox.createWrapper(function(e) {
  print(e.getPlayer().getName() + " said " + e.getMessage());
}, true);
API.getHandlers().register(org.bukkit.event.player.AsyncPlayerChatEvent.class, "talk-log", Wrapper);
```
