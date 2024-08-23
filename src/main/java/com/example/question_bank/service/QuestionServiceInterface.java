package com.example.question_bank.service;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import com.example.question_bank.form.InputForm;
import com.example.question_bank.form.QuestionForm;
import com.example.question_bank.form.QuestionsForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface QuestionServiceInterface {

    public Question saveQuestion(Question question, InputForm inputForm);

    public Questionj saveQuestionj(Questionj questionj, QuestionForm questionForm);

    public Questions saveQuestions(Questions questions, QuestionsForm questionsForm);

}
