package com.example.generator.mapper;

import com.example.generator.entity.IdGenerate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 13057
* @description 针对表【t_id_generate_config】的数据库操作Mapper
* @createDate 2023-12-26 09:58:10
* @Entity com.example.generator.entity.IdGenerate
*/
public interface IdGenerateMapper extends BaseMapper<IdGenerate> {
    @Update("update t_id_generate_config set next_threshold=next_threshold+step," +
            "current_start=current_start+step,version=version+1 where id =#{id} and version=#{version}")
    int updateNewIdCountAndVersion(@Param("id")int id, @Param("version")int version);

    @Select("select * from t_id_generate_config")
    List<IdGenerate> selectAll();

}




