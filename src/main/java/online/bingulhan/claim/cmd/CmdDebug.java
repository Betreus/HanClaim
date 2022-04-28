package online.bingulhan.claim.cmd;

import online.bingulhan.claim.business.ClaimListenerManager;
import online.bingulhan.claim.business.ClaimManager;
import online.bingulhan.claim.business.ClaimPlayerManager;
import online.bingulhan.claim.ui.ClaimMainMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CmdDebug implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player)

            if (new ClaimListenerManager().isChunkInClaim(((Player) sender).getLocation().getChunk())) {

                if (new ClaimListenerManager().isChunkOwner((Player) sender, new ClaimListenerManager().getClaim(((Player) sender).getLocation().getChunk()))) {

                    ClaimPlayerManager.showGui(((Player) sender).getPlayer(), new ClaimMainMenu());
                    sender.sendMessage(ChatColor.GREEN+"Menü açıldı");

                }

            }else {
                (new ClaimManager()).createChunk((Player)sender);
            }
        return true;
    }
}