package com.ams.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ams.dto.RequestCompleteDetails;
import com.ams.entity.Details;
@Transactional
@Repository
public class DetailsDAO implements IDetailsDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Details getDetailsById(int id) {
		return entityManager.find(Details.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Details> getAllDetails() {
		String hql = "FROM Details as atcl ORDER BY atcl.id";
		return (List<Details>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addDetails(Details details) {
		entityManager.persist(details);
	}
	@Override
	public void updateDetails(Details details) {
		Details artcl = getDetailsById(details.getId());
		artcl.setApplicationNo(details.getApplicationNo());
		artcl.setProposalNo(details.getProposalNo());
		artcl.setCategory(details.getCategory());
		entityManager.flush();
	}
	@Override
	public void deleteDetails(int articleId) {
		entityManager.remove(getDetailsById(articleId));
	}
	@Override
	public boolean detailsExists(String applicationOrProposalNo) {
		String hql = "FROM Details as atcl WHERE atcl.proposalNo = ? OR atcl.applicationNo = ?";
		int count = entityManager.createQuery(hql).setParameter(1, applicationOrProposalNo).setParameter(2, applicationOrProposalNo)
		              .getResultList().size();
		return count > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Details> getApplicationOrProposalNoDetails(String applicationOrProposalNo){
		List<Details> list = new ArrayList<Details>();
		String hql = "FROM Details as atcl WHERE atcl.proposalNo like :proposalNo OR atcl.applicationNo like :applicationNo";
		list = (List<Details>)entityManager.createQuery(hql)
				.setParameter("proposalNo", "%"+applicationOrProposalNo+"%")
				.setParameter("applicationNo", "%"+applicationOrProposalNo+"%").getResultList();
		return list;
	}
}
