package cn.bugstack.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @description ChatGPT open ai 接口:https://platform.openai.com/account/usage
 */
public interface IOpenAI {

    String doChatGPT(String question) throws IOException;
}
