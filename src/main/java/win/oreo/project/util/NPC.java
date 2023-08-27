package win.oreo.project.util;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPC {
    private UUID uuid;
    private String name;
    private EntityPlayer entityPlayer;
    private String value;
    private String signature;

    public NPC(UUID uuid, String name, String value, String signature) {
        this.uuid = uuid;
        this.name = name;
        this.value = value;
        this.signature = signature;
    }

    public NPC() {

    }

    public static boolean summon(String name, double x, double y, double z, UUID uuid, String value, String signature) {
        return new NPC(uuid, name, value, signature).spawn(x, y, z);
    }

    public boolean spawn(double x, double y, double z) {
        if (name == null) {
            return false;
        }
        if (name.length() >= 16) {
            return false;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }

        entityPlayer = v1_12_R1.spawn(this, x, y, z);

        NPCUtil.NPCSet.add(this);

        return true;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public EntityPlayer getEntityPlayer() {
        return entityPlayer;
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }

    public void removePlayer() {
        v1_12_R1.removePlayer(this);
    }
}
