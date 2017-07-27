package com.solution.service.general;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solution.domain.model.StarDiscoverer;

public class TestGeneralService {

	@Test
	public void checkObjectTheSameForValidation() {

		StarDiscoverer toCheck1 = new StarDiscoverer();
		StarDiscoverer toCheck2 = new StarDiscoverer();

		toCheck1.setName("toCheck1");
		toCheck2.setName("toCheck1");

		Assert.assertTrue(toCheck1.equals(toCheck2));
		Assert.assertTrue(GeneralService.domainObjectsAreSame(toCheck1, toCheck2));
	}

}
