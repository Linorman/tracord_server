package com.mbsnjdxyry.tracord_backend.controller;

import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import com.mbsnjdxyry.tracord_backend.domain.PassageInfo;
import com.mbsnjdxyry.tracord_backend.domain.User;
import com.mbsnjdxyry.tracord_backend.domain.vo.PassageToFront;
import com.mbsnjdxyry.tracord_backend.service.PassageService;
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
     * @param user
     * @return ResponseResult
     */
    @PostMapping("/getUserPassageList")
    public ResponseResult getUserPassageList(@RequestBody User user){
        return passageService.getUserPassageList(user);
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
     * @param map
     * @return ResponseResult
     */
    @PostMapping("/followPassage")
    public ResponseResult followPassage(@RequestBody Map<String, Integer> map){
        return passageService.followPassage(map.get("userId"), map.get("passageId"));
    }

    /**
     * 取消点赞帖子
     * @param map
     * @return ResponseResult
     */
    @PostMapping("/unfollowPassage")
    public ResponseResult unfollowPassage(@RequestBody Map<String, Integer> map){
        return passageService.unfollowPassage(map.get("userId"), map.get("passageId"));
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
}
