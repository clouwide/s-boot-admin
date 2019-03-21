package com.ruoyi;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Date;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.ruoyi.*.mapper")
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiApplication.class, args);
    }


    @Bean()
    @Primary
    public SqlSessionFactory sqlSessionFactory( DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setVfs(SpringBootVFS.class);
        sessionFactoryBean.setTypeAliasesPackage("com.ruoyi");
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/**/*Mapper.xml"));
        return sessionFactoryBean.getObject();
    }


    public class MyMetaObjectHandler implements MetaObjectHandler {

        private  final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

        @Override
        public void insertFill(MetaObject metaObject) {
            LOGGER.info("start insert fill ....");
            String userName = ShiroUtils.getSysUser().getUserName();
            if(StringUtils.isNotEmpty(userName)){
                this.setInsertFieldValByName("createBy", userName, metaObject);
            }
            this.setInsertFieldValByName("createTime", new Date(), metaObject);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            String userName = ShiroUtils.getSysUser().getUserName();
            if(StringUtils.isNotEmpty(userName)){
                this.setUpdateFieldValByName("updateBy", userName, metaObject);
            }
            this.setUpdateFieldValByName("updateTime", new Date(), metaObject);
        }
    }


}