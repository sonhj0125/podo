package common.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		description = "*.wine 을 했을 경우 응답.",
		urlPatterns = {"*.wine"},
		initParams = { 
		      @WebInitParam(name = "propertyConfig", value = "C:/NCS/podo/podo/src/main/webapp/WEB-INF/Command.properties", description = "*.wine Mapping prop")  
		})
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

private Map<String, Object> cmdMap = new HashMap<>();
	
	public void init(ServletConfig config) throws ServletException {

		FileInputStream fis = null;
		
		String props = config.getInitParameter("propertyConfig");
		try {
			fis = new FileInputStream(props);
			Properties pr = new Properties();
			pr.load(fis);
			Enumeration<Object> en = pr.keys();
			
			while(en.hasMoreElements()) {
				
				String key = (String) en.nextElement();
				String className = pr.getProperty(key);
				
				if(className != null) {
					 
					className = className.trim();
					
					Class<?> cls = Class.forName(className);
					Constructor<?> constrt = cls.getDeclaredConstructor(); 
					
					Object obj = constrt.newInstance();
					
					cmdMap.put(key, obj);
					
				}
				
			}
			
		}catch (FileNotFoundException e) {
			System.out.println("Property 파일이 없거나 찾지 못함\nFileNotFoundException [위치 : MainController]");
		}catch (IOException e) {
			System.out.println("IOExeption [위치 : MainController]");
		}catch (ClassNotFoundException e) {
			System.out.println("멥핑된 위치에 클래스 파일이 없음\nClassNotFoundException [위치 : MainController]");
		}catch (Exception e) {
			String err_msg = e.getMessage();
			System.out.println("알수없는 이유로 예외발생 [위치 : MainController]\n"+err_msg);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		
		String key = uri.substring(request.getContextPath().length()); 
		
		AbstractController action = (AbstractController) cmdMap.get(key); 
		
		if(action == null) {
			System.out.println(key+"는 Mapping되지 않음 [위치 : MainController]");
		}
		else {
			try {
				action.execute(request, response);
				
				boolean bool = action.isRedirect();
				String viewPage = action.getViewPage();
				
				if(!bool) {
					
					if(viewPage != null) {
						RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
						dispatcher.forward(request, response);
					}
				}
				else {
					if(viewPage != null) {
						response.sendRedirect(viewPage);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
