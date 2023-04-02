package cn.bugstack.chatbot.api.domain.zsxq.model.res;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 请求问答接口结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRes {
    private boolean succeed;

    public boolean isSucceed() {
        return succeed;
    }

}
