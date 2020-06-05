package com.jpaex.board.web;

import com.jpaex.board.service.post.UserService;
import com.jpaex.board.web.dto.UserJoinDto;
import com.jpaex.board.web.dto.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {
    final private UserService userService;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @PostMapping("/join")
    public String join(@RequestBody UserJoinDto userJoinDto){
        return userService.join(userJoinDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDto userLoginDto){
        try{
            return userService.login(userLoginDto);
            //return "Success";
        }finally {
            //return "Fail";
        }
    }
}
