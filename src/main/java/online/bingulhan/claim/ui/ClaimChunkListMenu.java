package online.bingulhan.claim.ui;


import java.util.ArrayList;

import com.cryptomorin.xseries.XMaterial;
import online.bingulhan.claim.Claim;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.business.ClaimListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClaimChunkListMenu extends ClaimGui {
    public void open(HClaim claim, Player player) {
        super.open(claim, player);
        Inventory gui = Bukkit.createInventory(null, 45, ChatColor.RED + "Boş Chunklar");
        int chunkSize = claim.chunks.size();
        int rb = Claim.getInstance().getConfig().getInt("startmoney");
        if (chunkSize > 5)
            rb *= 2;
        if (chunkSize > 10)
            rb *= 4;
        if (chunkSize > 20)
            rb *= 6;
        ItemStack center = new ItemStack(XMaterial.GREEN_WOOL.parseMaterial());
        ItemMeta meta = center.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + claim.mainChunk.getX() + " : " + claim.mainChunk.getZ());
        center.setItemMeta(meta);
        int mx = claim.mainChunk.getX();
        int mz = claim.mainChunk.getZ();
        ClaimListenerManager claimListenerManager = new ClaimListenerManager();
        gui.setItem(22, center);
        int slot = 22;
        int x;
        for (x = mx - 1; x >= mx - 3; x--) {
            slot--;
            Chunk chunk = Claim.getInstance().getServer().getWorld(claim.mainChunk.getWorld().getName()).getChunkAt(x, mz);
            if (claimListenerManager.isChunkInClaim(chunk)) {
                if (claimListenerManager.isChunkInClaim(chunk, claim)) {
                    ItemStack item = new ItemStack(XMaterial.ORANGE_WOOL.parseMaterial());
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName(ChatColor.GREEN + "Sana ait");
                    item.setItemMeta(m);
                    gui.setItem(slot, item);
                } else {
                    ItemStack item = new ItemStack(XMaterial.RED_WOOL.parseMaterial());
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName(ChatColor.GREEN + "Dolu Chunk");
                    item.setItemMeta(m);
                    gui.setItem(slot, item);
                }
            } else {
                ItemStack item = new ItemStack(XMaterial.LIGHT_GRAY_WOOL.parseMaterial());
                ItemMeta m = item.getItemMeta();
                m.setDisplayName(ChatColor.GREEN + "Boş Chunk");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(" ");
                lore.add(ChatColor.WHITE + "X: " + x);
                lore.add(ChatColor.WHITE + "Z: " + mz);
                lore.add(" ");
                lore.add(ChatColor.GREEN + "Fiyat: " + rb);
                m.setLore(lore);
                item.setItemMeta(m);
                gui.setItem(slot, item);
            }
        }
        slot = 22;
        for (x = mx + 1; x <= mx + 3; x++) {
            slot++;
            Chunk chunk = Claim.getInstance().getServer().getWorld(claim.mainChunk.getWorld().getName()).getChunkAt(x, mz);
            if (claimListenerManager.isChunkInClaim(chunk)) {
                if (claimListenerManager.isChunkInClaim(chunk, claim)) {
                    ItemStack item = new ItemStack(XMaterial.ORANGE_WOOL.parseMaterial());
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName(ChatColor.GREEN + "Sana ait");
                    item.setItemMeta(m);
                    gui.setItem(slot, item);
                } else {
                    ItemStack item = new ItemStack(XMaterial.RED_WOOL.parseMaterial());
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName(ChatColor.GREEN + "Dolu Chunk");
                    item.setItemMeta(m);
                    gui.setItem(slot, item);
                }
            } else {
                ItemStack item = new ItemStack(XMaterial.LIGHT_GRAY_WOOL.parseMaterial());
                ItemMeta m = item.getItemMeta();
                m.setDisplayName(ChatColor.GREEN + "Boş Chunk");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(" ");
                lore.add(ChatColor.WHITE + "X: " + x);
                lore.add(ChatColor.WHITE + "Z: " + mz);
                lore.add(" ");
                lore.add(ChatColor.GREEN + "Fiyat: " + rb);
                m.setLore(lore);
                item.setItemMeta(m);
                gui.setItem(slot, item);
            }
        }
        int nMx = (new Integer(mx - 4)).intValue();
        int nMz = (new Integer(mz - 1)).intValue();
        int q;
        for (q = 10; q < 17; q++) {
            nMx++;
            Chunk chunk = Claim.getInstance().getServer().getWorld(claim.mainChunk.getWorld().getName()).getChunkAt(nMx, nMz);
            if (claimListenerManager.isChunkInClaim(chunk)) {
                if (claimListenerManager.isChunkInClaim(chunk, claim)) {
                    ItemStack item = new ItemStack(XMaterial.ORANGE_WOOL.parseMaterial());
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName(ChatColor.GREEN + "Sana ait");
                    item.setItemMeta(m);
                    gui.setItem(q, item);
                } else {
                    ItemStack item = new ItemStack(XMaterial.RED_WOOL.parseMaterial());
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName(ChatColor.GREEN + "Dolu Chunk");
                    item.setItemMeta(m);
                    gui.setItem(q, item);
                }
            } else {
                ItemStack item = new ItemStack(XMaterial.LIGHT_GRAY_WOOL.parseMaterial());
                ItemMeta m = item.getItemMeta();
                m.setDisplayName(ChatColor.GREEN + "Boş Chunk");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(" ");
                lore.add(ChatColor.WHITE + "X: " + nMx);
                lore.add(ChatColor.WHITE + "Z: " + nMz);
                lore.add(" ");
                lore.add(ChatColor.GREEN + "Fiyat: " + rb);
                m.setLore(lore);
                item.setItemMeta(m);
                gui.setItem(q, item);
            }
        }
        nMx = (new Integer(mx - 4)).intValue();
        nMz = (new Integer(mz + 1)).intValue();
        for (q = 28; q < 35; q++) {
            nMx++;
            Chunk chunk = Claim.getInstance().getServer().getWorld(claim.mainChunk.getWorld().getName()).getChunkAt(nMx, nMz);
            if (claimListenerManager.isChunkInClaim(chunk)) {
                if (claimListenerManager.isChunkInClaim(chunk, claim)) {
                    ItemStack item = new ItemStack(XMaterial.ORANGE_WOOL.parseMaterial());
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName(ChatColor.GREEN + "Sana ait");
                    item.setItemMeta(m);
                    gui.setItem(q, item);
                } else {
                    ItemStack item = new ItemStack(XMaterial.RED_WOOL.parseMaterial());
                    ItemMeta m = item.getItemMeta();
                    m.setDisplayName(ChatColor.GREEN + "Dolu Chunk");
                    item.setItemMeta(m);
                    gui.setItem(q, item);
                }
            } else {
                ItemStack item = new ItemStack(XMaterial.LIGHT_GRAY_WOOL.parseMaterial());
                ItemMeta m = item.getItemMeta();
                m.setDisplayName(ChatColor.GREEN + "Boş Chunk");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(" ");
                lore.add(ChatColor.WHITE + "X: " + nMx);
                lore.add(ChatColor.WHITE + "Z: " + nMz);
                lore.add(" ");
                lore.add(ChatColor.GREEN + "Fiyat: " + rb);
                m.setLore(lore);
                item.setItemMeta(m);
                gui.setItem(q, item);
            }
        }
        player.openInventory(gui);
    }
}
