# A todo list made with Java EE 6, Groovy and AngularJS

This is a fork of https://github.com/jponge/todoapp-javaee6-angularjs where the original, classic java back-end has been re-implemented in Groovy.

Due to differences in vendor implementations of JAXB-JSON marshalling (Java EE anyone?), the REST endpoints return _manually_ built JSON strings :

```groovy

@Path("todo")
@Stateless
@LocalBean
@Slf4j
class TodoResource {
  
  @Inject
  private Todos todos;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  def String list() {
    def result
    try {
        def todoList = todos.all()
        def json = new JsonBuilder(todoList)
        result = json.toString()
    } catch (Exception ex) {
      log.error(ex)
      result = "[]";
    }
    result
  }
  
  [...]
}
```

Notable changes from the original example are : 

* Groovy EJBs are annotated with @LocalBean (see [this](https://github.com/wildfly/wildfly/pull/4777#issuecomment-22286670))   
* The JMS invocation was removed to simplify the example and be able to run on TomEE + JAX-RS
* Changed fr.insalyon.telecom.* namespaces to a more neutral foo.bar.*
* Works OOTB on JBoss AS 7.1.1, WilfFly 8.0 Beta1 and TomEE 1.5.2 + JAX-RS (which [requires a web.xml](https://issues.apache.org/jira/browse/TOMEE-1078))
* *Does not work* on GlassFish (it doesn't like the @LocalBean annotation on Groovy classes)
 

## License

Copyright (c) 2012-2013 Julien Ponge, Institut National des Sciences Appliquées de Lyon; Fred Bricon

This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
