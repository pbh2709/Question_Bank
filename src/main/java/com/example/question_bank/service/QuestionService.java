package com.example.question_bank.service;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import com.example.question_bank.form.InputForm;
import com.example.question_bank.form.QuestionForm;
import com.example.question_bank.form.QuestionsForm;
import com.example.question_bank.repository.QuestionRepository;
import com.example.question_bank.repository.QuestionjRepository;
import com.example.question_bank.repository.QuestionsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@RequiredArgsConstructor
public class QuestionService implements QuestionServiceInterface {

    private final QuestionRepository questionRepository;
    private final QuestionjRepository questionjRepository;
    private final QuestionsRepository questionsRepository;




    @Transactional
    @Override
    public Question saveQuestion(Question question,
                                 @Valid InputForm inputForm) {
            BeanUtils.copyProperties(inputForm, question);
            question = questionRepository.save(question);

        return question;
    }


    @Transactional
    @Override
    public Questionj saveQuestionj(Questionj questionj,
                                  @Valid QuestionForm questionForm) {
        BeanUtils.copyProperties(questionForm, questionj);
        questionj = questionjRepository.save(questionj);

        return questionj;
    }

    @Transactional
    @Override
    public Questions saveQuestions(Questions questions,
                                   @Valid QuestionsForm questionsForm) {
        BeanUtils.copyProperties(questionsForm, questions);
        questions = questionsRepository.save(questions);

        return questions;
    }

}



