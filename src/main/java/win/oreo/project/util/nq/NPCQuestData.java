package win.oreo.project.util.nq;

import java.util.List;

public class NPCQuestData {
    private String name;
    private String story;
    private List<Quest> questList;

    public NPCQuestData(String name, String story, List<Quest> questList) {
        this.name = name;
        this.story = story;
        this.questList = questList;
    }

    public String getName() {
        return name;
    }

    public String getStory() {
        return story;
    }

    public List<Quest> getQuestList() {
        return questList;
    }
}
