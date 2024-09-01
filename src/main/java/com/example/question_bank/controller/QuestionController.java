package com.example.question_bank.controller;

import com.example.question_bank.config.ImageUtil;
import com.example.question_bank.entity.*;
import com.example.question_bank.form.*;
import com.example.question_bank.repository.*;

import com.example.question_bank.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
public class QuestionController {


    private final QuestionRepository questionRepository;


    private final QuestionService questionService;

    private final ListService listService;

    private final QuestionEditService questionEditService;

    private  final QuestionImageService questionImageService;

    private final DeleteService deleteService;

    private final QuestionimageRepository questionimageRepository;
//
//
//    private final QuestionjRepository questionjRepository;
//
//
//    private final  QuestionsRepository questionsRepository;
//
//
    private final TestedRepository testedRepository;
//
//    private final TestedInfoRepository testedInfoRepository;
//
    @Autowired
    HttpSession session;
    //
    @Autowired
    ApplicationContext applicationContext;

    private final UserRepository userRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(
            InputForm inputForm
    ) {
        return "index";
    }

    @GetMapping("/question")
    public String question_add(
            InputForm inputForm
    ) {
        return "question";
    }

    @PostMapping("question_add")
    public String question_add(
            @Valid InputForm inputForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes

    ) {
        if (!bindingResult.hasErrors()) {
            Question question = new Question();
            question = questionService.saveQuestion(question, inputForm);

            if (question != null) {
                    redirectAttributes.addFlashAttribute(
                            "toast_message", "문제가 등록되었습니다.");
                    return "redirect:/question_list";
                }

        }  return "question";
    }




    @GetMapping("/question_list")
    public String question_list(
            Model model,
            @ModelAttribute("toast_message") String toast_message

    ) {
        Question question=new Question();
          listService.listQuestion(question,model);
        return "question_list";
    }

    @GetMapping("/question_edit/{uuid}")
    public String question_edit(
            InputForm inputForm,
            @PathVariable String uuid,
            Model model
    ) {
        Question question=new Question();
        questionEditService.editStartQuestion(question,uuid,inputForm,model);

        return "question_edit";
    }
//
    @PostMapping("/question_edit_process")
    public String question_edit_process(
            @Valid InputForm inputForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (!bindingResult.hasErrors()) {
            Question question=new Question();
             question = questionEditService.editEndQuestion(question,inputForm,model);
                if (question != null) {
                    String toast_message = "문제 수정";
                    redirectAttributes.addFlashAttribute("toast_message", toast_message);
                    return "redirect:/question_list";
                }
            }
        return "question_edit";
    }

    @GetMapping("question_details/{uuid}")
    public String question_details(
            @PathVariable String uuid,
            InputForm inputForm,
            Model model
    ) {

        Question question=new Question();
        questionEditService.editStartQuestion(question,uuid,inputForm,model);
        return "question_details";
    }
    @GetMapping("question_delete/{uuid}")
    public String question_delete(
            QuestionForm questionForm,
            @PathVariable String uuid,
            RedirectAttributes redirectAttributes
    ) {
        String toast_message = " ";
        var idList = testedRepository.findIdByQuestionUuid(uuid);
        if (!idList.isEmpty()) {
            toast_message = "출시된 문제 삭제 불가능";
            redirectAttributes.addFlashAttribute("toast_message", toast_message);
            return "redirect:/question_list";
        }
         Question question =new Question();
        deleteService.questionDelete(question,uuid);
        toast_message = "삭제";
        redirectAttributes.addFlashAttribute("toast_message", toast_message);
        return "redirect:/question_list";

    }
    @GetMapping("/questionj")
    public String questionj(
            QuestionForm questionForm
    ) {
        return "questionj";
    }

    @PostMapping("/questionj_add")
    public String questionj_add(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes

    ) {
        if (!bindingResult.hasErrors()) {
            Questionj questionj = new Questionj();
            questionj = questionService.saveQuestionj(questionj, questionForm);

            if (questionj != null) {
                redirectAttributes.addFlashAttribute(
                        "toast_message", "문제가 등록되었습니다.");
                return "redirect:/questionj_list";
                //정상 등록
            }
        }

        return "questionj";
    }

