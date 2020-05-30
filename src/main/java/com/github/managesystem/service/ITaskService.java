package com.github.managesystem.service;

import com.github.managesystem.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.AddTaskReq;
import com.github.managesystem.model.req.DeleteTaskReq;
import com.github.managesystem.model.req.EditTaskReq;
import com.github.managesystem.model.req.ListTaskReq;
import com.github.managesystem.model.resp.ListTaskResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
public interface ITaskService extends IService<Task> {

    ListTaskResp listTask(ListTaskReq req);

    void deleteTask(DeleteTaskReq req);

    void editTask(EditTaskReq req);

    void addTask(AddTaskReq req) throws CodeException;

    String asertTaskNum(String taskNum) throws CodeException;
}
