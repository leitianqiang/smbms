import com.wpt.dao.provider.ProviderMapper;
import com.wpt.pojo.Provider;
import com.wpt.util.MybatisUtils;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @Author ltq
 * @Date 2021/11/24 9:17
 */
public class ProviderTest {

    private SqlSession sqlSession;

    //根据供应商编码或供应商名称查询供应商总数
    @Test
    public void queryProviderCounts(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
            int providerCounts = mapper.getProviderCounts("GZ_GYS001","深圳");
            System.out.println("总数量" + providerCounts);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //根据供应商编码或供应商名称查询供应商列表
    @Test
    public void getProviderList(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
            List<Provider> providerList = mapper.getProviderList("GZ_GYS001","深圳",0,1);
            for (Provider provider : providerList) {
                System.out.println(provider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //添加供应商
    @Test
    public void addProvider(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
            Provider provider = new Provider();
            provider.setProCode("ZJ_GYS002");
            provider.setProName("乐摆日用品厂");
            provider.setProDesc("长期合作伙伴，高档塑料杯");
            provider.setProContact("蕾蕾");
            provider.setProPhone("13143516269");
            provider.setProAddress("深圳市龙岗区");
            provider.setProFax("0579-34452321");
            provider.setCreatedBy(1);
            provider.setCreationDate(new Date());
            if (mapper.addProvider(provider) > 0){
                System.out.println("新增成功");
            }else{
                System.out.println("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }

    //根据Id查询供应商信息
    @Test
    public void queryProviderById(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
            Provider provider = mapper.queryProviderById(32);
            System.out.println(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //修改供应商
    @Test
    public void updateProvider(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
            Provider provider = new Provider();
            provider.setProCode("ZJ_GYS002");
            provider.setProName("乐摆日用品厂");
            provider.setProDesc("长期合作伙伴，高档塑料杯");
            provider.setProContact("黄文超");
            provider.setProPhone("13143516269");
            provider.setProAddress("深圳市龙岗区");
            provider.setProFax("0579-34452321");
            provider.setModifyBy(1);
            provider.setModifyDate(new Date());
            provider.setId(32);
            if (mapper.updateProvider(provider) > 0){
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }


    }

    //根据Id删除供应商
    @Test
    public void deleteProviderById(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
            if (mapper.deleteProvider(31) > 0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
