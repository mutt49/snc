package org.demo.spinncast.misc;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.hibernate.Query;
import org.hibernate.Session;

@ManagedBean(name = "SuggestionBean")
@SessionScoped
public class SuggestionBean {

	public ArrayList<CustomerVO> getCustomerFromName(Object input) {
		String userInput = (String) input;
		ArrayList<CustomerVO> ret = new ArrayList<CustomerVO>();

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m where customer_name like :cust_name");
		hibernateQuery.setString("cust_name", "%" + userInput + "%");
		java.util.List<CustomerHBC> results = hibernateQuery.list();

		CustomerVO tempCustVo = new CustomerVO();
		for (int i = 0; i < results.size(); i++) {
			tempCustVo.setCustomer_id(results.get(0).getCustomer_id());
			tempCustVo.setCustomer_name(results.get(0).getCustomer_name());
			tempCustVo
					.setCustomer_address(results.get(0).getCustomer_address());
			tempCustVo.setBst_no(results.get(0).getBst_no());
			tempCustVo.setCst_no(results.get(0).getCst_no());
			tempCustVo.setEcc_no(results.get(0).getEcc_no());
			tempCustVo.setOctroi_no(results.get(0).getOctroi_no());
			tempCustVo.setVendor_code(results.get(0).getVendor_code());
			ret.add(tempCustVo);
		}
		session.close();
		return ret;
	}
}
