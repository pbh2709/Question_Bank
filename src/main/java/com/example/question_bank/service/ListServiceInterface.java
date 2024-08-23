package com.example.question_bank.service;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.QuestionImage;
import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import org.springframework.ui.Model;

import java.util.List;

public interface ListServiceInterface {

    public List<Question> listQuestion(Question question, Model model);

    public List<Questionj> listQuestionj(Questionj questionj, Model model);

    public  List<QuestionImage> listQuestionImage(QuestionImage questionImage,Model model);

    public List<Questions> listQuestions(Questions questions, Model model);

}
