package org.demo.spinncast.bean;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.demo.spinncast.applicationHelper.SHAEncoder;
import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.CcmHBC;
import org.demo.spinncast.hibernate.UserHBC;
import org.demo.spinncast.vo.CcmVO;
import org.demo.spinncast.vo.UserVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean implements Filter {

	private String userName;
	private String password = "";
	UserVO userVo = new UserVO();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginBean() {

	}

	@SuppressWarnings("unchecked")
	public boolean validateUser() {
		try {
			UserVO user = new UserVO();
			user.setUserName(userName);
			String encPassword = SHAEncoder.get_SHA_512_SecurePassword(
					password, userName);
			user.setPassword(encPassword);
			ConnectionPool cpool = ConnectionPool.getInstance();
			Session session = cpool.getSession();
			Query hibernateQuery = session
					.createQuery("from UserHBC as m where m.userName = :uName and password = :pass");
			hibernateQuery.setString("uName", user.getUserName());
			hibernateQuery.setString("pass", user.getPassword());
			java.util.List<UserHBC> results = hibernateQuery.list();

			for (int i = 0; i < results.size(); i++) {
				userVo = new UserVO(results.get(i));
			}

			session.close();
			if (userVo.getUserId() == null || userVo.getUserId() == 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String login() {
		if (validateUser()) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", userVo);
			return "TestCertificateSearch";
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Please enter valid Username and Password !",
									null));
			return "";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "LoginPage";
	}

	public String forgotPassword() {

		return "";
	}

	public String reset() {
		userName = "";
		password = "";
		userVo = new UserVO();
		return "LoginPage";
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// check if "role" attribute is null
		if (req.getSession().getAttribute("user") == null) {
			// forward request to login.jsp
			req.getRequestDispatcher("/").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {

	}

}