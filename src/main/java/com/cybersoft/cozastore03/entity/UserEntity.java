package com.cybersoft.cozastore03.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;

    // Thằng nào chữ N thì khóa ngoại luôn là @ManyToOne, @JoinColumn
    // Nếu chữ cuối là One thì là một đối tượng của Entity tham chiếu tới
    // Nếu chữ cuối là Many thì là một list đối tượng của Entity tham chiếu tới

    @ManyToOne
    @JoinColumn(name = "id_member_ranking")
    private MemberRanking memberRanking;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;

}
