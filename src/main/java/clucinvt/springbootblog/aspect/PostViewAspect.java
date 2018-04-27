/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.aspect;

import clucinvt.springbootblog.domain.BlogPost;
import clucinvt.springbootblog.repository.BlogPostRepository;
import java.util.Date;
import java.util.Optional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Cam Luc
 */
@Aspect
@Configuration
public class PostViewAspect {
    
    @Autowired
    private BlogPostRepository blogPostRepo;
    
    @AfterReturning(value = "execution(* clucinvt.springbootblog.controller.PostController.postDetails(..))")
    public void afterPostView(JoinPoint joinPoint) {
        
        Object[] args = joinPoint.getArgs();
        int postId = (int) args[0];
        
        Optional<BlogPost> oBlogPost = blogPostRepo.findById(postId);
        if (oBlogPost.isPresent()) {
            BlogPost blogPost = oBlogPost.get();
            blogPost.getPostViews().add(new Date());
            blogPostRepo.save(blogPost);
        }
        
        // todo: log if post is not found
    }
}
