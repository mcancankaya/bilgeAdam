package com.mcancankaya;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final VehicleFabric2 vehicleFabric = new VehicleFabric2();

    public static void main(String[] args) {

        while (true) {
            vehicleFabric.updateLists();
            System.out.println("----------------------------------------");
            System.out.println("1. Üretim İşlemleri\n2. Satış İşlemleri\n3. İade İşlemleri\n4. Stokları Görüntüle\n0. Sistemden Çık");
            System.out.println("Bir işlem seçin: ");
            int action = Integer.parseInt(scanner.nextLine());
            switch (action) {
                case 1:
                    produceProcess();
                    break;
                case 2:
                    salesProcess();
                    break;
                case 3:
                    returnSalesProcess();
                    break;
                case 4:
                    showStockAll();
                    break;
                case 0:
                    return;
            }

        }
    }

    private static void showStockAll() {
        vehicleFabric.printAll();
    }

    private static void returnSalesProcess() {
        System.out.println("\n1. İade et\n2. İadeleri görüntüle\n0. Geri Dön");
        int action = Integer.parseInt(scanner.nextLine());
        switch (action) {
            case 1:
                returnSaleVehicle();
                break;
            case 2:
                vehicleFabric.printReturnSaleDetails();
                break;
            case 0 :
                return;
        }

    }

    private static void returnSaleVehicle() {
        vehicleFabric.printSaleDetails();
        System.out.println("İadesi yapılacak Satışın ID'sini girin : ");
        int returnSalesId = Integer.parseInt(scanner.nextLine());

        vehicleFabric.returnSaleVehicle(returnSalesId);
    }

    private static void salesProcess() {
        System.out.println("1. Satış Yap\n2. Satışları Görüntüle\n0. Geri Dön");
        System.out.println("İşlem Seç : ");
        int action = Integer.parseInt(scanner.nextLine());
        switch (action) {
            case 1:
                sellVehicle();
                break;
            case 2:
                vehicleFabric.printSaleDetails();
                break;
            case 0:
                return;
            default:
                System.err.println("Hatalı seçim");
        }
    }

    private static void sellVehicle() {
        vehicleFabric.printAll();
        System.out.println("Satışı yapılacak Aracın ID'sini girin : ");
        int sellVehicleId = Integer.parseInt(scanner.nextLine());
        System.out.println("Satış adetini girin : ");
        int quantity = Integer.parseInt(scanner.nextLine());
        vehicleFabric.sellVehicle(sellVehicleId, quantity);
    }

    private static void produceProcess() {
        System.out.println("1. Araba Üret\n2. Motor Üret\n3. Bisiklet üret\n4. Kamyon Üret\n0. Geri Dön");
        System.out.println("İşlem Seç : ");
        int action = Integer.valueOf(scanner.nextLine());
        if (action == 0) return;
        produceVehicle(action);
    }

    private static void produceVehicle(int type) {
        System.out.println("Model Gir : ");
        String model = scanner.nextLine();
        System.out.println("Yıl gir : ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("üretim miktarı giriniz : ");
        int quantity = Integer.valueOf(scanner.nextLine());
        if (type == 1)
            vehicleFabric.produceCar(quantity, model, year);
        else if (type == 2) {
            vehicleFabric.produceMotorcycle(quantity, model, year);
        } else if (type == 3) {
            vehicleFabric.produceBicycle(quantity, model, year);
        } else if (type == 4) {
            vehicleFabric.produceVans(quantity, model, year);
        }
    }


}
