import com.wpt.dao.user.UserMapper;
import com.wpt.pojo.User;
import com.wpt.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @Author ltq
 * @Date 2021/11/24 10:31
 */
public class UserTest {
    private SqlSession sqlSession;

    //根据userCode查找用户信息
    @Test
    public void queryUserByUserCode(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User userByUserCode = mapper.findUserByUserCode("admin");
            System.out.println(userByUserCode);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //修改密码
    @Test
    public void updatePwd(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.updatePwd(19,"123456");
            if (i > 0){
                System.out.println("修改密码成功");
            }else{
                System.out.println("修改密码失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //根据用户名或角色查询用户总数
    @Test
    public void getUserList(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userOrRoleList = mapper.getUserOrRoleList("孙",3,0,5);
            for (User user : userOrRoleList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加用户
    @Test
    public void addUser(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserCode("leilei");
            user.setUserName("蕾蕾");
            user.setUserPassword("123456");
            user.setGender(1);
            user.setBirthday(new Date("1997/01/01"));
            user.setPhone("13143516269");
            user.setAddress("深圳市龙岗区");
            user.setUserRole(1);
            user.setCreatedBy(1);
            user.setCreationDate(new Date());
            if (mapper.addUser(user) > 0){
                System.out.println("新增用户成功");
            }else{
                System.out.println("新增用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //根据id查询用户信息
    @Test
    public void queryUserById(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User userById = mapper.getUserById(20);
            System.out.println(userById);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //修改用户
    @Test
    public void updateUser(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setId(20);
            user.setUserName("蕾蕾");
            user.setGender(1);
            user.setBirthday(new Date("1996/01/01"));
            user.setPhone("13143516269");
            user.setAddress("深圳市龙岗区");
            user.setUserRole(1);
            user.setModifyBy(1);
            user.setModifyDate(new Date());
            if (mapper.updateUser(user) > 0){
                System.out.println("修改用户成功");
            }else{
                System.out.println("修改用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //删除用户
    @Test
    public void deleteUser(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            if (mapper.deleteUser(19) > 0){
                System.out.println("删除用户成功");
            }else{
                System.out.println("删除用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
