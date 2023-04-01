package cn.bugstack.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * 单元测试
 */
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        get.addHeader("cookie","sensorsdata2015jssdkcross={\"distinct_id\":\"187330f60ca15e-08dfb156bced638-26021f51-2073600-187330f60cbc91\",\"first_id\":\"\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MzMwZjYwY2ExNWUtMDhkZmIxNTZiY2VkNjM4LTI2MDIxZjUxLTIwNzM2MDAtMTg3MzMwZjYwY2JjOTEifQ==\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"187330f60ca15e-08dfb156bced638-26021f51-2073600-187330f60cbc91\"}; zsxq_access_token=30834553-1D2F-5139-7A34-4DDA33A09813_04AED0B1A68E3535; zsxqsessionid=1803ff431d14007a33130a94c247c106; abtest_env=beta");
        get.addHeader("Content-Type", "application/json;charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void comment() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/181455124112812/comments");
        post.addHeader("cookie",
                "zsxq_access_token=30834553-1D2F-5139-7A34-4DDA33A09813_04AED0B1A68E3535; zsxqsessionid=1803ff431d14007a33130a94c247c106; UM_distinctid=1873b803d4b877-01b8c775bcfbba-26021f51-1fa400-1873b803d4c936; sensorsdata2015jssdkcross={\"distinct_id\":\"184251124584112\",\"first_id\":\"187330f60ca15e-08dfb156bced638-26021f51-2073600-187330f60cbc91\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MzMwZjYwY2ExNWUtMDhkZmIxNTZiY2VkNjM4LTI2MDIxZjUxLTIwNzM2MDAtMTg3MzMwZjYwY2JjOTEiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIxODQyNTExMjQ1ODQxMTIifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"184251124584112\"},\"$device_id\":\"187330f60ca15e-08dfb156bced638-26021f51-2073600-187330f60cbc91\"}; abtest_env=beta");
        post.addHeader("Content-Type", "application/json;charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"不知道呀\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
