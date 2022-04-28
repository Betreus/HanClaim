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

public class ClaimMainMenu extends ClaimGui {
    public void open(HClaim claim, Player player) {
        super.open(claim, player);
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.RED + "Claim Menü");
                ItemStack chunks = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = chunks.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Alanı yükselt");
                chunks.setItemMeta(meta);
        ItemStack settings = new ItemStack(XMaterial.REDSTONE_LAMP.parseMaterial());
        ItemMeta metaS = settings.getItemMeta();
        metaS.setDisplayName(ChatColor.GREEN + "Ayarlar");
        settings.setItemMeta(metaS);
        ItemStack members = new ItemStack(XMaterial.JUNGLE_SIGN.parseMaterial());
        ItemMeta metam = members.getItemMeta();
        metam.setDisplayName(ChatColor.GREEN + "Üyeler");
                members.setItemMeta(metam);
        ItemStack remove = new ItemStack(Material.BARRIER);
        ItemMeta metar = remove.getItemMeta();
        metar.setDisplayName(ChatColor.GREEN + "Claimi sil");
        remove.setItemMeta(metar);
        gui.setItem(12, chunks);
        gui.setItem(13, settings);
        gui.setItem(14, members);
        gui.setItem(15, remove);
        player.openInventory(gui);
    }
}


