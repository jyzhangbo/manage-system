package com.github.managesystem.service.impl;

import com.github.managesystem.entity.Task;
import com.github.managesystem.mapper.TaskMapper;
import com.github.managesystem.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
