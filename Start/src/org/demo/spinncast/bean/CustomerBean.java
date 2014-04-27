package org.demo.spinncast.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.hibernate.CustomerPersonnelHBC;
import org.demo.spinncast.vo.CustomerPersonnelVO;
import org.demo.spinncast.vo.CustomerVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "CustomerBean")
@SessionScoped
public class CustomerBean {

	CustomerVO customerVO;
	CustomerPersonnelVO customerPersonnelVO;

	public CustomerPersonnelVO getCustomerPersonnelVO() {
		return customerPersonnelVO;
	}

	public void setCustomerPersonnelVO(CustomerPersonnelVO customerPersonnelVO) {
		this.customerPersonnelVO = customerPersonnelVO;
	}

	private List<CustomerVO> searchList;

	int editid;
	Transaction trans;
	private Integer selectedId;
	private Integer selectedPersonnelId;
	private CustomerVO editedCustomer;

	public CustomerVO getEditedCustomer() {
		return editedCustomer;
	}

	public void setEditedCustomer(CustomerVO editedCustomer) {
		this.editedCustomer = editedCustomer;
	}

	public int getEditid() {
		return editid;
	}

	public void setEditid(int editid) {
		this.editid = editid;
	}

	public CustomerBean() {
		customerVO = new CustomerVO();
		searchList = new ArrayList<CustomerVO>();
		customerPersonnelVO = new CustomerPersonnelVO();
		editedCustomer = new CustomerVO();
	}

	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}

	public void store() {
		searchList.set(editid, editedCustomer);
	}

	@SuppressWarnings("unchecked")
	public String search() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m order by m.customer_id");
		java.util.List<CustomerHBC> results = hibernateQuery.list();
		setSearchList(new ArrayList<CustomerVO>());
		for (int i = 0; i < results.size(); i++) {
			CustomerVO customerItem = new CustomerVO(results.get(i));
			getSearchList().add(customerItem);
		}
		System.out.println(getSearchList().size());
		Transaction trans = session.beginTransaction();
		trans.commit();

		session.close();
		return "CustomerSearch";
	}

	private boolean editFlag = true;

	public String add() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		CustomerHBC customerhbc = new CustomerHBC(customerVO);
		session.saveOrUpdate(customerhbc);
		trans.commit();
		session.close();
		customerVO = new CustomerVO();
		return search();
	}

	public String save() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		CustomerHBC customerhbc = new CustomerHBC(customerVO);
		System.out.println("CHBC ID: " + customerVO.getCustomer_id());
		session.update(customerhbc);
		trans.commit();
		session.close();
		customerVO = new CustomerVO();
		return search();
	}

	public String edit() {
		/**
		 * Get Customer Personnel From db.
		 */
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerPersonnelHBC as m where m.customer_id="
						+ customerVO.getCustomer_id().toString()
						+ " order by m.personnel_id ");
		@SuppressWarnings("unchecked")
		java.util.List<CustomerPersonnelHBC> results = hibernateQuery.list();
		customerVO.setPersonnelList(new ArrayList<CustomerPersonnelVO>());
		for (int i = 0; i < results.size(); i++) {
			CustomerPersonnelVO customerPersonnelItem = new CustomerPersonnelVO(
					results.get(i));
			customerVO.getPersonnelList().add(customerPersonnelItem);
		}
		System.out.println(getSearchList().size());
		Transaction trans = session.beginTransaction();
		trans.commit();

		session.close();

		customerVO.getPersonnelList().add(new CustomerPersonnelVO());
		return "CustomerAdd";
	}

	public String reset() {
		getSearchList().clear();
		return "CustomerSearch";
	}

	public String delete() {
		return "";
	}

	public List<CustomerVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<CustomerVO> searchList) {
		this.searchList = searchList;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}

	public ByteArrayOutputStream createPDF() throws IOException,
			COSVisitorException {

		PDDocument document;
		PDPage page;
		PDFont font;
		PDPageContentStream contentStream;
		PDJpeg front;
		PDJpeg back;

		InputStream inputFront;
		InputStream inputBack;
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		// Creating Document
		document = new PDDocument();

		// Creating Pages
		for (int i = 0; i < 2; i++) {

			page = new PDPage();

			// Adding page to document
			document.addPage(page);

			// Adding FONT to document
			font = PDType1Font.HELVETICA;
			// Next we start a new content stream which will "hold" the to be
			// created content.
			contentStream = new PDPageContentStream(document, page);

			// Let's define the content stream
			contentStream.beginText();
			contentStream.setFont(font, 8);
			contentStream.moveTextPositionByAmount(10, 770);
			contentStream.drawString("Amount: $1.00");
			contentStream.endText();

			contentStream.beginText();
			contentStream.setFont(font, 8);
			contentStream.moveTextPositionByAmount(200, 770);
			contentStream.drawString("Sequence Number: 123456789");
			contentStream.endText();

			contentStream.beginText();
			contentStream.setFont(font, 8);
			contentStream.moveTextPositionByAmount(10, 760);
			contentStream.drawString("Account: 123456789");
			contentStream.endText();

			contentStream.beginText();
			contentStream.setFont(font, 8);
			contentStream.moveTextPositionByAmount(200, 760);
			contentStream.drawString("Captura Date: 04/25/2011");
			contentStream.endText();

			contentStream.beginText();
			contentStream.setFont(font, 8);
			contentStream.moveTextPositionByAmount(10, 750);
			contentStream.drawString("Bank Number: 123456789");
			contentStream.endText();

			contentStream.beginText();
			contentStream.setFont(font, 8);
			contentStream.moveTextPositionByAmount(200, 750);
			contentStream.drawString("Check Number: 123456789");
			contentStream.endText();

			// Let's close the content stream
			contentStream.close();

		}

		// Finally Let's save the PDF
		document.save(output);
		document.close();

		return output;
	}

	public String print() {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			output = createPDF();
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc
					.getExternalContext().getResponse();

			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"yourFile.pdf\"");
			response.getOutputStream().write(output.toByteArray());
			fc.responseComplete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Print Over");
		return "CustomerSearch";

	}

	public Integer getSelectedPersonnelId() {
		return selectedPersonnelId;
	}

	public void setSelectedPersonnelId(Integer selectedPersonnelId) {
		this.selectedPersonnelId = selectedPersonnelId;
	}

	public void savePersonnel() {
		System.out.println(selectedPersonnelId);
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		customerPersonnelVO.setCustomer_id(customerVO.getCustomer_id());
		CustomerPersonnelHBC customerPersonnelHbc = new CustomerPersonnelHBC(
				customerPersonnelVO);
		session.saveOrUpdate(customerPersonnelHbc);
		trans.commit();
		session.close();
		edit();
		return;
	}
}