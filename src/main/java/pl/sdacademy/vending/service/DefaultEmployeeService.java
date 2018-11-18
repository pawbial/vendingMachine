package pl.sdacademy.vending.service;

import pl.sdacademy.vending.controller.service.EmployeeService;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repository.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Optional;

public class DefaultEmployeeService implements EmployeeService {

    private final VendingMachineRepository machineRepository;
    private final Configuration configuration;

    public DefaultEmployeeService(VendingMachineRepository machineRepository, Configuration configuration) {
        this.machineRepository = machineRepository;
        this.configuration = configuration;
    }

    @Override
    public Optional<String> addTray(Tray tray) {
        //Załaduj vendingMachine z repo, dodaj tackę, sprawdź czy zadziałało,
        //jeżeli tak, to zapisujemy automat, jeżeli nie to komunikat błędu
        Optional<VendingMachine> load = machineRepository.load();

        VendingMachine vendingMachine = (VendingMachine) load.orElseGet(() -> new VendingMachine(configuration));
        boolean check = vendingMachine.placeTray(tray);

        if (check) {
            machineRepository.save(vendingMachine);
        } else {
            String serviceError = "Service Error, could not add tray, check providaded machine";
           return Optional.of(serviceError);
        }

        return Optional.empty();
    }
}
