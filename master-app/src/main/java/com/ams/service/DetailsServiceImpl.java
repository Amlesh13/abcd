package com.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.dao.IDetailsDAO;
import com.ams.dto.RequestCompleteDetails;
import com.ams.entity.Details;
@Service
public class DetailsServiceImpl implements IDetailsService {
	@Autowired
	private IDetailsDAO detailsDAO;
	@Override
	public Details getDetailsById(int id) {
		Details obj = detailsDAO.getDetailsById(id);
		return obj;
	}	
	@Override
	public List<Details> getAllDetails(){
		return detailsDAO.getAllDetails();
	}
	@Override
	public synchronized boolean addDetails(Details details){
       if (detailsDAO.detailsExists(details.getApplicationNo()!=null?details.getApplicationNo():details.getProposalNo())) {
    	   return false;
       } else {
    	   detailsDAO.addDetails(details);
    	   return true;
       }
	}
	@Override
	public void updateDetails(Details details) {
		detailsDAO.updateDetails(details);
	}
	@Override
	public void deleteDetails(int id) {
		detailsDAO.deleteDetails(id);
	}
	
	@Override
	public List<Details> getApplicationOrProposalNoDetails(String applicationOrProposalNo){
		return detailsDAO.getApplicationOrProposalNoDetails(applicationOrProposalNo);
	}
}
