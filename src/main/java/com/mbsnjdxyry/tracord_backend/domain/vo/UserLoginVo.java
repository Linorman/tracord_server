package com.mbsnjdxyry.tracord_backend.domain.vo;

import com.mbsnjdxyry.tracord_backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private String token;
    private User user;
}
