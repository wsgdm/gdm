package cn.tx.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tx.model.Dep;
import cn.tx.service.DepService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class DepServiceImplTest {

	@Autowired
	private DepService depService;
	
	@Test
	public void testSaveDep() {
		Dep dep = new Dep();
		dep.setDepId(9);
		dep.setName("sss");
		dep.setTel("123456");
		depService.save(dep);
	}

	@Test
	public void testUpdateEmp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDep() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDep() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryDepByCondition() {
		fail("Not yet implemented");
	}

}
