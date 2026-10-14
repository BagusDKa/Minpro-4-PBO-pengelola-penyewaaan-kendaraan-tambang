package main;

import controller.KendaraanController;
import model.PengelolaKendaraan;
import view.KendaraanView;

public class Main {

    public static void main(String[] args) {
        PengelolaKendaraan model = new PengelolaKendaraan();
        KendaraanView view = new KendaraanView();
        KendaraanController controller = new KendaraanController(model, view);
        controller.jalankan();
    }
}
