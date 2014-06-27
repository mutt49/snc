package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.handler.CustomerHandler;
import org.demo.spinncast.handler.InvoiceHandler;
import org.demo.spinncast.handler.PartMasterHandler;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.hibernate.InvoiceHeaderHBC;
import org.demo.spinncast.hibernate.InvoiceLineItemHBC;
import org.demo.spinncast.hibernate.PartMasterHBC;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.hibernate.PurchaseOrderLinesHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.demo.spinncast.vo.InvoiceHeaderVO;
import org.demo.spinncast.vo.InvoiceLineItemVO;
import org.demo.spinncast.vo.PartGradeMappingVO;
import org.demo.spinncast.vo.PartMasterVO;
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
	private PurchaseOrderLinesVO poLineItem = new PurchaseOrderLinesVO();
	private PartMasterVO partVo = new PartMasterVO();
	private List<PurchaseOrderVO> searchList;
	private Boolean headerSaved = false;
	private Boolean editFlag = false;
	private Integer selectedId;
	private List<PartGradeMappingVO> selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();

	private boolean showPopUpPanel = false;

	public boolean isShowPopUpPanel() {
		return showPopUpPanel;
	}

	public void setShowPopUpPanel(boolean showPopUpPanel) {
		this.showPopUpPanel = showPopUpPanel;
	}

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
		searchPurchaseOrderVO = new PurchaseOrderVO();
		selectedPurchaseOrderVO = new PurchaseOrderVO();
		poLineItem = new PurchaseOrderLinesVO();
		partVo = new PartMasterVO();

		headerSaved = false;
		editFlag = false;
		selectedId = 0;
		selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
		getSearchList().clear();
		selectedId = null;
		return "PurchaseOrderSearch";
	}

	@SuppressWarnings("unchecked")
	public String search() {
		CustomerHandler custHandler = new CustomerHandler();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();

		StringBuilder sb = new StringBuilder("from PurchaseOrderHBC as m");

		if (searchPurchaseOrderVO.getPurchaseOrderNo() != null
				&& searchPurchaseOrderVO.getPurchaseOrderNo() != "") {
			sb.append(" where ");
			sb.append(" purchaseOrderNo = :poNo ");
		}

		sb.append(" order by m.purchaseOrderNo ");

		Query hibernateQuery = session.createQuery(sb.toString());
		if (searchPurchaseOrderVO.getPurchaseOrderNo() != null
				&& searchPurchaseOrderVO.getPurchaseOrderNo() != "") {
			hibernateQuery.setString("poNo",
					searchPurchaseOrderVO.getPurchaseOrderNo());
		}
		java.util.List<PurchaseOrderHBC> results = hibernateQuery.list();
		setSearchList(new ArrayList<PurchaseOrderVO>());
		for (int i = 0; i < results.size(); i++) {
			PurchaseOrderVO po = new PurchaseOrderVO(results.get(i));
			CustomerVO custVo = new CustomerVO();
			custVo = custHandler.populateCustomerDetailsUsingCustomerId(results
					.get(i).getCustomerId());
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
		selectedPurchaseOrderVO.setPurchaseOrderId(0);
		PurchaseOrderHBC purchaseOrderHBC = new PurchaseOrderHBC(
				selectedPurchaseOrderVO);
		session.saveOrUpdate(purchaseOrderHBC);
		trans.commit();
		session.close();
		selectedId = purchaseOrderHBC.getPurchaseOrderId();
		selectedPurchaseOrderVO.setPurchaseOrderId(selectedId);
		editFlag = true;
		return "PurchaseOrderAdd";
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

	public String view() {
		/*
		 * We have id. Get PO Details. Get PO Line Items. Show it on page.
		 */

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();

		Query hibernateQuery = session
				.createQuery("from PurchaseOrderHBC as m where (m.purchaseOrderId = :purchaseOrderId)");
		hibernateQuery.setInteger("purchaseOrderId", selectedId);
		java.util.List<PurchaseOrderHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			selectedPurchaseOrderVO = new PurchaseOrderVO(results.get(i));
			selectedPurchaseOrderVO
					.setCustDetails(populateCustomerDetails(results.get(i)
							.getCustomerId()));
		}

		/*
		 * Get Line Items.
		 */
		populateLineItems();

		session.close();
		editFlag = true;
		return "PurchaseOrderAdd";
	}

	private void populateLineItems() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();

		Query hibernateQuery = session
				.createQuery("from PurchaseOrderLinesHBC as m where (m.purchaseOrderId = :purchaseOrderId)");
		hibernateQuery.setInteger("purchaseOrderId",
				selectedPurchaseOrderVO.getPurchaseOrderId());
		java.util.List<PurchaseOrderLinesHBC> results = hibernateQuery.list();
		selectedPurchaseOrderVO
				.setPoLines(new ArrayList<PurchaseOrderLinesVO>());
		for (PurchaseOrderLinesHBC result : results) {
			selectedPurchaseOrderVO.getPoLines().add(
					new PurchaseOrderLinesVO(result));
		}
		session.close();
		showPopUpPanel = false;
	}

	public PurchaseOrderVO getSelectedPurchaseOrderVO() {
		return selectedPurchaseOrderVO;
	}

	public void setSelectedPurchaseOrderVO(
			PurchaseOrderVO selectedPurchaseOrderVO) {
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

	public String deleteLineItem() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PurchaseOrderLinesHBC poLineHBC = new PurchaseOrderLinesHBC(poLineItem);
		session.delete(poLineHBC);
		session.flush();
		trans.commit();
		session.flush();
		session.close();
		poLineItem = new PurchaseOrderLinesVO();
		populateLineItems();
		return "PurchaseOrderAdd";
	}

	public String resetBeforeAdd() {
		selectedPurchaseOrderVO = new PurchaseOrderVO();
		selectedPurchaseOrderVO.setCustDetails(new CustomerVO());
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

	public PartMasterVO getPartVo() {
		return partVo;
	}

	public void setPartVo(PartMasterVO partVo) {
		this.partVo = partVo;
	}

	public List<PartGradeMappingVO> getSelectedPartGradeMapping() {
		return selectedPartGradeMapping;
	}

	public void setSelectedPartGradeMapping(
			List<PartGradeMappingVO> selectedPartGradeMapping) {
		this.selectedPartGradeMapping = selectedPartGradeMapping;
	}

	public List<String> partNameAutoComplete(String prefix) {
		List<String> result = new ArrayList<String>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where part_name like '%"
						+ prefix + "%'");
		List<PartMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			result.add(results.get(i).getPartName());
		}
		session.close();
		return result;
	}

	public void getGradesForPart(AjaxBehaviorEvent event) {
		PartMasterHandler partMasterHandler = new PartMasterHandler();
		selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
		String partName = (String) ((UIOutput) event.getSource()).getValue();
		int partId = partMasterHandler.getPartIdByName(partName);
		partVo = new PartMasterVO();
		partVo.setPartId(partId);
		partVo.setPartName(partName);
		partMasterHandler.getGradesForPart(partVo);
		// incorrect grades are coming for part
		selectedPartGradeMapping = partVo.getPartGradeMapping();
	}

	public String addLineItem() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		getPartDetails(partVo.getPartName());
		poLineItem.setPurchaseOrderId(selectedId);
		poLineItem.setPartId(partVo.getPartId());
		poLineItem.setPurchaseOrderId(selectedPurchaseOrderVO
				.getPurchaseOrderId());

		//
		// DOES POLINE ITEM HAVE AMOUNT ???? CHECK
		//

		if (poLineItem.getUnit().equalsIgnoreCase("KG")
				|| poLineItem.getUnit().equalsIgnoreCase("set")) {
			poLineItem.setAmount(poLineItem.getQuantityKg()
					* poLineItem.getRate());
		} else {
			poLineItem.setAmount(poLineItem.getQuantity()
					* poLineItem.getRate());
		}

		PurchaseOrderLinesHBC poLinesHBC = new PurchaseOrderLinesHBC(poLineItem);
		session.saveOrUpdate(poLinesHBC);
		trans.commit();
		session.flush();
		session.close();
		partVo = new PartMasterVO();
		selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
		populateLineItems();
		return "PurchaseOrderAdd";
	}

	public void showAddLineItemPopUp() {
		poLineItem = new PurchaseOrderLinesVO();
	}

	public void getPartDetails(String partName) {
		PartMasterHandler partMasterHanlder = new PartMasterHandler();
		partVo = partMasterHanlder.populatePartMaster(partName);
	}

	public void getPartDetails(int partId) {
		PartMasterHandler partMasterHanlder = new PartMasterHandler();
		partVo = partMasterHanlder.populatePartMaster(partId);
	}

	public void getCustomerDataUsingVendorCode(ValueChangeEvent event) {
		CustomerHandler custHandler = new CustomerHandler();
		String vendorCode = (String) event.getNewValue();
		if (vendorCode.isEmpty()) {
			return;
		}
		// CustomerVO tempCustVo =
		// populateCustomerDetailsUsingVendorCode(vendorCode);
		CustomerVO tempCustVo = custHandler
				.populateCustomerDetailsUsingVendorCode(vendorCode);
		if (tempCustVo != null) {
			selectedPurchaseOrderVO.setCustDetails(tempCustVo);
			selectedPurchaseOrderVO.setCustomerId(tempCustVo.getCustomer_id());
		}
	}

	public List<String> vendorCodeAutoComplete(String prefix) {
		List<String> result = new ArrayList<String>();

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m where vendor_code like '%"
						+ prefix + "%'");
		java.util.List<CustomerHBC> results = hibernateQuery.list();

		for (int i = 0; i < results.size(); i++) {
			result.add(results.get(i).getVendor_code());
		}
		session.close();

		return result;
	}

	public String createInvoice() {
		InvoiceHeaderVO invHdr = new InvoiceHeaderVO(selectedPurchaseOrderVO);
		InvoiceHandler invHandler = new InvoiceHandler();
		invHandler.addInvoiceHeader(invHdr);

		for (PurchaseOrderLinesVO poLine : selectedPurchaseOrderVO.getPoLines()) {
			if (poLine.isChecked()) {
				if (poLine.getUnit().equalsIgnoreCase("no")
						&& poLine.getCurrQuantity() > poLine.getQuantity()) {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"Invoice Quantity Should Not Be Greater Than Original Quantity. ",
											null));
					return "";
				} else if (poLine.getCurrQuantity() > poLine.getQuantityKg()
						&& (poLine.getUnit().equalsIgnoreCase("kg") || poLine
								.getUnit().equalsIgnoreCase("set"))) {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"Invoice Quantity Should Not Be Greater Than Original Quantity. ",
											null));
					return "";
				}
			}
		}

		for (PurchaseOrderLinesVO poLine : selectedPurchaseOrderVO.getPoLines()) {
			if (poLine.isChecked()) {
				invHandler.addLineItem(poLine, invHdr);
				invHandler.updateInvoiceLIAmount(poLine);
				updatePurchaseOrderLine(poLine);
			}
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Invoice with number " + invHdr.getInvNo()
								+ " Created Successfully.", null));
		return "";
	}

	public void updatePurchaseOrderLine(PurchaseOrderLinesVO poLine) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PurchaseOrderLinesHBC poLineHBC = new PurchaseOrderLinesHBC(poLine);
		session.update(poLineHBC);
		trans.commit();
		session.close();
	}

	public void showPopup() {
		System.out.println("show PopUp");
		partVo = new PartMasterVO();
		selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
		// invLineItem = new InvoiceLineItemVO();
		poLineItem = new PurchaseOrderLinesVO();
		showPopUpPanel = true;
	}

}