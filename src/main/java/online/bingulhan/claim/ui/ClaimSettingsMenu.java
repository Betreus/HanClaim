package online.bingulhan.claim.ui;


import com.cryptomorin.xseries.XMaterial;
import online.bingulhan.claim.HClaim;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClaimSettingsMenu extends ClaimGui {
    public void open(HClaim claim, Player player) {
        super.open(claim, player);
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.RED + "Claim Ayarları");
                ItemStack pvp = null;
        ItemStack breakBlock = null;
        ItemStack breakPlace = null;
        if (claim.settings.isPvP) {
            pvp = new ItemStack(XMaterial.GREEN_WOOL.parseMaterial());
            ItemMeta meta = pvp.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "PvP");
            pvp.setItemMeta(meta);
        }
        if (!claim.settings.isPvP) {
            pvp = new ItemStack(XMaterial.RED_WOOL.parseMaterial());
            ItemMeta meta = pvp.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "PvP");
            pvp.setItemMeta(meta);
        }
        if (claim.settings.isBlockBreak) {
            breakBlock = new ItemStack(XMaterial.GREEN_WOOL.parseMaterial());
            ItemMeta meta = breakBlock.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Blok Kırma");
                    breakBlock.setItemMeta(meta);
        }
        if (!claim.settings.isBlockBreak) {
            breakBlock = new ItemStack(XMaterial.RED_WOOL.parseMaterial());
            ItemMeta meta = breakBlock.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Blok Kırma");
                    breakBlock.setItemMeta(meta);
        }
        if (claim.settings.isBlockPlace) {
            breakPlace = new ItemStack(XMaterial.GREEN_WOOL.parseMaterial());
            ItemMeta meta = breakPlace.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Blok Koyma");
            breakPlace.setItemMeta(meta);
        }
        if (!claim.settings.isBlockPlace) {
            breakPlace = new ItemStack(XMaterial.RED_WOOL.parseMaterial());
            ItemMeta meta = breakPlace.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Blok Koyma");
            breakPlace.setItemMeta(meta);
        }
        gui.setItem(10, pvp);
        gui.setItem(11, breakBlock);
        gui.setItem(12, breakPlace);
        player.openInventory(gui);
    }
}

