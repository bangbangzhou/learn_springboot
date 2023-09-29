package com.zbbmeta.seatastudy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zbbmeta.seatastudy.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


public interface AccountMapper extends BaseMapper<Account> {

    @Update("update tb_account set money = money - ${money} where user_id = #{userId}")
    int deduct(@Param("userId") String userId, @Param("money") int money);

    @Update("update tb_account set money = money + ${money} where user_id = #{userId}")
    int refund(@Param("userId") String userId, @Param("money") int money);
}
