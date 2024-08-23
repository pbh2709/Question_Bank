package com.example.question_bank.service;

import com.example.question_bank.entity.QuestionImage;
import com.example.question_bank.form.QuestionimageForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

public interface QuestionImageInterface {
    public QuestionImage questionImageSave (QuestionImage questionImage, QuestionimageForm questionimageForm,MultipartFile file,InputStream inputStream);
}
