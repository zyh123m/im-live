package com.example.common;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.generator.service.IdGeneratorRpc;
import org.springframework.stereotype.Component;

@Component
public class ImLiveIdGenerator implements IdentifierGenerator {


    @DubboReference
    IdGeneratorRpc generatorRpc;
    @Override
    public Number nextId(Object entity) {
        String bizKey = entity.getClass().getName();

        Long seqId = generatorRpc.getSeqId(bizKey.hashCode()%300);
        return seqId;
    }
}
