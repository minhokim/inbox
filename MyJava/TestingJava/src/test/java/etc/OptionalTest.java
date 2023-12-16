package etc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {

    @Test
    public void optionalTest() {
        OptionalUserRepository repository = new OptionalUserRepository();
        String nonExistentUserId = "3";

        String userName = repository.findById(nonExistentUserId).orElse(new User("3", "Jane")).getName();
        assertThat("Jane".equals(userName));
    }


}
