package com.mbsnjdxyry.tracord_backend.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToFront implements Serializable {
    private Integer id;
    private String avatar;
}
