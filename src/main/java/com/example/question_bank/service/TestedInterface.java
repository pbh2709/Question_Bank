package com.example.question_bank.service;

import com.example.question_bank.entity.*;
import com.example.question_bank.form.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.UUID;

public interface TestedInterface {
    public List<Question> testedQuestion(Model model);

    public List<Questionj> testedQuestionj( Model model);

    public  List<QuestionImage> testedQuestionImage( Model model);

    public List<Questions> testedQuestions( Model model);

    public Tested testedQuestionSave(TestedInfo testedInfo,UUID uuidShare,UUID uuidQuestions);

    public Tested testedQuestionjSave(TestedInfo testedInfo, UUID uuidShare);

    public Tested testedQuestionsSave(TestedInfo testedInfo, UUID uuidShare,UUID uuidQuestions);

    public Tested testedQuestionImageSave(TestedInfo testedInfo, UUID uuidShare);

    public TestedInfo TestedInfoSave(UUID uuidShare);

    public String retest(TestedInfo testedInfo, String uuid3, AnswerForm answerForm, Model model);

    public  void testView(String uuid, List<TestedForm> testedList, List<QuestionForm> questionList, List<QuestionimageForm> questionimageList, List<QuestionsForm> questionsList, Model model);

    public Tested testedDelete(String uuid3);
    public TestedInfo testedInfoDelete(String uuid3);
}
