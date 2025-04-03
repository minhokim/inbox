package kr.re.bgp.jpademo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;

public enum JsonObjectMapper {
    INSTANCE;

    private final ObjectMapper mapper;

    JsonObjectMapper() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // OCPP messages contain some mandatory primitive fields (like transactionId), that are not allowed
        // to be null. any misinterpretation/mapping of these fields like "null -> 0" is a mistake.
        //
        // true story: while testing with abusive-charge-point, it sends stopTransactions where transactionId=null
        // in communication flows, where a startTransaction before causes an Exception and we cannot send a regular
        // response with a transactionId, but an error message. if we do not fail early, it will fail at the database
        // level which we want to prevent.
        mapper.configure(FAIL_ON_NULL_FOR_PRIMITIVES, true);

        mapper.configure(WRITE_BIGDECIMAL_AS_PLAIN, true);

        mapper.registerModule(new CustomStringModule());

    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
