package online.bingulhan.claim.cmd;


import online.bingulhan.claim.Claim;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.business.ClaimListenerManager;
import online.bingulhan.claim.business.ClaimPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CmdClaim implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            ClaimListenerManager claimListenerManager = new ClaimListenerManager();
            if (claimListenerManager.isChunkInClaim(player.getLocation().getChunk())) {
                Chunk x = player.getLocation().getChunk();
                HClaim claim = claimListenerManager.getClaim(x);
                if (player.getName().equals(claim.owner.getName())) {
                    if (args.length > 0) {
                        OfflinePlayer reciver = Claim.getInstance().getServer().getOfflinePlayer(args[0]);
                        if (reciver.isOnline()) {
                            (new ClaimPlayerManager()).invitePlayerClaim(reciver, claim);
                        } else {
                            player.sendMessage(ChatColor.RED + "Oyuncu aktif değil");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Oyuncu ismi giriniz.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Bu Claimin sahibi değilsin");
                }
            }
        }
        return true;
    }
}

