package com.github.managesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_task_device_attribute")
@Builder
public class TaskDeviceAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务编号
     */
    private String taskNum;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备图片
     */
    private String deviceImg;

    /**
     * 属性名
     */
    private String attributeCode;

    /**
     * 属性信息
     */
    private String attributeInfo;

    /**
     * 属性别名
     */
    private String attributeName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 任务名称
     */
    private String taskName;


    public static final String ID = "id";

    public static final String TASK_NUM = "task_num";

    public static final String DEVICE_NUM = "device_num";

    public static final String DEVICE_NAME = "device_name";

    public static final String DEVICE_IMG = "device_img";

    public static final String ATTRIBUTE_CODE = "attribute_code";

    public static final String ATTRIBUTE_NAME = "attribute_name";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_TIME = "modify_time";

    public static final String TASK_NAME = "task_name";

}
