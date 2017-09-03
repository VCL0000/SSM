package com.vcl0000.data.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by vcl0000 on 17-7-25.
 */
@Service
public interface DataService {
    public List findUserAll();

    public Map findUserById(String userId);
}
