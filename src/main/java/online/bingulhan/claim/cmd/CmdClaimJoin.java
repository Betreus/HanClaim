package online.bingulhan.claim.cmd;

import online.bingulhan.claim.ClaimDal;
import online.bingulhan.claim.business.ClaimPlayerManager;
import online.bingulhan.claim.settings.ClaimInvite;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CmdClaimJoin implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)
            if (strings.length > 0) {
                String inviter = strings[0];
                Player player = (Player)commandSender;
                for (ClaimInvite invite : ClaimDal.invites) {
                    if (invite.claim.owner.getName().equals(inviter)) {
                        if (invite.player.getName().equals(player.getName())) {
                            (new ClaimPlayerManager()).acceptInv((OfflinePlayer)player, invite.claim.owner, invite.claim);
                            return true;
                        }
                        continue;
                    }
                    player.sendMessage(ChatColor.RED + "Bu kişiden davet almamışsın");
                    return true;
                }
                player.sendMessage(ChatColor.RED + "Herhangi bir davet almamışsın");
            }
        return true;
    }
}

