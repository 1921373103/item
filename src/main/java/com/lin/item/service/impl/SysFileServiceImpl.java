package com.lin.item.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lin.item.common.exception.CustomException;
import com.lin.item.common.util.SecurityUtil;
import com.lin.item.dao.SysFileDao;
import com.lin.item.entity.SysFile;
import com.lin.item.service.ISysFileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: L
 * @Date: 2023/3/9 18:27
 * @Desc: 图片附件 - IMPL
 */
@Service("sysFileServiceImpl")
public class SysFileServiceImpl extends ServiceImpl<SysFileDao, SysFile> implements ISysFileService {

    @Autowired
    private SysFileDao sysFileDao;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * fds上传地址
     */
    @Value("${fdfs.host}")
    private String host;

    /**
     * fds上传组
     */
    @Value("${fdfs.group}")
    private String group;

    /**
     * 上传图片
     * @param file 图片
     * @return
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public SysFile upload(MultipartFile file) throws Exception {
        // 获取文件后缀名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 将要上传的文件存入FastDFS
        StorePath sp = fastFileStorageClient.uploadFile(group,file.getInputStream(),file.getSize(),extension);
        // 存储图片到数据库
        SysFile sysFile = new SysFile();
        sysFile.setFileName(file.getOriginalFilename());
        sysFile.setFileGroup(group);
        sysFile.setFileUrl(host + sp.getFullPath());
        sysFile.setCreateBy(SecurityUtil.getSysUserLogin());
        sysFile.setCreateTime(DateUtil.date());
        if (!save(sysFile)) {
            throw new CustomException("图片上传失败!");
        }
        return sysFile;
    }
}
