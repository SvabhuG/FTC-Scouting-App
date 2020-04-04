package scouting.app.pioneerrobotics;

import com.github.mikephil.charting.formatter.ValueFormatter;

        import java.text.DecimalFormat;

public class MyValueFormatter extends ValueFormatter
{

    private final DecimalFormat mFormat;


    public MyValueFormatter() {
        mFormat = new DecimalFormat("#");

    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value);
    }

}