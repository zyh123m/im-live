package com.example.generator;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.generator.service.IdGeneratorRpc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImLiveMsgProviderApplicationTests {

    @DubboReference
    IdGeneratorRpc generatorRpc;
    @Test
    public void getNextId(){

        for (int i = 800; i < 1000; i++) {
            Long seqId = generatorRpc.getSeqId(8);
            System.out.println(seqId);
        }


    }





}
