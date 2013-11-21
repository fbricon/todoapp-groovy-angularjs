package foo.bar.boundary

import javax.ejb.LocalBean
import javax.ejb.Stateless
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

import foo.bar.entity.Todo
import groovy.util.logging.Slf4j;


@Stateless
@LocalBean
@Slf4j
public class Todos implements Serializable {

	private static final long serialVersionUID = 1L

	@PersistenceContext(unitName = "TodoAppPU")
	private EntityManager em;

	def List<Todo> all() {
		em.createQuery("SELECT t from Todo t", Todo.class).resultList
	}

	def void mark(Long id, boolean status) {
		Todo todo = em.find(Todo.class, id)
		if (todo) {
			todo.done = status
			em.merge(todo)
			log.info "Marked '${todo.text}' as " + ((status)?"":"un") + "done"			
		}
	}

	def void add(String text) {
		em.persist(new Todo(text: text, done: false))
		log.info "Added '${text}'"
	}

	def void delete(Long id) {
		Todo todo = em.find(Todo.class, id)
		if (todo) {
			em.remove(todo);
			log.info "deleted '${todo.text}'"
		}
	}
}
