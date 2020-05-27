package com.github.managesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
@TableName("t_device_attribute")
public class DeviceAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 属性别名
     */
    private String attributeName;

    /**
     * 属性名
     */
    private String attributeCode;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


    public static final String ID = "id";

    public static final String DEVICE_NUM = "device_num";

    public static final String DEVICE_NAME = "device_name";

    public static final String ATTRIBUTE_NAME = "attribute_name";

    public static final String ATTRIBUTE_CODE = "attribute_code";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_TIME = "modify_time";

}
