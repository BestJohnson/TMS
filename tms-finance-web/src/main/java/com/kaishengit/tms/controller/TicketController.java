package com.kaishengit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.TicketInRecord;
import com.kaishengit.tms.entity.TicketOutRecord;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.service.TicketService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 年票业务控制器
 */

@Controller
@RequestMapping("/ticket")
public class TicketController {


    @Autowired
    private TicketService ticketService;

    /**
     * 入库的首页
     * @return
     */
    @GetMapping("/storage")
    public String home(Model model) {
        List<TicketInRecord> ticketInRecordList = ticketService.findAllTicketInRecord();
        model.addAttribute("ticketInRecordList",ticketInRecordList);
        return "inStorage/home";
    }

    /**
     * 新增入库
     * @param model
     * @return
     */
    @GetMapping("/storage/new")
    public String inStorage(Model model) {
        String today = DateTime.now().toString("YYYY-MM-dd");

        model.addAttribute("today",today);
        return "inStorage/new";
    }

    @PostMapping("/storage/new")
    public String inStorage(TicketInRecord ticketInRecord) {
        //获取当前进行入库操作的账户
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        ticketInRecord.setAccountName(account.getAccountName());
        ticketInRecord.setAccountId(account.getId());

        ticketService.addTicketInRecord(ticketInRecord);
        return "redirect:/ticket/storage";
    }

    /**
     * 入库信息修改
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String editInStorage(@PathVariable Integer id,
                                Model model) {

        TicketInRecord  ticketInRecord = ticketService.findTicketInRecordById(id);
        model.addAttribute("ticketInRecord",ticketInRecord);
        return "inStorage/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String editInStorage(TicketInRecord ticketInRecord) {
        ticketService.updateTicketInRecord(ticketInRecord);

        return "redirect:/ticket/storage";
    }

    /**
     * 入库信息删除
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    public String delInStorage(@PathVariable Integer id) {
        ticketService.delTicketInRecordById(id);

        return "redirect:/ticket/storage";
    }

    /**
     * 年票下发首页
     */

    @GetMapping("/out/storage")
    public String outStorage(Model model,
                             @RequestParam(defaultValue = "1",name = "p",required = false) Integer pageNo) {

        PageInfo<TicketOutRecord> pageInfo = ticketService.findAllTicketOutRecordByPageNo(pageNo);
        model.addAttribute("page",pageInfo);
        return "outStorage/home";
    }

    /**
     * 新增年票下发
     */
    @GetMapping("/out/storage/new")
    public String newOutStorage(Model model) {
        String today = DateTime.now().toString("YYYY-MM-dd");
        List<TicketStore> ticketStoreList = ticketService.findAllTicketStore();
        model.addAttribute("today",today);
        model.addAttribute("ticketStoreList",ticketStoreList);
        return "outStorage/new";
    }

    @PostMapping("/out/storage/new")
    public String newOutStorage(TicketOutRecord ticketOutRecord) {
        ticketService.addTicketOutRecord(ticketOutRecord);
        return "redirect:/ticket/out/storage";
    }

    @GetMapping("/out/storage/{id:\\d+}/del")
    public @ResponseBody  ResponseBean delOutStorage(@PathVariable Integer id) {
        ticketService.delTicketOutStorage(id);

        return ResponseBean.success();
    }












}
