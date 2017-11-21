package com.ams.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ams.entity.Details;

public class RestClientUtil {
    public void getDetailsByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/Details/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Details> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Details.class, 1);
        Details Details = responseEntity.getBody();
        System.out.println("Id:"+Details.getId()+", Title:"+Details.getProposalNo()
                 +", Category:"+Details.getApplicationNo());      
    }
	public void getAllDetailssDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/Detailss";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Details[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Details[].class);
        Details[] Detailss = responseEntity.getBody();
        for(Details Details : Detailss) {
        	  System.out.println("Id:"+Details.getId()+", Title:"+Details.getProposalNo()
                      +", Category:"+Details.getApplicationNo());  
        }
    }
    public void addDetailsDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/service/Details";
	    Details objDetails = new Details();
	    objDetails.setProposalNo("KHH00020P");
	    objDetails.setApplicationNo("KHH00020P");
	    objDetails.setCategory("Spring");
        HttpEntity<Details> requestEntity = new HttpEntity<Details>(objDetails, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    public void updateDetailsDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/Details";
	    Details objDetails = new Details();
	    objDetails.setProposalNo("KHH10020P");
	    objDetails.setApplicationNo("KHH10020P");
	    objDetails.setCategory("Java");
        HttpEntity<Details> requestEntity = new HttpEntity<Details>(objDetails, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteDetailsDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/Details/{id}";
        HttpEntity<Details> requestEntity = new HttpEntity<Details>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    public static void main(String args[]) {
    	RestClientUtil util = new RestClientUtil();
        //util.getDetailsByIdDemo();
    	//util.getAllDetailssDemo();
    	util.addDetailsDemo();
    	//util.updateDetailsDemo();
    	//util.deleteDetailsDemo();
    }    
}
