package com.stockapp.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockapp.restapi.model.StockDomain;
import com.stockapp.restapi.service.StockMarketService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@Ignore
public class StocksControllerTest {

    public static final String BASE_URL = "http://localhost:8080";
    private RestTemplate restTemplate = new RestTemplate();
    @Test
    public void testCreateNewStock() throws URISyntaxException {
        final String baseUrl = BASE_URL + "/api/create";
        URI uri = new URI(baseUrl);
        ResponseEntity<StockDomain> result = restTemplate.postForEntity(uri, getData(), StockDomain.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().getStockName().equals("AA"));

    }

    @Test
    public void testReadStock() throws URISyntaxException {
        final String baseUrl = BASE_URL + "/api/find/AA";
        URI uri = new URI(baseUrl);
        ResponseEntity<StockDomain[]> result = restTemplate.getForEntity(uri, StockDomain[].class);
        StockDomain[] stockDomains = result.getBody();
        List<StockDomain> list = Arrays.asList(stockDomains);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertFalse(list.stream().filter(stock -> !stock.getStockName().equals("AA")).findAny().isPresent());

    }

    @Test
    public void testUploadFile() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new ClassPathResource("dow_jones_index.data"));
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
                map, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(BASE_URL + "/api/upload", requestEntity, String.class);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().equals("File uploaded Successfully - dow_jones_index.data"));
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

