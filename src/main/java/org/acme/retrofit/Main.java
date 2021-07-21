package org.acme.retrofit;
import dto.HttpError;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Tag(name = "comments")
@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
public class Main {
    @Inject
    Controller controller ;

    public Main() { }

    @Operation(summary = "List of Comments", description = "List all comments")
    @APIResponse(responseCode = "200", description = "comments listed", content = @Content(schema = @Schema(implementation = Comment.class, type = SchemaType.ARRAY)))
    @GET
    public List<Comment> getComments() throws IOException {
        return controller.getAPI().getComments().execute().body();
    }

    @Operation(summary = "Comment by id", description = "Get comment by Id")
    @APIResponse(responseCode = "200", description = "Comment retrieved")
    @APIResponse(responseCode = "400", description = "Comment not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @GET
    @Path("/{id}")
    public Comment getComment(@PathParam("id") int id) throws IOException {
        retrofit2.Response<Comment> rs =  controller.getAPI().getCommentById(id).execute();
        if (rs.isSuccessful()==false){
            throw new BadRequestException("sdfsd");
        }
        return rs.body();
    }

    @Operation(summary = "Create Comment", description = "Create new comment")
    @APIResponse(responseCode = "201", description = "comment created")
    @POST
    public Response createComment(@Valid Comment comment) throws IOException {
        Comment createdComment = controller.getAPI().createComment(comment).execute().body();
        return Response.status(201).entity(createdComment).build();
    }

    @Operation(summary = "Delete comment", description = "Delete comment by Id")
    @APIResponse(responseCode = "204", description = "comment deleted")
    @APIResponse(responseCode = "400", description = "comment not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @DELETE
    @Path("/{id}")
    public void deleteComment(@PathParam("id") int id) throws IOException {
        retrofit2.Response rs = controller.getAPI().deleteComment(id).execute();
        if (rs.isSuccessful() == false){
            throw new BadRequestException("xdx");
        }
    }

    @Operation(summary = "Update comment", description = "Update comment by id")
    @APIResponse(responseCode = "200", description = "comment updated")
    @APIResponse(responseCode = "400", description = "comment not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @PUT
    @Path("/{id}")
    public Comment updateComment(@PathParam("id") int id, @Valid Comment comment) throws IOException {
        retrofit2.Response rs = controller.getAPI().updateComment(id, comment).execute();
        if (!rs.isSuccessful()){
            throw new BadRequestException("fgd");
        }
        return (Comment) rs.body();
    }


}
