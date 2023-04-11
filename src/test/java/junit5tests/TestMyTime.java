package junit5tests;

import calculator.MyTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMyTime {

    @Test
    void testGetAsDate() {
        // Simple date
        String date = "2020-02-01";
        MyTime res = MyTime.getAsDate(date);
        assertEquals(res.getValue(), 1580515200);

        // Date with hour
        date = "2020-02-01 15:30:00";
        MyTime res1 = MyTime.getAsDate(date);

        // 12-hour format
        date = "2020-02-01 03:30:00 PM";
        MyTime res2 = MyTime.getAsDate(date);

        // Timezone
        date = "2020-02-01 17:30:00 +02:00";
        MyTime res3 = MyTime.getAsDate(date);

        assertEquals(res1.getValue(), res2.getValue());
        assertEquals(res2.getValue(), res3.getValue());
    }

    @Test
    void testGetAsHours() {
        // Simple time
        String time = "15:34:26";
        MyTime res = MyTime.getAsHours(time);
        assertEquals(res.getValue(), 15 * 3600 + 34 * 60 + 26);

        // Reduced time
        time = "15:34";
        res = MyTime.getAsHours(time);
        assertEquals(res.getValue(), 15 * 3600 + 34 * 60);

        // 12-hour format
        time = "3:34 PM";
        res = MyTime.getAsHours(time);
        assertEquals(res.getValue(), 15 * 3600 + 34 * 60);

        // String results
        assertEquals(res.toString(), "15 hours; 34 minutes; 0 seconds");
    }

    @Test
    void testGetAsDays() {
        int nDays = 100;
        MyTime res = MyTime.getAsDays(nDays);

        assertEquals(res.getValue(), 100 * 24 * 3600);
        assertEquals(res.toString(), "100 days");
    }

    @Test
    void testGetAs() {
        // Date
        String date = "2020-02-01";
        MyTime res1 = MyTime.getAsDate(date);
        MyTime res2 = MyTime.getAs(date);
        assertEquals(res1.getValue(), res2.getValue());

        // Hour
        String time = "3:34 PM";
        res1 = MyTime.getAsHours(time);
        res2 = MyTime.getAs(time);
        assertEquals(res1.getValue(), res2.getValue());

        // Days
        int nDays = 100;
        res1 = MyTime.getAsDays(nDays);
        res2 = MyTime.getAs(String.valueOf(nDays));
        assertEquals(res1.getValue(), res2.getValue());

        // Exception
        assertThrows(IllegalArgumentException.class, () -> MyTime.getAs(""));
    }
}
