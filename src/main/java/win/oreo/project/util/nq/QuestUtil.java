package win.oreo.project.util.nq;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QuestUtil {
    public static HashMap<UUID, String> NPCStoryMap = new HashMap<>(); //UUID : NPC ID, String : NPC Story
    public static HashMap<UUID, List<Quest>> NPCQuestMap = new HashMap<>(); //UUID : NPC ID, LIST<QUEST> : List of quests that belongs to npc
    public static HashMap<UUID, HashMap<UUID, QuestStatus>> userQuestStatusHashMap = new HashMap<>(); //UUID : user, HashMap : User's Quest status (<UUID, QuestStatus> >> QuestID, status)
}
