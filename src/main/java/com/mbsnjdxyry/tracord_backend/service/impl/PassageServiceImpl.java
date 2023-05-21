package com.mbsnjdxyry.tracord_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbsnjdxyry.tracord_backend.common.CityUtils;
import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import com.mbsnjdxyry.tracord_backend.common.ResultCode;
import com.mbsnjdxyry.tracord_backend.domain.PassageFollowerNum;
import com.mbsnjdxyry.tracord_backend.domain.PassageInfo;
import com.mbsnjdxyry.tracord_backend.domain.User;
import com.mbsnjdxyry.tracord_backend.domain.vo.PassageToFront;
import com.mbsnjdxyry.tracord_backend.domain.vo.UserToFront;
import com.mbsnjdxyry.tracord_backend.mapper.PassageFollowerNumMapper;
import com.mbsnjdxyry.tracord_backend.mapper.PassageInfoMapper;
import com.mbsnjdxyry.tracord_backend.mapper.UserMapper;
import com.mbsnjdxyry.tracord_backend.service.PassageService;
import com.mbsnjdxyry.tracord_backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 帖子服务实现类
 * @date 2023/05/21
 * @auther linorman
 */
@Service
public class PassageServiceImpl extends ServiceImpl<PassageInfoMapper, PassageInfo> implements PassageService {
    @Autowired
    private PassageInfoMapper passageInfoMapper;

