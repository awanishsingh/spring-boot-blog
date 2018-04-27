/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.repository;

import clucinvt.springbootblog.domain.BlogPost;
import clucinvt.springbootblog.repository.projection.BlogPostDescriptor;
import clucinvt.springbootblog.repository.projection.BlogPostTitle;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cam Luc
 */
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

    @Query("SELECT p FROM BlogPost p WHERE publishDate IS NOT NULL ORDER BY publishDate DESC")
    List<BlogPost> findPublishedPosts();

    @Query("SELECT p FROM BlogPost p WHERE publishDate IS NOT NULL ORDER BY publishDate DESC")
    Page<BlogPost> findPublishedPosts(Pageable pageable);
    
    @Query("SELECT p.id as id, p.title as title FROM BlogPost p WHERE publishDate IS NOT NULL ORDER BY publishDate DESC")
    Page<BlogPostTitle> findPublishedPostTitles(Pageable pageable);
    
    @Query("SELECT p.id as id, p.title as title, p.author.name as authorName, p.createDate as createDate, p.publishDate as publishDate, count(v) as viewCount"
            + " FROM BlogPost p LEFT JOIN p.postViews v GROUP BY p ORDER BY createDate DESC")
    List<BlogPostDescriptor> findAllPostDescriptors();
}
