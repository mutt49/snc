package org.demo.spinncast.bean;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.hibernate.InvoiceHeaderHBC;
import org.demo.spinncast.hibernate.InvoiceLineItemHBC;
import org.demo.spinncast.hibernate.LineItemHBC;
import org.demo.spinncast.hibernate.PartMasterHBC;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.demo.spinncast.vo.InvoiceHeaderVO;
import org.demo.spinncast.vo.InvoiceLineItemVO;
import org.demo.spinncast.vo.LineItemVO;
import org.demo.spinncast.vo.PartMasterVO;
import org.demo.spinncast.vo.PurchaseOrderVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;

@ManagedBean(name = "InvoiceHeaderBean")
@SessionScoped
public class InvoiceHeaderBean {

	private InvoiceHeaderVO selectedInvHdrVo;
	private InvoiceLineItemVO invLineItem;
	private List<InvoiceLineItemVO> invLineItemList;
	private PartMasterVO partVo;
	private List<InvoiceHeaderVO> searchList;
	private Boolean editFlag = false;
	private Integer selectedInvId;

	// Data read from properties file
	private String vendorCode;
	private String exciseCode;
	private String regCertNo;
	private String plaNo;
	private String rangeAddress;
	private String division;

	private boolean headerSaved = false;
	private String retPage;
	private Boolean printEntirePage;

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

	public void setSelectedInvId(Integer selectedInvId) {
		this.selectedInvId = selectedInvId;
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
		readProperties();
	}

	public String reset() {
		getSearchList().clear();
		selectedInvId = null;
		return "InvoiceHeaderSearch";
	}

	@SuppressWarnings("unchecked")
	public String search() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from InvoiceHeaderHBC as m where (m.invId= :invId and :invId !=0 ) or (:invId = 0)  order by m.invId");
		hibernateQuery.setInteger("invId", selectedInvId);
		java.util.List<InvoiceHeaderHBC> results = hibernateQuery.list();
		searchList = new ArrayList<InvoiceHeaderVO>();
		for (int i = 0; i < results.size(); i++) {
			InvoiceHeaderVO tempInvHdr = new InvoiceHeaderVO();
			tempInvHdr.setCustomerId(results.get(i).getCustomerId());
			tempInvHdr.setCustDetails(populateCustomerDetails(results.get(i)
					.getCustomerId()));
			tempInvHdr.setInvId(results.get(i).getInvId());
			tempInvHdr.setInvDate(results.get(i).getInvDate());
			tempInvHdr.setCustDetails(populateCustomerDetails(results.get(i)
					.getCustomerId()));
			tempInvHdr.setDelivaryChallanNo(results.get(i)
					.getDelivaryChallanNo());
			tempInvHdr.setDelivaryChallanDate(results.get(i)
					.getDelivaryChallanDate());
			tempInvHdr.setPurchaseOrderId(results.get(i).getPurchaseOrderId());
			tempInvHdr.setPurchaseOrderDate(results.get(i)
					.getPurchaseOrderDate());
			tempInvHdr.setModeOfTransport(results.get(i).getModeOfTransport());
			tempInvHdr.setVehicleNo(results.get(i).getVehicleNo());
			tempInvHdr.setNetTotalAmount(results.get(i).getNetTotalAmount());
			tempInvHdr
					.setFreightInsurance(results.get(i).getFreightInsurance());
			tempInvHdr.setGrandTotal(results.get(i).getGrandTotal());
			tempInvHdr.setTcNo(results.get(i).getTcNo());
			tempInvHdr.setLrNo(results.get(i).getLrNo());
			tempInvHdr.setIrNo(results.get(i).getIrNo());
			tempInvHdr.setPaymentTerms(results.get(i).getPaymentTerms());
			tempInvHdr.setPkgFrwdChg(results.get(i).getPkgFrwdChg());
			tempInvHdr.setInvIssueDate(results.get(i).getInvIssueDate());
			tempInvHdr.setRemovalDate(results.get(i).getRemovalDate());
			tempInvHdr.setLiAmountTotal(results.get(i).getLiAmountTotal());
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
		selectedInvHdrVo.setCustomerId(selectedInvHdrVo.getCustDetails()
				.getCustomer_id());
		Transaction trans = session.beginTransaction();
		InvoiceHeaderHBC invHeaderHBC = new InvoiceHeaderHBC(selectedInvHdrVo);
		session.saveOrUpdate(invHeaderHBC);
		selectedInvId = invHeaderHBC.getInvId();
		trans.commit();
		session.close();
		selectedInvHdrVo.setInvId(selectedInvId);
		headerSaved = true;
		editFlag = true;
	}

