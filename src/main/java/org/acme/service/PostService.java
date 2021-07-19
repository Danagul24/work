package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.acme.dao.PostDao;
import org.acme.entity.Post;

@ApplicationScoped
public class PostService implements IPostService{

	@Inject
	PostDao dao;
	
	@Override
	public List<Post> posts() {
		return dao.findAll();
	}

	@Override
	public Post create(Post post) {
		return dao.save(post);
	}

	@Override
	public Post get(int id) {
		return dao.findById(id).orElseThrow(() -> new NotFoundException("Post №" + id + " doesnt exist" ));
	}

	@Override
	public Post update(int id, Post post) {
		Post p = get(id);
		p.setName(post.getName());
		p.setTitle(post.getTitle());
		return dao.save(p);
	}

	@Override
	public void delete(int id) {
		if (dao.existsById(id)) {
			dao.deleteById(id);
		}else {
			throw new NotFoundException("Post №" + id + " doesnt exist" );
		}
		
	}
	
}
