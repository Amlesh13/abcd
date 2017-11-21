package com.ams.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.ams.dto.RequestCompleteDetails;
import com.ams.entity.Details;
import com.ams.service.IDetailsService;

@Controller
//@RequestMapping("service")
public class DetailsController {
	@Autowired
	private IDetailsService detailsService;
	
	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}
	
	@GetMapping("details/{id}")
	public ResponseEntity<Details> getArticleById(@PathVariable("id") Integer id) {
		Details article = detailsService.getDetailsById(id);
		return new ResponseEntity<Details>(article, HttpStatus.OK);
	}
	@GetMapping("details")
	public ResponseEntity<List<Details>> getAllArticles() {
		List<Details> list = detailsService.getAllDetails();
		return new ResponseEntity<List<Details>>(list, HttpStatus.OK);
	}
	@PostMapping("details")
	public ResponseEntity<Void> addArticle(@RequestBody Details details, UriComponentsBuilder builder) {
        boolean flag = detailsService.addDetails(details);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(details.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("details")
	public ResponseEntity<Details> updateArticle(@RequestBody Details details) {
		detailsService.updateDetails(details);
		return new ResponseEntity<Details>(details, HttpStatus.OK);
	}
	@DeleteMapping("details/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
		detailsService.deleteDetails(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	@PostMapping("completeDetails")
	public ResponseEntity<List<Details>> details(@RequestBody RequestCompleteDetails requestDetails) {
        List<Details> list = detailsService.getApplicationOrProposalNoDetails(requestDetails.getApplcationOrProposalNo());
        System.out.println("Size :::::::::"+list.size());
        if (list == null || list.isEmpty()) {
        	return new ResponseEntity<List<Details>>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<List<Details>>(list, HttpStatus.OK);
	}
} 