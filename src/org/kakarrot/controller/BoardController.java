package org.kakarrot.controller;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.kakarrot.annotation.RequestMapping;
import org.kakarrot.dao.BoardDAO;
import org.kakarrot.dao.BoardDAOImpl;
import org.kakarrot.domain.BoardVO;
import org.kakarrot.domain.PageMaker3;
import org.kakarrot.domain.SearchPaging;

import lombok.extern.log4j.Log4j;

// board뒤에 뭐가오든 이 Servlet이 실행됨 
@WebServlet("/board/*")
@Log4j
@MultipartConfig(location = "C:\\zzz", maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024
		* 50, fileSizeThreshold = 1024 * 1024)
public class BoardController extends BasicController {
	private static final long serialVersionUID = 1L;

	private final BoardDAO bd;

	public BoardController() {
		this.bd = new BoardDAOImpl();
	}

	// board뒤에 /list가 오면 실행되는 메서드
	@RequestMapping(value = "/board/list", type = "GET")
	public String getList(HttpServletRequest req, HttpServletResponse resp) {
		SearchPaging p = new SearchPaging(req.getParameter("page"), req.getParameter("amount"));

		p.setCategory(req.getParameter("category"));
		p.setValue(req.getParameter("val"));

		List<BoardVO> list = bd.selectList(p);

		req.setAttribute("list", list);
		System.out.println(list);
		PageMaker3 pm = new PageMaker3(list.get(list.get(0).getTotal()).getTotal(), p);
		System.out.println(pm);
		req.setAttribute("pm", pm);

		return "/board/list";
	}

	@RequestMapping(value = "/board/post", type = "GET")
	public String getPost(HttpServletRequest req, HttpServletResponse resp) {
		BoardVO vo = bd.selectOne(Long.parseLong(req.getParameter("bno")));

		req.setAttribute("postData", vo);

		return "/board/post";
	}

	@RequestMapping(value = "/board/register", type = "GET")
	public String getRegister(HttpServletRequest req, HttpServletResponse resp) {
		return "/board/register";
	}

	@RequestMapping(value = "/board/register", type = "POST")
	public String setRegister(HttpServletRequest req, HttpServletResponse resp) {
		BoardVO vo = new BoardVO();

		vo.setTitle(req.getParameter("title"));
		vo.setContent(req.getParameter("content"));
		vo.setWriter(req.getParameter("writer"));

		vo.setNotice(req.getParameter("notice") != null ? 1 : 0);

		try {
			Collection<Part> parts = req.getParts();

			for (Part part : parts) {
				if (part.getName().equals("fs") == false) {
					continue;
				}
				String fname = part.getSubmittedFileName();
				System.out.println(fname);
				String saveName = System.currentTimeMillis() + "_" + fname;
				File file = new File("C:\\zzz\\upload", saveName);

				try (InputStream in = part.getInputStream();) {
					FileUtils.copyInputStreamToFile(in, file);
					vo.addFileName(saveName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		bd.insert(vo);

		return "redirect:/list";
	}

	@RequestMapping(value = "/board/download", type = "GET")
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp) {
		String fname = req.getParameter("fname");
		String imgType = "^([\\S]+(\\.(?i)(jpg|png|gif|bmp))$)";
		try {
			File file = new File("C:\\zzz\\upload", fname);
			if (!file.getName().matches(imgType)) {
				resp.setContentType("application/octet-stream");
				FileUtils.copyFile(file, resp.getOutputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/board/img", type = "GET")
	public void imgLoding(HttpServletRequest req, HttpServletResponse resp) {
		String fname = req.getParameter("fname");
		String imgType = "^([\\S]+(\\.(?i)(jpg|png|gif|bmp))$)";
		try {
			File file = new File("C:\\zzz\\upload", fname);
			if (file.getName().matches(imgType)) {
				resp.setContentType(URLConnection.guessContentTypeFromName(file.getName()));
				FileUtils.copyFile(file, resp.getOutputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
