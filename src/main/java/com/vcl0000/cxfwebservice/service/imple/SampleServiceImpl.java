package com.vcl0000.cxfwebservice.service.imple;

import com.vcl0000.cxfwebservice.service.SampleService;
import com.vcl0000.data.mapper.DataMapper;
import com.vcl0000.data.model.User;
import com.vcl0000.data.model.UserAndInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by vcl0000 on 17-7-30.
 */
@WebService(endpointInterface = "com.vcl0000.cxfwebservice.service.SampleService", serviceName = "sample")
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SampleServiceImpl implements SampleService {

    private static Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);

    @Resource
    private DataMapper dataMapper;

    public UserAndInfo getUserById(String userId) {
        logger.info(userId);
        User user = new User();
        user.setUserId(userId);
        UserAndInfo userAndInfo = dataMapper.findUserAndInfo(user);
        logger.info(String.valueOf(userAndInfo));
        return userAndInfo;
    }

    public String inputUser(User user) {
        logger.info(user.toString());
        return "success";
    }
}
