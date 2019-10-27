package org.kakarrot.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kakarrot.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

//@WebServlet("/BasicController")
//URL로 안쓰고(호출용도X) 기능만 구현
//어노테이션을 이용해서 해당 메서드를 찾아서 여기저기 뿌려주는 역할 
@Log4j
public abstract class BasicController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String clientPath = req.getRequestURI();
		String methodType = req.getMethod();
		log.info(methodType + ": " + clientPath);
		System.out.println(clientPath + " " + methodType);

		Class<?> clz = this.getClass();
		try {
			Method[] methods = clz.getDeclaredMethods();
			Method target = null;
			for (Method m : methods) {
				RequestMapping anno = m.getDeclaredAnnotation(RequestMapping.class);
				if (clientPath.equals(anno.value()) && methodType.equals(anno.type())) {
					target = m;
					break;
				}
			}

			String result = (String) target.invoke(this, req, resp);
			
			if(result == null) return;
			
			if (result.startsWith("redirect:")) {
				resp.sendRedirect(result.substring(10));
			} else {
				req.getRequestDispatcher("/WEB-INF/views" + result + ".jsp").forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
