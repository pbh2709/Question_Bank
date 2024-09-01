package com.example.question_bank.service;

import com.example.question_bank.entity.*;
import com.example.question_bank.form.InputForm;
import com.example.question_bank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListService implements ListServiceInterface {

    private final QuestionRepository questionRepository;
    private final QuestionjRepository questionjRepository;

    private final TestedInfoRepository testedInfoRepository;

    private final QuestionimageRepository questionimageRepository;
    private final QuestionsRepository questionsRepository;


    @Override
    public List<Question> listQuestion(Question question, Model model) {

        List<Question> questionList = questionRepository.findAll(Sort.by("createdAt").descending());
        model.addAttribute("questionList", questionList);

        return questionList;
    }

    @Override
    public List<Questionj> listQuestionj(Questionj questionj, Model model) {

        List<Questionj> questionjList = questionjRepository.findAll(Sort.by("createdAt").descending());
        model.addAttribute("questionjList", questionjList);

        return questionjList;
    }

    @Override
    public List<QuestionImage> listQuestionImage(QuestionImage questionImage, Model model) {

        List<QuestionImage> questionImageList = questionimageRepository.findAll(Sort.by("createdAt").descending());
        model.addAttribute("questionImageList", questionImageList);

        return questionImageList;
    }

    @Override
    public List<Questions> listQuestions(Questions questions, Model model) {

        List<Questions> questionsList = questionsRepository.findAll(Sort.by("createdAt").descending());

        model.addAttribute("questionsList", questionsList);

        return questionsList;
    }

    public List<TestedInfo> listTestedInfo() {

        List<TestedInfo> testedInfoList = testedInfoRepository.findAll(Sort.by("testedAt").descending());


        return testedInfoList;

    }

    public List<Question> listQuestionEdit(InputForm inputForm) {
        List<Question>questionList1=new ArrayList<>();
        String uuid = inputForm.getUuid();
        List<String> idlist = questionRepository.findIdByUuid(uuid);
        if (idlist != null) {
            for (int i = 0; i <= idlist.size(); i++) {
                String id = idlist.get(i);
                Optional<Question> questionOptional = questionRepository.findById(id);
                if (questionOptional.isPresent()) {
                    Question question = questionOptional.get();
                    List<Question> questionList = new ArrayList<Question>();
                    questionList.add(question);
                    return questionList;
                }
            }
        }    return questionList1;
    }
}