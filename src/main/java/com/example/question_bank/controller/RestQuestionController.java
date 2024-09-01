package com.example.question_bank.controller;

import com.example.question_bank.entity.*;
import com.example.question_bank.form.AnswerForm;
import com.example.question_bank.form.InputForm;
import com.example.question_bank.form.ScoreDto;
import com.example.question_bank.repository.QuestionRepository;
import com.example.question_bank.repository.TestedInfoRepository;
import com.example.question_bank.repository.TestedRepository;
import com.example.question_bank.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RestQuestionController {

    private final QuestionService questionService;

    private final ListService listService;

    private final DeleteService deleteService;

    private final QuestionEditService questionEditService;

    private final TestedRepository testedRepository;

    private final QuestionRepository questionRepository;

    private final TestedService testedService;

    private final TestedInfoRepository testedInfoRepository;

    private final AnswerService answerService;

    private final ScoreService scoreService;

    @PostMapping(value = "/QuestionAddPage")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void SaveQuestion(@RequestBody InputForm inputForm) {

        String id = UUID.randomUUID().toString();

        inputForm.setUuid(id);
        Question question = new Question();
        questionService.saveQuestion(question, inputForm);
        System.out.println(inputForm)

        ;

    }

    @PostMapping(value = "/TestDto")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void Saveas(@RequestBody InputForm questionDto) {

        String id = UUID.randomUUID().toString();

        System.out.println(questionDto);

    }

    @GetMapping("/TestDto")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Question> QuestionList(Question question, Model model) {


        return listService.listQuestion(question, model);
        //리스트를 보내줘서 문제 목록에 문제 정보를 보내준다.
    }

    @GetMapping("/Question_Edit/{uuid}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List <Question> questionEditList(@PathVariable String uuid, Question question, InputForm inputForm, Model model) {
        inputForm.setUuid(uuid);
        return listService.listQuestionEdit(inputForm);
    }

    @PostMapping(value = "/Question_Edit/{uuid}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void SaveQuestionEdit(@RequestBody InputForm inputForm ,@PathVariable String uuid,Model model) {



        inputForm.setUuid(uuid);
        Question question = new Question();
        questionEditService.editEndQuestion(question,inputForm,model);
        System.out.println(inputForm)

        ;

    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/QuestionDelete")
    public void deleteById(String uuid, InputForm inputForm) {
        var idList = testedRepository.findIdByQuestionUuid(uuid);
        if (!idList.isEmpty()) {

        }
        inputForm.setUuid(uuid);
        Question question = new Question();
        String uuid1 = inputForm.getUuid();
        deleteService.questionDelete(question, uuid1);


        // 문제를 삭제해주는 컨트롤러이나 왜인지 그냥 받아온 uuid는 컬럼을 조회하지 못하는
        //문제가 발생하여 폼에 있는 변수에 값을 넣어서 우회해서 메서드를 실핼해야 했다.

    }

    @GetMapping("/TestInfo")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<TestedInfo> TestedInfoList(TestedInfo testedInfo, Model model) {




        return listService.listTestedInfo();

        // 문제를 삭제해주는 컨트롤러이나 왜인지 그냥 받아온 uuid는 컬럼을 조회하지 못하는
        //문제가 발생하여 폼에 있는 변수에 값을 넣어서 우회해서 메서드를 실핼해야 했다.

    }




        @GetMapping("/Testing")
        @CrossOrigin(origins = "*", allowedHeaders = "*")
        public List <Question> Testing (Question question, Model model) {




            TestedInfo testedInfo = new TestedInfo();

            UUID uuidShare = UUID.randomUUID();
            String uuids = uuidShare.toString();
            Tested testedQ = testedService.testedQuestionSave(testedInfo, uuidShare);
            Tested testedJ = testedService.testedQuestionjSave(testedInfo,uuidShare);
            Tested testedS = testedService.testedQuestionsSave(testedInfo,uuidShare);
            Tested testedImage = testedService.testedQuestionImageSave(testedInfo,uuidShare);//서술형

            //위의 역할은 시험지를 생성했을 때 점수를 채점 할 수 있게 문제 갯수나 문제 유형 테이블
            // 아이디 값을 저장하는 기능

                   List<Question>questionList1 = questionRepository.findAll();
                   for(int i=0; i<=1;i++) {
                       Question question123 = questionList1.get(i);
                       String uuid = question123.getUuid();
                       Optional<Question> questionOptional = questionRepository.findById(uuid);
                       if (questionOptional.isPresent()) {
                            question123=questionOptional.get();
                               question123.setTestuuid(uuids);
                           questionRepository.save(question123);
                       }
                   }



            List<Question> questionList = testedService.testedQuestion(model);
            List<Questionj> questionjList = testedService.testedQuestionj(model);
            List<Questions> questionsList = testedService.testedQuestions(model);
            List<QuestionImage> questionImageList = testedService.testedQuestionImage(model);



            TestedInfo Tested = testedInfo.builder()
                    .uuid1(testedS.getTestedUuid()) //주관식
                    .uuid(testedImage.getTestedUuid())  //객관식
                    .uuid0(testedQ.getTestedUuid())  //이미지
                    .uuid2(testedJ.getTestedUuid())  //서술형
                    .uuid3(uuidShare.toString()) //공용
                .questionCount(questionList.size())
                .questionimageCount(questionImageList.size())
                .questionsCount(questionsList.size())
                .questionjCount(questionjList.size())
                    .retest("x")
                    .grading("x")
                    .correctsAnswerScore("미채점")
                    .build();
            testedInfoRepository.save(Tested);
            //시험지 정보에 공통으로 들어갈 기본 값을 저장해줌 그렇기에
            //굳이 서비스로 로직을 분리하지 않아도 변경될  것이 적음



//        session.setAttribute("testedUuid0", tested1.getTestedUuid()); //이미지
//        session.setAttribute("testedUuid1", tested4.getTestedUuid()); //주관식
//        session.setAttribute("testedUuid", tested2.getTestedUuid());  //객관식
//        session.setAttribute("testedUuid2", tested3.getTestedUuid()); //서술형


            return questionList;

        }
    @PostMapping(value = "/Score")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void ScoreTest(@RequestBody AnswerForm answerForm,
                          Model model) {

        int count = answerForm.getCount();
        String name=answerForm.getName();
        String uuid =answerForm.getTestUuid();


        List<Integer> answerList = answerService.answerListReturn(answerForm);
//        List<String> answerjList = answerService.answerjListReturn(answerForm);
//        List<String> answersList = answerService.answersListReturn(answerForm);
//        List<Integer> answerImageList = answerService.answerImageListReturn(answerForm);
 //  시험지 제출로 내가 쓴 정답을 유형별로 리스트에 담아서 저장

//        String testedUuid = (String) session.getAttribute("testedUuid");
//        String testedUuid0 = (String) session.getAttribute("testedUuid0");
//        String testedUuid1 = (String) session.getAttribute("testedUuid1");
//        String testedUuid2 = (String) session.getAttribute("testedUuid2");
//
        List<Long> questionIdList = scoreService.questionIdList(uuid, answerList);
//        List<Long> questionjIdList = scoreService.questionjIdList(testedUuid1, answerjList);
//        List<Long> questionsIdList = scoreService.questionsIdList(testedUuid2, answersList);
//        List<Long> questionImageIdList = scoreService.questionImageIdList(testedUuid0, answerImageList);
////위의  uuid는 시험지를 생성했을 때 생성된 문제들 중에서 같은 유형의 문제들끼리 값을 공유하는 uuid이다/
//        //이 값으로 내가 본 시험에 해당 문제 열에 내 정답을 저장한다.
//
        int hitCount = scoreService.QuestionHitCount(uuid, questionIdList);
//        int hitCountj = scoreService.QuestionjHitCount(testedUuid1, questionjIdList);
//        int hitCountImage = scoreService.QuestionImageHitCount(testedUuid0, questionImageIdList);
//정답을 저장하면 테스티드 테이블은 다른 문제 테이블과 조인 한 상태기 때문에 내가 저장한 답과 문제의 정답을
        //비교해서 점수를 채점하는 것이 가능해진다.
        //이 걸 잘 이용하면 화면에 맞춘 문제와 못 맞춘 문제를 표시하는 것 도 가능할 것
        //일단은 시간이 없으니 나중에
        scoreService.scoreSave(answerForm, hitCount,uuid,model);
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





    }
}






