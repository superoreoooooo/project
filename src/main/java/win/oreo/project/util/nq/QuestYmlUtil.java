package win.oreo.project.util.nq;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.Project;
import win.oreo.project.util.NPC.NPC;
import win.oreo.project.util.NPC.NPCUtil;

import java.util.UUID;

public class QuestYmlUtil {
    private Project plugin;

    public QuestYmlUtil() {
        this.plugin = JavaPlugin.getPlugin(Project.class);
    }

    public void load() { //TODO 수정
        for (String idS : plugin.ymlManager.getConfig().getConfigurationSection("npc.").getKeys(false)) {
            UUID id = UUID.fromString(idS);
            String n = plugin.ymlManager.getConfig().getString("npc." + idS + ".name");
            double x = plugin.ymlManager.getConfig().getDouble("npc." + idS + ".locX");
            double y = plugin.ymlManager.getConfig().getDouble("npc." + idS + ".locY");
            double z = plugin.ymlManager.getConfig().getDouble("npc." + idS + ".locZ");
            String v = plugin.ymlManager.getConfig().getString("npc." + idS + ".value");
            String s = plugin.ymlManager.getConfig().getString("npc." + idS + ".signature");

            if (NPC.summon(n, x, y, z, id, v, s)) {
                Bukkit.broadcastMessage(ChatColor.GREEN + "loaded : " + n);
            }
        }

        for (NPC npc : NPCUtil.NPCSet) {
            if (npc.getEntityPlayer().getBukkitEntity().isDead()) {
                npc.getEntityPlayer().getBukkitEntity().spigot().respawn();
            }
        }
    }
}