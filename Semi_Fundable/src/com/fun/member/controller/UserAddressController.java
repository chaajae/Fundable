


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
 * Servlet implementation class UserAddressController
 */
@WebServlet("/address.me")
public class UserAddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddressController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member m = (Member) request.getSession().getAttribute("loginUser");

		request.getRequestDispatcher("views/member/myPageAddress.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member addressM = (Member) request.getSession().getAttribute("addressM");

		String addressName = request.getParameter("addressName");
		String addressPhone = request.getParameter("addressPhone");
		String [] Arr = request.getParameterValues("address");
		
		String address = "";
		
		if(Arr != null) {
			address = String.join(",", Arr);
		}

			addressM.setUserName(addressName);
			addressM.setPhone(addressPhone);
			addressM.setAddress(address);
	
			request.getSession().setAttribute("alertMsg","성공적으로 주소지를 수정했습니다.");
			request.getSession().setAttribute("addressM", addressM); 
			
			request.getRequestDispatcher("views/member/myPageAddress.jsp").forward(request, response);

	}

}
