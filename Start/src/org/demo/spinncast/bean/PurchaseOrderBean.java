package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.LineItemHBC;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.vo.LineItemVO;
import org.demo.spinncast.vo.PurchaseOrderVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;

@ManagedBean(name = "PurchaseOrderBean")
@SessionScoped
public class PurchaseOrderBean {

	private Transaction trans;
	private PurchaseOrderVO selectedPurchaseOrderVO;

	private List<PurchaseOrderVO> searchList;

	private Boolean editFlag = true;
	private Integer selectedId;

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public PurchaseOrderVO getSelectedPurchaseOrderVO() {
		return selectedPurchaseOrderVO;
	}

	public void setSelectedPurchaseOrderVO(
			PurchaseOrderVO selectedPurchaseOrderVO) {
		this.selectedPurchaseOrderVO = selectedPurchaseOrderVO;
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
		selectedPurchaseOrderVO = new PurchaseOrderVO();
		searchList = new ArrayList<PurchaseOrderVO>();
	}

	public String reset() {
		getSearchList().clear();
		selectedId = null;
		return "PurchaseOrderSearch";
	}

	public String search() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PurchaseOrderHBC as m order by m.id");
		@SuppressWarnings("unchecked")
		java.util.List<PurchaseOrderHBC> results = hibernateQuery.list();
		setSearchList(new ArrayList<PurchaseOrderVO>());
		for (int i = 0; i < results.size(); i++) {
			PurchaseOrderVO poItem = new PurchaseOrderVO();
			poItem.setOrder_id(results.get(i).getOrder_id());
			poItem.setCustomer_id(results.get(i).getCustomer_id());
			poItem.setDescription(results.get(i).getDescription());
			poItem.setGross_price(results.get(i).getGross_price());
			poItem.setNet_value(results.get(i).getNet_value());
			/*
			 * Get Line Items for this specific Purchase Order.
			 */
			getSearchList().add(poItem);
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
				selectedPurchaseOrderVO);
		session.saveOrUpdate(purchaseOrderHBC);
		trans.commit();
		session.close();
		/*
		 * Also save Line Items
		 */
		selectedPurchaseOrderVO = new PurchaseOrderVO();
		return search();
	}

	public String edit() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PurchaseOrderHBC testcertificatehbc = new PurchaseOrderHBC(
				selectedPurchaseOrderVO);
		session.update(testcertificatehbc);
		trans.commit();
		session.close();
		selectedPurchaseOrderVO = new PurchaseOrderVO();
		return search();
	}

}