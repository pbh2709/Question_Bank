package com.example.question_bank.service;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.QuestionImage;
import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import com.example.question_bank.form.AnswerForm;
import org.springframework.ui.Model;

import java.util.List;

public interface ScoreInterface {

    public List<Long> questionIdList  (String uuid, List<Integer> answerList);
    public List<Long> questionjIdList  (String uuid, List<String> answerList);
    public List<Long> questionsIdList  (String uuid, List<String> answerList);
    public List<Long> questionImageIdList  (String uuid, List<Integer> answerList);
    public int QuestionHitCount (String uuid,List<Long> questionIdList);
    public int QuestionjHitCount (String uuid,List<Long> questionjIdList);
    public int QuestionImageHitCount (String uuid,List<Long> questionImageIdList);
    public List<String> scoreSave(AnswerForm answerForm,  int hitCount,  String uuid, Model model);
    public void scoreModel(List<Question> questionList, List<Questionj> questionjList, List<Questions>questionsList
            , List<QuestionImage>questionImageList, Model model);
}
