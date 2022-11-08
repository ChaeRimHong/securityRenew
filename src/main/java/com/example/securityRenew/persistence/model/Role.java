package com.example.securityRenew.persistence.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}


/*

-- TB_ROLE
CREATE TABLE tb_role (
	id number(9) NOT NULL,
	name varchar(255) NULL,
	CONSTRAINT tb_role_pkey PRIMARY KEY (id)
);

-- TB_USER_ROLES
CREATE TABLE tb_user_roles (
	user_id varchar(255) NOT NULL,
	role_id number(9) NOT NULL,
	CONSTRAINT tb_user_roles_pkey PRIMARY KEY (user_id, role_id)
);


-- 역할 등록
INSERT INTO tb_role
(id, name)
VALUES(1, 'ROLE_SYSTEM');

INSERT INTO tb_role
(id, name)
VALUES(2, 'ROLE_USER');

INSERT INTO tb_role
(id, name)
VALUES(3, 'ROLE_BOARD');


-- 사용자 별 역할 등록
INSERT INTO tb_user_roles
(user_id, role_id)
VALUES('system', 1);

INSERT INTO tb_user_roles
(user_id, role_id)
VALUES('user', 2);

INSERT INTO tb_user_roles
(user_id, role_id)
VALUES('user', 3);


-- 데이터를 생성할 수 있는 권한 등록
INSERT INTO tb_role (id, name)
VALUES(4, 'OP_CREATE_DATA');

-- 데이터를 삭제할 수 있는 권한 등록
INSERT INTO tb_role (id, name)
VALUES(5, 'OP_DELETE_DATA');


-- A 관리자에게 SYSTEM 역할과 OP_CREATE_DATA 권한 등록
INSERT INTO tb_user_roles (user_id, role_id)
VALUES('A', 1);

INSERT INTO tb_user_roles (user_id, role_id)
VALUES('A', 4);

-- B 관리자에게 SYSTEM 역할과 OP_DELETE_DATA 권한 등록
INSERT INTO tb_user_roles (user_id, role_id)
VALUES('B', 1);

INSERT INTO tb_user_roles (user_id, role_id)
VALUES('B', 5);


 */