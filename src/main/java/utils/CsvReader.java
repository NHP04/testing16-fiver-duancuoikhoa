package utils;

import org.apache.commons.io.IOIndexedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvReader {
    public static List<String[]> readCsv(String filePath) throws IOException {
      List<String[]> data = new ArrayList<>();

      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      try (reader) {
          String line;
          boolean isHeader = true;

          while((line = reader.readLine()) != null){
              if(isHeader){
                  isHeader = false;
                  continue;
              }

              if(line.trim().isEmpty()){
                  continue;
              }

              String[] values = line.split(",");
              data.add(values);

          }

      }

      return  data;
    }

    public static Object[][] toDataProviderArray(List<String[]> rows){
        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i<rows.size(); i++){
            data[i] = rows.get(i);
        }
        return data;
    }

}
