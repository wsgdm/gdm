package cn.tx.interceptor;

import java.util.Map;

import javax.mail.Session;

import org.apache.commons.lang.StringUtils;

import cn.tx.controller.BaseAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class Login extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		String res = "";
		Map<String, Object> session = ActionContext.getContext().getSession();
		String username = (String) session.get("user");
		if(StringUtils.isNotBlank(username)){
			res = ai.invoke();
		}else{
			res =new BaseAction().LOGIN;
		}
		return res;
	}

}
