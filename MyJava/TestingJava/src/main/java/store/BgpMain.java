package store;

public class BgpMain {

    public static void main(String[] args) {
        Deserializer deserializer1 = new Deserializer(Bgp16TypeStore.INSTANCE);
        deserializer1.printTypeStore();

        Deserializer deserializer2 = new Deserializer(Bgp16TypeStore.INSTANCE);
        deserializer2.printTypeStore();
    }

}
