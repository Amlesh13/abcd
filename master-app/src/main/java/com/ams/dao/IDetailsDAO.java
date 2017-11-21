package com.ams.dao;
import java.util.List;

import com.ams.dto.RequestCompleteDetails;
import com.ams.entity.Details;
public interface IDetailsDAO {
    List<Details> getAllDetails();
    Details getDetailsById(int id);
    void addDetails(Details details);
    void updateDetails(Details details);
    void deleteDetails(int id);
    boolean detailsExists(String applicationOrProposalNo);
    public List<Details> getApplicationOrProposalNoDetails(String applicationOrProposalNo);
}
 