package foo.bar.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Todo implements Serializable {
  
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  Long id;  
  
  @Column
  String text = "";
  
  @Column
  boolean done;

}
