package com.kaishengit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.tms.entity.TicketOutRecord;
import com.kaishengit.tms.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 财务类业务控制器
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private TicketService ticketService;

    /**
     * 缴费首页
     */
    @GetMapping("/ticket")
    public String home(@RequestParam(defaultValue = "未支付",required = false) String state,
                       @RequestParam(defaultValue = "1",required = false) Integer pageNo,
                       Model model) {
        Map<String ,Object> queryParam = Maps.newHashMap();
        queryParam.put("state",state);

        PageInfo<TicketOutRecord> pageInfo = ticketService.findTicketOutRecordByPageNoAndQueryParam(pageNo,queryParam);

        model.addAttribute("page",pageInfo);
        return "finance/ticket/home";
    }

    /**
     * 下发支付
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}/pay")
    public String pay(@PathVariable Integer id,
                      Model model) {
        TicketOutRecord ticketOutRecord = ticketService.findTicketOutRecordById(id);
        model.addAttribute("ticketOutRecord",ticketOutRecord);

        return "finance/ticket/pay";
    }

    @PostMapping("/{id:\\d+}/pay")
    public String pay(@PathVariable Integer id) {
        ticketService.payTicketOutRecord(id);
        return "redirect:/finance/ticket";
    }















}
