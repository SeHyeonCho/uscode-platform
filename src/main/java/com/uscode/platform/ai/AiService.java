package com.uscode.platform.ai;

import com.uscode.platform.ai.dto.ImageGradeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiService {
    private final RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:8000/api/v1/";

    public ImageGradeDto getImageClassify(String filename) {
        String requestUrl = url + "classify-image";
        Map<String, String> body = new HashMap<>();
        body.put("filename", filename);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<ImageGradeDto> response = restTemplate.postForEntity(requestUrl, request, ImageGradeDto.class);
        return response.getBody();
    }

}
