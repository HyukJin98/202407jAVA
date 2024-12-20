package com.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.*;
import com.board.service.BoardService;
import com.board.service.UserService;

@WebServlet("/")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = null;

		// URL에서 프로젝트 이름 뒷 부분의 문자열 얻어내기
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		// 주어진 URL에 따라 지정된 동작 수행
		if (com.equals("/")) {
			view = "index.jsp";
		} else if (com.equals("/login")) {
			view = "/sign-in/login.jsp";
		} else if (com.equals("/loginProcess")) {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			try {
				UserDTO user = new UserService().login(id, password);
				request.getSession().setAttribute("user", user);
				view = "index.jsp";
			} catch (Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				view = "login.jsp";
			}
		}

		// 로그아웃 처리
		else if (com.equals("/logout")) {
			request.getSession().invalidate();
			view = "index.jsp";
		}

		if (com.equals("/register")) {
			view = "/sign-in/register.jsp";
		}
		// 회원가입 처리
		else if (com.equals("/registerProcess")) {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");

			UserDTO userDto = new UserDTO();
			userDto.setId(id);
			userDto.setPassword(password);
			userDto.setName(name);

			try {
				new UserService().register(userDto);
				view = "redirect:login"; // 회원가입 성공 후 로그인 페이지로 이동

			} catch (Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				view = "register.jsp";
			}
		}

		if (com.equals("/list")) {
			String tmp = request.getParameter("page");
			int pageNo = (tmp != null && tmp.length() > 0) ? Integer.parseInt(tmp) : 1;

			request.setAttribute("msgList", new BoardService().getMsgList(pageNo));
			request.setAttribute("pgnList", new BoardService().getPagination(pageNo));
			System.out.println(new BoardService().getPagination(pageNo));
			view = "list.jsp";

		} else if (com.equals("/view")) {
			int num = Integer.parseInt(request.getParameter("num"));

			request.setAttribute("msg", new BoardService().getMsg(num));
			view = "view.jsp";

		} else if (com.equals("/write")) {
			String tmp = request.getParameter("num");
			int num = (tmp != null && tmp.length() > 0) ? Integer.parseInt(tmp) : 0;

			BoardDto dto = new BoardDto();
			String action = "insert";

			if (num > 0) {
				dto = new BoardService().getMsgForWrite(num);
				action = "update?num=" + num;
			}

			request.setAttribute("msg", dto);
			request.setAttribute("action", action);
			view = "write.jsp";

		} else if (com.equals("/insert")) {
			request.setCharacterEncoding("utf-8");
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			try {
				new BoardService().writeMsg(writer, title, content);
				view = "redirect:list";

			} catch (Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				view = "errorBack.jsp";
			}

		} else if (com.equals("/update")) {
			request.setCharacterEncoding("utf-8");
			int num = Integer.parseInt(request.getParameter("num"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			try {
				new BoardService().updateMsg(writer, title, content, num);
				view = "redirect:list";

			} catch (Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				view = "errorBack.jsp";
			}

		} else if (com.equals("/delete")) {
			int num = Integer.parseInt(request.getParameter("num"));

			new BoardService().deleteMsg(num);
			view = "redirect:list";
		}

		// view에 담긴 문자열에 따라 포워딩 또는 리다이렉팅
		if (view.startsWith("redirect:")) {
			response.sendRedirect(view.substring(9));
		} else {
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}