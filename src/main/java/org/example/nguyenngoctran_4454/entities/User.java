package org.example.nguyenngoctran_4454.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;
    @Column(nullable = false,unique = true)
    private String username;
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    private boolean isDelete;


    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;


}
