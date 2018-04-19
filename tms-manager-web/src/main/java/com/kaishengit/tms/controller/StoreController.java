package com.kaishengit.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manage/store")
public class StoreController {

    @GetMapping
    public String home(Model model) {

        List<TicketStore> ticketStoreList = ticketStoreService.findAllStore();
        model.addAttribute("storeList",ticketStoreList);

        return "manage/store/home";
    }

    @GetMapping("/new")
    public String newStore() {

        return "/manage/store/new";
    }

    @PostMapping("/new")
    @Transactional
    public String newStore(TicketStore ticketStore,
                           RedirectAttributes redirectAttributes) {

        ticketStoreService.insert(ticketStore);

        //store_account表中也需要新增


        return "redirect:manage/store/home";
    }










}
