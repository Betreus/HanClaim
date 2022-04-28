package online.bingulhan.claim.listener;


import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.business.ClaimListenerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void event(BlockBreakEvent e) {
        if ((new ClaimListenerManager()).isChunkInClaim(e.getBlock().getChunk())) {
            HClaim claim = (new ClaimListenerManager()).getClaim(e.getBlock().getChunk());
            if (e.getBlock().getX() == claim.settingsBlock.getX() && e.getBlock().getY() == claim.settingsBlock.getY() && e.getBlock().getZ() == claim.settingsBlock.getZ())
                e.setCancelled(true);
            for (String member : claim.members) {
                if (e.getPlayer().getName().equals(member)) {
                    if (!claim.settings.isBlockBreak) {
                        if (e.getPlayer().getName().equals(claim.owner.getName()))
                            return;
                        e.setCancelled(true);
                        return;
                    }
                    return;
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void event2(BlockPlaceEvent e) {
        if ((new ClaimListenerManager()).isChunkInClaim(e.getBlock().getChunk())) {
            HClaim claim = (new ClaimListenerManager()).getClaim(e.getBlock().getChunk());
            for (String member : claim.members) {
                if (e.getPlayer().getName().equals(member)) {
                    if (!claim.settings.isBlockPlace) {
                        if (e.getPlayer().getName().equals(claim.owner.getName()))
                            return;
                        e.setCancelled(true);
                        return;
                    }
                    return;
                }
            }
            e.setCancelled(true);
        }
    }
}
