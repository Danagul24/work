package org.acme.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Schema(name = "Post", description = "Post with name and title")
public class Post {
	@Id
	@SequenceGenerator(name = "giftSeq", sequenceName = "gift_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "giftSeq", strategy = GenerationType.IDENTITY)
	@Schema(name = "id", description = "Unique id of post", example = "1")
	int id = 0;

	@NotBlank(message="Name may not be blank")
	@Schema(name = "name", description = "Name of post", example = "default name")
	String name;

	@NotBlank(message="Title may not be blank")
	@Schema(name = "title", description = "Title of post", example = "default title")
	String title;
	{
		id=id+1;
	}
	public Post(){		
	}
	public Post(String name, String title) {
		this.name = name;
		this.title = title;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public String getTitle() {
		return this.title;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}