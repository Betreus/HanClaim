package online.bingulhan.claim.listener.gui;


import com.cryptomorin.xseries.XMaterial;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.business.ClaimListenerManager;
import online.bingulhan.claim.business.ClaimManager;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChunkListListener implements Listener {
    @EventHandler
    public void event(InventoryClickEvent e) {
        for (HumanEntity w : e.getViewers()) {
            String title = w.getOpenInventory().getTitle();
            if (w.getName().equals(e.getWhoClicked().getName()) && title.equals(ChatColor.RED + "Boş Chunklar")) {
                e.setCancelled(true);
                if (e.getCurrentItem() == null)
                    return;
                if (e.getCurrentItem().getType().equals(XMaterial.LIGHT_GRAY_WOOL.parseMaterial())) {
                    int x = Integer.parseInt(((String)e.getCurrentItem().getItemMeta().getLore().get(1)).substring(5));
                    int z = Integer.parseInt(((String)e.getCurrentItem().getItemMeta().getLore().get(2)).substring(5));
                    Chunk chunk = e.getWhoClicked().getWorld().getChunkAt(x, z);
                    HClaim claim = new ClaimListenerManager().getClaim(e.getWhoClicked().getLocation().getChunk());
                    new ClaimManager().addChunkToClaim(claim, chunk, (Player)e.getWhoClicked());
                    e.getWhoClicked().closeInventory();
                }
                return;
            }
        }
    }
}

