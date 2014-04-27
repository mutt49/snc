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

		CustomerVO tempCustVo = new CustomerVO();
		if (results.size() > 0) {
			tempCustVo.setCustomer_id(results.get(0).getCustomer_id());
			tempCustVo.setCustomer_name(results.get(0).getCustomer_name());
			tempCustVo
					.setCustomer_address(results.get(0).getCustomer_address());
			tempCustVo.setBst_no(results.get(0).getBst_no());
			tempCustVo.setCst_no(results.get(0).getCst_no());
			tempCustVo.setEcc_no(results.get(0).getEcc_no());
			tempCustVo.setOctroi_no(results.get(0).getOctroi_no());
			tempCustVo.setVendor_code(results.get(0).getVendor_code());
		}
		session.close();
		return tempCustVo;
	}
}
