package org.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.demo.entity.BankCard;
import org.demo.pojo.BankCardDO;
import org.demo.pojo.UnitTableDO;
import org.demo.type.CryptTypeHandler;

import java.util.List;

/**
 * @author andyXu xu9529@gmail.com
 * @date 2020/3/30
 * https://blog.csdn.net/heartsdance/article/details/119734906
 */
@Mapper
public interface BankCardMapper extends BaseMapper<BankCard> {

    @Results(id = "bankCard", value = {
            // 通过指定 typeHandler 进行解密
            @Result(property = "card_no", column = "card_no", typeHandler = CryptTypeHandler.class),
            // 通过指定 javaType 进行解密
            @Result(property = "phone", column = "phone", typeHandler = CryptTypeHandler.class),
            @Result(property = "name", column = "name", typeHandler = CryptTypeHandler.class),
            @Result(property = "id_no", column = "id_no", typeHandler = CryptTypeHandler.class),
    })
    @Select("select * from bank_card where id=#{id}")
    BankCardDO queryById(int id);

    // 没有的列，会排除加解密
//    @ResultMap(value="unitXml")
    @ResultMap(value="bankCard")
    @Select("select * from bank_card where card_no=#{card_no,javaType=crypt}")
    List<BankCardDO> queryByCardNo(String card_no);

    @Results(id = "unitXml", value = {
            // 通过指定 typeHandler 进行解密
            @Result(property = "card_no", column = "card_no", typeHandler = CryptTypeHandler.class),
            // 通过指定 javaType 进行解密
            @Result(property = "phone", column = "phone", typeHandler = CryptTypeHandler.class),
            @Result(property = "name", column = "name", typeHandler = CryptTypeHandler.class),
            @Result(property = "id_no", column = "id_no", typeHandler = CryptTypeHandler.class),
            @Result(property = "aaa002", column = "AAA002", typeHandler = CryptTypeHandler.class),
            @Result(property = "aaa005", column = "AAA005", typeHandler = CryptTypeHandler.class)
    })
    @Select("select a.*, b.AAA002, b.AAA003, b.AAA004, b.AAA005, b.AAA006 from bank_card a, aa01 b where a.id = b.AAA001 AND a.phone in(#{phone,javaType=crypt})")
    List<UnitTableDO> queryUnitTable(@Param("phone") String phone);

    // 获取自增 id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO bank_card (card_no, phone,name,id_no) " +
            "VALUES (#{card.card_no,javaType=crypt}, #{card.phone,typeHandler=org.demo.type.CryptTypeHandler},#{card.name,javaType=crypt},#{card.id_no,javaType=crypt})")
    void insertBankCard(@Param("card") BankCardDO cardDO);
}
