package org.example.cloudlandgeminispringai.simple;

import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/chat")
public class ChatController {

    private final VertexAiGeminiChatModel vertexAiGemini;

    public ChatController(VertexAiGeminiChatModel vertexAiGemini) {
        this.vertexAiGemini = vertexAiGemini;
    }

    @GetMapping("/{text}")
    public String chat(@PathVariable String text) {

        return vertexAiGemini.call(text);
    }

    @PostMapping("/multi-model")
    public String file(@RequestParam("file") MultipartFile file, @RequestParam String question) {

        var userMessage = new UserMessage(
                question, // text content
                List.of(new Media(MimeTypeUtils.IMAGE_PNG, file.getResource()))); // image content
        return vertexAiGemini.call(new Prompt(userMessage)).getResult().getOutput().getContent();
    }
}
