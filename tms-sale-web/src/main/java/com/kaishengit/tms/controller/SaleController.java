package com.kaishengit.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 年票销售控制器
 */
@Controller
@RequestMapping("/ticket")
public class SaleController {


    /**
     * 年票办理
     * @return
     */
    @GetMapping("/sale/new")
    public String newSale() {

        return "ticket/new";
    }











}
