package online.bingulhan.claim.settings;

public class ClaimSettings {
    public boolean isBlockBreak = false;

    public boolean isBlockPlace = false;

    public boolean isPvP = false;

    public ClaimSettings(boolean isBlockBreak, boolean isBlockPlace, boolean isPvP) {
        this.isBlockBreak = isBlockBreak;
        this.isBlockPlace = isBlockPlace;
        this.isPvP = isPvP;
    }

    public ClaimSettings() {}
}
