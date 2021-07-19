package org.acme.service;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.acme.dao.PostsDao;
import org.acme.entity.Post;

@ApplicationScoped
public class PostService implements IPostService{

	@Inject
	PostsDao dao;
	
	@Override
	public List<Post> posts() throws SQLException {   //from db
		return dao.findPosts();
	}

	@Override
	public Post create(Post post) {
		return dao.createPost(post);
	}

	@Override
	public Post get(int id) {
		//return dao.findById(id).orElseThrow(() -> new NotFoundException("Post №" + id + " doesnt exist" ));
		return dao.getPostById(id);
	}

	@Override
	public Post update(int id, Post post) {
		/*Post p = get(id);
		p.setName(post.getName());
		p.setTitle(post.getTitle());*/
		return dao.updatePost(id, post);
	}

	@Override
	public void delete(int id) {
		/*if (dao.existsById(id)) {
			dao.deleteById(id);
		}else {
			throw new NotFoundException("Post №" + id + " doesnt exist" );
		}
		*/
		dao.deletePost(id);
	}

}
