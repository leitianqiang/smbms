package com.wpt.dao.bill;

import com.wpt.pojo.Bill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ltq
 * @Date 2021/11/23 11:52
 */
@Repository
public interface BillMapper {

    //根据商品名称、供应商Id、是否付款查询订单总数
    int getBillCount(@Param("productName") String queryProductName,@Param("providerId") Integer queryProviderId,
                     @Param("isPayment") Integer queryIsPayment);
    //根据商品名称、供应商Id、是否付款查询订单列表
    List<Bill> getBillList(@Param("productName") String queryProductName, @Param("providerId") Integer queryProviderId,
                           @Param("isPayment") Integer queryIsPayment, @Param("currentPageNo") Integer currentPageNo,
                           @Param("pageSize") Integer pageSize);

    //新添订单
    int addBill(Bill bill);

    //根据订单id查询订单
    Bill queryBillById(@Param("id") int id);

    //修改订单
    int updateBill(Bill bill);

    //根据Id删除订单
    int deleteBill(@Param("id") int id);

}
