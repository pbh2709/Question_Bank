package com.example.question_bank.service;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.QuestionImage;
import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import com.example.question_bank.form.InputForm;
import com.example.question_bank.form.QuestionForm;
import com.example.question_bank.form.QuestionimageForm;
import com.example.question_bank.form.QuestionsForm;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

public interface QuestionEditInterface {
    public Question editStartQuestion(Question question, String uuid, InputForm inputForm, Model model);

    public Questionj editStartQuestionj(Questionj questionj, String uuid, QuestionForm questionForm, Model model);

    public QuestionImage editStartQuestionImage(QuestionImage questionImage, String uuid, QuestionimageForm questionimageForm, Model model);

    public Questions editStartQuestions(Questions questions, String uuid, QuestionsForm questionsForm, Model model);

    public Question editEndQuestion(Question question, InputForm inputForm, Model model);

    public Questionj editEndQuestionj(Questionj questionj, QuestionForm questionform, Model model);

    public QuestionImage editEndQuestionImage(QuestionImage questionImage, QuestionimageForm questionimageForm, MultipartFile file, InputStream inputStream);

    public Questions editEndQuestions(Questions questions, QuestionsForm questionsForm, Model model);
}

