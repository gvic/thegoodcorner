package core;

import com.google.inject.Binder;
import com.google.inject.Module;
import dao.UserService;
import javax.naming.Context;
import javax.naming.InitialContext;
import static com.google.inject.jndi.JndiIntegration.fromJndi;


public class EJBModule implements Module {

	@Override
	public void configure(Binder binder) {
		// Bind Context to the default InitialContext.
		binder.bind(Context.class).to(InitialContext.class);
		// Bind the remote interface to the JNDI name using the JNDI Provider
		binder.bind(UserService.class).toProvider(fromJndi(UserService.class,"dao.UserServiceImpl"));
	}
}