    @Autowired
    private PassageFollowerNumMapper passageFollowerNumMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult<List<PassageToFront>> getPassageList() {
        LambdaQueryWrapper<PassageInfo> queryWrapper = new LambdaQueryWrapper<>();
        List<PassageInfo> passageInfoList = passageInfoMapper.selectList(queryWrapper);
        if (passageInfoList.isEmpty()){
            return ResponseResult.error(ResultCode.PASSAGE_LIST_NULL,null);
        }
        List<PassageToFront> passageToFrontList = new ArrayList<>();
        for (PassageInfo passageInfo : passageInfoList) {
            PassageToFront passageToFront = new PassageToFront();
            passageToFront.setId(passageInfo.getId());
            passageToFront.setUserId(passageInfo.getUserId());
            passageToFront.setContent(passageInfo.getContent());
            passageToFront.setAddress(passageInfo.getAddress());
            passageToFront.setPhotoTime(passageInfo.getPhotoTime());
            passageToFront.setCreateDate(passageInfo.getCreateDate());
            passageToFront.setImage(passageInfo.getImage());
            passageToFront.setFollowerNum(passageInfo.getFollowerNum());
            LambdaQueryWrapper<PassageFollowerNum> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PassageFollowerNum::getPassageId,passageInfo.getId());
            List<PassageFollowerNum> passageFollowerNumList = passageFollowerNumMapper.selectList(queryWrapper1);
            if (passageFollowerNumList.isEmpty()){
                passageToFront.setFollowerNum(0);
            }else {
                passageToFront.setFollowerNum(passageFollowerNumList.size());
            }
            List<UserToFront> userToFrontList = new ArrayList<>();
            for (int i = 0; i < passageFollowerNumList.size(); i++) {
                if (i == 3) {
                    break;
                }
                LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.eq(User::getId, passageFollowerNumList.get(i).getFollowerId());
                User user = userMapper.selectOne(queryWrapper2);
                userToFrontList.add(new UserToFront(user.getId(),user.getAvatar()));
            }
            passageToFront.setUserList(userToFrontList);
            passageToFrontList.add(passageToFront);
        }
        return ResponseResult.success(ResultCode.PASSAGE_LIST_GET_SUCCESS,passageToFrontList);
    }

    @Override
    public ResponseResult<List<PassageToFront>> getUserPassageList() {
        Integer userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<PassageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PassageInfo::getUserId,userId);
        List<PassageInfo> passageInfoList = passageInfoMapper.selectList(queryWrapper);
        if (passageInfoList.isEmpty()){
            return ResponseResult.error(ResultCode.PASSAGE_LIST_NULL,null);
        }
        List<PassageToFront> passageToFrontList = new ArrayList<>();
        for (PassageInfo passageInfo : passageInfoList) {
            PassageToFront passageToFront = new PassageToFront();
            passageToFront.setId(passageInfo.getId());
            passageToFront.setUserId(passageInfo.getUserId());
            passageToFront.setContent(passageInfo.getContent());
            passageToFront.setPhotoTime(passageInfo.getPhotoTime());
            passageToFront.setAddress(passageInfo.getAddress());
            passageToFront.setCreateDate(passageInfo.getCreateDate());
            passageToFront.setImage(passageInfo.getImage());
            passageToFront.setFollowerNum(passageInfo.getFollowerNum());
            LambdaQueryWrapper<PassageFollowerNum> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PassageFollowerNum::getPassageId,passageInfo.getId());
            List<PassageFollowerNum> passageFollowerNumList = passageFollowerNumMapper.selectList(queryWrapper1);
            if (passageFollowerNumList.isEmpty()){
                passageToFront.setFollowerNum(0);
            }else {
                passageToFront.setFollowerNum(passageFollowerNumList.size());
            }
            List<UserToFront> userToFrontList = new ArrayList<>();
            for (int i = 0; i < passageFollowerNumList.size(); i++) {
                if (i == 3) {
                    break;
                }
                LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.eq(User::getId, passageFollowerNumList.get(i).getFollowerId());
                User user1 = userMapper.selectOne(queryWrapper2);
                userToFrontList.add(new UserToFront(user1.getId(),user1.getAvatar()));
            }
            passageToFront.setUserList(userToFrontList);
            passageToFrontList.add(passageToFront);
        }
        return ResponseResult.success(ResultCode.PASSAGE_LIST_GET_SUCCESS,passageToFrontList);
    }

    @Override
    public ResponseResult<PassageInfo> getPassageById(PassageInfo passageInfo) {
        LambdaQueryWrapper<PassageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PassageInfo::getId,passageInfo.getId());
        PassageInfo passageInfo1 = passageInfoMapper.selectOne(queryWrapper);
        if (passageInfo1 == null){
            return ResponseResult.error(ResultCode.PASSAGE_GET_ERROR,null);
        }
        return ResponseResult.success(ResultCode.PASSAGE_GET_SUCCESS,passageInfo1);
    }

    @Override
    public ResponseResult publishPassage(PassageInfo passageInfo) {
        Integer userId = SecurityUtils.getUserId();
        passageInfo.setUserId(userId);
        int result = passageInfoMapper.insert(passageInfo);
        if (result == 0){
            return ResponseResult.error(ResultCode.PASSAGE_ADD_ERROR,null);
        }
        return ResponseResult.success(ResultCode.PASSAGE_ADD_SUCCESS,null);
    }

    @Override
    public ResponseResult<PassageInfo> deletePassage(PassageInfo passageInfo) {
        LambdaQueryWrapper<PassageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PassageInfo::getId,passageInfo.getId());
        int result = passageInfoMapper.delete(queryWrapper);
        if (result == 0){
            return ResponseResult.error(ResultCode.PASSAGE_DELETE_ERROR,null);
        }
        return ResponseResult.success(ResultCode.PASSAGE_DELETE_SUCCESS,null);
    }

    @Override
    public ResponseResult followPassage(Integer userId, Integer passageId) {
        PassageFollowerNum passageFollowerNum = new PassageFollowerNum();
        passageFollowerNum.setFollowerId(userId);
        passageFollowerNum.setPassageId(passageId);
        int result = passageFollowerNumMapper.insert(passageFollowerNum);
        if (result == 0){
            return ResponseResult.error(ResultCode.PASSAGE_LIKE_ERROR,null);
        }
        LambdaQueryWrapper<PassageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PassageInfo::getId,passageId);
        PassageInfo passageInfo = passageInfoMapper.selectOne(queryWrapper);
        passageInfo.setFollowerNum(passageInfo.getFollowerNum()+1);
        passageInfoMapper.updateById(passageInfo);
        return ResponseResult.success(ResultCode.PASSAGE_LIKE_SUCCESS,null);
    }

    @Override
    public ResponseResult unfollowPassage(Integer userId, Integer passageId) {
        LambdaQueryWrapper<PassageFollowerNum> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PassageFollowerNum::getFollowerId,userId);
        queryWrapper.eq(PassageFollowerNum::getPassageId,passageId);
        int result = passageFollowerNumMapper.delete(queryWrapper);
        if (result == 0){
            return ResponseResult.error(ResultCode.PASSAGE_CANCEL_LIKE_ERROR,null);
        }
        LambdaQueryWrapper<PassageInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(PassageInfo::getId,passageId);
        PassageInfo passageInfo = passageInfoMapper.selectOne(queryWrapper1);
        passageInfo.setFollowerNum(passageInfo.getFollowerNum()-1);
        passageInfoMapper.updateById(passageInfo);
        return ResponseResult.success(ResultCode.PASSAGE_CANCEL_LIKE_SUCCESS,null);
    }

    @Override
    public ResponseResult<Integer> getPassageFollowerNum(PassageInfo passageInfo) {
        LambdaQueryWrapper<PassageFollowerNum> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PassageFollowerNum::getPassageId,passageInfo.getId());
        List<PassageFollowerNum> passageFollowerNumList = passageFollowerNumMapper.selectList(queryWrapper);
        if (passageFollowerNumList.isEmpty()){
            return ResponseResult.success(ResultCode.PASSAGE_FOLLOWER_NUM_GET_SUCCESS,0);
        }
        return ResponseResult.success(ResultCode.PASSAGE_FOLLOWER_NUM_GET_SUCCESS,passageFollowerNumList.size());
    }

    @Override
    public ResponseResult<List<PassageToFront>> getPassageListByAddress(String address) {
        LambdaQueryWrapper<PassageInfo> queryWrapper = new LambdaQueryWrapper<>();
        boolean flag = true;
        for (int i = 0; i < CityUtils.CITY_NAMES.length; i++) {
            if (address.contains(CityUtils.CITY_NAMES[i])){
                address = CityUtils.CITY_NAMES[i];
                flag = false;
                break;
            }
        }
        if (!flag){
            for (int i = 0; i < CityUtils.PROVINCE.length; i++) {
                if (address.contains(CityUtils.PROVINCE[i])) {
                    address = CityUtils.getCityByProvince(CityUtils.PROVINCE[i]);
                    flag = true;
                    break;
                }
            }
        }

        queryWrapper.like(PassageInfo::getAddress,address);
        List<PassageInfo> passageInfoList = passageInfoMapper.selectList(queryWrapper);
        if (passageInfoList.isEmpty()){
            return ResponseResult.error(ResultCode.PASSAGE_LIST_GET_ERROR,null);
        }
        List<PassageToFront> passageToFrontList = new ArrayList<>();
        for (PassageInfo passageInfo : passageInfoList) {
            PassageToFront passageToFront = new PassageToFront();
            passageToFront.setId(passageInfo.getId());
            passageToFront.setUserId(passageInfo.getUserId());
            passageToFront.setContent(passageInfo.getContent());
            passageToFront.setAddress(passageInfo.getAddress());
            passageToFront.setPhotoTime(passageInfo.getPhotoTime());
            passageToFront.setCreateDate(passageInfo.getCreateDate());
            passageToFront.setImage(passageInfo.getImage());
            passageToFront.setFollowerNum(passageInfo.getFollowerNum());
            LambdaQueryWrapper<PassageFollowerNum> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(PassageFollowerNum::getPassageId,passageInfo.getId());
            List<PassageFollowerNum> passageFollowerNumList = passageFollowerNumMapper.selectList(queryWrapper1);
            if (passageFollowerNumList.isEmpty()){
                passageToFront.setFollowerNum(0);
            }else {
                passageToFront.setFollowerNum(passageFollowerNumList.size());
            }
            List<UserToFront> userToFrontList = new ArrayList<>();
            for (int i = 0; i < passageFollowerNumList.size(); i++) {
                if (i == 3) {
                    break;
                }
                LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.eq(User::getId, passageFollowerNumList.get(i).getFollowerId());
                User user1 = userMapper.selectOne(queryWrapper2);
                userToFrontList.add(new UserToFront(user1.getId(),user1.getAvatar()));
            }
            passageToFront.setUserList(userToFrontList);
            passageToFrontList.add(passageToFront);
        }
        return ResponseResult.success(ResultCode.PASSAGE_LIST_GET_SUCCESS,passageToFrontList);
    }

    @Override
    public ResponseResult getTotalFollowerNum(Integer userId) {
        LambdaQueryWrapper<PassageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PassageInfo::getUserId,userId);
        List<PassageInfo> passageInfoList = passageInfoMapper.selectList(queryWrapper);
        if (passageInfoList.isEmpty()){
            return ResponseResult.success(ResultCode.PASSAGE_FOLLOWER_NUM_GET_SUCCESS,0);
        }
        int sum = 0;
        for (int i = 0; i < passageInfoList.size(); i++) {
            sum += passageInfoList.get(i).getFollowerNum();
        }
        return ResponseResult.success(ResultCode.PASSAGE_FOLLOWER_NUM_GET_SUCCESS,sum);
    }

    @Override
    public ResponseResult getUserPassageNum(Integer userId) {
        LambdaQueryWrapper<PassageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PassageInfo::getUserId,userId);
        List<PassageInfo> passageInfoList = passageInfoMapper.selectList(queryWrapper);
        if (passageInfoList.isEmpty()){
            return ResponseResult.success(ResultCode.PASSAGE_NUM_GET_SUCCESS,0);
        }
        return ResponseResult.success(ResultCode.PASSAGE_NUM_GET_SUCCESS,passageInfoList.size());
    }
}
