package net.bgp.ws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;

public enum JsonObjectMapper {
    INSTANCE;

    private final ObjectMapper mapper;

    JsonObjectMapper() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(FAIL_ON_NULL_FOR_PRIMITIVES, true);
        mapper.configure(WRITE_BIGDECIMAL_AS_PLAIN, true);
        //mapper.registerModule()

        mapper.setAnnotationIntrospector(AnnotationIntrospector.pair(
                new JacksonAnnotationIntrospector(),
                new JaxbAnnotationIntrospector(mapper.getTypeFactory())
        ));
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

}
