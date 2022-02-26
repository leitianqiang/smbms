package com.wpt.service.impl;

import com.wpt.dao.role.RoleMapper;
import com.wpt.pojo.Role;
import com.wpt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/22 10:45
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
