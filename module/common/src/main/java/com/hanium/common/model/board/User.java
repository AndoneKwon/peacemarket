package com.hanium.common.model.board;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanium.common.model.audit.BaseTimeEntity;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;

@Table(name = "User", uniqueConstraints = @UniqueConstraint(name = "UNIQUE_USER",columnNames = {"EMAIL","PHONE","NICKNAME"}))
@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMAIL",nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    @JsonIgnore
    private String password; //password

    @Column
    @Nullable
    private char gender;

    @Column(nullable = false)
    @NonNull
    private String address;

    @Column(name="PHONE",nullable = false)
    @NonNull
    private String phoneNumber;

    @Column(name="NICKNAME",nullable = false)
    @NonNull
    private String nickname;

    @Column(nullable = false)
    @NonNull
    String salt;

    @Column(columnDefinition = "TINYINT(1) default 5")
    Byte auth;

    @Builder
    public User(String email, String password, char gender, String address, String phoneNumber, String nickName,String salt,Byte auth){
        this.email=email;
        this.password=password;
        this.gender=gender;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.nickname=nickName;
        this.salt=salt;
        this.auth=auth;
    }
}
