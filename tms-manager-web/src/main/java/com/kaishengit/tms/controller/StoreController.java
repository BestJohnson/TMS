package com.kaishengit.tms.controller;

import com.kaishengit.tms.controller.exception.NotFoundException;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.service.TicketStoreService;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ticketstore")
public class StoreController {

    @Autowired
    private TicketStoreService ticketStoreService;

    @GetMapping
    public String home(Model model) {

        List<TicketStore> ticketStoreList = ticketStoreService.findAllStore();
        model.addAttribute("ticketStoreList",ticketStoreList);

        return "store/home";
    }

    @GetMapping("/new")
    public String newStore(Model model) {
        String accessKey = "1111";
        String secretKey = "222";
        String bucket = "kaishengit-tms";

        Auth auth = Auth.create(accessKey,secretKey);
        String uploadToken = auth.uploadToken(bucket);

        model.addAttribute("upToken",uploadToken);

        return "store/new";
    }

    @PostMapping("/new")
    public String newStore(TicketStore ticketStore,
                           RedirectAttributes redirectAttributes) {

        ticketStoreService.saveTicketStore(ticketStore);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/ticketstore";
    }

    @GetMapping("/{id:\\d+}")
    public String showStore(@PathVariable Integer id,
                            Model model) {
        TicketStore ticketStore = ticketStoreService.findStoreById(id);

        if(ticketStore == null) {
            throw new NotFoundException();
        }

        //查相关联的账户
        StoreAccount storeAccount = ticketStoreService.findStoreAccountById(ticketStore.getStoreAccountId());
        model.addAttribute("storeAccount",storeAccount);
        model.addAttribute("ticketStore",ticketStore);

        return "store/show";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String editStore(@PathVariable Integer id,Model model) {
        TicketStore ticketStore = ticketStoreService.findStoreById(id);
        if(ticketStore == null) {
            throw new NotFoundException();
        }

        model.addAttribute("ticketStore",ticketStore);
        return "store/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String editStore(TicketStore ticketStore,
                            RedirectAttributes redirectAttributes) {

        ticketStoreService.updateStore(ticketStore);
        redirectAttributes.addFlashAttribute("message","修改成功");

        return "redirect:/ticketstore";
    }

    @GetMapping("/{id:\\d+}/del")
    public String delStore(@PathVariable Integer id,
                           RedirectAttributes redirectAttributes) {
        ticketStoreService.deleteStoreById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");

        return "redirect:/ticketstore";
    }

    @GetMapping("/{id:\\d+}/disable")
    public String disableStore(@PathVariable Integer id) {
        ticketStoreService.disableStoreAccountById(id);

        return "redirect:/ticketstore";
    }











}
