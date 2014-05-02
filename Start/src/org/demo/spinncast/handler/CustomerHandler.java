package org.demo.spinncast.handler;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.hibernate.Query;
import org.hibernate.Session;

public class CustomerHandler {

	public CustomerVO populateCustomerDetailsUsingVendorCode(String vendorCode2) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m where vendor_code =:vendorCode");
		hibernateQuery.setString("vendorCode", vendorCode2);
		java.util.List<CustomerHBC> results = hibernateQuery.list();

		CustomerVO tempCustVo = null;

		if (results.size() > 0) {
			tempCustVo = new CustomerVO(results.get(0));
		}
		session.close();
		return tempCustVo;
	}

	public CustomerVO populateCustomerDetailsUsingCustomerId(int custId) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m where customer_id =:customerId");
		hibernateQuery.setInteger("customerId", custId);
		java.util.List<CustomerHBC> results = hibernateQuery.list();
		CustomerVO tempCustVo = null;

		if (results.size() > 0) {
			tempCustVo = new CustomerVO(results.get(0));
		}
		session.close();
		return tempCustVo;
	}
}
