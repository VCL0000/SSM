package com.vcl0000.data.service.impl;

import com.vcl0000.data.mapper.DataMapper;
import com.vcl0000.data.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by vcl0000 on 17-7-25.
 */
@Service
public class DataServiceImpl implements DataService{

    @Resource
    private DataMapper dataMapper;

    public List findUserAll() {
        return dataMapper.findUserAll();
    }

    public Map findUserById(String userId) {
        return dataMapper.findUserById(userId);
    }
}
