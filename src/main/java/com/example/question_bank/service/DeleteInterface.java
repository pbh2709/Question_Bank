package com.example.question_bank.service;

import com.example.question_bank.entity.*;

import java.util.List;

public interface DeleteInterface {

    public Question questionDelete(Question question, String uuid);

    public Questionj questionjDelete(Questionj questionj, String uuid);


    public QuestionImage questionImageDelete(QuestionImage questionImage,String uuid);

    public Questions questionsDelete(Questions question, String uuid);
}
