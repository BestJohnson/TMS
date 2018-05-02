package com.kaishengit.tms.controller;


import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.shiro.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 首页、登录、登出
 */
@Controller
public class HomeController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ShiroUtils shiroUtils;

    /**
     * 登录
     * @return
     */
    @GetMapping("/")
    public String home() {
        Subject subject = SecurityUtils.getSubject();

        if(subject.isAuthenticated()) {
            subject.logout();
        }

        if(subject.isRemembered()) {
            return "redirect:/home";
        }

        return "index";
    }

    @PostMapping("/")
    public String login(String accountMobile,
                       String password,
                       String rememberMe,
                       HttpServletRequest request,
                       RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        String requestIp = request.getRemoteAddr();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountMobile, password,rememberMe != null,requestIp);

        try {
            subject.login(usernamePasswordToken);
            //记住退出登录前的url
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = "/home";

            if (savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }
            return "redirect:" + url;
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        } catch (LockedAccountException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账户被锁定");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        }
        return "redirect:/";
    }

    /**
     * 登录成功
     * @return
     */
    @GetMapping("/home")
    public String home(Model model) {
        TicketStore ticketStore = shiroUtils.getCurrentAccount();


        //查询当前售票点库存年票数量和已售出年票数量
        Map<String,Integer> countResult = ticketService.countTicketByStateAndStoreAccountId(ticketStore.getId());

        model.addAttribute("resultMap",countResult);
        return "home";
    }











}
