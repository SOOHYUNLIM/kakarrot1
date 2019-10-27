package org.kakarrot.controller;

import java.io.File;
import java.io.InputStream;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.kakarrot.annotation.RequestMapping;
import org.kakarrot.dao.MemberDAO;
import org.kakarrot.dao.MemberDAOImpl;
import org.kakarrot.domain.MemberDTO;

import lombok.extern.log4j.Log4j;

@Log4j
@WebServlet("/member/*")
@MultipartConfig(location = "C:\\zzz", maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024
* 50, fileSizeThreshold = 1024 * 1024)
public class MemberController extends BasicController {

	private final MemberDAO md;

	public MemberController() {
		this.md = new MemberDAOImpl();
	}
	
	@RequestMapping(value = "/member/register", type = "GET")
	public String goRegister(HttpServletRequest request, HttpServletResponse response) {
		return "/member/register";
	}

	@RequestMapping(value = "/member/register", type = "POST")
	public String setMember(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setPic("");
		System.out.println(dto);
		try {
			Part part = request.getPart("pic");
			String fname = part.getSubmittedFileName();
			String saveName = System.currentTimeMillis() + "_" + fname;
			File file = new File("C:\\zzz\\upload", saveName);


			try (InputStream in = part.getInputStream();) {
				FileUtils.copyInputStreamToFile(in, file);
				dto.setPic(saveName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(md.insert(dto));
		return "redirect://board/list";
	}

	@RequestMapping(value = "/member/myPage", type = "GET")
	public String getMember(HttpServletRequest request, HttpServletResponse response) {
		return "/member/myPage";
	}
	
	@RequestMapping(value = "/member/login", type = "POST")
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		// 아이디를 갖고와서 검사한다 // 없을수있음 -> how?
		String result = "";
		MemberDTO dto = null;
		String prev = (String)request.getSession().getAttribute("prev");
		System.out.println(prev);
		try {
			dto = md.selectOne(request.getParameter("id"));	
			result = request.getParameter("pw").equals(dto.getPw()) ? prev :"redirect:/login?msg=pwError";
		} catch (Exception e) {
			System.out.println("아이디가 틀림");
			result = "redirect:/login?msg=idError";
		}
		if(result.equals(prev))	{
			HttpSession session = request.getSession();
			session.setAttribute("member", dto);
			session.setAttribute("login", "display:none");
		}
		return result;
	}

	@RequestMapping(value = "/member/login", type = "GET")
	public String getLogin(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", request.getParameter("msg")); //????무슨 기능
		request.getSession().setAttribute("prev", "redirect:/"+request.getHeader("Referer"));
		
		return "/member/login";
	}
	
	
	@RequestMapping(value = "/member/logout", type = "GET")
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "redirect:/"+request.getHeader("Referer");
	}
}