    @GetMapping("/questionj_list")
    public String questionj_list(

            Model model,
            @ModelAttribute("toast_message") String toast_message
    ) {
        Questionj questionj=new Questionj();
        listService.listQuestionj(questionj,model);
        return "questionj_list";
    }

    @GetMapping("questionj_details/{uuid}")
    public String questionj_details(
            @PathVariable String uuid,
            QuestionForm questionForm,
            Model model
    ) {
        Questionj questionj=new Questionj();
        questionEditService.editStartQuestionj(questionj,uuid,questionForm,model);
        return "questionj_details";
    }

    @GetMapping("questionj_edit/{uuid}")
    public String questionj_edit(
            @PathVariable String uuid,
            Model model,
            QuestionForm questionForm
    ) {
        Questionj questionj=new Questionj();
        questionEditService.editStartQuestionj(questionj,uuid,questionForm,model);
        return "questionj_edit";
    }
    //
    @PostMapping("questionj_edit")
    public String questionj_edit(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (!bindingResult.hasErrors()) {
            Questionj questionj=new Questionj();
            questionj = questionEditService.editEndQuestionj(questionj,questionForm,model);
            if (questionj != null) {
                String toast_message = "문제 수정";
                redirectAttributes.addFlashAttribute("toast_message", toast_message);
                return "redirect:/questionj_list";
            }
        }
        return "questionj_edit";

    }

    @GetMapping("questionj_delete/{uuid}")
    public String questionj_delete(
            QuestionForm questionForm,
            @PathVariable String uuid,
            RedirectAttributes redirectAttributes
    ) {
        String toast_message = " ";
        var idList = testedRepository.findIdByQuestionjUuid(uuid);
        if (!idList.isEmpty()) {
            toast_message = "출시된 문제 삭제 불가능";
            redirectAttributes.addFlashAttribute("toast_message", toast_message);
            return "redirect:/questionj_list";
        }
        Questionj questionj =new Questionj();
        deleteService.questionjDelete(questionj,uuid);
        toast_message = "삭제";
        redirectAttributes.addFlashAttribute("toast_message", toast_message);
        return "redirect:/questionj_list";

    }

    @GetMapping("/questionimage")
    public String questionimage(
            QuestionimageForm questionimageForm
    ) {


        return "questionimage";
    }
//
    @PostMapping("/questionimage_add")
    public String questionimage(
            Model model,
            @RequestParam("file") MultipartFile file,
            @Valid QuestionimageForm questionimageForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
            InputStream inputStream=null;

        if (!bindingResult.hasErrors()) {
            QuestionImage questionImage = new QuestionImage();
               questionImageService.questionImageSave(questionImage,questionimageForm,file,inputStream);
            if (questionImage != null) {
                redirectAttributes.addFlashAttribute(
                        "toast_message", "문제가 등록되었습니다.");
                return "redirect:/questionimage_list";
            }
        }
            return "questionimage";
        }

    @GetMapping("/questionimage_list")
    public String questionimage_list(
            Model model,
            @ModelAttribute("toast_message") String toast_message

    ) {
        QuestionImage questionImage =new QuestionImage();
        var questionimageList = listService.listQuestionImage(questionImage,model);

        model.addAttribute("questionimageList", questionimageList);
        return "questionimage_list";
    }
//
    @GetMapping("questionimage_details/{uuid}")
    public String questionimage_details(
            @PathVariable String uuid,
            QuestionimageForm questionimageForm,
            Model model
    ) {

        QuestionImage questionImage=new QuestionImage();
        questionEditService.editStartQuestionImage(questionImage,uuid,questionimageForm,model);

        return "questionimage_details";
    }

    @GetMapping("/questionimage_edit/{uuid}")
    public String questionimage_edit(
            @PathVariable String uuid,
            QuestionimageForm questionimageForm,
            Model model
    ) {
        QuestionImage questionImage=new QuestionImage();
        questionEditService.editStartQuestionImage(questionImage,uuid,questionimageForm,model);

        return "questionimage_edit";
    }

