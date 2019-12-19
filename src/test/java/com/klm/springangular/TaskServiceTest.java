package com.klm.springangular;

import com.klm.springangular.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskServiceTest {
    @Autowired
    TaskService taskService;
    @Test
    public void dataAggregation() throws Exception{
        taskService.dataAggregation("AMS","BBA");
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(new GetAirportTask("AMS",null));

    }


}
