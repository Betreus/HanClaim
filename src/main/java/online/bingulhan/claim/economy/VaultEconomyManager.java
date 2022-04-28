package online.bingulhan.claim.economy;


import net.milkbowl.vault.economy.Economy;
import online.bingulhan.claim.Claim;
import online.bingulhan.claim.HClaim;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultEconomyManager extends BaseEconomyManager {
    Economy economy;

    public VaultEconomyManager() {
        setupEconomy();
    }

    private boolean setupEconomy() {
        if (Claim.getInstance().getServer().getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = Claim.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return false;
        this.economy = (Economy)rsp.getProvider();
        Claim.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"Vault desteklendi.");
        return (this.economy != null);
    }

    public boolean isMoneyRequest(double money, OfflinePlayer player) {
        if (this.economy.getBalance(player) >= money)
            return true;
        return false;
    }

    public void giveMoney(OfflinePlayer player, double money) {
        this.economy.depositPlayer(player, money);
    }

    public void takeMoney(OfflinePlayer player, double money) {
        this.economy.withdrawPlayer(player, money);
    }
}

