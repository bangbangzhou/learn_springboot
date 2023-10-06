package com.zbbmeta.seatastudy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zbbmeta.seatastudy.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


public interface StorageMapper extends BaseMapper<Storage> {
    @Update("update tb_storage set `count` = `count` - #{count} where commodity_code = #{code}")
    int deduct(@Param("code") String commodityCode, @Param("count") int count);
}
