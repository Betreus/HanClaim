package online.bingulhan.claim.backup;

import java.util.ArrayList;
import online.bingulhan.claim.HClaim;

public interface IBackupManager {
    void backup(HClaim paramHClaim);

    ArrayList<HClaim> getBackups();
}
