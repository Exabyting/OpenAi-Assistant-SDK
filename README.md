
# 🧠 OpenAI Assistants Java SDK

A clean and extensible Java SDK to integrate with OpenAI's Assistants v2 API. This SDK provides service and model classes for handling assistants, threads, messages, runs, and tool outputs.

---

## 📦 Requirements

- Java 8 or higher
- [OkHttp](https://square.github.io/okhttp/) for HTTP communication
- [Jackson](https://github.com/FasterXML/jackson) for JSON (de)serialization
- OpenAI API Key with access to Assistants v2

---

## 🔧 Installation

Add the following dependencies to your `pom.xml` (Maven):
For Using locally first keep this module in same folder and make jar by using mvn clean install .
Then use this dependency on your application `pom.xml`. 

```xml
<dependencies>
  <dependency>
    <groupId>org.com</groupId>
    <artifactId>OpenAI-SDK</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
</dependencies>
```

---

If want to try demo Application then just apply mvn clean install on OpenAi-SDK module and then run demo application with your api key

## 🧠 SDK Features Overview

| Feature                  | Description                                               |
|--------------------------|-----------------------------------------------------------|
| Assistant Management     | Create, update, fetch, and delete assistants              |
| Thread Management        | Create, fetch, delete conversation threads                |
| Message Handling         | Send, fetch, and list messages within threads             |
| Run Execution            | Create, cancel, and check run statuses                    |
| Tool Output Submission   | Submit tool outputs to an assistant run                   |

---

## 🧪 Usage Examples

> Replace `YOUR_API_KEY_HERE` with your actual OpenAI API key.

### 🔹 1. Create an Assistant

```java
AssistantRequest assistantRequest = new AssistantRequest();
assistantRequest.setInstructions("You are a helpful assistant that answers questions clearly and concisely.");
assistantRequest.setModel("gpt-4");
assistantRequest.setName("SmartHelper");
Assistant assistant = openAiClient.createAssistant(assistantRequest);

System.out.println("Created Assistant ID: " + assistant.getId());
```

### 🔹 2. Create a Thread

```java
OpenAiThread thread = openAiClient.createThread();
System.out.println("Thread ID: " + thread.getId());
```

### 🔹 3. Add a Message to the Thread

```java
MessageRequest messageRequest = new MessageRequest();
messageRequest.setRole("user");
messageRequest.setContent("Hi, is there any one for help me !");
openAiClient.createMessage(thread.getId(), messageRequest);
System.out.println("Message ID: " + message.getId());
```

### 🔹 4. Retrieve Messages from a Thread

```java
MessageListResponse messages = openAiClient.getMessages(thread.getId());
System.out.println(messages);

response.getMessages().forEach(m -> 
  System.out.println(m.getContent().get(0).getText().getValue())
);
```

### 🔹 5. Start a Run

```java
openAiClient.getRunDetails(thread.getId(), run.getId());

RunRequest runRequest = new RunRequest(assistant.getId());
Run run = runService.createRun(thread.getId(), runRequest);
System.out.println("Run ID: " + run.getId());

```

### 🔹 6. Check Run Status

```java
Run runDetails = openAiClient.getRunDetails(thread.getId(), run.getId());
System.out.println("Run Status: " + runDetails.getStatus());
```

### 🔹 7. Cancel a Run

```java
openAiClient.cancelRun(thread.getId(), run.getId());
System.out.println("Run cancelled.");
```

### 🔹 8. Submit Tool Outputs

```java
ToolOutput toolOutput = new ToolOutput();
toolOutput.toolCallId("tool-call-id");
toolOutput.output("Tool response content");

SubmitToolOutputsRequest outputsRequest = new SubmitToolOutputsRequest();
outputsRequest.toolOutputs(Collections.singletonList(toolOutput));

runService.submitToolOutputs(thread.getId(), run.getId(), outputsRequest);
System.out.println("Tool outputs submitted.");
```

### 🔹 9. Delete a Thread

```java
openAiClient.deleteThread(thread.getId());
System.out.println("Thread deleted.");
```

---

## 📂 Class Overview

### 📄 Services

| Class               | Responsibility                                              |
|--------------------|--------------------------------------------------------------|
| `AssistantService` | Create, get, update, delete assistants                       |
| `ThreadService`    | Create, fetch, delete threads                                |
| `MessageService`   | Send and retrieve thread messages                            |
| `RunService`       | Start, retrieve, cancel runs; submit tool outputs            |
| `SDKConstant`      | Holds API URLs and headers                                   |
| `OpenAiClient`     | (Optional wrapper for simplified client usage)               |

### 📄 Models

| Class                         | Description                                          |
|------------------------------|------------------------------------------------------|
| `Assistant`                  | Assistant object returned by API                     |
| `AssistantRequest`           | Request for creating/updating assistants             |
| `Tool`                       | Tool capabilities included in assistants             |
| `OpenAiThread`               | Represents a thread (conversation context)           |
| `Message`                    | Represents a message in a thread                     |
| `MessageRequest`             | Message payload for thread communication             |
| `MessageListResponse`        | List wrapper for multiple messages                   |
| `Content`                    | Message content block (text, tool calls, etc.)       |
| `Text`                       | Text content wrapper inside content                  |
| `Run`                        | Represents the status and metadata of a run          |
| `RunRequest`                 | Payload to start a run                               |
| `ToolOutput`                 | Individual tool output with call ID and content      |
| `SubmitToolOutputsRequest`   | Wrapper to submit multiple tool outputs              |

---

## 🔐 Authentication

All requests require your OpenAI API key as a Bearer token:

```
Authorization: Bearer YOUR_API_KEY_HERE
OpenAI-Beta: assistants=v2
```

These headers are automatically set in all services via `SDKConstant.java`.

---

## ✅ Status

✔️ Feature-complete for Assistants v2  
🧪 Tested with GPT-4 assistants  
🌱 Easy to extend with function calling and tool enhancements  

---

## 🙋‍♂️ Support

For questions, issues, or feature requests, please contact the repository maintainer or submit an issue on GitHub.





