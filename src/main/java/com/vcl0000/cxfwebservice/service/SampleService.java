package com.vcl0000.cxfwebservice.service;

import com.vcl0000.data.model.User;
import com.vcl0000.data.model.UserAndInfo;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by vcl0000 on 17-7-30.
 */
@WebService
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SampleService {

    public UserAndInfo getUserById(@WebParam(name = "userId") String userId);

    public String inputUser(User user);
}
