package online.bingulhan.claim.events;

import online.bingulhan.claim.HClaim;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClaimCreateEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final OfflinePlayer player;
    private final HClaim claim;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public ClaimCreateEvent(OfflinePlayer owner, HClaim claim) {
        this.player=owner;
        this.claim=claim;
    }
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }


    public HClaim getClaim() {
        return this.claim;
    }

    public OfflinePlayer getClaimOwner() {
        return player;
    }

}
