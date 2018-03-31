/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.repository;

import clucinvt.springbootblog.domain.BlogPost;
import clucinvt.springbootblog.domain.User;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Cam Luc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogPostRepositoryTest {

    @Autowired
    private BlogPostRepository blogPostRepo;
    @Autowired
    private UserRepository userRepo;

    @Test
    public void canSavePostViews() {
        BlogPost post = new BlogPost();
        post.setTitle("test");
        post.setBody("test");
        post.setCreateDate(new Date());
        post.setAuthor(getAdmin());

        Assert.assertNull(post.getPostViews());

        post.setPostViews(new ArrayList<>());
        post.getPostViews().add(new Date());

        blogPostRepo.save(post);
        Assert.assertTrue(post.getId() > 0);
    }

    @Test
    public void canFindPublishedPosts() {
        BlogPost post = new BlogPost();
        post.setTitle("unpublished");
        post.setBody("unpublished");
        post.setCreateDate(new Date());
        post.setAuthor(getAdmin());

        blogPostRepo.save(post);
        Assert.assertNotEquals(blogPostRepo.count(), blogPostRepo.findPublishedPosts().size());

        Pageable pageable = PageRequest.of(0, 1);
        Assert.assertEquals(1, blogPostRepo.findPublishedPosts(pageable).getContent().size());
        
        Assert.assertEquals(2, blogPostRepo.findPublishedPostTitles(pageable).getContent().get(0).getId());
    }

    private User getAdmin() {
        return userRepo.findByUsername("admin");
    }
}
