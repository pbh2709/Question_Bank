package com.example.question_bank.controller;

import com.example.question_bank.config.ImageUtil;
import com.example.question_bank.entity.*;
import com.example.question_bank.form.*;
import com.example.question_bank.repository.*;
import com.example.question_bank.service.AnswerService;
import com.example.question_bank.service.GradeService;
import com.example.question_bank.service.ScoreService;
import com.example.question_bank.service.TestedService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class test2Controller {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionimageRepository questionimageRepository;

    @Autowired
    QuestionjRepository questionjRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    TestedRepository testedRepository;
    @Autowired
    TestedInfoRepository testedInfoRepository;

    @Autowired
    HttpSession session;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TestedService testedService;

    @Autowired
    AnswerService answerService;

    @Autowired
    GradeService gradeService;

    @Autowired
    ScoreService scoreService;


    @GetMapping("question_test")
    public String question_test(
            AnswerForm answerForm,
            Model model
    ) {
        List<Question> questionList = testedService.testedQuestion(model);
        List<Questionj> questionjList = testedService.testedQuestionj(model);
        List<Questions> questionsList = testedService.testedQuestions(model);
        List<QuestionImage> questionImageList = testedService.testedQuestionImage(model);


        // 시험지 테이블 작성
        UUID uuidShare = UUID.randomUUID(); // 공통 uuid


        Tested tested2 = testedService.testedQuestionSave(uuidShare);
        Tested tested4 = testedService.testedQuestionjSave(uuidShare);
        Tested tested3 = testedService.testedQuestionsSave(uuidShare);
        Tested tested1 = testedService.testedQuestionImageSave(uuidShare);//서술형

        TestedInfo testedInfo = new TestedInfo().builder()
                .uuid1(tested3.getTestedUuid()) //주관식
                .uuid(tested1.getTestedUuid())  //객관식
                .uuid0(tested2.getTestedUuid())  //이미지
                .uuid2(tested4.getTestedUuid())  //서술형
                .uuid3(uuidShare.toString()) //공용
                .questionCount(questionList.size())
                .questionimageCount(questionImageList.size())
                .questionsCount(questionsList.size())
                .questionjCount(questionjList.size())
                .retest("x")
                .grading("x")
                .correctsAnswerScore("미채점")
                .build();
        testedInfoRepository.save(testedInfo);

        if (testedInfo != null) {
            System.out.println("시험 정보 생성");

        }

        session.setAttribute("testedUuid0", tested1.getTestedUuid()); //이미지
        session.setAttribute("testedUuid1", tested4.getTestedUuid()); //주관식
        session.setAttribute("testedUuid", tested2.getTestedUuid());  //객관식
        session.setAttribute("testedUuid2", tested3.getTestedUuid()); //서술형


        return "question_test";
    }

    @PostMapping("question_test")
    public String question_test(
            Model model,
            AnswerForm answerForm
    ) {
        List<Integer> answerList = answerService.answerListReturn(answerForm);
        List<String> answerjList = answerService.answerjListReturn(answerForm);
        List<String> answersList = answerService.answersListReturn(answerForm);
        List<Integer> answerImageList = answerService.answerImageListReturn(answerForm);


        String testedUuid = (String) session.getAttribute("testedUuid");
        String testedUuid0 = (String) session.getAttribute("testedUuid0");
        String testedUuid1 = (String) session.getAttribute("testedUuid1");
        String testedUuid2 = (String) session.getAttribute("testedUuid2");

        List<Long> questionIdList = scoreService.questionIdList(testedUuid, answerList);
        List<Long> questionjIdList = scoreService.questionjIdList(testedUuid1, answerjList);
        List<Long> questionsIdList = scoreService.questionsIdList(testedUuid2, answersList);
        List<Long> questionImageIdList = scoreService.questionImageIdList(testedUuid0, answerImageList);

        int hitCount = scoreService.QuestionHitCount(testedUuid, questionIdList);
        int hitCountj = scoreService.QuestionjHitCount(testedUuid1, questionjIdList);
        int hitCountImage = scoreService.QuestionImageHitCount(testedUuid0, questionImageIdList);

        scoreService.scoreSave(answerForm, hitCountImage,hitCount,hitCountj, testedUuid, model);

        String name=answerForm.getName();
        int scoreimage=hitCountImage*5;
        int score=hitCount*5;
        int scorej=hitCountj*5;
        int score_total=score+scorej+scoreimage;


        System.out.println("객관식 정답수는"+hitCount +"주관식 정답수는"+hitCountImage);
        System.out.println("객관식 점수는"+score +"주관식 점수는"+scorej);


        List<Questionj> questionjList = (List) session.getAttribute("questionjList");
        List<Question> questionList = (List) session.getAttribute("questionList");
        List<QuestionImage> questionImageList = (List) session.getAttribute("questionImageList");
        List<Questions> questionsList = (List) session.getAttribute("questionsList");
        scoreService.scoreModel(questionList, questionjList, questionsList, questionImageList, model);



        return "question_test";
    }

    @GetMapping("tested_list")
    public String tested_list(
            Model model
    ) {
        TestedInfo testedInfo = new TestedInfo();
        gradeService.listTestedInfo(testedInfo, model);

        return "tested_list";
    }

    @GetMapping("testeds_list") //서술형 채점 페이지
    public String testeds_list(
            Model model
    ) {

        TestedInfo testedInfo = new TestedInfo();
        gradeService.listTestedInfo(testedInfo, model);

        return "testeds_list";
    }

    @GetMapping("tested_grading/{uuid3}")
    public String tested_grading(
            @PathVariable String uuid3,
            Model model,
            AnswerForm answerForm,
            GradingForm gradingForm


    ) {
        Questions questions = new Questions();
        gradeService.editStartQuestionsGrading(questions, uuid3, gradingForm, model);


        return "tested_grading";
    }

    @PostMapping("/tested_grading")
    public String tested_grading(
            Model model,
            TestedForm testedForm,
            @Valid GradingForm gradingForm,
            BindingResult bindingResult
    ) {
        TestedInfo testedInfo = new TestedInfo();
        if (!bindingResult.hasErrors()) {
            TestedInfo t = gradeService.editEndQuestionsGrading(testedInfo, gradingForm, model);

            if (t != null) {


                return "redirect:/testeds_list";
            }
        }
        return "tested_grading";
    }


    @GetMapping("question_retest/{uuid3}")
    public String question_retest(
            @PathVariable String uuid3,
            Model model,
            AnswerForm answerForm
    ) {
        TestedInfo testedInfo1 = new TestedInfo();


        testedService.retest(testedInfo1, uuid3, answerForm, model);
        String name = answerForm.getName();


        List<Question> questionList = testedService.testedQuestion(model);
        List<Questionj> questionjList = testedService.testedQuestionj(model);
        List<Questions> questionsList = testedService.testedQuestions(model);
        List<QuestionImage> questionImageList = testedService.testedQuestionImage(model);

        UUID uuid4 = UUID.randomUUID();  //재시험 공통 uuid


        Tested tested2 = testedService.testedQuestionSave(uuid4);
        Tested tested4 = testedService.testedQuestionjSave(uuid4);
        Tested tested3 = testedService.testedQuestionsSave(uuid4);
        Tested tested1 = testedService.testedQuestionImageSave(uuid4);

        new TestedInfo();
        TestedInfo testedInfo = TestedInfo.builder()
                .uuid1(tested3.getTestedUuid()) //주관식
                .uuid(tested1.getTestedUuid())  //객관식
                .uuid0(tested2.getTestedUuid())  //이미지
                .uuid2(tested4.getTestedUuid())  //서술형
                .uuid3(uuid4.toString())
                .userName(name)
                .questionCount(questionList.size())
                .questionimageCount(questionImageList.size())
                .questionjCount(questionjList.size())
                .retest("재시험자")
                .grading("미채점")
                .build();


        testedInfoRepository.save(testedInfo);
        if (testedInfo != null) {
            System.out.println("재시험 정보 생성");

        }
        session.setAttribute("uuid3", uuid3);
        session.setAttribute("testedUuid0", tested1.getTestedUuid()); //이미지
        session.setAttribute("testedUuid1", tested4.getTestedUuid()); //주관식
        session.setAttribute("testedUuid", tested2.getTestedUuid());  //객관식
        session.setAttribute("testedUuid2", tested3.getTestedUuid()); //서술형
        System.out.println(name);

        return "question_retest";
    }

    @PostMapping("question_retest")
    public String question_retest(
            Model model,
            AnswerForm answerForm
    ) {

        List<Integer> answerList = answerService.answerListReturn(answerForm);
        List<String> answerjList = answerService.answerjListReturn(answerForm);
        List<String> answersList = answerService.answersListReturn(answerForm);
        List<Integer> answerImageList = answerService.answerImageListReturn(answerForm);


        String testedUuid = (String) session.getAttribute("testedUuid");
        String testedUuid0 = (String) session.getAttribute("testedUuid0");
        String testedUuid1 = (String) session.getAttribute("testedUuid1");
        String testedUuid2 = (String) session.getAttribute("testedUuid2");

        List<Long> questionIdList = scoreService.questionIdList(testedUuid, answerList);
        List<Long> questionjIdList = scoreService.questionjIdList(testedUuid1, answerjList);
        List<Long> questionsIdList = scoreService.questionsIdList(testedUuid2, answersList);
        List<Long> questionImageIdList = scoreService.questionImageIdList(testedUuid0, answerImageList);


        String uuid3 = (String) session.getAttribute("uuid3");


        testedService.testedDelete(uuid3);

        testedService.testedInfoDelete(uuid3);

        int hitCount = scoreService.QuestionHitCount(testedUuid,questionIdList);
        int hitCountj = scoreService.QuestionjHitCount(testedUuid1,questionjIdList);
        int hitCountImage = scoreService.QuestionImageHitCount(testedUuid0,questionImageIdList);

        scoreService.scoreSave(answerForm, hitCountImage,hitCount,hitCountj, testedUuid, model);


        List<Questionj> questionjList = (List) session.getAttribute("questionjList");
        List<Question> questionList = (List) session.getAttribute("questionList");
        List<QuestionImage> questionImageList = (List) session.getAttribute("questionImageList");
        List<Questions> questionsList = (List) session.getAttribute("questionsList");
        scoreService.scoreModel(questionList, questionjList, questionsList, questionImageList, model);

        System.out.println("객관식 정답수는"+hitCount +"주관식 정답수는"+hitCountImage);


        return "question_retest";
    }


    @GetMapping("tested_view/{uuid3}")
    public String tested_view(
            @PathVariable String uuid3,
            Model model,
            AnswerForm answerForm

    ) {
        List<QuestionForm>questionList=new ArrayList<QuestionForm>();
        List<QuestionimageForm>questionimageList=new ArrayList<QuestionimageForm>();
        List<TestedForm> testedList = new ArrayList<TestedForm>();
        var questionsList =new  ArrayList<QuestionsForm>();
        testedService.testView(uuid3,testedList,questionList,questionimageList,questionsList ,model);

        return "tested_view";
    }
}

