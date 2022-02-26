package com.wpt.service.impl;

import com.wpt.dao.user.UserMapper;
import com.wpt.pojo.User;
import com.wpt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/18 10:22
 */
@Service
public class UserServiceImpl implements UserService {

    //业务层调用dao层
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userCode, String password) {
        //先通过 userCode查找用户的信息 再判断用户密码是否相等 如果不相同就返回 null
        User user = userMapper.findUserByUserCode(userCode);
        boolean flag = user != null ? user.getUserPassword().equals(password) : false;
        return flag == true ? user : null;
    }

    @Override
    public boolean updatePwd(int id, String password) {
        return userMapper.updatePwd(id,password) == 1;
    }

    @Override
    public boolean ifExistUserCode(String userCode) {
        return userMapper.findUserByUserCode(userCode) != null;
    }

    @Override
    public int getUserCounts(String userName, Integer userRole) {
        return userMapper.getUserOrRoleCounts(userName,userRole);
    }

    @Override
    public List<User> getUserList(String queryUserName, Integer queryUserRole, Integer currentPageNo, Integer pageSize) {
        return userMapper.getUserOrRoleList(queryUserName,queryUserRole,currentPageNo,pageSize);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user) > 0;
    }

    @Override
    public boolean deleteUser(int userId) {
        return userMapper.deleteUser(userId) > 0;
    }

    @Override
    public User findById(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public boolean modify(User user) {
        return userMapper.updateUser(user) > 0;
    }
}
