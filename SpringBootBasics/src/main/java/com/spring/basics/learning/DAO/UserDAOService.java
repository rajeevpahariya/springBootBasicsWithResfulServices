package com.spring.basics.learning.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.spring.basics.learning.jpa.User;
// Added transactional to have transaction when insering data in DB. Can be add at method level too.
@Repository
@Transactional
public class UserDAOService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(User pUser){
		entityManager.persist(pUser);
		return pUser.getId();
	}

}
