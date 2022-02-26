package com.wpt.dao.user;

import com.wpt.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ltq
 * @Date 2021/11/23 12:05
 */
/*@Repository: 让Spring容器管理，相当于在xml中配置了一个bean
* value 属性指定bean的Id 如果不指定，默认Id为当前类的类名（首字母小写）userMapper*/
@Repository
public interface UserMapper {

    //根据userCode查找用户信息
    User findUserByUserCode(@Param("userCode") String userCode);

    //修改密码
    int updatePwd(@Param("id")int id,@Param("userPassword") String password);

    //根据用户名或角色查询用户总数
    int getUserOrRoleCounts(@Param("userName") String userName,
                            @Param("userRole") Integer userRole);

    //根据用户名或角色查询用户列表
    List<User> getUserOrRoleList(@Param("userName") String userName,
                                 @Param("userRole") Integer userRole,
                                 @Param("currentPageNo") Integer currentPageNo,
                                 @Param("pageSize") Integer pageSize);

    //添加用户
    int addUser(User user);

    //根据id查询用户信息
    User getUserById(@Param("id") int id);

    //修改用户
    int updateUser(User user);

    //删除用户
    int deleteUser(@Param("id") int id);

}
