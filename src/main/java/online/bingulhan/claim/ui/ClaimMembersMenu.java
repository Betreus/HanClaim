package online.bingulhan.claim.ui;


import java.util.ArrayList;

import com.cryptomorin.xseries.XMaterial;
import online.bingulhan.claim.HClaim;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ClaimMembersMenu extends ClaimGui {
    public void open(HClaim claim, Player player) {
        super.open(claim, player);
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.RED + "Üyeler");
        for (String member : claim.members) {
            ItemStack membership = new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short)3);
            SkullMeta sm = (SkullMeta)membership.getItemMeta();
            sm.setDisplayName(ChatColor.GREEN + member);
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(" ");
            if (member.equals(claim.owner.getName()))
                arrayList.add(ChatColor.RED + "Claim sahibi");
            if (player.getName().equals(claim.owner.getName()) &&
                    !member.equals(player.getName()))
                arrayList.add(ChatColor.RED + "Claimden at");
            sm.setLore(arrayList);
            sm.setOwner(member);
            membership.setItemMeta((ItemMeta)sm);
            gui.addItem(new ItemStack[] { membership });
        }
        ItemStack info = new ItemStack(XMaterial.BOOK.parseMaterial());
        ItemMeta meta = info.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Önemli");
                ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.GREEN + "/claimdavet <oyuncuad" + ChatColor.WHITE + "Claime oyuncu davet edebilirsin!");
        lore.add(" ");
        meta.setLore(lore);
        info.setItemMeta(meta);
        gui.setItem(26, info);
        player.openInventory(gui);
    }
}
