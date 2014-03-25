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
import org.demo.spinncast.hibernate.TestCertificateHBC;
import org.demo.spinncast.vo.TestCertificateVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "TestCertificateBean")
@SessionScoped
public class TestCertificateBean {

	TestCertificateVO testCertificateVO;

	private List<TestCertificateVO> searchList;

	int editid;
	Transaction trans;
	private Integer selectedId;

	private TestCertificateVO editedTc;

	public TestCertificateVO getEditedTc() {
		return editedTc;
	}

	public void setEditedTc(TestCertificateVO editedTc) {
		System.out.println("set" + editedTc.getId());
		this.editedTc = editedTc;
	}

	public int getEditid() {
		return editid;
	}

	public void setEditid(int editid) {
		this.editid = editid;
	}

	public TestCertificateBean() {
		testCertificateVO = new TestCertificateVO();
		searchList = new ArrayList<TestCertificateVO>();

		editedTc = new TestCertificateVO();
	}

	public TestCertificateVO getTestCertificateVO() {
		return testCertificateVO;
	}

	public void setTestCertificateVO(TestCertificateVO testCertificateVO) {
		this.testCertificateVO = testCertificateVO;
	}

	public void store() {
		searchList.set(editid, editedTc);
	}

	public String search() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from TestCertificateHBC as m order by m.id");
		@SuppressWarnings("unchecked")
		java.util.List<TestCertificateHBC> results = hibernateQuery.list();
		setSearchList(new ArrayList<TestCertificateVO>());
		for (int i = 0; i < results.size(); i++) {
			TestCertificateVO tcItem = new TestCertificateVO();
			tcItem.setId(results.get(i).getId());
			tcItem.setName(results.get(i).getName());
			tcItem.setDescription(results.get(i).getDescription());
			tcItem.setQuantity(results.get(i).getQuantity());
			tcItem.setRate(results.get(i).getRate());
			getSearchList().add(tcItem);
		}
		System.out.println(getSearchList().size());
		Transaction trans = session.beginTransaction();
		trans.commit();

		session.close();
		return "TestCertificateSearch";
	}

	private boolean editFlag = true;

	public String add() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		TestCertificateHBC testcertificatehbc = new TestCertificateHBC(
				testCertificateVO);
		session.saveOrUpdate(testcertificatehbc);
		trans.commit();
		session.close();
		testCertificateVO = new TestCertificateVO();
		return search();
	}

	public String edit() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		TestCertificateHBC testcertificatehbc = new TestCertificateHBC(
				testCertificateVO);
		System.out.println("TCHBC ID: " + testcertificatehbc.getId());
		session.update(testcertificatehbc);
		trans.commit();
		session.close();
		testCertificateVO = new TestCertificateVO();
		return search();
	}

	public String reset() {
		getSearchList().clear();
		return "TestCertificateSearch";
	}

	public String delete() {
		return "";
	}

	public List<TestCertificateVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<TestCertificateVO> searchList) {
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

		int xOffset = 0;
		int yOffset = 0;
		float height = PDPage.PAGE_SIZE_A4.getHeight();
		float width = PDPage.PAGE_SIZE_A4.getWidth();

		ByteArrayOutputStream output = new ByteArrayOutputStream();

		// Creating Document
		document = new PDDocument();

		// Creating Pages
		for (int i = 0; i < 1; i++) {

			page = new PDPage(PDPage.PAGE_SIZE_A4);

			// Adding page to document
			document.addPage(page);

			// Adding FONT to document
			font = PDType1Font.HELVETICA;
			// Next we start a new content stream which will "hold" the to be
			// created content.
			contentStream = new PDPageContentStream(document, page);

			// Let's define the content stream
			contentStream.setStrokingColor(0, 0, 0);
			// Margin
			contentStream.addLine(xOffset + 2, yOffset + 2, xOffset + 2,
					yOffset + height - 2);
			contentStream.addLine(xOffset + 2, yOffset + 2, xOffset + width,
					yOffset + 2);
			contentStream.addLine(xOffset + 2, yOffset + height - 2, xOffset
					+ width - 2, yOffset + height - 2);
			contentStream.addLine(xOffset + width - 2, yOffset + 2, xOffset
					+ width - 2, yOffset + height - 2);

			// Top Section
			contentStream.addLine(xOffset + 2, yOffset + height - 80, xOffset
					+ width - 2, yOffset + height - 80);

			// Second Section
			contentStream.addLine(
					Float.valueOf(
							new Double(xOffset + (width / 2.0)).toString())
							.floatValue(),
					yOffset + height - 80,
					Float.valueOf(
							new Double(xOffset + (width / 2.0)).toString())
							.floatValue(), yOffset + height - 180);

			contentStream.addLine(xOffset + 2, yOffset + height - 180, xOffset
					+ width - 2, yOffset + height - 180);

			// Third Section
			contentStream.addLine(
					Float.valueOf(
							new Double(xOffset + (width / 3.0)).toString())
							.floatValue(),
					yOffset + height - 180,
					Float.valueOf(
							new Double(xOffset + (width / 3.0)).toString())
							.floatValue(), yOffset + height - 320);

			contentStream.addLine(
					Float.valueOf(
							new Double(xOffset + (width * 2.0 / 3.0))
									.toString()).floatValue(),
					yOffset + height - 180,
					Float.valueOf(
							new Double(xOffset + (width * 2.0 / 3.0))
									.toString()).floatValue(), yOffset + height
							- 320);

			contentStream.addLine(xOffset + 2, yOffset + height - 320, xOffset
					+ width - 2, yOffset + height - 320);

			// Fourth Section
			contentStream.beginText();
			contentStream.setFont(font, 10);
			contentStream.moveTextPositionByAmount(xOffset + 12, yOffset + height - 334);
			contentStream.drawString("Ferrous Sulphate Casting");
			contentStream.endText();
			contentStream.addLine(xOffset + 2, yOffset + height - 340, xOffset
					+ width - 2, yOffset + height - 340);

			// Fifth Section
			contentStream.addLine(xOffset + 2, yOffset + height - 600, xOffset
					+ width - 2, yOffset + height - 600);

			// Six Section
			contentStream.addLine(xOffset + width - 100,
					yOffset + height - 340, xOffset + width - 100, yOffset + 2);
			contentStream.addLine(xOffset + width - 200,
					yOffset + height - 600, xOffset + width - 200, yOffset + 2);

			contentStream.stroke();
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
			fc.responseComplete(); // Important! Otherwise JSF will attempt to
									// render the response which obviously will
									// fail since it's already written with a
									// file and closed.

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Print Over");
		return "TestCertificateSearch";

	}
}