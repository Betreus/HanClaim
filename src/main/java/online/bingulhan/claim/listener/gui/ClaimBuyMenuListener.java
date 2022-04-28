package online.bingulhan.claim.listener.gui;

import online.bingulhan.claim.Claim;
import online.bingulhan.claim.business.ClaimManager;
import online.bingulhan.claim.business.ClaimPlayerManager;
import online.bingulhan.claim.ui.ClaimChunkListMenu;
import online.bingulhan.claim.ui.ClaimGui;
import online.bingulhan.claim.ui.ClaimGuiListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class ClaimBuyMenuListener extends ClaimGuiListener implements Listener {

    @EventHandler
    public void event(InventoryClickEvent e) {
        for (HumanEntity w : e.getViewers()) {
            String title = w.getOpenInventory().getTitle();
            if (w.getName().equals(e.getWhoClicked().getName()) && title.equals(ChatColor.RED+"Satın Almak istiyor musun?")) {
                e.setCancelled(true);
                if (e.getCurrentItem() == null)
                    return;
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN+"Kabul et")) {
                    e.getWhoClicked().closeInventory();
                    new ClaimManager().createChunkBuy((Player) e.getWhoClicked());
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED+"Reddet")) {
                    e.getWhoClicked().closeInventory();
                }
            }
        }
    }
}
