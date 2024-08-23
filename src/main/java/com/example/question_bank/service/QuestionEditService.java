package com.example.question_bank.service;

import com.example.question_bank.config.ImageUtil;
import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.QuestionImage;
import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import com.example.question_bank.form.InputForm;
import com.example.question_bank.form.QuestionForm;
import com.example.question_bank.form.QuestionimageForm;
import com.example.question_bank.form.QuestionsForm;
import com.example.question_bank.repository.QuestionRepository;
import com.example.question_bank.repository.QuestionimageRepository;
import com.example.question_bank.repository.QuestionjRepository;
import com.example.question_bank.repository.QuestionsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionEditService implements QuestionEditInterface {

    private final QuestionRepository questionRepository;

    private final QuestionjRepository questionjRepository;

    private final QuestionimageRepository questionimageRepository;

    private final QuestionsRepository questionsRepository;


    @Override
    @Transactional
    public Question editStartQuestion(Question question, String uuid, InputForm inputForm, Model model) {
        Optional<Question> questionOptional = questionRepository.findById(uuid);
        if (questionOptional.isPresent()) {
            Question q = questionOptional.get();
            BeanUtils.copyProperties(q, inputForm);
        }
        model.addAttribute("inputForm", inputForm);

        return question;
    }

    @Override
    @Transactional
    public Questionj editStartQuestionj(Questionj questionj, String uuid, QuestionForm questionForm, Model model) {
        Optional<Questionj> questionjOptional = questionjRepository.findById(uuid);
        if (questionjOptional.isPresent()) {
            Questionj q = questionjOptional.get();
            BeanUtils.copyProperties(q, questionForm);
        }
        model.addAttribute("questionForm", questionForm);

        return questionj;
    }

    @Override
    @Transactional
    public QuestionImage editStartQuestionImage(QuestionImage questionImage, String uuid, QuestionimageForm questionimageForm, Model model) {
        Optional<QuestionImage> questionImageOptional = questionimageRepository.findById(uuid);
        if (questionImageOptional.isPresent()) {
            QuestionImage q = questionImageOptional.get();
            BeanUtils.copyProperties(q, questionimageForm);
            model.addAttribute("q", q);
        }
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("questionimageForm", questionimageForm);

        return questionImage;
    }

    @Override
    @Transactional
    public Questions editStartQuestions(Questions questions, String uuid, QuestionsForm questionsForm, Model model) {
        Optional<Questions> questionsOptional = questionsRepository.findById(uuid);
        if (questionsOptional.isPresent()) {
            Questions q = questionsOptional.get();
            BeanUtils.copyProperties(q, questionsForm);
        }
        model.addAttribute("questionsForm", questionsForm);

        return questions;
    }

    @Override
    @Transactional
    public Question editEndQuestion(Question question, InputForm inputForm, Model model) {

        Optional<Question> questionOptional = questionRepository.findById(inputForm.getUuid());
        if (questionOptional.isPresent()) {
            Question s = questionOptional.get();

            BeanUtils.copyProperties(inputForm, s);

            question = questionRepository.save(s);

        }
        return question;
    }

    @Override
    @Transactional
    public Questionj editEndQuestionj(Questionj questionj, QuestionForm questionForm, Model model) {

        Optional<Questionj> questionjOptional = questionjRepository.findById(questionForm.getUuid());
        if (questionjOptional.isPresent()) {
            Questionj s = questionjOptional.get();

            BeanUtils.copyProperties(questionForm, s);

            questionj = questionjRepository.save(s);

        }
        return questionj;
    }

    @Override
    @Transactional
    public QuestionImage editEndQuestionImage(QuestionImage questionImage, QuestionimageForm questionimageForm, MultipartFile file, InputStream inputStream) {

        Optional<QuestionImage> questionImageOptional = questionimageRepository.findById(questionimageForm.getUuid());
        if (questionImageOptional.isPresent()) {
            BeanUtils.copyProperties(questionimageForm, questionImage);
            QuestionImage q = questionImageOptional.get();
            BeanUtils.copyProperties(questionImage, q);
            try {
                inputStream = file.getInputStream();
                byte[] image = inputStream.readAllBytes();
                q.setImage(image);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            questionImage = questionimageRepository.save(q);


        }
        return questionImage;
    }

    @Override
    @Transactional
    public Questions editEndQuestions(Questions questions, QuestionsForm questionsForm, Model model) {

        Optional<Questions> questionsOptional = questionsRepository.findById(questionsForm.getUuid());
        if (questionsOptional.isPresent()) {
            Questions s = questionsOptional.get();

            BeanUtils.copyProperties(questionsForm, s);

            questions = questionsRepository.save(s);

        }
        return questions;
    }
}
