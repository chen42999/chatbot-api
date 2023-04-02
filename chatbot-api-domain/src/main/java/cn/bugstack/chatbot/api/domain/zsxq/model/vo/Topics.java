package cn.bugstack.chatbot.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topics {
    private Long topicId;

    private Group group;

    private String type;

    private Talk talk;

    private List<ShowComments> showComments;

    private int likesCount;

    private int rewardsCount;

    private int commentsCount;

    private int readingCount;

    private int readersCount;

    private boolean digested;

    private boolean sticky;

    private String createTime;

    private UserSpecific userSpecific;

}
