package com.mbsnjdxyry.tracord_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import com.mbsnjdxyry.tracord_backend.domain.PassageInfo;
import com.mbsnjdxyry.tracord_backend.domain.User;
import com.mbsnjdxyry.tracord_backend.domain.vo.PassageToFront;

import java.util.List;

/**
 * 帖子服务接口
 *
 * @date 2023/05/21
 * @auther linorman
 */
public interface PassageService extends IService<PassageInfo> {
    /**
     * 获取帖子列表
     *
     * @return ResponseResult
     */
    public ResponseResult<List<PassageToFront>> getPassageList();

    /**
     * 获取用户帖子列表
     * @param user
     * @return ResponseResult
     */
    public ResponseResult<List<PassageToFront>> getUserPassageList(User user);

    /**
     * 根据帖子id获取帖子
     * @param passageInfo
     * @return ResponseResult
     */
    public ResponseResult<PassageInfo> getPassageById(PassageInfo passageInfo);

    /**
     * 发布帖子
     * @param passageInfo
     * @return ResponseResult
     */
    public ResponseResult<PassageInfo> publishPassage(PassageInfo passageInfo);

    /**
     * 删除帖子
     * @param passageInfo
     * @return ResponseResult
     */
    public ResponseResult<PassageInfo> deletePassage(PassageInfo passageInfo);

    /**
     * 点赞帖子
     * @param userId, passageId
     * @return ResponseResult
     */
    public ResponseResult followPassage(Integer userId, Integer passageId);

    /**
     * 取消点赞帖子
     * @param userId, passageId
     * @return ResponseResult
     */
    public ResponseResult unfollowPassage(Integer userId, Integer passageId);

    /**
     * 获取帖子点赞数
     * @param passageInfo
     * @return ResponseResult
     */
    public ResponseResult getPassageFollowerNum(PassageInfo passageInfo);

    /**
     * 根据地址获取帖子列表
     * @param address
     * @return ResponseResult
     */
    public ResponseResult<List<PassageToFront>> getPassageListByAddress(String address);

}
