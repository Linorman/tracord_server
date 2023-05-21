package com.mbsnjdxyry.tracord_backend;


import com.mbsnjdxyry.tracord_backend.domain.PassageInfo;
import com.mbsnjdxyry.tracord_backend.domain.User;
import com.mbsnjdxyry.tracord_backend.service.PassageService;
import com.mbsnjdxyry.tracord_backend.service.UploadService;
import com.mbsnjdxyry.tracord_backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class TracordBackendApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private PassageService passageService;

    @Test
    public void TestBCryptPasswordEncoder(){

//        $2a$10$npv5JSeFR6/wLz8BBMmSBOMb8byg2eyfK4/vvoBk3RKtTLBhIhcpy

        System.out.println(passwordEncoder.
                matches("1234",
                        "$2a$10$F48Sii8ktK9GTlAxbqjtKOzPMhKjxdvTYPuOdHA34IxdIY84NTOQG"));
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);

//        String encode2 = passwordEncoder.encode("1234");
//        System.out.println(encode2);

    }

    //测试注册
    @Test
    public void TestUserRegister(){
        User user = new User();
        user.setAccount("516165");
        user.setPassword("465");
        System.out.println(userService.userRegister(user));

    }

    //测试关注用户
    @Test
    public void TestFollowUser(){
        System.out.println(userService.followUser(1,2));
    }

    //测试取消关注用户
    @Test
    public void TestUnFollowUser(){
        System.out.println(userService.unfollowUser(1,2));
    }

    //测试获取关注列表
    @Test
    public void TestGetUserFollowList(){
        System.out.println(userService.getUserFollowList(1));
    }

    //测试获取关注数量
    @Test
    public void TestGetUserFollowCount(){
        System.out.println(userService.getUserFollowCount(1));
    }


    //测试登录
    @Test
    public void TestUserLogin(){
        User user = new User();
        user.setAccount("13813813813");
        user.setPassword("123");
        System.out.println(userService.userLogin(user));
    }

    //测试根据用户手机号登录
    @Test
    public void TestUserLoginByAccount(){
        System.out.println(userService.userLoginByAccount("13813813813"));
    }

    //测试退出登录
    @Test
    public void TestUserLogout(){
        System.out.println(userService.userLogout());
    }

    //测试查询用户信息
    @Test
    public void TestGetUserInfo(){
        System.out.println(userService.getUserInfo());
    }

    //测试更新用户信息
    @Test
    public void TestUpdateUserInfo(){
        User user = new User();
        user.setId(1);
        user.setPassword("546");
        System.out.println(userService.updateUserInfo(user));
    }



    //测试上传图片到oss
    @Test
    public void TestUploadImg(){
        // 构造一个 MockMultipartFile 对象
        String fileName = "test.jpg";
        String contentType = "image/jpeg";
        byte[] content = new byte[]{1, 2, 3};
        MultipartFile file = new MockMultipartFile(fileName, fileName, contentType, content);

        // 调用被测试的方法
        System.out.println(uploadService.uploadImg(file));
    }

    //测试获取帖子
    @Test
    public void TestGetPassageById(){
        PassageInfo passageInfo = new PassageInfo();
        passageInfo.setId(1);
        // 调用被测试的方法
        System.out.println(passageService.getPassageById(passageInfo));
    }

    //测试删除帖子
    @Test
    public void TestDeletePassage(){
        PassageInfo passageInfo = new PassageInfo();
        passageInfo.setId(1);
        // 调用被测试的方法
        System.out.println(passageService.deletePassage(passageInfo));
    }

    //测试点赞帖子
    @Test
    public void TestFollowPassage(){
        // 调用被测试的方法
        System.out.println(passageService.followPassage(1,2));
    }

    //测试获取所有帖子
    @Test
    public void TestGetPassageList(){
        // 调用被测试的方法
        System.out.println(passageService.getPassageList());
    }

    //测试发布帖子
    @Test
    public void TestPublishPassage(){
        PassageInfo passageInfo = new PassageInfo();
        passageInfo.setUserId(1);
        passageInfo.setAddress("dfaadf");
        // 调用被测试的方法
        System.out.println(passageService.publishPassage(passageInfo));
    }

    //测试获取帖子的点赞数
    @Test
    public void TestGetPassageFollowerNum(){
        PassageInfo passageInfo = new PassageInfo();
        passageInfo.setId(1);
        // 调用被测试的方法
        System.out.println(passageService.getPassageFollowerNum(passageInfo));
    }

    //测试获取某个地点的帖子
    @Test
    public void TestGetPassageListByAddress(){
        // 调用被测试的方法
        System.out.println(passageService.getPassageListByAddress("南京"));
    }

    //测试获取某个用户所有文章的点赞数
    @Test
    public void TestGetTotalFollowerNum(){
        // 调用被测试的方法
        System.out.println(passageService.getTotalFollowerNum(1));
    }

    //测试获取某个用户所有文章
    @Test
    public void TestGetUserPassageList(){
        // 调用被测试的方法
        System.out.println(passageService.getUserPassageList());
    }

    //测试获取某个用户所有文章的总数
    @Test
    public void TestGetUserPassageNum(){
        // 调用被测试的方法
        System.out.println(passageService.getUserPassageNum(1));
    }

    //测试获取取消点赞某个文章
    @Test
    public void TestUnfollowPassage(){
        // 调用被测试的方法
        System.out.println(passageService.unfollowPassage(1,2));
    }
}