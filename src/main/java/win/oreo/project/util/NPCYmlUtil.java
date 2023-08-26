package win.oreo.project.util;

import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.Project;

import java.util.ArrayList;
import java.util.List;

public class NPCYmlUtil {
    public static List<NPC> NPCList = new ArrayList<>();
    private Project plugin;

    public NPCYmlUtil() {
        this.plugin = JavaPlugin.getPlugin(Project.class);
    }

    public void add(NPC npc, double x, double y, double z) {
        NPCList.add(npc);
        plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".name", npc.getName());
        plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".locX", x);
        plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".locY", y);
        plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".locZ", z);
        plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".value", npc.getValue());
        plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".signature", npc.getSignature());
        plugin.ymlManager.saveConfig();
    }

    public void remove(NPC npc) {
        plugin.ymlManager.getConfig().set("npc." + npc.getUUID(), null);
        plugin.ymlManager.saveConfig();
    }
}
