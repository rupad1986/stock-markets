package com.stockapp.restapi.dao;

import com.stockapp.restapi.model.StockDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class StockMarketDaoImpl implements StockMarketDAO {
    public static final String STOCKMARKETS = "stockmarkets";
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    StockMongoRepository stockMongoRepository;

    @Override
    public List<StockDomain> findByStockName(String stockName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("stockName").is(stockName));
        return mongoTemplate.find(query, StockDomain.class);
    }

    @Override
    public StockDomain createNewRecord(StockDomain stockDomain) {
        doesStockMarketsExist();
        return mongoTemplate.insert(stockDomain);
    }

    private void doesStockMarketsExist() {
        if (!mongoTemplate.collectionExists(STOCKMARKETS)) {
            mongoTemplate.createCollection(STOCKMARKETS
            );
        }
    }

    @Override
    public List<StockDomain> uploadDataSet(List<StockDomain> stockDomainList) {
        doesStockMarketsExist();
        return stockMongoRepository.saveAll(stockDomainList);
    }
}
