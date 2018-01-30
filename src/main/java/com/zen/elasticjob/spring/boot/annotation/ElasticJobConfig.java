package com.zen.elasticjob.spring.boot.annotation;



import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 作业配置，具体参见文档 http://elasticjob.io/docs/elastic-job-lite/02-guide/config-manual
 *
 * @author xinjingziranchan@gmail.com
 * @version 1.0.0
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ElasticJobConfig {

    /**
     * cron表达式，用于控制作业触发时间
     */
    String cron();

    /**
     * 作业分片总
     */
    int shardingTotalCount() default 1;

    /**
     * 分片序列号和个性化参数对照表.
     * 分片序列号和参数用等号分隔, 多个键值对用逗号分隔. 类似map.
     * 分片序列号从0开始, 不可大于或等于作业分片总数.
     * 如:
     * 0=a,1=b,2=c
     */
    String shardingItemParameters() default "";

    /**
     * 作业自定义参数.
     * 作业自定义参数，可通过传递该参数为作业调度的业务方法传参，用于实现带参数的作业
     * 例：每次获取的数据量、作业实例从数据库读取的主键等
     */
    String jobParameter() default "";

    /**
     * 是否开启任务执行失效转移，开启表示如果作业在一次任务执行中途宕机，
     * 允许将该次未完成的任务在另一作业节点上补偿执行
     */
    boolean failover() default false;

    /**
     * 是否开启错过任务重新执行
     */
    boolean misfire() default true;

    /**
     * 作业描述信息.
     */
    String description() default "";

    /**
     * 配置jobProperties定义的枚举控制Elastic-Job的实现细节
     * JOB_EXCEPTION_HANDLER用于扩展异常处理类
     */
    String jobExceptionHandler() default "";

    /**
     * 配置jobProperties定义的枚举控制Elastic-Job的实现细节
     * EXECUTOR_SERVICE_HANDLER用于扩展作业处理线程池类
     */
    String executorServiceHandler() default "";

    /**
     * 是否流式处理数据
     * 如果流式处理数据, 则fetchData不返回空结果将持续执行作业
     * 如果非流式处理数据, 则处理数据完成后作业结
     */
    boolean streamingProcess() default false;

    /**
     * 脚本型作业执行命令行
     */
    String scriptCommandLine() default "";

    /**
     * 监控作业运行时状态
     * 每次作业执行时间和间隔时间均非常短的情况，建议不监控作业运行时状态以提升效率。
     * 因为是瞬时状态，所以无必要监控。请用户自行增加数据堆积监控。并且不能保证数据重复选取，应在作业中实现幂等性。
     * 每次作业执行时间和间隔时间均较长的情况，建议监控作业运行时状态，可保证数据不会重复选取。
     */
    boolean monitorExecution() default true;

    /**
     * 作业监控端口
     * 建议配置作业监控端口, 方便开发者dump作业信息。
     * 使用方法: echo “dump” | nc 127.0.0.1 9888
     */
    int monitorPort() default -1;

    /**
     * 最大允许的本机与注册中心的时间误差秒数
     * 如果时间误差超过配置秒数则作业启动时将抛异常
     * 配置为-1表示不校验时间误差
     */
    int maxTimeDiffSeconds() default -1;

    /**
     * 作业分片策略实现类全路径
     * 默认使用平均分配策略
     * 详情参见：作业分片策略http://elasticjob.io/docs/elastic-job-lite/02-guide/job-sharding-strategy
     */
    String jobShardingStrategyClass() default "";

    /**
     * 修复作业服务器不一致状态服务调度间隔时间，配置为小于1的任意值表示不执行修复
     * 单位：分钟
     */
    int reconcileIntervalMinutes() default 10;

    /**
     * 作业事件追踪的数据源Bean引用
     */
    String eventTraceRdbDataSource() default "";

    /**
     * 本地配置是否可覆盖注册中心配置.
     * 如果可覆盖, 每次启动作业都以本地配置为准.
     */
    boolean overwrite() default true;

    /**
     * 作业是否禁止启动
     * 可用于部署作业时，先禁止启动，部署结束后统一启动
     */
    boolean disabled() default false;
}