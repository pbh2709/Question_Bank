package com.example.question_bank.service;

import com.example.question_bank.entity.*;
import com.example.question_bank.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@RequiredArgsConstructor

public class DeleteService implements DeleteInterface{
    private  final QuestionRepository questionRepository;

    private  final QuestionjRepository questionjRepository;

    private  final QuestionimageRepository questionimageRepository;

    private  final QuestionsRepository questionsRepository;

    @Override
    @Transactional
    public Question questionDelete(Question question, String uuid) {
        questionRepository.deleteById(uuid);
        return question ;
    }
    @Override
    @Transactional
    public Questionj questionjDelete(Questionj questionj, String uuid) {
        questionjRepository.deleteById(uuid);
        return questionj ;
    }

    @Override
    @Transactional
    public QuestionImage questionImageDelete(QuestionImage questionImage,String uuid) {
         questionimageRepository.deleteById(uuid);
        return questionImage ;
    }
    @Override
    @Transactional
    public Questions questionsDelete(Questions questions, String uuid) {
        questionsRepository.deleteById(uuid);
        return questions ;
    }


}
