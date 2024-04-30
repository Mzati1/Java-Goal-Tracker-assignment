import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jdatepicker.impl.*;

//from https://stackoverflow.com/questions/31277001/jdatepicker-date-formatting both question and answer
import javax.swing.JFormattedTextField;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final String datePattern = "yyyy-MM-dd";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}
