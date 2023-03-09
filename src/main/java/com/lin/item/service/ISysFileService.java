package com.lin.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.item.common.entity.IPage;
import com.lin.item.entity.SysFile;
import com.lin.item.vo.SysFileVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: L
 * @Date: 2023/3/9 18:26
 * @Desc: 图片附件 - SERVICE
 */
public interface ISysFileService extends IService<SysFile> {


    /**
     * 上传图片
     * @param file 图片
     * @return 结果
     */
    SysFile upload(MultipartFile file) throws Exception;

    /**
     * 获取所有图片(带分页)
     */
    IPage<SysFile> queryPage(SysFileVo sysFileVo);
}
