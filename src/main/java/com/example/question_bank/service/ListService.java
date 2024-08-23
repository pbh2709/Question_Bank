package com.example.question_bank.service;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.QuestionImage;
import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import com.example.question_bank.repository.QuestionRepository;
import com.example.question_bank.repository.QuestionimageRepository;
import com.example.question_bank.repository.QuestionjRepository;
import com.example.question_bank.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService implements ListServiceInterface {

    private final QuestionRepository questionRepository;
    private  final QuestionjRepository questionjRepository;

    private  final QuestionimageRepository questionimageRepository;
    private  final QuestionsRepository questionsRepository;

    @Override
    public List<Question> listQuestion(Question question, Model model) {

        List<Question> questionList=questionRepository.findAll(Sort.by("createdAt").descending());
        model.addAttribute("questionList",questionList);

        return  questionList ;
    }
    @Override
    public List<Questionj> listQuestionj(Questionj questionj, Model model) {

        List<Questionj> questionjList=questionjRepository.findAll(Sort.by("createdAt").descending());
        model.addAttribute("questionjList",questionjList);

        return  questionjList ;
    }

    @Override
    public List<QuestionImage> listQuestionImage(QuestionImage questionImage, Model model) {

        List<QuestionImage> questionImageList=questionimageRepository.findAll(Sort.by("createdAt").descending());
        model.addAttribute("questionImageList",questionImageList);

        return  questionImageList ;
    }

    @Override
    public List<Questions> listQuestions(Questions questions, Model model) {

        List<Questions> questionsList=questionsRepository.findAll(Sort.by("createdAt").descending());
        model.addAttribute("questionsList",questionsList);

        return  questionsList ;
    }

}
