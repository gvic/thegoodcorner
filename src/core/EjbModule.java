package core;

import javax.naming.Context;
import javax.naming.InitialContext;
import static com.google.inject.jndi.JndiIntegration.fromJndi;
import com.google.inject.Binder;
import com.google.inject.Module;

import dao.AdService;
import dao.UserService;

public class EjbModule implements Module {

  public void configure(Binder binder) {
    // Bind Context to the default InitialContext.
    binder.bind(Context.class).to(InitialContext.class);
    // Bind the remote interface to the JNDI name using the JNDI Provider
    binder.bind(UserService.class)
          .toProvider(fromJndi(UserService.class, "dao.UserServiceImpl"));
    
    binder.bind(AdService.class)
    .toProvider(fromJndi(AdService.class, "dao.AdServiceImpl"));
    
    binder.bind(dao.PopulateService.class)
    .toProvider(fromJndi(dao.PopulateService.class, "dao.PopulateServiceImpl"));
    
    
  }
}