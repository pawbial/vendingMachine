package pl.sdacademy.vending.controller.service;

import pl.sdacademy.vending.model.Tray;

import java.util.Optional;

public interface EmployeeService {

     Optional <String> addTray (Tray tray);
}
