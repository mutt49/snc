package org.demo.spinncast.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.handler.CustomerHandler;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.hibernate.GradeCompositionHBC;
import org.demo.spinncast.hibernate.GradeMasterHBC;
import org.demo.spinncast.hibernate.TestCertificateActualValuesHBC;
import org.demo.spinncast.hibernate.TestCertificateHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.demo.spinncast.vo.GradeCompositionVO;
import org.demo.spinncast.vo.GradeMasterVO;
import org.demo.spinncast.vo.TestCertificateActualValuesVO;
import org.demo.spinncast.vo.TestCertificateVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "TestCertificateBean")
@SessionScoped
public class TestCertificateBean {
	private TestCertificateVO searchTestCertificateVO = new TestCertificateVO();
	private TestCertificateVO selectedTestCertificateVO = new TestCertificateVO();
	private ArrayList<TestCertificateVO> searchTCVOList;
	private Boolean editFlag = false;
	private int selectedTestCaseNo;
	private List<TestCertificateActualValuesVO> actualValuesChem = new ArrayList<TestCertificateActualValuesVO>();
	private List<TestCertificateActualValuesVO> actualValuesMech = new ArrayList<TestCertificateActualValuesVO>();
	private boolean printMechanicalProperties = true;

	public List<TestCertificateActualValuesVO> getActualValuesChem() {
		return actualValuesChem;
	} 

	public void setActualValuesChem(
			List<TestCertificateActualValuesVO> actualValuesChem) {
		this.actualValuesChem = actualValuesChem;
	}

	public List<TestCertificateActualValuesVO> getActualValuesMech() {
		return actualValuesMech;
	}

	public void setActualValuesMech(
			List<TestCertificateActualValuesVO> actualValuesMech) {
		this.actualValuesMech = actualValuesMech;
	}

	public TestCertificateVO getSearchTestCertificateVO() {
		return searchTestCertificateVO;
	}

	public void setSearchTestCertificateVO(
			TestCertificateVO searchTestCertificateVO) {
		this.searchTestCertificateVO = searchTestCertificateVO;
	}

	public TestCertificateVO getSelectedTestCertificateVO() {
		return selectedTestCertificateVO;
	}

	public void setSelectedTestCertificateVO(
			TestCertificateVO selectedTestCertificateVO) {
		this.selectedTestCertificateVO = selectedTestCertificateVO;
	}

	public ArrayList<TestCertificateVO> getSearchTCVOList() {
		return searchTCVOList;
	}

	public void setSearchTCVOList(ArrayList<TestCertificateVO> searchTCVOList) {
		this.searchTCVOList = searchTCVOList;
	}

	public Boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(Boolean editFlag) {
		this.editFlag = editFlag;
	}

	public int getSelectedTestCaseNo() {
		return selectedTestCaseNo;
	}

	public void setSelectedTestCaseNo(int selectedTestCaseNo) {
		this.selectedTestCaseNo = selectedTestCaseNo;
	}

	public String search() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		try {
			selectedTestCaseNo = searchTestCertificateVO.getTcNo();
		} catch (NumberFormatException e) {
			selectedTestCaseNo = 0;
		}

		Session session = cpool.getSession();
		StringBuilder query = new StringBuilder(
				"from TestCertificateHBC as m where ((m.tcNo= :tcNo and :tcNo !=0 ) or (:tcNo = 0) ");

		if (searchTestCertificateVO.getPoNo() != null
				&& !searchTestCertificateVO.getPoNo().equals("")) {
			query.append(" and m.poNo = :poNo ");
		}
		if (searchTestCertificateVO.getHeatBatchNo() != null
				&& !searchTestCertificateVO.getHeatBatchNo().equals("")) {
			query.append(" and m.heatBatchNo = :hcNo ");
		}
		if (searchTestCertificateVO.getChallanNo() != null
				&& !searchTestCertificateVO.getChallanNo().equals("")) {
			query.append(" and m.challanNo = :chNo ");
		}

		query.append(") order by m.tcId");

		Query hibernateQuery = session.createQuery(query.toString());
		hibernateQuery.setInteger("tcNo", searchTestCertificateVO.getTcNo());

		if (searchTestCertificateVO.getPoNo() != null
				&& !searchTestCertificateVO.getPoNo().equals("")) {
			hibernateQuery.setString("poNo", searchTestCertificateVO.getPoNo());
		}
		if (searchTestCertificateVO.getHeatBatchNo() != null
				&& !searchTestCertificateVO.getHeatBatchNo().equals("")) {
			hibernateQuery.setString("hcNo",
					searchTestCertificateVO.getHeatBatchNo());
		}
		if (searchTestCertificateVO.getChallanNo() != null
				&& !searchTestCertificateVO.getChallanNo().equals("")) {
			hibernateQuery.setString("chNo",
					searchTestCertificateVO.getChallanNo());
		}

