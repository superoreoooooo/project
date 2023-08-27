package win.oreo.project;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.command.NPCCommand;
import win.oreo.project.manager.YmlManager;
import win.oreo.project.util.NPC;
import win.oreo.project.util.NPCUtil;
import win.oreo.project.util.NPCYmlUtil;

import java.util.HashSet;
import java.util.Set;

public final class Project extends JavaPlugin {

    public YmlManager ymlManager;
    public NPCYmlUtil npcYmlUtil;

    private boolean usesPaper = false;

    private boolean updatedPaper = false;

    public boolean usesPaper() {
        return usesPaper;
    }

    public boolean isPaperUpdated() {
        return updatedPaper;
    }

    public void checkForClasses() {
        try {
            usesPaper = Class.forName("com.destroystokyo.paper.VersionHistoryManager$VersionData") != null;
            if (usesPaper) {
                Bukkit.getLogger().info("Paper detected.");
            }
        } catch (ClassNotFoundException ignored) {

        }
        try {
            updatedPaper = Class.forName("net.kyori.adventure.text.ComponentLike") != null;
        } catch (ClassNotFoundException ignored) {

        }
    }

    @Override
    public void onEnable() {
        getCommand("npc").setExecutor(new NPCCommand());
        checkForClasses();
        this.ymlManager = new YmlManager(this);
        this.npcYmlUtil = new NPCYmlUtil();

        npcYmlUtil.load();
    }

    @Override
    public void onDisable() {
        for (NPC npc : NPCUtil.NPCSet) {
            npc.removePlayer();
        }
        npcYmlUtil.save();
    }
}
