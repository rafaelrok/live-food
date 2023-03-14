package br.com.livefood.payments.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;

@FeignClient(name = "ms-order", url = "http://localhost:8002")
public interface OrderClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/orders/{id}/paid")
    void approvePayment(@PathVariable @NotNull Long id);
}
