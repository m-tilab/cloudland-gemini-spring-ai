package org.example.cloudlandgeminispringai;

import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.Part;
import com.google.cloud.vertexai.generativeai.ChatSession;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatSession chatSession;

    public ChatController(ChatSession chatSession) {
        this.chatSession = chatSession;
    }


    @GetMapping("/{text}")
    public String chat(@PathVariable String text) throws IOException {

        GenerateContentResponse response = chatSession.sendMessage(text);
        return ResponseHandler.getText(response);
    }

    @GetMapping("/history")
    public List<String> getChatHistory() {
        List<Content> history = this.chatSession.getHistory();
        return history.stream().flatMap(h -> h.getPartsList().stream()).map(Part::getText).toList();
    }
}
