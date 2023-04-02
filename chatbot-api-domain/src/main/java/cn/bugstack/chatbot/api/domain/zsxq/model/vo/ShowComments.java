package cn.bugstack.chatbot.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowComments {
    private Long comment_id;

    private String create_time;

    private Owner owner;

    private String text;

    private int likes_count;

    private int rewards_count;

    private boolean sticky;


}
