package com.kaishengit.tms.controller;

import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 权限控制器
 * @author shengsen
 */
@Controller
@RequestMapping("/manage/permission")
public class PermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;


    @GetMapping
    public String home(Model model) {
        List<Permission> permissionList = rolePermissionService.findAllPermission();
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/home";
    }

    @GetMapping("/new")
    public String addPermission(Model model) {
        //菜单类权限
        List<Permission> permissionList = rolePermissionService.findPermissionByType(Permission.MENU_TYPE);
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/new";
    }

    @PostMapping("/new")
    public String addPermission(Permission permission,
                                RedirectAttributes redirectAttributes) {
        rolePermissionService.addPermission(permission);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/manage/permission";
    }

    @GetMapping("/{id:\\d+}del")
    @ResponseBody
    public ResponseBean delPermission(@PathVariable Integer id) {
        try {

            rolePermissionService.delPermissionById(id);
            return ResponseBean.success();
        } catch (ServiceException ex) {
            return ResponseBean.error(ex.getMessage());
        }
    }

}
