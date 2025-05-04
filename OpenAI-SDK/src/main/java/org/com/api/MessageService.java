package org.com.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.com.SDKConstant;
import org.com.model.Message;
import org.com.model.MessageListResponse;
import org.com.model.MessageRequest;

public class MessageService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    public MessageService(String apiKey) {
        this.apiKey = apiKey;
    }

    public Message createMessage(String threadId, MessageRequest requestObj) throws Exception {
        String url = SDKConstant.THREAD_ENDPOINT + "/" + threadId + "/messages";
        String json = mapper.writeValueAsString(requestObj);

        RequestBody body = RequestBody.create(json, MediaType.get(SDKConstant.CONTENT_TYPE_JSON));

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Create message failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), Message.class);
        }
    }

    public MessageListResponse getMessages(String threadId) throws Exception {
        String url = SDKConstant.THREAD_ENDPOINT + "/" + threadId + "/messages";

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Get messages failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), MessageListResponse.class);
        }
    }

    public Message getMessageById(String threadId, String messageId) throws Exception {
        String url = SDKConstant.THREAD_ENDPOINT + "/" + threadId + "/messages/" + messageId;

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Get message by ID failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), Message.class);
        }
    }

    public boolean deleteMessageById(String threadId, String messageId) throws Exception {
        String url = SDKConstant.THREAD_ENDPOINT + "/" + threadId + "/messages/" + messageId;

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Delete message by ID failed: " + response.body().string());
            }

            return true;
        }
    }
}
