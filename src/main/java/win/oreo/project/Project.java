package win.oreo.project;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.command.NPCCommand;
import win.oreo.project.command.QuestCommand;
import win.oreo.project.listener.PlayerMovementListener;
import win.oreo.project.manager.YmlManager;
import win.oreo.project.util.npc.NPC;
import win.oreo.project.util.npc.NPCUtil;
import win.oreo.project.util.npc.NPCYmlUtil;
import win.oreo.project.util.nq.Quest;

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
        getCommand("quest").setExecutor(new QuestCommand());
        checkForClasses();
        this.ymlManager = new YmlManager(this);
        this.npcYmlUtil = new NPCYmlUtil();

        Bukkit.getPluginManager().registerEvents(new PlayerMovementListener(), this);

        npcYmlUtil.load();

        NPCUtil.run();
    }

    @Override
    public void onDisable() {
        npcYmlUtil.save();
        Set<NPC> cp = new HashSet<>(NPCUtil.NPCSet);
        for (NPC npc : cp) {
            npc.removePlayer();
        }
    }
}
