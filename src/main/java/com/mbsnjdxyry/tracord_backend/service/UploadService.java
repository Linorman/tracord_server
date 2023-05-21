package com.mbsnjdxyry.tracord_backend.service;

import com.mbsnjdxyry.tracord_backend.common.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
