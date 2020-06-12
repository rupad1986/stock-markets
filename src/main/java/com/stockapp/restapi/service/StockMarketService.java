package com.stockapp.restapi.service;

import com.stockapp.restapi.dao.StockMarketDAO;
import com.stockapp.restapi.model.StockDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StockMarketService {
    private final Logger logger = LoggerFactory.getLogger(StockMarketService.class);
    @Autowired
    StockMarketDAO stockMarketDAO;

    /**
     * Creates new Stock market record in DB
     * @param newStockData
     * @return StockDomain
     */
    public StockDomain createStockRecord(StockDomain newStockData){
        return stockMarketDAO.createNewRecord(newStockData);
    }

    /**
     * Fetches list of stocks by stock name
     * @param stockName
     * @return List<StockDomain>
     */
    public List<?> findByStockName(String stockName){
        List<StockDomain> stockDomainList = stockMarketDAO.findByStockName(stockName);
        if(!stockDomainList.isEmpty()) {
            return stockDomainList;
        }else {
            return Arrays.asList("Stock Data  not found with name "+stockName);
        }
    }

    /**
     * Uploads bulk data set for stocks into DB.
     * @param uploadfile
     * @return String
     */
    public String uploadBulkData(MultipartFile uploadfile) throws IOException {
        try {
            String line;
            InputStream is = uploadfile.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<StockDomain> stockDomainList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                logger.info("Inside uploadBulkData {}" , line);
                String[] array = line.split(",");
                stockDomainList.add(mapStockData(array));
            }
            if(!stockDomainList.isEmpty()){
                logger.info("stockDomainList {}" , stockDomainList.size());
                stockMarketDAO.uploadDataSet(stockDomainList);
            }
        } catch (Exception e) {
            logger.error("Fail to upload File into DB {}" , e);
            throw e;
        }
        return "File uploaded Successfully";
    }

    private StockDomain mapStockData(String[] array) {
        StockDomain stockDomain = new StockDomain();
        stockDomain.setQuarter(Long.valueOf(!StringUtils.isEmpty(array[0]) ? array[0] : "0"));
        stockDomain.setStockName(array[1]);
        stockDomain.setDate(array[2]);
        stockDomain.setOpen(array[3]);
        stockDomain.setHigh(array[4]);
        stockDomain.setLow(array[5]);
        stockDomain.setClose(array[6]);
        stockDomain.setVolume(Long.valueOf(!StringUtils.isEmpty(array[7]) ? array[7] : "0"));
        stockDomain.setPercentChangePrice(Double.valueOf(!StringUtils.isEmpty(array[8]) ? array[8] : "0"));
        stockDomain.setPercentChangeVolumeOverLastWk(Double.valueOf(!StringUtils.isEmpty(array[9]) ? array[9] :  "0"));
        stockDomain.setPreviousWeeksVolume(Long.valueOf(!StringUtils.isEmpty(array[10]) ? array[10] : "0"));
        stockDomain.setNextWeeksOpen(array[11]);
        stockDomain.setNextWeeksClose(array[12]);
        stockDomain.setPercentChangeNextWeeksPrice(Double.valueOf(!StringUtils.isEmpty(array[13]) ? array[13] : "0"));
        stockDomain.setDaysToNextDividend(Double.valueOf(!StringUtils.isEmpty(array[14]) ? array[14] : "0"));
        stockDomain.setPercentReturnNextDividend(Double.valueOf(!StringUtils.isEmpty(array[15]) ? array[15] : "0"));
        return stockDomain;
    }


}
