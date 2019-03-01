package com.cg.banking.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.bankingsystem.beans.Account;
import com.cg.bankingsystem.services.BankingServices;
import com.cg.bankingsystem.services.BankingSystemImpl;

@WebServlet("/openaccount")
public class OpenAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 private BankingServices services;

	@Override
public void init() throws ServletException {
	services=new BankingSystemImpl();
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account=services.openAccount(request.getParameter("accountType"), Integer.parseInt(request.getParameter("initBalance")));
		request.setAttribute("accountNo",account.getAccountNo());
		RequestDispatcher dispatcher=request.getRequestDispatcher("openAccountPage.jsp");
				dispatcher.forward(request, response);
	}

	@Override
	public void destroy() {
		services=null;
	}

}
