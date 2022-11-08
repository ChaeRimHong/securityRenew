package com.example.securityRenew.persistence.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tb_user")
@DynamicUpdate
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "tb_user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Builder
    public User(String id, String password, String name, Set<Role> roles) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }
}

/*

-- TB_USER
CREATE TABLE tb_user (
	id varchar(255) NOT NULL,
	name varchar(255) NULL,
	password varchar(255) NULL,
	CONSTRAINT tb_user_pkey PRIMARY KEY (id)
);

-- 사용자 정보 등록 (비밀번호는 BCryptPasswordEncoder로 인코딩 된 값을 넣었다. 로그인 비밀번호 : 123)
INSERT INTO tb_user
(id, name, password)
VALUES('system', 'system', 0000);


--비밀번호 123
INSERT INTO tb_user (id, name, password)
VALUES('system', 'system', '$2a$10$6iJv7gmGXdP2Sf0ojKBfwuUgxaHqv0c3f50SfDNdxNxq.dSymAAlW');


-- A 사용자 정보 등록 (비밀번호는 BCryptPasswordEncoder로 인코딩 된 값을 넣었다. 로그인 비밀번호 : 123)
INSERT INTO tb_user (id, name, password)
VALUES('A', 'A', '$2a$10$6iJv7gmGXdP2Sf0ojKBfwuUgxaHqv0c3f50SfDNdxNxq.dSymAAlW');

-- B 사용자 정보 등록 (비밀번호는 BCryptPasswordEncoder로 인코딩 된 값을 넣었다. 로그인 비밀번호 : 123)
INSERT INTO tb_user (id, name, password)
VALUES('B', 'B', '$2a$10$6iJv7gmGXdP2Sf0ojKBfwuUgxaHqv0c3f50SfDNdxNxq.dSymAAlW');

 */