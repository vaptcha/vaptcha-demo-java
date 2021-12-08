package com.vaptcha.utils;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {

    // send post request with json
    public static String postJSON(String uri, String json) throws Exception {
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        HttpPost post = new HttpPost(uri);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Accept", "application/json");
        post.setEntity(entity);
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400) {
                throw new Exception(EntityUtils.toString(response.getEntity()));
            }
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            throw new Exception("postRequest -- Client protocol exception!", e);
        } catch (IOException e) {
            throw new Exception("postRequest -- IO error!", e);
        } finally {
            post.releaseConnection();
        }
    }
}