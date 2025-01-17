package store;

public final class Bgp16TypeStore extends TypeStore {

    public static final Bgp16TypeStore INSTANCE = new Bgp16TypeStore();

    private Bgp16TypeStore() {
        super("RequestOne", "RequestOne");
    }
}
