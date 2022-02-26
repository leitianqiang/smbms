import com.wpt.dao.bill.BillMapper;
import com.wpt.pojo.Bill;
import com.wpt.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author ltq
 * @Date 2021/11/23 9:53
 */
public class BillTest {
    private SqlSession sqlSession;

    //根据商品名称、供应商id、是否付款 查询订单总数
    @Test
    public void getBillCount(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            BillMapper mapper = sqlSession.getMapper(BillMapper.class);
            int billCount = mapper.getBillCount("大",6,2);
            System.out.println("总数量：" + billCount);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //根据商品名称、供应商Id、是否付款查询订单列表
    @Test
    public void getBillList(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            BillMapper mapper = sqlSession.getMapper(BillMapper.class);
            List<Bill> billList = mapper.getBillList("大", 4, 2,0,2);
            for (Bill bill : billList) {
                System.out.println(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }

    //新添订单
    @Test
    public void addBill(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            BillMapper mapper = sqlSession.getMapper(BillMapper.class);
            Bill bill = new Bill();
            bill.setBillCode("BILL2016_018");
            bill.setProductName("补水液");
            bill.setProductDesc("饮料");
            bill.setProductUnit("箱");
            bill.setProductCount(new BigDecimal(500));
            bill.setTotalPrice(new BigDecimal(75));
            bill.setIsPayment(2);
            bill.setCreatedBy(1);
            bill.setCreationDate(new Date());
            bill.setProviderId(2);
            int i = mapper.addBill(bill);
            if (i > 0){
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

    //修改订单
    @Test
    public void updateBill(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            BillMapper mapper = sqlSession.getMapper(BillMapper.class);
            Bill bill = new Bill();
            bill.setId(18);
            bill.setBillCode("BILL2016_018");
            bill.setProductName("补水液(原味)");
            bill.setProductDesc("葡萄糖饮料");
            bill.setProductUnit("箱");
            bill.setProductCount(new BigDecimal(500));
            bill.setTotalPrice(new BigDecimal(75));
            bill.setIsPayment(2);
            bill.setModifyBy(1);
            bill.setModifyDate(new Date());
            int i = mapper.updateBill(bill);
            if (i > 0){
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

    //根据订单id查询订单
    @Test
    public void queryBillById(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            BillMapper mapper = sqlSession.getMapper(BillMapper.class);
            Bill bill = mapper.queryBillById(16);
            System.out.println(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    //根据Id删除订单
    @Test
    public void deleteBill(){
        try {
            sqlSession = MybatisUtils.sqlSession();
            BillMapper mapper = sqlSession.getMapper(BillMapper.class);
            if (mapper.deleteBill(18) > 0){
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
