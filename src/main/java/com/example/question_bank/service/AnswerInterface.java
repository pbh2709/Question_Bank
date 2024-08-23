package com.example.question_bank.service;

import com.example.question_bank.form.AnswerForm;

import java.util.List;

public interface AnswerInterface {
    public List<Integer> answerListReturn(AnswerForm answerForm);
    public List<String> answerjListReturn(AnswerForm answerForm);
    public List<String> answersListReturn(AnswerForm answerForm);
    public List<Integer> answerImageListReturn(AnswerForm answerForm);
}
