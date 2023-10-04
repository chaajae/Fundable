

// ================
// Create by 차재현
// ================


package com.fun.member.controller;
import com.fun.common.MyFileRenamePolicy;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fun.member.model.service.MemberService;
import com.fun.member.model.vo.Member;
import com.fun.product.model.vo.Image;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class infoUpdateController
 */
@WebServlet("/infoUpdate.me")
public class infoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public infoUpdateController() {
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
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10* 1024 * 1024;
			
			
			String savePath = request.getServletContext().getRealPath("views/img/");
			
			
			MultipartRequest multi = new MultipartRequest(request, savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
				
		
			
		Member m =  (Member) request.getSession().getAttribute("loginUser");
		
		String userId = m.getUserId();
		
		String nick = multi.getParameter("user-nickname");
		
		String email = multi.getParameter("user-emil");
		
		String [] iArr = multi.getParameterValues("user-interest");
		
		String changePwd = m.getUserPwd();
		
		if(!multi.getParameter("changePwd").equals("")) {
			changePwd = multi.getParameter("changePwd");
		}
		
		
		
		String interest = "";
		
		if(iArr != null) {
			interest = String.join(",", iArr);
		}
		
		
		
		String hiddenSql = multi.getParameter("hiddenSql");
		
		
		Image updateImg = null;
		
		if(multi.getOriginalFileName("changeImg") != null) {
			
			Image img = new Image();
			
		img.setOriginName(multi.getOriginalFileName("changeImg"));
		img.setChangeName(multi.getFilesystemName("changeImg"));
		img.setFilePath("views/img/");
		img.setUserNo(m.getUserNo());
		
			updateImg = new MemberService().updateImg(img,m.getUserId(),hiddenSql);
			System.out.println(updateImg);
			if(updateImg != null) {
				request.getSession().setAttribute("profileImg", updateImg);
			}
		}
		
		
		Member updateMember = new MemberService().updateMember(userId,nick,email,changePwd,interest);
		
		
		if(updateMember == null ) { // 실패
			
			request.setAttribute("alertMsg","회원정보 수정에 실패했습니다.");
			request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
		
		
		}else { // 성공
			request.getSession().setAttribute("alertMsg","성공적으로 회원정보를 수정했습니다.");
			request.getSession().setAttribute("loginUser", updateMember); 
			request.getSession().setAttribute("iArr", iArr); 
			request.getRequestDispatcher("views/member/myPageInfoSet.jsp").forward(request, response);
		}
		
		
		}
		
		
	}

}
