package com.example.postsapi.serializer;

import com.example.postsapi.model.Post;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PostSerializer extends StdSerializer<Post> {

    public PostSerializer() {
        this(null);
    }

    public PostSerializer(Class<Post> t) {
        super(t);
    }

    @Override
    public void serialize(
            Post post, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", post.getId());
        jgen.writeStringField("title", post.getTitle());
        jgen.writeStringField("description", post.getDescription());
            jgen.writeObjectFieldStart("user");
            jgen.writeObjectField("username", post.getUsername());
            jgen.writeEndObject();
        jgen.writeEndObject();
    }
}