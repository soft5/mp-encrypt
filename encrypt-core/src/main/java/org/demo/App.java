package org.demo;

import org.springframework.util.StringUtils;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        char c = "ddd".charAt(0);
//        System.out.println(c);
//        System.out.println(extractIndex("$7AnwZJ1e6BZ"));
//        BankCardDO bankCardDO = new BankCardDO();
//        // 将数据加密,放置到新的加密字段
//        bankCardDO.setCard_no_encrypt(encrypt(bankCardDO.getCard_no()));
//        ....
//        /***
//         * mybatis SQL 需要如下改造：
//         * select * from bankCard where card_no in(#{card_no},#{card_no_encrypt})
//         */
//        BankCardDO result = bankCardDao.query(bankCardDO);
//        /**
//         * 判断查询返回的数据是否是密文，判断规则不限于：
//         * 判断是否包含中文
//         * 判断长度
//         * 等等....
//         *
//         */
//        if(isEncrypt(bankCardDO.getCard_no())){
//            // 将数据解密替换返回值
//            result.setCard_no(decrypt(bankCardDO.getCard_no()));
//        }


    }

    public static String extractIndex(String encryptedData) {
        if (encryptedData == null || encryptedData.length() < 4) {
            return null;
        }
        char sepInData = encryptedData.charAt(0);
        if (encryptedData.charAt(encryptedData.length() - 1) != sepInData) {
            return null;
        }
        String[] parts = StringUtils.split(encryptedData, String.valueOf(sepInData));
        if (sepInData == '$' || sepInData == '#') {
            return parts[0];
        } else {
            return parts[1];
        }
    }
}
