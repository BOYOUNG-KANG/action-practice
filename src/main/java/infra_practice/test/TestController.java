package infra_practice.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    private TestRepo testRepo;

    @GetMapping("/aa")
    public String testName() {
        return "gg";
    }

    @GetMapping("/test")
    public List<Test> getMethodName() {
        List<Test> tests = testRepo.findAll();
        for (Test t : tests) {
            log.info(t.toString());
        }
        return tests;
    }

    @GetMapping("/go")
    public ResponseEntity<Void> getGo() {
        Test test = new Test(113, "goo");
        testRepo.save(test);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
