package com.hanium.pay.service;


import com.hanium.pay.model.Users;
import com.hanium.pay.payload.request.UserChargeRequest;
import com.hanium.pay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ChargerService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void charger(UserChargeRequest userChargeRequest){

        if(userRepository.findById(userChargeRequest.getUserId()).isPresent()){
            Users user = userRepository.findById(userChargeRequest.getUserId()).get();
            Long nowAmount = user.getAmount();
            user.setAmount(nowAmount + userChargeRequest.getAmount());
            userRepository.save(user);
        }
        

        
        
    }


}
