package com.exabyting.ai.sdk;

import com.exabyting.ai.sdk.api.*;
import com.exabyting.ai.sdk.model.*;

import java.io.File;
import java.io.IOException;

public class OpenAiClient {

    private final String apiKey;
    private final AssistantService assistantService;
    private final ThreadService  threadService;
    private final MessageService messageService;
    private final RunService  runService;
    private final FileStorageService fileStorageService;
    private final VectorStoreService vectorStoreService;

    public OpenAiClient(String apiKey) {
        this.apiKey = apiKey;
        this.assistantService = new AssistantService(apiKey);
        this.threadService = new ThreadService(apiKey);
        this.messageService = new MessageService(apiKey);
        this.runService = new RunService(apiKey);
        this.fileStorageService = new FileStorageService(apiKey);
        this.vectorStoreService = new VectorStoreService(apiKey);
    }

    public Assistant createAssistant(AssistantRequest request) throws Exception {

        return assistantService.createAssistant(request);
    }

    public Assistant updateAssistant(String assistantId, AssistantRequest request) throws Exception {

        return assistantService.updateAssistant(assistantId, request);
    }

    public Assistant retrieveAssistant (String assistantId) throws Exception {

        return assistantService.getAssistantById(assistantId);
    }

    public boolean deleteAssistant(String assistantId) throws Exception {

        return assistantService.deleteAssistant(assistantId);
    }

    public OpenAiThread createThread() throws Exception {

        return threadService.createThread();
    }

    public Thread retrieveThread(String threadId) throws Exception {

        return threadService.getThreadById(threadId);
    }

    public boolean deleteThread(String threadId) throws Exception {

        return threadService.deleteThread(threadId);
    }

    public Message createMessage(String threadId, MessageRequest request) throws Exception {

        return messageService.createMessage(threadId, request);
    }

    public MessageListResponse getMessages(String threadId) throws Exception {

        return messageService.getMessages(threadId);
    }

    public Message getMessageById(String threadId, String messageId) throws Exception {

        return messageService.getMessageById(threadId, messageId);
    }

    public boolean deleteMessageById(String threadId, String messageId) throws Exception {

        return messageService.deleteMessageById(threadId, messageId);
    }

    public Run createRun(String threadId, RunRequest runRequest) throws Exception {

        return runService.createRun(threadId, runRequest);
    }

    public Run getRunDetails(String threadId, String runId) throws Exception {

        return runService.getRunDetails(threadId, runId);
    }

    public boolean submitToolOutputs(String threadId, String runId, SubmitToolOutputsRequest request) throws Exception {
        return runService.submitToolOutputs(threadId, runId, request);
    }

    public boolean cancelRun(String threadId, String runId) throws Exception {

        return runService.cancelRun(threadId, runId);
    }

    public OpenAIFileUploadResponse uploadFile(File file, String purpose) throws IOException {

        return fileStorageService.uploadFile(file, purpose);
    }

    public OpenAIFileUploadResponse getFileById(String fileId) throws IOException {

        return fileStorageService.getFileById(fileId);
    }

    public boolean deleteFileById(String fileId) throws IOException {

        return fileStorageService.deleteFileById(fileId);
    }

    public OpenAIVectorStoreResponse createVectorStore(OpenAIVectorStoreRequest requestDto) throws IOException {

        return vectorStoreService.createVectorStore(requestDto);
    }

    public OpenAIVectorStoreResponse getVectorStoreById(String vectorStoreId) throws IOException {

        return vectorStoreService.getVectorStoreById(vectorStoreId);
    }

    public boolean deleteVectorStoreById(String vectorStoreId) throws IOException {

        return vectorStoreService.deleteVectorStoreById(vectorStoreId);
    }

    public void addFileToVectorStore(String vectorStoreId, String fileId) throws IOException {

        vectorStoreService.addFileToVectorStore(vectorStoreId, fileId);
    }

    public void deleteFileFromVectorStore(String vectorStoreId, String fileId) throws IOException {

        vectorStoreService.deleteFileFromVectorStore(vectorStoreId, fileId);
    }

}
