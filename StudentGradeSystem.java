/**
 * Ad Soyad: Nursena Karaduman
 * Öğrenci No: 250541046
 * Proje: Öğrenci Not Değerlendirme Sistemi
 * Tarih: 18.11.2025
 */

import java.util.Scanner;      

public class StudentGradeSystem {

    // Ortalama hesaplama
    public static double calculateAverage(double vize, double finalNotu, double odev) {
        return (vize * 0.30) + (finalNotu * 0.40) + (odev * 0.30);
//deneme yorum satırı
    }

    // Geçti mi kaldı mı
    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    // Harf notu
    public static char getLetterGrade(double ort) {
        if (ort >= 90) return 'A';
        else if (ort >= 80) return 'B';
        else if (ort >= 70) return 'C';
        else if (ort >= 60) return 'D';
        else if (ort >= 50) return 'E';
        else return 'F';
    }

    // Onur listesiş
    public static boolean isHonorList(double ort, double v, double f, double o) {
        return (ort >= 85) && (v >= 70 && f >= 70 && o >= 70);
    }

    // Bütünleme hakkı (40 ≤ ort < 50)
    public static boolean hasRetakeRight(double ort) {
        return ort >= 40 && ort < 50;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Vize: ");
        double vize = sc.nextDouble();

        System.out.print("Final: ");
        double finalNotu = sc.nextDouble();

        System.out.print("Ödev: ");
        double odev = sc.nextDouble();

        double ort = calculateAverage(vize, finalNotu, odev);
        char harf = getLetterGrade(ort);
        boolean gectiMi = isPassingGrade(ort);
        boolean onur = isHonorList(ort, vize, finalNotu, odev);
        boolean butunleme = hasRetakeRight(ort);

        System.out.println("\n--- Sonuç ---");
        System.out.printf("Ortalama: %.2f%n", ort);
        System.out.println("Harf Notu: " + harf);

        if (gectiMi) System.out.println("Durum: Geçti");
        else System.out.println("Durum: Kaldı");

        System.out.println("Onur Listesi: " + (onur ? "Evet" : "Hayır"));
        System.out.println("Bütünleme Hakkı: " + (butunleme ? "Evet" : "Hayır"));

        sc.close();
    }
}
