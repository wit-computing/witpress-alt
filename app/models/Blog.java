package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Blog extends Model
{
  public String title;

  @OneToMany(cascade=CascadeType.ALL)
  public List<Post> posts = new ArrayList<>();  
  
  public Blog (String title)
  {
    this.title = title;
  }
  
  public void addPost (Post post)
  {
    posts.add(post);
  }
}
