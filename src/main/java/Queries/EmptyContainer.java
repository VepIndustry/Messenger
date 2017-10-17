package Queries;

public class EmptyContainer extends Container {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public Integer getInteger(String name) {
        return 0;
    }

    @Override
    public String getString(String name) {
        return "";
    }

    @Override
    public Double getDouble(String name) {
        return 0.0;
    }
}
