package org.demo;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.assertj.core.util.Lists;
import org.demo.dao.Aa01DAO;
import org.demo.dao.BankCardDAO;
import org.demo.entity.Aa01;
import org.demo.entity.BankCard;
import org.demo.mapper.Aa01Mapper;
import org.demo.mapper.BankCardMapper;
import org.demo.mapper.BankCardXmlMapper;
import org.demo.pojo.BankCardDO;
import org.demo.pojo.UnitTableDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@SpringBootTest
class MybatisDemoApplicationTests {

    @Autowired
    BankCardMapper bankCardMapper;

    @Autowired
    BankCardXmlMapper bankCardXmlMapper;

    @Autowired
    Aa01Mapper aa01Mapper;

    @Autowired
    private BankCardDAO bankCardDAO;

    @Autowired
    private Aa01DAO aa01DAO;

    @Test
    public void test() {
        BankCardDO bankCardDO = BankCardDO
                .builder()
                .card_no("64321231231")
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        bankCardMapper.insertBankCard(bankCardDO);

        // 查询数据
        BankCardDO result = bankCardMapper.queryById(bankCardDO.getId());
        List<BankCardDO> bankCardDOS = bankCardMapper.queryByCardNo(bankCardDO.getCard_no());

        Assertions.assertEquals(bankCardDO.getCard_no(), result.getCard_no());
        Assertions.assertEquals(bankCardDO.getName(), result.getName());
        Assertions.assertEquals(bankCardDO.getId_no(), result.getId_no());
        Assertions.assertEquals(bankCardDO.getPhone(), result.getPhone());
    }

    @Test
    public void testXml() {
        BankCardDO bankCardDO = BankCardDO
                .builder()
                .card_no("64321231231")
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        bankCardXmlMapper.insertBankCard(bankCardDO);

        // 查询数据
        BankCardDO result = bankCardXmlMapper.queryById(bankCardDO.getId());

        Assertions.assertEquals(bankCardDO.getCard_no(), result.getCard_no());
        Assertions.assertEquals(bankCardDO.getName(), result.getName());
        Assertions.assertEquals(bankCardDO.getId_no(), result.getId_no());
        Assertions.assertEquals(bankCardDO.getPhone(), result.getPhone());
    }

    @Test
    public void testXmlBatch() {
        BankCardDO bankCardDO = BankCardDO
                .builder()
                .card_no("64321231231")
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        BankCardDO bankCardDO1 = BankCardDO
                .builder()
                .card_no("64321231231")
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        bankCardXmlMapper.insertBankCard(bankCardDO);
        bankCardXmlMapper.insertBankCard(bankCardDO1);

        // 查询数据
        List<BankCardDO> bankCardDOS = bankCardXmlMapper.queryByCardNo(bankCardDO.getCard_no());

        // BankCard 没有配置，则无效，无法加解密
        // 测 mp 原生方法，查询
        List<BankCard> bankCardDOS1 = bankCardXmlMapper.selectBatchIds(bankCardDOS.stream().map(BankCardDO::getId).collect(Collectors.toSet()));
        BankCard bankCard = bankCardDOS1.get(0);
        bankCard.setPhone("13567891234X");
        // 修改
        bankCardXmlMapper.updateById(bankCard);
        //
        bankCardDAO.getOne(new QueryWrapper<BankCard>().eq("ID", bankCard.getId()));
        bankCard.setPhone("13567891234XX");
        bankCardDAO.updateById(bankCard);

        //
        bankCardDAO.listByIds(bankCardDOS.stream().map(BankCardDO::getId).collect(Collectors.toSet()));

        bankCard.setPhone("13567891234XXX");
        bankCard.setId(null);
        bankCardXmlMapper.insert(bankCard);

        bankCard.setPhone("13567891234XXXX");
        bankCard.setId(null);
        bankCardDAO.save(bankCard);

        List<BankCard> objects = Lists.newArrayList();
        BankCard bankCard1 = new BankCard();
        bankCard1.setGmtCreate(new Date());
        bankCard1.setGmtUpdate(new Date());
        bankCard1.setCardNo("64321231231");
        bankCard1.setPhone(bankCardDO.getPhone());
        bankCard1.setName(bankCardDO.getName());
        bankCard1.setIdNo("1231231231");
        BankCard bankCard2 = new BankCard();
        bankCard2.setGmtCreate(bankCard1.getGmtCreate());
        bankCard2.setGmtUpdate(bankCard1.getGmtUpdate());
        bankCard2.setCardNo(bankCard1.getCardNo());
        bankCard2.setPhone(bankCard1.getPhone());
        bankCard2.setName(bankCard1.getName());
        bankCard2.setIdNo(bankCard1.getIdNo());
        objects.add(bankCard1);
        objects.add(bankCard2);
        bankCardDAO.saveBatch(objects);

        aa01DAO.getOne(new QueryWrapper<Aa01>().eq("AAA001", "aa"));

        bankCardXmlMapper.queryByCardNo(bankCardDO.getCard_no());


        bankCardDOS.forEach(result -> {
            Assertions.assertEquals(bankCardDO.getCard_no(), result.getCard_no());
            Assertions.assertEquals(bankCardDO.getName(), result.getName());
            Assertions.assertEquals(bankCardDO.getId_no(), result.getId_no());
            Assertions.assertEquals(bankCardDO.getPhone(), result.getPhone());
        });

    }

