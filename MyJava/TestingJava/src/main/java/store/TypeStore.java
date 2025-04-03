package store;

public class TypeStore {

    public TypeStore(String request, String response) {
        requestMap(request);
        responseMap(response);
    }

    private void requestMap(String request) {
        System.out.println("requestMap: " + request);
    }

    private void responseMap(String response) {
        System.out.println("responseMap: " + response);
    }

}
