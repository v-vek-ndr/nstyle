package com.nstyleintl.nstyle.repo;

import java.util.ArrayList;
import java.util.List;

import com.nstyleintl.nstyle.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

	private final EntityManager entityManager;
	
	@Override
	public boolean isEmailTaken(String email) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		
		Root<User> root = query.from(User.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.equal(root.get("email"), email));
		
		query.where(predicates.toArray(new Predicate[0]));
		List<Object> resultList = entityManager.createQuery(query).getResultList();
		return resultList.size() > 0 ? true : false;
	}

}
