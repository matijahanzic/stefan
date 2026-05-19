package stefanpresentationlayer.models;

public enum OrderType {
    All,
    Internal,
    External;

    public String getDisplayName() {
        switch (this) {
            case Internal:
                return "Interne";
            case External:
                return "Eksterne";
            case All:
            default:
                return "Sve";
        }
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
