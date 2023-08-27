package win.oreo.project.util.NPC;

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
}
