package lesson1;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JavaTasksTest {

    private static void assertFile(String name, String name2) {
        StringBuilder content = new StringBuilder();
        StringBuilder content2 = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            String curr;
            while ((curr = br.readLine()) != null) {
                content.append(curr);
            }
        } catch (IOException ex) {
            System.out.print(ex.toString());
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(name2));
            String curr2;
            while ((curr2 = br.readLine()) != null) {
                content2.append(curr2);
            }
        } catch (IOException ex) {
            System.out.print(ex.toString());
        }
        assertEquals(content.toString(), content2.toString());
    }

    @Test
    void sortTimes() {
        JavaTasks.sortTimes("time_in3.txt", "temptime.txt");
        assertFile("time_out3.txt", "temptime.txt");
        JavaTasks.sortTimes("time_in1.txt", "temptime.txt");
        assertFile("time_out1.txt", "temptime.txt");
        JavaTasks.sortTimes("time_in2.txt", "temptime.txt");
        assertFile("time_out2.txt", "temptime.txt");
    }

    @Test
    void sortTemperatures() {
        JavaTasks.sortTemperatures("temp_in1.txt", "temptime.txt");
        assertFile("temp_out1.txt", "temptime.txt");
        JavaTasks.sortTemperatures("temp_in2.txt", "temptime.txt");
        assertFile("temp_out2.txt", "temptime.txt");
        JavaTasks.sortTemperatures("temp_in3.txt", "temptime.txt");
        assertFile("temp_out3.txt", "temptime.txt");
    }
}