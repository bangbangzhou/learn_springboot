package com.zbbmeta.task.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Component
public class TestJob {
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("TestXXLJob")
    public ReturnT<String> jobHandler(String param) throws Exception {
        System.out.println("每5秒吃一次饭,我要吃成一个胖子..." + new Date());
        return ReturnT.SUCCESS;
    }
}
