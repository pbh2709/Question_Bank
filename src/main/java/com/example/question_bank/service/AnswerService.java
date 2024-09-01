package com.example.question_bank.service;

import com.example.question_bank.form.AnswerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService implements AnswerInterface {

    @Override
    public List<Integer> answerListReturn(AnswerForm answerForm){

        List<Integer> answerList =new ArrayList<Integer>(
                Arrays.asList(
                        answerForm.getAnswer1(),
                        answerForm.getAnswer2(),
                        answerForm.getAnswer3(),
                        answerForm.getAnswer4(),
                        answerForm.getAnswer5(),
                        answerForm.getAnswer_6(),
                        answerForm.getAnswer_7(),
                        answerForm.getAnswer_8(),
                        answerForm.getAnswer_9(),
                        answerForm.getAnswer_10(),
                        answerForm.getAnswer_11(),
                        answerForm.getAnswer_12(),
                        answerForm.getAnswer_13(),
                        answerForm.getAnswer_14(),
                        answerForm.getAnswer_15(),
                        answerForm.getAnswer_16(),
                        answerForm.getAnswer_17(),
                        answerForm.getAnswer_18(),
                        answerForm.getAnswer_19(),
                        answerForm.getAnswer_20())
        );

        return  answerList;
    }
    @Override
    public List<String> answerjListReturn(AnswerForm answerForm){

        List<String> answerList = new ArrayList<String>(
                Arrays.asList(

                        answerForm.getAnswerj_1(),
                        answerForm.getAnswerj_2(),
                        answerForm.getAnswerj_3(),
                        answerForm.getAnswerj_4(),
                        answerForm.getAnswerj_5()
                ));

        return  answerList;
    }
    @Override
    public List<String> answersListReturn(AnswerForm answerForm){

        List<String> answersList = new ArrayList<String>(
                Arrays.asList(

                        answerForm.getAnswers_1(),
                        answerForm.getAnswers_2(),
                        answerForm.getAnswers_3(),
                        answerForm.getAnswers_4(),
                        answerForm.getAnswers_5()
                )
        );

        return  answersList;
    }
    @Override
    public List<Integer> answerImageListReturn(AnswerForm answerForm){

        List<Integer> answerImageList = new ArrayList<Integer>(
                Arrays.asList(
                        answerForm.getAnswerimage_1(),
                        answerForm.getAnswerimage_2(),
                        answerForm.getAnswerimage_3(),
                        answerForm.getAnswerimage_4(),
                        answerForm.getAnswerimage_5()

                )
        );

        return  answerImageList;
    }
}
