package com.klm.springangular;

import com.klm.springangular.dto.FareDto;
import com.klm.springangular.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskServiceTest {
    @Autowired
    TaskService taskService;

    @Test
    public void dataAggregation() throws Exception {
        FareDto fareDto = taskService.dataAggregation("AMS", "BBA");
        assertTrue(fareDto.getCurrency().equals("EUR"));
    }
}
