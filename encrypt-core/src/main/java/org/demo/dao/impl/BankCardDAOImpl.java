package org.demo.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.demo.dao.BankCardDAO;
import org.demo.entity.BankCard;
import org.demo.mapper.BankCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: mp-encrypt
 * @description:
 * @author: o2o_o2o
 * @create: 2023-12-28 15:04
 */
@Service(value = "BankCardDAO")
public class BankCardDAOImpl extends ServiceImpl<BankCardMapper, BankCard> implements BankCardDAO {

    @Autowired
    private BankCardMapper aa01Mapper;
}
