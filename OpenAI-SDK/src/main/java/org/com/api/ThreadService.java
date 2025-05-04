package org.com.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.com.SDKConstant;
import org.com.model.OpenAiThread;

public class ThreadService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    public ThreadService(String apiKey) {
        this.apiKey = apiKey;
    }

    public OpenAiThread createThread() throws Exception {
        RequestBody emptyBody = RequestBody.create("", MediaType.get(SDKConstant.CONTENT_TYPE_JSON));

        Request request = new Request.Builder()
                .url(SDKConstant.THREAD_ENDPOINT)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(emptyBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Thread creation failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), OpenAiThread.class);
        }
    }

    public Thread getThreadById(String threadId) throws Exception {
        String url = SDKConstant.THREAD_ENDPOINT + "/" + threadId;

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Get thread failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), Thread.class);
        }
    }

    public boolean deleteThread(String threadId) throws Exception {
        String url = SDKConstant.THREAD_ENDPOINT + "/" + threadId;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Delete thread failed: " + response.body().string());
            }

            return true;
        }
    }


}
