package com.ams.service;

import java.util.List;

import com.ams.dto.RequestCompleteDetails;
import com.ams.entity.Details;

public interface IDetailsService {
     List<Details> getAllDetails();
     Details getDetailsById(int id);
     boolean addDetails(Details details);
     void updateDetails(Details details);
     void deleteDetails(int id);
     public List<Details> getApplicationOrProposalNoDetails(String requestCompleteDetails);
}
