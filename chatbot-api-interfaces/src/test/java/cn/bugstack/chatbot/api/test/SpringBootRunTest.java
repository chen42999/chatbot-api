package cn.bugstack.chatbot.api.test;

import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.zsxq.IZxsqApi;
import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnAnswerQuestionAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Autowired
    private IZxsqApi zxsqApi;
    @Autowired
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnswerQuestionAggregates unAnswerQuestionAggregates = zxsqApi.queryUnAnswerQuestionTopicId(groupId, cookie);
        logger.info("测试结果: {}", JSON.toJSONString(unAnswerQuestionAggregates));

        List<Topics> topicsList = unAnswerQuestionAggregates.getResp_data().getTopics();
        for (Topics t : topicsList) {
            Long topicId = t.getTopicId();
            String text = t.getTalk().getText();

            logger.info("topicId: {} text: {}", topicId, text);

            // 回答问题
            zxsqApi.answer(groupId, cookie, String.valueOf(topicId), text, false);
        }
    }

    @Test
    public void test_openAi() throws IOException {
        String res = openAI.doChatGPT("用java写出Avl树的代码");
        logger.info("测试结果: {}", res);
    }

}
