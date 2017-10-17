package Queries;

public class Factory {

    public static Container parse(String inputValue) {
        try {
            return new Query(inputValue);
        } catch (Exception e) {
            e.printStackTrace();
            return new EmptyContainer();
        }
    }

}
