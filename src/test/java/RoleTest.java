import com.wpt.dao.role.RoleMapper;
import com.wpt.pojo.Role;
import com.wpt.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author ltq
 * @Date 2021/11/24 9:49
 */
public class RoleTest {

    private SqlSession sqlSession;

    //获取角色列表
    @Test
    public void getRoleList(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<Role> roleList = mapper.getRoleList();
            for (Role role : roleList) {
                System.out.println(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
