package com.example.question_bank.service;

import com.example.question_bank.entity.QuestionImage;
import com.example.question_bank.form.QuestionimageForm;
import com.example.question_bank.repository.QuestionimageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
@Service
@RequiredArgsConstructor
public class QuestionImageService implements QuestionImageInterface{
    private final QuestionimageRepository questionimageRepository;

    @Override
    @Transactional
    public QuestionImage questionImageSave(QuestionImage questionImage, QuestionimageForm questionimageForm, MultipartFile file,InputStream inputStream) {

        BeanUtils.copyProperties(questionimageForm, questionImage);
        try {
            inputStream = file.getInputStream();
            byte[] image = inputStream.readAllBytes();
            questionImage.setImage(image);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            questionImage =questionimageRepository.save(questionImage);

        return questionImage;
    }
    @Transactional
    public QuestionImage questionImageEditSave(QuestionImage questionImage) {


        questionImage =questionimageRepository.save(questionImage);

        return questionImage;
    }

}
