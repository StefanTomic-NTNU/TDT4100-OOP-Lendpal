package lendpal.file;

import lendpal.model.LendPalModel;

import java.nio.file.Path;

public interface LendPalModelFileHandler {

    void save(LendPalModel model, String fileName);

    void load(String fileName);

}
