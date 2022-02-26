package com.wpt.service;

import com.wpt.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/25 11:34
 */
public interface BillService {
    //根据商品名称、供应商Id、是否付款查询订单总数
    int getBillCount(String queryProductName,Integer queryProviderId,
                     Integer queryIsPayment);
    //根据商品名称、供应商Id、是否付款查询订单列表
    List<Bill> getBillList(String queryProductName, Integer queryProviderId,
                           Integer queryIsPayment, Integer currentPageNo,
                           Integer pageSize);

    //新添订单
    boolean addBill(Bill bill);

    //根据订单id查询订单
    Bill queryBillById(int id);

    //修改订单
    boolean updateBill(Bill bill);

    //根据Id删除订单
    boolean deleteBill(@Param("id") int id);
}
