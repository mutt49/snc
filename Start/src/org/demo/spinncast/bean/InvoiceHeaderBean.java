package org.demo.spinncast.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.demo.spinncast.applicationHelper.ApplicationHepler;
import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.handler.CustomerHandler;
import org.demo.spinncast.handler.PartMasterHandler;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.hibernate.GradeMasterHBC;
import org.demo.spinncast.hibernate.InvoiceHeaderHBC;
import org.demo.spinncast.hibernate.InvoiceLineItemHBC;
import org.demo.spinncast.hibernate.PartGradeMappingHBC;
import org.demo.spinncast.hibernate.PartMasterHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.demo.spinncast.vo.GradeMasterVO;
import org.demo.spinncast.vo.InvoiceHeaderVO;
import org.demo.spinncast.vo.InvoiceLineItemVO;
import org.demo.spinncast.vo.PartGradeMappingVO;
import org.demo.spinncast.vo.PartMasterVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "InvoiceHeaderBean")
@SessionScoped
public class InvoiceHeaderBean {

	private InvoiceHeaderVO selectedInvHdrVo;
	private InvoiceHeaderVO invoiceToBeSearched;
	private InvoiceLineItemVO invLineItem;
	private List<InvoiceLineItemVO> invLineItemList;
	private PartMasterVO partVo;
	private List<InvoiceHeaderVO> searchList;
	private Boolean editFlag = false;
	private Integer selectedInvNo = 0;
	private Integer selectedInvId = 0;
	private List<PartGradeMappingVO> selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
	private boolean printInvoiceNumberOnPDF;
	// Data read from properties file
	private String vendorCode;
	private String exciseCode;
	private String regCertNo;
	private String plaNo;
	private String rangeAddress;
	private String division;
	private String tariffHeading;
	private String excemption;

	private boolean headerSaved = false;
	private String retPage;
	private Integer maxInvNo;
	private Boolean printEntirePage;
	private String nameofexcisable;
	private String serviceTax;
	private String incomeTaxPan;
	private String searchCustomer;
	private boolean showPopUpPanel = false;

	public String getInvoiceTypeString() {
		return invoiceTypeString;
	}

	public void setInvoiceTypeString(String invoiceTypeString) {
		this.invoiceTypeString = invoiceTypeString;
	}

	public boolean isInvoiceTypeBoolean() {
		return invoiceTypeBoolean;
	}

	public void setInvoiceTypeBoolean(boolean invoiceTypeBoolean) {
		this.invoiceTypeBoolean = invoiceTypeBoolean;
	}

	private String invoiceTypeString;
	private boolean invoiceTypeBoolean;
	
	static final Integer lineLimitOnPdf = 16;

	public boolean isShowPopUpPanel() {
		return showPopUpPanel;
	}

	public void setShowPopUpPanel(boolean showPopUpPanel) {
		this.showPopUpPanel = showPopUpPanel;
	}

	public String getSearchCustomer() {
		return searchCustomer;
	}

	public void setSearchCustomer(String searchCustomer) {
		this.searchCustomer = searchCustomer;
	}

	public void setMaxInvNo(Integer maxInvNo) {
		this.maxInvNo = maxInvNo;
	}

	public List<PartGradeMappingVO> getSelectedPartGradeMapping() {
		return selectedPartGradeMapping;
	}

	public void setSelectedPartGradeMapping(
			List<PartGradeMappingVO> selectedPartGradeMapping) {
		this.selectedPartGradeMapping = selectedPartGradeMapping;
	}

	public String getTariffHeading() {
		return tariffHeading;
	}

	public void setTariffHeading(String tariffHeading) {
		this.tariffHeading = tariffHeading;
	}

	public String getExcemption() {
		return excemption;
	}

	public void setExcemption(String excemption) {
		this.excemption = excemption;
	}

	public int getMaxInvNo() {
		return maxInvNo;
	}

	public void setMaxInvNo(int maxInvNo) {
		this.maxInvNo = maxInvNo;
	}

	public String getRetPage() {
		return retPage;
	}

	public void setRetPage(String retPage) {
		this.retPage = retPage;
	}

	public List<InvoiceLineItemVO> getInvLineItemList() {
		return invLineItemList;
	}

	public void setInvLineItemList(List<InvoiceLineItemVO> invLineItemList) {
		this.invLineItemList = invLineItemList;
	}

	public PartMasterVO getPartVo() {
		return partVo;
	}

	public void setPartVo(PartMasterVO partVo) {
		this.partVo = partVo;
	}

	public InvoiceLineItemVO getInvLineItem() {
		return invLineItem;
	}

	public void setInvLineItem(InvoiceLineItemVO invLineItem) {
		this.invLineItem = invLineItem;
	}

	public boolean isHeaderSaved() {
		return headerSaved;
	}

	public void setHeaderSaved(boolean headerSaved) {
		this.headerSaved = headerSaved;
	}

	public String getPlaNo() {
		return plaNo;
	}

	public void setPlaNo(String plaNo) {
		this.plaNo = plaNo;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}

	public String getRegCertNo() {
		return regCertNo;
	}

	public void setRegCertNo(String regCertNo) {
		this.regCertNo = regCertNo;
	}

	public String getRangeAddress() {
		return rangeAddress;
	}

	public void setRangeAddress(String rangeAddress) {
		this.rangeAddress = rangeAddress;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public InvoiceHeaderVO getSelectedInvHdrVo() {
		return selectedInvHdrVo;
	}

	public void setSelectedInvHdrVo(InvoiceHeaderVO selectedInvHdrVo) {
		this.selectedInvHdrVo = selectedInvHdrVo;
	}

	public List<InvoiceHeaderVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<InvoiceHeaderVO> searchList) {
		this.searchList = searchList;
	}

	public Integer getSelectedInvId() {
		return selectedInvId;
	}

	public Integer getSelectedInvNo() {
		return selectedInvNo;
	}

	public void setSelectedInvNo(Integer selectedInvNo) {
		this.selectedInvNo = selectedInvNo;
	}

	public void setSelectedInvId(Integer selectedInvNo) {
		this.selectedInvNo = selectedInvNo;
	}

	public Boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(Boolean editFlag) {
		this.editFlag = editFlag;
	}

	public InvoiceHeaderBean() {
		selectedInvHdrVo = new InvoiceHeaderVO();
		searchList = new ArrayList<InvoiceHeaderVO>();
		invLineItem = new InvoiceLineItemVO();
		partVo = new PartMasterVO();
		invoiceTypeBoolean = false;
		// readProperties();
		readProps();
	}

	public void readProps() {       System.out.println("Working Directory = " +
            System.getProperty("user.dir"));
		ApplicationHepler.readProperties();
		vendorCode = ApplicationHepler.getVendorCode();
		exciseCode = ApplicationHepler.getExciseCode();
		regCertNo = ApplicationHepler.getRegCertNo();
		plaNo = ApplicationHepler.getPlaNo();
		rangeAddress = ApplicationHepler.getRangeAddress();
		division = ApplicationHepler.getDivision();
		nameofexcisable = ApplicationHepler.getNameofexcisable();
		tariffHeading = ApplicationHepler.getTariffHeading();
		excemption = ApplicationHepler.getExcemption();
		serviceTax = ApplicationHepler.getServiceTax();
		incomeTaxPan = ApplicationHepler.getIncomeTaxPan();
	}

	public String reset() {
		selectedInvHdrVo = new InvoiceHeaderVO();
		invLineItem = new InvoiceLineItemVO();
		invLineItemList = new ArrayList<InvoiceLineItemVO>();
		getSearchList().clear();
		partVo = new PartMasterVO();
		editFlag = false;
		selectedInvNo = 0;
		selectedInvId = 0;
		return "InvoiceHeaderSearch";
	}

	@SuppressWarnings("unchecked")
	public String search() {

		ConnectionPool cpool = ConnectionPool.getInstance();
		try {
			selectedInvNo = Integer.parseInt(selectedInvHdrVo.getInvNo());
		} catch (NumberFormatException e) {
			selectedInvNo = 0;
		}
		int customer_id = 0;
		if (selectedInvHdrVo.getCustDetails() != null) {
			if (selectedInvHdrVo.getCustDetails().getCustomer_id() != null)
				customer_id = selectedInvHdrVo.getCustDetails()
						.getCustomer_id();
		}

		Session session = cpool.getSession();
		StringBuilder query = new StringBuilder(
				"from InvoiceHeaderHBC as m where (m.invNo= :invNo and :invNo !=0 ) or (:invNo = 0) ");

		if (customer_id != 0) {
			query.append(" and m.customerId= :custId ");
		}

		query.append(" order by m.invId");

		Query hibernateQuery = session.createQuery(query.toString());
		hibernateQuery.setInteger("invNo", selectedInvNo);
		if (customer_id != 0)
			hibernateQuery.setInteger("custId", customer_id);

		java.util.List<InvoiceHeaderHBC> results = hibernateQuery.list();
		searchList = new ArrayList<InvoiceHeaderVO>();
		for (int i = 0; i < results.size(); i++) {
			InvoiceHeaderVO tempInvHdr = new InvoiceHeaderVO(results.get(i));
			tempInvHdr.setCustDetails(populateCustomerDetails(results.get(i)
					.getCustomerId()));
			searchList.add(tempInvHdr);
		}
		System.out.println(getSearchList().size());
		session.close();
		return "InvoiceHeaderSearch";
	}

