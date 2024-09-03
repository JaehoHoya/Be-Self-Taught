package com.sku.fitizen.service;


import com.sku.fitizen.dto.JoinDTO;
import com.sku.fitizen.entity.FitizenUser;
import com.sku.fitizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public void join(JoinDTO dto)
    {

        String userName=dto.getUserName();
        String userPwd=dto.getUserPwd();

        Boolean isExist=userRepository.existsByUserName(userName);

        if(isExist) return;


        FitizenUser user=new FitizenUser();
        user.setUserName(userName);
        user.setUserPwd(bCryptPasswordEncoder.encode(userPwd));
        user.setUserRole(user.getUserRole());
        userRepository.save(user);


    }


}
