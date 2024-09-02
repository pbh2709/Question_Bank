package com.example.question_bank.service;

import com.example.question_bank.config.ImageUtil;
import com.example.question_bank.entity.*;
import com.example.question_bank.form.*;
import com.example.question_bank.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor

public class TestedService implements TestedInterface {
    private final QuestionRepository questionRepository;
    private final QuestionjRepository questionjRepository;
    private final QuestionimageRepository questionimageRepository;
    private final QuestionsRepository questionsRepository;
    private final TestedRepository testedRepository;
    private final TestedInfoRepository testedInfoRepository;
    @Autowired
    HttpSession session;


    @Override
    public List<Question> testedQuestion( Model model) {

        List<Question> questionList=questionRepository.findAll();

        model.addAttribute("questionList",questionList);


        session.setAttribute("questionList", questionList);

        return questionList;
    }

    @Override
    public List<Questionj> testedQuestionj( Model model) {

        List<Questionj> questionjList = questionjRepository.findAll();
        model.addAttribute("questionjList", questionjList);
        session.setAttribute("questionjList", questionjList);

        return questionjList;
    }

    @Override
    public List<QuestionImage> testedQuestionImage( Model model) {

        List<QuestionImage> questionImageList = questionimageRepository.findAll();
        model.addAttribute("questionImageList", questionImageList);
        model.addAttribute("imgUtil", new ImageUtil());
        session.setAttribute("questionImageList", questionImageList);
        return questionImageList;
    }

    @Override
    public List<Questions> testedQuestions( Model model) {

        List<Questions> questionsList = questionsRepository.findAll();
        model.addAttribute("questionsList", questionsList);
        session.setAttribute("questionsList", questionsList);

        return questionsList;
    }
    @Override
    public Tested testedQuestionSave(TestedInfo testedInfo,UUID uuidShare,UUID uuidQuestions) {
        UUID uuid = UUID.randomUUID();
        List<Question> questionList = questionRepository.findAll();
  Tested tested =new Tested();
        if (questionList != null) {
            for (int i = 0; i < questionList.size(); i++) {
                Question q = questionList.get(i);

                Tested tested1 = new Tested();
                tested1.setTestedUuid(uuid.toString());
                //특정 문제 유형에만 공통으로  저장되어 한 시험지에 같은 유형의 문제끼리
                //같은 uuid값을 가짐
                tested1.setTestedShareUuid(uuidShare.toString());
                tested1.setQuestion(q);
                testedRepository.save(tested1);
                return tested1;
            }


            //퀘션의 테이블의 id값을 저장해서 조인 컬럼으로 정답을 조회해서 테스티드에 내가 저장한
            //내 시험지의 답과 정답을 맞춰보기 위해서  그래서 문제가 많을 수록 비효율 적인 방식
            // 문제가 늘어날수록 테스티드에 저장될 행이 너무 많음 나중에 다른 로직을 생각해 볼 것
            //아마 테이블 개수를 늘려서 분할 하는 걸 먼저 생각
            TestedInfo testedInfo1 = testedInfo.builder()
                    .uuid(uuid.toString())
                    .questionCount(questionList.size())
                    .uuid2(uuidQuestions.toString())
                    .uuid3(uuidShare.toString())
                    .retest("x")
                    .grading("x")
                    .correctsAnswerScore("미채점")
                    .build();
            testedInfoRepository.save(testedInfo1);
        }

        session.setAttribute("testedUuidQuestion", uuid.toString());

            //시험 정보 테이블에 문제 개수나 특정 역할 수행을 위한 UUId값을 저장해준다.
   return  tested;
    }
    @Override
    public Tested testedQuestionjSave(TestedInfo testedInfo,UUID uuidShare) {
        UUID uuid = UUID.randomUUID();
        List<Questionj> questionjList = questionjRepository.findAll(Sort.by("createdAt").descending());
        Tested tested = new Tested();
        for (Questionj q : questionjList) {
            tested.setTestedUuid(uuid.toString());
            tested.setTestedShareUuid(uuidShare.toString());
            tested.setQuestionj(q);
            testedRepository.save(tested);
        }
//        TestedInfo testedInfo1 = new TestedInfo().builder()
//               .uuid1(uuid.toString())
//                .questionjCount(questionjList.size()).build();
//
//        testedInfoRepository.save(testedInfo1);


        session.setAttribute("testedUuidQuestionj", uuid.toString());
        return tested;
    }

