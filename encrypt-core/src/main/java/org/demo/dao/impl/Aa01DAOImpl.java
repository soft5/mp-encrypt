package org.demo.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.demo.dao.Aa01DAO;
import org.demo.entity.Aa01;
import org.demo.mapper.Aa01Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: mp-encrypt
 * @description:
 * @author: o2o_o2o
 * @create: 2023-12-28 15:04
 */
@Service(value = "Aa01DAO")
public class Aa01DAOImpl extends ServiceImpl<Aa01Mapper, Aa01> implements Aa01DAO {

    @Autowired
    private Aa01Mapper aa01Mapper;
}
