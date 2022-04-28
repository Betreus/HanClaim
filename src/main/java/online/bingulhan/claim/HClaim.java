package online.bingulhan.claim;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import online.bingulhan.claim.settings.ClaimSettings;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

public class HClaim {
    public OfflinePlayer owner;

    public ArrayList<String> members;

    public ArrayList<Chunk> chunks;

    public Chunk mainChunk;

    public ClaimSettings settings;

    public Block settingsBlock;



    public HClaim(@NotNull OfflinePlayer owner, @NotNull ArrayList<String> members, @NotNull ArrayList<Chunk> chunks, @NotNull Chunk mainChunk, @NotNull ClaimSettings settings, Block settingsBlock) {
        this.owner = owner;
        this.chunks = chunks;
        this.mainChunk = mainChunk;
        this.settings = settings;
        this.settingsBlock = settingsBlock;
        this.members = members;
    }

}