    @PostMapping("/questionimage_edit_process")
    public String questionimage_edit_process(
            @RequestParam("file") MultipartFile file,
            @Valid QuestionimageForm questionimageForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        InputStream inputStream = null;

        if (!bindingResult.hasErrors()) {
            QuestionImage questionImage =new QuestionImage();
            BeanUtils.copyProperties(questionimageForm, questionImage);
                    questionEditService.editEndQuestionImage(questionImage,questionimageForm,file,inputStream);

                if (questionImage != null) {
                    String toast_message = "문제 수정";
                    redirectAttributes.addFlashAttribute("toast_message", toast_message);
                    return "redirect:/questionimage_list";
                }
            }

        return "questionimage_edit";
    }
//
    @GetMapping("questionimage_delete/{uuid}")
    public String questionimage_delete(
            QuestionimageForm questionimageForm,
            @PathVariable String uuid,
            RedirectAttributes redirectAttributes
    ) {
        QuestionImage questionImage=new QuestionImage();
        String toast_message = " ";
        var idList = testedRepository.findIdByQuestionimageUuid(uuid);
        if (!idList.isEmpty()) {
            toast_message = "출시된 문제 삭제 불가능";
            redirectAttributes.addFlashAttribute("toast_message", toast_message);
            return "redirect:/questionimage_list";
        }
        deleteService.questionImageDelete(questionImage,uuid);
        toast_message = "삭제";
        redirectAttributes.addFlashAttribute("toast_message", toast_message);
        return "redirect:/questionimage_list";

    }
    @GetMapping("/questions")
    public String questions(
            QuestionsForm questionsForm,
            Model model
    ){
        model.addAttribute("questionsForm", questionsForm);
        return "questions";
    }
    @PostMapping("/questions_add")
    public String questions_add(
            @Valid QuestionsForm questionsForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ){

        if (!bindingResult.hasErrors()) {
            Questions questions = new Questions();
            questions = questionService.saveQuestions(questions, questionsForm);

            if (questions != null) {
                redirectAttributes.addFlashAttribute(
                        "toast_message", "문제가 등록되었습니다.");
                return "redirect:/questions_list";
                //정상 등록
            }
        }
        return "questions";
    }
//
    @GetMapping("/questions_list")
    public String questions_list(
            Model model,
            @ModelAttribute("toast_message") String toast_message
    ) {
        Questions questions=new Questions();
        listService.listQuestions(questions,model);
        return "questions_list";
    }

    @GetMapping("questions_details/{uuid}")
    public String questions_details(
            @PathVariable String uuid,
            QuestionsForm questionsForm,
            Model model
    ) {
        Questions questions=new Questions();
        questionEditService.editStartQuestions(questions,uuid,questionsForm,model);
        return "questions_details";
    }
    @GetMapping("questions_edit/{uuid}")
    public String questions_edit(
            @PathVariable String uuid,
            Model model,
            QuestionsForm questionsForm
    ) {
        Questions questions=new Questions();
        questionEditService.editStartQuestions(questions,uuid,questionsForm,model);
        return "questions_edit";
    }

    @PostMapping("questions_edit")
    public String questions_edit(
            @Valid QuestionsForm questionsForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (!bindingResult.hasErrors()) {
            Questions questions=new Questions();
            questions = questionEditService.editEndQuestions(questions,questionsForm,model);
            if (questions != null) {
                String toast_message = "문제 수정";
                redirectAttributes.addFlashAttribute("toast_message", toast_message);
                return "redirect:/questions_list";
            }
        }
        return "questions_edit";

    }
    @GetMapping("questions_delete/{uuid}")
    public String questions_delete(
            QuestionsForm questionsForm,
            @PathVariable String uuid,
            RedirectAttributes redirectAttributes
    ) {
        String toast_message = " ";
        var idList = testedRepository.findIdByQuestionjUuid(uuid);
        if (!idList.isEmpty()) {
            toast_message = "출시된 문제 삭제 불가능";
            redirectAttributes.addFlashAttribute("toast_message", toast_message);
            return "redirect:/questions_list";
        }
        Questions questions =new Questions();
        deleteService.questionsDelete(questions,uuid);
        toast_message = "삭제";
        redirectAttributes.addFlashAttribute("toast_message", toast_message);
        return "redirect:/questions_list";
    }
}





