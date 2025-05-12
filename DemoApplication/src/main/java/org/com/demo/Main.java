package org.com.demo;


import com.exabyting.ai.sdk.OpenAiClient;
import com.exabyting.ai.sdk.model.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        OpenAiClient openAiClient = new OpenAiClient("API_KEY");

        //CREATE AI ASSISTANT

        AssistantRequest assistantRequest = new AssistantRequest();

        assistantRequest.setInstructions("You are a helpful assistant that answers questions clearly and concisely.");
        assistantRequest.setModel("gpt-4");
        assistantRequest.setName("SmartHelper");


        Assistant assistant = openAiClient.createAssistant(assistantRequest);

        System.out.println(assistant.getId());


        //Update Assistant

        AssistantRequest updateAssistantRequest = new AssistantRequest();

        updateAssistantRequest.setInstructions("");
        updateAssistantRequest.setModel("");
        updateAssistantRequest.setName("");

        Assistant assistant1 = openAiClient.updateAssistant(assistant.getId(), updateAssistantRequest);

        System.out.println(assistant1.getId());


        //Delete Assistant

        openAiClient.deleteAssistant(assistant1.getId());


        //Create Thread

        OpenAiThread thread = openAiClient.createThread();

        System.out.println(thread.getId());


        //Add Message To  thread

        MessageRequest messageRequest = new MessageRequest();

        messageRequest.setRole("user");
        messageRequest.setContent("Hi, is there any one for help me !");

        openAiClient.createMessage(thread.getId(), messageRequest);

        //Run thread
        RunRequest runRequest = new RunRequest();
        runRequest.setAssistantId(assistant.getId());

        Run run = openAiClient.createRun(thread.getId(), runRequest);

        System.out.println(run.getId());


        //check run status

        Run runDetails = openAiClient.getRunDetails(thread.getId(), run.getId());

        openAiClient.cancelRun(thread.getId(), run.getId());

        //Get List of Message of thread

        MessageListResponse messages = openAiClient.getMessages(thread.getId());

        System.out.println(messages);

        openAiClient.deleteThread(thread.getId());


    }
}