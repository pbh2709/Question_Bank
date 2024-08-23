package com.example.question_bank.controller;

import com.example.question_bank.config.ImageUtil;
import com.example.question_bank.entity.*;
import com.example.question_bank.form.*;
import com.example.question_bank.repository.*;
import com.example.question_bank.service.TestedService;
import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.data.domain.Sort;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.*;
//
//public class TestController {
//
//    @Autowired
//    HttpSession session;
//
//    @Autowired
//    ApplicationContext applicationContext;
//
//    private   TestedService testedService;
//    private  TestedRepository testedRepository;
//
//    private  TestedInfoRepository testedInfoRepository;
//
//    private  QuestionRepository questionRepository;
//
//    private  QuestionjRepository questionjRepository;
//
//    private  QuestionsRepository questionsRepository;
//
//    private  QuestionimageRepository questionimageRepository;
//
//    @GetMapping("question_test")
//    public String question_test(
//            AnswerForm answerForm,
//            Model model
//    ) {
////        List<Question> questionList = questionRepository.findAll();
////        model.addAttribute("questionList", questionList);
////        List<Questionj> questionjList = questionjRepository.findAll();
////        model.addAttribute("questionjList", questionjList);
////        List<QuestionImage> questionImageList = questionimageRepository.findAll();
////        model.addAttribute("questionImageList", questionImageList);
////        List<Questions> questionsList = questionsRepository.findAll();
////        model.addAttribute("questionsList", questionsList);
////        Question question=new Question();
////        testedService.testedQuestion(question,model);
////        Questionj questionj=new Questionj();
////        testedService.testedQuestionj(questionj,model);
////        QuestionImage questionImage=new QuestionImage();
////        testedService.testedQuestionImage(questionImage,model);
////        Questions questions=new Questions();
////        testedService.testedQuestions(questions,model);
//
//        List<Question> questionList = questionRepository.findAll();
//        List<Questionj>questionjList=questionjRepository.findAll();
//        var questionsList=questionsRepository.findAll();
//        var questionimageList=questionimageRepository.findAll();
//        model.addAttribute("questionList", questionList);
//        model.addAttribute("questionjList",questionjList);
//        model.addAttribute("questionsList",questionsList);
//        model.addAttribute("questionImageList",questionimageList);
//        model.addAttribute("imgUtil", new ImageUtil());
//
//        session.setAttribute("questionList",questionList);
//        session.setAttribute("questionjList",questionjList);
//        session.setAttribute("questionsList",questionsList);
//        session.setAttribute("questionImageList",questionimageList);
//
//
//        // 시험지 테이블 작성
//        UUID uuidShare=UUID.randomUUID(); // 공통 uuid
//
////        UUID uuid = UUID.randomUUID(); //객관식 uuid
////        for (int i = 0; i < questionList.size(); i++) {
////            Question q = questionList.get(i);
////            Tested tested = new Tested();
////            tested.setTestedUuid(uuid.toString());
////            tested.setTested1Uuid(uuid3.toString());
////            tested.setQuestion(q);
////            testedRepository.save(tested);
////        }
//        testedService.testedQuestionSave(uuidShare);
//        testedService.testedQuestionjSave(uuidShare);
//        testedService.testedQuestionsSave(uuidShare);
//        testedService.testedQuestionImageSave(uuidShare);
//        testedService.TestedInfoSave(uuidShare);
////        model.addAttribute("imgUtil", new ImageUtil());
////        UUID uuid0 = UUID.randomUUID(); //이미지 uuid
////        for (int i = 0; i < questionimageList.size(); i++) {
////            QuestionImage q = questionimageList.get(i);
////            Tested tested = new Tested();
////            tested.setTestedUuid(uuid0.toString());
////            tested.setTested1Uuid(uuid3.toString());
////            tested.setQuestionimage(q);
////            testedRepository.save(tested);
////        }
////        UUID uuid1 = UUID.randomUUID();  //주관식 uuid
////        for (int i = 0; i < questionjList.size(); i++) {
////           Questionj s = questionjList.get(i);
////            Tested tested = new Tested();
////            tested.setTestedUuid(uuid1.toString());
////            tested.setTested1Uuid(uuid3.toString());
////            tested.setQuestionj(s);
////            testedRepository.save(tested);
////        }
////        UUID uuid2 = UUID.randomUUID();  //서술형 uuid
////        for (int i = 0; i < questionsList.size(); i++) {
////            Questions s = questionsList.get(i);
////            Tested tested = new Tested();
////            tested.setTestedUuid(uuid2.toString());
////            tested.setTested1Uuid(uuid3.toString());
////            tested.setQuestions(s);
////            testedRepository.save(tested);
////        }
////
////
////        TestedInfo testedInfo = new TestedInfo().builder()
////                .uuid1(uuid1.toString())
////                .uuid(uuid.toString())
////                .uuid0(uuid0.toString())
////                .uuid2(uuid2.toString())
////                .uuid3(uuid3.toString())
////                .questionCount(questionList.size())
////                .questionimageCount(questionimageList.size())
////                .questionsCount(questionsList.size())
////                .questionjCount(questionjList.size())
////                .retest("x")
////                .grading("x")
////                .correctsAnswerScore("미채점")
////                .build();
////
////
////        testedInfoRepository.save(testedInfo);
////        if (testedInfo != null) {
////            System.out.println("시험 정보 생성");
////
////        }
////        session.setAttribute("testedUuid1",uuid1.toString());
////        session.setAttribute("testedUuid", uuid.toString());
////        session.setAttribute("testedUuid2", uuid2.toString());
////        session.setAttribute("testedUuid0", uuid0.toString());
//
//        return "question_test";
//    }
//
//    @PostMapping("question_test")
//    public String question_test(
//            Model model,
//            AnswerForm answerForm
//    ) {
//        List<Integer> answerList = new ArrayList<Integer>(
//                Arrays.asList(
//                        answerForm.getAnswer_1(),
//                        answerForm.getAnswer_2(),
//                        answerForm.getAnswer_3(),
//                        answerForm.getAnswer_4(),
//                        answerForm.getAnswer_5(),
//                        answerForm.getAnswer_6(),
//                        answerForm.getAnswer_7(),
//                        answerForm.getAnswer_8(),
//                        answerForm.getAnswer_9(),
//                        answerForm.getAnswer_10(),
//                        answerForm.getAnswer_11(),
//                        answerForm.getAnswer_12(),
//                        answerForm.getAnswer_13(),
//                        answerForm.getAnswer_14(),
//                        answerForm.getAnswer_15(),
//                        answerForm.getAnswer_16(),
//                        answerForm.getAnswer_17(),
//                        answerForm.getAnswer_18(),
//                        answerForm.getAnswer_19(),
//                        answerForm.getAnswer_20()
//
//                )
//        );
//        List<String> answerListt = new ArrayList<String>(
//                Arrays.asList(
//
//                        answerForm.getAnswerj_1(),
//                        answerForm.getAnswerj_2(),
//                        answerForm.getAnswerj_3(),
//                        answerForm.getAnswerj_4(),
//                        answerForm.getAnswerj_5()
//                )
//        );
//        List<String> answerLists = new ArrayList<String>(
//                Arrays.asList(
//
//                        answerForm.getAnswers_1(),
//                        answerForm.getAnswers_2(),
//                        answerForm.getAnswers_3(),
//                        answerForm.getAnswers_4(),
//                        answerForm.getAnswers_5()
//                )
//        );
//        List<Integer> answerimageList = new ArrayList<Integer>(
//                Arrays.asList(
//                        answerForm.getAnswerimage_1(),
//                        answerForm.getAnswerimage_2(),
//                        answerForm.getAnswerimage_3(),
//                        answerForm.getAnswerimage_4(),
//                        answerForm.getAnswerimage_5()
//
//                )
//        );
//
//
//        String testedUuid = (String) session.getAttribute("testedUuid");
//        String testedUuid0=(String)session.getAttribute("testedUuid0");
//        String testedUuid1=(String)session.getAttribute("testedUuid1");
//        String testedUuid2=(String)session.getAttribute("testedUuid2");
//        List<Long> idList = testedRepository.findIdByUuid(testedUuid);
//        List<Long> idList0 = testedRepository.findIdByUuid(testedUuid0);
//        List<Long> idList1 = testedRepository.findIdByUuid(testedUuid1);
//        List<Long> idList2 = testedRepository.findIdByUuid(testedUuid2);
//
//        for (int i = 0; i < idList0.size(); i++) {
//            long id = idList0.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = answerimageList.get(i);
//                t.setAnswerimage(answer);
//                testedRepository.save(t);
//            }
//        }
//        for (int i = 0; i < idList.size(); i++) {
//            long id = idList.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = answerList.get(i);
//                t.setAnswer(answer);
//                testedRepository.save(t);
//            }
//        }
//        for (int i = 0; i < idList1.size(); i++) {
//            long id = idList1.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                String answer = answerListt.get(i);
//                t.setAnswerj(answer);
//                testedRepository.save(t);
//            }
//        }
//        for (int i = 0; i < idList2.size(); i++) {
//            long id = idList2.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                String answer = answerLists.get(i);
//                t.setAnswers(answer);
//                testedRepository.save(t);
//            }
//        }
//
//        List<Integer> answerList1 = new ArrayList<Integer>();
//        List<Integer> correctList = new ArrayList<Integer>();
//
//        int hitCount = 0;
//
//        for (int i = 0; i < idList.size(); i++) {
//            long id = idList.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = t.getAnswer();
//                Question q = t.getQuestion();
//                int correctAnswer = q.getCorrect_answer();
//
//
//                if (answer == correctAnswer) {
//                    hitCount++;
//                }
//
//
//                Optional<TestedInfo> testedInfoOptional = testedInfoRepository.findById(testedUuid);
//                if (testedInfoOptional.isPresent()) {
//                    TestedInfo w = testedInfoOptional.get();
//                    w.setCorrectAnswerCount(hitCount);
//                    testedInfoRepository.save(w);
//                }
//
//            }
//        }
//        int hitCount0 = 0;
//
//        for (int i = 0; i < idList0.size(); i++) {
//            long id = idList0.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = t.getAnswerimage();
//                QuestionImage q = t.getQuestionimage();
//                int correctAnswer = q.getCorrect_answer();
//
//
//                if (answer == correctAnswer) {
//                    hitCount0++;
//                }
//
//
//                Optional<TestedInfo> testedInfoOptional = testedInfoRepository.findById(testedUuid);
//                if (testedInfoOptional.isPresent()) {
//                    TestedInfo w = testedInfoOptional.get();
//                    w.setCorrectAnswerCount(hitCount0);
//                    testedInfoRepository.save(w);
//                }
//
//            }
//        }
//
//
//                 int hitCount1 = 0;
//                for (int j = 0; j < idList1.size(); j++) {
//                    long id1 = idList1.get(j);
//                    Optional<Tested> testedOptional1 = testedRepository.findById(id1);
//                    if (testedOptional1.isPresent()) {
//                        Tested r = testedOptional1.get();
//                        String answerj = r.getAnswerj();
//                        Questionj y = r.getQuestionj();
//                        String correctjanswer = y.getCorrect_answer();
//                        List<String>splitList=new ArrayList<>();
//                        String[] split = correctjanswer.split(",");
//                        for(int l=0;l<split.length;l++) {
//                            splitList.add(split[l]);
//                            String splitAnswer = splitList.get(l);
//
//                            if (Objects.equals(answerj, splitAnswer)) {
//                                hitCount1++;
//                            }
//                        }
//                            Optional<TestedInfo> testedInfoOptional1 = testedInfoRepository.findById(testedUuid);
//                            if (testedInfoOptional1.isPresent()) {
//                                TestedInfo w = testedInfoOptional1.get();
//                                w.setCorrectjAnswerCount(hitCount1);
//                                testedInfoRepository.save(w);
//                            }
//                }
//        }
//                String name=answerForm.getName();
//                int scoreimage=hitCount0*5;
//                int score=hitCount*5;
//                int scorej=hitCount1*5;
//                int score_total=score+scorej+scoreimage;
//                Optional<TestedInfo>testedInfoOptional =testedInfoRepository.findById(testedUuid);
//                if(testedInfoOptional.isPresent()){
//                    TestedInfo u=testedInfoOptional.get();
//                    u.setCorrectAnswerScore(score);
//                    u.setCorrectimageAnswerScore(scoreimage);
//                    u.setCorrectjAnswerScore(scorej);
//                    u.setScoreTotal(score_total);
//                    u.setUserName(name);
//                    testedInfoRepository.save(u);
//                }
//
//              String jum="점";
//                List<Questionj> questionjList = (List) session.getAttribute("questionjList");
//                List<Question> questionList = (List) session.getAttribute("questionList");
//        List<QuestionImage> questionimageList = (List) session.getAttribute("questionimageList");
//        var questionsList=(List)session.getAttribute("questionsList");
//        model.addAttribute("questionsList",questionsList);
//        model.addAttribute("questionimageList",questionimageList);
//        model.addAttribute("questionList", questionList);
//        model.addAttribute("questionjList", questionjList);
//        model.addAttribute("imgUtil", new ImageUtil());
//        System.out.println("객관식 정답수는"+hitCount +"주관식 정답수는"+hitCount1);
//        System.out.println("객관식 점수는"+score +"주관식 점수는"+scorej);
//        model.addAttribute("score",score);
//        model.addAttribute("scoreimage",scoreimage);
//        model.addAttribute("scorej",scorej);
//        model.addAttribute("score_total",score_total);
//        model.addAttribute("jum",jum);
//        return "question_test";
//    }
//
//    @PostMapping("question_end")
//    public String question_end(
//            AnswerForm answerForm
//    ) {
//
//        List<Integer> answerList = new ArrayList<Integer>(
//                Arrays.asList(
//                        answerForm.getAnswer_1(),
//                        answerForm.getAnswer_2(),
//                        answerForm.getAnswer_3(),
//                        answerForm.getAnswer_4(),
//                        answerForm.getAnswer_5(),
//                        answerForm.getAnswer_6(),
//                        answerForm.getAnswer_7(),
//                        answerForm.getAnswer_8(),
//                        answerForm.getAnswer_9(),
//                        answerForm.getAnswer_10(),
//                        answerForm.getAnswer_11(),
//                        answerForm.getAnswer_12(),
//                        answerForm.getAnswer_13(),
//                        answerForm.getAnswer_14(),
//                        answerForm.getAnswer_15(),
//                        answerForm.getAnswer_16(),
//                        answerForm.getAnswer_17(),
//                        answerForm.getAnswer_18(),
//                        answerForm.getAnswer_19(),
//                        answerForm.getAnswer_20()
//                )
//        );
//
//        String testedUuid = (String) session.getAttribute("testedUuid");
//        List<Long> idList = testedRepository.findIdByUuid(testedUuid);
//      List<String>idlist1 = testedInfoRepository.findIdByUuid1(testedUuid);
//
//        for (int i = 0; i < idList.size(); i++) {
//            long id = idList.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = answerList.get(i);
//                t.setAnswer(answer);
//                testedRepository.save(t);
//            }
//        }
//
//
//
//        return "question_end";
//    }
//
//
//
//    @GetMapping("tested_list")
//    public String tested_list(
//            Model model
//    ) {
//
//        List<TestedInfo> testedInfoList =
//                testedInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "scoreTotal"));
//
//        model.addAttribute("testedInfoList", testedInfoList);
//        return "tested_list";
//    }
//    @GetMapping("testeds_list") //서술형 채점 페이지
//    public String testeds_list(
//            Model model
//    ) {
//
//        List<TestedInfo> testedInfoList =
//                testedInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "scoreTotal"));
//
//        model.addAttribute("testedInfoList", testedInfoList);
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
//    ) {
//
//        List<Long> idList = testedRepository.findIdByUuid1(uuid3);
//
//        List<QuestionForm>questionList=new ArrayList<QuestionForm>();
//        List<TestedForm> testedList = new ArrayList<TestedForm>();
//        var questionsList =new  ArrayList<QuestionsForm>();
//        for (int i = 0; i < idList.size(); i++) {
//            long id = idList.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//
//                Questions qs = t.getQuestions();
//              if (qs != null) {
//                    QuestionsForm questionsForm= new QuestionsForm()
//                            .builder()
//                            .title(qs.getTitle())
//                            .my_answer(t.getAnswers())
//                            .build();
//                    questionsList.add(questionsForm);
//
//                }
//                model.addAttribute("questionsList",questionsList);
//
//            }
//            model.addAttribute("gradingForm", gradingForm);
//        }
//
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
//        if (!bindingResult.hasErrors()) {
//            var testedInfoList = testedInfoRepository.findIdByUuid(gradingForm.getUuid3());
//            for (int i = 0; i < testedInfoList.size(); i++) {
//                String id = testedInfoList.get(i);
//                var testedInfoOptional = testedInfoRepository.findById(id);
//                if (testedInfoOptional.isPresent()) {
//                    TestedInfo t = testedInfoOptional.get();
//                    String ds = gradingForm.getCorrectsAnswerScore();
//                    t.setCorrectsAnswerScore(ds);
//                    t.setGrading("채점 완료");
//                    int score=t.getCorrectAnswerScore() + t.getCorrectjAnswerScore();
//                    int scores=Integer.parseInt(ds)+score;
//                    t.setScoreTotal(scores);
//                    if (t != null) {
//
//                        testedInfoRepository.save(t);
//                        return "redirect:/testeds_list";
//                    }
//                }
//            }
//        }
//
//            return "tested_grading";
//        }
//



