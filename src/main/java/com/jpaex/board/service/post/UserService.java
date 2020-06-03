package com.jpaex.board.service.post;

import com.jpaex.board.domain.posts.User;
import com.jpaex.board.domain.posts.UserRepository;
import com.jpaex.board.web.dto.UserJoinDto;
import com.jpaex.board.web.dto.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //PasswordEncoder passwordEncoder;

    @Transactional
    public String join(UserJoinDto userJoinDto){
        try{
            logger.info(userJoinDto.toEntity().getPassword());
            userRepository.save(userJoinDto.toEntity());
            return "Success";
        }finally {
            return "Fail";
        }

    }

    @Transactional
    public String login(UserLoginRequestDto loginDto){
        User user=userRepository.findByEmail(loginDto.getEmail());
        if(user==null){
            return "No Email";
        }else {

            return loginDto.getEmail();
        }
    }
}
