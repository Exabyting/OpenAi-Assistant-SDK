package com.exabyting.ai.sdk.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import com.exabyting.ai.sdk.SDKConstant;
import com.exabyting.ai.sdk.model.Message;
import com.exabyting.ai.sdk.model.MessageListResponse;
import com.exabyting.ai.sdk.model.MessageRequest;

/**
 * Service class for managing messages within a conversation thread via the OpenAI API.
 * Provides methods to create, retrieve, and delete messages in a specified thread.
 */
public class MessageService {

    /**
     * HTTP client for executing API requests.
     */
    private final OkHttpClient client = new OkHttpClient();

    /**
     * API key used for authorization headers in requests.
     */
    private final String apiKey;

    /**
     * JSON serializer/deserializer for request and response bodies.
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructs a new MessageService with the specified API key.
     *
     * @param apiKey the API key for authenticating requests
     */
    public MessageService(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Creates a new message in the given thread.
     *
     * @param threadId   the identifier of the thread to add the message to
     * @param requestObj the message request containing content and metadata
     * @return the created {@link Message} object
     * @throws Exception if serialization fails or the HTTP request is unsuccessful
     */
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

    /**
     * Retrieves all messages from the specified thread.
     *
     * @param threadId the identifier of the thread to fetch messages from
     * @return a {@link MessageListResponse} containing the list of messages
     * @throws Exception if the HTTP request fails or response parsing fails
     */
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

    /**
     * Retrieves a single message by its ID within a specified thread.
     *
     * @param threadId  the identifier of the thread containing the message
     * @param messageId the identifier of the message to retrieve
     * @return the requested {@link Message} object
     * @throws Exception if the HTTP request fails or response parsing fails
     */
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

    /**
     * Deletes a message by its ID within the specified thread.
     *
     * @param threadId  the identifier of the thread containing the message
     * @param messageId the identifier of the message to delete
     * @return {@code true} if deletion was successful
     * @throws Exception if the HTTP request fails
     */
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
