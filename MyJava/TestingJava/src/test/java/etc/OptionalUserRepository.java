package etc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalUserRepository {
    private final List<User> users = Arrays.asList(new User("1", "John"), new User("2", "Maria"));

    public Optional<User> findById(String id) {
        for ( User u : users) {
            if (u.getId().equals(id)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}
