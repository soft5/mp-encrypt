package org.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.demo.type.CryptTypeHandler;

import java.io.Serializable;
import java.util.Date;

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
@TableName(value = "BANK_CARD", autoResultMap = true)
@KeySequence(value="SEQ_AAZ179",clazz = Long.class)
public class BankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_update")
    private Date gmtUpdate;

    @TableField(value = "card_no", typeHandler = CryptTypeHandler.class)
    private String cardNo;

    @TableField(value = "phone", typeHandler = CryptTypeHandler.class)
    private String phone;

    @TableField(value = "name", typeHandler = CryptTypeHandler.class)
    private String name;

    @TableField(value = "id_no", typeHandler = CryptTypeHandler.class)
    private String idNo;
}
