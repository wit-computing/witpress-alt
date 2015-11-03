package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import models.Blog;
import models.Comment;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class PublicBlog  extends Controller
{
	public static void show(Long id)
	{
	  User user = User.findById(id);
    Blog blog = user.blogs.get(0);
	  List<Post> reversePosts  = new ArrayList<Post> (blog.posts);
	  Collections.reverse(reversePosts);
	  
	  User loggedInUser = null;
	  if (session.contains("logged_in_userid"))
	  {
	    String userId = session.get("logged_in_userid");
	    loggedInUser = User.findById(Long.parseLong(userId));
	  }
	  render(user, loggedInUser, reversePosts);
	} 
	
	  public static void newComment(Long postid, Long userID, Long loggedInUserID, String content)
	  {    
	    Logger.info("Post ID = " + postid);
	    Post post = Post.findById(postid);
	    Comment comment = new Comment(content);

	    post.addComment(comment);
	    post.save();
	    show(userID);
	  } 
}

