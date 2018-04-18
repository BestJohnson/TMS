package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.PermissionRolesExample;
import com.kaishengit.tms.entity.PermissionRolesKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionRolesMapper {
    long countByExample(PermissionRolesExample example);

    int deleteByExample(PermissionRolesExample example);

    int deleteByPrimaryKey(PermissionRolesKey key);

    int insert(PermissionRolesKey record);

    int insertSelective(PermissionRolesKey record);

    List<PermissionRolesKey> selectByExample(PermissionRolesExample example);

    int updateByExampleSelective(@Param("record") PermissionRolesKey record, @Param("example") PermissionRolesExample example);

    int updateByExample(@Param("record") PermissionRolesKey record, @Param("example") PermissionRolesExample example);
}