package online.bingulhan.claim;

import java.util.ArrayList;
import online.bingulhan.claim.settings.ClaimInvite;

public class ClaimDal {
    public static ArrayList<HClaim> claims = new ArrayList<>();

    public static ArrayList<ClaimInvite> invites = new ArrayList<>();

    public ClaimDal() {
        claims=new ArrayList<>();
        invites=new ArrayList<>();

    }
}
