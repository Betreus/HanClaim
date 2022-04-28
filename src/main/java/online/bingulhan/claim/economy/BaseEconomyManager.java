package online.bingulhan.claim.economy;


import org.bukkit.OfflinePlayer;

public class BaseEconomyManager {
    public void giveMoney(OfflinePlayer player, double money) {}

    public void takeMoney(OfflinePlayer player, double money) {}

    public boolean isMoneyRequest(double money, OfflinePlayer player) {
        return true;
    }
}
