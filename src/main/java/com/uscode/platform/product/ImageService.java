package com.uscode.platform.product;

import com.uscode.platform.ai.dto.ImageGradeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    private final String dir = "/home/yun777567/images/";

    public String saveImage(MultipartFile file) {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(dir + filename);

        try {
            file.transferTo(dest);
            return "/images/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 실패", e);
        }
    }

    public ProductGrade grading(ImageGradeDto dto) {
        String status = dto.getQuality_status();
        ProductGrade grade;

        switch (status) {
            case "우수" -> grade = ProductGrade.A;
            case "양호" -> grade = ProductGrade.B;
            case "보통" -> grade = ProductGrade.C;
            default  -> grade = ProductGrade.D;
        }

        return grade;
    }
}
