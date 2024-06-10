package org.example.cloudlandgeminispringai;

import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/multi-model")
public class MultiModelController {

    private final GenerativeModel generativeModel;

    public MultiModelController(@Qualifier("geminiProVisionGenerativeModel") GenerativeModel generativeModel) {
        this.generativeModel = generativeModel;
    }

    @PostMapping
    public String file(@RequestParam("file") MultipartFile file, @RequestParam String question) throws IOException {
        GenerateContentResponse generateContentResponse = this.generativeModel.generateContent(
                ContentMaker.fromMultiModalData(
                        PartMaker.fromMimeTypeAndData(Objects.requireNonNull(file.getContentType()), file.getBytes()),
                        question
                )
        );
        return ResponseHandler.getText(generateContentResponse);
    }
}
