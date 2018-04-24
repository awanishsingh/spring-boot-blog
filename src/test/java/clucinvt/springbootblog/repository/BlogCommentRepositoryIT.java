/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.repository;

import clucinvt.springbootblog.domain.BlogComment;
import clucinvt.springbootblog.domain.BlogPost;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Cam Luc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogCommentRepositoryIT {

    @Autowired
    private BlogCommentRepository blogCommentRepo;
    @Autowired
    private BlogPostRepository blogPostRepo;

    @Test
    public void canCrud() {
        Assert.assertNotNull(blogCommentRepo);

        long count = blogCommentRepo.count();

        BlogComment c = new BlogComment();
        BlogPost p = blogPostRepo.findById(1).get();
        c.setBlogPost(p);
        c.setCommenterName("test");
        c.setTitle("test");
        c.setBody("this is a test comment.");
        c.setCreateDate(new Date());
        blogCommentRepo.save(c);
        Assert.assertEquals(count + 1, blogCommentRepo.count());
    }

    @Test
    public void canFindApprovedComments() {
        List<BlogComment> comments = blogCommentRepo.findApprovedCommentsByPost(1);

        Assert.assertFalse(comments.isEmpty());
    }
}
