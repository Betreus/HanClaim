package online.bingulhan.claim.business;


import online.bingulhan.claim.ClaimDal;
import online.bingulhan.claim.HClaim;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class ClaimListenerManager {
    public boolean isChunkInClaim(Chunk chunk) {
        for (HClaim claim : ClaimDal.claims) {
            for (Chunk x : claim.chunks) {
                if (chunk.getX() == x.getX() && chunk.getZ() == x.getZ())
                    return true;
            }
        }
        return false;
    }

    public HClaim getClaim(Chunk chunk) {
        for (HClaim claim : ClaimDal.claims) {
            for (Chunk x : claim.chunks) {
                if (chunk.getX() == x.getX() && chunk.getZ() == x.getZ())
                    return claim;
            }
        }
        return null;
    }

    public boolean isChunkOwner(Player player, HClaim claim) {
        return claim.owner.getName().equals(player.getName());
    }

    public boolean isChunkMember(Player player, HClaim claim) {
        for (String member : claim.members) {
            if (player.getName().equals(member))
                return true;
        }
        return false;
    }

    public boolean isChunkInClaim(Chunk chunk, HClaim claim) {
        for (Chunk x : claim.chunks) {
            if (x.getX() == chunk.getX() && x.getZ() == chunk.getZ())
                return true;
        }
        return false;
    }
}