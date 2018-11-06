package cn.tx.service.impl;

import static org.junit.Assert.*;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tx.model.Emp;
import cn.tx.service.EmpService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class EmpServiceImplTest {

	@Autowired
	private EmpService empService;
	
	@Test
	public void testSave() {
		Emp emp = new Emp();
		emp.setAddress("地球");
		emp.setBirthday(new Date());
		emp.setDepId(123);
		emp.setEmail("1111");
		emp.setEmpId(1144);
		emp.setGender(1);
		emp.setName("sds");
		emp.setPassword("123456789");
		emp.setTel("ssss");
		emp.setUsername("aa");
		
		empService.save(emp);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetObjectById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryObjByCondition() {
		fail("Not yet implemented");
	}

}
