package com.zbbmeta.seatastudy.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface AccountTccService {

    /**
     * 根据用户id扣减余额，记录冻结金额
     * @param userId 用户id
     * @param money  扣减金额
     * @return
     */
    @TwoPhaseBusinessAction(name = "deduct",
                            commitMethod = "confirmAccount",
                            rollbackMethod = "cancelAccount")
    boolean deduct(@BusinessActionContextParameter("userId")String userId,
                   @BusinessActionContextParameter("money")int money);

    /**
     * 执行TCC事务，提交事务时执行方法
     * @param ctx
     * @return
     */
    boolean confirmAccount(BusinessActionContext ctx);

    /**
     * 执行TCC事务，回滚事务时执行方法
     * @param ctx
     * @return
     */
    boolean cancelAccount(BusinessActionContext ctx);
}
