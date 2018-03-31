/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.repository;

import clucinvt.springbootblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cam Luc
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);
}
