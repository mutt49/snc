package org.demo.spinncast.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.CcmHBC;
import org.demo.spinncast.hibernate.MpmHBC;
import org.demo.spinncast.vo.CcmVO;
import org.demo.spinncast.vo.MpmVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "MpmBean")
@SessionScoped
public class MpmBean {

	private MpmVO mpmVO;

	private List<MpmVO> searchList;

	int editid;
	Transaction trans;
	private Integer selectedId;
	private Integer selectedPersonnelId;
	private MpmVO editedMpm;

	public int getEditid() {
		return editid;
	}

	public void setEditid(int editid) {
		this.editid = editid;
	}

	public MpmBean() {
		setMpmVO(new MpmVO());
		searchList = new ArrayList<MpmVO>();
		editedMpm = new MpmVO();
	}

	public void store() {
		searchList.set(editid, editedMpm);
	}

	public String search() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from MpmHBC as m order by m.mpm_id");
		@SuppressWarnings("unchecked")
		java.util.List<MpmHBC> results = hibernateQuery.list();
		setSearchList(new ArrayList<MpmVO>());
		for (int i = 0; i < results.size(); i++) {
			MpmVO mpmItem = new MpmVO(results.get(i));
			getSearchList().add(mpmItem);
		}
		System.out.println(getSearchList().size());
		Transaction trans = session.beginTransaction();
		trans.commit();

		session.close();
		return "MpmSearch";
	}

	private boolean editFlag = true;

	public String add() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		MpmHBC mpmhbc = new MpmHBC(mpmVO);
		session.saveOrUpdate(mpmhbc);
		trans.commit();
		session.close();
		mpmVO = new MpmVO();
		return search();
	}

	public String save() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		MpmHBC mpmhbc = new MpmHBC(mpmVO);
		System.out.println("MpmVO ID: " + mpmVO.getMpm_id());
		session.update(mpmhbc);
		trans.commit();
		session.close();
		mpmVO = new MpmVO();
		return search();
	}

	public String edit() {
		return "MpmAdd";
	}

	public String reset() {
		selectedId = 0;
		editedMpm = new MpmVO();
		mpmVO = new MpmVO();
		getSearchList().clear();
		return "MpmSearch";
	}

	public String delete() {
		return "";
	}

	public List<MpmVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<MpmVO> searchList) {
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

	public MpmVO getEditedMpm() {
		return editedMpm;
	}

	public void setEditedMpm(MpmVO editedMpm) {
		this.editedMpm = editedMpm;
	}

	public MpmVO getMpmVO() {
		return mpmVO;
	}

	public void setMpmVO(MpmVO mpmVO) {
		this.mpmVO = mpmVO;
	}
}