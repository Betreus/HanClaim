package online.bingulhan.claim.listener;

import online.bingulhan.claim.business.ClaimListenerManager;
import online.bingulhan.claim.business.ClaimPlayerManager;
import online.bingulhan.claim.ui.ClaimGui;
import online.bingulhan.claim.ui.ClaimMainMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void event(PlayerInteractEvent e) {
        Location location = e.getClickedBlock().getLocation();
        try {
            if (!e.getClickedBlock().getType().equals(Material.BEDROCK))
                return;
            if ((new ClaimListenerManager()).isChunkInClaim(location.getChunk())) {
                int x = ((new ClaimListenerManager()).getClaim(location.getChunk())).settingsBlock.getLocation().getBlockX();
                int y = ((new ClaimListenerManager()).getClaim(location.getChunk())).settingsBlock.getLocation().getBlockY();
                int z = ((new ClaimListenerManager()).getClaim(location.getChunk())).settingsBlock.getLocation().getBlockZ();
                if (location.getBlockX() == x && location.getBlockY() == y && location.getBlockZ() == z)
                    if (e.getPlayer().getName().equals(((new ClaimListenerManager()).getClaim(location.getChunk())).owner.getName()))
                        ClaimPlayerManager.showGui(e.getPlayer(), (ClaimGui)new ClaimMainMenu());
            }
        } catch (NullPointerException nullPointerException) {}
    }
}
