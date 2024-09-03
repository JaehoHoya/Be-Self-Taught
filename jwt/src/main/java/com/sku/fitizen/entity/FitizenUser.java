package com.sku.fitizen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class FitizenUser {

    @Id
    private String userName;
    private String userPwd;
    private String profileName;
    private String email;
    private Date birth;
    private Date reg_date;
    private Date up_date;

    private String userRole="ROLE_USER";
}


