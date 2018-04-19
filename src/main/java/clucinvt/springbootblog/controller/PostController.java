/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.controller;

import clucinvt.springbootblog.controller.exception.NotFoundException;
import clucinvt.springbootblog.domain.BlogComment;
import clucinvt.springbootblog.domain.BlogPost;
import clucinvt.springbootblog.repository.BlogCommentRepository;
import clucinvt.springbootblog.repository.BlogPostRepository;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Cam Luc
 */
@Controller()
@RequestMapping("/post")
public class PostController {

    @Autowired
    private BlogPostRepository blogPostRepo;
    @Autowired
    private BlogCommentRepository blogCommentRepo;

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public String detail(@PathVariable("id") int id, Model model, BlogComment blogComment) throws NotFoundException {

        loadDetailData(id, model);
        model.addAttribute("blogComment", blogComment);

        return "post";
    }

    @RequestMapping(value = "/{id}/comment", method = {RequestMethod.POST})
    public String comment(@PathVariable("id") int id, @Valid BlogComment blogComment,
            BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws NotFoundException {

        if (bindingResult.hasErrors()) {
            loadDetailData(id, model);
            return "post";
        }

        Optional<BlogPost> post = blogPostRepo.findById(id);
        if (!post.isPresent()) {
            throw new NotFoundException();
        }
        blogComment.setBlogPost(post.get());
        blogComment.setCreateDate(new Date());
        blogCommentRepo.save(blogComment);

        redirectAttributes.addFlashAttribute("message", "Your comment was submitted");

        return "redirect:/post/" + id;
    }

    private void loadDetailData(int id, Model model) throws NotFoundException {
        Optional<BlogPost> post = blogPostRepo.findById(id);
        if (!post.isPresent()) {
            throw new NotFoundException();
        }
        model.addAttribute("post", post.get());

        Pageable titlePageable = PageRequest.of(0, 10);
        model.addAttribute("titles", blogPostRepo.findPublishedPostTitles(titlePageable).getContent());
    }
}
