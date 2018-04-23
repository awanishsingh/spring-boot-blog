/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.repository;

import clucinvt.springbootblog.domain.User;
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
public class UserRepositoryIT {
    
    @Autowired
    private UserRepository userRepo;
    
    @Test
    public void canCrud() {
        Assert.assertNotNull(userRepo);
        
        Assert.assertEquals(1, userRepo.count());
        
        User u = new User();
        u.setName("test");
        u.setUsername("test");
        u.setPassword("test");
        u.setEmail("test@gmail.com");
        userRepo.save(u);        
        Assert.assertEquals(2, userRepo.count());
    }
    
}
