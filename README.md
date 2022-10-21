## SealWatch - An API to control custom event listeners

***What is this?*** This is a plugin I decided to code while I was bored the other day. It helps to register custom event listeners without having to use Java code.
For instance, you could use <a href="https://github.com/PlaceholderAPI/Javascript-Expansion">Javascript-Expansion</a> by clip to create custom event listeners

## ***Code samples***
- *How to register an event*
###### A listener to handle PlayerMoveEvent
```javascript
var API = BukkitServer.getPluginManager().getPlugin("SealWatch");
var Wrapper = API.toolbox.createWrapper(function(e) {
  e.getPlayer().sendMessage("You moved!");
}, false);
API.getHandlers().register(org.bukkit.event.player.PlayerMoveEvent.class, Wrapper);
```
