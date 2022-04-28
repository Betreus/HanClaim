package online.bingulhan.claim.events;

import online.bingulhan.claim.HClaim;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClaimLeaveEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final HClaim claim;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public ClaimLeaveEvent(Player player, HClaim claim) {
        this.player=player;
        this.claim=claim;

    }
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }

    public Player getPlayer() {
        return this.player;
    }

    public HClaim getClaim() {
        return this.claim;
    }

}
