package org.acme.dao;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import org.acme.entity.Post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.agroal.api.AgroalDataSource;


// bean
//
//@ApplicationScoped
public class PostsDao {
	
	@Inject
	AgroalDataSource defaultDataSource;
	
	public List<Post> posts = new ArrayList<>();
	
	private Logger log = LoggerFactory.getLogger(PostsDao.class);
	
	public PostsDao(){
		posts.add(new Post("n1", "t1"));
		posts.add(new Post("n2", "t2"));
	}
	
	public List<Post> getAll() {
		return posts;
	}
	
	public Post get(int id) {
		if (id>=posts.size()) {
			System.out.println("error");
			log.error("error");
			throw new BadRequestException("asd");
		}
    	
        return posts.get(id);
	}
	
	public Post create(Post post) {
		posts.add(post);
    	return post;
	}
	
	public Post delete(int id) {
		if (id>=posts.size())
    		throw new BadRequestException("asd");
    	
    	return posts.remove(id);
	}
	
	public Post update(int id, Post post) {
		if (id>=posts.size())
    		throw new BadRequestException("asd");
    	Post p = posts.get(id);
    	p.setName(post.getName());
    	p.setTitle(post.getTitle());
    	return p;
	}
}
