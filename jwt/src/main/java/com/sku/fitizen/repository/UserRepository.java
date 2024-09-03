package com.sku.fitizen.repository;

import com.sku.fitizen.entity.FitizenUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<FitizenUser,Integer> {

    //회원가입시 동일 이름이 있는지 찾음
    Boolean existsByUserName(String userName);
    //로그인 검증시 사용함
    FitizenUser findByUserName(String userName);
}
