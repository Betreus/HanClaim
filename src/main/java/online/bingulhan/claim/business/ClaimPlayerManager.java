package online.bingulhan.claim.business;

import online.bingulhan.claim.Claim;
import online.bingulhan.claim.ClaimDal;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.settings.ClaimInvite;
import online.bingulhan.claim.ui.ClaimGui;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ClaimPlayerManager {
    public void invitePlayerClaim(OfflinePlayer player, HClaim claim) {
        if ((new ClaimListenerManager()).isChunkMember(player.getPlayer(), claim))
            return;
        ClaimInvite invite = new ClaimInvite(claim, player);
        player.getPlayer().sendMessage(ChatColor.GOLD + "Claim sahibi: " + Claim.getInstance().getServer().getOfflinePlayer(claim.owner.getUniqueId()).getName());
        player.getPlayer().sendMessage(ChatColor.GREEN + "Katiclaime /claimkatil <claimsahibi>");
        ClaimDal.invites.add(invite);
        Claim.getInstance().getServer().getScheduler().runTaskLater((Plugin)Claim.getInstance(), () -> ClaimDal.invites.remove(invite), 300L);
    }

    public void acceptInv(OfflinePlayer req, OfflinePlayer inviter, HClaim claim) {
        claim.members.add(req.getName());
        req.getPlayer().sendTitle("", ChatColor.GREEN + "Claime katıldın!");
    }

    public static void showGui(Player player, ClaimGui gui) {
        gui.open((new ClaimListenerManager()).getClaim(player.getLocation().getChunk()), player);
    }

    public void kickMember(HClaim claim, String playerName) {
        claim.members.remove(playerName);
    }
}
