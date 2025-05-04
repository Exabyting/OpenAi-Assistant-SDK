package org.com.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.com.SDKConstant;
import org.com.model.Assistant;
import org.com.model.AssistantRequest;

public class AssistantService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    public AssistantService(String apiKey) {
        this.apiKey = apiKey;
    }

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
