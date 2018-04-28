package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import com.kaishengit.tms.service.TicketStoreService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);


    @Autowired
    private TicketStoreService ticketStoreService;



    /**
     * 判断角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //获取当前登录对象
        Account account = (Account) principalCollection.getPrimaryPrincipal();
        //获取对象拥有的角色
        List<Roles> rolesList = rolePermissionService.findAllRolesByAccountId(account.getId());

        //获取角色对应的的权限
        List<Permission> permissionList = new ArrayList<>();
        for(Roles roles : rolesList) {
            permissionList = rolePermissionService.findAllPermissionByRolesId(roles.getId());
        }

        Set<String> rolesName = new HashSet<>();
        for(Roles roles : rolesList) {
            rolesName.add(roles.getRolesCode());
        }

        Set<String> permissionName = new HashSet<>();
        for(Permission permission : permissionList) {
            permissionName.add(permission.getPermissionCode());
        }

        //当前账号拥有的角色（code）
        simpleAuthorizationInfo.setRoles(rolesName);
        //当前账号拥有的权限（code）
        simpleAuthorizationInfo.setStringPermissions(permissionName);


        return simpleAuthorizationInfo;*/
        return null;
    }


    /**
     * 判断登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();

        if(userMobile != null) {
            StoreAccount storeAccount = ticketStoreService.findStoreAccountByMobile(userMobile);
            if(storeAccount == null) {
                throw new UnknownAccountException("该账号不存在");
            } else {
                if(StoreAccount.ACCOUNT_STATE_NOMAL.equals(storeAccount.getStoreState())) {
                    logger.info("{} 登录成功： {}",storeAccount,usernamePasswordToken.getHost());

                    //查找售票点
                    TicketStore ticketStore = ticketStoreService.findTicketStoreByStoreMobile(userMobile);

                    //保存登录日志
                    /*StoreLoginLog storeLoginLog = new StoreLoginLog();
                    storeLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    storeLoginLog.setLoginTime(new Date());
                    storeLoginLog.setStoreAccountId(storeAccount.getId());

                    ticketStoreService.saveStoreAccountLoginLog(storeLoginLog);*/

                    //将ticketStore放入shiro
                    return  new SimpleAuthenticationInfo(ticketStore,storeAccount.getStorePassword(),getName());
                } else {
                    throw new LockedAccountException("账户被锁定或禁用");
                }
            }
        }

        return null;
    }
}
