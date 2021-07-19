package org.acme.dao;

import java.sql.*;
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
@ApplicationScoped
public class PostsDao {
	
	@Inject
	AgroalDataSource defaultDataSource;

	
	private Logger log = LoggerFactory.getLogger(PostsDao.class);
	
	public PostsDao(){

	}
	/*
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
	*/


	public List<Post> findPosts()  {
		Connection conn = null;
		List<Post> posts_temp = new ArrayList<>();
		try {
			conn = defaultDataSource.getConnection();   // access to DB
			var stmt = conn.createStatement();
			String selectPosts = "SELECT * FROM post";

			try {
				ResultSet rs = stmt.executeQuery(selectPosts);
				while (rs.next()) {
					Post p1 = new Post();
					p1.setId(rs.getInt("id"));
					p1.setName(rs.getString("name"));
					p1.setTitle(rs.getString("title"));
					posts_temp.add(p1);

				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return posts_temp;
	}

	public Post createPost(Post post){
		Connection conn = null;
		try {
			conn = defaultDataSource.getConnection();   // access to DB
			String insertPost = "INSERT INTO post(id, name, title)" +
					"VALUES (?,?,?)";

			try(PreparedStatement pstmt = conn.prepareStatement(insertPost)){
				pstmt.setInt(1, post.getId());
				pstmt.setString(2, post.getName());
				pstmt.setString(3, post.getTitle());

				pstmt.execute();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return post;
	}

	public Post getPostById(int idPost){
		Connection conn = null;
		Post post = new Post();
		try {
			conn = defaultDataSource.getConnection();   // access toDB
			String selectPost = "SELECT * from post WHERE id = ?";

			try{
				//Statement stmt = conn.createStatement();
				//ResultSet rs = stmt.executeQuery(selectPost);
				PreparedStatement pstmt = conn.prepareStatement(selectPost);
				pstmt.setInt(1, idPost);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next() == false){
					throw new BadRequestException("asd");
				}
				post.setId(rs.getInt("id"));
				post.setName(rs.getString("name"));
				post.setTitle(rs.getString("title"));
			}catch (SQLException throwables) {
				throwables.printStackTrace();
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return post;
	}

	public Post updatePost(int id, Post post){
		Connection conn = null;
		Post post1 = new Post();
		try {
			conn = defaultDataSource.getConnection();   // access to DB
			String updateNameSql = "UPDATE post SET name = ? WHERE id = ?";

			try {
				PreparedStatement pstmt = conn.prepareStatement(updateNameSql);
				pstmt.setString(1, post.getName());
				pstmt.setInt(2, id);
				post1.setName(post.getName());
				int affectedRows = pstmt.executeUpdate();
				if (affectedRows == 0){
					throw new BadRequestException("dsfs");
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return post1;
	}

	public void deletePost(int idPost){
		Connection conn = null;
		Post post;
		try {
			conn = defaultDataSource.getConnection();   // access to DB
			String deletePostSql = "DELETE FROM post WHERE id = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(deletePostSql);
				pstmt.setInt(1, idPost);
				int affectedRows = pstmt.executeUpdate();
				if (affectedRows == 0){
					throw new BadRequestException("dfs");
				}

			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


}
