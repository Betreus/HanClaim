package online.bingulhan.claim;


import java.io.File;
import online.bingulhan.claim.backup.DataBackupManager;
import online.bingulhan.claim.backup.IBackupManager;
import online.bingulhan.claim.business.ClaimListenerManager;
import online.bingulhan.claim.business.ClaimManager;
import online.bingulhan.claim.cmd.CmdClaim;
import online.bingulhan.claim.cmd.CmdClaimJoin;
import online.bingulhan.claim.cmd.CmdDebug;
import online.bingulhan.claim.economy.BaseEconomyManager;
import online.bingulhan.claim.economy.VaultEconomyManager;
import online.bingulhan.claim.listener.BlockBreakListener;
import online.bingulhan.claim.listener.PlayerInteractListener;
import online.bingulhan.claim.listener.PlayerMoveEventListener;
import online.bingulhan.claim.listener.TestListener;
import online.bingulhan.claim.listener.gui.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Claim extends JavaPlugin {
    private static Claim instance;

    public static File claimsFolder;

    public static IBackupManager backupManager;

    public static BaseEconomyManager economyManager;

    public static ClaimListenerManager claimListenerManager;
    public static ClaimManager claimManager;


    public void onEnable() {
        instance = this;
        registerListener();

        claimListenerManager=new ClaimListenerManager();
        claimManager=new ClaimManager();

        claimsFolder = new File(getInstance().getDataFolder(), "claims");
        if (!claimsFolder.exists()) {
            claimsFolder.mkdir();
        }

        new ClaimDal();


        getCommand("claim").setExecutor((CommandExecutor)new CmdDebug());
        getCommand("claimdavet").setExecutor((CommandExecutor)new CmdClaim());
        getCommand("claimkatil").setExecutor((CommandExecutor)new CmdClaimJoin());
        getConfig().set("version", "1.0.0");
        getConfig().set("startmoney", Integer.valueOf(10000));
        getConfig().options().copyDefaults(true);
        saveConfig();

        economyManager = new VaultEconomyManager();

        backupManager = new DataBackupManager();
        backupManager.getBackups();

    }

    public void onDisable() {
        for (HClaim x : ClaimDal.claims)
            backupManager.backup(x);
    }

    public void registerListener() {
        getServer().getPluginManager().registerEvents((Listener)new BlockBreakListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new PlayerInteractListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new ChunkListListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new ClaimMainMenuListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new ClaimSettingMenuListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new ClaimMembersMenuListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new ClaimBuyMenuListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new PlayerMoveEventListener(), this);
      //  getServer().getPluginManager().registerEvents(new TestListener(), this);
    }

    public static Claim getInstance() {
        return instance;
    }
}

