/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.controller;

import clucinvt.springbootblog.domain.BlogComment;
import clucinvt.springbootblog.domain.BlogPost;
import clucinvt.springbootblog.domain.User;
import clucinvt.springbootblog.repository.BlogCommentRepository;
import clucinvt.springbootblog.repository.BlogPostRepository;
import clucinvt.springbootblog.repository.UserRepository;
import java.security.Principal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Cam Luc
 */
@Controller()
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BlogPostRepository blogPostRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BlogCommentRepository blogCommentRepo;

    @RequestMapping(value = {"", "/"})
    public String index(Model model) {
        model.addAttribute("posts", blogPostRepo.findAllPostDescriptors());
        return "admin/index";
    }

    @RequestMapping(value = "/write", method = {RequestMethod.GET})
    public String write(Model model) {
        BlogPost newPost = new BlogPost();
        model.addAttribute("newpost", newPost);
        return "admin/write";
    }

    @RequestMapping(value = "/write/{id}", method = {RequestMethod.GET})
    public String write(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", blogPostRepo.findById(id).get());
        return "admin/write";
    }

    @RequestMapping(value = "/write", method = {RequestMethod.POST})
    public String write(@ModelAttribute BlogPost post, BindingResult errors, Model model, Principal principal) {
        User author = userRepo.findByUsername(principal.getName());
        post.setAuthor(author);
        if (post.getId() == 0) { // it is a new post
            post.setCreateDate(new Date());
            blogPostRepo.save(post);
            return "redirect:/admin/";
        } else { // it is an existing post
            BlogPost existingPost = blogPostRepo.findById(post.getId()).get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            blogPostRepo.save(existingPost);
            return "redirect:/admin/";
        }
    }

    @RequestMapping(value = "publish", method = {RequestMethod.POST})
    public String publishPost(@RequestParam("id") int id) {
        BlogPost post = blogPostRepo.findById(id).get();
        post.setPublishDate(new Date());
        blogPostRepo.save(post);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "unpublish", method = {RequestMethod.POST})
    public String unpublishPost(@RequestParam("id") int id) {
        BlogPost post = blogPostRepo.findById(id).get();
        post.setPublishDate(null);
        blogPostRepo.save(post);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "comments", method = {RequestMethod.GET})
    public String comments(Model model) {
        model.addAttribute("comments", blogCommentRepo.findAllCommentDescriptors());
        return "admin/comments";
    }

    @RequestMapping(value = "approve", method = {RequestMethod.POST})
    public String approveComment(@RequestParam("id") int id) {
        BlogComment comment = blogCommentRepo.findById(id).get();
        comment.setApproveDate(new Date());
        blogCommentRepo.save(comment);
        return "redirect:/admin/comments";
    }

    @RequestMapping(value = "disapprove", method = {RequestMethod.POST})
    public String disapproveComment(@RequestParam("id") int id) {
        BlogComment comment = blogCommentRepo.findById(id).get();
        comment.setApproveDate(null);
        blogCommentRepo.save(comment);
        return "redirect:/admin/comments";
    }
}
