package com.example.question_bank.controller;

import com.example.question_bank.config.LoginUserDetails;
import com.example.question_bank.entity.User;
import com.example.question_bank.form.LoginChangeForm;
import com.example.question_bank.form.LoginForm;
import com.example.question_bank.form.PasswordForm;
import com.example.question_bank.form.RoleChangeForm;
import com.example.question_bank.repository.UserRepository;
import com.example.question_bank.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    HttpSession session;
    //
    @Autowired
    ApplicationContext applicationContext;


    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(
            HttpServletRequest request,
            HttpServletResponse response,
            @ModelAttribute("toast_message") String toast_message
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @GetMapping("/my")
    public String my(HttpServletRequest request) {
        /**
         HttpSession session = request.getSession();
         SecurityContext securityContext = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
         Authentication authentication = securityContext.getAuthentication();
         */
        return "my";
    }

    @GetMapping("/get_auth")
    public String get_auth() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        System.out.println(loginUserDetails);
        return "get_auth";
    }

    @GetMapping("/register")
    public String register(LoginForm userForm) {

        return "register";
    }

    @PostMapping(value = "/register")
    public String register(
            @Valid LoginForm loginForm,
            BindingResult bindingResult) {


        Optional<User> userOptional = userRepository.findByUsername(loginForm.getUsername());
        if (userOptional.isPresent()) {
            bindingResult.addError(new FieldError("loginForm"
                    , "username", "중복된 아이디 입니다."));
        }

        Optional<User> userOptional1 = userRepository.findByEmail(loginForm.getEmail());
        if (userOptional1.isPresent()) {
            bindingResult.addError(new FieldError("loginForm"
                    , "email", "중복된 이메일 입니다."));
        }
        if (!bindingResult.hasErrors()) {
            User user = new User();
            userService.passwordConfirm(loginForm, user);
            if (user != null) {
                System.out.println("정상적으로 등록되었습니다.");
                return "redirect:/";
            }

        }

        return "register";
    }

    @GetMapping("roles")
    public String roles() {

        return "roles";
    }

    @GetMapping("/user_list")
    public String user_list(
            Model model
    ) {
        userService.listUser(model);
        return "user_list";
    }

    @GetMapping("/role_edit/{id}")
    public String role_edit(
            Model model,
            @PathVariable("id") int id,
            RoleChangeForm roleChangeForm,
            @ModelAttribute("toast_message") String toast_message
    ) {
        User user = new User();
        userService.editUser(user, id, roleChangeForm, model);
        model.addAttribute("roleChangeForm", roleChangeForm);
        return "role_edit";
    }

    @PostMapping("/role_edit")
    public String role_edit(
            @Valid RoleChangeForm roleChangeForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model

    ) {
        int id = roleChangeForm.getOriginalId();
        String password = roleChangeForm.getPassword();
        if (!bindingResult.hasErrors()) {
            userService.editEndUserRole(id, roleChangeForm, model);

            redirectAttributes.addFlashAttribute(
                    "toast_message", "권한 변경 성공.");
            return "redirect:/logout";
        } else if (bindingResult.hasErrors()) {
            System.out.println("실패");
            return "role_edit";

        }
        return "redirect:/logout";
    }

    @GetMapping("/userinfo")
    public String memberInfo(Principal principal, ModelMap modelMap) {
        String username = principal.getName();
        User user = new User();
        userService.userInfo(user, username, modelMap);
        return "userinfo";
    }

    @GetMapping("/user_edit/{id}")
    public String user_edit(
            Model model,
            @PathVariable("id") int id,
            LoginForm loginForm,
            @ModelAttribute("toast_message") String toast_message
    ){
        User user=new User();
        userService.EditUserInfoID(user,id,loginForm,model);

        return "user_edit";
    }

    @PostMapping("/user_edit")
    public String user_edit(
            @Valid LoginForm loginForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @RequestParam("password") String password,
            Model model
    ) {


        if (!bindingResult.hasErrors()) {

            int id=loginForm.getOriginalId();
            userService.editEndUserInfoID( id, loginForm,model,password);
//            Optional<User> userOptional = userRepository.findById(id);
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                user.setOriginalId(loginForm.getOriginalId());
//                user.setUsername(loginForm.getUsername());
////                user.setPassword(passwordEncoder.encode(loginForm.getPassword()));
//                userRepository.save(user);
                redirectAttributes.addFlashAttribute(
                        "toast_message", "회원 정보 변경");
                return "redirect:/logout";
            }
//        }

        return "user_edit";
    }

    @GetMapping("/password_edit/{id}")
    public String password_edit_process(
            Model model,
            @PathVariable("id") int id,
            PasswordForm passwordForm,
            @ModelAttribute("toast_message") String toast_message
    ) {
        User user=new User();
        userService.EditUserInfoPassword(user,id,passwordForm,model);
        return "password_edit";
    }

    @PostMapping("/password_edit_process")
    public String password_edit_process(
            @Valid PasswordForm loginForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @RequestParam("password") String password,
            Model model
    ) {
        int id = loginForm.getOriginalId();
        if (!bindingResult.hasErrors()) {
            User user=new User();
            userService.editEndUserInfoPassword(id,model,password);
//            Optional<User> userOptional = userRepository.findById(id);
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                user.setOriginalId(user.getOriginalId());
//                user.setPassword(passwordEncoder.encode(password));
//                userRepository.save(user);
                redirectAttributes.addFlashAttribute(
                        "toast_message", "회원 정보 변경");

            return "redirect:/logout";
        }
        System.out.println("afad");
        return "password_edit";
    }
}

