package online.bingulhan.claim.events;

import online.bingulhan.claim.HClaim;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClaimDeleteEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final OfflinePlayer player;
    private final Chunk chunk;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public ClaimDeleteEvent(OfflinePlayer owner, Chunk chunk) {
        this.player=owner;
        this.chunk=chunk;
    }
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }


    public Chunk getMainChunk() {
        return this.chunk;
    }

    public OfflinePlayer getClaimOwner() {
        return player;
    }

}