		java.util.List<TestCertificateHBC> results = hibernateQuery.list();
		searchTCVOList = new ArrayList<TestCertificateVO>();
		for (int i = 0; i < results.size(); i++) {
			TestCertificateVO tempTc = new TestCertificateVO(results.get(i));
			searchTCVOList.add(tempTc);
		}
		getActualValues();
		session.close();
		return "TestCertificateSearch";
	}

	public void getActualValues() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		for (TestCertificateVO tc : searchTCVOList) {
			Session session = cpool.getSession();
			StringBuilder query = new StringBuilder(
					"from TestCertificateActualValuesHBC as m where m.tcId= :tcId ");
			Query hibernateQuery = session.createQuery(query.toString());
			hibernateQuery.setInteger("tcId", tc.getTcId());
			java.util.List<TestCertificateActualValuesHBC> results = hibernateQuery
					.list();
			for (int i = 0; i < results.size(); i++) {
				TestCertificateActualValuesVO tempTc = new TestCertificateActualValuesVO(
						results.get(i));
				tc.getActualValues().add(tempTc);
			}
		}
	}

	// Add reset method

	public String reset() {
		searchTestCertificateVO = new TestCertificateVO();
		selectedTestCertificateVO = new TestCertificateVO();
		searchTCVOList = new ArrayList<TestCertificateVO>();
		editFlag = false;
		selectedTestCaseNo = 0;
		actualValuesChem = new ArrayList<TestCertificateActualValuesVO>();
		actualValuesMech = new ArrayList<TestCertificateActualValuesVO>();
		return "TestCertificateSearch";
	}

	public String addNew() {
		reset();
		return "TestCertificateAdd.jsf";
	}

	public String add() {
		try {
			ConnectionPool cpool = ConnectionPool.getInstance();
			Session session = cpool.getSession();
			Transaction trans = session.beginTransaction();
			TestCertificateHBC tcHbc = new TestCertificateHBC(
					selectedTestCertificateVO);
			session.saveOrUpdate(tcHbc);
			trans.commit();
			session.close();
			selectedTestCertificateVO.setTcId(tcHbc.getTcId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		addActualValues();
		selectedTestCertificateVO = new TestCertificateVO();
		actualValuesChem = new ArrayList<TestCertificateActualValuesVO>();
		actualValuesMech = new ArrayList<TestCertificateActualValuesVO>();
		selectedTestCaseNo = 0;
		return search();
	}

	public void addActualValues() {
		try {
			selectedTestCertificateVO
					.setActualValues(new ArrayList<TestCertificateActualValuesVO>());
			for (TestCertificateActualValuesVO tempVO : actualValuesChem) {
				tempVO.setTcId(selectedTestCertificateVO.getTcId());
				selectedTestCertificateVO.getActualValues().add(tempVO);
			}
			for (TestCertificateActualValuesVO tempVO : actualValuesMech) {
				tempVO.setTcId(selectedTestCertificateVO.getTcId());
				selectedTestCertificateVO.getActualValues().add(tempVO);
			}
			ConnectionPool cpool = ConnectionPool.getInstance();
			Session session = cpool.getSession();
			Transaction trans = session.beginTransaction();
			for (TestCertificateActualValuesVO tempData : selectedTestCertificateVO
					.getActualValues()) {
				TestCertificateActualValuesHBC tcActualHbc = new TestCertificateActualValuesHBC(
						tempData);
				session.saveOrUpdate(tcActualHbc);
			}
			trans.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editActualValues() {
		try {
			selectedTestCertificateVO
					.setActualValues(new ArrayList<TestCertificateActualValuesVO>());
			for (TestCertificateActualValuesVO tempVO : actualValuesChem) {
				tempVO.setTcId(selectedTestCertificateVO.getTcId());
				selectedTestCertificateVO.getActualValues().add(tempVO);
			}
			for (TestCertificateActualValuesVO tempVO : actualValuesMech) {
				tempVO.setTcId(selectedTestCertificateVO.getTcId());
				selectedTestCertificateVO.getActualValues().add(tempVO);
			}
			ConnectionPool cpool = ConnectionPool.getInstance();
			Session session = cpool.getSession();
			Transaction trans = session.beginTransaction();
			for (TestCertificateActualValuesVO tempData : selectedTestCertificateVO
					.getActualValues()) {
				TestCertificateActualValuesHBC tcActualHbc = new TestCertificateActualValuesHBC(
						tempData);
				session.update(tcActualHbc);
			}
			trans.commit();
			session.close();
			selectedTestCertificateVO
					.setActualValues(new ArrayList<TestCertificateActualValuesVO>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchGradeDetails(ValueChangeEvent event) {
		String gradeName = (String) event.getNewValue();
		selectedTestCertificateVO
				.setActualValues(new ArrayList<TestCertificateActualValuesVO>());
		actualValuesChem = new ArrayList<TestCertificateActualValuesVO>();
		actualValuesMech = new ArrayList<TestCertificateActualValuesVO>();
		int gradeId = getGrdeIdbyName(gradeName);
		if (gradeName.isEmpty()) {
			return;
		}
		// Move this method to handler
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeCompositionHBC as g where g.gradeId = :gradeId order by g.gradeCompositionId");
		hibernateQuery.setInteger("gradeId", gradeId);

		java.util.List<GradeCompositionHBC> results = hibernateQuery.list();

		for (int i = 0; i < results.size(); i++) {
			GradeCompositionVO tempObj = new GradeCompositionVO();
			tempObj.setIngrediantName(results.get(i).getIngrediantName());
			tempObj.setIngrediantType(results.get(i).getIngrediantType());
			tempObj.setMaxValue(results.get(i).getMaxValue());
			tempObj.setMinValue(results.get(i).getMinValue());
			if (results.get(i).getIngrediantType().equalsIgnoreCase("C")) {
				actualValuesChem
						.add(new TestCertificateActualValuesVO(tempObj));
			} else {
				actualValuesMech
						.add(new TestCertificateActualValuesVO(tempObj));
			}
			selectedTestCertificateVO.getActualValues().add(
					new TestCertificateActualValuesVO(tempObj));
		}

		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
	}

	// Move this method to handler
	public int getGrdeIdbyName(String gradeName) {
		GradeMasterVO tempObj = new GradeMasterVO();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as g where g.gradeName like :gradeName order by g.gradeName");
		hibernateQuery.setString("gradeName", gradeName);

		java.util.List<GradeMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			tempObj.setGradeId(results.get(i).getGradeId());
			tempObj.setGradeName(results.get(i).getGradeName());
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return tempObj.getGradeId();
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
		selectedTestCertificateVO.setVendorAddress(tempCustVo
				.getCustomer_address());
		selectedTestCertificateVO.setVendorRealName(tempCustVo
				.getCustomer_name());
	}

	public void getCustomerData(ValueChangeEvent event) {
		String custName = (String) event.getNewValue();
		if (custName.isEmpty()) {
			return;
		}
		CustomerVO tempCustVo = populateCustomerDetails(custName);
		if (tempCustVo != null) {
			selectedTestCertificateVO.setVendorAddress(tempCustVo.getCustomer_address());
			selectedTestCertificateVO.setVendorRealName(tempCustVo.getCustomer_name());
			selectedTestCertificateVO.setVendorName (tempCustVo.getVendor_code());
		}
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

	public List<String> gradeAutoComplete(String prefix) {
		List<String> result = new ArrayList<String>();

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as m where gradeName like '%"
						+ prefix + "%'");
		java.util.List<GradeMasterHBC> results = hibernateQuery.list();

		for (int i = 0; i < results.size(); i++) {
			result.add(results.get(i).getGradeName());
		}
		session.close();
		return result;
	}

	public String viewTestCertificate() {
		actualValuesChem = new ArrayList<TestCertificateActualValuesVO>();
		actualValuesMech = new ArrayList<TestCertificateActualValuesVO>();
		for (TestCertificateActualValuesVO testVo : selectedTestCertificateVO
				.getActualValues()) {
			if (testVo.getPropType().equalsIgnoreCase("C")) {
				actualValuesChem.add(testVo);
			} else {
				actualValuesMech.add(testVo);
			}
		}
		// editFlag = true;
		return "TestCertificateAdd";
	}

	public String save() {
		try {
			ConnectionPool cpool = ConnectionPool.getInstance();
			Session session = cpool.getSession();
			Transaction trans = session.beginTransaction();
			TestCertificateHBC tcHbc = new TestCertificateHBC(
					selectedTestCertificateVO);
			session.update(tcHbc);
			trans.commit();
			session.close();
			selectedTestCertificateVO.setTcId(tcHbc.getTcId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		editActualValues();
		selectedTestCertificateVO = new TestCertificateVO();
		actualValuesChem = new ArrayList<TestCertificateActualValuesVO>();
		actualValuesMech = new ArrayList<TestCertificateActualValuesVO>();
		selectedTestCaseNo = 0;

		return search();
	}

	public void print() {

		try {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			output = createPDF(false);
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc
					.getExternalContext().getResponse();

			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"TestCertificate.pdf\"");
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

	public ByteArrayOutputStream createPDF(boolean bill) throws IOException,
			COSVisitorException {

		boolean flag = true;

		String bigString = "UIYQWIUQWYE ( QWEIUQWE + QWIEWQ ) QWIEQWE";
		// bigString = bigString.replaceAll("\\(", "\\\\(").replaceAll("\\)",
		// "\\\\)").replaceAll ("\\+","\\\\+");

		// C:\Users\Aniket\Desktop\cast\One.png
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
				boolean printEntirePage = true;
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

					// Top Image
					InputStream in = new FileInputStream(new File(
							"tcHeader.jpg"));
					PDJpeg img = new PDJpeg(document, in);
					contentStream.drawXObject(img, xOffset + 7, yOffset
							+ height - 50, 225, 40);

					yOffset = yOffset + 25;
					// Top Section
					contentStream.addLine(xOffset + 2, yOffset + height - 80,
							xOffset + width - 2, yOffset + height - 80);

					contentStream.addLine(xOffset + 250, yOffset + height - 2
							- 25, xOffset + 250, yOffset + height - 80);

					contentStream.beginText();
					contentStream.setFont(fontBold, 20);
					contentStream.moveTextPositionByAmount(xOffset + 315,
							yOffset + height - 50);
					contentStream.drawString("TEST CERTIFICATE");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 260,
							yOffset + height - 70);
					contentStream.drawString("T.C. No.:");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + 310,
							yOffset + height - 70);
					contentStream.drawString(Integer
							.toString(selectedTestCertificateVO.getTcNo()));
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 400,
							yOffset + height - 70);
					contentStream.drawString("Date:");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + 430,
							yOffset + height - 70);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String dateInString = sdf.format(selectedTestCertificateVO
							.getTcDate());

					contentStream.drawString(dateInString);
					contentStream.endText();

					// Second Section
					contentStream.addLine(xOffset + 2, yOffset + height - 110,
							xOffset + width - 2, yOffset + height - 110);
					contentStream.beginText();
					contentStream.setFont(fontBold, 13);
					contentStream.moveTextPositionByAmount(xOffset + 5, yOffset
							+ height - 100);
					contentStream
							.drawString("SPECIALISTS IN MFG. NON-FERROUS CENTRIFUGAL CASTINGS TO UNIT OF 2000 KGS.");
					contentStream.endText();

					// Third Section
					contentStream.addLine(xOffset + 2, yOffset + height - 147,
							xOffset + width - 2, yOffset + height - 147);
					contentStream.beginText();
					contentStream.setFont(fontBold, 11);
					contentStream.moveTextPositionByAmount(xOffset + 35,
							yOffset + height - 125);
					contentStream.drawString("Works: ");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + 75,
							yOffset + height - 125);
					contentStream
							.drawString("Plot No. 12, Gat No. 281, Ghotawade Phata, Village Kasar Amboli, Taluka Mulshi, Dist. Pune - 412108");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + 80,
							yOffset + height - 137);
					contentStream
							.drawString("Phone: (020) 6674 8984, 6674 8985 Fax: (020) 6674 8986 Email: spinncast@hotmail.com");
					contentStream.endText();

					// Fourth Section
					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 35,
							yOffset + height - 180);
					contentStream.drawString("Heat Batch No.:");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + 112,
							yOffset + height - 180);
					contentStream.drawString(selectedTestCertificateVO
							.getHeatBatchNo());
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 187, yOffset + height - 180);
					contentStream.drawString("Date:");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 150, yOffset + height - 180);
					dateInString = sdf.format(selectedTestCertificateVO
							.getHeatBatchDate());
					contentStream.drawString(dateInString);
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 35,
							yOffset + height - 210);
					contentStream.drawString("To,");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + 35,
							yOffset + height - 225);
					contentStream.drawString(selectedTestCertificateVO
							.getVendorRealName());
					contentStream.endText();

					int addressLineIndex = 0;
					int addressLinePadding = -15;
					System.out.println(selectedTestCertificateVO
							.getVendorAddress());
					for (String addressLine : getFormattedAddress(
							selectedTestCertificateVO.getVendorAddress(), 80)) {
						contentStream.beginText();
						contentStream.setFont(font, 10);
						contentStream
								.moveTextPositionByAmount(
										xOffset + 35,
										yOffset
												+ height
												- 240
												+ (addressLineIndex * addressLinePadding));
						contentStream.drawString(addressLine);
						contentStream.endText();
						addressLineIndex++;
					}
					//
					// contentStream.beginText();
					// contentStream.setFont(fontBold, 10);
					// contentStream.moveTextPositionByAmount(xOffset + 35,
					// yOffset + height - 240);
					// contentStream.drawString("To,");
					// contentStream.endText();
					//
					// contentStream.beginText();
					// contentStream.setFont(fontBold, 10);
					// contentStream.moveTextPositionByAmount(xOffset + 35,
					// yOffset + height - 255);
					// contentStream.drawString("To,");
					// contentStream.endText();
					//
					// contentStream.beginText();
					// contentStream.setFont(fontBold, 10);
					// contentStream.moveTextPositionByAmount(xOffset + 35,
					// yOffset + height - 270);
					// contentStream.drawString("To,");
					// contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 35,
							yOffset + height - 300);
					contentStream.drawString("Your Purchase Order No.: ");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + 160,
							yOffset + height - 300);
					contentStream.drawString(selectedTestCertificateVO
							.getPoNo());
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 187, yOffset + height - 300);
					contentStream.drawString("Date:");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 150, yOffset + height - 300);
					dateInString = sdf.format(selectedTestCertificateVO
							.getPoDate());
					contentStream.drawString(dateInString);
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 35,
							yOffset + height - 330);
					contentStream.drawString("Our Challan No	.: ");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + 160,
							yOffset + height - 330);
					contentStream.drawString(selectedTestCertificateVO
							.getChallanNo());
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 187, yOffset + height - 330);
					contentStream.drawString("Date:");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 150, yOffset + height - 330);
					dateInString = sdf.format(selectedTestCertificateVO
							.getChallanDate());
					contentStream.drawString(dateInString);
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + 35,
							yOffset + height - 360);
					contentStream.drawString("Description:");
					contentStream.endText();

					List<String> partDetails = getFormattedAddress(
							selectedTestCertificateVO.getDesc(), 33);
					int partLineIndex = 0;
					int partLinePadding = -15;
					for (String str : partDetails) {

						contentStream.beginText();
						contentStream.setFont(font, 10);
						contentStream.moveTextPositionByAmount(xOffset + 160,
								yOffset + height - 360
										+ ((partLineIndex) * partLinePadding));
						contentStream.drawString(str);
						contentStream.endText();
						partLineIndex++;
					}

					// TABLES
					if (isPrintMechanicalProperties()) {
						contentStream.beginText();
						contentStream.setFont(fontBold, 12);
						contentStream.moveTextPositionByAmount(xOffset + 70,
								yOffset + height - 450);
						contentStream.drawString("CHEMICAL COMPOSITION");
						contentStream.endText();

						contentStream.beginText();
						contentStream.setFont(fontBold, 12);
						contentStream.moveTextPositionByAmount(xOffset + width
								- 250, yOffset + height - 450);
						contentStream.drawString("MECHANICAL PROPERTIES");
						contentStream.endText();


						contentStream.beginText();
						contentStream.setFont(fontBold, 10);
						contentStream.moveTextPositionByAmount(xOffset + 30,
								yOffset + height - 465);
						contentStream.drawString("Grade .: ");
						contentStream.endText();

						contentStream.beginText();
						contentStream.setFont(font, 10);
						contentStream.moveTextPositionByAmount(xOffset + 80,
								yOffset + height - 465);
						contentStream.drawString(selectedTestCertificateVO
								.getGrade());
						contentStream.endText();
					} else {
						contentStream.beginText();
						contentStream.setFont(fontBold, 12);
						contentStream.moveTextPositionByAmount(xOffset + 195,
								yOffset + height - 450);
						contentStream.drawString("CHEMICAL COMPOSITION");
						contentStream.endText();

						contentStream.beginText();
						contentStream.setFont(fontBold, 10);
						contentStream.moveTextPositionByAmount(xOffset + 150,
								yOffset + height - 470);
						contentStream.drawString("Grade .: ");
						contentStream.endText();

						contentStream.beginText();
						contentStream.setFont(font, 10);
						contentStream.moveTextPositionByAmount(xOffset + 200,
								yOffset + height - 470);
						contentStream.drawString(selectedTestCertificateVO
								.getGrade());
						contentStream.endText();
					}

					int tableOffset = 20;
					if (printMechanicalProperties) {
					int actualValueIndex = 0;
					for (int ij = 0; ij < 15; ij++) {

						if (ij == 1) {
							contentStream.addLine(xOffset + tableOffset + 20,
									yOffset + height - (475 + (16 * ij)), xOffset
											+ tableOffset + 210, yOffset + height
											- (475 + (16 * ij)));
							contentStream.beginText();
							contentStream.setFont(font, 10);
							contentStream.moveTextPositionByAmount(xOffset
									+ tableOffset + 25, yOffset + height
									- ((471 + (16 * ij))));
							contentStream.drawString("Ingredients");
							contentStream.endText();

							contentStream.beginText();
							contentStream.setFont(font, 10);
							contentStream.moveTextPositionByAmount(xOffset
									+ tableOffset + 80, yOffset + height
									- ((471 + (16 * ij))));
							contentStream.drawString("Specified");
							contentStream.endText();

							contentStream.beginText();
							contentStream.setFont(font, 10);
							contentStream.moveTextPositionByAmount(xOffset
									+ tableOffset + 165, yOffset + height
									- ((471 + (16 * ij))));
							contentStream.drawString("Actual");
							contentStream.endText();
						} else if (ij == 0) {
							contentStream.addLine(xOffset + tableOffset + 20,
									yOffset + height - (475 + (16 * ij)), xOffset
											+ tableOffset + 210, yOffset + height
											- (475 + (16 * ij)));
						} else if (ij == 2) {
							contentStream.addLine(xOffset + tableOffset + 20,
									yOffset + height - (475 + (16 * ij)), xOffset
											+ tableOffset + 210, yOffset + height
											- (475 + (16 * ij)));
							contentStream.beginText();
							contentStream.setFont(font, 10);
							contentStream.moveTextPositionByAmount(xOffset
									+ tableOffset + 80, yOffset + height
									- ((471 + (16 * ij))));
							contentStream.drawString("Min.");
							contentStream.endText();

							contentStream.beginText();
							contentStream.setFont(font, 10);
							contentStream.moveTextPositionByAmount(xOffset
									+ tableOffset + 120, yOffset + height
									- ((471 + (16 * ij))));
							contentStream.drawString("Max.");
							contentStream.endText();
						} else {
							for (; actualValueIndex < selectedTestCertificateVO
									.getActualValues().size(); actualValueIndex++) {
								if (selectedTestCertificateVO.getActualValues()
										.get(actualValueIndex).getPropType()
										.equalsIgnoreCase("C")) {
									contentStream.addLine(xOffset + tableOffset + 20,
											yOffset + height - (475 + (16 * ij)), xOffset
													+ tableOffset + 210, yOffset + height
													- (475 + (16 * ij)));
									contentStream.addLine(xOffset + tableOffset + 20,
											yOffset + height - (475 + (16 * 0)),
											xOffset + tableOffset + 20, yOffset
													+ height - (475 + (16 * ij)));
									contentStream.addLine(xOffset + tableOffset + 78,
											yOffset + height - (475 + (16 * 0)),
											xOffset + tableOffset + 78, yOffset
													+ height - (475 + (16 * ij)));

									// Line between min & max.
									contentStream.addLine(xOffset + tableOffset + 117,
											yOffset + height - (475 + (16 * 1)),
											xOffset + tableOffset + 117, yOffset
													+ height - (475 + (16 * ij)));
									contentStream.addLine(xOffset + tableOffset + 157,
											yOffset + height - (475 + (16 * 0)),
											xOffset + tableOffset + 157, yOffset
													+ height - (475 + (16 * ij)));
									// Last Line
									contentStream.addLine(xOffset + tableOffset + 210,
											yOffset + height - (475 + (16 * 0)),
											xOffset + tableOffset + 210, yOffset
													+ height - (475 + (16 * ij)));

									contentStream.beginText();
									contentStream.setFont(font, 10);
									contentStream.moveTextPositionByAmount(
											xOffset + tableOffset + 25, yOffset
													+ height
													- ((471 + (16 * ij))));
									contentStream
											.drawString(selectedTestCertificateVO
													.getActualValues()
													.get(actualValueIndex)
													.getPropName());
									contentStream.endText();

									contentStream.beginText();
									contentStream.setFont(font, 10);
									contentStream.moveTextPositionByAmount(
											xOffset + tableOffset + 80, yOffset
													+ height
													- ((471 + (16 * ij))));
									contentStream
											.drawString(selectedTestCertificateVO
													.getActualValues()
													.get(actualValueIndex)
													.getMinValue());
									contentStream.endText();

									contentStream.beginText();
									contentStream.setFont(font, 10);
									contentStream.moveTextPositionByAmount(
											xOffset + tableOffset + 120,
											yOffset + height
													- ((471 + (16 * ij))));
									contentStream
											.drawString(selectedTestCertificateVO
													.getActualValues()
													.get(actualValueIndex)
													.getMaxValue());
									contentStream.endText();

									contentStream.beginText();
									contentStream.setFont(font, 10);
									contentStream.moveTextPositionByAmount(
											xOffset + tableOffset + 165,
											yOffset + height
													- ((471 + (16 * ij))));
									contentStream
											.drawString(selectedTestCertificateVO
													.getActualValues()
													.get(actualValueIndex)
													.getActual1());
									contentStream.endText();
									actualValueIndex++;
									break;
								}
								actualValueIndex++;
							}
						}
					}
						int tableOffset2 = 10;
						actualValueIndex = 0;
						for (int ij = 0; ij < 15; ij++) {

							if (ij == 1) {
								contentStream.addLine(xOffset + tableOffset2 + 255,
										yOffset + height - (475 + (16 * ij)),
										xOffset + tableOffset2 + 525, yOffset
												+ height - (475 + (16 * ij)));
								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset2 + 260, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Particulars");
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset2 + 325, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Specified");
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset2 + 410, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Actual");
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset2 + 450, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Actual");
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset2 + 490, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Actual");
								contentStream.endText();
							} else if (ij == 2) {
								contentStream.addLine(xOffset + tableOffset2 + 255,
										yOffset + height - (475 + (16 * ij)),
										xOffset + tableOffset2 + 525, yOffset
												+ height - (475 + (16 * ij)));
								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset2 + 325, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Min.");
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset2 + 365, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Max.");
								contentStream.endText();
							} else if (ij == 0) {
								contentStream.addLine(xOffset + tableOffset2 + 255,
										yOffset + height - (475 + (16 * ij)),
										xOffset + tableOffset2 + 525, yOffset
												+ height - (475 + (16 * ij)));
							} else { 
								for (; actualValueIndex < selectedTestCertificateVO
										.getActualValues().size(); actualValueIndex++) {
									if (selectedTestCertificateVO
											.getActualValues()
											.get(actualValueIndex)
											.getPropType()
											.equalsIgnoreCase("M")) {
										contentStream.addLine(xOffset + tableOffset2 + 255,
												yOffset + height - (475 + (16 * ij)),
												xOffset + tableOffset2 + 525, yOffset
														+ height - (475 + (16 * ij)));
										

										contentStream.addLine(xOffset + tableOffset2
												+ 255, yOffset + height
												- (475 + (16 * 0)), xOffset
												+ tableOffset2 + 255, yOffset + height
												- (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset2
												+ 320, yOffset + height
												- (475 + (16 * 0)), xOffset
												+ tableOffset2 + 320, yOffset + height
												- (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset2
												+ 362, yOffset + height
												- (475 + (16 * 1)), xOffset
												+ tableOffset2 + 362, yOffset + height
												- (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset2
												+ 405, yOffset + height
												- (475 + (16 * 0)), xOffset
												+ tableOffset2 + 405, yOffset + height
												- (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset2
												+ 445, yOffset + height
												- (475 + (16 * 0)), xOffset
												+ tableOffset2 + 445, yOffset + height
												- (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset2
												+ 485, yOffset + height
												- (475 + (16 * 0)), xOffset
												+ tableOffset2 + 485, yOffset + height
												- (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset2
												+ 525, yOffset + height
												- (475 + (16 * 0)), xOffset
												+ tableOffset2 + 525, yOffset + height
												- (475 + (16 * ij)));
										
										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset2 + 260,
												yOffset + height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getPropName());
										contentStream.endText();

										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset2 + 325,
												yOffset + height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getMinValue());
										contentStream.endText();

										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset2 + 365,
												yOffset + height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getMaxValue());
										contentStream.endText();

										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset2 + 410,
												yOffset + height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getActual1());
										contentStream.endText();

										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset2 + 450,
												yOffset + height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getActual2());
										contentStream.endText();

										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset2 + 490,
												yOffset + height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getActual3());
										contentStream.endText();
										actualValueIndex++;
										break;
									}
									actualValueIndex++;
								}
							}
						}
					} else {
						// Print the table in the middle.
						tableOffset = 155;
						int actualValueIndex = 0;
						for (int ij = 0; ij < 15; ij++) {

							if (ij == 1) {
								contentStream.addLine(xOffset + tableOffset + 20,
										yOffset + height - (475 + (16 * ij)), xOffset
												+ tableOffset + 210, yOffset + height
												- (475 + (16 * ij)));
								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset + 25, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Ingredients");
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset + 80, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Specified");
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset + 165, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Actual");
								contentStream.endText();
							} else if (ij == 0) {
								contentStream.addLine(xOffset + tableOffset + 20,
										yOffset + height - (475 + (16 * ij)), xOffset
												+ tableOffset + 210, yOffset + height
												- (475 + (16 * ij)));

							} else if (ij == 2) {
								contentStream.addLine(xOffset + tableOffset + 20,
										yOffset + height - (475 + (16 * ij)), xOffset
												+ tableOffset + 210, yOffset + height
												- (475 + (16 * ij)));
								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset + 80, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Min.");
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(font, 10);
								contentStream.moveTextPositionByAmount(xOffset
										+ tableOffset + 120, yOffset + height
										- ((471 + (16 * ij))));
								contentStream.drawString("Max.");
								contentStream.endText();
							} else {
								for (; actualValueIndex < selectedTestCertificateVO
										.getActualValues().size(); actualValueIndex++) {
									if (selectedTestCertificateVO.getActualValues()
											.get(actualValueIndex).getPropType()
											.equalsIgnoreCase("C")) {
										contentStream.addLine(xOffset + tableOffset + 20,
												yOffset + height - (475 + (16 * ij)), xOffset
														+ tableOffset + 210, yOffset + height
														- (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset + 20,
												yOffset + height - (475 + (16 * 0)),
												xOffset + tableOffset + 20, yOffset
														+ height - (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset + 78,
												yOffset + height - (475 + (16 * 0)),
												xOffset + tableOffset + 78, yOffset
														+ height - (475 + (16 * ij)));

										// Line between min & max.
										contentStream.addLine(xOffset + tableOffset + 117,
												yOffset + height - (475 + (16 * 1)),
												xOffset + tableOffset + 117, yOffset
														+ height - (475 + (16 * ij)));
										contentStream.addLine(xOffset + tableOffset + 157,
												yOffset + height - (475 + (16 * 0)),
												xOffset + tableOffset + 157, yOffset
														+ height - (475 + (16 * ij)));
										// Last Line
										contentStream.addLine(xOffset + tableOffset + 210,
												yOffset + height - (475 + (16 * 0)),
												xOffset + tableOffset + 210, yOffset
														+ height - (475 + (16 * ij)));
										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset + 25, yOffset
														+ height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getPropName());
										contentStream.endText();

										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset + 80, yOffset
														+ height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getMinValue());
										contentStream.endText();

										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset + 120,
												yOffset + height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getMaxValue());
										contentStream.endText();

										contentStream.beginText();
										contentStream.setFont(font, 10);
										contentStream.moveTextPositionByAmount(
												xOffset + tableOffset + 165,
												yOffset + height
														- ((471 + (16 * ij))));
										contentStream
												.drawString(selectedTestCertificateVO
														.getActualValues()
														.get(actualValueIndex)
														.getActual1());
										contentStream.endText();
										actualValueIndex++;
										break;
									}
									actualValueIndex++;
								}
							}
						}

					}
					tableOffset = 20;
					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset
							+ tableOffset + 25, yOffset + height - 720);
					contentStream.drawString("Other Tests And Remarks: ");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset
							+ tableOffset + 155, yOffset + height - 720);
					contentStream.drawString(selectedTestCertificateVO
							.getOtherTests());
					contentStream.endText();

					// Signature
					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 170, yOffset + height - 770);
					contentStream.drawString("For");
					contentStream.endText();
					contentStream.beginText();
					contentStream.setFont(fontBold, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 150, yOffset + height - 770);
					contentStream.drawString("SPIN-N-CAST (UNIT - II)");
					contentStream.endText();

					contentStream.beginText();
					contentStream.setFont(font, 10);
					contentStream.moveTextPositionByAmount(xOffset + width
							- 148, yOffset + height - 820);
					contentStream.drawString("Authorised Signatory");
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

	private List<String> getFormattedAddress(String bigString, int characters) {
		List<String> returnValue = new ArrayList<String>();
		// bigString = bigString.replaceAll("\\(", "");
		// bigString = bigString.replaceAll("\\)", "");
		
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
						temp = temp.replaceAll("\\(", "\\\\(");
						temp = temp.replaceAll("\\)", "\\\\)");
						temp = temp.replaceAll("\\+", "\\\\+");
						bigString = bigString.replaceFirst(temp + " ", "");
						temp = temp.replaceAll("\\\\\\(", "(");
						temp = temp.replaceAll("\\\\\\)", ")");
						temp = temp.replaceAll("\\\\\\+", "+");
					} else {
						temp = temp.substring(0, temp.lastIndexOf(","));
						bigString = bigString.replaceFirst(temp + ",", "");
					}
					returnValue.add(temp);
				}
				returnValue.add(bigString.replaceAll("\\\\\\(", "(")
						.replaceAll("\\\\\\)", ")").replaceAll("\\\\\\+", "+"));
				//returnValue.add(bigString);
			}
		}
		return returnValue;
	}

	public boolean isPrintMechanicalProperties() {
		return printMechanicalProperties;
	}

	public void setPrintMechanicalProperties(boolean printMechanicalProperties) {
		this.printMechanicalProperties = printMechanicalProperties;
	}

}