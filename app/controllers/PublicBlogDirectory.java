package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import models.Comment;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class PublicBlogDirectory  extends Controller
{
  public static void index()
  {
    ArrayList<User> usersWithBlogs = new ArrayList<User>();
    
    List<User> users = User.findAll();
    for (User user : users)
    {
      if (user.blogs.get(0).posts.size() > 0)
      {
        usersWithBlogs.add(user);
      }
    }
    render(usersWithBlogs);
  }
}