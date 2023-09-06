package win.oreo.project.util.nq;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.Project;
import win.oreo.project.util.npc.NPC;
import win.oreo.project.util.npc.NPCUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QuestYmlUtil {
    private Project plugin;

    public QuestYmlUtil() {
        this.plugin = JavaPlugin.getPlugin(Project.class);
    }

    public void load() { //TODO 수정
        for (String idS : plugin.ymlManager.getConfig().getConfigurationSection("npc.").getKeys(false)) {
            UUID id = UUID.fromString(idS);

            String s = plugin.ymlManager.getConfig().getString("npc." + idS + ".story");
            List<Quest> questList = new ArrayList<>();

            for (int i = 1; i <= 3; i++) {
                String qn = "Quest" + i;
                String questID = plugin.ymlManager.getConfig().getString("npc." + idS + ".quest." + qn + ".id");
                String title = plugin.ymlManager.getConfig().getString("npc." + idS + ".quest." + "Quest" + qn + ".title");
                String subtitle = plugin.ymlManager.getConfig().getString("npc." + idS + ".quest." + "Quest" + qn + ".subtitle");
                String reward = plugin.ymlManager.getConfig().getString("npc." + idS + ".quest." + "Quest" + qn + ".reward");
                String story = plugin.ymlManager.getConfig().getString("npc." + idS + ".quest." + "Quest" + qn + ".story");
                String task = plugin.ymlManager.getConfig().getString("npc." + idS + ".quest." + "Quest" + qn + ".task");

                Quest quest = new Quest(UUID.fromString(questID), title, subtitle, story, task, reward);

                questList.add(quest);
            }

            QuestUtil.NPCStoryMap.put(id, s);
            QuestUtil.NPCQuestMap.put(id, questList);
        }
        for (String idS : plugin.ymlManager.getConfig().getConfigurationSection("user.").getKeys(false)) {
            UUID id = UUID.fromString(idS);

            HashMap<UUID, QuestStatus> questStatusMap = new HashMap<>();
            for (String idQ : plugin.ymlManager.getConfig().getConfigurationSection("user." + id + ".quests.").getKeys(false)) {
                QuestStatus questStatus = QuestStatus.valueOf(plugin.ymlManager.getConfig().getString("user." + id + ".quests." + idQ + ".status"));

                questStatusMap.put(UUID.fromString(idQ), questStatus);
            }

            QuestUtil.userQuestStatusHashMap.put(id, questStatusMap);
        }
    }

    public void save() { //TODO 수정

    }
}
