package org.com.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.com.SDKConstant;
import org.com.model.Run;
import org.com.model.RunRequest;
import org.com.model.SubmitToolOutputsRequest;

public class RunService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    public RunService(String apiKey) {
        this.apiKey = apiKey;
    }

    public Run createRun(String threadId, RunRequest runRequest) throws Exception {
        String json = mapper.writeValueAsString(runRequest);
        RequestBody body = RequestBody.create(json, MediaType.get(SDKConstant.CONTENT_TYPE_JSON));

        String url = SDKConstant.THREAD_ENDPOINT + "/" + threadId + "/runs";

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Create run failed: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), Run.class);
        }
    }

    public Run getRunDetails(String threadId, String runId) throws Exception {
        String url = SDKConstant.BASE_URL + "/threads/" + threadId + "/runs/" + runId;

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to retrieve run details: " + response.body().string());
            }

            return mapper.readValue(response.body().string(), Run.class);
        }
    }

    public boolean submitToolOutputs(String threadId, String runId, SubmitToolOutputsRequest request) throws Exception {
        String url = SDKConstant.BASE_URL + "/threads/" + threadId + "/runs/" + runId + "/submit_tool_outputs";

        String json = mapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(json, MediaType.get(SDKConstant.CONTENT_TYPE_JSON));

        Request requestObj = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(body)
                .build();

        try (Response response = client.newCall(requestObj).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to submit tool outputs: " + response.body().string());
            }
        }

        return true;
    }

    public boolean cancelRun(String threadId, String runId) throws Exception {
        String url = SDKConstant.BASE_URL + "/threads/" + threadId + "/runs/" + runId + "/cancel";

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(RequestBody.create("", MediaType.get(SDKConstant.CONTENT_TYPE_JSON)))  // Empty body for POST request
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to cancel run: " + response.body().string());
            }
        }

        return true;
    }
}
