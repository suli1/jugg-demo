package com.small.lib.utils.logger;


/**
 * 云存储接口
 * TODO 后期对日志上传的功能可实现该接口
 */
public interface IStorage {

    /**
     * 上传日志.
     *
     * @param logger Logger
     */
    void upload(Logger logger);
}
