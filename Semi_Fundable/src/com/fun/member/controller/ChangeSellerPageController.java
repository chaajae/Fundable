
// ================
// Create by 차재현
// ================


package com.fun.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fun.member.model.service.MemberService;
import com.fun.member.model.vo.Member;

/**
 * Servlet implementation class ChangeSellerPageController
 */
@WebServlet("/na")
public class ChangeSellerPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeSellerPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member m = (Member) request.getSession().getAttribute("loginUser");
		
		int userNo = m.getUserNo();
		
		int result = new MemberService().myPageInsertSeller(userNo);
		
		
		
		if(result > 0 ) {
			request.getRequestDispatcher("views/seller/projectManagement.jsp").forward(request, response);
			
		}
		request.getRequestDispatcher("views/seller/projectManagement.jsp").forward(request, response);
	
	
	}

}
