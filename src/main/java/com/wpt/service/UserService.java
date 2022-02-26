package com.wpt.service;

import com.wpt.pojo.User;

import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/18 10:09
 */
public interface UserService {
    //登录
    User login(String userCode,String password);
    //修改密码
    boolean updatePwd(int id,String password);
    //根据用户userCode查询是否拥有这个用户
    boolean ifExistUserCode(String userCode);

    //用户管理-查询记录数
    int getUserCounts(String userName,Integer userRole);
    //根据条件 查询用户列表
    List<User> getUserList(String queryUserName,Integer queryUserRole, Integer currentPageNo, Integer pageSize);


    //用户管理模块中的 子模块—— 添加用户
    boolean addUser(User user);
    //用户管理模块中的子模块 —— 删除用户
    boolean deleteUser(int userId);
    //根据id查询用户信息
    User findById(int userId);
    //用户管理模块中的子模块 —— 更改用户信息
    boolean modify(User user);
}
