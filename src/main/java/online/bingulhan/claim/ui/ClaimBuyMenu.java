package online.bingulhan.claim.ui;

import online.bingulhan.claim.Claim;
import online.bingulhan.claim.HClaim;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ClaimBuyMenu extends ClaimGui{

    @Override
    public void open(HClaim claim, Player player) {
        super.open(claim, player);

        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.RED+"Satın Almak istiyor musun?");

        ItemStack yes = new ItemStack(Material.PAPER);
        ItemMeta metaYes = yes.getItemMeta();
        metaYes.setDisplayName(ChatColor.GREEN+"Kabul et");
        ArrayList<String> loreYes = new ArrayList<>();
        loreYes.add(" ");
        int rb = Claim.getInstance().getConfig().getInt("startmoney");
        loreYes.add(ChatColor.translateAlternateColorCodes('&', "&fFiyat: &c"+rb));
        loreYes.add(" ");

        metaYes.setLore(loreYes);

        yes.setItemMeta(metaYes);

        gui.setItem(12, yes);

        ItemStack no = new ItemStack(Material.PAPER);
        ItemMeta metaNo = no.getItemMeta();
        metaNo.setDisplayName(ChatColor.RED+"Reddet");
        no.setItemMeta(metaNo);

        gui.setItem(14, no);

        player.openInventory(gui);


    }
}
