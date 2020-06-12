package com.stockapp.restapi.dao;

import com.stockapp.restapi.model.StockDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMongoRepository  extends MongoRepository<StockDomain, String> {
}
