package foo.bar.rest;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import foo.bar.boundary.Todos;

@Startup
@Singleton
public class JavaBootStrap {//For some reason, @Startup a groovy class doesn't work 
	
	private static final Logger LOG = LoggerFactory.getLogger(JavaBootStrap.class);

	@Inject //Look, I inject a groovy class!
	Todos todos;
	
	@PostConstruct
	private void postConstruct() {
		LOG.info("Bootstraping the application");
		if (todos != null && todos.all().isEmpty()) {
			todos.add("Check that groovy rocks");
			LOG.info("Seeded initial todo");
		}
	}
}
