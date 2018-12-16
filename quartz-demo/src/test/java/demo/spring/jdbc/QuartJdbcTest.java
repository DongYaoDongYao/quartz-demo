package demo.spring.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartJdbcTest {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		context.start();
		
	}
}
