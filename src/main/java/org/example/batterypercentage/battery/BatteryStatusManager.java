package org.example.batterypercentage.battery;

import org.example.batterypercentage.win32api.Kernel32;

public class BatteryStatusManager {
    private final Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
    public BatteryStatusManager() {
        update();
    }
    public void update(){
        Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
    }
    public int getBatteryLifePercent(){
        return batteryStatus.BatteryLifePercent;
    }
    public String getBatteryLifePercentText(){
        return batteryStatus.getBatteryLifePercent();
    }
}
