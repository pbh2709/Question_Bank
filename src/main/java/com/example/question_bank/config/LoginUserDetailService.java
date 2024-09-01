//package com.example.question_bank.config;
//
//import com.example.question_bank.entity.User;
//import com.example.question_bank.repository.UserRepository;
//import com.example.question_bank.entity.User;
//import com.example.question_bank.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class LoginUserDetailService implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;
//
////    public LoginUserDetailService(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        /**
//         Optional<User> _user = userRepository.findByEmail(email);
//         return _user.map(user -> new LoginUserDetails(user))
//         .orElseThrow(() -> new UsernameNotFoundException("not found email=" + email));
//         */
//        Optional<User> _user = userRepository.findByUsername(username);
//        if(_user.isPresent()) {
//            User user = _user.get();
//            return new LoginUserDetails(user);
//        }
//
//        throw new UsernameNotFoundException("not found username=" + username);
//    }
//}