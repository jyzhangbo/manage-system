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
@TableName("t_device")
@Builder
public class Device implements Serializable {

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
     * 公司名称
     */
    private String companyName;

    /**
     * 设备图片
     */
    private String deviceImg;

    /**
     * 采集间隔 =
     */
    private Integer collectSpace;

    private String attributeInfo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 控制内容
     */
    private String controlData;

    public static final String ID = "id";

    public static final String DEVICE_NUM = "device_num";

    public static final String DEVICE_NAME = "device_name";

    public static final String COMPANY_NUM = "company_num";

    public static final String COMPANY_NAME = "company_name";

    public static final String DEVICE_IMG = "device_img";

    public static final String COLLECT_SPACE = "collect_space";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_TIME = "modify_time";

    public static final String CONTROL_DATA = "control_data";


}
