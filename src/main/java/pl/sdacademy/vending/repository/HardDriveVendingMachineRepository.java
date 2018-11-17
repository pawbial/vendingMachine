package pl.sdacademy.vending.repository;

import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repository.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.io.*;
import java.util.Optional;

public class HardDriveVendingMachineRepository implements VendingMachineRepository {

    private final String repoLocation;

    public HardDriveVendingMachineRepository (Configuration configuration) {
        repoLocation = configuration.getStringProperty("repository.hardrive.vm.path","VendingMachine.ser");

    }

    @Override
    public VendingMachine save(VendingMachine vendingMachine) {
        //1.Utworzyć OOS (output Stream)
        //bazując na repolocation
        //zapisać obiekt machine na dysku
        //zwrócić to z metody



        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repoLocation))) {
        oos.writeObject(vendingMachine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vendingMachine;
    }

    @Override
    public Optional<VendingMachine> load() {
        //1. Utworzyć OIS (input Stream)
        // bazując na repolocation odczytać obiekt, rzutować go i opakować w Optional,
        // WAŻNE! Pliku może niebyć na dysku -> Optional.empty ()

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repoLocation))) {
        VendingMachine loaded = (VendingMachine) ois.readObject();
        return Optional.ofNullable(loaded);
        } catch (IOException e) {
            System.out.println("Repository not found");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
