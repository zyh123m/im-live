package com.example.generator.service;

import com.example.generator.entity.IdGenerate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 13057
* @description 针对表【t_id_generate_config】的数据库操作Service
* @createDate 2023-12-26 09:58:10
*/
public interface IdGenerateService extends IService<IdGenerate> {
    /**
     * 获取有序id
     *
     * @param id
     * @return
     */
    Long getSeqId(Integer id);

    /**
     * 获取无序id
     *
     * @param id
     * @return
     */
    Long getUnSeqId(Integer id);

}
