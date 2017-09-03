package com.vcl0000.cxfwebservice.client;

import com.vcl0000.cxfwebservice.service.SampleService;
import com.vcl0000.data.model.User;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vcl0000 on 17-8-1.
 */
public class ClientForXCF {
    private static Logger logger = LoggerFactory.getLogger(ClientForXCF.class);

    public static SampleService getInterFace() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(SampleService.class);
        factoryBean.setAddress("http://localhost:8080/service/sample");
        return (SampleService) factoryBean.create();
    }

    public static void main(String[] args) {
        SampleService sampleService = getInterFace();
        System.out.println("client: " + sampleService.getUserById("1"));
        User user = new User();
        user.setUserId("1");
        System.out.println("client: " + sampleService.inputUser(user));
//        logger.info("client: " + s ampleService.getUserById("1"));
    }

}
