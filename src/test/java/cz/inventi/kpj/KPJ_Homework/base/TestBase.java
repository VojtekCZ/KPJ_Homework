package cz.inventi.kpj.KPJ_Homework.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static java.util.Objects.isNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestBase {
    private RestTemplate restTemplate;

    @Autowired
    protected ServerPortService serverPortService;

    protected RestTemplate getRestTemplate() {
        if (isNull(restTemplate)) {
            restTemplate = new RestTemplate();
            restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(getServiceUrl()));
        }
        return restTemplate;
    }

    protected String getServiceUrl() {
        return "http://localhost:" + serverPortService.getPort();
    }
}