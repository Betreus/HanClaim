package online.bingulhan.claim.backup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import online.bingulhan.claim.Claim;
import online.bingulhan.claim.ClaimDal;
import online.bingulhan.claim.HClaim;
import online.bingulhan.claim.settings.ClaimSettings;
import online.bingulhan.claim.util.FileUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataBackupManager implements IBackupManager {
    public void backup(HClaim claim) {

        File filex = new File(Claim.claimsFolder.getPath() + "/claim" + System.currentTimeMillis() + ".yml");

        if (!filex.exists())
            try {
                filex.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }


        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(filex);
        yamlConfiguration.set("owner", claim.owner.getName());
        yamlConfiguration.set("settingBlock.x", Double.valueOf(claim.settingsBlock.getLocation().getX()));
        yamlConfiguration.set("settingBlock.y", Double.valueOf(claim.settingsBlock.getLocation().getY()));
        yamlConfiguration.set("settingBlock.z", Double.valueOf(claim.settingsBlock.getLocation().getZ()));
        yamlConfiguration.set("main.x", Integer.valueOf(claim.mainChunk.getX()));
        yamlConfiguration.set("main.z", Integer.valueOf(claim.mainChunk.getZ()));
        yamlConfiguration.set("main.world", claim.mainChunk.getWorld().getName());
        yamlConfiguration.set("setting.break", Boolean.valueOf(claim.settings.isBlockBreak));
        yamlConfiguration.set("setting.place", Boolean.valueOf(claim.settings.isBlockPlace));
        yamlConfiguration.set("setting.pvp", Boolean.valueOf(claim.settings.isPvP));
        for (Chunk x : claim.chunks) {
            int idInt = claim.chunks.indexOf(x) + 1;
            String id = "Id" + idInt;
            yamlConfiguration.set("chunks." + id + ".x", Integer.valueOf(x.getX()));
            yamlConfiguration.set("chunks." + id + ".z", Integer.valueOf(x.getZ()));
        }
        yamlConfiguration.set("members", claim.members);
        try {
            yamlConfiguration.save(filex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<HClaim> getBackups() {
        ArrayList<HClaim> claims = new ArrayList<>();



        for (File x : Claim.claimsFolder.listFiles()) {

                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(x);
                int mainx = yamlConfiguration.getInt("main.x");
                int mainz = yamlConfiguration.getInt("main.z");
                String worldName= yamlConfiguration.getString("main.world");

                Claim.getInstance().getServer().getScheduler().runTaskLater(Claim.getInstance(), () -> {
                    World world = Claim.getInstance().getServer().getWorld(worldName);
                    Chunk main = world.getChunkAt(mainx, mainz);

                    Block settingBlock = world.getBlockAt(yamlConfiguration.getInt("settingBlock.x"), yamlConfiguration.getInt("settingBlock.y"), yamlConfiguration.getInt("settingBlock.z"));
                    OfflinePlayer player = Claim.getInstance().getServer().getOfflinePlayer(yamlConfiguration.getString("owner"));
                    ArrayList<Chunk> chunks = new ArrayList<>();
                    for (String id : yamlConfiguration.getConfigurationSection("chunks").getKeys(false)) {
                        int xLoc = yamlConfiguration.getInt("chunks." + id + ".x");
                        int zLoc = yamlConfiguration.getInt("chunks." + id + ".z");
                        Chunk chunk = Claim.getInstance().getServer().getWorld(yamlConfiguration.getString("main.world")).getChunkAt(xLoc, zLoc);
                        chunks.add(chunk);
                    }
                    boolean isPvp = yamlConfiguration.getBoolean("setting.pvp");
                    boolean isPlace = yamlConfiguration.getBoolean("setting.place");
                    boolean isBreak = yamlConfiguration.getBoolean("setting.break");

                    ArrayList<String> members = new ArrayList<>();
                    members = (ArrayList<String>)yamlConfiguration.getList("members");


                    ClaimSettings settings = new ClaimSettings(isBreak, isPlace, isPvp);

                    HClaim claim = new HClaim(player, members, chunks, main, settings, settingBlock);
                    claims.add(claim);


                    FileUtil.delete(x);

                    Claim.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GREEN+""+" blok 2: "+claim.settingsBlock.getLocation().getBlockX()+" "+claim.settingsBlock.getLocation().getBlockY()+claim.settingsBlock.getLocation().getBlockZ());


                }, 40);


         /*       Chunk main = Claim.getInstance().getServer().getWorld(worldName).getChunkAt(mainx, mainz);
                 Block settingBlock = Claim.getInstance().getServer().getWorld(yamlConfiguration.getString("main.world")).getBlockAt(new Location(

                        Claim.getInstance().getServer().getWorld(yamlConfiguration.getString("main.world")), yamlConfiguration
                        .getInt("settingBlock.x"), yamlConfiguration
                        .getInt("settingBlock.y"), yamlConfiguration
                        .getInt("settingBlock.z")));
                OfflinePlayer player = Claim.getInstance().getServer().getOfflinePlayer(yamlConfiguration.getString("owner"));
                ArrayList<Chunk> chunks = new ArrayList<>();
                for (String id : yamlConfiguration.getConfigurationSection("chunks").getKeys(false)) {
                    int xLoc = yamlConfiguration.getInt("chunks." + id + ".x");
                    int zLoc = yamlConfiguration.getInt("chunks." + id + ".z");
                    Chunk chunk = Claim.getInstance().getServer().getWorld(yamlConfiguration.getString("main.world")).getChunkAt(xLoc, zLoc);
                    chunks.add(chunk);
                }
                boolean isPvp = yamlConfiguration.getBoolean("setting.pvp");
                boolean isPlace = yamlConfiguration.getBoolean("setting.place");
                boolean isBreak = yamlConfiguration.getBoolean("setting.break");

               ArrayList<String> members = new ArrayList<>();
                members = (ArrayList<String>)yamlConfiguration.getList("members");


                ClaimSettings settings = new ClaimSettings(isBreak, isPlace, isPvp);

               HClaim claim = new HClaim(player, members, chunks, main, settings, settingBlock);

         *///       claims.add(claim);

        }

        Claim.getInstance().getServer().getScheduler().runTaskLater(Claim.getInstance(), () -> {

            for (HClaim claim : claims) {
                ClaimDal.claims.add(claim);
                Claim.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"Claim eklendi!");
            }

        }, 60);


        return claims;
    }
}