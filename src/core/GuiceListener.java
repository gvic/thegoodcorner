package core;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;;

public class GuiceListener extends GuiceServletContextListener{

	private final Injector injector;

	  @Inject
	  public GuiceListener(Injector injector) {
	    this.injector = injector;
	  }

	  @Override
	  public Injector getInjector() {
	    return injector;
	  }
}
