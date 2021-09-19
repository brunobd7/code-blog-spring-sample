package com.spring.codeblog.controller;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    //MAPPING ACCESS
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts(){

        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = postService.listAll();

        mv.addObject("posts",posts);

        return mv;

    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable long id){

        ModelAndView mv = new ModelAndView("postDetails");
        Post post = postService.findPostById(id);

        mv.addObject("post",post);

        return mv;

    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm(){
        return "postForm";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String getSavePost(@Valid Post post , BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()) {
            attributes.addFlashAttribute("message","Verify required fields !");
            return "redirect:/newpost";
        }

        post.setPublishDate(LocalDate.now());
        postService.savePost(post);

        return "redirect:/posts";
    }



}
