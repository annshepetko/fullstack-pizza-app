package com.example.annfullstack.models.user;

import com.example.annfullstack.models.combined.UserOrdersFullInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class UserPageDetails {

    private String firstname;
    private String lastname;
    private List<UserOrdersFullInfo> orders;
    private Integer generalExpenses;

}
