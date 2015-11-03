package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Accounts extends Controller
{
  public static void signup()
  {
    render();
  }

  public static void logout()
  {
	session.clear();
    index();
  }

  public static void index()
  {
    render();
  }
  
  public static void register(String firstName, String lastName,
                              String email,     String password)
  {
    Logger.info(firstName + " " + lastName + " " + email + " " + password);
    User user = new User(firstName, lastName, email, password);
    Blog blog = new Blog ("Default Blog");
    blog.save();
    user.blogs.add(blog);
    user.save();    
    index();
  }

  public static void authenticate(String email, String password)
  {
    User user = User.findByEmail(email);
    if (user == null || !user.checkPassword(password))
    {
      index();
    }
    session.put("logged_in_userid", user.id);
    BlogController.index();
  }
  
  public static User getLoggedInUser()
  {
    User user = null;
    if (session.contains("logged_in_userid"))
    {
      String userId = session.get("logged_in_userid");
      user = User.findById(Long.parseLong(userId));
    }
    else
    {
      //login();
    	index();
    }
    return user;
  }
}