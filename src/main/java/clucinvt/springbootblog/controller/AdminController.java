/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.controller;

import clucinvt.springbootblog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cam Luc
 */
@Controller()
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BlogPostRepository blogPostRepo;

    @RequestMapping(value = {"", "/"})
    public String index(Model model) {
        model.addAttribute("posts", blogPostRepo.findAllPostDescriptors());
        return "admin/index";
    }
}
