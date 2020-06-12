package com.stockapp.restapi.dao;

import com.stockapp.restapi.model.StockDomain;

import java.util.Collection;
import java.util.List;

public interface StockMarketDAO {
    List<StockDomain> findByStockName(String stockName);
    StockDomain createNewRecord(StockDomain stockDomain);
    Collection<StockDomain> uploadDataSet(List<StockDomain> stockDomainList);
}
