package co.com.hoteles.turin.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "empleado")
public class Employee {
 
    @Id
    @Column(name = "id")
    private int id;
 
    @Column(name = "fistName")
    private String firstName;
    
    
 
 
    public Employee() {
 
    }
 
    public Employee(int id, String firstName) {
        this.setId(id);
        this.setFirstName(firstName);
       
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
   
}