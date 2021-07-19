package org.acme.service;

import java.sql.SQLException;
import java.util.List;

import org.acme.entity.Post;

public interface IPostService {
	List<Post> posts() throws SQLException;
	
	Post create(Post post);
	
	Post get(int id);
	
	Post update(int id, Post post);
	
	void delete(int id);
	
}
