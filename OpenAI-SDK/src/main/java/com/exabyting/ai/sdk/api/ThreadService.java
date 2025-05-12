package com.exabyting.ai.sdk.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import com.exabyting.ai.sdk.SDKConstant;
import com.exabyting.ai.sdk.model.OpenAiThread;

/**
 * Service class for managing OpenAI threads via API calls.
 */
public class ThreadService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructs a new ThreadService with the provided API key.
     *
     * @param apiKey the OpenAI API key for authorization
     */
    public ThreadService(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Creates a new thread using the OpenAI API.
     *
     * @return the created {@link OpenAiThread} object
     * @throws Exception if the API request fails or response parsing fails
     */
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

    /**
     * Retrieves a thread by its ID.
     *
     * @param threadId the ID of the thread to retrieve
     * @return the {@link Thread} object corresponding to the given ID
     * @throws Exception if the API request fails or response parsing fails
     */
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

    /**
     * Deletes a thread by its ID.
     *
     * @param threadId the ID of the thread to delete
     * @return true if the deletion is successful
     * @throws Exception if the API request fails
     */
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
