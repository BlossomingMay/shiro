package com.huang.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.huang.shiro.pojo.Order;
import com.huang.shiro.service.OrderService;
import com.huang.shiro.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author huangwx
 * @date 2020-02-08 19:02
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/{id}")
    public String list(@PathVariable("id") int id, Model model) {

        List<OrderVo> list = orderService.getAllOrderById(id);
        model.addAttribute("list", list);
        return "order";
    }


}
