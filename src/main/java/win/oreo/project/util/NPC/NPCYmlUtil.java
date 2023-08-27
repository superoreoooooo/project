package win.oreo.project.util.NPC;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.Project;

import java.util.UUID;

public class NPCYmlUtil {
    private Project plugin;

    public NPCYmlUtil() {
        this.plugin = JavaPlugin.getPlugin(Project.class);
    }

    public void clearData() {
        plugin.ymlManager.getConfig().set("npc", null);
    }

    public void save() {
        clearData();
        for (NPC npc : NPCUtil.NPCSet) {
            Bukkit.broadcastMessage(npc.getName());
            plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".name", npc.getName());
            plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".locX", npc.getEntityPlayer().getBukkitEntity().getLocation().getX());
            plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".locY", npc.getEntityPlayer().getBukkitEntity().getLocation().getY());
            plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".locZ", npc.getEntityPlayer().getBukkitEntity().getLocation().getZ());
            plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".value", npc.getValue());
            plugin.ymlManager.getConfig().set("npc." + npc.getUUID() + ".signature", npc.getSignature());
        }
        plugin.ymlManager.saveConfig();
    }

    public void load() {
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
