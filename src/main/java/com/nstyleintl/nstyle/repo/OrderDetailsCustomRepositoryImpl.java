package com.nstyleintl.nstyle.repo;

import com.nstyleintl.nstyle.model.OrderDetails;
import com.nstyleintl.nstyle.model.OrderHeader;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderDetailsCustomRepositoryImpl implements OrderDetailsCustomRepository {

	private EntityManager entityManager;
	
	@Override
	public void deleteOrderDetailsByHeader(OrderHeader orderHeader) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<OrderDetails> criteriaDelete = cb.createCriteriaDelete(OrderDetails.class);

        Root<OrderDetails> root = criteriaDelete.from(OrderDetails.class);

        criteriaDelete.where(cb.equal(root.get("orderHeader"), orderHeader));

        entityManager.createQuery(criteriaDelete).executeUpdate();
	}

}