    @Test
    public void testQueryByPhone(){
        BankCardDO bankCardDO = BankCardDO
                .builder()
                .card_no("64321231231")
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        bankCardXmlMapper.insertBankCard(bankCardDO);

        // 查询数据
        BankCardDO result = bankCardXmlMapper.queryByPhone("13567891234");

        Assertions.assertEquals(bankCardDO.getCard_no(), result.getCard_no());
        Assertions.assertEquals(bankCardDO.getName(), result.getName());
        Assertions.assertEquals(bankCardDO.getId_no(), result.getId_no());
        Assertions.assertEquals(bankCardDO.getPhone(), result.getPhone());


    }

    @Test
    public void testUnit() {
        BankCardDO bankCardDO = BankCardDO
                .builder()
                .card_no("64321231231")
                .gmt_create(new Date())
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        BankCard bankCard = new BankCard();
        bankCard.setGmtCreate(new Date());
        bankCard.setCardNo(bankCardDO.getCard_no());
        bankCard.setPhone(bankCardDO.getPhone());
        bankCard.setName(bankCardDO.getName());
        bankCard.setIdNo(bankCardDO.getId_no());

        BankCard bankCard1 = new BankCard();
        bankCard1.setGmtCreate(new Date());
        bankCard1.setCardNo(bankCardDO.getCard_no());
        bankCard1.setPhone(bankCardDO.getPhone());
        bankCard1.setName(bankCardDO.getName());
        bankCard1.setIdNo(bankCardDO.getId_no());

        List<BankCard> bankCardList = Lists.newArrayList();
        bankCardList.add(bankCard);
        bankCardList.add(bankCard1);
        bankCardDAO.saveBatch(bankCardList);

        List<BankCardDO> bankCardDOS = bankCardXmlMapper.queryByCardNo("64321231231");

        Aa01 aa01 = new Aa01();
        aa01.setAaa001(bankCardDOS.get(0).getId());
        aa01.setAaa002("字段2");
        aa01.setAaa003("字段3");
        aa01.setAaa004("字段4");
        aa01.setAaa005("字段5");
        aa01.setAaa006("字段6");

        Aa01 aa011 = new Aa01();
        aa011.setAaa001(bankCardDOS.get(1).getId());
        aa011.setAaa002("字段2");
        aa011.setAaa003("字段3");
        aa011.setAaa004("字段4");
        aa011.setAaa005("字段5");
        aa011.setAaa006("字段6");

        List<Aa01> aa01List = Lists.newArrayList();
        aa01List.add(aa01);
        aa01List.add(aa011);
        // 故意 实体Aa01 加密了 AAA006，而queryUnitTable没有解密
        aa01DAO.saveBatch(aa01List);
        // 故意insertAa01 XML 语句里 加密了 AAA003，而queryUnitTable没有解密
//        aa01Mapper.insertAa01(aa01);
//        aa01Mapper.insertAa01(aa011);
        List<UnitTableDO> unitTableDOS = bankCardXmlMapper.queryUnitTable("13567891234");
        unitTableDOS = bankCardMapper.queryUnitTable("13567891234");
    }
}
