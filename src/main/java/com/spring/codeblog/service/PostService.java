package com.spring.codeblog.service;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post savePost(Post savedPost){

        return postRepository.save(savedPost);

    }

    public List<Post> listAll(){

        return postRepository.findAll();
    }

    public Post findPostById(Long id){
        return postRepository.findById(id).orElse(new Post());
    }

}
