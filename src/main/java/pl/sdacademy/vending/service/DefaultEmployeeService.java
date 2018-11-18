package pl.sdacademy.vending.service;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import pl.sdacademy.vending.controller.service.EmployeeService;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repository.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

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

    @Override
    public Optional<String> removeTrayWithSymbol(String traySymbol) {
        Optional<VendingMachine> load = machineRepository.load();
        if (load.isPresent()) {
            VendingMachine vendingMachine = load.get();
            Optional<Tray> removedTray = vendingMachine.removeTrayWithSymbol(traySymbol);
            if (removedTray.isPresent()) {
                machineRepository.save(vendingMachine);
                return Optional.empty();
            }
        } else {
            return Optional.of("There is no vending machine");
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> addProduct(String traySymbol, String productName, Integer quantity) {
        Optional<VendingMachine> load = machineRepository.load();
        int fail = 0;
        int success = 0;
        if (!load.isPresent()) {
           return Optional.of("There is no vending machine, please create new one");
        }
        if (load.isPresent()) {
            VendingMachine vendingMachine = load.get();
            for (int i = 0; i < quantity; i++) {
                boolean addedProductCount = vendingMachine.addProductToTray(traySymbol, new Product(productName));
                if (addedProductCount) {
                    success ++;
                } else if (!addedProductCount) {
                    fail++;
                }
            machineRepository.save(vendingMachine);
                if (fail == 0) {
                    return Optional.empty();
                }
                return Optional.of(success + " products added correctly, and " + fail + " not");
            }
            machineRepository.save(vendingMachine);
        }
        return Optional.empty();
    }
}


