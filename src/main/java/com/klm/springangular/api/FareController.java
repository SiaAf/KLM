package nl.klm.demo.api;

import nl.klm.demo.dto.FareDto;
import nl.klm.demo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FareController {

    @Autowired
    TaskService taskService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FareController.class);

    @GetMapping
    @RequestMapping(value = "/fares/{OrigCode}/{desCode}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FareDto> getAirport(@PathVariable String OrigCode, @PathVariable String desCode) {
        LOGGER.info("get the fare for OrigCode:" + OrigCode + "and destCode of " + desCode);
        try {
            FareDto fareDto = taskService.dataAggregation(OrigCode, desCode);
            return new ResponseEntity<>(fareDto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return new ResponseEntity("bad request", HttpStatus.BAD_REQUEST);
        }
    }
}
