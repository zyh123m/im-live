package com.example.generator.rpc;

import com.example.generator.service.IdGenerateService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.generator.service.IdGeneratorRpc;
import org.springframework.stereotype.Service;

@Slf4j
@DubboService
public class IdGeneratorImpl implements IdGeneratorRpc {

    @Resource
    IdGenerateService generateService;
    @Override
    public Long getSeqId(Integer id) {
        return generateService.getSeqId(id);
    }

    @Override
    public Long getUnSeqId(Integer id) {
        return generateService.getUnSeqId(id);
    }
}
