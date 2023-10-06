package com.zbbmeta.seatastudy.service.impl;

import com.zbbmeta.seatastudy.entity.AccountFreeze;
import com.zbbmeta.seatastudy.mapper.AccountFreezeMapper;
import com.zbbmeta.seatastudy.mapper.AccountMapper;
import com.zbbmeta.seatastudy.service.AccountTccService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Service
public class AccountTccServiceImpl implements AccountTccService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountFreezeMapper accountFreezeMapper;


    @Transactional
    @Override
    public boolean deduct(String userId, int money) {
//获取事务id
        String xid = RootContext.getXID();
        //处理业务悬挂
        AccountFreeze freeze = accountFreezeMapper.selectById(xid);
        if (freeze != null) {
            //已经cancel过，拒绝业务
            return false;
        }

        //1 扣减余额
        accountMapper.deduct(userId, money);

        //2 记录冻结金额
        AccountFreeze accountFreeze = new AccountFreeze();
        accountFreeze.setXid(xid);
        accountFreeze.setUserId(userId);
        accountFreeze.setFreezeMoney(money);
        accountFreeze.setState(AccountFreeze.State.TRY);
        return accountFreezeMapper.insert(accountFreeze)>0? true:false;
    }

    @Override
    public boolean confirmAccount(BusinessActionContext ctx) {
        //删除冻结金额
        String xid = ctx.getXid();
        int count = accountFreezeMapper.deleteById(xid);
        return count==1;
    }

    @Override
    public boolean cancelAccount(BusinessActionContext ctx) {
        //获取全局事务id
        String xid = ctx.getXid();
        //用户id
        String userId = ctx.getActionContext("userId").toString();
        AccountFreeze accountFreeze = accountFreezeMapper.selectById(xid);

        //处理空回滚
        if (accountFreeze == null) {
            accountFreeze = new AccountFreeze();
            accountFreeze.setXid(xid);
            accountFreeze.setUserId(userId);
            accountFreeze.setFreezeMoney(0);
            accountFreeze.setState(AccountFreeze.State.CANCEL);
            accountFreezeMapper.insert(accountFreeze);
            return true;
        }

        //幂等处理
        if (accountFreeze.getState() == AccountFreeze.State.CANCEL) {
            //说明已经回滚过了；不需要再处理
            return true;
        }

        //1 回退金额
        accountMapper.refund(userId, accountFreeze.getFreezeMoney());

        //2 更新冻结金额为0和状态
        accountFreeze.setFreezeMoney(0);
        accountFreeze.setState(AccountFreeze.State.CANCEL);
        int count = accountFreezeMapper.updateById(accountFreeze);

        return count==1;
    }
}
