package com.exabyting.ai.sdk.api;

import com.exabyting.ai.sdk.SDKConstant;
import com.exabyting.ai.sdk.model.OpenAIVectorStoreRequest;
import com.exabyting.ai.sdk.model.OpenAIVectorStoreResponse;
import com.exabyting.ai.sdk.model.VectorStoreFileAttachRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

/**
 * Service class for managing Vector Stores with the OpenAI API.
 * <p>
 * This class provides functionality to create, retrieve, delete vector stores,
 * and attach or remove files to/from a vector store.
 * </p>
 */
public class VectorStoreService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructs a new VectorStoreService with the specified API key.
     *
     * @param apiKey the OpenAI API key used for authentication
     */
    public VectorStoreService(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Creates a new vector store using the given request data.
     *
     * @param requestDto the request object containing vector store configuration
     * @return the response containing metadata about the created vector store
     * @throws IOException if the creation fails or the response cannot be parsed
     */
    public OpenAIVectorStoreResponse createVectorStore(OpenAIVectorStoreRequest requestDto) throws IOException {
        String jsonRequest = mapper.writeValueAsString(requestDto);
        RequestBody body = RequestBody.create(jsonRequest, MediaType.get(SDKConstant.CONTENT_TYPE_JSON));

        Request request = new Request.Builder()
                .url(SDKConstant.BASE_URL + "/vector_stores")
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to create vector store: " + response.code() + " - " + response.body().string());
            }

            String jsonResponse = response.body().string();
            return mapper.readValue(jsonResponse, OpenAIVectorStoreResponse.class);
        }
    }

    /**
     * Retrieves the details of a vector store by its ID.
     *
     * @param vectorStoreId the unique identifier of the vector store
     * @return the response containing metadata about the vector store
     * @throws IOException if the retrieval fails or the response cannot be parsed
     */
    public OpenAIVectorStoreResponse getVectorStoreById(String vectorStoreId) throws IOException {
        Request request = new Request.Builder()
                .url(SDKConstant.BASE_URL + "/vector_stores")
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to retrieve vector store: " + response.code() + " - " + response.body().string());
            }

            String json = response.body().string();
            return mapper.readValue(json, OpenAIVectorStoreResponse.class);
        }
    }

    /**
     * Deletes a vector store by its ID.
     *
     * @param vectorStoreId the unique identifier of the vector store
     * @return {@code true} if the deletion was successful
     * @throws IOException if the deletion fails
     */
    public boolean deleteVectorStoreById(String vectorStoreId) throws IOException {
        Request request = new Request.Builder()
                .url(SDKConstant.BASE_URL + "/vector_stores/" + vectorStoreId)
                .delete()
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to delete vector store: " + response.code() + " - " + response.body().string());
            }

            return true;
        }
    }

    /**
     * Attaches a file to an existing vector store.
     *
     * @param vectorStoreId the ID of the vector store
     * @param fileId        the ID of the file to attach
     * @throws IOException if the operation fails
     */
    public void addFileToVectorStore(String vectorStoreId, String fileId) throws IOException {
        VectorStoreFileAttachRequest attachRequest = new VectorStoreFileAttachRequest(fileId);
        String json = mapper.writeValueAsString(attachRequest);

        RequestBody body = RequestBody.create(json, MediaType.get(SDKConstant.CONTENT_TYPE_JSON));

        Request request = new Request.Builder()
                .url(SDKConstant.BASE_URL + "/vector_stores/" + vectorStoreId + "/files")
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to attach file to vector store: " + response.code() + " - " + response.body().string());
            }
        }
    }

    /**
     * Removes a file from a vector store.
     *
     * @param vectorStoreId the ID of the vector store
     * @param fileId        the ID of the file to remove
     * @throws IOException if the operation fails
     */
    public void deleteFileFromVectorStore(String vectorStoreId, String fileId) throws IOException {
        Request request = new Request.Builder()
                .url(SDKConstant.BASE_URL + "/vector_stores/" + vectorStoreId + "/files/" + fileId)
                .delete()
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .header(SDKConstant.HEADER_CONTENT_TYPE, SDKConstant.CONTENT_TYPE_JSON)
                .header(SDKConstant.HEADER_BETA, SDKConstant.BETA_VERSION)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to delete file from vector store: " + response.code() + " - " + response.body().string());
            }
        }
    }
}
