import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Write {

    final static int colCount = 4;
    final static int rowCount = 100;
    final static String filename = "test.csv";
    final static String delimiter = ";";


    public static void fillData(AppData data) {

        String[] headers = new String[colCount];

        for (int i = 0; i < colCount; i++) {
            headers[i] = "Value" + i;
        }

        data.setHeader(headers);


        int[][] dataBuffer = new int[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                dataBuffer[i][j] = (int) (Math.random() * 1000);
            }
        }

        data.setData(dataBuffer);
    }


    public static void saveData(AppData data, String filename) {

        try (OutputStream out = new BufferedOutputStream(
                new FileOutputStream(filename))) {


            for (int i = 0; i < colCount; i++) {
                out.write((data.getHeader()[i]).getBytes());
                if (i < colCount - 1) {
                    out.write(delimiter.getBytes());
                }
            }
            out.write(System.getProperty("line.separator").getBytes());


            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    out.write(Integer.toString(data.getData()[i][j]).getBytes());
                    if (j < colCount - 1) {
                        out.write(delimiter.getBytes());
                    }
                }
                out.write(System.getProperty("line.separator").getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка записи файла " + filename);
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        System.out.println("Запись CSV-файла");

        AppData data = new AppData();


        fillData(data);

        saveData(data, filename);

        System.out.println("Файл " + filename + " успешно записан.");

    }
}


