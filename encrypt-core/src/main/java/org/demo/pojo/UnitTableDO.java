package org.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author andyXu xu9529@gmail.com
 * @date 2020/3/30
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@Data
public class UnitTableDO {

    private int id;

    private Date gmt_create;

    private Date gmt_update;

    private String card_no;

    private String phone;

    private String name;

    private String id_no;

    private Long aaa001;

    private String aaa002;

    private String aaa003;

    private String aaa004;

    private String aaa005;

    private String aaa006;
}
