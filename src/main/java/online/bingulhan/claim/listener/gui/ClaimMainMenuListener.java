package online.bingulhan.claim.listener.gui;


import com.cryptomorin.xseries.XMaterial;
import online.bingulhan.claim.Claim;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.business.ClaimListenerManager;
import online.bingulhan.claim.business.ClaimManager;
import online.bingulhan.claim.business.ClaimPlayerManager;
import online.bingulhan.claim.ui.ClaimChunkListMenu;
import online.bingulhan.claim.ui.ClaimGui;
import online.bingulhan.claim.ui.ClaimGuiListener;
import online.bingulhan.claim.ui.ClaimMembersMenu;
import online.bingulhan.claim.ui.ClaimSettingsMenu;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class ClaimMainMenuListener extends ClaimGuiListener implements Listener {
    @EventHandler
    public void event(InventoryClickEvent e) {
        for (HumanEntity w : e.getViewers()) {
            String title = w.getOpenInventory().getTitle();
            if (w.getName().equals(e.getWhoClicked().getName()) && title.equals(ChatColor.RED + "Claim Menü")) {
            if (e.getCurrentItem() == null)
                return;
            if (e.getCurrentItem().getType().equals(XMaterial.NETHER_STAR.parseMaterial())) {
                e.getWhoClicked().closeInventory();
                Claim.getInstance().getServer().getScheduler().runTaskLater((Plugin)Claim.getInstance(), () -> {
                    new ClaimPlayerManager();
                    ClaimPlayerManager.showGui((Player)e.getWhoClicked(), (ClaimGui)new ClaimChunkListMenu());
                }, 1);
            }
            if (e.getCurrentItem().getType().equals(XMaterial.REDSTONE_LAMP.parseMaterial())) {
                e.getWhoClicked().closeInventory();
                Claim.getInstance().getServer().getScheduler().runTaskLater((Plugin)Claim.getInstance(), () -> {
                    new ClaimPlayerManager();
                    ClaimPlayerManager.showGui((Player)e.getWhoClicked(), (ClaimGui)new ClaimSettingsMenu());
                }, 1);
            }
            if (e.getCurrentItem().getType().equals(XMaterial.JUNGLE_SIGN.parseMaterial())) {
                e.getWhoClicked().closeInventory();
                Claim.getInstance().getServer().getScheduler().runTaskLater((Plugin)Claim.getInstance(), () -> {
                    new ClaimPlayerManager();
                    ClaimPlayerManager.showGui((Player)e.getWhoClicked(), (ClaimGui)new ClaimMembersMenu());
                }, 1);
            }
            if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                e.getWhoClicked().closeInventory();
                Chunk chunk = e.getWhoClicked().getLocation().getChunk();
                HClaim claim = (new ClaimListenerManager()).getClaim(chunk);
                (new ClaimManager()).removeClaim(claim);
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Claim silindi!");
            }
            e.setCancelled(true);
            return;
        }
    }
}
}
