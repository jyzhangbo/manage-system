package com.github.managesystem.service;

import com.github.managesystem.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.ListTaskResp;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
public interface ITaskService extends IService<Task> {

    ListTaskResp listTask(ListTaskReq req, HttpServletRequest request);

    void deleteTask(DeleteTaskReq req);

    void editTask(EditTaskReq req);

    void addTask(AddTaskReq req, HttpServletRequest request) throws CodeException;

    String asertTaskNum(String taskNum, HttpServletRequest request) throws CodeException;

    List<TaskDevice> listTaskSearch(ListTaskSearchReq req, HttpServletRequest request);
}
