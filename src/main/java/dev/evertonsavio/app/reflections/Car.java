package dev.evertonsavio.app.reflections;

public class Car
{
    private final String brand;
    private final String model;
    private String color;

    public Car(String brand, String model, String color)
    {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
}
