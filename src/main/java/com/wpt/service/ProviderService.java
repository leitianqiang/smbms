package com.wpt.service;

import com.wpt.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/25 9:43
 */
public interface ProviderService {

    //根据供应商编码或供应商名称查询供应商总数
    int getProviderCounts(String queryProCode,
                          String queryProName);

    //根据供应商编码或供应商名称查询供应商列表
    List<Provider> getProviderList(String queryProCode,
                                   String queryProName,
                                   Integer currentPageNo,
                                   Integer pageSize);
    //添加供应商
    int addProvider(Provider provider);

    //根据Id查询供应商信息
    Provider queryProviderById(int id);

    //修改供应商
    boolean updateProvider(Provider provider);

    //根据Id删除供应商
    boolean deleteProvider( int id);

}
