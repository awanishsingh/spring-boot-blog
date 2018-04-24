/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.repository;

import clucinvt.springbootblog.domain.BlogComment;
import clucinvt.springbootblog.repository.projection.BlogCommentDescriptor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Cam Luc
 */
public interface BlogCommentRepository extends JpaRepository<BlogComment, Integer> {

    @Query("SELECT c.id as id, c.blogPost.id as postId, c.commenterName as commenterName, c.title as title, c.createDate as createDate, c.approveDate as approveDate"
            + " FROM BlogComment c ORDER BY createDate DESC")
    List<BlogCommentDescriptor> findAllCommentDescriptors();

    @Query("SELECT c FROM BlogComment c WHERE c.blogPost.id = :postId AND c.approveDate IS NOT NULL ORDER BY createDate DESC")
    List<BlogComment> findApprovedCommentsByPost(@Param("postId") int postId);
}
