package com.wpt.dao.provider;

import com.wpt.pojo.Provider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ltq
 * @Date 2021/11/23 12:05
 */
@Repository
public interface ProviderMapper {

    //根据供应商编码或供应商名称查询供应商总数
    int getProviderCounts(@Param("proCode") String queryProCode,
                          @Param("proName") String queryProName);

    //根据供应商编码或供应商名称查询供应商列表
    List<Provider> getProviderList(@Param("proCode") String queryProCode,
                                   @Param("proName") String queryProName,
                                   @Param("currentPageNo") Integer currentPageNo,
                                   @Param("pageSize") Integer pageSize);

    //添加供应商
    int addProvider(Provider provider);

    //根据Id查询供应商信息
    Provider queryProviderById(@Param("id") int id);

    //修改供应商
    int updateProvider(Provider provider);

    //根据Id删除供应商
    int deleteProvider(@Param("id") int id);
}
