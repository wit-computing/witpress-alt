package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User extends Model
{ 
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  
  @OneToMany(cascade = CascadeType.ALL)
  public List<Blog> blogs = new ArrayList<>();
  
  public User(String firstName, String lastName,String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;   
    this.email = email;
    this.password = password;
  }
  
  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }  
  
  public String toString()
  {
    return firstName;
  }
}