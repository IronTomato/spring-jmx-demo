package com.irontomato.springjmxdemo.config;

import com.irontomato.springjmxdemo.mbean.TestMBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MBeanConfig {

//    @Bean
//    public TestMBean testMBean(){
//        TestMBean bean = new TestMBean();
//        bean.setName("adfjaldjf");
//        bean.setAge(199);
//        return bean;
//    }
//
//    @Bean("exporter")
//    public MBeanExporter mBeanExporter(){
//        MBeanExporter exporter = new MBeanExporter();
//        Map<String, Object> beans = new HashMap<>();
//        TestMBean bean = testMBean();
//        beans.put("spring.bean:name=testMBean", bean);
//        exporter.setBeans(beans);
//        exporter.setAutodetect(true);
//        exporter.setAssembler(new );
//        return exporter;
//    }

}
