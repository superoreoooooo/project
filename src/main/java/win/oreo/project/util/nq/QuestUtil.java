package win.oreo.project.util.nq;

import java.util.HashMap;
import java.util.UUID;

public class QuestUtil {
    public static HashMap<UUID, UserQuestStatus> userQuestStatusHashMap = new HashMap<>(); //UUID : user, UserQuestStatus : User's Quest status (<UUID, QuestStatus> >> QuestID, status)
}
