package cn.bugstack.chatbot.api.domain.zsxq.model.res;

import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Topics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 结果数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespData {
    private List<Topics> topics;

}