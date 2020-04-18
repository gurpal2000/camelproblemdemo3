package com.example.camelproblemdemo3.camel;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends EndpointRouteBuilder {

  @Override
  public void configure() throws Exception {
    from("direct:test")
      .to(kafka("my-topic")
        .clientId("my-clientid")
        .brokers("proxmox:9092")
        .enableIdempotence(true)
        .retries(10_000_000)
        .requestRequiredAcks("all")
        .maxInFlightRequest(1)
        .additionalProperties("transactional.id=ABC")
      )
    ;
  }
}
