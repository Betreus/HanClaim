package online.bingulhan.claim.listener;

import online.bingulhan.claim.Claim;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.events.ClaimEnterEvent;
import online.bingulhan.claim.events.ClaimLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveEventListener implements Listener {

    @EventHandler
    public void move(PlayerMoveEvent e) {


        if (!e.getFrom().getChunk().equals(e.getTo().getChunk())) {


            Chunk newChunk = e.getTo().getChunk();
            Chunk oldChunk = e.getFrom().getChunk();

            if (Claim.getInstance().claimListenerManager.isChunkInClaim(newChunk) && !Claim.getInstance().claimListenerManager.isChunkInClaim(oldChunk) ) {

                ClaimEnterEvent event = new ClaimEnterEvent(e.getPlayer(), Claim.getInstance().claimListenerManager.getClaim(newChunk));

                Bukkit.getPluginManager().callEvent(event);
            }else {
                if (Claim.getInstance().claimListenerManager.isChunkInClaim(oldChunk)) {

                    ClaimLeaveEvent event = new ClaimLeaveEvent(e.getPlayer(), Claim.getInstance().claimListenerManager.getClaim(newChunk));

                    Bukkit.getPluginManager().callEvent(event);
                }
            }

        }
    }
}
