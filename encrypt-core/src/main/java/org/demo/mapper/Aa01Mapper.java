package org.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.demo.entity.Aa01;

/**
 * @program: mp-encrypt
 * @description:
 * @author: o2o_o2o
 * @create: 2023-12-28 15:07
 */
@Mapper
public interface Aa01Mapper extends BaseMapper<Aa01> {

    void insertAa01(Aa01 aa01);
}

