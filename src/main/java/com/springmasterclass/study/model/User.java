package com.springmasterclass.study.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String address;





}
