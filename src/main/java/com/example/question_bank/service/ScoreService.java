package com.example.question_bank.service;

import com.example.question_bank.config.ImageUtil;
import com.example.question_bank.entity.*;
import com.example.question_bank.form.AnswerForm;
import com.example.question_bank.repository.TestedInfoRepository;
import com.example.question_bank.repository.TestedRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoreService implements ScoreInterface {

    private final TestedRepository testedRepository;
    private final TestedInfoRepository testedInfoRepository;
    @Autowired
    HttpSession session;

    @Override
    public List<Long> questionIdList(String uuid, List<Integer> answerList) {

        List<Long> questionIdList = testedRepository.findIdByUuid(uuid);
        for (int i = 0; i < questionIdList.size(); i++) {
            long id = questionIdList.get(i);
            Optional<Tested> testedOptional = testedRepository.findById(id);
            if (testedOptional.isPresent()) {
                Tested t = testedOptional.get();
                int answer = answerList.get(i);
                t.setAnswer(answer);
                testedRepository.save(t);
            }
        }
        return questionIdList;
    }
    @Override
    public List<Long> questionjIdList(String uuid, List<String> answerjList) {

        List<Long> questionjIdList = testedRepository.findIdByUuid(uuid);
        for (int i = 0; i < questionjIdList.size(); i++) {
            long id = questionjIdList.get(i);
            Optional<Tested> testedOptional = testedRepository.findById(id);
            if (testedOptional.isPresent()) {
                Tested t = testedOptional.get();
                String answer = answerjList.get(i);
                t.setAnswerj(answer);
                testedRepository.save(t);
            }
        }
        return questionjIdList;
    }
    @Override
    public List<Long> questionsIdList(String uuid, List<String> answersList) {

        List<Long> questionsIdList = testedRepository.findIdByUuid(uuid);
        for (int i = 0; i < questionsIdList.size(); i++) {
            long id = questionsIdList.get(i);
            Optional<Tested> testedOptional = testedRepository.findById(id);
            if (testedOptional.isPresent()) {
                Tested t = testedOptional.get();
                String answer = answersList.get(i);
                t.setAnswers(answer);
                testedRepository.save(t);
            }
        }
        return questionsIdList;
    }
    @Override
    public List<Long> questionImageIdList(String uuid, List<Integer> answerImageList) {

        List<Long> questionImageIdList = testedRepository.findIdByUuid(uuid);
        for (int i = 0; i < questionImageIdList.size(); i++) {
            long id = questionImageIdList.get(i);
            Optional<Tested> testedOptional = testedRepository.findById(id);
            if (testedOptional.isPresent()) {
                Tested t = testedOptional.get();
                int answer = answerImageList.get(i);
                t.setAnswerimage(answer);
                testedRepository.save(t);
            }
        }
        return questionImageIdList;
    }
    @Override
    public int QuestionHitCount(String uuid, List<Long> questionIdList) {

        int QuestionHitCount = 0;

        for (int i = 0; i < questionIdList.size(); i++) {    //id리스트 변수로 입력해서 히트카운트 출력하는 메소드 생각
            long id = questionIdList.get(i);
            Optional<Tested> testedOptional = testedRepository.findById(id);
            if (testedOptional.isPresent()) {
                Tested t = testedOptional.get();
                int answer = t.getAnswer();
                Question q = t.getQuestion();
                int correctAnswer = q.getCorrect_answer();

                if (answer == correctAnswer) {
                    QuestionHitCount++;
                }
                Optional<TestedInfo> testedInfoOptional = testedInfoRepository.findById(uuid);
                if (testedInfoOptional.isPresent()) {
                    TestedInfo w = testedInfoOptional.get();
                    w.setCorrectAnswerCount(QuestionHitCount);
                    testedInfoRepository.save(w);
                }
            }
        }
        return QuestionHitCount;
    }
    @Override
    public int QuestionjHitCount(String uuid, List<Long> questionjIdList) {

        int hitCount1 = 0;
        for (int j = 0; j < questionjIdList.size(); j++) {
            long id1 = questionjIdList.get(j);
            Optional<Tested> testedOptional1 = testedRepository.findById(id1);
            if (testedOptional1.isPresent()) {
                Tested r = testedOptional1.get();
                String answerj = r.getAnswerj();
                Questionj y = r.getQuestionj();
                String correctjanswer = y.getCorrect_answer();
                List<String> splitList = new ArrayList<>();
                String[] split = correctjanswer.split(",");
                for (int l = 0; l < split.length; l++) {
                    splitList.add(split[l]);
                    String splitAnswer = splitList.get(l);

                    if (Objects.equals(answerj, splitAnswer)) {
                        hitCount1++;
                    }
                }
                Optional<TestedInfo> testedInfoOptional1 = testedInfoRepository.findById(uuid);
                if (testedInfoOptional1.isPresent()) {
                    TestedInfo w = testedInfoOptional1.get();
                    w.setCorrectjAnswerCount(hitCount1);
                    testedInfoRepository.save(w);
                }
            }
        }
        return hitCount1;
    }
    @Override
    public int QuestionImageHitCount(String uuid, List<Long> questionImageIdList) {

        int hitCount0 = 0;

        for (int i = 0; i < questionImageIdList.size(); i++) {
            long id = questionImageIdList.get(i);
            Optional<Tested> testedOptional = testedRepository.findById(id);
            if (testedOptional.isPresent()) {
                Tested t = testedOptional.get();
                int answer = t.getAnswerimage();
                QuestionImage q = t.getQuestionimage();
                int correctAnswer = q.getCorrect_answer();

                if (answer == correctAnswer) {
                    hitCount0++;
                }
                Optional<TestedInfo> testedInfoOptional = testedInfoRepository.findById(uuid);
                if (testedInfoOptional.isPresent()) {
                    TestedInfo w = testedInfoOptional.get();
                    w.setCorrectAnswerCount(hitCount0);
                    testedInfoRepository.save(w);
                }
            }
        }  return hitCount0;
    }
    @Override
    public List<String> scoreSave(AnswerForm answerForm,int hitCountImage, int hitCount, int hitCountj, String uuid,Model model){
        String name=answerForm.getName();
        String jum="점";
        int scoreimage=hitCountImage*5;
        int score=hitCount*5;
        int scorej=hitCountj*5;
        int score_total=score+scorej+scoreimage;
        List<String> testedInfoList =testedInfoRepository.findIdByUuidQuestion(uuid);
        for (int i = 0; i < testedInfoList.size(); i++) {
            String id = testedInfoList.get(i);
            Optional<TestedInfo> testedInfoOptional = testedInfoRepository.findById(id);
            if (testedInfoOptional.isPresent()) {
                TestedInfo u = testedInfoOptional.get();
                u.setCorrectAnswerScore(score);
                u.setCorrectimageAnswerScore(scoreimage);
                u.setCorrectjAnswerScore(scorej);
                u.setScoreTotal(score_total);
                u.setUserName(name);
                testedInfoRepository.save(u);
                System.out.println("시험 점수 저장 완료");
            }
        }

        model.addAttribute("score",score);
        model.addAttribute("scoreimage",scoreimage);
        model.addAttribute("scorej",scorej);
        model.addAttribute("score_total",score_total);
        model.addAttribute("jum",jum);
        return testedInfoList;
    }
    @Override
    public void scoreModel(List<Question> questionList, List<Questionj> questionjList, List<Questions>questionsList
    , List<QuestionImage>questionImageList, Model model){

        model.addAttribute("questionsList",questionsList);
        model.addAttribute("questionImageList",questionImageList);
        model.addAttribute("questionList", questionList);
        model.addAttribute("questionjList", questionjList);
        model.addAttribute("imgUtil", new ImageUtil());
    }
}