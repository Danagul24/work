package org.acme.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import dto.HttpError;
import org.acme.entity.Post;
import org.acme.service.IPostService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

@Tag(name = "posts")
@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

	@Inject 
	IPostService postService;
	
	public GreetingResource() {}
    
	@Inject
	Validator validator;

	@Operation(summary = "List of posts", description = "List all posts")
    @APIResponse(responseCode = "200", description = "posts listed", content = @Content(schema = @Schema(implementation = Post.class, type = SchemaType.ARRAY)))
	@GET
	public List<Post> getPosts() throws SQLException {
        //return dao.getAll();
		return postService.posts();
    }

    @Operation(summary = "Get post", description = "Get post by id")
    @APIResponse(responseCode = "200", description = "Post retrieved")
    @APIResponse(responseCode = "400", description = "Post not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @GET
    @Path("/{id}")
    public Post getPost(@PathParam("id") int id) {
    	return postService.get(id);
    }

    @Operation(summary = "Create post", description = "Create new post")
    @APIResponse(responseCode = "201", description = "Post created", content = @Content(schema = @Schema(implementation = Post.class)))
    @Consumes(MediaType.APPLICATION_JSON)
    @POST  
    public Response addPost(@Valid Post post) {
    	//return dao.create(post);
    	Post createsPost = postService.create(post);
    	return Response.status(201).entity(createsPost).build();
    }

    @Operation(summary = "Delete post", description = "Delete post with id")
    @APIResponse(responseCode = "204", description = "Post deleted")
    @APIResponse(responseCode = "400", description = "Post not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @DELETE
    @Path("/{id}")
    public void deletePost(@PathParam("id") int id) {
    	//return dao.delete(id);
    	postService.delete(id);
    }

    @Operation(summary = "Update post", description = "Update post with id")
    //
    @APIResponse(responseCode = "200", description = "Post updated")
    @APIResponse(responseCode = "400", description = "Post not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Post updatePost(@PathParam("id") int id, @Valid Post post) {
    	//return dao.update(id, post);
    	return postService.update(id, post);
    }
   
    
    
   
}