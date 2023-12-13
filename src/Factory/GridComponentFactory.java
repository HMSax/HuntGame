package Factory;

public class GridComponentFactory{
    public GridComponent createGridComponent(String type)
    {
        if (type == null || type.isEmpty())
            return null;
        type = type.trim().toUpperCase();
        switch (type) {
            case "HUNTER":
                return new Hunter();
            case "TARGET":
                return new Target();
            default:
                System.out.println("Unknown component type "+type);
                return null;
        }
    }
}
