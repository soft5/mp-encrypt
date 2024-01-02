package org.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.demo.type.CryptTypeHandler;

import java.io.Serializable;

/**
 * <p>
 * AA01(综合参数)
 * </p>
 *
 * @author hxq
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "AA01", autoResultMap = true)
@KeySequence(value="SEQ_AAZ179",clazz = Long.class)
public class Aa01 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AAA000", type = IdType.INPUT)
    private Long aaa000;

    @TableField("AAA001")
    private Integer aaa001;

    @TableField(value = "AAA002", typeHandler = CryptTypeHandler.class)
    private String aaa002;

    @TableField("AAA003")
    private String aaa003;

    @TableField("AAA004")
    private String aaa004;

    @TableField(value = "AAA005")
    private String aaa005;

    @TableField(value = "AAA006", typeHandler = CryptTypeHandler.class)
    private String aaa006;
}
