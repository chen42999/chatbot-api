package cn.bugstack.chatbot.api.domain.zsxq.model.vo;

import cn.bugstack.chatbot.api.domain.zsxq.model.res.RespData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    private boolean succeeded;

    private RespData respData;


}