package com.jpaex.board.service.post;

import com.hanium.common.model.board.User;
import com.hanium.common.model.board.UserRepository;
import com.jpaex.board.web.dto.UserJoinDto;
import com.jpaex.board.web.dto.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String join(UserJoinDto userJoinDto){
        try{
            logger.info("Test : "+userRepository.findByEmail(userJoinDto.toEntity().getEmail()).toString());
            if(userRepository.findByEmail(userJoinDto.toEntity().getEmail())==null){
                logger.info(userJoinDto.toEntity().getPassword());
                userJoinDto.setPassword(passwordEncoder.encode(userJoinDto.toEntity().getPassword()+userJoinDto.toEntity().getSalt()));
                userRepository.save(userJoinDto.toEntity());
                return "Success";
            }else{
                logger.info("fail");
                return "Already";
            }
        }finally {
            return "Fail";
        }

    }

    @Transactional
    public String login(UserLoginRequestDto loginDto){
        //Optional<User> user=userRepository.findByEmail(loginDto.getEmail());
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        if(user.isPresent()){
            return "get email";
        }else {
            return "No Email";
        }
    }
}
