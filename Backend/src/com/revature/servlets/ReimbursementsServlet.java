package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementRequestDAO;
import com.revature.domain.ReimbursementRequest;
import com.revature.utilities.DAOUtilities;


/**
 * Servlet implementation class ReimbursementsServlet
 */

@WebServlet("/api/reimbursement-requests/*")
public class ReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ReimbursementRequestDAO dao = DAOUtilities.getReimbursementRequestDAO();
		String path = request.getRequestURI().substring(request.getContextPath().length());
//	    getMyPending(): List<ReimbursementRequest>
		if (path.equals("/api/reimbursement-requests/my/pending")) {
			List<ReimbursementRequest> requestsPending = dao.findPendingReimbursementRequest();
			response.getWriter().append(requestsPending.toString());
//		getMyResolved(): List<ReimbursementRequest>
		} else if (path.equals("/api/reimbursement-requests/my/resolved")) {
			List<ReimbursementRequest> requestsResolved = dao.findResolvedReimbursementRequest();
			response.getWriter().append(requestsResolved.toString());
		} else if ((request.getParameter("reim_id") != null) && (path.equals("/api/reimbursement-requests/approve"))) {
			String appcode = request.getParameter("appcode");
			String reim_id = request.getParameter("reim_id");
			int int_reim_id = Integer.parseInt(reim_id);
			ReimbursementRequest rreq = dao.findReimbursementRequestByReimbursementId(int_reim_id);
//			approve(id): Request
			if (appcode.equals("1")) {
//				dao.updateReimbursementRequest(rreq,1);
//				response.getWriter().append("Approved: ").append(rreq.toString());
				if (dao.updateReimbursementRequest(rreq, 1)) {
					response.getWriter().append("Approved: ").append(rreq.toString());
				}
			} else if (appcode.equals("0")) {
//			deny(id): Request
				dao.updateReimbursementRequest(rreq,0);
				response.getWriter().append("Denied: ").append(rreq.toString());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getRequestURI().substring(request.getContextPath().length());
		//	    create(request): ReimbursementRequest
		if (path.equals("/api/reimbursement-requests")) {
			ReimbursementRequest rreq = new Gson().fromJson(request.getReader(), ReimbursementRequest.class);
			DAOUtilities.getReimbursementRequestDAO().addReimbursementRequest(rreq);
			response.getWriter().append("New RREQ: ").append(rreq.toString());
		}
		
	}

}
