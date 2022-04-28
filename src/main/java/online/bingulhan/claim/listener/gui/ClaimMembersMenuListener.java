package online.bingulhan.claim.listener.gui;

import com.cryptomorin.xseries.XMaterial;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.business.ClaimListenerManager;
import online.bingulhan.claim.business.ClaimPlayerManager;
import online.bingulhan.claim.ui.ClaimGuiListener;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClaimMembersMenuListener extends ClaimGuiListener implements Listener {
    @EventHandler
    public void event(InventoryClickEvent e) {
        for (HumanEntity w : e.getViewers()) {
            String title = w.getOpenInventory().getTitle();
            if (w.getName().equals(e.getWhoClicked().getName()) && title.equals(ChatColor.RED + "Üyeler")) {
                    e.setCancelled(true);
            Chunk chunk = e.getWhoClicked().getLocation().getChunk();
            HClaim claim = (new ClaimListenerManager()).getClaim(chunk);
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType().equals(XMaterial.BOOK.parseMaterial()))
                    return;
                String member = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
                if (!member.equals(e.getWhoClicked().getName())) {
                    e.getWhoClicked().closeInventory();
                    (new ClaimPlayerManager()).kickMember(claim, member);
                    e.getWhoClicked().sendMessage(ChatColor.RED + "" + member + " Claimden at");
                }
            }
            return;
        }
    }
}
}

