package org.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DistributedLockHandler
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/1 13:55
 * @Version 1.0
 */

public  class  DistributedLockHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockHandler.class);

    /**
     * 最大持有锁的时间(毫秒)
     */
    private final static long LOCK_EXPIRE = 30 * 1000L;

    /**
     * 尝试获取锁的时间间隔（毫秒）
     */
    private final static long LOCK_TRY_INTERVAL = 30L;

    /**
     * 获取锁最大等待时间( 毫秒 )
     */
    private final static long LOCK_TRY_TIMEOUT = 100L;

    @Resource
    private static RedisTemplate<String, String> template;

    /**
     * 尝试获取 分布式锁
     *
     * @param lockKey
     *            锁名
     * @return true 得到了锁 ，false 获取锁失败
     */
    public boolean tryLock(String lockKey) {
        return getLock(lockKey, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_TRY_INTERVAL);
    }

    /**
     * 尝试获取 分布式锁（不自动释放锁）
     *
     * @param lockKey
     *            锁名
     * @return true 得到了锁 ，false 获取锁失败
     */
    public boolean tryLockNotAutoRelease(String lockKey) {
        return getLock(lockKey, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, -1);
    }

    /**
     * 尝试获取 分布式锁
     *
     * @param lockKey
     *            锁名
     * @param timeout
     *            获取锁最大等待时间
     * @return true 得到了锁 ，false 获取锁失败
     */
    public boolean tryLock(String lockKey, long timeout) {
        return getLock(lockKey, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取 分布式锁（不自动释放锁）
     *
     * @param lockKey
     *            锁名
     * @param timeout
     *            获取锁最大等待时间
     * @return true 得到了锁 ，false 获取锁失败
     */
    public boolean tryLockNotAutoRelease(String lockKey, long timeout) {
        return getLock(lockKey, timeout, LOCK_TRY_INTERVAL, -1);
    }

    /**
     * 尝试获取 分布式锁
     *
     * @param lockKey
     *            锁名
     * @param timeout
     *            获取锁最大等待时间
     * @param tryInterval
     *            获取锁尝试 时间间隔
     * @return true 得到了锁 ，false 获取锁失败
     */
    public boolean tryLock(String lockKey, long timeout, long tryInterval) {
        return getLock(lockKey, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取 分布式锁（不释放锁）
     *
     * @param lockKey
     *            锁名
     * @param timeout
     *            获取锁最大等待时间
     * @param tryInterval
     *            获取锁尝试 时间间隔
     * @return true 得到了锁 ，false 获取锁失败
     */
    public boolean tryLockNotAutoRelease(String lockKey, long timeout, long tryInterval) {
        return getLock(lockKey, timeout, tryInterval, -1);
    }

//    /**
//     * 尝试获取 分布式锁
//     *
//     * @param lockKey
//     *            锁名
//     * @param timeout
//     *            获取锁最大等待时间
//     * @param tryInterval
//     *            获取锁尝试 时间间隔
//     * @param lockExpireTime
//     *            锁最大持有时间
//     * @return true 得到了锁 ，false 获取锁失败
//     */
//    public boolean tryLock(String lockKey, long timeout, long tryInterval, long lockExpireTime) {
//        return getLock(lockKey, timeout, tryInterval, lockExpireTime);
//    }

    /**
     * 尝试获取 分布式锁
     *
     * @param lockKey
     *            锁名
     * @param timeout
     *            获取锁最大等待时间
     * @param tryInterval
     *            获取锁尝试 时间间隔
     * @param lockExpireTime
     *            锁最大持有时间
     * @return true 得到了锁 ，false 获取锁失败
     */
    public static boolean tryLock2(String lockKey) {
        return getLock(lockKey,LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 获取分布式锁
     *
     * @param lockKey
     *            锁名
     * @param timeout
     *            获取锁最大等待时间
     * @param tryInterval
     *            获取锁尝试 时间间隔
     * @param lockExpireTime
     *            锁最大持有时间
     * @return true 得到了锁 ，false 获取锁失败
     */
    public static boolean getLock(String lockKey, long timeout, long tryInterval, long lockExpireTime) {
        try {
            if (StringUtils.isEmpty(lockKey)) {
                return false;
            }
            long startTime = System.currentTimeMillis();
            do {
                ValueOperations<String, String> ops = template.opsForValue();
                if (ops.setIfAbsent(lockKey, "lockValue")) {
                    if (lockExpireTime > 0) {
                        template.expire(lockKey, lockExpireTime, TimeUnit.MILLISECONDS);
                    }
                    return true;
                }
                Thread.sleep(tryInterval);
            } while (System.currentTimeMillis() - startTime < timeout);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     */
    public void unLock(String lockKey) {
        if (!StringUtils.isEmpty(lockKey)) {
            template.delete(lockKey);
        }
    }
}