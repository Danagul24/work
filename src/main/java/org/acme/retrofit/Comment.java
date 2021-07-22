package org.acme.retrofit;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "comment")
@XmlType(propOrder = {"id", "name", "body"})
@Entity
@Schema(name = "Comment", description = "Comment with attributes")
public class Comment {
    @Schema(name = "id of post", description = "Id of post connected with Comment")
    private int postId;
    @Id
    @SequenceGenerator(name = "giftSeq", sequenceName = "gift_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "giftSeq", strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Id of Comment", example = "1")
    private int id;

    @NotBlank(message = "Name should not be blank")
    @Schema(name = "name", description = "Name of Comment", example = "default_name")
    private String name;


    @NotBlank(message = "Email should not be blank")
    @Schema(name = "email", description = "Email of Comment", example = "default_email")
    private String email;

    @NotBlank(message = "Body should not be blank")
    @Schema(name = "body", description = "Body of Comment", example = "default_body")
    private String body;
    {
        id = id + 1;
    }
    public Comment(){}
    public Comment(int postId, String name, String email, String body){
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }
    @XmlAttribute
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @XmlTransient
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    @XmlElement(name = "title")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
