import java.util.ArrayList;
import java.util.List;

import models.Blog;
import models.Comment;
import models.Post;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class BlogTest  extends UnitTest
{
  private User bob;
  
  @Before
  public void setup()
  {
    bob = new User("bob", "jones", "bob@jones.com", "secret");
    Blog blog = new Blog ("Test Blog");
    blog.save();
    bob.blogs.add(blog);
    bob.save();
  }
  
  @After
  public void teardown()
  {
    bob.delete();
  }
  
  @Test
  public void testCreatePost()
  {
    Blog blog = bob.blogs.get(0);
    Post aPost = new Post ("Post Title", "This is the post content");
    bob.blogs.get(0).addPost(aPost);
    bob.save();
    
    User testUser = User.findByEmail("bob@jones.com");
    Blog testBlog = testUser.blogs.get(0);
    assertNotNull (testUser);
    assertNotNull (testBlog);
    
    assertEquals (1, testBlog.posts.size());
    
    Post post = testBlog.posts.get(0);
    assertEquals(post.title, "Post Title");
    assertEquals(post.content, "This is the post content");
  }
  
  
  @Test
  public void testCreateMultiplePosts()
  {
    Blog blog = bob.blogs.get(0);
    Post post1 = new Post ("Post Title 1", "This is the first post content");
    Post post2 = new Post ("Post Title 2", "This is the second post content");
    blog.addPost(post1);
    blog.addPost(post2);
    bob.save();
    
    User user = User.findByEmail("bob@jones.com");
    List<Post> posts = user.blogs.get(0).posts;
    assertEquals(2, posts.size());
    Post posta = posts.get(0);
    assertEquals(posta.title, "Post Title 1");
    assertEquals(posta.content, "This is the first post content");
    
    Post postb = posts.get(1);
    assertEquals(postb.title, "Post Title 2");
    assertEquals(postb.content, "This is the second post content");   
   }
  
 
  @Test
  public void testDeletePost()
  {
    Blog blog = bob.blogs.get(0);
    Post post1 = new Post ("Post Title 1", "This is the first post content");
    Post post2 = new Post ("Post Title 2", "This is the second post content");
    blog.addPost(post1);
    blog.addPost(post2);
    bob.save();
    
    User user = User.findByEmail("bob@jones.com");
    
    Post post = user.blogs.get(0).posts.get(0);
    
    user.blogs.get(0).posts.remove(post);
    user.save();
    post.delete();
    
    User anotherUser = User.findByEmail("bob@jones.com");
    assertEquals(1, anotherUser.blogs.get(0).posts.size());   
   }
  
  
  @Test
  public void testCreatePostWithComment()
  {
    Blog blog = bob.blogs.get(0);
    Post aPost = new Post ("Post Title", "This is the post content");
    blog.addPost(aPost);
    
    Comment comment = new Comment ("This is a comment");
    aPost.addComment(comment);
    bob.save();

    
    User user = User.findByEmail("bob@jones.com");
    List<Post> posts = user.blogs.get(0).posts;
    Post thePost = posts.get(0);
    assertEquals(thePost.comments.size(), 1);
    Comment theComment = thePost.comments.get(0);
    assertEquals(theComment.content, "This is a comment");
  }
  
}
