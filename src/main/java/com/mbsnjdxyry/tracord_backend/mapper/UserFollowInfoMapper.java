package com.mbsnjdxyry.tracord_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mbsnjdxyry.tracord_backend.domain.UserFollowInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关注信息Mapper
 * @date 2022/05/21
 * @auther linorman
 */
@Mapper
public interface UserFollowInfoMapper extends BaseMapper<UserFollowInfo> {
}