	public void getCustomerData(ValueChangeEvent event) {
		String custName = (String) event.getNewValue();
		CustomerVO tempCustVo = populateCustomerDetails(custName);
		selectedInvHdrVo.setCustDetails(tempCustVo);
	}

	public void getCustomerDataUsingVendorCode(ValueChangeEvent event) {
		String vendorCode = (String) event.getNewValue();
		CustomerVO tempCustVo = populateCustomerDetailsUsingVendorCode(vendorCode);
		selectedInvHdrVo.setCustDetails(tempCustVo);
	}

	// Move this function to session Level - JK

	private CustomerVO populateCustomerDetailsUsingVendorCode(String vendorCode2) {
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

	public void readProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		// InputStream configStream =
		// ctx.getResourceAsStream("/WEB-INF/config.properties");
		try {
			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			vendorCode = prop.getProperty("vendorCode");
			exciseCode = prop.getProperty("exciseCode");
			regCertNo = prop.getProperty("regCertNo");
			plaNo = prop.getProperty("PLANO");
			rangeAddress = prop.getProperty("rangeAddress");
			division = prop.getProperty("division");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public PartMasterVO populatePartMaster(String partName) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where part_name =:part_name");
		hibernateQuery.setString("part_name", partName);
		java.util.List<PartMasterHBC> results = hibernateQuery.list();

		PartMasterVO tempPartVo = new PartMasterVO();
		if (results.size() > 0) {
			tempPartVo.setCastWeight(results.get(0).getCastWeight());
			tempPartVo.setDrgNo(results.get(0).getDrgNo());
			tempPartVo.setGrade(results.get(0).getGrade());
			tempPartVo.setPartId(results.get(0).getPartId());
			tempPartVo.setPartName(results.get(0).getPartName());
			tempPartVo.setPartRate(results.get(0).getPartRate());
			tempPartVo.setPartUom(results.get(0).getPartUom());
			tempPartVo.setPmSize(results.get(0).getPmSize());
			tempPartVo.setProofMachineWeight(results.get(0)
					.getProofMachineWeight());
			tempPartVo.setQuantity(results.get(0).getQuantity());
		}
		session.close();
		return tempPartVo;
	}

	public void getPartDetails(String partName) {
		partVo = populatePartMaster(partName);
	}

	public String addLineItem() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		getPartDetails(partVo.getPartName());
		invLineItem.setPartId(partVo.getPartId());
		invLineItem.setInvId(selectedInvId);

		if (invLineItem.getUnit().equalsIgnoreCase("KG")) {
			invLineItem.setAmount(invLineItem.getQuantityKgs()
					* invLineItem.getRate());
		} else {
			invLineItem.setAmount(invLineItem.getQuantityNo()
					* invLineItem.getRate());
		}

		InvoiceLineItemHBC invLineItemHBC = new InvoiceLineItemHBC(invLineItem);
		session.saveOrUpdate(invLineItemHBC);
		trans.commit();
		session.close();
		amountAggregation(invLineItem);
		invLineItem = new InvoiceLineItemVO();
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

			invLineItemList.add(tempInvLI);
		}
		System.out.println(getSearchList().size());
		session.close();
		return "InvoiceHeaderAdd";
	}

	public String viewInvoice() {
		selectedInvId = selectedInvHdrVo.getInvId();
		headerSaved = true;
		editFlag = true;
		search();
		selectedInvHdrVo = searchList.get(0);
		selectedInvId = selectedInvHdrVo.getInvId();
		// selectedInvHdrVo.setNetTotalAmount(selectedInvHdrVo.getLiAmountTotal()
		// + selectedInvHdrVo.getFreightInsurance());
		retPage = "invoiceHeaderSearch";
		return getLineItemsForInvId();
	}

	public String clearHeader() {
		selectedInvHdrVo = new InvoiceHeaderVO();
		selectedInvId = null;
		editFlag = false;
		headerSaved = false;
		searchList = new ArrayList<InvoiceHeaderVO>();
		invLineItemList = new ArrayList<InvoiceLineItemVO>();
		if (retPage.equalsIgnoreCase("InvoiceHeaderAdd")) {
			return "InvoiceHeaderAdd";
		} else {
			return "invoiceHeaderSearch";
		}
	}

	public void amountAggregation(InvoiceLineItemVO invLine) {
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
		session.close();
		session = cpool.getSession();
		trans = session.beginTransaction();
		hql = "update InvoiceHeaderHBC set net_total_amount = pkg_frwd_chg + li_amount_total where invId = :inv_id ";
		query = session.createQuery(hql);
		query.setInteger("inv_id", invLineItem.getInvId());
		result = query.executeUpdate();
		trans.commit();
		session.close();

		session = cpool.getSession();
		trans = session.beginTransaction();
		hql = "update InvoiceHeaderHBC set grand_total = net_total_amount + (net_total_amount * (12/100)) + freight_insurance where invId = :inv_id ";
		query = session.createQuery(hql);
		query.setInteger("inv_id", invLineItem.getInvId());
		result = query.executeUpdate();
		trans.commit();
		session.close();

		System.out.println("No of rows updated" + result);
		selectedInvId = selectedInvHdrVo.getInvId();
		// remove this call
		viewInvoice();
	}

	public void taxCalculation() {

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

	public ByteArrayOutputStream createPDF() throws IOException,
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

		// Creating Document
		try {
			document = new PDDocument();
			// Creating Pages
			for (int i = 0; i < 1; i++) {

				page = new PDPage(PDPage.PAGE_SIZE_A4);

				// Adding page to document
				document.addPage(page);

				// Adding FONT to document
				font = PDType1Font.HELVETICA;
				fontBold = PDType1Font.HELVETICA_BOLD;
				// Next we start a new content stream which will "hold" the to
				// be
				// created content.
				contentStream = new PDPageContentStream(document, page);

				// Let's define the content stream
				contentStream.setStrokingColor(0, 0, 0);
				boolean printEntirePage = false;
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
					contentStream.drawString("7101031799");
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
					contentStream.drawString("K II - 03");
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
					contentStream.drawString("PUNE VI - 01");
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
					contentStream.drawString("Non Ferrous Casting");
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
				contentStream.drawString("TRF HDNG NO");			// Data
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
				contentStream.drawString("ECMTPNT");				// Data
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
					contentStream.drawString("AAKFS 2087H ST 001");		// Data
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
					contentStream.drawString("AAKFS2087H");			// Data
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
				contentStream.drawString("VNDR CD");					// Data
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.moveTextPositionByAmount(xOffset + 12, yOffset
						+ height - 207);
				contentStream.drawString("CSTMR NAME");					// Data
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.moveTextPositionByAmount(xOffset + 12, yOffset
						+ height - 220);
				contentStream.drawString("CSTMR ADDR");					// Data
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.moveTextPositionByAmount(xOffset + 12, yOffset
						+ height - 233);
				contentStream.drawString("");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.moveTextPositionByAmount(xOffset + 12, yOffset
						+ height - 246);
				contentStream.drawString("");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.moveTextPositionByAmount(xOffset + 12, yOffset
						+ height - 259);
				contentStream.drawString("");
				contentStream.endText();
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
				contentStream.drawString("ECC NMR");					// Data
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
				contentStream.drawString("OCTR");						// Data
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
				contentStream.drawString("CST NOO NOO");				// Data
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
				contentStream.drawString("VATN ON O");					// Data
				contentStream.endText();

				// Second Pane - Third Section
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 194);
					contentStream.drawString("Delivery Address & Name :");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 207);
					contentStream.drawString("CSTMR NAME");				// Data
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 220);
					contentStream.drawString("CSTMR ADDR");				// Data
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 233);
					contentStream.drawString("");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 246);
					contentStream.drawString("");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 198,
							yOffset + height - 259);
					contentStream.drawString("");
					contentStream.endText();

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
				contentStream.drawString("12322");					// Data
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(xOffset + 404 - 20,
							yOffset + height - 207);
					contentStream.drawString("Date :");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(xOffset + 464 - 50,
						yOffset + height - 207);
				contentStream.drawString((new Date()).toGMTString());		// Data
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
				contentStream.drawString("1111111");				// Data
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
				contentStream.drawString("222222222222");			// Data
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
				contentStream.drawString("TRK");					// Data
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
				contentStream.drawString("QWEQ");					// Data
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
				contentStream.drawString("LR NOQWE");			// Data
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
				contentStream.drawString("TERMS OF PAMNT");		// Data
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
				contentStream
						.drawString("OIPQWEKJKJSAD QWIEQWPO QWIQWOJEQOQWE");		// Data
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 9);
					contentStream.moveTextPositionByAmount(width - 200, yOffset
							+ height - 334);
					contentStream.drawString("Date : ");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(width - 170, yOffset
						+ height - 334);
				contentStream.drawString((new Date()).toGMTString());				// Data
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

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 56,
							yOffset + height - 354);
					contentStream
							.drawString("Description and specification of goods");
					contentStream.endText();
				}

				// Line Items	// Data
				for (int lineItemIndex = 0; lineItemIndex < 5; lineItemIndex++) {
					int lineItemPadding = -40;
					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 395 + (lineItemIndex * lineItemPadding));
					contentStream.drawString("1234");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 40,
							yOffset + height - 395
									+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("Part Details");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 40,
							yOffset + height - 407
									+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("Part Details");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 40,
							yOffset + height - 419
									+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("Part Details");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(xOffset + 253 + 9,
							yOffset + height - 395
									+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("12");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 253 + 9 + 45, yOffset + height - 395
									+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("12");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 253 + 9 + 90, yOffset + height - 395
									+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("12");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 9);
					contentStream.moveTextPositionByAmount(
							xOffset + 253 + 9 + 130, yOffset + height - 395
									+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("12");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width - 60
							- 8, yOffset + height - 395
							+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("17,78,789.88");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 115 - 18, yOffset + height - 395
							+ (lineItemIndex * lineItemPadding));
					contentStream.drawString("17,78,789.88");
					contentStream.endText();
				}

				// Line Items Over.
				contentStream.beginText();
				contentStream.setFont(fontBold, 9);
				contentStream.moveTextPositionByAmount(xOffset + 64, yOffset
						+ height - 365);
				contentStream.drawString("Proof M/C Non Ferrous Casting");		// Data
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
							.drawString("Total Central Excise Duy Paid (in words): ");
					contentStream.endText();
				}
				contentStream.beginText();																
				contentStream.setFont(font, 8);
				contentStream.moveTextPositionByAmount(xOffset + 160, yOffset
						+ height - 612);
				contentStream
						.drawString("Seventy Thousand Seven Hundred and Seventy Seven Only");	// Data
				contentStream.endText();
				contentStream.beginText();
				contentStream.setFont(font, 8);
				contentStream.moveTextPositionByAmount(xOffset + 160, yOffset
						+ height - 622);
				contentStream.drawString("Seven Lakhs Seven Thousand");
				contentStream.endText();
				if (printEntirePage) {
					contentStream.beginText();
					contentStream.setFont(font, 8);
					contentStream.moveTextPositionByAmount(xOffset + 12,
							yOffset + height - 634);
					contentStream.drawString("Total Amount (in words): ");
					contentStream.endText();
				}
				contentStream.beginText();
				contentStream.setFont(font, 8);
				contentStream.moveTextPositionByAmount(xOffset + 160, yOffset
						+ height - 634);
				contentStream
						.drawString("Seventy Thousand Seven Hundred and Seventy Seven Only");	// Data
				contentStream.endText();
				contentStream.beginText();
				contentStream.setFont(font, 8);
				contentStream.moveTextPositionByAmount(xOffset + 160, yOffset
						+ height - 644);
				contentStream.drawString("Seven Lakhs Seven Thousand");
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

				contentStream.beginText();
				contentStream.setFont(fontBold, 8);
				contentStream.moveTextPositionByAmount(xOffset + 155, yOffset
						+ height - 661);
				contentStream.drawString((new Date()).toGMTString());				// Data
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 8);
				contentStream.moveTextPositionByAmount(xOffset + 155, yOffset
						+ height - 672);
				contentStream.drawString((new Date()).toGMTString());			// Data
				contentStream.endText();

				if (printEntirePage) {
					// Bottom right labels
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 628);
					contentStream.drawString("Pkg Chg.");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 650);
					contentStream.drawString("Assessable /");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 658);
					contentStream.drawString("Tariff Value");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 680);
					contentStream.drawString("B.E.D.         %");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 702);
					contentStream.drawString("Ed. Cess     %");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 724);
					contentStream.drawString("SHS Cess    %");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 746);
					contentStream.drawString("Sub Total");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 768);
					contentStream.drawString("VAT/CST     %");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 8);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 121 - 11, yOffset + height - 788);
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

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 630);
				contentStream.drawString("17,78,789.88");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 655);
				contentStream.drawString("17,78,789.88");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 680);
				contentStream.drawString("17,78,789.88");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 703);
				contentStream.drawString("17,78,789.88");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 725);
				contentStream.drawString("17,78,789.88");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 747);
				contentStream.drawString("17,78,789.88");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 768);
				contentStream.drawString("17,78,789.88");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 788);
				contentStream.drawString("17,78,789.88");
				contentStream.endText();

				contentStream.beginText();
				contentStream.setFont(fontBold, 10);
				contentStream.moveTextPositionByAmount(
						xOffset + width - 60 - 8, yOffset + height - 810);
				contentStream.drawString("17,78,789.88");
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
					contentStream.drawString("Excise duty payable.");
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

	public void print() {
		setPrintEntirePage(true);
		try {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			output = createPDF();
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc
					.getExternalContext().getResponse();

			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"invoice.pdf\"");
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
		setPrintEntirePage(false);
		try {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			output = createPDF();
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc
					.getExternalContext().getResponse();

			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"invoice.pdf\"");
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

}