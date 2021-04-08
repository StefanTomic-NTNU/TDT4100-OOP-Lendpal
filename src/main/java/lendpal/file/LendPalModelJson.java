package lendpal.file;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import lendpal.model.LendPalModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    }

    @Override
    public void load(String fileName) {

    }
}