//    @GetMapping("question_retest/{uuid3}")
//    public String question_retest(
//            @PathVariable String uuid3,
//            Model model,
//            AnswerForm answerForm
//    ){
//        String name="";
//         var testedInfoList =  testedInfoRepository.findIdByUuid(uuid3);
//         for(int i =0;i<testedInfoList.size();i++){
//             String id=testedInfoList.get(i);
//             Optional<TestedInfo> testedInfoOptional=testedInfoRepository.findById(id);
//             if(testedInfoOptional.isPresent()){
//                 TestedInfo t=testedInfoOptional.get();
//                  name=t.getUserName();
//                  answerForm.setName(name);
//
//             }
//
//         }
//       var questionList = questionRepository.findAll();
//        var questionimageList = questionimageRepository.findAll();
//        var questionjList=questionjRepository.findAll();
//        var questionsList=questionsRepository.findAll();
//        model.addAttribute("questionList", questionList);
//        model.addAttribute("questionimageList", questionimageList);
//        model.addAttribute("questionjList",questionjList);
//        model.addAttribute("questionsList",questionsList);
//        model.addAttribute("imgUtil", new ImageUtil());
//        session.setAttribute("questionimageList",questionimageList);
//        session.setAttribute("questionList",questionList);
//        session.setAttribute("questionjList",questionjList);
//        session.setAttribute("questionsList",questionsList);
//
//        // 시험지 테이블 작성
//        UUID uuid4=UUID.randomUUID();  //재시험 공통 uuid
//
//        UUID uuid0 = UUID.randomUUID();
//        for (int i = 0; i < questionimageList.size(); i++) {
//            QuestionImage q = questionimageList.get(i);
//            Tested tested = new Tested();
//            tested.setTestedUuid(uuid0.toString());
//            tested.setTested1Uuid(uuid4.toString());
//            tested.setQuestionimage(q);
//            testedRepository.save(tested);
//        }
//
//        UUID uuid = UUID.randomUUID();
//        for (int i = 0; i < questionList.size(); i++) {
//            Question q = questionList.get(i);
//            Tested tested = new Tested();
//            tested.setTestedUuid(uuid.toString());
//            tested.setTested1Uuid(uuid4.toString());
//            tested.setQuestion(q);
//            testedRepository.save(tested);
//        }
//        UUID uuid1 = UUID.randomUUID();
//        for (int i = 0; i < questionjList.size(); i++) {
//            Questionj s = questionjList.get(i);
//            Tested tested = new Tested();
//            tested.setTestedUuid(uuid1.toString());
//            tested.setTested1Uuid(uuid4.toString());
//            tested.setQuestionj(s);
//            testedRepository.save(tested);
//        }
//        UUID uuid2 = UUID.randomUUID();  //서술형 uuid
//        for (int i = 0; i < questionsList.size(); i++) {
//            Questions s = questionsList.get(i);
//            Tested tested = new Tested();
//            tested.setTestedUuid(uuid2.toString());
//            tested.setTested1Uuid(uuid4.toString());
//            tested.setQuestions(s);
//            testedRepository.save(tested);
//        }
//
//
//        TestedInfo testedInfo = new TestedInfo().builder()
//                .uuid1(uuid1.toString())
//                .uuid(uuid.toString())
//                .uuid0(uuid0.toString())
//                .uuid3(uuid4.toString())
//                .userName(name)
//                .questionCount(questionList.size())
//                .questionimageCount(questionimageList.size())
//                .questionjCount(questionjList.size())
//                .retest("재시험자")
//                .grading("미채점")
//                .build();
//
//
//        testedInfoRepository.save(testedInfo);
//        if (testedInfo != null) {
//            System.out.println("재시험 정보 생성");
//
//        }
//        session.setAttribute("uuid3",uuid3);
//        session.setAttribute("testedUuid0", uuid0.toString());
//        session.setAttribute("testedUuid1",uuid1.toString());
//        session.setAttribute("testedUuid", uuid.toString());
//        session.setAttribute("testedUuid2", uuid2.toString());
//
//        System.out.println(name);
//
//        return "question_retest";
//    }
//
//    @PostMapping("question_retest")
//    public String question_retest(
//            Model model,
//            AnswerForm answerForm
//    ){
//        List<Integer> answerimageList = new ArrayList<>(
//                Arrays.asList(
//
//                        answerForm.getAnswerimage_1(),
//                        answerForm.getAnswerimage_2(),
//                        answerForm.getAnswerimage_3(),
//                        answerForm.getAnswerimage_4(),
//                        answerForm.getAnswerimage_5()
//                )
//        );
//
//        List<Integer> answerList = new ArrayList<Integer>(
//                Arrays.asList(
//                        answerForm.getAnswer_1(),
//                        answerForm.getAnswer_2(),
//                        answerForm.getAnswer_3(),
//                        answerForm.getAnswer_4(),
//                        answerForm.getAnswer_5(),
//                        answerForm.getAnswer_6(),
//                        answerForm.getAnswer_7(),
//                        answerForm.getAnswer_8(),
//                        answerForm.getAnswer_9(),
//                        answerForm.getAnswer_10(),
//                        answerForm.getAnswer_11(),
//                        answerForm.getAnswer_12(),
//                        answerForm.getAnswer_13(),
//                        answerForm.getAnswer_14(),
//                        answerForm.getAnswer_15(),
//                        answerForm.getAnswer_16(),
//                        answerForm.getAnswer_17(),
//                        answerForm.getAnswer_18(),
//                        answerForm.getAnswer_19(),
//                        answerForm.getAnswer_20()
//
//                )
//        );
//        List<String> answerListt = new ArrayList<String>(
//                Arrays.asList(
//
//                        answerForm.getAnswerj_1(),
//                        answerForm.getAnswerj_2(),
//                        answerForm.getAnswerj_3(),
//                        answerForm.getAnswerj_4(),
//                        answerForm.getAnswerj_5()
//                )
//        );
//        List<String> answerLists = new ArrayList<String>(
//                Arrays.asList(
//
//                        answerForm.getAnswers_1(),
//                        answerForm.getAnswers_2(),
//                        answerForm.getAnswers_3(),
//                        answerForm.getAnswers_4(),
//                        answerForm.getAnswers_5()
//                )
//        );
//
//        String uuid3=(String) session.getAttribute("uuid3");
//        String testedUuid = (String) session.getAttribute("testedUuid");
//        String testedUuid1=(String)session.getAttribute("testedUuid1");
//        String testedUuid2=(String)session.getAttribute("testedUuid2");
//        String testedUuid0=(String)session.getAttribute("testedUuid0");
//        var idList = testedRepository.findIdByUuid(testedUuid);
//        var idList0 = testedRepository.findIdByUuid(testedUuid0);
//        var idList1 = testedRepository.findIdByUuid(testedUuid1);
//        var idList12 = testedRepository.findIdByUuid(testedUuid2);
//        var idList2=testedRepository.findIdByUuid1(uuid3); //엔티티tested의 시험 정보 삭제를 위해
//        var idList3=testedInfoRepository.findIdByUuid(uuid3); //엔티티testedinfo의 시험 정보 삭제를 위해
//
//        for(int i=0;i<idList2.size();i++){   //세션으로 저장해놓은 uuid에 해당되는 시험지를 삭제
//            long id=idList2.get(i);
//            Optional<Tested> testedOptional=testedRepository.findById(id);
//            if(testedOptional.isPresent()){
//                Tested t=testedOptional.get();
//                testedRepository.delete(t);
//            }
//        }
//        for(int i=0;i<idList3.size();i++){  //세션으로 저장해놓은 uuid에 해당되는 시험지를 삭제
//            String id=idList3.get(i);
//            Optional<TestedInfo> testedInfoOptional=testedInfoRepository.findById(id);
//            if(testedInfoOptional.isPresent()){
//                TestedInfo t=testedInfoOptional.get();
//
//                testedInfoRepository.delete(t);
//            }
//        }
//
//        for (int i = 0; i < idList0.size(); i++) {   //재시험 이미지 문제 답 저장
//            long id = idList0.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = answerimageList.get(i);
//                t.setAnswerimage(answer);
//                testedRepository.save(t);
//            }
//        }
//
//        for (int i = 0; i < idList.size(); i++) {  //재시험 객관식 문제 답 저장
//            long id = idList.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = answerList.get(i);
//                t.setAnswer(answer);
//                testedRepository.save(t);
//            }
//        }
//        for (int i = 0; i < idList1.size(); i++) {  //재시험 주관식 문제 답 저장
//            long id = idList1.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                String answer = answerListt.get(i);
//                t.setAnswerj(answer);
//                testedRepository.save(t);
//            }
//        }
//        for (int i = 0; i < idList12.size(); i++) {  //재시험 서술형 문제 답 저장
//            long id = idList12.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                String answer = answerLists.get(i);
//                t.setAnswers(answer);
//                testedRepository.save(t);
//            }
//        }
//
//        List<Integer> answerList1 = new ArrayList<Integer>();
//        List<Integer> correctList = new ArrayList<Integer>();
//
//        int hitCount0 = 0;
//
//        for (int i = 0; i < idList0.size(); i++) {
//            long id = idList0.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = t.getAnswerimage();
//                QuestionImage q = t.getQuestionimage();
//                int correctAnswer = q.getCorrect_answer();
//
//
//                if (answer == correctAnswer) {
//                    hitCount0++;
//                }
//
//
//                Optional<TestedInfo> testedInfoOptional = testedInfoRepository.findById(testedUuid);
//                if (testedInfoOptional.isPresent()) {
//                    TestedInfo w = testedInfoOptional.get();
//                    w.setCorrectAnswerCount(hitCount0);
//                    testedInfoRepository.save(w);
//                }
//
//            }
//        }
//
//
//
//        int hitCount = 0;
//
//        for (int i = 0; i < idList.size(); i++) {
//            long id = idList.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                int answer = t.getAnswer();
//                Question q = t.getQuestion();
//                int correctAnswer = q.getCorrect_answer();
//
//
//                if (answer == correctAnswer) {
//                    hitCount++;
//                }
//
//
//                Optional<TestedInfo> testedInfoOptional = testedInfoRepository.findById(testedUuid);
//                if (testedInfoOptional.isPresent()) {
//                    TestedInfo w = testedInfoOptional.get();
//                    w.setCorrectAnswerCount(hitCount);
//                    testedInfoRepository.save(w);
//                }
//
//            }
//        }
//
//
//        int hitCount1 = 0;
//        for (int j = 0; j < idList1.size(); j++) {
//            long id1 = idList1.get(j);
//            Optional<Tested> testedOptional1 = testedRepository.findById(id1);
//            if (testedOptional1.isPresent()) {
//                Tested r = testedOptional1.get();
//                String answerj = r.getAnswerj();
//                Questionj y = r.getQuestionj();
//                String correctjanswer = y.getCorrect_answer();
//
//                if (Objects.equals(answerj, correctjanswer)) {
//                    hitCount1++;
//                }
//                Optional<TestedInfo> testedInfoOptional1 = testedInfoRepository.findById(testedUuid);
//                if (testedInfoOptional1.isPresent()) {
//                    TestedInfo w = testedInfoOptional1.get();
//                    w.setCorrectjAnswerCount(hitCount1);
//                    testedInfoRepository.save(w);
//                }
//
//
//            }
//
//
//
//        }
//        String name=answerForm.getName();
//        int scoreimage=hitCount0*5;
//        int score=hitCount*5;
//        int scorej=hitCount1*5;
//        int score_total=score+scorej+scoreimage;
//        Optional<TestedInfo>testedInfoOptional =testedInfoRepository.findById(testedUuid);
//        if(testedInfoOptional.isPresent()){
//            TestedInfo u=testedInfoOptional.get();
//            u.setCorrectAnswerScore(score);
//            u.setCorrectimageAnswerScore(scoreimage);
//            u.setCorrectjAnswerScore(scorej);
//            u.setScoreTotal(score_total);
//            testedInfoRepository.save(u);
//        }
//
//
//        var questionjList = (List) session.getAttribute("questionjList");
//        var questionList = (List) session.getAttribute("questionList");
//        var questionimageList = (List) session.getAttribute("questionimageList");
//        var questionsList = (List) session.getAttribute("questionsList");
//        model.addAttribute("questionList", questionList);
//        model.addAttribute("questionjList", questionjList);
//        model.addAttribute("questionsList", questionsList);
//        model.addAttribute("questionimageList", questionimageList);
//        model.addAttribute("imgUtil", new ImageUtil());
//        System.out.println("객관식 정답수는"+hitCount +"주관식 정답수는"+hitCount1);
//        System.out.println("객관식 점수는"+score +"주관식 점수는"+scorej);
//        model.addAttribute("score",score);
//        model.addAttribute("scorej",scorej);
//        model.addAttribute("scoreimage",scoreimage);
//        model.addAttribute("score_total",score_total);
//        return "question_retest";
//    }
//
//
//    @GetMapping("tested_view/{uuid3}")
//    public String tested_view(
//           @PathVariable String uuid3,
//            Model model,
//            AnswerForm answerForm
//
//    ) {
//
//        List<Long> idList = testedRepository.findIdByUuid1(uuid3);
//
//        List<QuestionForm>questionList=new ArrayList<QuestionForm>();
//        List<QuestionimageForm>questionimageList=new ArrayList<QuestionimageForm>();
//        List<TestedForm> testedList = new ArrayList<TestedForm>();
//          var questionsList =new  ArrayList<QuestionsForm>();
//        for (int i = 0; i < idList.size(); i++) {
//            long id = idList.get(i);
//            Optional<Tested> testedOptional = testedRepository.findById(id);
//            if (testedOptional.isPresent()) {
//                Tested t = testedOptional.get();
//                Question q = t.getQuestion();
//                QuestionImage qi=t.getQuestionimage();
//                Questionj qj = t.getQuestionj();
//                Questions qs = t.getQuestions();
//                if(q != null){
//                TestedForm tested = new TestedForm()
//                        .builder()
//                        .title(q.getTitle())
//                        .choice1(q.getChoice1())
//                        .choice2(q.getChoice2())
//                        .choice3(q.getChoice3())
//                        .choice4(q.getChoice4())
//                        .correctAnswer(q.getCorrect_answer())
//                        .answer(t.getAnswer())
//                        .build();
//                testedList.add(tested);
//            } else if (qi !=null) {
//                    QuestionimageForm questionimage=new QuestionimageForm()
//                            .builder()
//                            .title(qi.getTitle())
//                            .image(qi.getImage())
//                            .choice1(qi.getChoice1())
//                            .choice2(qi.getChoice2())
//                            .choice3(qi.getChoice3())
//                            .choice4(qi.getChoice4())
//                            .correct_answer(qi.getCorrect_answer())
//                            .my_answer(qi.getMy_answer())
//                            .build();
//                    questionimageList.add(questionimage);
//                }else if (qj != null) {
//                    QuestionForm question= new QuestionForm()
//                            .builder()
//                            .title(qj.getTitle())
//                            .correct_answer(t.getAnswerj())
//                            .build();
//                    questionList.add(question);
//                } else if (qs != null) {
//                    QuestionsForm questionsForm= new QuestionsForm()
//                            .builder()
//                            .title(qs.getTitle())
//                            .my_answer(t.getAnswers())
//                            .build();
//                    questionsList.add(questionsForm);
//
//                }
//                model.addAttribute("questionimageList", questionimageList);
//                model.addAttribute("imgUtil", new ImageUtil());
//                model.addAttribute("testedList", testedList);
//                model.addAttribute("questionList", questionList);
//                model.addAttribute("questionsList",questionsList);
//            }
//        }
//
//
//        return "tested_view";
//    }
//}
