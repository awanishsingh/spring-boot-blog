/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.controller;

import clucinvt.springbootblog.controller.exception.NotFoundException;
import clucinvt.springbootblog.domain.BlogPost;
import clucinvt.springbootblog.repository.BlogPostRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cam Luc
 */
@Controller
public class PostController {

    @Autowired
    private BlogPostRepository blogPostRepo;

    @RequestMapping(value = "/post/{id}", method = {RequestMethod.GET})
    public String detail(@PathVariable("id") int id, Model model) throws NotFoundException {
        Optional<BlogPost> post = blogPostRepo.findById(id);
        if (!post.isPresent()) {
            throw new NotFoundException();
        }
        model.addAttribute("post", post.get());
        Pageable titlePageable = PageRequest.of(0, 10);
        model.addAttribute("titles", blogPostRepo.findPublishedPostTitles(titlePageable).getContent());
        return "post";
    }
}
