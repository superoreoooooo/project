package win.oreo.project.util.nq;

import java.util.UUID;

public class Quest {
    private UUID questID;
    private String questTitle;
    private String questSubtitle;
    private String questStory;
    private String taskORIGIN;
    private String rewardORIGIN;

    public Quest(UUID questID, String questTitle, String questSubtitle, String questStory, String taskORIGIN, String rewardORIGIN) {
        this.questID = questID;
        this.questTitle = questTitle;
        this.questSubtitle = questSubtitle;
        this.questStory = questStory;
        this.taskORIGIN = taskORIGIN;
        this.rewardORIGIN = rewardORIGIN;
    }

    public UUID getQuestID() {
        return questID;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public String getQuestSubtitle() {
        return questSubtitle;
    }

    public String getQuestStory() {
        return questStory;
    }

    public String getTaskORIGIN() {
        return taskORIGIN;
    }

    public String getRewardORIGIN() {
        return rewardORIGIN;
    }
}
