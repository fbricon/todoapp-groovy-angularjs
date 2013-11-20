package foo.bar.rest

import javax.ejb.LocalBean
import javax.ejb.Stateless
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.FormParam
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import foo.bar.boundary.Todos
import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j

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
  
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void create(@FormParam("text") String text) {
    todos.add text
  }
    
  @Path("{id}")
  @DELETE  
  public void delete(@PathParam("id") Long id) {
    todos.delete id
  }
  
  @Path("{id}")
  @PUT
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void update(@PathParam("id") Long id, @FormParam("done") String done) {
    todos.mark(id, "true" == done)
  }
}
