package com.vcl0000.data;

import com.vcl0000.data.controller.DataConteoller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by vcl0000 on 2017/3/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = {"classpath*:appliaction-context.xml","classpath*:spring-mvc.xml", "classpath*:spring-db.xml", "classpath*:config/mybatis-config.xml"})
public class ControllerTest extends AbstractJUnit4SpringContextTests {
    private Logger logger = LoggerFactory.getLogger(ControllerTest.class);



    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


    @Before
    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//        mockMvc = MockMvcBuilders.standaloneSetup(TestController).build();

    }
    @Test
    public void testController() throws Exception {
        DataConteoller dataConteoller = this.wac.getBean(DataConteoller.class);
        String index = dataConteoller.index();
        logger.info(index);
    }

    @Test
    public void testController2() throws Exception {
        //need servlet maven
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/data/findUser/{userId}", "1"))
                .andExpect(MockMvcResultMatchers.view().name("userAndInfo"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userAndInfo"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertNotNull(result.getModelAndView().getModel().get("userAndInfo"));
    }
    // TODO test dao callback
//
//    @Test
//    public void testDao(){
//        TestMapper testMapper = this.applicationContext.getBean(TestMapper.class);
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("userId", "1");
//        List<Map> byId = testMapper.findById(map);
//        for (Map m: byId) {
//            logger.info(m.toString());
//        }
//    }
}
