package com.msrazavi.test.grpc.client;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.msrazavi.test.grpc.common.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryClient client;

    @GetMapping(value = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody String s) throws InvalidProtocolBufferException {
        Country country = client.save(s);
        return JsonFormat.printer().print(country);
    }
}
