package com.example.fetch;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.oss.MinioUtils;
import com.example.oss.response.Result;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.user.dto.UserDTO;
import org.example.user.service.UserRpcService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
class BasicFetchApplicationTests {
    int i =1600;
    @Resource
    RestTemplate restTemplate;
    @Resource
    MongoTemplate mongoTemplate;

    @DubboReference
    UserRpcService userRpcService;
    @Resource
    private MinioUtils minioUtils;


    @Test
    void contextLoads() throws Exception {
        for (int i = 40; i < 200; i++) {
            fetch(i,15);
            Thread.sleep(2000);
        }

    }

    private void fetch(int pageNo,int pageSize) throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        JSONObject ret = new JSONObject();
        HttpEntity requestEntity2 = new HttpEntity<>(ret, httpHeaders);
        ResponseEntity res = restTemplate.exchange("https://play.daidaidj.com/recommend/api/god/homeRecommendForPC?page="+pageNo+"&size="+pageSize+"&loading=true&more=true", HttpMethod.GET,requestEntity2, JSONObject.class);
        JSONObject response = (JSONObject) res.getBody();
        System.out.println(response);
        JSONArray data = response.getJSONArray("data");
        for (Object datum : data) {


            JSONObject object = (JSONObject) datum;
            String headPic = object.getStr("headPic");
            String voiceUrl = object.getStr("voiceUrl");

            //mongoTemplate.insert(object, "user");
            UserDTO user = UserDTO.builder()
                    .username("username" + i)
                    .password("password" + i)
                    .build();
            user.setName(object.getStr("nickname"));
            user.setEmail("user" + i + "@example.com");
            user.setPhone("18888888888");
            user.setGender(object.getInt("sex"));
            user.setAge(object.getInt("age"));
            user.setAvatar("https://avatar.png");
            user.setLastLoginTime(new Date());
            user.setStatus(1);
            user.setVipExpireTime(new Date());
            user.setCity(object.getStr("city"));
            user.setBalance(object.getBigDecimal("price"));
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setSign(object.getStr("sign"));
            user.setVoiceUrl(uploadFile(voiceUrl));
            user.setAvatar(uploadFile(headPic));
            try {
                userRpcService.insertUser(user);
                i++;
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }

    public String uploadFile(String path) throws Exception {
        try {
        String fullPath="https://img-play.daidaidj.com/img/"+path;

        ResponseEntity<byte[]> response = restTemplate.getForEntity(fullPath, byte[].class);
        byte[] imageBytes = response.getBody();
        if (imageBytes == null || imageBytes.length == 0) {
            throw new Exception("Failed to fetch image data from URL");
        }
        InputStream inputStream = new ByteArrayInputStream(imageBytes);

        minioUtils.uploadFile("music", path,inputStream);
        String url = minioUtils.getPresignedObjectUrl("music", path);
        String[] split = url.split("\\?");
            return split[0];
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

}
