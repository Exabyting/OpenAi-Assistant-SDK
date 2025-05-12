package com.exabyting.ai.sdk.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import com.exabyting.ai.sdk.SDKConstant;
import com.exabyting.ai.sdk.model.Assistant;
import com.exabyting.ai.sdk.model.AssistantRequest;

/**
 * Service class for interacting with the OpenAI Assistant API.
 * Provides methods to create, update, retrieve, and delete assistants.
 */
public class AssistantService {

    /** HTTP client used for sending API requests. */
    private final OkHttpClient client = new OkHttpClient();

    /** API key for authenticating requests. */
    private final String apiKey;

    /** ObjectMapper for serializing/deserializing JSON. */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructs a new AssistantService instance with the given API key.
     *
     * @param apiKey the API key for authorization
     */
    public AssistantService(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Creates a new Assistant using the provided request data.
     *
     * @param requestObj the request object containing assistant details
     * @return the created Assistant
     * @throws Exception if the request fails or response parsing fails
     */
    public Assistant createAssistant(AssistantRequest requestObj) throws Exception {
        String json = mapper.writeValueAsString(requestObj);
        RequestBody body = RequestBody.create(json, MediaType.get(SDKConstant.CONTENT_TYPE_JSON));

        Request request = new Request.Builder()
                .url(SDKConstant.ASSISTANT_ENDPOINT)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), Assistant.class);
        }
    }

    /**
     * Updates an existing Assistant with the given ID using the update request data.
     *
     * @param assistantId    the ID of the Assistant to update
     * @param updateRequest  the updated request data
     * @return the updated Assistant
     * @throws Exception if the request fails or response parsing fails
     */
    public Assistant updateAssistant(String assistantId, AssistantRequest updateRequest) throws Exception {
        String json = mapper.writeValueAsString(updateRequest);
        RequestBody body = RequestBody.create(json, MediaType.get(SDKConstant.CONTENT_TYPE_JSON));

        Request request = new Request.Builder()
                .url(SDKConstant.ASSISTANT_ENDPOINT + "/" + assistantId)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Update failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), Assistant.class);
        }
    }

    /**
     * Retrieves an Assistant by its ID.
     *
     * @param assistantId the ID of the Assistant to retrieve
     * @return the retrieved Assistant
     * @throws Exception if the request fails or response parsing fails
     */
    public Assistant getAssistantById(String assistantId) throws Exception {
        String url = SDKConstant.ASSISTANT_ENDPOINT + "/" + assistantId;

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Get failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), Assistant.class);
        }
    }

    /**
     * Deletes an Assistant by its ID.
     *
     * @param assistantId the ID of the Assistant to delete
     * @return true if deletion is successful
     * @throws Exception if the request fails
     */
    public boolean deleteAssistant(String assistantId) throws Exception {
        String url = SDKConstant.ASSISTANT_ENDPOINT + "/" + assistantId;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Delete failed: " + response.body().string());
            }
            return true;
        }
    }
}
