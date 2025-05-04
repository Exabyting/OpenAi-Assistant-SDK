package org.com;

import org.com.api.AssistantService;
import org.com.api.MessageService;
import org.com.api.RunService;
import org.com.api.ThreadService;
import org.com.model.*;

import javax.print.attribute.standard.MediaSize;

public class OpenAiClient {

    private final String apiKey;
    private final AssistantService assistantService;
    private final ThreadService  threadService;
    private final MessageService messageService;
    private final RunService  runService;

    public OpenAiClient(String apiKey) {
        this.apiKey = apiKey;
        this.assistantService = new AssistantService(apiKey);
        this.threadService = new ThreadService(apiKey);
        this.messageService = new MessageService(apiKey);
        this.runService = new RunService(apiKey);
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


}
