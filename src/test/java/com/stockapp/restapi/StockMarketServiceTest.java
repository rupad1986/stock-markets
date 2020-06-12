package com.stockapp.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockapp.restapi.dao.StockMarketDaoImpl;
import com.stockapp.restapi.model.StockDomain;
import com.stockapp.restapi.service.StockMarketService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockMarketServiceTest {

    @Autowired
    StockMarketService stockMarketService;

    @Test
    public void shouldMatchNewStockTest() {
        StockDomain stockDomain = getData();
        StockDomain result = stockMarketService.createStockRecord(stockDomain);
        Assert.assertEquals(result.getQuarter(), stockDomain.getQuarter());
        Assert.assertEquals(result.getClose(), stockDomain.getClose());
        Assert.assertEquals(result.getDate(), stockDomain.getDate());

    }

    @Test
    public void shouldFindByStockName() {
        List<StockDomain> resultList = (List<StockDomain>) stockMarketService.findByStockName("AA");
        Assert.assertTrue(resultList.stream().filter(stock -> stock.getStockName().equals("AA")).findAny().isPresent());
    }

    @Test
    public void shouldNotFindAnyOtherStockName() {
        List<StockDomain> resultList = (List<StockDomain>) stockMarketService.findByStockName("AXP");
        Assert.assertFalse(resultList.stream().filter(stock -> stock.getStockName().equals("AA")).findAny().isPresent());
    }

    @Test
    public void shouldUploadFile() throws IOException {
        InputStream is = getClass().getResourceAsStream("/dow_jones_index.data");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        MockMultipartFile file = new MockMultipartFile("file", "dow_jones_index.data",
                MediaType.TEXT_PLAIN_VALUE, is);
        String s = stockMarketService.uploadBulkData(file);
        Assert.assertEquals(s, "File uploaded Successfully");

    }


    private StockDomain getData() {
        String jsonData = "{\n" +
                "\"quarter\" : 1,\n" +
                "\"stockName\" : \"AA\",\n" +
                "\"date\" : \"1/14/2011\",\n" +
                "\"open\" : \"$16.71\",\n" +
                "\"high\" : \"$16.71\",\n" +
                "\"low\" : \"$15.64\",\n" +
                "\"close\" : \"$15.97\",\n" +
                "\"volume\" : 242963398,\n" +
                "\"percentChangePrice\" : -4.42849,\n" +
                "\"percentChangeVolumeOverLastWk\" : 1.380223028,\n" +
                "\"previousWeeksVolume\" : 239655616,\n" +
                "\"nextWeeksOpen\" : \"$16.19\",\n" +
                "\"nextWeeksClose\" : \"$15.79\",\n" +
                "\"percentChangeNextWeeksPrice\" : -2.47066,\n" +
                "\"daysToNextDividend\" : 19,\n" +
                "\"percentReturnNextDividend\" : 0.187852\n" +
                "}";
        try {
            return new ObjectMapper().readValue(jsonData, StockDomain.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

