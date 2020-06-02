package com.jpaex.board.domain.posts;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpaex.board.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "User")
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    //@NonNull
    String email;

    @Column
    //@NonNull
    @JsonIgnore
    String password; //password
/*
    @Column
    char gender;

    @Column
    //@NonNull
    String address;

    @Column
    //@NonNull
    String phoneNumber;

    @Column
    //@NonNull
    String nickName;
*/
    @Column
    //@NonNull
    String salt;

    @Builder
    public User(String email, String password, char gender, String address, String phoneNumber, String nickName,String salt){
        this.email=email;
        this.password=password;
        //this.gender=gender;
        //this.address=address;
        //this.phoneNumber=phoneNumber;
        //this.nickName=nickName;
        this.salt=salt;
    }
}
