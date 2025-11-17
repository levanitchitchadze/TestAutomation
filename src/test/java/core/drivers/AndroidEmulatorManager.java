package core.drivers;

import core.config.android.VirtualDeviceConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class AndroidEmulatorManager {

    private static AndroidEmulatorManager instance;
    private VirtualDeviceConfig virtualDeviceConfig;
    private Process emulatorProcess;
    private Thread logThread;
    private String adbPath;

    private AndroidEmulatorManager(VirtualDeviceConfig virtualDeviceConfig) {
        this.virtualDeviceConfig = virtualDeviceConfig;
    }


    public static synchronized AndroidEmulatorManager getInstance(VirtualDeviceConfig deviceConfig) {
        if (instance == null) {
            instance = new AndroidEmulatorManager(deviceConfig);
        }
        return instance;
    }

    private Process getProcess() {


        ProcessBuilder builder = new ProcessBuilder(
                virtualDeviceConfig.getANDROID_HOME() + "/emulator/emulator",
                "-avd", virtualDeviceConfig.getDEVICE_NAME(),
                "-port", virtualDeviceConfig.getPORT(),
                "-no-snapshot-load",
                "-no-snapshot-save",
                "-no-boot-anim"
        );


        builder.redirectErrorStream(true);
        try {
            emulatorProcess = builder.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emulatorProcess;
    }

    public void waitUntilBootCompleted() {
        System.out.println("Waiting for emulator to boot...");

        while (true) {
            try {
                Process process = new ProcessBuilder(
                        "adb", "-s", virtualDeviceConfig.getDEVICE_SERIAL_NUMBER(), "shell", "getprop", "sys.boot_completed"
                ).start();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );

                String line = reader.readLine();

                if ("1".equals(line)) {
                    System.out.println("Emulator boot completed!");
                    break;
                }

                Thread.sleep(2000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public CompletableFuture<Void> startEmulatorAsync() {
        return CompletableFuture.supplyAsync(() -> {
                    if (isEmulatorRunning(virtualDeviceConfig.getDEVICE_SERIAL_NUMBER())) return true;
                    getProcess();
                    waitUntilBootCompleted();
                    return true;
                })
                .thenAccept(ready -> {
                    System.out.println("Emulator is ready!");
                });
    }


    private boolean isEmulatorRunning(String deviceSerialNumber) {

        String command = String.format("adb devices | grep '%s'", deviceSerialNumber);

        String[] executionOptions = {"bash", "-c", command};

        StringBuilder out = new StringBuilder();

        try {
            Process proc = Runtime.getRuntime().exec(executionOptions);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line).append("\n");
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
