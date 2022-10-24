import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Read {

    static int colCount;
    static int rowCount;
    final static String filename = "test.csv";
    final static String delimiter = ";";



    public static void readFile(String filename, AppData data) {
        try (InputStream in = new BufferedInputStream(
                new FileInputStream(filename))) {
            int x;
            String buffer = ""; //
            StringBuilder stringBuilder = new StringBuilder();
            while ((x = in.read()) != -1) {
                stringBuilder.append((char) x);
            }

            buffer = stringBuilder.toString();


            String[] rows = buffer.split(System.getProperty("line.separator"));
            if (rows.length == 0) {
                System.out.println("Файл пустой!");
                System.exit(0);
            }

            rowCount = rows.length - 1;

            String[] headers = rows[0].split(delimiter);

            colCount = headers.length;

            data.setHeader(headers);

            int[][] dataBuffer = new int[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                String[] cols = rows[i+1].split(delimiter);
                for (int j = 0; j < colCount; j++) {
                    dataBuffer[i][j] = Integer.parseInt(cols[j]);
                }
            }

            data.setData(dataBuffer);


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка чтения файла " + filename);
            System.exit(0);
        }

    }


    public static void showData(AppData data) {


        for (int i = 0; i < colCount; i++) {
            System.out.print(data.getHeader()[i] + " ");
        }
        System.out.println();


        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                System.out.print(data.getData()[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        System.out.println("Чтение CSV-файла " + filename);


        AppData data = new AppData();


        readFile(filename, data);

        showData(data);

    }

}

