package com.mbsnjdxyry.tracord_backend.controller;

import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import com.mbsnjdxyry.tracord_backend.domain.PassageInfo;
import com.mbsnjdxyry.tracord_backend.domain.User;
import com.mbsnjdxyry.tracord_backend.domain.vo.PassageToFront;
import com.mbsnjdxyry.tracord_backend.service.PassageService;
import com.mbsnjdxyry.tracord_backend.utils.SecurityUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 帖子控制器
 * @date 2023/05/21
 * @auther linorman
 */
@RestController
@RequestMapping("/passage")
public class PassageController {
    @Autowired
    private PassageService passageService;

    /**
     * 获取帖子列表
     * @return ResponseResult
     */
    @GetMapping("/getPassageList")
    public ResponseResult getPassageList(){
        return passageService.getPassageList();
    }

    /**
     * 获取用户帖子列表
     * @return ResponseResult
     */
    @PostMapping("/getUserPassageList")
    public ResponseResult getUserPassageList(){
        return passageService.getUserPassageList();
    }

    /**
     * 根据帖子id获取帖子
     * @param passageInfo
     * @return ResponseResult
     */
    @PostMapping("/getPassageById")
    public ResponseResult getPassageById(@RequestBody PassageInfo passageInfo){
        return passageService.getPassageById(passageInfo);
    }

    /**
     * 发布帖子
     * @param passageInfo
     * @return ResponseResult
     */
    @PostMapping("/publishPassage")
    public ResponseResult publishPassage(@RequestBody PassageInfo passageInfo){
        return passageService.publishPassage(passageInfo);
    }

    /**
     * 删除帖子
     * @param passageInfo
     * @return ResponseResult
     */
    @DeleteMapping("/deletePassage")
    public ResponseResult deletePassage(@RequestBody PassageInfo passageInfo){
        return passageService.deletePassage(passageInfo);
    }

    /**
     * 点赞帖子
     * @param passageId
     * @return ResponseResult
     */
    @PostMapping("/followPassage")
    public ResponseResult followPassage(Integer passageId){
        val userId = SecurityUtils.getUserId();
        return passageService.followPassage(userId, passageId);
    }

    /**
     * 取消点赞帖子
     * @param passageId
     * @return ResponseResult
     */
    @PostMapping("/unfollowPassage")
    public ResponseResult unfollowPassage(Integer passageId){
        val userId = SecurityUtils.getUserId();
        return passageService.unfollowPassage(userId, passageId);
    }

    /**
     * 获取帖子点赞数
     * @param passageInfo
     * @return ResponseResult
     */
    @PostMapping("/getPassageFollowNum")
    public ResponseResult getPassageFollowCount(@RequestBody PassageInfo passageInfo){
        return passageService.getPassageFollowerNum(passageInfo);
    }

    /**
     * 通过地址进行passage查询
     * @param address
     * @return ResponseResult
     */
    @PostMapping("/getPassageByAddress")
    public ResponseResult getPassageByAddress(@RequestBody String address){
        return passageService.getPassageListByAddress(address);
    }

    /**
     * 获取用户总点赞量
     * @return ResponseResult
     */
    @PostMapping("/getUserFollowNum")
    public ResponseResult getTotalFollowerNum(){
        val userId = SecurityUtils.getUserId();
        return passageService.getTotalFollowerNum(userId);
    }

    /**
     * 获取用户总帖子量
     * @return ResponseResult
     */
    @PostMapping("/getUserPassageNum")
    public ResponseResult getUserPassageNum(){
        val userId = SecurityUtils.getUserId();
        return passageService.getUserPassageNum(userId);
    }

}
