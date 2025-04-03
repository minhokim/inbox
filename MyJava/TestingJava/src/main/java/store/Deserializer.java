package store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
public class Deserializer {
    private final TypeStore typeStore;

    public void printTypeStore() {
        System.out.println(this.typeStore);
    }
}
