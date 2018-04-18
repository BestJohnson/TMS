package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 角色管理控制器
 */
@Controller
@RequestMapping("/manage/roles")
public class RolesController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("rolesList",rolePermissionService.findAllRolesWithPermission());
        return "manage/roles/home";
    }

    /**
     * 新增角色
     * @return
     */
    @GetMapping("/new")
    public String newRoles(Model model) {
        List<Permission> permissionList = rolePermissionService.findAllPermission();
        model.addAttribute("permissionList",permissionList);
        return "manage/roles/new";
    }

    @PostMapping("/new")
    public String newRoles(Roles roles,
                           Integer[] permissionId,
                           RedirectAttributes redirectAttributes) {
        rolePermissionService.addRoles(roles,permissionId);
        redirectAttributes.addFlashAttribute("message","角色添加成功");
        return "redirect:/manage/roles";

    }

}
