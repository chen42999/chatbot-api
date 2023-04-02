package cn.bugstack.chatbot.api.domain.zsxq;

import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnAnswerQuestionAggregates;

import java.io.IOException;

/**
 * 知识星球的API接口
 */
public interface IZxsqApi {

    UnAnswerQuestionAggregates queryUnAnswerQuestionTopicId(String groupId, String cookie) throws IOException;
    boolean answer(String groupId, String cookie, String topicId, String text, boolean silence) throws IOException;
}
