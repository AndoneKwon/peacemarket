package com.hanium.pay.service;


import com.hanium.pay.model.Product;
import com.hanium.pay.model.ProductType;
import com.hanium.pay.model.Trade;
import com.hanium.pay.model.User;
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
    public void tradeHandling(Trade trade){
        //seller 포인트 추가
        User seller = userRepository.findById(trade.getSellerId()).get();
        Long nowSellerAmount = seller.getAmount();
        seller.setAmount(nowSellerAmount+trade.getPrice());

        //purchaser 포인트 차감
        User purchaser = userRepository.findById(trade.getPurchaserId()).get();
        Long nowPurchaserAmount = purchaser.getAmount();
        purchaser.setAmount(nowPurchaserAmount - trade.getPrice());

        //상품 수정
        Product product = productRepository.findById(trade.getPurchaserId()).get();
        product.setProductType(ProductType.OUT);

        //거래로그 생성
        userRepository.save(seller);
        userRepository.save(purchaser);
        productRepository.save(product);
        tradeRepository.save(trade);
    }

}
