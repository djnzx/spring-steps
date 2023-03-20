package app.client;

import app.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "personClient", url = "http://localhost:8081")
public interface PersonClient {

    @RequestMapping(method = RequestMethod.GET, value = "/person", produces = "application/json")
    Person getById(@RequestParam("id") Integer id);

}
