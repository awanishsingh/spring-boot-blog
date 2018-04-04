/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.controller;

import clucinvt.springbootblog.repository.BlogPostRepository;
import java.security.Principal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cam Luc
 */
@Controller
public class HomeController {

    @Autowired
    private BlogPostRepository blogPostRepo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Pageable pageable) {
        model.addAttribute("posts", blogPostRepo.findPublishedPosts(pageable).getContent());
        Pageable titlePageable = PageRequest.of(0, 10);
        model.addAttribute("titles", blogPostRepo.findPublishedPostTitles(titlePageable).getContent());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Principal principal) {
        if (principal != null) {
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.stream().anyMatch(a -> "admin".equals(a.getAuthority()))) {
                return "redirect:/admin";
            }
        }
        return "login";
    }
}
