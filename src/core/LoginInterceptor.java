package core;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 3734192766056573212L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map<String, Object> session =  arg0.getInvocationContext().getSession();  
        String actionName = arg0.getInvocationContext().getName();
        if (!"Login".equalsIgnoreCase(actionName.split("_")[0])
    		&& !"loginPage".equalsIgnoreCase(actionName)) {  
            session.put("lastAction", actionName);  
            System.out.println("Last Action :"+actionName);
        }  
		return arg0.invoke();
	}

}
