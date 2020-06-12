package com.stockapp.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stockmarkets")
public class StockDomain {

    @Id
    private String id;

    private Long quarter;

    private String stockName;

    private String date;

    private String open;

    private String high;

    private String low;

    private String close;
    private Long volume;

    private Double percentChangePrice;

    private Double percentChangeVolumeOverLastWk;

    private Long previousWeeksVolume;

    private String nextWeeksOpen;

    private String nextWeeksClose;

    private Double percentChangeNextWeeksPrice;

    private Double daysToNextDividend;

    private Double percentReturnNextDividend;
    @JsonIgnore
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getQuarter() {
        return quarter;
    }

    public void setQuarter(Long quarter) {
        this.quarter = quarter;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getPercentChangePrice() {
        return percentChangePrice;
    }

    public void setPercentChangePrice(Double percentChangePrice) {
        this.percentChangePrice = percentChangePrice;
    }

    public Double getPercentChangeVolumeOverLastWk() {
        return percentChangeVolumeOverLastWk;
    }

    public void setPercentChangeVolumeOverLastWk(Double percentChangeVolumeOverLastWk) {
        this.percentChangeVolumeOverLastWk = percentChangeVolumeOverLastWk;
    }

    public Long getPreviousWeeksVolume() {
        return previousWeeksVolume;
    }

    public void setPreviousWeeksVolume(Long previousWeeksVolume) {
        this.previousWeeksVolume = previousWeeksVolume;
    }

    public String getNextWeeksOpen() {
        return nextWeeksOpen;
    }

    public void setNextWeeksOpen(String nextWeeksOpen) {
        this.nextWeeksOpen = nextWeeksOpen;
    }

    public String getNextWeeksClose() {
        return nextWeeksClose;
    }

    public void setNextWeeksClose(String nextWeeksClose) {
        this.nextWeeksClose = nextWeeksClose;
    }

    public Double getPercentChangeNextWeeksPrice() {
        return percentChangeNextWeeksPrice;
    }

    public void setPercentChangeNextWeeksPrice(Double percentChangeNextWeeksPrice) {
        this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
    }

    public Double getDaysToNextDividend() {
        return daysToNextDividend;
    }

    public void setDaysToNextDividend(Double daysToNextDividend) {
        this.daysToNextDividend = daysToNextDividend;
    }

    public Double getPercentReturnNextDividend() {
        return percentReturnNextDividend;
    }

    public void setPercentReturnNextDividend(Double percentReturnNextDividend) {
        this.percentReturnNextDividend = percentReturnNextDividend;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StockDomain{" +
                "id=" + id +
                ", quarter=" + quarter +
                ", stockName='" + stockName + '\'' +
                ", date='" + date + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", volume=" + volume +
                ", percentChangePrice=" + percentChangePrice +
                ", percentChangeVolumeOverLastWk=" + percentChangeVolumeOverLastWk +
                ", previousWeeksVolume=" + previousWeeksVolume +
                ", nextWeeksOpen='" + nextWeeksOpen + '\'' +
                ", nextWeeksClose='" + nextWeeksClose + '\'' +
                ", percentChangeNextWeeksPrice=" + percentChangeNextWeeksPrice +
                ", daysToNextDividend=" + daysToNextDividend +
                ", percentReturnNextDividend=" + percentReturnNextDividend +
                '}';
    }
}
