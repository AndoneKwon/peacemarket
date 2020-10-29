package com.hanium.pay.service;


import com.hanium.pay.model.*;
import com.hanium.pay.repository.ProductRepository;
import com.hanium.pay.repository.TradeRepository;
import com.hanium.pay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    //요청처리
    @Transactional
    public boolean tradeHandling(TradeRequestDto trade,Long consumerId){
        if(productRepository.findById(trade.getProductId()).get().getProductType()==ProductType.OUT){
            return false;
        }
        //seller 포인트 추가
        Users seller = userRepository.findById(trade.getSellerId()).get();
        Long nowSellerAmount = seller.getAmount();
        seller.setAmount(nowSellerAmount+trade.getPrice());

        //purchaser 포인트 차감
        Users purchaser = userRepository.findById(consumerId).get();
        Long nowPurchaserAmount = purchaser.getAmount();
        purchaser.setAmount(nowPurchaserAmount - trade.getPrice());

        //상품 수정
        Product product = productRepository.findById(trade.getProductId()).get();
        product.setProductType(ProductType.OUT);

        //거래로그 생성
        userRepository.save(seller);
        userRepository.save(purchaser);
        productRepository.save(product);

        return true;
    }

}
