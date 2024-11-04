package Model;

import java.util.List;

public class Car
{
    private String _variantReg;
    private String _make;
    private String _model;
    private String _year;

    public Car(String variantReg, String make, String model, String year)
    {
        _variantReg = variantReg;
        _make = make;
        _model = model;
        _year = year;
    }

    public String getVariantReg()
    {
        return _variantReg;
    }
}
