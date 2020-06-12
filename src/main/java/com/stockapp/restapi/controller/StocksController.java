package com.stockapp.restapi.controller;

import com.stockapp.restapi.model.StockDomain;
import com.stockapp.restapi.service.StockMarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StocksController {
    private final Logger logger = LoggerFactory.getLogger(StocksController.class);

    @Autowired
    StockMarketService stockMarketService;

    @PostMapping("/create")
    public StockDomain create(@RequestBody StockDomain newStockData) {
        return stockMarketService.createStockRecord(newStockData);
    }

    @GetMapping("/find/{stockName}")
    public List<?> read(@PathVariable String stockName) {
        return stockMarketService.findByStockName(stockName);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
        try {
            logger.info("Single file upload!");
            if (uploadfile.isEmpty()) {
                return new ResponseEntity("Please select a file!", HttpStatus.OK);
            }
            String response = stockMarketService.uploadBulkData(uploadfile);
            return new ResponseEntity(response + " - " +
                    uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
