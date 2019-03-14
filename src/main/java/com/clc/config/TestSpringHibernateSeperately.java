package com.clc.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

public class TestSpringHibernateSeperately {

//before execution of this class.. disable/remove  @EnableWebMvc from SpringBean java file
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringBean.class);
		EmpServiceImpl impl = (EmpServiceImpl) context.getBean("serv");
		System.out.println("EmpDao : "+ impl.empdao);
		
		
		EmpBean bean1 = new EmpBean(4, "AA1","Pune",23, 2231.2);
		EmpBean bean2= new EmpBean(5, "AAA2","Pune",23, 2231.2);
		
		impl.addEmp(bean1);
		impl.addEmp(bean2);
		
		System.out.println(impl.getEmp(4));
		System.out.println(impl.getAllEmps());
		
		bean1.setEmpAddress("Mumbai");
		impl.updateEmp(bean1);
		
		impl.deleteEmp(5);
		
		System.out.println(impl.getAllEmps());
		
		
	}
	
}
