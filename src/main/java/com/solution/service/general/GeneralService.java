package com.solution.service.general;

import com.solution.domain.logic.DomainObject;

public class GeneralService {

	public static boolean domainObjectsAreSame(DomainObject domainObject, DomainObject domainObjectToCheck) {
		if (domainObjectToCheck != null
				&& ((domainObject.getId() == null) || (domainObject.getId() != null && !domainObject.getId().equals(
						domainObjectToCheck.getId())))) {
			return true;
		} else {
			return false;
		}
	}
}
