package demo.spring.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloService {
	public void hello(){
		System.out.println("北京时间："+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}
}
