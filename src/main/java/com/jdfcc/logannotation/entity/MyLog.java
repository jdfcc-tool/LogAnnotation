package com.jdfcc.logannotation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jdfcc
 * @Description MyLog
 * @DateTime 2023/6/26 14:45
 */
@Data
@NoArgsConstructor
public class MyLog implements Serializable {

    /**
     * 日志内容
     */
    private String  value;
    /**
     * 花费时间
     */
    private Long time;

}
