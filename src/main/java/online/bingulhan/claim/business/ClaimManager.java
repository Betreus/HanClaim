package online.bingulhan.claim.business;

import java.io.File;
import java.util.ArrayList;
import online.bingulhan.claim.Claim;
import online.bingulhan.claim.ClaimDal;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.events.ClaimCreateEvent;
import online.bingulhan.claim.events.ClaimDeleteEvent;
import online.bingulhan.claim.events.ClaimLeaveEvent;
import online.bingulhan.claim.settings.ClaimSettings;
import online.bingulhan.claim.ui.ClaimBuyMenu;
import online.bingulhan.claim.ui.ClaimChunkListMenu;
import online.bingulhan.claim.ui.ClaimGui;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ClaimManager {
    public void createChunkBuy(Player player) {
        int rb = Claim.getInstance().getConfig().getInt("startmoney");

        if (!isClaimOk(player.getLocation().getChunk()) && Claim.economyManager.isMoneyRequest(rb, player)) {
            Claim.economyManager.takeMoney(player, rb);
            ArrayList<String> members = new ArrayList<>();
            members.add(player.getName());
            ArrayList<Chunk> chunks = new ArrayList<>();
            chunks.add(player.getLocation().getChunk());
            player.sendTitle("", ChatColor.GREEN + "Claim Oluştu");
                    player.getLocation().getBlock().setType(Material.BEDROCK);
            ClaimSettings settings = new ClaimSettings();
            HClaim claim = new HClaim((OfflinePlayer)player, members, chunks, player.getLocation().getChunk(), settings, player.getLocation().getBlock());
            addChunkToList(claim);
            player.teleport(new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY() + 2.0D, player.getLocation().getZ()));

            ClaimCreateEvent event = new ClaimCreateEvent(claim.owner, claim);
            Bukkit.getPluginManager().callEvent(event);

        } else {
            player.sendMessage(ChatColor.RED + "Claim satın alınamadı.");
        }
    }

    public void createChunk(Player player) {
        player.closeInventory();
        Claim.getInstance().getServer().getScheduler().runTaskLater((Plugin)Claim.getInstance(), () -> {
            ClaimPlayerManager.showGui(player, new ClaimBuyMenu());
        }, 1);
    }

    public void addChunkToList(HClaim claim) {
        ClaimDal.claims.add(claim);
    }

    public boolean isClaimOk(Chunk chunk) {
        return (new ClaimListenerManager()).isChunkInClaim(chunk);
    }

    public void addChunkToClaim(HClaim claim, Chunk chunk, Player player) {
        int chunkSize = claim.chunks.size();
        int rb = Claim.getInstance().getConfig().getInt("startmoney");
        if (chunkSize > 5)
            rb = rb* 2;
        if (chunkSize > 10)
            rb = rb* 4;
        if (chunkSize > 20)
            rb = rb* 6;
        if (Claim.economyManager.isMoneyRequest(rb, player)) {
            claim.chunks.add(chunk);
            player.sendMessage(ChatColor.GREEN + "Claim Genişletildi!");
            Claim.economyManager.takeMoney(player, rb);
        } else {
            player.sendMessage(ChatColor.RED + "Yeterli paran yok!");
        }
    }

    public void removeClaim(HClaim claim) {
        claim.settingsBlock.setType(Material.AIR);

        ClaimDeleteEvent event = new ClaimDeleteEvent(claim.owner, claim.mainChunk);
        Bukkit.getPluginManager().callEvent(event);


        ClaimDal.claims.remove(claim);
    }
}