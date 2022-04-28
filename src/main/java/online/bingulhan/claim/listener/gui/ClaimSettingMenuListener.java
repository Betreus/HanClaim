package online.bingulhan.claim.listener.gui;

import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.business.ClaimListenerManager;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClaimSettingMenuListener implements Listener {
    @EventHandler
    public void event(InventoryClickEvent e) {
        for (HumanEntity w : e.getViewers()) {
            String title = w.getOpenInventory().getTitle();
            if (w.getName().equals(e.getWhoClicked().getName()) && title.equals(ChatColor.RED + "Claim Ayarları")) {
                    e.setCancelled(true);
            Chunk chunk = e.getWhoClicked().getLocation().getChunk();
            HClaim claim = (new ClaimListenerManager()).getClaim(chunk);
            if (e.getCurrentItem() == null)
                return;
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Blok Kırma") || e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Blok Kırma")) {
                claim.settings.isBlockBreak = !claim.settings.isBlockBreak;
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Seçilen ayar güncellendi (Block Kırma)");
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Blok Koyma") || e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Blok Koyma")) {
                claim.settings.isBlockPlace = !claim.settings.isBlockPlace;
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Seçilen ayar güncellendi (Block Koyma)");
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "PvP") || e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "PvP")) {
                claim.settings.isPvP = !claim.settings.isPvP;
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Seçilen ayar güncellendi (PVP)");
            }
            return;
        }
    }
}
}

