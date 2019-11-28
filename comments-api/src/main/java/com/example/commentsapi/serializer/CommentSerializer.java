package com.example.commentsapi.serializer;

import com.example.commentsapi.model.Comment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CommentSerializer extends StdSerializer<Comment> {

    public CommentSerializer() {
        this(null);
    }

    public CommentSerializer(Class<Comment> t) {
        super(t);
    }

    @Override
    public void serialize(Comment comment, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", comment.getId());
        jgen.writeNumberField("postId", comment.getPostId());
        jgen.writeStringField("text", comment.getText());
            jgen.writeObjectFieldStart("user");
            jgen.writeObjectField("username", comment.getUsername());
            jgen.writeEndObject();
        jgen.writeEndObject();
    }
}