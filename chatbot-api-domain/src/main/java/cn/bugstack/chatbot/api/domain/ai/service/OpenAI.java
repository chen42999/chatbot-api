package cn.bugstack.chatbot.api.domain.ai.service;

import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import cn.bugstack.chatbot.api.domain.ai.model.vo.Choices;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OpenAI implements IOpenAI {

    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Override
    public String doChatGPT(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + openAiKey);

        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);

        String proxyIp = "127.0.0.1";
        int proxyPort = 7890;
        HttpHost httpHost = new HttpHost(proxyIp, proxyPort);

        post.setConfig(
                RequestConfig.custom()
                        .setProxy(httpHost)
                        .build()
        );

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answer = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answer.append(choice.getMessage());
            }
            return answer.toString();
        } else {
            throw new RuntimeException("api.openai.com Error Code is " + response.getStatusLine().getStatusCode()) ;
        }
    }
}
