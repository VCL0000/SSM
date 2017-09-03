package com.vcl0000.data.controller;

import com.vcl0000.data.mapper.DataMapper;
import com.vcl0000.data.mapper.UserInfoMapper;
import com.vcl0000.data.mapper.UserMapper;
import com.vcl0000.data.model.User;
import com.vcl0000.data.model.UserAndInfo;
import com.vcl0000.data.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by vcl0000 on 17-7-25.
 */
@RequestMapping("/data")
@Controller
public class DataConteoller {
    private static Logger logger = LoggerFactory.getLogger(DataConteoller.class);

    @Resource
    private DataService dataService;

    @Resource
    private DataMapper dataMapper;

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String controller(@PathVariable Integer id) {
        return id.toString();
    }
    @RequestMapping("/find")
    public ModelAndView findUser(){
        List<Map> userAll = dataService.findUserAll();
            logger.info(userAll.toString());
        ModelAndView modelAndView = new ModelAndView("returnUser");
        modelAndView.getModel().put("userAll", userAll.toString());
        return modelAndView;
    }


    @RequestMapping("/find/{userId}")
    public ModelAndView findUserById(@PathVariable String userId){
        Map user = dataService.findUserById(userId);
        logger.info(user.toString());
        logger.info(dataMapper.findUserNameByUserId(userId).toString());
        logger.info(dataMapper.findOneUser().toString());
        logger.info(dataMapper.findOneUserObject().toString());
        ModelAndView modelAndView = new ModelAndView("returnUser");
        modelAndView.getModel().put("user", user.toString());
        return modelAndView;

    }

    @RequestMapping("/findUser/{userId}")
    public ModelAndView findUser(@PathVariable String userId ){
        User user = new User();
        user.setUserId(userId);
        UserAndInfo userAndInfo = dataMapper.findUserAndInfo(user);
        logger.info(userAndInfo.toString());
        ModelAndView modelAndView = new ModelAndView("userAndInfo");
        modelAndView.getModel().put("userAndInfo", userAndInfo);
        return modelAndView;

    }
    @RequestMapping("/inputUser")
    @ResponseBody
    public User findUser(@RequestBody User user ){
        //need json lib
        logger.info(user.toString());
        return user;
    }

}
