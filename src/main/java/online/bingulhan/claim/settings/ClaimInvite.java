package online.bingulhan.claim.settings;
import online.bingulhan.claim.HClaim;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class ClaimInvite {
    public HClaim claim;

    public OfflinePlayer player;

    public ClaimInvite(@NotNull HClaim claim, @NotNull OfflinePlayer player) {
        this.claim = claim;
        this.player = player;
    }
}
