## 通用的数据加解密方案

通过自定义的 `typeHandler` 实现数据加密入库，查询结果解密返回。

本项目改于 [mybatis-encrypt](https://github.com/9526xu/mybatis-encrypt/pull/2) ，mybatis改为了mybatis-plus，
同时增加了批量操作和框架自带方法的加解密操作。

## 使用方式

1.引入 `encrypt-interface` 依赖

2.修改配置，以下三种方式可以根据项目实际情况选择。

**单独使用 mybatis**

这种场景需要在 **mybatis-config.xml** 配置，mybatis 启动时将会加载该配置文件。

```xml
<typeHandlers>
  <!--类型转换器包路径-->
  <package name="com.xx.xx"/>
</typeHandlers>
  <!-- 别名定义 -->
<typeAliases>
		<!-- 针对单个别名定义 type:类型的路径 alias:别名 -->
		<typeAlias type="xx.xx.xx" alias="xx"/>
</typeAliases>
```

**使用 Spring 配置 Mybatis Bean**

配合 Spring 使用时需要将 `typeHandler` 注入 `SqlSessionFactoryBean` ，配置方式如下：

```xml
<!-- MyBatis 工厂 -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />

    <!--alias 注入-->
    <property name="typeAliasesPackage" value="xx.xx.xx"/>
    <!--  typeHandlers 注入   -->
    <property name="typeHandlersPackage" value="xx.xx.xx"/>
</bean>
```

**SpringBoot**

SpringBoot 方式就最简单了，只要引入 `mybatis-starter`，配置文件加入如下配置即可:

```properties
## mybatis 配置
# 类型转换器包路径
mybatis.type-handlers-package=com.xx.xx.x
mybatis.type-aliases-package=com.xx.xx
```

3.改造相应的sqlmap

**xml 方式：**

insert 语句示例：

```xml
<insert id="insertBankCard" keyProperty="id" useGeneratedKeys="true" parameterType="org.demo.pojo.BankCardDO">
    INSERT INTO bank_card (card_no, phone,name,id_no)
    VALUES
    (#{card_no,javaType=crypt},
    #{phone,typeHandler=org.demo.type.CryptTypeHandler},
    #{name,javaType=crypt},
    #{id_no,javaType=crypt})
</insert>
```

查询语句示例：

```xml
<resultMap id="bankCardXml" type="org.demo.pojo.BankCardDO">
        <result property="card_no" column="card_no" typeHandler="org.demo.type.CryptTypeHandler"/>
        <result property="name" column="name" typeHandler="org.demo.type.CryptTypeHandler"/>
        <result property="id_no" column="id_no" typeHandler="org.demo.type.CryptTypeHandler"/>
        <result property="phone" column="phone" typeHandler="org.demo.type.CryptTypeHandler"/>
</resultMap>
<select id="queryById" resultMap="bankCardXml">
        select * from bank_card where id=#{id}
</select>
```

数据库明文、密文共存的情况，查询解密示例如下：

```xml
<!-- resultMap 同上   -->
<select id="queryByPhone" resultMap="bankCardXml">
      select * from bank_card where phone in(#{card_no,javaType=crypt},#{card_no})
</select>
```

**muybatis 注解方式示例**

insert 语句：

```java
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO bank_card (card_no, phone,name,id_no) " +
            "VALUES (#{card.card_no,javaType=crypt}, #{card.phone,typeHandler=org.demo.type.CryptTypeHandler},#{card.name,javaType=crypt},#{card.id_no,javaType=crypt})")
    void insertBankCard(@Param("card") BankCardDO cardDO);
```

查询语句示例：

```java
    @Results(id = "bankCard", value = {
            // 通过指定 typeHandler 进行解密
            @Result(column = "card_no", property = "card_no", typeHandler = CryptTypeHandler.class),
            // 通过指定 javaType 进行解密
            @Result(column = "phone", property = "phone", typeHandler = CryptTypeHandler.class),
            @Result(column = "name", property = "name", typeHandler = CryptTypeHandler.class),
            @Result(column = "id_no", property = "id_no", typeHandler = CryptTypeHandler.class),
    })
    @Select("select * from bank_card where id=#{id}")
    BankCardDO queryById(int id);
```
mybatis和mybatis plus自带的方法解

```autoResultMap = true``` 和 ```typeHandler = CryptTypeHandler.class```
```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "BANK_CARD", autoResultMap = true)
@KeySequence(value="SEQ_AAZ179",clazz = Long.class)
public class BankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

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
```
注意：autoResultMap = true 最小版本 3.1.2

## 相关测试

本工程提供一个测试 demo，使用了内嵌的 H2 数据库，无需使用其他数据库。

测试例子比较简单，只要运行 `MybatisDemoApplicationTests` 即可。

## 参考链接
【1】[Java 代码解决敏感信息加解密](https://mp.weixin.qq.com/s/3AkqXoGN4QB8CtDEv9gCEw)

【2】[Java 代码解决敏感信息加解密](https://juejin.cn/post/7138330262303637541?searchId=2023122818053636D8F9AF3DBF5C7C9EE1)

【3】[在Mybatis-Plus中指定TypeHandler后不生效的问题与解决办法](https://blog.csdn.net/weixin_50276625/article/details/115858779)
