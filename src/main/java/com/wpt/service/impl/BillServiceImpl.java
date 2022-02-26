package com.wpt.service.impl;

import com.wpt.dao.bill.BillMapper;
import com.wpt.pojo.Bill;
import com.wpt.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/25 11:35
 */
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public int getBillCount(String queryProductName, Integer queryProviderId, Integer queryIsPayment) {
        return billMapper.getBillCount(queryProductName,queryProviderId,queryIsPayment);
    }

    @Override
    public List<Bill> getBillList(String queryProductName, Integer queryProviderId, Integer queryIsPayment, Integer currentPageNo, Integer pageSize) {
        return billMapper.getBillList(queryProductName,queryProviderId,queryIsPayment,currentPageNo,pageSize);
    }

    @Override
    public boolean addBill(Bill bill) {
        return billMapper.addBill(bill) == 1;
    }

    @Override
    public Bill queryBillById(int id) {
        return billMapper.queryBillById(id);
    }

    @Override
    public boolean updateBill(Bill bill) {
        return billMapper.updateBill(bill) == 1;
    }

    @Override
    public boolean deleteBill(int id) {
        return billMapper.deleteBill(id) == 1;
    }
}
