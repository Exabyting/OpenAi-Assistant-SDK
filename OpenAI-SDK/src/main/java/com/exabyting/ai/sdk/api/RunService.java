package com.exabyting.ai.sdk.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import com.exabyting.ai.sdk.SDKConstant;
import com.exabyting.ai.sdk.model.Run;
import com.exabyting.ai.sdk.model.RunRequest;
import com.exabyting.ai.sdk.model.SubmitToolOutputsRequest;

/**
 * Service class for handling run-related operations in the AI SDK.
 * Supports creating, retrieving, submitting tool outputs to, and canceling runs.
 */
public class RunService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructs a new RunService with the specified API key.
     *
     * @param apiKey the API key used for authenticating requests
     */
    public RunService(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Creates a new run for the specified thread.
     *
     * @param threadId   the ID of the thread to create the run for
     * @param runRequest the run request payload
     * @return the created Run object
     * @throws Exception if the API call fails or response parsing fails
     */
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

    /**
     * Retrieves the details of a specific run.
     *
     * @param threadId the ID of the thread containing the run
     * @param runId    the ID of the run to retrieve
     * @return the Run object containing run details
     * @throws Exception if the API call fails or response parsing fails
     */
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

    /**
     * Submits tool outputs for a specific run.
     *
     * @param threadId the ID of the thread
     * @param runId    the ID of the run to submit tool outputs to
     * @param request  the request payload containing tool outputs
     * @return true if submission is successful; otherwise, false
     * @throws Exception if the API call fails
     */
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

    /**
     * Cancels an active run for a given thread.
     *
     * @param threadId the ID of the thread containing the run
     * @param runId    the ID of the run to cancel
     * @return true if cancellation is successful
     * @throws Exception if the API call fails
     */
    public boolean cancelRun(String threadId, String runId) throws Exception {
        String url = SDKConstant.BASE_URL + "/threads/" + threadId + "/runs/" + runId + "/cancel";

        Request request = new Request.Builder()
                .url(url)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(RequestBody.create("", MediaType.get(SDKConstant.CONTENT_TYPE_JSON)))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to cancel run: " + response.body().string());
            }
        }

        return true;
    }
}