	@SuppressWarnings("unchecked")
	public String search(int invId) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		selectedInvId = selectedInvHdrVo.getInvId();
		Session session = cpool.getSession();
		/*
		 * Query hibernateQuery = session .createQuery(
		 * "from InvoiceHeaderHBC as m where (m.invId = :invId) and ((m.invNo= :invNo and :invNo !=0 ) or (:invNo = 0))  order by m.invId"
		 * );
		 */
		Query hibernateQuery = session
				.createQuery("from InvoiceHeaderHBC as m where (m.invId = :invId) order by m.invId");
		hibernateQuery.setInteger("invId", selectedInvHdrVo.getInvId());
		java.util.List<InvoiceHeaderHBC> results = hibernateQuery.list();
		searchList = new ArrayList<InvoiceHeaderVO>();
		for (int i = 0; i < results.size(); i++) {
			InvoiceHeaderVO tempInvHdr = new InvoiceHeaderVO(results.get(i));
			tempInvHdr.setCustDetails(populateCustomerDetails(results.get(i)
					.getCustomerId()));
			searchList.add(tempInvHdr);
		}
		System.out.println(getSearchList().size());
		session.close();
		return "InvoiceHeaderSearch";
	}

	public CustomerVO populateCustomerDetails(String custName) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m where customer_name =:cust_name");
		hibernateQuery.setString("cust_name", custName);
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

	public void add() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		try {
			selectedInvHdrVo.setCustomerId(selectedInvHdrVo.getCustDetails()
					.getCustomer_id());
			InvoiceHeaderHBC invHeaderHBC = new InvoiceHeaderHBC(
					selectedInvHdrVo);
			session.saveOrUpdate(invHeaderHBC);
			trans.commit();
			// selectedInvId = invHeaderHBC.getInvId();
			selectedInvHdrVo.setInvId(invHeaderHBC.getInvId());
			headerSaved = true;
			editFlag = true;
			addLIamountAggregation(new InvoiceLineItemVO());
		} catch (Exception e) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Can not save Invoice Header. Please try with correct values",
									null));
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void getCustomerData(ValueChangeEvent event) {
		String custName = (String) event.getNewValue();
		if (custName.isEmpty()) {
			return;
		}
		CustomerVO tempCustVo = populateCustomerDetails(custName);
		selectedInvHdrVo.setCustDetails(tempCustVo);
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
		selectedInvHdrVo.setCustDetails(tempCustVo);
	}

	public String getNameofexcisable() {
		return nameofexcisable;
	}

	public void setNameofexcisable(String nameofexcisable) {
		this.nameofexcisable = nameofexcisable;
	}

	public String getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(String serviceTax) {
		this.serviceTax = serviceTax;
	}

	public String getIncomeTaxPan() {
		return incomeTaxPan;
	}

	public void setIncomeTaxPan(String incomeTaxPan) {
		this.incomeTaxPan = incomeTaxPan;
	}

	/*
	 * public PartMasterVO populatePartMaster(String partName) { ConnectionPool
	 * cpool = ConnectionPool.getInstance(); Session session =
	 * cpool.getSession(); Query hibernateQuery = session
	 * .createQuery("from PartMasterHBC as m where part_name =:part_name");
	 * hibernateQuery.setString("part_name", partName);
	 * java.util.List<PartMasterHBC> results = hibernateQuery.list();
	 * 
	 * PartMasterVO tempPartVo = null; if (results.size() > 0 && results.size()
	 * < 2) { tempPartVo = new PartMasterVO(results.get(0)); } session.close();
	 * return tempPartVo; }
	 * 
	 * public PartMasterVO populatePartMaster(int partId) { ConnectionPool cpool
	 * = ConnectionPool.getInstance(); Session session = cpool.getSession();
	 * Query hibernateQuery = session
	 * .createQuery("from PartMasterHBC as m where part_id =:part_id");
	 * hibernateQuery.setInteger("part_id", partId);
	 * java.util.List<PartMasterHBC> results = hibernateQuery.list();
	 * 
	 * PartMasterVO tempPartVo = null; if (results.size() > 0 && results.size()
	 * < 2) { tempPartVo = new PartMasterVO(results.get(0)); } session.close();
	 * return tempPartVo; }
	 */
	public void getPartDetails(String partName) {
		PartMasterHandler partMasterHanlder = new PartMasterHandler();
		partVo = partMasterHanlder.populatePartMaster(partName);
	}

	public void getPartDetails(int partId) {
		PartMasterHandler partMasterHanlder = new PartMasterHandler();
		partVo = partMasterHanlder.populatePartMaster(partId);
	}
	
	public void showPopup(){
		System.out.println("show PopUp");
		partVo = new PartMasterVO();
		selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
		invLineItem = new InvoiceLineItemVO(); 
		showPopUpPanel = true;
	}

	public String addLineItem() {

		/*
		 * We check whether this line item will fit on the PDF or not! If not we
		 * will not add it and return back.
		 */
		List<String> partDetails = getWrappedStringList(partVo.getPartName()
				+ " " + invLineItem.getPkgDesc(), 40);

		if (selectedInvHdrVo.getLinesOfLineItem() + partDetails.size() > lineLimitOnPdf) {
			/*
			 * Add error msg on page.
			 */
			System.out.println("ERROR: LINEITEM WILL NOT FIT ON PDF.");
			return getLineItemsForInvId();
		}

		selectedInvHdrVo.setLinesOfLineItem(selectedInvHdrVo
				.getLinesOfLineItem() + partDetails.size());

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		getPartDetails(partVo.getPartName());
		invLineItem.setPartId(partVo.getPartId());
		invLineItem.setInvId(selectedInvHdrVo.getInvId());

		if (invLineItem.getUnit().equalsIgnoreCase("KG")
				|| invLineItem.getUnit().equalsIgnoreCase("set")) {
			invLineItem.setAmount(invLineItem.getQuantityKgs()
					* invLineItem.getRate());
		} else {
			invLineItem.setAmount(invLineItem.getQuantityNo()
					* invLineItem.getRate());
		}

		InvoiceLineItemHBC invLineItemHBC = new InvoiceLineItemHBC(invLineItem);
		session.saveOrUpdate(invLineItemHBC);
		trans.commit();
		session.flush();
		session.close();
		selectedInvId = invLineItemHBC.getInvId();
		addLIamountAggregation(invLineItem);
		invLineItem = new InvoiceLineItemVO();
		partVo = new PartMasterVO();
		selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
		return getLineItemsForInvId();
	}

	public String getLineItemsForInvId() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from InvoiceLineItemHBC as m where m.invId =:inv_id order by m.invLineItemId");
		hibernateQuery.setInteger("inv_id", selectedInvHdrVo.getInvId());
		java.util.List<InvoiceLineItemHBC> results = hibernateQuery.list();
		invLineItemList = new ArrayList<InvoiceLineItemVO>();
		
		selectedInvHdrVo.setLinesOfLineItem(0);
		
		for (int i = 0; i < results.size(); i++) {
			InvoiceLineItemVO tempInvLI = new InvoiceLineItemVO();
			tempInvLI.setInvId(results.get(i).getInvId());
			tempInvLI.setAmount(results.get(i).getAmount());
			tempInvLI.setInvLineItemId(results.get(i).getInvLineItemId());
			tempInvLI.setPartId(results.get(i).getPartId());
			tempInvLI.setPkgDesc(results.get(i).getPkgDesc());
			tempInvLI.setQuantityKgs(results.get(i).getQuantityKgs());
			tempInvLI.setQuantityNo(results.get(i).getQuantityNo());
			tempInvLI.setRate(results.get(i).getRate());
			tempInvLI.setUnit(results.get(i).getUnit());
			tempInvLI.setSerialNo(results.get(i).getSerialNo());
			tempInvLI.setNoOfPkgs(results.get(i).getNoOfPkgs());
			tempInvLI.setGradeId(results.get(i).getGradeId());

			getPartDetails(tempInvLI.getPartId());

			List<String> partDetails = getWrappedStringList(
					partVo.getPartName() + " " + tempInvLI.getPkgDesc(), 40);

			selectedInvHdrVo.setLinesOfLineItem(selectedInvHdrVo
					.getLinesOfLineItem() + partDetails.size());

			invLineItemList.add(tempInvLI);
		}
		System.out.println(getSearchList().size());
		session.close();
		partVo = new PartMasterVO();
		selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
		showPopUpPanel = false;
		return "InvoiceHeaderAdd";
	}

	public String viewInvoice() {
		headerSaved = true;
		editFlag = true;
		search(selectedInvHdrVo.getInvId());
		selectedInvHdrVo = searchList.get(0);
		retPage = "invoiceHeaderSearch";
		return getLineItemsForInvId();
	}

	public String clearHeader() {

		reset();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("select max(m.invId)+1 from InvoiceHeaderHBC as m");
		List list = hibernateQuery.list();
		if (!list.isEmpty()) {
			maxInvNo = Integer.parseInt(list.get(0).toString());
		} else {
			maxInvNo = 1;
		}
		selectedInvHdrVo.setInvNo(maxInvNo.toString());
		session.close();
		if (retPage.equalsIgnoreCase("InvoiceHeaderAdd")) {
			return "InvoiceHeaderAdd";
		} else {
			return "invoiceHeaderSearch";
		}
	}

	public void addLIamountAggregation(InvoiceLineItemVO invLine) {
		float liTotalAmount = invLine.getAmount();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		String hql = "update InvoiceHeaderHBC set liAmountTotal = :li_Amount_Total+liAmountTotal where invId = :inv_id ";
		Query query = session.createQuery(hql);
		query.setFloat("li_Amount_Total", liTotalAmount);
		query.setInteger("inv_id", invLineItem.getInvId());
		int result = query.executeUpdate();
		trans.commit();
		session.flush();
		session.close();
		session = cpool.getSession();
		trans = session.beginTransaction();
		hql = "update InvoiceHeaderHBC set net_total_amount = :pkg_frwd_chg + li_amount_total where invId = :inv_id ";
		query = session.createQuery(hql);
		query.setFloat("pkg_frwd_chg", selectedInvHdrVo.getPkgFrwdChg());

		query.setInteger("inv_id", selectedInvId);

		result = query.executeUpdate();
		trans.commit();
		session.flush();
		session.close();

		search(invLineItem.getInvId());
		selectedInvHdrVo = searchList.get(0);

		Float taxTotal = taxCalculation();

		session = cpool.getSession();
		trans = session.beginTransaction();
		hql = "update InvoiceHeaderHBC set grand_total = net_total_amount + :taxTotal + :freight_insurance where invId = :inv_id ";
		query = session.createQuery(hql);

		query.setInteger("inv_id", selectedInvId);

		query.setFloat("freight_insurance",
				selectedInvHdrVo.getFreightInsurance());
		query.setFloat("taxTotal", taxTotal);

		result = query.executeUpdate();
		trans.commit();
		session.flush();
		session.close();

		System.out.println("No of rows updated" + result);
		// remove this call
		viewInvoice();
	}

	public void subtractLiAmount() {
		float liTotalAmount = invLineItem.getAmount();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		String hql = "update InvoiceHeaderHBC set liAmountTotal = liAmountTotal - :li_Amount_Total where invId = :inv_id ";
		Query query = session.createQuery(hql);
		query.setFloat("li_Amount_Total", liTotalAmount);
		query.setInteger("inv_id", invLineItem.getInvId());
		int result = query.executeUpdate();
		trans.commit();
		session.flush();
		session.close();
		session = cpool.getSession();
		trans = session.beginTransaction();
		hql = "update InvoiceHeaderHBC set net_total_amount = pkg_frwd_chg + li_amount_total where invId = :inv_id ";
		query = session.createQuery(hql);
		query.setInteger("inv_id", invLineItem.getInvId());
		result = query.executeUpdate();
		trans.commit();
		session.flush();
		session.close();
		selectedInvId = invLineItem.getInvId();
		search(selectedInvId);

		selectedInvHdrVo = searchList.get(0);

		Float taxTotal = taxCalculation();

		session = cpool.getSession();
		trans = session.beginTransaction();
		hql = "update InvoiceHeaderHBC set grand_total = net_total_amount + :totalTax + freight_insurance where invId = :inv_id ";
		query = session.createQuery(hql);
		query.setInteger("inv_id", invLineItem.getInvId());
		query.setFloat("totalTax", taxTotal);
		result = query.executeUpdate();
		trans.commit();
		session.flush();
		session.close();
		System.out.println("No of rows updated" + result);
		// remove this call
		viewInvoice();
	}

	public Float taxCalculation() {
		Float bedAmount = round(
				((selectedInvHdrVo.getLiAmountTotal() + selectedInvHdrVo.getPkgFrwdChg()) * (selectedInvHdrVo
						.getBedRate() / 100)), 2);
		Float edCessAmount = round(
				(bedAmount * (selectedInvHdrVo.getEdCessRate() / 100)), 2);
		Float shsCessAmount = round(
				(bedAmount * (selectedInvHdrVo.getShsCess() / 100)), 2);

		Float vatAmount = round(
				((selectedInvHdrVo.getLiAmountTotal()
						+ selectedInvHdrVo.getPkgFrwdChg() + bedAmount
						+ edCessAmount + shsCessAmount) * (selectedInvHdrVo
						.getVatOrCst() / 100)),
				2);
		Float taxTotal = bedAmount + edCessAmount + shsCessAmount + vatAmount;
		return taxTotal;
	}

	/*
	 * public String edit() { System.out.println(getSelectedId());
	 * ConnectionPool cpool = ConnectionPool.getInstance(); Session session =
	 * cpool.getSession(); Transaction trans = session.beginTransaction();
	 * PurchaseOrderHBC testcertificatehbc = new PurchaseOrderHBC(
	 * selectedInvHdrVO); session.update(testcertificatehbc); trans.commit();
	 * session.close(); selectedInvHdrVO = new PurchaseOrderVO(); return
	 * search(); }
	 */

	public static void drawS(String line, PDPageContentStream contentStream,
			int index) throws IOException {

		PDType1Font font = PDType1Font.HELVETICA;
		float charSpacing = 0;
		if (line.length() > 1) {
			float size = 7 * font.getStringWidth(line) / 1000;
			float free = 200 - size;
			if (free > 0) {
				charSpacing = free / (line.length() - 1);
			}
		}
		contentStream.appendRawCommands(String.format("%f Tc\n", charSpacing)
				.replace(',', '.'));

		contentStream.drawString(line);
		contentStream.moveTextPositionByAmount(5,
				0 + PDPage.PAGE_SIZE_A4.getHeight() - index);
	}

	public ByteArrayOutputStream createPDF(boolean bill) throws IOException,
			COSVisitorException {

		PDDocument document;
		PDPage page;
		PDFont font;
		PDFont fontBold;
		PDPageContentStream contentStream;

		float height = PDPage.PAGE_SIZE_A4.getHeight();
		float width = PDPage.PAGE_SIZE_A4.getWidth() - 32;
		int xOffset = 30;
		int yOffset = 0;
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		int pageCount;
		// Creating Document
		try {

			if (bill) {
				pageCount = 1;
			} else {
				pageCount = 6;
			}

			document = new PDDocument();
			// Creating Pages
			for (int i = 0; i < pageCount; i++) {

				page = new PDPage(PDPage.PAGE_SIZE_A4);

				// Adding page to document
				document.addPage(page);

				// Adding FONT to document
				font = new PDType1Font("Helvetica");
				fontBold = new PDType1Font("Helvetica-Bold");
				// Next we start a new content stream which will "hold" the to
				// be
				// created content.
				contentStream = new PDPageContentStream(document, page);

				// Let's define the content stream
				contentStream.setStrokingColor(0, 0, 0);
				if (printEntirePage) {
					// Margin
					contentStream.addLine(xOffset + 2, yOffset + 15,
							xOffset + 2, yOffset + height - 2);
					contentStream.addLine(xOffset + 2, yOffset + 15, xOffset
							+ width - 2, yOffset + 15);
					contentStream.addLine(xOffset + 2, yOffset + height - 2,
							xOffset + width - 2, yOffset + height - 2);
					contentStream.addLine(xOffset + width - 2, yOffset + 15,
							xOffset + width - 2, yOffset + height - 2);

					// Top Section
					contentStream.addLine(xOffset + 2, yOffset + height - 80,
							xOffset + width - 2, yOffset + height - 80);
					if (i == 0) {
						InputStream in = new FileInputStream(
								new File("Uno.jpg"));
						PDJpeg img = new PDJpeg(document, in);
						contentStream.drawXObject(img, xOffset + 7, yOffset
								+ height - 75, 550, 70);
					}
					if (i == 1) {
						InputStream in = new FileInputStream(
								new File("Two.jpg"));
						PDJpeg img = new PDJpeg(document, in);
						contentStream.drawXObject(img, xOffset + 7, yOffset
								+ height - 75, 550, 70);
					}
					if (i == 2) {
						InputStream in = new FileInputStream(new File(
								"Three.jpg"));
						PDJpeg img = new PDJpeg(document, in);
						contentStream.drawXObject(img, xOffset + 7, yOffset
								+ height - 75, 550, 70);
					}
					if (i > 2) {
						InputStream in = new FileInputStream(new File(
								"Rest.jpg"));
						PDJpeg img = new PDJpeg(document, in);
						contentStream.drawXObject(img, xOffset + 7, yOffset
								+ height - 75, 550, 70);
					}
					// Second Section
					contentStream.addLine(
							Float.valueOf(
									new Double(xOffset + (width / 2.0))
											.toString()).floatValue(),
							yOffset + height - 80,
							Float.valueOf(
									new Double(xOffset + (width / 2.0))
											.toString()).floatValue(), yOffset
									+ height - 180);

					contentStream.addLine(xOffset + 2, yOffset + height - 180,
							xOffset + width - 2, yOffset + height - 180);

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 93);
					contentStream.drawString("Code No. / Central Excise   :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 140,
							yOffset + height - 93);
					contentStream.drawString("711004");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 106);
					contentStream.drawString("Registration Certificate No. :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 140,
							yOffset + height - 106);
					contentStream.drawString("AAKFS 2087HXM 002");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 119);
					contentStream.drawString("P.L.A. No :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 140,
							yOffset + height - 119);
					contentStream.drawString("02/74/2006");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 132);
					contentStream.drawString("Commissionrate :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 140,
							yOffset + height - 132);
					contentStream.drawString("71");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 145);
					contentStream.drawString("Range :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 50,
							yOffset + height - 145);
					contentStream.drawString("R - IV");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 140,
							yOffset + height - 145);
					contentStream.drawString("Division :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 183,
							yOffset + height - 145);
					contentStream.drawString("I (Mulshi)  10");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 158);
					contentStream.drawString("Range & Division Address :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 140,
							yOffset + height - 158);
					contentStream.drawString("41A, SASSON Rd., Ice House,");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 140,
							yOffset + height - 171);
					contentStream.drawString("A WING, PUNE - 411 001");
					contentStream.endText();
				}
				// Right Pane - Second Pane
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 306 - 16,
							yOffset + height - 93);
					contentStream.drawString("Name of Excisable Commodity :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 450 - 16,
							yOffset + height - 93);
					contentStream.drawString(nameofexcisable);
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 306 - 16,
							yOffset + height - 106);
					contentStream.drawString("Tariff heading No. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 450 - 16,
						yOffset + height - 106);
				contentStream.drawString(tariffHeading); // Data
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 306 - 16,
							yOffset + height - 119);
					contentStream.drawString("Exemption Notif. No. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 450 - 16,
						yOffset + height - 119);
				contentStream.drawString(excemption); // Data
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 306 - 16,
							yOffset + height - 132);
					contentStream.drawString("Service Tax :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 450 - 16,
							yOffset + height - 132);
					contentStream.drawString(serviceTax); // Data
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 306 - 16,
							yOffset + height - 145);
					contentStream.drawString("Income Tax P.A.N. No. :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 450 - 16,
							yOffset + height - 145);
					contentStream.drawString(incomeTaxPan); // Data
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 306 - 16,
							yOffset + height - 158);
					contentStream
							.drawString("MVAT TIN No. : 27160010336 V w.e.f. 1 - 4 - 2006");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 306 - 16,
							yOffset + height - 171);
					contentStream
							.drawString("C.S.T. TIN No. : 27160010336 C w.e.f. 1 - 4 - 2006");
					contentStream.endText();
				}
				// Third Section
				// Lines for Third Section.. 3 verticle sections. 2 lines.
				if (printEntirePage) {
					contentStream.addLine(
							Float.valueOf(
									new Double(xOffset + (width / 3.0))
											.toString()).floatValue(),
							yOffset + height - 180,
							Float.valueOf(
									new Double(xOffset + (width / 3.0))
											.toString()).floatValue(), yOffset
									+ height - 320);

					contentStream.addLine(
							Float.valueOf(
									new Double(xOffset + (width * 2.0 / 3.0))
											.toString()).floatValue(),
							yOffset + height - 180,
							Float.valueOf(
									new Double(xOffset + (width * 2.0 / 3.0))
											.toString()).floatValue(), yOffset
									+ height - 320);

					contentStream.addLine(xOffset + 2, yOffset + height - 320,
							xOffset + width - 2, yOffset + height - 320);

					// First Pane - Third Section
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 194);
					contentStream.drawString("Consignee Name :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 90, yOffset
						+ height - 194);
				contentStream.drawString(selectedInvHdrVo.getCustDetails()
						.getVendor_code());
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.moveTextPositionByAmount(xOffset + 12, yOffset
						+ height - 207);
				contentStream.drawString(selectedInvHdrVo.getCustDetails()
						.getCustomer_name()); // Data
				contentStream.endText();

				int addressLineIndex = 0;
				int addressLinePadding = -13;
				System.out.println(selectedInvHdrVo.getCustDetails()
						.getCustomer_address());
				for (String addressLine : getFormattedAddress(selectedInvHdrVo
						.getCustDetails().getCustomer_address(), 33)) {

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 220
									+ (addressLineIndex * addressLinePadding));
					contentStream.drawString(addressLine);
					contentStream.endText();
					addressLineIndex++;
				}

				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 272);
					contentStream.drawString("ECC NO. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 63, yOffset
						+ height - 272);
				contentStream.drawString(selectedInvHdrVo.getCustDetails()
						.getEcc_no());
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 285);
					contentStream.drawString("OCTROI/LBT NO. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 95, yOffset
						+ height - 285);
				contentStream.drawString(selectedInvHdrVo.getCustDetails()
						.getOctroi_no());
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 298);
					contentStream.drawString("CST NO. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 63, yOffset
						+ height - 298);
				contentStream.drawString(selectedInvHdrVo.getCustDetails()
						.getCst_no());
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 311);
					contentStream.drawString("VAT NO. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 63, yOffset
						+ height - 311);
				contentStream.drawString(selectedInvHdrVo.getCustDetails()
						.getBst_no());
				contentStream.endText();

				// Second Pane - Third Section
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 194);
					contentStream.drawString("Delivery Address & Name :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.moveTextPositionByAmount(xOffset + 198, yOffset
						+ height - 207);
				contentStream.drawString(selectedInvHdrVo.getDeliveryTo());
				contentStream.endText();

				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 272);
					contentStream.drawString("ECC NO. :");
					contentStream.endText();
				}
				/*
				 * contentStream.beginText(); contentStream.setFont(fontBold,
				 * 9); contentStream.moveTextPositionByAmount(xOffset + 249,
				 * yOffset + height - 272);
				 * contentStream.drawString(selectedInvHdrVo.getCustDetails()
				 * .getEcc_no()); contentStream.endText();
				 */if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 285);
					contentStream.drawString("OCTROI/LBT NO. :");
					contentStream.endText();
				}
				/*
				 * contentStream.beginText(); contentStream.setFont(fontBold,
				 * 9); contentStream.moveTextPositionByAmount(xOffset + 281,
				 * yOffset + height - 285);
				 * contentStream.drawString(selectedInvHdrVo.getCustDetails()
				 * .getOctroi_no()); contentStream.endText();
				 */if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 298);
					contentStream.drawString("CST NO. :");
					contentStream.endText();
				}
				/*
				 * contentStream.beginText(); contentStream.setFont(fontBold,
				 * 9); contentStream.moveTextPositionByAmount(xOffset + 249,
				 * yOffset + height - 298);
				 * contentStream.drawString(selectedInvHdrVo.getCustDetails()
				 * .getCst_no()); contentStream.endText();
				 */if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 311);
					contentStream.drawString("VAT NO. :");
					contentStream.endText();
				}
				/*
				 * contentStream.beginText(); contentStream.setFont(fontBold,
				 * 9); contentStream.moveTextPositionByAmount(xOffset + 249,
				 * yOffset + height - 311);
				 * contentStream.drawString(selectedInvHdrVo.getCustDetails()
				 * .getBst_no()); contentStream.endText();
				 */
				addressLineIndex = 0;
				addressLinePadding = -13;

				for (String addressLine : getFormattedAddress(
						selectedInvHdrVo.getDeliveryAddress(), 33)) {

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 220
									+ (addressLineIndex * addressLinePadding));
					contentStream.drawString(addressLine);
					contentStream.endText();
					addressLineIndex++;
				}
				if (printEntirePage) {
					// Third Pane - Third Section
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 194);
					contentStream.drawString("Invoice No. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 458 - 20,
						yOffset + height - 194);

				if (printInvoiceNumberOnPDF)
					contentStream.drawString(selectedInvHdrVo.getInvNo());
				else
					contentStream.drawString("");
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 207);
					contentStream.drawString("Date :");
					contentStream.endText();
				}

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String dateInString = sdf.format(selectedInvHdrVo.getInvDate());

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 464 - 50,
						yOffset + height - 207);
				contentStream.drawString(dateInString); // Data
				contentStream.endText();

				// Third Section Divider 1
				if (printEntirePage) {
					contentStream.addLine(
							Float.valueOf(
									new Double(xOffset + (width * 2.0 / 3.0))
											.toString()).floatValue(), yOffset
									+ height - 215, xOffset + width - 2,
							yOffset + height - 215);

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 230);
					contentStream.drawString("T.C. No. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 505 - 80,
						yOffset + height - 230);
				contentStream.drawString(selectedInvHdrVo.getTcNo()); // Data
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 243);
					contentStream.drawString("I.R. No. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 464 - 40,
						yOffset + height - 243);
				contentStream.drawString(selectedInvHdrVo.getIrNo()); // Data
				contentStream.endText();

				// Third Section Divider 2
				if (printEntirePage) {
					contentStream.addLine(
							Float.valueOf(
									new Double(xOffset + (width * 2.0 / 3.0))
											.toString()).floatValue(), yOffset
									+ height - 251, xOffset + width - 2,
							yOffset + height - 251);

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 266);
					contentStream.drawString("Mode of Transport :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 505 - 40,
						yOffset + height - 266);
				contentStream.drawString(selectedInvHdrVo.getModeOfTransport()); // Data
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 279);
					contentStream.drawString("Vehicle No. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 464 - 25,
						yOffset + height - 279);
				contentStream.drawString(selectedInvHdrVo.getVehicleNo()); // Data
				contentStream.endText();

				// Third Section Divider 3
				if (printEntirePage) {
					contentStream.addLine(
							Float.valueOf(
									new Double(xOffset + (width * 2.0 / 3.0))
											.toString()).floatValue(), yOffset
									+ height - 287, xOffset + width - 2,
							yOffset + height - 287);

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 302);
					contentStream.drawString("L. R. No. :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 505 - 75,
						yOffset + height - 302);
				contentStream.drawString(selectedInvHdrVo.getLrNo()); // Data
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 315);
					contentStream.drawString("Terms of Payment :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 464, yOffset
						+ height - 315);
				contentStream.drawString(selectedInvHdrVo.getPaymentTerms()); // Data
				contentStream.endText();
				if (printEntirePage) {
					// Fourth Section
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 334);
					contentStream.drawString("Your P.O. No. : ");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 12 + 70,
						yOffset + height - 334);
				contentStream.drawString(selectedInvHdrVo.getPurchaseOrderId()); // Data
				contentStream.endText();

				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(width - 80, yOffset
							+ height - 334);
					contentStream.drawString("Date : ");
					contentStream.endText();
				}

				sdf = new SimpleDateFormat("dd/MM/yyyy");
				String poDateString = sdf.format(selectedInvHdrVo
						.getPurchaseOrderDate());

				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(width - 50, yOffset
						+ height - 334);
				contentStream.drawString(poDateString); // Data
				contentStream.endText();

				// Line below Your P.O. No.
				if (printEntirePage) {
					contentStream.addLine(xOffset + 2, yOffset + height - 340,
							xOffset + width - 2, yOffset + height - 340);

					// Vertical Lines Fifth Section
					contentStream.addLine(xOffset + 30, yOffset + height - 340,
							xOffset + 30, yOffset + height - 600);
					// Header - Fifth Section

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 354);
					contentStream.drawString("Sr.");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 365);
					contentStream.drawString("No.");
					contentStream.endText();

					String descriptionAndSpecs = new String ("Description and specification of goods");
					float descriptionAndSpecsWidth = fontBold.getStringWidth(descriptionAndSpecs) / 1000 * 9;
					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 30 + ((227 - descriptionAndSpecsWidth) / 2),
							yOffset + height - 354);
					contentStream
							.drawString(descriptionAndSpecs);
					contentStream.endText();
				}

				// Line Items // Data
				float currentLineOnPdf = 0;
				float topLineOfCurrentLineItemOnPdf = 0;
				int lineItemPadding = -13;
				float yOffsetForLineItemText = yOffset + height - 395;
				for (int lineItemIndex = 0; lineItemIndex < invLineItemList
						.size(); lineItemIndex++) {

					String tempStr = invLineItemList.get(lineItemIndex)
							.getPkgDesc();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream
							.moveTextPositionByAmount(
									xOffset + 5,
									(yOffsetForLineItemText + topLineOfCurrentLineItemOnPdf
											* lineItemPadding));
					contentStream.drawString(Integer.toString(invLineItemList
							.get(lineItemIndex).getSerialNo()));
					contentStream.endText();
					getPartDetails(invLineItemList.get(lineItemIndex)
							.getPartId());
					List<String> partDetails = getWrappedStringList(
							partVo.getPartName() + " " + tempStr, 40);
					int partLineIndex = 0;
					int partLinePadding = -13;
					for (String str : partDetails) {

						contentStream.beginText();
						contentStream.setFont(fontBold, 9);
						contentStream
								.moveTextPositionByAmount(
										xOffset + 40,
										yOffsetForLineItemText
												+ ((currentLineOnPdf + partLineIndex) * lineItemPadding));
						contentStream.drawString(str);
						contentStream.endText();
						partLineIndex++;
					}

					List<String> noOfPartsLines = getWrappedStringList(
							invLineItemList.get(lineItemIndex).getNoOfPkgs(), 7);
					int noOfPackagesIndex = 0;
					int noOfPackagesPadding = -13;
					for (String str : noOfPartsLines) {

						contentStream.beginText();
						contentStream.setFont(fontBold, 9);
						int woodenPadding = 0;
						if (noOfPackagesIndex == 0)
							woodenPadding = 9;
						else
							woodenPadding = 4;
						contentStream
								.moveTextPositionByAmount(
										xOffset + 253 + woodenPadding,
										yOffsetForLineItemText
												+ ((currentLineOnPdf + noOfPackagesIndex) * lineItemPadding));
						contentStream.drawString(str);
						contentStream.endText();
						noOfPackagesIndex++;
					}

					if (partLineIndex > noOfPackagesIndex) {
						currentLineOnPdf += partLineIndex;
					} else {
						currentLineOnPdf += noOfPackagesIndex;
					}

					partVo = new PartMasterVO();

					// contentStream.beginText();
					// contentStream.setFont(fontBold, 9);
					// contentStream.moveTextPositionByAmount(xOffset + 253 + 9,
					// yOffset + height - 395
					// + (lineItemIndex * lineItemPadding));
					// contentStream.drawString(invLineItemList.get(lineItemIndex)
					// .getNoOfPkgs());
					// contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 253 + 9 + 45, yOffsetForLineItemText
									+ topLineOfCurrentLineItemOnPdf
									* lineItemPadding);
					contentStream.drawString(Integer.toString(invLineItemList
							.get(lineItemIndex).getQuantityNo()));
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 253 + 9 + 90, yOffsetForLineItemText
									+ topLineOfCurrentLineItemOnPdf
									* lineItemPadding);
					contentStream.drawString(Float.toString(invLineItemList
							.get(lineItemIndex).getQuantityKgs()));
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 253 + 9 + 130, yOffsetForLineItemText
									+ topLineOfCurrentLineItemOnPdf
									* lineItemPadding);
					contentStream.drawString(invLineItemList.get(lineItemIndex)
							.getUnit());
					contentStream.endText();

					String rateString = Float.toString(invLineItemList.get(
							lineItemIndex).getAmount());
					if (rateString.endsWith(".0")) {
						rateString = rateString.replaceAll("\\.0", "\\.00");
					}
					float rateStringWidth = fontBold.getStringWidth(rateString) / 1000 * 10;

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width - 15
							- rateStringWidth, yOffsetForLineItemText
							+ topLineOfCurrentLineItemOnPdf * lineItemPadding);
					contentStream.drawString(rateString);
					contentStream.endText();

					String totalAmountString = Float.toString(invLineItemList
							.get(lineItemIndex).getRate());
					if (totalAmountString.endsWith(".0")) {
						totalAmountString = totalAmountString.replaceAll(
								"\\.0", "\\.00");
					}
					float totalAmountStringWidth = fontBold
							.getStringWidth(totalAmountString) / 1000 * 10;

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width - 82
							- totalAmountStringWidth, yOffsetForLineItemText
							+ topLineOfCurrentLineItemOnPdf * lineItemPadding);
					contentStream.drawString(totalAmountString);
					contentStream.endText();

					topLineOfCurrentLineItemOnPdf = currentLineOnPdf;
				}

				// Line Items Over.
				String invoiceType = null;
				if (invoiceTypeBoolean) {
					if (invoiceTypeString == null)
						invoiceTypeString = new String ("");
					invoiceType= invoiceTypeString;
				}
				else
					invoiceType= new String ("Proof M/C Non Ferrous Casting");
				
				float invoiceTypeWidth = fontBold.getStringWidth(invoiceType) / 1000 * 9;
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 30 + ((227 - invoiceTypeWidth) / 2), yOffset
						+ height - 365);
				contentStream.drawString(invoiceType); // Data
				contentStream.endText();
				if (printEntirePage) {
					contentStream.addLine(xOffset + 250 + 7, yOffset + height
							- 340, xOffset + 250 + 7, yOffset + height - 600);
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
						+ height - 376);
				contentStream.drawString("");
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 253 + 9,
							yOffset + height - 354);
					contentStream.drawString("No. of ");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 255 + 9,
							yOffset + height - 365);
					contentStream.drawString("pkgs");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 259 + 9,
						yOffset + height - 376);
				contentStream.drawString("");
				contentStream.endText();
				// Line after No. of Packages
				if (printEntirePage) {
					contentStream.addLine(xOffset + 300 + 9 - 11, yOffset
							+ height - 340, xOffset + 300 + 9 - 11, yOffset
							+ height - 600);

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 314 + 9 - 11, yOffset + height - 354);
					contentStream.drawString("Qty.");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 308 + 9 - 11, yOffset + height - 365);
					contentStream.drawString("in Nos.");
					contentStream.endText();
					// Line after Qnty. in Nos
					contentStream.addLine(xOffset + 345 + 9 - 11, yOffset
							+ height - 340, xOffset + 345 + 9 - 11, yOffset
							+ height - 600);

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 354 + 9 - 11, yOffset + height - 354);
					contentStream.drawString("Total");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 350 + 9 - 11, yOffset + height - 365);
					contentStream.drawString("Weight");
					contentStream.endText();
					contentStream.addLine(xOffset + 387 + 9 - 11, yOffset
							+ height - 340, xOffset + 387 + 9 - 11, yOffset
							+ height - 600);

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 402 - 11,
							yOffset + height - 354);
					contentStream.drawString("U.O.M.");
					contentStream.endText();
					contentStream.addLine(xOffset + width - 125 - 11, yOffset
							+ height - 340, xOffset + width - 125 - 11, yOffset
							+ height - 600);

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 452 - 11,
							yOffset + height - 354);
					contentStream.drawString("Rate per");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 460 - 11,
							yOffset + height - 365);
					contentStream.drawString("unit");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 520 - 6,
							yOffset + height - 354);
					contentStream.drawString("Total");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 514 - 6,
							yOffset + height - 365);
					contentStream.drawString("Amount");
					contentStream.endText();
				}
				/*
				 * contentStream.beginText(); contentStream.setFont(fontBold,
				 * 9); contentStream.moveTextPositionByAmount(xOffset + 435,
				 * yOffset + height - 365);0 contentStream.drawString("U.O.M.");
				 * contentStream.endText();
				 * 
				 * contentStream.beginText(); contentStream.setFont(fontBold,
				 * 9); contentStream.moveTextPositionByAmount(xOffset + 425,
				 * yOffset + height - 376);
				 * contentStream.drawString("quantity");
				 * contentStream.endText();
				 */
				if (printEntirePage) {
					// Bottom Border of Header of Table.
					contentStream.addLine(xOffset + 2, yOffset + height - 380,
							xOffset + width - 2, yOffset + height - 380);

					// Fifth Section
					// Bottom Border of Table.
					contentStream.addLine(xOffset + 2, yOffset + height - 600,
							xOffset + width - 2, yOffset + height - 600);

					// Vert Line between rate and total amount
					contentStream.addLine(xOffset + width - 61 - 11, yOffset
							+ height - 340, xOffset + width - 61 - 11,
							yOffset + 15);
					// Vert Line before P&F
					contentStream.addLine(xOffset + width - 125 - 11, yOffset
							+ height - 600, xOffset + width - 125 - 11,
							yOffset + 15);

					// Middle Section over
					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 612);
					contentStream
							.drawString("Total Central Excise Duty Paid (in words): ");
					contentStream.endText();
				}
				Float bedAmount = round(
						((selectedInvHdrVo.getLiAmountTotal()
								+ invLineItem.getAmount() + selectedInvHdrVo.getPkgFrwdChg()) * (selectedInvHdrVo
								.getBedRate() / 100)), 2);
				Float edCessAmount = round(
						(bedAmount * (selectedInvHdrVo.getEdCessRate() / 100)),
						2);
				Float shsCessAmount = round(
						(bedAmount * (selectedInvHdrVo.getShsCess() / 100)), 2);

				Float vatAmount = round(
						((selectedInvHdrVo.getLiAmountTotal()
								+ invLineItem.getAmount()
								+ selectedInvHdrVo.getPkgFrwdChg() + bedAmount
								+ edCessAmount + shsCessAmount) * (selectedInvHdrVo
								.getVatOrCst() / 100)), 2);

				/*
				 * int dollars = (int) Math.floor(money); double cents = money -
				 * dollars; int centsAsInt = (int) (100 * cents);
				 */
				int n = (int) round((bedAmount + edCessAmount + shsCessAmount),
						2);
				float nPaise = round(
						(bedAmount + edCessAmount + shsCessAmount), 2) - n;
				int paiseAsInt = (int) (100 * nPaise);

				StringBuilder exciseInWords = new StringBuilder();
				exciseInWords.append(pw((n / 1000000000), " Hundred"));

				exciseInWords.append(pw((n / 10000000) % 100, " crore"));

				exciseInWords.append(pw(((n / 100000) % 100), " Lakh"));

				exciseInWords.append(pw(((n / 1000) % 100), " Thousand"));

				exciseInWords.append(pw(((n / 100) % 10), " Hundred"));

				exciseInWords.append(pw((n % 100), " "));

				if (paiseAsInt != 0) {
					exciseInWords.append(" Rupees And");
					exciseInWords.append(pw((paiseAsInt % 100), " "));

					exciseInWords.append(" Paise");
				}

				exciseInWords.append(" Only");

				List<String> exciseLines = getWrappedStringList(
						exciseInWords.toString(), 70);
				int exciseIndex = 0;
				int excisePadding = -10;
				for (String str : exciseLines) {

					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 160,
							yOffset + height - 612
									+ (exciseIndex * excisePadding));
					contentStream.drawString(str);
					contentStream.endText();
					exciseIndex++;
				}

				// contentStream.beginText();
				// contentStream.setFont(font, 8);
				// contentStream.moveTextPositionByAmount(xOffset + 160, yOffset
				// + height - 612);
				// contentStream.drawString(exciseInWords.toString() + " Only");
				// // Data
				// contentStream.endText();
				// contentStream.beginText();
				// contentStream.setFont(font, 8);
				// contentStream.moveTextPositionByAmount(xOffset + 160, yOffset
				// + height - 622);
				// contentStream.drawString("");
				// contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 634);
					contentStream.drawString("Total Amount (in words): ");
					contentStream.endText();
				}

				n = new Integer(
						(int) round(selectedInvHdrVo.getGrandTotal(), 0));
				StringBuilder amountInWords = new StringBuilder();
				amountInWords.append(pw((n / 1000000000), " Hundred"));

				amountInWords.append(pw((n / 10000000) % 100, " crore"));

				amountInWords.append(pw(((n / 100000) % 100), " Lakh"));

				amountInWords.append(pw(((n / 1000) % 100), " Thousand"));

				amountInWords.append(pw(((n / 100) % 10), " Hundred"));

				amountInWords.append(pw((n % 100), " "));

				contentStream.beginText();
				contentStream.setFont(font, 8);
				contentStream.moveTextPositionByAmount(xOffset + 100, yOffset
						+ height - 634);
				contentStream.drawString(amountInWords.toString() + " Only"); // Data
				contentStream.endText();
				contentStream.beginText();
				contentStream.setFont(font, 8);
				contentStream.moveTextPositionByAmount(xOffset + 160, yOffset
						+ height - 644);
				contentStream.drawString("");
				contentStream.endText();

				if (printEntirePage) {
					// Line Below Total Amount in Words.
					contentStream.addLine(xOffset + 2, yOffset + height - 650,
							xOffset + width - 125 - 11, yOffset + height - 650);
				}
				int registerOffset = -13;
				// Declaration
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 675 + registerOffset);
					drawS("I/We hereby certify that my/our registration certificate",
							contentStream, 655);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 683 + registerOffset);
					drawS("under the Maharashtra Value Added Tax Act, 2002 is in",
							contentStream, 655);
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 691 + registerOffset);
					drawS("force on the date which the sale of the goods specified",
							contentStream, 665);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 699 + registerOffset);
					drawS("in this tax invoice is made by me/us and that the transac-",
							contentStream, 665);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 707 + registerOffset);
					drawS("tion of sale convered by this tax invoice has been effected",
							contentStream, 100);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 715 + registerOffset);
					drawS("by me/us and it shall be accounted in the turnover of sales",
							contentStream, 665);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 723 + registerOffset);
					drawS("while filling of return and the due tax, if any, payable on",
							contentStream, 665);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 731 + registerOffset);
					contentStream
							.drawString("the sale has been paid or shall be paid.");
					contentStream.endText();

					// Line between registration and certification
					contentStream.addLine(xOffset + 210,
							yOffset + height - 677, xOffset + 210, yOffset
									+ height - 735 + registerOffset);

					// Line below Date & Time of Removal.
					contentStream.addLine(xOffset + 2, yOffset + height - 677,
							xOffset + width - 125 - 11, yOffset + height - 677);

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 661);
					contentStream
							.drawString("Date & Time of Issue of invoice:");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 672);
					contentStream.drawString("Date & Time of Removal: ");
					contentStream.endText();
				}

				sdf = new SimpleDateFormat("dd/MM/yyyy\t\t\t\t\t\t\thh:mm:ss");
				dateInString = sdf.format(selectedInvHdrVo.getInvIssueDate());

				contentStream.beginText();
				contentStream.setFont(fontBold, 8);
				contentStream.moveTextPositionByAmount(xOffset + 155, yOffset
						+ height - 661);
				contentStream.drawString(dateInString); // Data
				contentStream.endText();

				dateInString = sdf.format(selectedInvHdrVo.getRemovalDate());
				contentStream.beginText();
				contentStream.setFont(fontBold, 8);
				contentStream.moveTextPositionByAmount(xOffset + 155, yOffset
						+ height - 672);
				contentStream.drawString(dateInString); // Data
				contentStream.endText();

				if (printEntirePage) {
					// Bottom right labels
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 628);
					contentStream.drawString("Pkg Chg.");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 650);
					contentStream.drawString("Assessable /");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 658);
					contentStream.drawString("Tariff Value");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 680);
					contentStream.drawString("B.E.D.");
					contentStream.endText();

					// Right Justifying %s.
					String bedRateString = Float.toString(selectedInvHdrVo
							.getBedRate());
					bedRateString = bedRateString.concat("%");
					float bedRateStringWidth = fontBold
							.getStringWidth(bedRateString) / 1000 * 8;

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width - 75
							- bedRateStringWidth, yOffset + height - 680);
					contentStream.drawString(bedRateString);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 702);
					contentStream.drawString("Ed. Cess");
					contentStream.endText();

					String edCessString = Float.toString(selectedInvHdrVo
							.getEdCessRate());
					edCessString = edCessString.concat("%");
					float edCessStringWidth = fontBold
							.getStringWidth(edCessString) / 1000 * 8;

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width - 75
							- edCessStringWidth, yOffset + height - 702);
					contentStream.drawString(edCessString);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 724);
					contentStream.drawString("SHSCess");
					contentStream.endText();

					String shsCessString = Float.toString(selectedInvHdrVo
							.getShsCess());
					shsCessString = shsCessString.concat("%");
					float shsCessStringWidth = fontBold
							.getStringWidth(shsCessString) / 1000 * 8;

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width - 75
							- shsCessStringWidth, yOffset + height - 724);
					contentStream.drawString(shsCessString);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 746);
					contentStream.drawString("Sub Total");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 768);
					contentStream.drawString("VAT/CST");
					contentStream.endText();

					String vatCstString = Float.toString(selectedInvHdrVo
							.getVatOrCst());
					vatCstString = vatCstString.concat("%");
					float vatCstStringWidth = fontBold
							.getStringWidth(vatCstString) / 1000 * 8;

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width - 75
							- vatCstStringWidth, yOffset + height - 768);
					contentStream.drawString(vatCstString);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11 - 2, yOffset + height - 788);
					contentStream.drawString("Freight & Ins.");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 117 - 11, yOffset + height - 807);
					contentStream.drawString("GRAND");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 115 - 11, yOffset + height - 817);
					contentStream.drawString("TOTAL");
					contentStream.endText();
				}

				String pkgFrwdAmountString = Float.toString(round(
						selectedInvHdrVo.getPkgFrwdChg(), 2));
				if (pkgFrwdAmountString.endsWith(".0")) {
					pkgFrwdAmountString = pkgFrwdAmountString.replaceAll(
							"\\.0", "\\.00");
				}
				float pkgFrwdAmountStringWidth = fontBold
						.getStringWidth(pkgFrwdAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + width - 15
						- pkgFrwdAmountStringWidth, yOffset + height - 630);
				contentStream.drawString(pkgFrwdAmountString);
				contentStream.endText();

				System.out.print("After conversion number in words is :");

				Float netAssassableVal = selectedInvHdrVo.getPkgFrwdChg()
						+ selectedInvHdrVo.getLiAmountTotal()
						+ invLineItem.getAmount();

				String assessableAmountString = Float.toString(round(
						netAssassableVal, 2));
				if (assessableAmountString.endsWith(".0")) {
					assessableAmountString = assessableAmountString.replaceAll(
							"\\.0", "\\.00");
				}
				float assessableAmountStringWidth = fontBold
						.getStringWidth(assessableAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + width - 15
						- assessableAmountStringWidth, yOffset + height - 655);
				contentStream.drawString(assessableAmountString);
				contentStream.endText();

				String bedAmountString = Float.toString(bedAmount);
				if (bedAmountString.endsWith(".0")) {
					bedAmountString = bedAmountString.replaceAll("\\.0",
							"\\.00");
				}
				float bedAmountStringWidth = fontBold
						.getStringWidth(bedAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + width - 15
						- bedAmountStringWidth, yOffset + height - 680);
				contentStream.drawString(bedAmountString);
				contentStream.endText();

				String edCessAmountString = Float.toString(round(edCessAmount,
						2));
				if (edCessAmountString.endsWith(".0")) {
					edCessAmountString = edCessAmountString.replaceAll("\\.0",
							"");
				}
				float edCessAmountStringWidth = fontBold
						.getStringWidth(edCessAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + width - 15
						- edCessAmountStringWidth, yOffset + height - 703);
				contentStream.drawString(edCessAmountString);
				contentStream.endText();

				String shsCessAmountString = Float.toString(round(
						shsCessAmount, 2));
				if (shsCessAmountString.endsWith(".0")) {
					shsCessAmountString = shsCessAmountString.replaceAll(
							"\\.0", "\\.00");
				}
				float shsCessAmountStringWidth = fontBold
						.getStringWidth(shsCessAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + width - 15
						- shsCessAmountStringWidth, yOffset + height - 725);
				contentStream.drawString(shsCessAmountString);
				contentStream.endText();

				Float subTotal = netAssassableVal + bedAmount + edCessAmount
						+ shsCessAmount;

				String subTotalAmountString = Float
						.toString(round(subTotal, 2));
				if (subTotalAmountString.endsWith(".0")) {
					subTotalAmountString = subTotalAmountString.replaceAll(
							"\\.0", "\\.00");
				}
				float subTotalAmountStringWidth = fontBold
						.getStringWidth(subTotalAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + width - 15
						- subTotalAmountStringWidth, yOffset + height - 747);
				contentStream.drawString(subTotalAmountString);
				contentStream.endText();

				String vatCstAmountString = Float.toString(round(vatAmount, 2));
				if (vatCstAmountString.endsWith(".0")) {
					vatCstAmountString = vatCstAmountString.replaceAll("\\.0",
							"");
				}
				float vatCstAmountStringWidth = fontBold
						.getStringWidth(vatCstAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + width - 15
						- vatCstAmountStringWidth, yOffset + height - 768);
				contentStream.drawString(vatCstAmountString);
				contentStream.endText();

				String frtInsuranceAmountString = Float.toString(round(
						selectedInvHdrVo.getFreightInsurance(), 2));
				if (frtInsuranceAmountString.endsWith(".0")) {
					frtInsuranceAmountString = frtInsuranceAmountString
							.replaceAll("\\.0", "\\.00");
				}
				float frtInsuranceAmountStringWidth = fontBold
						.getStringWidth(frtInsuranceAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream
						.moveTextPositionByAmount(xOffset + width - 15
								- frtInsuranceAmountStringWidth, yOffset
								+ height - 788);
				contentStream.drawString(frtInsuranceAmountString);
				contentStream.endText();

				String grandTotalAmountString = Float.toString(round(
						selectedInvHdrVo.getGrandTotal(), 0));
				if (grandTotalAmountString.endsWith(".0")) {
					grandTotalAmountString = grandTotalAmountString.replaceAll(
							"\\.0", "\\.00");
				}
				float grandTotalAmountStringWidth = fontBold
						.getStringWidth(grandTotalAmountString) / 1000 * 10;

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + width - 15
						- grandTotalAmountStringWidth, yOffset + height - 810);
				contentStream.drawString(grandTotalAmountString);
				contentStream.endText();

				if (printEntirePage) {
					// Certification
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 213,
							yOffset + height - 675 + registerOffset);
					drawS("Certified that the particulars given above are true and correct",
							contentStream, 655);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 213,
							yOffset + height - 683 + registerOffset);
					drawS("and the amount indicated represents the price acctually",
							contentStream, 655);
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 213,
							yOffset + height - 691 + registerOffset);
					drawS("charged and that there is no flow of additional consideration",
							contentStream, 665);
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 7);
					contentStream.moveTextPositionByAmount(xOffset + 213,
							yOffset + height - 699 + registerOffset);
					contentStream
							.drawString("directly or indirectly from the buyer.");
					contentStream.endText();

					// Line above Excise Duty Payable
					contentStream.addLine(xOffset + 210, yOffset + height - 705
							+ registerOffset, xOffset + width - 113
							+ registerOffset - 11, yOffset + height - 705
							+ registerOffset);

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 216,
							yOffset + height - 722 + registerOffset);
					contentStream.drawString(selectedInvHdrVo.getExciseDutyPayableString());
					contentStream.endText();

					// Line Below Excise Duty Payable
					// contentStream.addLine(xOffset + 2, yOffset + height
					// - 735 + registerOffset, xOffset + 464, yOffset + height -
					// 735 + registerOffset);

					// Bottom Signatures
					// Top Border
					contentStream.addLine(xOffset + 2, yOffset + height - 748,
							xOffset + width - 125 - 11, yOffset + height - 748);
					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 746 + registerOffset);
					contentStream.drawString("For");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + 28,
							yOffset + height - 746 + registerOffset);
					contentStream.drawString("SPIN-N-CAST (Unit - II)");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + 34 + registerOffset);
					contentStream.drawString("Authorised Signatory");
					contentStream.endText();

					// Line after First box
					contentStream
							.addLine(xOffset + 150, yOffset + height - 748,
									xOffset + 150, yOffset + 15);

					contentStream.addLine(xOffset + 2, yOffset + height - 748,
							xOffset + width - 125 - 11, yOffset + height - 748);
					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 156,
							yOffset + height - 746 + registerOffset);
					contentStream.drawString("For");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + 172,
							yOffset + height - 746 + registerOffset);
					contentStream.drawString("SPIN-N-CAST (Unit - II)");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 156,
							yOffset + 34 + registerOffset);
					contentStream.drawString("Pre-Authentication By");
					contentStream.endText();

					// Line after Second box
					contentStream
							.addLine(xOffset + 300, yOffset + height - 748,
									xOffset + 300, yOffset + 15);

					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 306,
							yOffset + 34 + registerOffset);
					contentStream.drawString("Check By");
					contentStream.endText();

					// Line after Third box
					contentStream.addLine(xOffset + 370 - 7, yOffset + height
							- 748, xOffset + 370 - 7, yOffset + 15);

					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 376 - 7,
							yOffset + 34 + registerOffset);
					contentStream.drawString("Check By");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream
							.moveTextPositionByAmount(xOffset, yOffset + 5);
					contentStream
							.drawString("(Based on C.B.E. & C, Circular No. 29/29/94-CX 6 dt. 21-3-1994)");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 130, yOffset + 5);
					contentStream
							.drawString("Subject to Pune Jurisdiction only");
					contentStream.endText();
				}
				contentStream.stroke();
				// Let's close the content stream
				contentStream.close();

			}

			// Finally Let's save the PDF
			document.save(output);
			document.close();

		} catch (IOException | COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

	private List<String> getFormattedAddress(String bigString, int characters) {
		List<String> returnValue = new ArrayList<String>();
		bigString = bigString.replaceAll("\r", "");
		if (bigString.contains("\n")) {
			// This is from a text area.
			while (bigString.length() > characters) {
				String temp = bigString.substring(0, characters);
				temp = temp.substring(0, temp.indexOf("\n"));
				bigString = bigString.replaceFirst(temp + "\n", "");
				returnValue.add(temp);
			}
			returnValue.add(bigString);
		} else {
			if (bigString.length() < characters) {
				returnValue.add(bigString);
			} else {
				while (bigString.length() > characters) {
					String temp = bigString.substring(0, characters);
					if (temp.lastIndexOf(" ") > 0) {
						temp = temp.substring(0, temp.lastIndexOf(" "));
						bigString = bigString.replaceFirst(temp + " ", "");
					} else {
						temp = temp.substring(0, temp.lastIndexOf(","));
						bigString = bigString.replaceFirst(temp + ",", "");
					}
					returnValue.add(temp);
				}
				returnValue.add(bigString);
			}
		}
		return returnValue;
	}

	public void print() {
		setPrintEntirePage(true);
		try {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			output = createPDF(false);
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc
					.getExternalContext().getResponse();

			response.addHeader("Content-Type", "application/force-download");
			response.addHeader(
					"Content-Disposition",
					"attachment; filename=\"Invoice_"
							+ selectedInvHdrVo.getInvId() + ".pdf\"");
			response.getOutputStream().write(output.toByteArray());
			fc.responseComplete(); // Important! Otherwise JSF will attempt to
									// render the response which obviously will
									// fail since it's already written with a
									// file and closed.

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Print Over");
	}

	public void printBill() {
		setPrintEntirePage(true);
		try {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			output = createPDF(true);
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc
					.getExternalContext().getResponse();

			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"InvoiceAttachment_"
							+ selectedInvHdrVo.getInvId() + ".pdf\"");
			response.getOutputStream().write(output.toByteArray());
			fc.responseComplete(); // Important! Otherwise JSF will attempt to
									// render the response which obviously will
									// fail since it's already written with a
									// file and closed.

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Print BillOver");
	}

	public List<String> customerNameAutoComplete(String prefix) {
		List<String> result = new ArrayList<String>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m where customer_name like '%"
						+ prefix + "%'");
		java.util.List<CustomerHBC> results = hibernateQuery.list();

		for (int i = 0; i < results.size(); i++) {
			result.add(results.get(i).getCustomer_name());
		}
		session.close();

		return result;
	}

	public List<String> partNameAutoComplete(String prefix) {
		List<String> result = new ArrayList<String>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where part_name like '"
						+ prefix + "%'");
		List<PartMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			result.add(results.get(i).getPartName());
		}
		session.close();
		return result;
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

	public Boolean getPrintEntirePage() {
		return printEntirePage;
	}

	public void setPrintEntirePage(Boolean printEntirePage) {
		this.printEntirePage = printEntirePage;
	}

	public String pw(int n, String ch) {
		String one[] = {

		" ", " One", " Two", " Three", " Four", " Five", " Six", " Seven",
				" Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen",
				" Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen",
				" Nineteen" };

		String ten[] = { " ", " ", " Twenty", " Thirty", " Forty", " Fifty",
				" Sixty", " Seventy", " Eighty", " Ninety" };

		StringBuilder returnValue = new StringBuilder();
		if (n > 19) {
			returnValue.append(ten[n / 10] + " " + one[n % 10]);
		} else {
			returnValue.append(one[n]);
		}
		if (n > 0)
			returnValue.append(ch);

		return returnValue.toString();
	}

	public static List<String> getWrappedStringList(String bigString,
			int characters) {
		List<String> returnValue = new ArrayList<String>();
		if (bigString.length() < characters) {
			bigString = bigString.trim();

			returnValue.add(bigString);
		} else {
			while (bigString.length() > characters) {
				bigString = bigString.trim();
				if (bigString.length() <= characters) {
					returnValue.add(bigString);
					return returnValue;
				}
				String temp = bigString.substring(0, characters);

				if (temp.indexOf(" ") != -1)
					temp = temp.substring(0, temp.lastIndexOf(" "));
				temp = temp.replaceAll("\\(", "\\\\(");
				temp = temp.replaceAll("\\)", "\\\\)");
				temp = temp.replaceAll("\\+", "\\\\+");
				bigString = bigString.replaceFirst(temp, "");
				temp = temp.replaceAll("\\\\\\(", "(");
				temp = temp.replaceAll("\\\\\\)", ")");
				temp = temp.replaceAll("\\\\\\+", "+");
				returnValue.add(temp);
			}
			returnValue.add(bigString.replaceAll("\\\\\\(", "(")
					.replaceAll("\\\\\\)", ")").replaceAll("\\\\\\+", "+"));
		}
		return returnValue;
	}

	public void addTaxes() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		selectedInvHdrVo.setCustomerId(selectedInvHdrVo.getCustDetails()
				.getCustomer_id());
		Transaction trans = session.beginTransaction();
		InvoiceHeaderHBC invHeaderHBC = new InvoiceHeaderHBC(selectedInvHdrVo);
		session.saveOrUpdate(invHeaderHBC);
		// selectedInvId = invHeaderHBC.getInvId();
		trans.commit();
		session.flush();
		session.close();
		selectedInvHdrVo.setInvId(invHeaderHBC.getInvId());
		headerSaved = true;
		editFlag = true;
		addLIamountAggregation(new InvoiceLineItemVO());
	}

	public String deleteLineItem() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		subtractLiAmount();
		InvoiceLineItemHBC invLineItemHBC = new InvoiceLineItemHBC(invLineItem);
		session.delete(invLineItemHBC);
		session.flush();
		trans.commit();
		session.flush();
		session.close();
		invLineItem = new InvoiceLineItemVO();
		return getLineItemsForInvId();
	}

	public static float round(float value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.floatValue();
	}

	public void getGradesForPart(AjaxBehaviorEvent event) {
		PartMasterHandler partMasterHandler = new PartMasterHandler();
		selectedPartGradeMapping = new ArrayList<PartGradeMappingVO>();
		String partName = (String) ((UIOutput) event.getSource()).getValue();
		int partId = partMasterHandler.getPartIdByExactName(partName);
		partVo = new PartMasterVO();
		partVo.setPartId(partId);
		partVo.setPartName(partName);
		partMasterHandler.getGradesForPart(partVo);
		// incorrect grades are coming for part
		selectedPartGradeMapping = partVo.getPartGradeMapping();
	}

	public boolean isPrintInvoiceNumberOnPDF() {
		return printInvoiceNumberOnPDF;
	}

	public void setPrintInvoiceNumberOnPDF(boolean printInvoiceNumberOnPDF) {
		this.printInvoiceNumberOnPDF = printInvoiceNumberOnPDF;
	}

	public InvoiceHeaderVO getInvoiceToBeSearched() {
		return invoiceToBeSearched;
	}

	public void setInvoiceToBeSearched(InvoiceHeaderVO invoiceToBeSearched) {
		this.invoiceToBeSearched = invoiceToBeSearched;
	}
}