package com.example.question_bank.service;

import com.example.question_bank.entity.Questions;
import com.example.question_bank.entity.TestedInfo;
import com.example.question_bank.form.GradingForm;
import org.springframework.ui.Model;

import java.util.List;

public interface GradeInterface {

    public Questions editStartQuestionsGrading(Questions questions, String uuid, GradingForm gradingForm, Model model);

    public List<TestedInfo> listTestedInfo(TestedInfo testedInfo, Model model);

    public TestedInfo editEndQuestionsGrading(TestedInfo testedInfo, GradingForm gradingForm, Model model);
}
