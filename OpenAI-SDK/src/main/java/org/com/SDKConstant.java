package org.com;

public class SDKConstant {

    public static final String BASE_URL = "https://api.openai.com/v1";
    public static final String ASSISTANT_ENDPOINT = BASE_URL + "/assistants";
    public static final String THREAD_ENDPOINT = BASE_URL + "/threads";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_BETA = "OpenAI-Beta";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String BETA_VERSION = "assistants=v2";
}
