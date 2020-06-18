package com.kkxu.demo.controller;

import com.kkxu.demo.common.utils.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class CommunicateController {
    //页面请求
    @GetMapping("/index/{userId}")
    public ModelAndView socket(@PathVariable String userId) {
        ModelAndView mav=new ModelAndView("/socket1");
        mav.addObject("userId", userId);
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public Map pushToWeb(@PathVariable String cid, String message) {
        Map result = new HashMap();
        try {
            WebSocketServer.sendInfo(message, cid);
            result.put("code", 200);
            result.put("msg", "success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}