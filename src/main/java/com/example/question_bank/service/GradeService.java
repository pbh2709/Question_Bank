package com.example.question_bank.service;

import com.example.question_bank.entity.*;
import com.example.question_bank.form.GradingForm;
import com.example.question_bank.form.QuestionForm;
import com.example.question_bank.form.QuestionsForm;
import com.example.question_bank.form.TestedForm;
import com.example.question_bank.repository.TestedInfoRepository;
import com.example.question_bank.repository.TestedRepository;
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
public class GradeService implements GradeInterface {

    private final TestedInfoRepository testedInfoRepository;
    private final TestedRepository testedRepository;

   @Override
    public List<TestedInfo> listTestedInfo(TestedInfo testedInfo, Model model) {

        List<TestedInfo> testedInfoList = testedInfoRepository.findAll(Sort.by("testedAt").descending());
        model.addAttribute("testedInfoList", testedInfoList);

        return testedInfoList;
    }
    @Override
    public Questions editStartQuestionsGrading(Questions questions, String uuid, GradingForm gradingForm, Model model) {
        List<Long> idList = testedRepository.findIdByUuid1(uuid);

        var questionsList = new ArrayList<QuestionsForm>();
        for (int i = 0; i < idList.size(); i++) {
            long id = idList.get(i);
            Optional<Tested> testedOptional = testedRepository.findById(id);
            if (testedOptional.isPresent()) {
                Tested t = testedOptional.get();

                Questions qs = t.getQuestions();
                if (qs != null) {
                    QuestionsForm questionsForm = new QuestionsForm()
                            .builder()
                            .title(qs.getTitle())
                            .my_answer(t.getAnswers())
                            .build();
                    questionsList.add(questionsForm);

                }
                model.addAttribute("questionsList", questionsList);

            }
            model.addAttribute("gradingForm", gradingForm);
        }
        return questions;
    }
    @Override
    public TestedInfo editEndQuestionsGrading(TestedInfo testedInfo, GradingForm gradingForm, Model model) {

        var testedInfoList = testedInfoRepository.findIdByUuid(gradingForm.getUuid3());
        for (int i = 0; i < testedInfoList.size(); i++) {
            String id = testedInfoList.get(i);
            var testedInfoOptional = testedInfoRepository.findById(id);
            if (testedInfoOptional.isPresent()) {
                TestedInfo t = testedInfoOptional.get();
                String ds = gradingForm.getCorrectsAnswerScore();
                t.setCorrectsAnswerScore(ds);
                t.setGrading("채점 완료");
                int score = t.getCorrectAnswerScore() + t.getCorrectjAnswerScore();
                int scores = Integer.parseInt(ds) + score;
                t.setScoreTotal(scores);
                testedInfoRepository.save(t);
            }

        }
        return testedInfo;
    }
}
