//package com.example.question_bank.controller;
//
//import com.example.question_bank.config.ImageUtil;
//import com.example.question_bank.entity.*;
//import com.example.question_bank.form.*;
//import com.example.question_bank.repository.*;
//import com.example.question_bank.service.AnswerService;
//import com.example.question_bank.service.GradeService;
//import com.example.question_bank.service.ScoreService;
//import com.example.question_bank.service.TestedService;
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.data.domain.Sort;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.*;
//
//@Controller
//public class test2Controller {
//
//    @Autowired
//    QuestionRepository questionRepository;
//
//    @Autowired
//    QuestionimageRepository questionimageRepository;
//
//    @Autowired
//    QuestionjRepository questionjRepository;
//
//    @Autowired
//    QuestionsRepository questionsRepository;
//
//    @Autowired
//    TestedRepository testedRepository;
//    @Autowired
//    TestedInfoRepository testedInfoRepository;
//
//    @Autowired
//    HttpSession session;
//
//    @Autowired
//    ApplicationContext applicationContext;
//
//    @Autowired
//    UserRepository userRepository;
//
////    @Autowired
////    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    TestedService testedService;
//
//    @Autowired
//    AnswerService answerService;
//
//    @Autowired
//    GradeService gradeService;
//
//    @Autowired
//    ScoreService scoreService;
//
//
//    @GetMapping("question_test")
//    public String question_test(
//            AnswerForm answerForm,
//            Model model
//    ) {
//        List<Question> questionList = testedService.testedQuestion(model);
//        List<Questionj> questionjList = testedService.testedQuestionj(model);
//        List<Questions> questionsList = testedService.testedQuestions(model);
//        List<QuestionImage> questionImageList = testedService.testedQuestionImage(model);
//
//        //문제 유형 마다 데이터 베이스에서 파인드 올 해서 모델
//
//
//        // 시험지 테이블 작성
//        UUID uuidShare = UUID.randomUUID(); // 공통 uuid
//
//
//        Tested tested2 = testedService.testedQuestionSave(uuidShare);
//        Tested tested4 = testedService.testedQuestionjSave(uuidShare);
//        Tested tested3 = testedService.testedQuestionsSave(uuidShare);
//        Tested tested1 = testedService.testedQuestionImageSave(uuidShare);//서술형
//
//        //위의 역할은 시험지를 생성했을 때 점수를 채점 할 수 있게 문제 갯수나 문제 유형 테이블
//        // 아이디 값을 저장하는 기능
//
//        TestedInfo testedInfo = new TestedInfo().builder()
//                .uuid1(tested3.getTestedUuid()) //주관식
//                .uuid(tested1.getTestedUuid())  //객관식
//                .uuid0(tested2.getTestedUuid())  //이미지
//                .uuid2(tested4.getTestedUuid())  //서술형
//                .uuid3(uuidShare.toString()) //공용
//                .questionCount(questionList.size())
//                .questionimageCount(questionImageList.size())
//                .questionsCount(questionsList.size())
//                .questionjCount(questionjList.size())
//                .retest("x")
//                .grading("x")
//                .correctsAnswerScore("미채점")
//                .build();
//        testedInfoRepository.save(testedInfo);
//         //시험지 정보에 공통으로 들어갈 기본 값을 저장해줌 그렇기에
//        //굳이 서비스로 로직을 분리하지 않아도 변경될  것이 적음
//
//        if (testedInfo != null) {
//            System.out.println("시험 정보 생성");
//
//        }
//
//        session.setAttribute("testedUuid0", tested1.getTestedUuid()); //이미지
//        session.setAttribute("testedUuid1", tested4.getTestedUuid()); //주관식
//        session.setAttribute("testedUuid", tested2.getTestedUuid());  //객관식
//        session.setAttribute("testedUuid2", tested3.getTestedUuid()); //서술형
//
//
//        return "question_test";
//    }
//
//    @PostMapping("question_test")
//    public String question_test(
//            Model model,
//            AnswerForm answerForm
//    ) {
//        List<Integer> answerList = answerService.answerListReturn(answerForm);
//        List<String> answerjList = answerService.answerjListReturn(answerForm);
//        List<String> answersList = answerService.answersListReturn(answerForm);
//        List<Integer> answerImageList = answerService.answerImageListReturn(answerForm);
// //  시험지 제출로 내가 쓴 정답을 유형별로 리스트에 담아서 저장
//
//        String testedUuid = (String) session.getAttribute("testedUuid");
//        String testedUuid0 = (String) session.getAttribute("testedUuid0");
//        String testedUuid1 = (String) session.getAttribute("testedUuid1");
//        String testedUuid2 = (String) session.getAttribute("testedUuid2");
//
//        List<Long> questionIdList = scoreService.questionIdList(testedUuid, answerList);
//        List<Long> questionjIdList = scoreService.questionjIdList(testedUuid1, answerjList);
//        List<Long> questionsIdList = scoreService.questionsIdList(testedUuid2, answersList);
//        List<Long> questionImageIdList = scoreService.questionImageIdList(testedUuid0, answerImageList);
////위의  uuid는 시험지를 생성했을 때 생성된 문제들 중에서 같은 유형의 문제들끼리 값을 공유하는 uuid이다/
//        //이 값으로 내가 본 시험에 해당 문제 열에 내 정답을 저장한다.
//
//        int hitCount = scoreService.QuestionHitCount(testedUuid, questionIdList);
//        int hitCountj = scoreService.QuestionjHitCount(testedUuid1, questionjIdList);
//        int hitCountImage = scoreService.QuestionImageHitCount(testedUuid0, questionImageIdList);
////정답을 저장하면 테스티드 테이블은 다른 문제 테이블과 조인 한 상태기 때문에 내가 저장한 답과 문제의 정답을
//        //비교해서 점수를 채점하는 것이 가능해진다.
//        //이 걸 잘 이용하면 화면에 맞춘 문제와 못 맞춘 문제를 표시하는 것 도 가능할 것
//        //일단은 시간이 없으니 나중에
//        scoreService.scoreSave(answerForm, hitCountImage,hitCount,hitCountj, testedUuid, model);
////위의  로직으로 산출한 점수를 시험정보 테이블에 저장
//        String name=answerForm.getName();
//        int scoreimage=hitCountImage*5;
//        int score=hitCount*5;
//        int scorej=hitCountj*5;
//        int score_total=score+scorej+scoreimage;
//
//
//        System.out.println("객관식 정답수는"+hitCount +"주관식 정답수는"+hitCountImage);
//        System.out.println("객관식 점수는"+score +"주관식 점수는"+scorej);
//
//
//        List<Questionj> questionjList = (List) session.getAttribute("questionjList");
//        List<Question> questionList = (List) session.getAttribute("questionList");
//        List<QuestionImage> questionImageList = (List) session.getAttribute("questionImageList");
//        List<Questions> questionsList = (List) session.getAttribute("questionsList");
//        scoreService.scoreModel(questionList, questionjList, questionsList, questionImageList, model);
//
//
//
//        return "question_test";
//    }
//
//    @GetMapping("tested_list")
//    public String tested_list(
//            Model model
//    ) {
//        TestedInfo testedInfo = new TestedInfo();
//        gradeService.listTestedInfo(testedInfo, model);
////시험 정보 리스트 모델
//        return "tested_list";
//    }
//
//    @GetMapping("testeds_list") //서술형 채점 페이지
//    public String testeds_list(
//            Model model
//    ) {
//
//        TestedInfo testedInfo = new TestedInfo();
//        gradeService.listTestedInfo(testedInfo, model);
////서술형 채점을 위한 시험 정보만 볼 수 있는 리스트 모델
//        return "testeds_list";
//    }
//
//    @GetMapping("tested_grading/{uuid3}")
//    public String tested_grading(
//            @PathVariable String uuid3,
//            Model model,
//            AnswerForm answerForm,
//            GradingForm gradingForm
//
//
//    ) {
//        Questions questions = new Questions();
//        gradeService.editStartQuestionsGrading(questions, uuid3, gradingForm, model);
////서술형 채점은 관리자 마음대로 이기에 문제를 보고 관리자가 점수를 입력해서 POST로 보내 저장만 해주면 된다.
//        //그래서 문제와 해당 시험지의 서술형 답을 보여주는 페이지
//
//        return "tested_grading";
//    }
//
//    @PostMapping("/tested_grading")
//    public String tested_grading(
//            Model model,
//            TestedForm testedForm,
//            @Valid GradingForm gradingForm,
//            BindingResult bindingResult
//    ) {
//        TestedInfo testedInfo = new TestedInfo();
//        if (!bindingResult.hasErrors()) {
//            TestedInfo t = gradeService.editEndQuestionsGrading(testedInfo, gradingForm, model);
//
//            if (t != null) {
//
//   //채점 점수를 받아서 저장
//                return "redirect:/testeds_list";
//            }
//        }
//        return "tested_grading";
//    }
//
//
//    @GetMapping("question_retest/{uuid3}")
//    public String question_retest(
//            @PathVariable String uuid3,
//            Model model,
//            AnswerForm answerForm
//    ) {
//        TestedInfo testedInfo1 = new TestedInfo();
//
//  //재시험을 위한 페이지로 시험목록에서 0점을 받은 학생들에게만 해당 버튼이 보이도록 설계
//        testedService.retest(testedInfo1, uuid3, answerForm, model);
//        String name = answerForm.getName();
//
//        //새 시험지를 부를고 기존 정보에서 이름 값을 받아 새시험지에 넣어줌
//
//
//        List<Question> questionList = testedService.testedQuestion(model);
//        List<Questionj> questionjList = testedService.testedQuestionj(model);
//        List<Questions> questionsList = testedService.testedQuestions(model);
//        List<QuestionImage> questionImageList = testedService.testedQuestionImage(model);
//
//        //새 시험지를 출력함
//
//        UUID uuid4 = UUID.randomUUID();  //재시험 공통 uuid
//
//
//        Tested tested2 = testedService.testedQuestionSave(uuid4);
//        Tested tested4 = testedService.testedQuestionjSave(uuid4);
//        Tested tested3 = testedService.testedQuestionsSave(uuid4);
//        Tested tested1 = testedService.testedQuestionImageSave(uuid4);
//
//        new TestedInfo();
//        TestedInfo testedInfo = TestedInfo.builder()
//                .uuid1(tested3.getTestedUuid()) //주관식
//                .uuid(tested1.getTestedUuid())  //객관식
//                .uuid0(tested2.getTestedUuid())  //이미지
//                .uuid2(tested4.getTestedUuid())  //서술형
//                .uuid3(uuid4.toString())
//                .userName(name)
//                .questionCount(questionList.size())
//                .questionimageCount(questionImageList.size())
//                .questionjCount(questionjList.size())
//                .retest("재시험자")
//                .grading("미채점")
//                .build();
//
//  //기능은 시험지와 비슷하나 시험 정보에서 재시험자의 이름을 그대로 넣고
//        //재시험자 표시를 해주는 것으로 바뀜
//        testedInfoRepository.save(testedInfo);
//        if (testedInfo != null) {
//            System.out.println("재시험 정보 생성");
//
//        }
//        session.setAttribute("uuid3", uuid3);
//        session.setAttribute("testedUuid0", tested1.getTestedUuid()); //이미지
//        session.setAttribute("testedUuid1", tested4.getTestedUuid()); //주관식
//        session.setAttribute("testedUuid", tested2.getTestedUuid());  //객관식
//        session.setAttribute("testedUuid2", tested3.getTestedUuid()); //서술형
//        System.out.println(name);
//
//        return "question_retest";
//    }
//
//    @PostMapping("question_retest")
//    public String question_retest(
//            Model model,
//            AnswerForm answerForm
//    ) {
//
//        List<Integer> answerList = answerService.answerListReturn(answerForm);
//        List<String> answerjList = answerService.answerjListReturn(answerForm);
//        List<String> answersList = answerService.answersListReturn(answerForm);
//        List<Integer> answerImageList = answerService.answerImageListReturn(answerForm);
//
//
//        String testedUuid = (String) session.getAttribute("testedUuid");
//        String testedUuid0 = (String) session.getAttribute("testedUuid0");
//        String testedUuid1 = (String) session.getAttribute("testedUuid1");
//        String testedUuid2 = (String) session.getAttribute("testedUuid2");
//
//        List<Long> questionIdList = scoreService.questionIdList(testedUuid, answerList);
//        List<Long> questionjIdList = scoreService.questionjIdList(testedUuid1, answerjList);
//        List<Long> questionsIdList = scoreService.questionsIdList(testedUuid2, answersList);
//        List<Long> questionImageIdList = scoreService.questionImageIdList(testedUuid0, answerImageList);
//
//
//        String uuid3 = (String) session.getAttribute("uuid3");
//
//
//        testedService.testedDelete(uuid3);
//
//        testedService.testedInfoDelete(uuid3);
//
//        //채점 부분은 기존 시험과 로직이 달라진 점은 없음
//        //다만 재시험자이기 때문에 기존에 저장한 시험지와 시험지 정보를 폐기 해야함
//        //기존 시험지가 가지고 있던 UUID3값을 이용해 해당 행을 찾아서 삭제해줌
//        //uuid3는 시험지를 생성할 때 모든 문제에 공통으로 부여 했던 uuid값이기에 해당 시험지 값을
//        //전부 찾을 수 있름
//
//        int hitCount = scoreService.QuestionHitCount(testedUuid,questionIdList);
//        int hitCountj = scoreService.QuestionjHitCount(testedUuid1,questionjIdList);
//        int hitCountImage = scoreService.QuestionImageHitCount(testedUuid0,questionImageIdList);
//
//        scoreService.scoreSave(answerForm, hitCountImage,hitCount,hitCountj, testedUuid, model);
//
//
//        List<Questionj> questionjList = (List) session.getAttribute("questionjList");
//        List<Question> questionList = (List) session.getAttribute("questionList");
//        List<QuestionImage> questionImageList = (List) session.getAttribute("questionImageList");
//        List<Questions> questionsList = (List) session.getAttribute("questionsList");
//        scoreService.scoreModel(questionList, questionjList, questionsList, questionImageList, model);
//
//        System.out.println("객관식 정답수는"+hitCount +"주관식 정답수는"+hitCountImage);
//
//
//        return "question_retest";
//    }
//
//
//    @GetMapping("tested_view/{uuid3}")
//    public String tested_view(
//            @PathVariable String uuid3,
//            Model model,
//            AnswerForm answerForm
//
//    ) {
//        List<QuestionForm>questionList=new ArrayList<QuestionForm>();
//        List<QuestionimageForm>questionimageList=new ArrayList<QuestionimageForm>();
//        List<TestedForm> testedList = new ArrayList<TestedForm>();
//        var questionsList =new  ArrayList<QuestionsForm>();
//        testedService.testView(uuid3,testedList,questionList,questionimageList,questionsList ,model);
//       //여기서도 공통 uuid로 시험지 정보를 찾아서 내가 적은 정답을 가져와  페이지에 표시하는 로직을 씀
//        return "tested_view";
//    }
//}
//
