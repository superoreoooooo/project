package win.oreo.project.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.Project;
import win.oreo.project.util.nq.Quest;
import win.oreo.project.util.nq.QuestStatus;
import win.oreo.project.util.nq.QuestUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QuestListener implements Listener {
    private static List<Player> coolDown = new ArrayList<>();

    public void delay(Player player) {
        coolDown.add(player);
        Bukkit.getScheduler().runTaskLaterAsynchronously(JavaPlugin.getPlugin(Project.class), () -> coolDown.remove(player), 10);
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if (coolDown.contains(player)) return;
        delay(player);

        if (!e.getPlayer().isSneaking()) return;

        if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
            if (e.getRightClicked().hasMetadata("npc")) {
                String npcMetadata = e.getRightClicked().getMetadata("npc").get(0).asString();

                if (QuestUtil.userQuestStatusHashMap.get(player.getUniqueId()) == null) {
                    HashMap<UUID, QuestStatus> map = new HashMap<>();

                    QuestUtil.userQuestStatusHashMap.put(player.getUniqueId(), map);
                    return;
                }

                if (QuestUtil.NPCQuestMap.get(Bukkit.getPlayer(npcMetadata).getUniqueId()) != null) {
                    List<Quest> quests = QuestUtil.NPCQuestMap.get(Bukkit.getPlayer(npcMetadata).getUniqueId());
                    for (Quest quest : quests) {
                        if (QuestUtil.userQuestStatusHashMap.get(player.getUniqueId()).containsKey(quest.getQuestID())) {
                            HashMap<UUID, QuestStatus> questStatusHashMap = QuestUtil.userQuestStatusHashMap.get(player.getUniqueId());
                        } else {

                        }
                    }
                    return;
                }
            }
        }
    }
}
