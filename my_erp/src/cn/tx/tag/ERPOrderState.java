package cn.tx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import cn.tx.utils.ERPConstants;

public class ERPOrderState extends TagSupport {

	private String orderType;
	
	private String orderState;
	
	

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		String text = "";
		switch(orderType){
			case ERPConstants.ORDER_TYPE_BUY:
				switch(orderState){
					case ERPConstants.ORDER_TYPE_BUY_AUDIT:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_BUY_AUDIT_PASS:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_PASS_TEXT;
						break;	
					case ERPConstants.ORDER_TYPE_BUY_AUDIT_REJECT:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_REJECT_TEXT;
						break;	
				};break;
				
		}
		try {
			out.write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
