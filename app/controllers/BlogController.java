package controllers;

import java.util.List;
import models.Post;
import models.User;
import models.Blog;
import play.Logger;
import play.mvc.Controller;
import java.util.Collections;
import java.util.ArrayList;

public class BlogController  extends Controller
{
  public static void index()
  {
    User user = Accounts.getLoggedInUser();
    List<Post> reversePosts  = new ArrayList<Post> (user.blogs.get(0).posts);
    Collections.reverse(reversePosts);
    render(user, reversePosts);
  }
  
  public static void newPost(String title, String content)
  {
    User user = Accounts.getLoggedInUser();
    Blog blog = user.blogs.get(0);
    Post post = new Post (title, content);
    blog.addPost(post);
    blog.save();
    //user.save();
    
    Logger.info ("title:" + title + " content:" + content);
    index();
  }
  
  public static void deletePost(Long postid)
  {    
    User user = Accounts.getLoggedInUser(); 
    Blog blog = user.blogs.get(0);
    
    Post post = Post.findById(postid);
    blog.posts.remove(post);

    user.save();
    post.delete();

    index();
  }
}