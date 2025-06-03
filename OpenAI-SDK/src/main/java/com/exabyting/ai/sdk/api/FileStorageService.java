package com.exabyting.ai.sdk.api;

import com.exabyting.ai.sdk.SDKConstant;
import com.exabyting.ai.sdk.model.OpenAIFileUploadResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * Service class for handling file storage operations with the OpenAI API.
 * <p>
 * This class provides methods to upload a file, retrieve file details by ID,
 * and delete a file from the OpenAI system.
 * </p>
 */
public class FileStorageService {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructs a new FileStorageService with the specified API key.
     *
     * @param apiKey the OpenAI API key used for authentication
     */
    public FileStorageService(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Uploads a file to the OpenAI API for a specified purpose.
     * <p>
     * The file must be in JSON Lines format and must conform to the API's requirements.
     * </p>
     *
     * @param file    the file to upload
     * @param purpose the intended purpose for the file (e.g., "fine-tune")
     * @return an {@link OpenAIFileUploadResponse} containing file metadata
     * @throws IOException if the file upload fails or the response cannot be parsed
     */
    public OpenAIFileUploadResponse uploadFile(File file, String purpose) throws IOException {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("purpose", purpose)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(file, MediaType.parse("application/jsonl")))
                .build();

        Request request = new Request.Builder()
                .url(SDKConstant.BASE_URL + "/files")
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("File upload failed: " + response.code() + " - " + response.body().string());
            }

            String jsonResponse = response.body().string();
            return mapper.readValue(jsonResponse, OpenAIFileUploadResponse.class);
        }
    }

    /**
     * Retrieves metadata and details of a file uploaded to OpenAI by its ID.
     *
     * @param fileId the ID of the file to retrieve
     * @return an {@link OpenAIFileUploadResponse} containing file metadata
     * @throws IOException if the request fails or the response cannot be parsed
     */
    public OpenAIFileUploadResponse getFileById(String fileId) throws IOException {
        Request request = new Request.Builder()
                .url(SDKConstant.BASE_URL + "/files/" + fileId)
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to retrieve file: " + response.code() + " - " + response.body().string());
            }

            String jsonResponse = response.body().string();
            return mapper.readValue(jsonResponse, OpenAIFileUploadResponse.class);
        }
    }

    /**
     * Deletes a file from the OpenAI system using its file ID.
     *
     * @param fileId the ID of the file to delete
     * @return {@code true} if the file was successfully deleted
     * @throws IOException if the deletion fails
     */
    public boolean deleteFileById(String fileId) throws IOException {
        Request request = new Request.Builder()
                .url(SDKConstant.BASE_URL + "/files/" + fileId)
                .delete()
                .header(SDKConstant.HEADER_AUTHORIZATION, "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to delete file: " + response.code() + " - " + response.body().string());
            }

            return true;
        }
    }
}
