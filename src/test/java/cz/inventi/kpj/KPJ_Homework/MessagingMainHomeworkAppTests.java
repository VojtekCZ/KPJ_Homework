package cz.inventi.kpj.KPJ_Homework;

import cz.inventi.kpj.KPJ_Homework.base.TestBase;
import cz.inventi.kpj.KPJ_Homework.dto.MessageDto;
import cz.inventi.kpj.KPJ_Homework.service.ServiceRegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

import java.util.List;

import static cz.inventi.kpj.KPJ_Homework.service.MessageService.getMessageName;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

class MessagingMainHomeworkAppTests extends TestBase {

    @Value("${self.registration.message.content}")
    private String content;

    @Autowired
    private ServiceRegistrationServiceImpl serviceRegistrationService;

    @Test
    public void thisServiceSuccessfullyRegisteredTest() {
        MessageDto service = getRestTemplate().getForObject("/services/{name}", MessageDto.class, getMessageName(content));

        if (isNull(service)) {
            throw new AssertionError(format("It is expected that the service [%s] is registered but it is not.", content));
        }
    }

    @Test
    public void serviceIsRegisteredOnlyOnceTest() {
        getRestTemplate().exchange("/services/register", HttpMethod.POST, null, String.class);

        List<MessageDto> services = asList(requireNonNull(getRestTemplate().getForObject("/services", MessageDto[].class)));

        if (services.size() > 1) {
            throw new AssertionError(format("It is expected that the service [%s] is registered only once but it is present multiple times [%s] in the registry.",
                    content, services.size()));
        }
    }
}