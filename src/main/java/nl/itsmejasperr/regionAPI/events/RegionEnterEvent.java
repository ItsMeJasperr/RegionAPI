package nl.itsmejasperr.regionAPI.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RegionEnterEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final String regionId;

    private boolean cancelled;

    public RegionEnterEvent(Player player, String regionId) {
        this.player = player;
        this.regionId = regionId;
        this.cancelled = false;
    }

    public Player getPlayer() {
        return player;
    }

    public String getRegionId() {
        return regionId;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
