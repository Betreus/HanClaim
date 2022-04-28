package online.bingulhan.claim.ui;

import online.bingulhan.claim.HClaim;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClaimInviteMenu extends ClaimGui {
    public void open(HClaim claim, Player player) {
        super.open(claim, player);
        Inventory inventory = Bukkit.createInventory((InventoryHolder)player, InventoryType.ANVIL, ChatColor.RED + "Davet et");
        ItemStack text = new ItemStack(Material.PAPER);
        ItemMeta meta = text.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Oyuncu adı");
                text.setItemMeta(meta);
        inventory.addItem(new ItemStack[] { text });
        player.openInventory(inventory);
    }
}

