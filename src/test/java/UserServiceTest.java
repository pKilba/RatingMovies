import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.UserService;
import com.epam.ratingmovies.util.LineHasher;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {
    private static final String TEST_USER_LOGIN = "pavelkilbas";
    private static final String TEST_USER_PASSWORD = "pavelkilbas";


    UserService userService = UserService.getInstance();

    @Test
    public void testCheckUserByLoginAndPassword() {
        LineHasher lineHasher = new LineHasher();
        String hashPass = lineHasher.hashingLine(TEST_USER_PASSWORD);
        Optional<User> user = Optional.empty();
        try {

            user = userService.findUserByLoginAndPassword(TEST_USER_LOGIN, hashPass);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        assertTrue(user.isPresent());
    }


}
