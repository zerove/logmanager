package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.swing.Timer;

import org.apache.log4j.Logger;

@WebServlet(name="timerServlet",
urlPatterns="/timerServlet",loadOnStartup=1)
public class LogTest1 extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("初始化完成");
		Timer t = new Timer(5*1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Logger logger = Logger.getLogger(LogTest1.class);
				 // 记录debug级别的信息  
		        logger.debug("This is debug message.");  
		        // 记录info级别的信息  
		        logger.info("This is info message.");  
		        // 记录error级别的信息  
		        logger.error("This is error message.");  
		        System.out.println("输出日志");
			}
		});
		t.start();
	}
}
