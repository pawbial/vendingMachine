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
    public Optional<String> addTray(String traySymbol, Long price) {
        Optional<VendingMachine> load = machineRepository.load();
        VendingMachine vendingMachine = (VendingMachine) load.orElseGet(() -> new VendingMachine(configuration));
        Tray tray = Tray.builder(traySymbol).price(price).build();
        boolean check = vendingMachine.placeTray(tray);

        if (check) {
            machineRepository.save(vendingMachine);
        } else {
            String serviceError = "Service Error, could not add tray, check provided machine";
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
        Optional<VendingMachine> loadedMachine = machineRepository.load();
        if (!loadedMachine.isPresent()) {
            return Optional.of(
                    "There is no vending machine, add one by creating tray");
        }
        VendingMachine machine = loadedMachine.get();
        for (int addedProductCount = 0; addedProductCount < quantity; addedProductCount++) {
            Product product = new Product(productName);
            if (!machine.addProductToTray(traySymbol, product)) {
                machineRepository.save(machine);
                return Optional.of(
                        "Could not add "
                                + (quantity - addedProductCount)
                                + " products");
            }
        }
        machineRepository.save(machine);
        return Optional.empty();
    }

    @Override
    public Optional<String> changePrice(String traySymbol, Long updatedPrice) {
        Optional<VendingMachine> loadedMachine = machineRepository.load();
        if (loadedMachine.isPresent()) {
            VendingMachine vendingMachine = loadedMachine.get();
            boolean succesfull = vendingMachine.updatePriceForSymbol(traySymbol, updatedPrice);
            machineRepository.save(vendingMachine);
            if (succesfull){
                return Optional.empty();
            } else {
                return Optional.of("Could not change price, check tray symbol");
            }

        }

        return Optional.of("There is no vending machine, create one");
    }
}


