package org.demo.spinncast.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.InvoiceHeaderHBC;
import org.demo.spinncast.hibernate.InvoiceLineItemHBC;
import org.demo.spinncast.vo.InvoiceHeaderVO;
import org.demo.spinncast.vo.InvoiceLineItemVO;
import org.demo.spinncast.vo.PartGradeMappingVO;
import org.demo.spinncast.vo.PartMasterVO;
import org.demo.spinncast.vo.PurchaseOrderLinesVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class InvoiceHandler {

	public void addInvoiceHeader(InvoiceHeaderVO invHeaderVO) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		try {
			/*
			 * invHeaderVO.setCustomerId(invHeaderVO.getCustDetails()
			 * .getCustomer_id());
			 */
			invHeaderVO.setInvDate(new Date());
			invHeaderVO.setInvNo("Draft" + invHeaderVO.getPurchaseOrderId());
			InvoiceHeaderHBC invHeaderHBC = new InvoiceHeaderHBC(invHeaderVO);
			session.saveOrUpdate(invHeaderHBC);
			trans.commit();
			// selectedInvId = invHeaderHBC.getInvId();
			invHeaderVO.setInvId(invHeaderHBC.getInvId());
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

	public String addLineItem(PurchaseOrderLinesVO poLine,
			InvoiceHeaderVO invHdr) {

		/*
		 * We check whether this line item will fit on the PDF or not! If not we
		 * will not add it and return back.
		 */
		/*
		 * List<String> partDetails = getWrappedStringList(partVo.getPartName()
		 * + " " + invLineItem.getPkgDesc(), 40);
		 * 
		 * if (selectedInvHdrVo.getLinesOfLineItem() + partDetails.size() >
		 * lineLimitOnPdf) {
		 * 
		 * Add error msg on page.
		 * 
		 * System.out.println("ERROR: LINEITEM WILL NOT FIT ON PDF."); return
		 * getLineItemsForInvId(); }
		 * 
		 * selectedInvHdrVo.setLinesOfLineItem(selectedInvHdrVo
		 * .getLinesOfLineItem() + partDetails.size());
		 */

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		
		poLine.setInvId(invHdr.getInvId());

		if (poLine.getUnit().equalsIgnoreCase("KG")
				|| poLine.getUnit().equalsIgnoreCase("set")) {
			poLine.setAmount(poLine.getQuantityKg() * poLine.getRate());
			poLine.setPendingQuantity(poLine.getQuantityKg()
					- poLine.getCurrQuantity());
		} else {
			poLine.setAmount(poLine.getQuantity() * poLine.getRate());
			poLine.setPendingQuantity(poLine.getQuantity()
					- poLine.getCurrQuantity());
		}

		InvoiceLineItemHBC invLineItemHBC = new InvoiceLineItemHBC(poLine);
		session.saveOrUpdate(invLineItemHBC);
		trans.commit();
		session.flush();
		session.close();

		// addLIamountAggregation(poLine);
		return "";
	}

	public void updateInvoiceLIAmount(PurchaseOrderLinesVO poLine) {
		double liTotalAmount = 0;
		if(poLine.getUnit().equalsIgnoreCase("no")){
			liTotalAmount = Float.parseFloat(poLine.getCurrQuantity()+"") * poLine.getRate();
		}else{
			liTotalAmount = Float.parseFloat(poLine.getCurrQuantity()+"") * poLine.getRate();
		}
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		String hql = "update InvoiceHeaderHBC set liAmountTotal = :li_Amount_Total+liAmountTotal where invId = :inv_id ";
		Query query = session.createQuery(hql);
		query.setDouble("li_Amount_Total", liTotalAmount);
		query.setInteger("inv_id", poLine.getInvId());
		query.executeUpdate();
		trans.commit();
		session.flush();
		session.close();
	}
}
