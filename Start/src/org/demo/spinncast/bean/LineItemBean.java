package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.LineItemHBC;
import org.demo.spinncast.vo.LineItemVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "LineItemBean")
@SessionScoped
public class LineItemBean {

	private LineItemVO selectedLineItemVO = new LineItemVO();
	private List<LineItemVO> lineItemList = new ArrayList<LineItemVO>();
	private Integer selectedPurchaseOrderId;

	public Integer getSelectedPurchaseOrderId() {
		return selectedPurchaseOrderId;
	}

	public void setSelectedPurchaseOrderId(Integer selectedPurchaseId) {
		this.selectedPurchaseOrderId = selectedPurchaseId;
	}

	public List<LineItemVO> getLineItemList() {
		return lineItemList;
	}

	public void setLineItemList(List<LineItemVO> lineItemList) {
		this.lineItemList = lineItemList;
	}

	public LineItemVO getSelectedLineItemVO() {
		return selectedLineItemVO;
	}

	public void setSelectedLineItemVO(LineItemVO selectedLineItemVO) {
		this.selectedLineItemVO = selectedLineItemVO;
	}

	public String resetLineItemAdd() {
		selectedLineItemVO = new LineItemVO();
		return "PurchaseOrderSearch";
	}

	@SuppressWarnings("unchecked")
	public String fetchLineItems() {
		lineItemList = new ArrayList<LineItemVO>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from LineItemHBC as m where m.purchase_order_id = :pid order by m.id");
		hibernateQuery.setInteger("pid", selectedPurchaseOrderId);
		java.util.List<LineItemHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			LineItemVO lnItmVo = new LineItemVO();
			lnItmVo.setId(results.get(i).getLine_item_id());
			lnItmVo.setDescription(results.get(i).getDescription());
			lnItmVo.setPurchase_order_id(results.get(i).getPurchase_order_id());
			lnItmVo.setQuantity(results.get(i).getQuantity());
			lnItmVo.setRate(results.get(i).getRate());
			lnItmVo.setStatus(results.get(i).getStatus());
			lnItmVo.setUnit(results.get(i).getUnit());
			lineItemList.add(lnItmVo);
		}

		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return "LineItemDetailsEdit";
	}

	public void editLineItems() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		LineItemHBC modifiedLi = new LineItemHBC(selectedLineItemVO);
		session.update(modifiedLi);
		trans.commit();
		session.close();
		selectedLineItemVO = new LineItemVO();
	}

	public String saveLineItem() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		selectedLineItemVO.setPurchase_order_id(selectedPurchaseOrderId);
		LineItemHBC lineItemHBC = new LineItemHBC(selectedLineItemVO);
		session.saveOrUpdate(lineItemHBC);
		trans.commit();
		session.close();
		selectedLineItemVO = new LineItemVO();
		return "";
	}
}
