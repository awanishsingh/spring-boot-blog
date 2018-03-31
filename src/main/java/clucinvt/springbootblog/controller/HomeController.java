/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.controller;

import clucinvt.springbootblog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cam Luc
 */
@Controller
public class HomeController {
    
    @Autowired
    private BlogPostRepository blogPostRepo;
    
    @RequestMapping("/")
    public String index(Model model, Pageable pageable) {
        model.addAttribute("posts", blogPostRepo.findPublishedPosts(pageable).getContent());
        Pageable titlePageable = PageRequest.of(0, 10);
        model.addAttribute("titles", blogPostRepo.findPublishedPostTitles(titlePageable).getContent());
        return "index";
    }
}
