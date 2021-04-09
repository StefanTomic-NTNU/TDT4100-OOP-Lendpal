package lendpal.file;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import lendpal.model.LendPalModel;

import java.io.*;
import java.nio.file.Path;
import java.text.Format;

public class LendPalModelJson implements LendPalModelFileHandler {

    @Override
    public void save(LendPalModel model, String fileName) {
        Moshi moshi = new Moshi.Builder()
                .add(new ZonedDateTimeAdapter())
                .build();
        JsonAdapter<LendPalModel> jsonAdapter = moshi.adapter(LendPalModel.class);

        String json = jsonAdapter.toJson(model);
        System.out.println(json);

        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(fileName)
            );
            bw.write(json);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public LendPalModel load(String fileName) {
        Moshi moshi = new Moshi.Builder()
                .add(new ZonedDateTimeAdapter())
                .build();
        JsonAdapter<LendPalModel> jsonAdapter = moshi.adapter(LendPalModel.class);

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(fileName)
            );
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String json = jsonBuilder.toString();
            br.close();
            return jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
