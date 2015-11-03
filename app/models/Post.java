package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Lob;

import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends Model
{
  public String title;
  
  @Lob
  public String content;

  @OneToMany(cascade = CascadeType.ALL) 
  public List<Comment> comments;

  public Post(String title, String content)
  {
    this.title = title;
    this.content = content;
    this.comments = new ArrayList<Comment>();
  }

  public void addComment(Comment comment)
  {
    comments.add(comment);
  }

  public String toString()
  {
    return title;
  }
}