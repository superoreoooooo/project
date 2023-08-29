package win.oreo.project.util.npc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.Project;

import java.util.HashSet;
import java.util.Set;

public class NPCUtil {
    public static Set<NPC> NPCSet = new HashSet<>();
    public static NPC getNPC(String name) {
        for (NPC npc : NPCSet) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }

    public static void run() {
        Bukkit.getScheduler().runTaskTimer(JavaPlugin.getPlugin(Project.class), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.spigot().setCollidesWithEntities(false);
            }
        }, 0, 20);
    }
}
