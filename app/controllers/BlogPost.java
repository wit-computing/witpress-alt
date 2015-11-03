package controllers;

import models.Post;
import models.User;
import models.Comment;
import play.Logger;
import play.mvc.Controller;

public class BlogPost  extends Controller
{
  public static void show(Long postid)
  {
    Logger.info("Post ID = " + postid);
    Post post = Post.findById(postid);
    render (post);
  }
  
  public static void newComment(Long postid, String content)
  {    
    Logger.info("Post ID = " + postid);
    Post post = Post.findById(postid);
    Comment comment = new Comment(content);

    post.addComment(comment);
    post.save();
    show(postid);
  }
  
  public static void deleteComment(Long postid, Long commentid)
  {    
    Logger.info("Post ID = " + postid);
    Comment comment = Comment.findById(commentid);
    Post post = Post.findById(postid);
    post.comments.remove(comment);
    post.save();
    show(postid);
  }
}