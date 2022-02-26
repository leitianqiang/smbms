package com.wpt.service.impl;

import com.wpt.dao.provider.ProviderMapper;
import com.wpt.pojo.Provider;
import com.wpt.service.ProviderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/25 9:43
 */
@Service
public class ProviderServiceImpl implements ProviderService {
    @Resource
    private ProviderMapper providerMapper;

    @Override
    public int getProviderCounts(String queryProCode, String queryProName) {
        return providerMapper.getProviderCounts(queryProCode,queryProName);
    }

    @Override
    public List<Provider> getProviderList(String queryProCode, String queryProName, Integer currentPageNo, Integer pageSize) {
        return providerMapper.getProviderList(queryProCode,queryProName,currentPageNo,pageSize);
    }

    @Override
    public int addProvider(Provider provider) {
        return providerMapper.addProvider(provider);
    }

    @Override
    public Provider queryProviderById(int id) {
        return providerMapper.queryProviderById(id);
    }

    @Override
    public boolean updateProvider(Provider provider) {
        return providerMapper.updateProvider(provider) > 0;
    }

    @Override
    public boolean deleteProvider(int id) {
        return providerMapper.deleteProvider(id) > 0;
    }
}
