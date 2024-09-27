package com.epam;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.RequestPattern;
import org.openqa.selenium.devtools.v85.network.model.ResourceType;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.openqa.selenium.devtools.v85.performance.Performance;
import org.openqa.selenium.devtools.v85.performance.model.Metric;


import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        driver.get("https://google.com");

//        Capture browser console logs, which can be useful for debugging issues directly from the browser's console output:

        devTools.send(Log.enable());

        List<LogEntry> allLogEntries = driver.manage().logs().get(LogType.BROWSER).getAll();
        for (LogEntry entry : allLogEntries) {
            System.out.println(entry.getLevel() + " - " + entry.getMessage());
        }

        //Modify network conditions or intercept HTTP network requests and responses:

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        RequestPattern pattern = new RequestPattern(Optional.of("*Get*"), Optional.of(ResourceType.DOCUMENT), Optional.empty());
        devTools.send(Network.setRequestInterception(List.of(pattern)));

        devTools.addListener(Network.requestIntercepted(), intercepted -> {
            System.out.println("Intercepted request: " + intercepted.getRequest().getUrl());
            devTools.send(Network.continueInterceptedRequest(intercepted.getInterceptionId(), Optional.empty(),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.empty()));
        });

//        3. Emulate Geolocation
//        Test how your application behaves in different geographic locations by emulating geolocation:

        double latitude = 37.7749;
        double longitude = -122.4194;
        int accuracy = 100;  // 100 meters accuracy

        devTools.send(Emulation.setGeolocationOverride(Optional.of(latitude), Optional.of(longitude), Optional.of(accuracy)));

//        4. Simulate Network Conditions
//        Simulate various network conditions to see how your site performs under different data throughput scenarios:
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 100, 200000, 100000, Optional.of(ConnectionType.CELLULAR3G)));

//        5. Capture Performance Metrics
//        Get performance metrics to analyze the loading performance of your web application:

        devTools.send(Performance.enable());
        driver.get("https://www.example.com");

        List<Metric> metrics = devTools.send(Performance.getMetrics());
        metrics.forEach(metric ->
                System.out.println(metric.getName() + " : " + metric.getValue())
        );
    }
}