package infra_practice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class TestControllerTest {

    @Mock
    private TestRepo testRepo;

    @InjectMocks
    private TestController testController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @org.junit.jupiter.api.Test
    public void testGetMethodName() {
        // given
        List<Test> mockTests = Arrays.asList(
                new Test(1, "Test1"),
                new Test(2, "Test2")
        );
        when(testRepo.findAll()).thenReturn(mockTests);

        // when
        List<Test> result = testController.getMethodName();

        // then
        assertEquals(2, result.size());
        assertEquals("Test1", result.get(0).getCol1());
        verify(testRepo, times(1)).findAll();
    }

    @org.junit.jupiter.api.Test
    public void testGetGo() {
        // given
        Test test = new Test(113, "goo");
        when(testRepo.save(any(Test.class))).thenReturn(test);

        // when
        ResponseEntity<Void> response = testController.getGo();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(testRepo, times(1)).save(any(Test.class));
    }

    @org.junit.jupiter.api.Test
    public void testTestName() {
        // when
        String result = testController.testName();

        // then
        assertEquals("gg", result);
    }
}