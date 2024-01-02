package org.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.demo.entity.Aa01;
import org.demo.entity.BankCard;
import org.demo.pojo.BankCardDO;
import org.demo.pojo.UnitTableDO;

import java.util.List;

/**
 * @author andyXu xu9529@gmail.com
 * @date 2020/4/1
 */
@Mapper
public interface BankCardXmlMapper extends BaseMapper<BankCard> {

    BankCardDO queryById(int id);

    List<BankCardDO> queryByCardNo(String card_no);

    BankCardDO queryByPhone(String phone);

    List<UnitTableDO> queryUnitTable(String phone);

    void insertBankCard(BankCardDO cardDO);
}
