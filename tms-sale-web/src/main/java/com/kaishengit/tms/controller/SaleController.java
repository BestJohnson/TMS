package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Customer;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

/**
 * 年票销售控制器
 */
@Controller
@RequestMapping("/ticket")
public class SaleController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ShiroUtils shiroUtils;


    /**
     * 年票办理
     * @return
     */
    @GetMapping("/sale/new")
    public String newSale() {

        return "ticket/new";
    }

    @PostMapping("/sale/new")
    public String newSale(Customer customer,String ticketNum,Long price,
                          RedirectAttributes redirectAttributes) {

        //获取当前售票点
        TicketStore ticketStore = shiroUtils.getCurrentAccount();
        try {
            ticketService.saleTicket(customer,ticketNum,ticketStore,price);
            redirectAttributes.addFlashAttribute("message","办理成功");
        } catch (ServiceException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }

        return "redirect:/ticket/home";
    }

    /**
     * 年票挂失
     * @return
     */
    @GetMapping("/lost")
    public String lostTicket() {

        return "ticket/lost";
    }

    @PostMapping("/lost")
    public String lostticket(String ticketNum, String customerIdcard,
                             RedirectAttributes redirectAttributes) {
        ticketService.lostTicket(ticketNum,customerIdcard);

        return "redirect:/ticket/home";
    }












}
