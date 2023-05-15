package org.example.batterypercentage.ui;

import org.example.batterypercentage.battery.BatteryStatusManager;
import org.example.batterypercentage.icon.NumberIconGenerator;

import javax.swing.*;
import java.awt.*;

public class SystemTrayManager {

    private int previousBatteryLifePercent;
    private BatteryStatusManager batteryStatus;
    private TrayIcon trayIcon;

    public SystemTrayManager() {

        if (!SystemTray.isSupported()) {
            System.err.println("System tray not supported!");
            return;
        }

        init();
        batteryInformationUpdate();
    }

    private void batteryInformationUpdate(){

        Timer timer = new Timer(30000, e -> {

            batteryStatus.update();

            int updatedBatteryLifePercent = batteryStatus.getBatteryLifePercent();

            if (previousBatteryLifePercent == updatedBatteryLifePercent) {
                return;
            }

            trayIcon.setImage(NumberIconGenerator.generateNumberIcon(updatedBatteryLifePercent));

            previousBatteryLifePercent = updatedBatteryLifePercent;
        });

        timer.start();
    }

    private void init() {

        batteryStatus = new BatteryStatusManager();
        previousBatteryLifePercent = batteryStatus.getBatteryLifePercent();

        SystemTray tray = SystemTray.getSystemTray();

        trayIcon = new TrayIcon(NumberIconGenerator.generateNumberIcon(previousBatteryLifePercent));

        setPopup();

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("Could not add tray icon");
        }
    }

    private void setPopup(){

        PopupMenu popup = new PopupMenu();
        MenuItem exitItem = new MenuItem("exit");

        exitItem.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 20));
        exitItem.addActionListener(e -> System.exit(0));

        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);
    }
}