    @Override
    public Tested testedQuestionsSave(TestedInfo testedInfo,UUID uuidShare,UUID uuidQuestions) {
        UUID uuid = UUID.randomUUID();
        Tested tested =new Tested();
        List<Questions> questionsList = questionsRepository.findAll(Sort.by("createdAt").descending());
       if(questionsList !=null) {
           for (Questions q : questionsList) {
               Tested tested1 = new Tested();
               tested1.setTestedUuid(uuid.toString());
               tested1.setTestedShareUuid(uuidShare.toString());
               tested1.setQuestions(q);
               testedRepository.save(tested);
               return tested1;
           }
           session.setAttribute("testedUuidQuestions", uuid.toString());
//        TestedInfo testedInfo1 = testedInfo.builder()
//                .uuid2(uuid.toString()).build();
//        testedInfoRepository.save(testedInfo1);

       } return  tested;


    }
    @Override
    public Tested testedQuestionImageSave(TestedInfo testedInfo,UUID uuidShare) {
        UUID uuid = UUID.randomUUID();
        List<QuestionImage> questionImageList = questionimageRepository.findAll(Sort.by("createdAt").descending());
        Tested tested = new Tested();
        for (QuestionImage q : questionImageList) {
            tested.setTestedUuid(uuid.toString());
            tested.setTestedShareUuid(uuidShare.toString());
            tested.setQuestionimage(q);
            testedRepository.save(tested);
        }
//        TestedInfo testedInfo1 = new TestedInfo().builder()
//                .uuid0(uuid.toString())
//                .questionimageCount(questionImageList.size())
//                .build();
//        testedInfoRepository.save(testedInfo);
//        session.setAttribute("testedUuidQuestionImage", uuid.toString());
        return tested;
    }
  @Override
    public TestedInfo TestedInfoSave(UUID uuidShare){
        List<Question> questionList=questionRepository.findAll();
        List<Questionj> questionjList=questionjRepository.findAll();
        List<Questions> questionsList=questionsRepository.findAll();
        List<QuestionImage> questionImageList=questionimageRepository.findAll();
        TestedInfo testedInfo = new TestedInfo().builder()
                .uuid3(uuidShare.toString())
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

        } return  testedInfo;
    }
    @Override
    public String retest(TestedInfo testedInfo, String uuid3, AnswerForm answerForm, Model model) {

        String name="";

        var testedInfoList =  testedInfoRepository.findIdByUuid(uuid3);
        for(int i =0;i<testedInfoList.size();i++){
            String id=testedInfoList.get(i);
            Optional<TestedInfo> testedInfoOptional=testedInfoRepository.findById(id);
            if(testedInfoOptional.isPresent()){
                TestedInfo t=testedInfoOptional.get();
                name=t.getUserName();
                answerForm.setName(name);

            }

        }
        return name;
    }
   @Override
    public  void testView(String uuid,List<TestedForm> testedList, List<QuestionForm> questionList,List<QuestionimageForm> questionimageList,List<QuestionsForm> questionsList,Model model){

        List<Long> idList = testedRepository.findIdByUuid1(uuid);
        for (int i = 0; i < idList.size(); i++) {
            long id = idList.get(i);
            Optional<Tested> testedOptional = testedRepository.findById(id);
            if (testedOptional.isPresent()) {
                Tested t = testedOptional.get();
                Question q = t.getQuestion();
                QuestionImage qi=t.getQuestionimage();
                Questionj qj = t.getQuestionj();
                Questions qs = t.getQuestions();
                if(q != null){
                    TestedForm tested = new TestedForm()
                            .builder()
                            .title(q.getTitle())
                            .choice1(q.getChoice1())
                            .choice2(q.getChoice2())
                            .choice3(q.getChoice3())
                            .choice4(q.getChoice4())
                            .correctAnswer(q.getAnswer())
                            .answer(t.getAnswer())
                            .build();
                    testedList.add(tested);
                } else if (qi !=null) {
                    QuestionimageForm questionimage=new QuestionimageForm()
                            .builder()
                            .title(qi.getTitle())
                            .image(qi.getImage())
                            .choice1(qi.getChoice1())
                            .choice2(qi.getChoice2())
                            .choice3(qi.getChoice3())
                            .choice4(qi.getChoice4())
                            .correct_answer(qi.getCorrect_answer())
                            .my_answer(qi.getMy_answer())
                            .build();
                    questionimageList.add(questionimage);
                }else if (qj != null) {
                    QuestionForm question= new QuestionForm()
                            .builder()
                            .title(qj.getTitle())
                            .correct_answer(t.getAnswerj())
                            .build();
                    questionList.add(question);
                } else if (qs != null) {
                    QuestionsForm questionsForm= new QuestionsForm()
                            .builder()
                            .title(qs.getTitle())
                            .my_answer(t.getAnswers())
                            .build();
                    questionsList.add(questionsForm);

                }
                model.addAttribute("questionimageList", questionimageList);
                model.addAttribute("imgUtil", new ImageUtil());
                model.addAttribute("testedList", testedList);
                model.addAttribute("questionList", questionList);
                model.addAttribute("questionsList",questionsList);
            }


        }
    }
    @Override
    public Tested testedDelete(String uuid3){
      Tested tested=new Tested();
        var idList2=testedRepository.findIdByUuid1(uuid3);
        for(int i=0;i<idList2.size();i++){   //세션으로 저장해놓은 uuid에 해당되는 시험지를 삭제
            long id=idList2.get(i);
            Optional<Tested> testedOptional=testedRepository.findById(id);
            if(testedOptional.isPresent()){
                Tested t=testedOptional.get();
                testedRepository.delete(t);
            }
        }  return  tested;
    }

    @Override
    public TestedInfo testedInfoDelete(String uuid3){
        TestedInfo testedInfo=new TestedInfo();
        var idList3=testedInfoRepository.findIdByUuid(uuid3);
        for(int i=0;i<idList3.size();i++){  //세션으로 저장해놓은 uuid에 해당되는 시험지를 삭제
            String id=idList3.get(i);
            Optional<TestedInfo> testedInfoOptional=testedInfoRepository.findById(id);
            if(testedInfoOptional.isPresent()){
                TestedInfo t=testedInfoOptional.get();

                testedInfoRepository.delete(t);
            }
        }  return  testedInfo;
    }


}
