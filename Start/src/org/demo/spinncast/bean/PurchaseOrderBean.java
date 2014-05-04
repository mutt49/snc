package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.handler.CustomerHandler;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.hibernate.InvoiceHeaderHBC;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.hibernate.PurchaseOrderLinesHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.demo.spinncast.vo.InvoiceHeaderVO;
import org.demo.spinncast.vo.PurchaseOrderLinesVO;
import org.demo.spinncast.vo.PurchaseOrderVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "PurchaseOrderBean")
@SessionScoped
public class PurchaseOrderBean {

	private PurchaseOrderVO searchPurchaseOrderVO;
	private PurchaseOrderVO selectedPurchaseOrderVO;
	private PurchaseOrderLinesVO poLineItem;
	private List<PurchaseOrderVO> searchList;
	private Boolean headerSaved = false;
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
		
		StringBuilder sb = new StringBuilder ("from PurchaseOrderHBC as m");
		
		if (searchPurchaseOrderVO.getPurchaseOrderNo() != 0) {
			sb.append (" where ");
			sb.append (" purchaseOrderNo = :poNo ");		
		}
		Query hibernateQuery = session
				.createQuery(sb.toString ());
		if (searchPurchaseOrderVO.getPurchaseOrderNo() != 0) {
			hibernateQuery.setInteger("poNo", searchPurchaseOrderVO.getPurchaseOrderNo());
		}
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

	public String view () {
		/*
		 * We have id.
		 * Get PO Details.
		 * Get PO Line Items.
		 * Show it on page.
		 */
		
		ConnectionPool cpool = ConnectionPool.getInstance ();
		Session session = cpool.getSession ();
		
		Query hibernateQuery = session
				.createQuery("from PurchaseOrderHBC as m where (m.purchaseOrderId = :purchaseOrderId)");
		hibernateQuery.setInteger("purchaseOrderId", selectedId);
		java.util.List<PurchaseOrderHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			selectedPurchaseOrderVO = new PurchaseOrderVO(results.get(i));
			selectedPurchaseOrderVO.setCustDetails(populateCustomerDetails(results.get(i)
					.getCustomerId()));
		}
		
		/*
		 * Get Line Items.
		 */
		populateLineItems ();
		
		session.close();
		editFlag = true;
		return "PurchaseOrderAdd";
	}

	private void populateLineItems() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession ();
		
		Query hibernateQuery = session.createQuery ("from PurchaseOrderLinesHBC as m where (m.purchaseOrderId = :purchaseOrderId)");
		hibernateQuery.setInteger ("purchaseOrderId", selectedPurchaseOrderVO.getPurchaseOrderId());
		java.util.List<PurchaseOrderLinesHBC> results = hibernateQuery.list ();
		for (PurchaseOrderLinesHBC result : results) {
			selectedPurchaseOrderVO.getPoLines().add(new PurchaseOrderLinesVO(result));
		}
		session.close ();
	}

	public PurchaseOrderVO getSelectedPurchaseOrderVO() {
		return selectedPurchaseOrderVO;
	}

	public void setSelectedPurchaseOrderVO(PurchaseOrderVO selectedPurchaseOrderVO) {
		this.selectedPurchaseOrderVO = selectedPurchaseOrderVO;
	}
	public CustomerVO populateCustomerDetails(int custId) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m where customer_id =:cust_id");
		hibernateQuery.setInteger("cust_id", custId);
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

	public PurchaseOrderLinesVO getPoLineItem() {
		return poLineItem;
	}

	public void setPoLineItem(PurchaseOrderLinesVO poLineItem) {
		this.poLineItem = poLineItem;
	}
	
	public void deleteLineItem () {
		
	}
	
	public String resetBeforeAdd () {
		selectedPurchaseOrderVO = new PurchaseOrderVO();
		getSearchList().clear();
		selectedId = null;
		editFlag = false;
		return "PurchaseOrderAdd";
	}

	public Boolean getHeaderSaved() {
		return headerSaved;
	}

	public void setHeaderSaved(Boolean headerSaved) {
		this.headerSaved = headerSaved;
	}
}