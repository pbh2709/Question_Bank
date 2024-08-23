package com.example.question_bank.service;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.User;
import com.example.question_bank.form.*;
import com.example.question_bank.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Transactional
    public void checkUsernameDuplication(LoginForm loginForm) {
        boolean usernameDuplicate = userRepository.existsByUsername(loginForm.getUsername());
        if (usernameDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }


    }


    @Transactional
    public void checkEmailDuplication(LoginForm loginForm) {
        boolean emailDuplicate = userRepository.existsByEmail(loginForm.getEmail());
        if (emailDuplicate) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");


        }
    }

    @Transactional
    public void userSave(
            LoginForm loginForm,
            @RequestParam("originalId") int id
    ) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BeanUtils.copyProperties(loginForm, user);

            userRepository.save(user);
        }
    }

    public List<User> listUser(Model model) {

        List<User> userList = userRepository.findAll(Sort.by("createdAt").descending());
        model.addAttribute("userList", userList);

        return userList;
    }

    public User editUser(User user, int id, RoleChangeForm roleChangeForm, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();

            roleChangeForm.setOriginalId(user.getOriginalId());
            roleChangeForm.setUsername(user.getUsername());
            roleChangeForm.setRoles(user.getRoles());
            roleChangeForm.setCreatedAt(user.getCreatedAt());

            List<String> currentRoles = Arrays.asList(user.getRoles().replaceAll("ROLE_", "").toLowerCase(Locale.ROOT));
            roleChangeForm.setCurrentRoles(currentRoles);
            //roles.stream().map(r -> r.split("_"));
            // List<String> roles = List.of("admin");


        }
        model.addAttribute("roleChangeForm", roleChangeForm);
        return user;
    }

    public void editEndUserRole(int id, RoleChangeForm roleChangeForm, Model model) {
        int id1 = roleChangeForm.getOriginalId();
        Optional<User> userOptional = userRepository.findById(id1);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // ROLE_ 머리에 붙여야 한다
            user.setRoles("ROLE_ADMIN");
            user = userRepository.save(user);

        }

    }

    public User userInfo(User user, String username, ModelMap modelMap) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();

            modelMap.addAttribute("user", user1);
        }
        return user;
    }

    public User EditUserInfoID(User user, int id, LoginForm loginForm, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            BeanUtils.copyProperties(user1, loginForm);
        }
        model.addAttribute("loginChangeForm", loginForm);
        return user;
    }

    public Optional<User> editEndUserInfoID( int id,LoginForm loginForm,  Model model,String password) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            user1.setOriginalId(loginForm.getOriginalId());
            user1.setUsername(loginForm.getUsername());
            user1.setPassword(passwordEncoder.encode(password));
            userRepository.save(user1);

        }
        return userOptional;
    }

    public Optional<User> EditUserInfoPassword(User user, int id, PasswordForm passwordForm, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            BeanUtils.copyProperties(user1, passwordForm);
        }
        model.addAttribute("passwordForm", passwordForm);
        return userOptional;
    }

    public Optional<User> editEndUserInfoPassword( int id, Model model,String password) {

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setOriginalId(user.getOriginalId());
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }
            return userOptional;
        }

    public User passwordConfirm(@Valid LoginForm loginForm, User user) {

        if (Objects.equals(loginForm.getPassword(), loginForm.getPasswordConfirm())) {
            user = User.builder()
                    .username(loginForm.getUsername())
                    .password(passwordEncoder.encode(loginForm.getPassword()))
                    .email(loginForm.getEmail())
                    .roles("ROLE_USER")
                    .build();
            user = userRepository.save(user);

            return user;
        } return user;
    }
}