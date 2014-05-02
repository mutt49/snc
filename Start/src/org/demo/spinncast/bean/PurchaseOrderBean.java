package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.handler.CustomerHandler;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.demo.spinncast.vo.PurchaseOrderVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "PurchaseOrderBean")
@SessionScoped
public class PurchaseOrderBean {

	private PurchaseOrderVO searchPurchaseOrderVO;

	private List<PurchaseOrderVO> searchList;

	private Boolean editFlag = false;
	private Integer selectedId;

	public PurchaseOrderVO getSearchPurchaseOrderVO() {
		return searchPurchaseOrderVO;
	}

	public void setSearchPurchaseOrderVO(PurchaseOrderVO searchPurchaseOrderVO) {
		this.searchPurchaseOrderVO = searchPurchaseOrderVO;
	}

	public List<PurchaseOrderVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<PurchaseOrderVO> searchList) {
		this.searchList = searchList;
	}

	public Boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(Boolean editFlag) {
		this.editFlag = editFlag;
	}

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}

	public PurchaseOrderBean() {
		searchPurchaseOrderVO = new PurchaseOrderVO();
		searchList = new ArrayList<PurchaseOrderVO>();
	}

	public String reset() {
		getSearchList().clear();
		selectedId = null;
		return "PurchaseOrderSearch";
	}
	@SuppressWarnings("unchecked")
	public String search() {
		CustomerHandler custHandler = new CustomerHandler();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PurchaseOrderHBC as m where purchaseOrderNo = :poNo");
		hibernateQuery.setInteger("poNo", searchPurchaseOrderVO.getPurchaseOrderNo());
		java.util.List<PurchaseOrderHBC> results = hibernateQuery.list();
		setSearchList(new ArrayList<PurchaseOrderVO>());
		for (int i = 0; i < results.size(); i++) {
			PurchaseOrderVO po = new PurchaseOrderVO(results.get(i));
			CustomerVO custVo = new CustomerVO();
			custVo = custHandler.populateCustomerDetailsUsingCustomerId(results.get(i).getCustomerId());
			po.setCustomerName(custVo.getCustomer_name());
			/*
			 * Get Line Items for this specific Purchase Order.
			 */
			getSearchList().add(po);
		}
		System.out.println(getSearchList().size());
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return "PurchaseOrderSearch";
	}

	public String add() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PurchaseOrderHBC purchaseOrderHBC = new PurchaseOrderHBC(
				searchPurchaseOrderVO);
		session.saveOrUpdate(purchaseOrderHBC);
		trans.commit();
		session.close();
		/*
		 * Also save Line Items
		 */
		searchPurchaseOrderVO = new PurchaseOrderVO();
		return search();
	}

	public String edit() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PurchaseOrderHBC testcertificatehbc = new PurchaseOrderHBC(
				searchPurchaseOrderVO);
		session.update(testcertificatehbc);
		trans.commit();
		session.close();
		searchPurchaseOrderVO = new PurchaseOrderVO();
		return search();
	}

}