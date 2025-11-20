/**
 * Ad Soyad: Nursena Karaduman
 * Öğrenci No: 250541046
 * Proje 2: Sinema Bileti Fiyatlandırma Sistemi
 * Tarih: 19.11.2025
 */

import java.util.Scanner;

public class CinemaTicketSystem {

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        // 6 = Cumartesi, 7 = Pazar
        return gun == 6 || gun == 7;
    }

    // 2) Matine mi? (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel bilet fiyatını hesapla
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);
        double basePrice = 0;

        if (!weekend && matinee) {
            // Hafta içi matine
            basePrice = 45.0;
        } else if (!weekend && !matinee) {
            // Hafta içi normal
            basePrice = 65.0;
        } else if (weekend && matinee) {
            // Hafta sonu matine
            basePrice = 55.0;
        } else {
            // Hafta sonu normal
            basePrice = 85.0;
        }

        return basePrice;
    }

    // 4) İndirim oranını hesapla (0.20 = %20 gibi)
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double ageDiscount = 0.0;
        double jobDiscount = 0.0;

        // Yaşa göre indirim
        if (yas >= 65) {
            ageDiscount = 0.30;   // %30
        } else if (yas < 12) {
            ageDiscount = 0.25;   // %25
        }

        // Mesleğe göre indirim (switch şart)
        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) { // Pzt - Perş
                    jobDiscount = 0.20;     // %20
                } else { // Cuma - Pazar
                    jobDiscount = 0.15;     // %15
                }
                break;
            case 2: // Öğretmen
                if (gun == 3) {             // Çarşamba
                    jobDiscount = 0.35;     // %35
                }
                break;
            case 3: // Diğer
                jobDiscount = 0.0;
                break;
            default:
                jobDiscount = 0.0;
        }

        // Aynı anda hem yaş hem meslek indirimi varsa:
        // Basitlik için en yüksek olanı uygulayalım.
        double finalDiscount = ageDiscount;
        if (jobDiscount > finalDiscount) {
            finalDiscount = jobDiscount;
        }

        return finalDiscount;
    }

    // 5) Film formatına göre ekstra ücret
    public static double getFormatExtra(int filmTuru) {
        double extra;

        // 1=2D, 2=3D, 3=IMAX, 4=4DX
        switch (filmTuru) {
            case 1: // 2D
                extra = 0.0;
                break;
            case 2: // 3D
                extra = 25.0;
                break;
            case 3: // IMAX
                extra = 35.0;
                break;
            case 4: // 4DX
                extra = 50.0;
                break;
            default:
                extra = 0.0;
        }

        return extra;
    }

    // 6) Nihai ücret hesaplama
    public static double calculateFinalPrice(double basePrice,
                                             double discountRate,
                                             double formatExtra,
                                             boolean isWeekend,
                                             boolean isMatinee) {

        // Önce format ücretini ekleyelim
        double price = basePrice + formatExtra;

        // 200 TL üzeri %10 ekstra indirim (buna da dikkat)
        double totalDiscountRate = discountRate;

        // 200 TL üzeri indirim sadece fiyat 200'ü geçerse ekleniyor
        if (price > 200) {
            totalDiscountRate += 0.10;
        }

        // Toplam indirimi uygula
        double discountAmount = price * totalDiscountRate;
        double finalPrice = price - discountAmount;

        return finalPrice;
    }

    // 7) Bilet bilgisini ekrana yazdır
    public static void generateTicketInfo(double basePrice,
                                          double formatExtra,
                                          double discountRate,
                                          double finalPrice) {

        double priceWithExtra = basePrice + formatExtra;
        double discountAmount = priceWithExtra * discountRate;
        double totalDiscountRatePercent = discountRate * 100;

        System.out.println("\n--- Bilet Bilgileri ---");
        System.out.printf("Temel Fiyat       : %.2f TL%n", basePrice);
        System.out.printf("Format Ekstra     : %.2f TL%n", formatExtra);
        System.out.printf("Ara Toplam        : %.2f TL%n", priceWithExtra);
        System.out.printf("İndirim Oranı     : %.0f%%%n", totalDiscountRatePercent);
        System.out.printf("İndirim Tutarı    : %.2f TL%n", discountAmount);
        System.out.printf("Ödenecek Tutar    : %.2f TL%n", finalPrice);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Sinema Bileti Fiyatlandırma Sistemi ===\n");

        // Gün seçimi (switch zorunluluğu için isim de gösterelim)
        System.out.println("Gün Seçimi:");
        System.out.println("1 - Pazartesi");
        System.out.println("2 - Salı");
        System.out.println("3 - Çarşamba");
        System.out.println("4 - Perşembe");
        System.out.println("5 - Cuma");
        System.out.println("6 - Cumartesi");
        System.out.println("7 - Pazar");
        System.out.print("Gün (1-7): ");
        int gun = sc.nextInt();

        String gunAdi;
        switch (gun) { // switch-case kullanımı
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Salı"; break;
            case 3: gunAdi = "Çarşamba"; break;
            case 4: gunAdi = "Perşembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
            default: gunAdi = "Geçersiz"; break;
        }

        System.out.print("Saat (0-23): ");
        int saat = sc.nextInt();

        System.out.print("Yaş: ");
        int yas = sc.nextInt();

        System.out.println("\nMeslek Seçimi:");
        System.out.println("1 - Öğrenci");
        System.out.println("2 - Öğretmen");
        System.out.println("3 - Diğer");
        System.out.print("Seçiminiz: ");
        int meslek = sc.nextInt();

        System.out.println("\nFilm Türü Seçimi:");
        System.out.println("1 - 2D");
        System.out.println("2 - 3D");
        System.out.println("3 - IMAX");
        System.out.println("4 - 4DX");
        System.out.print("Seçiminiz: ");
        int filmTuru = sc.nextInt();

        // Hesaplamalar
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);

        double finalPrice = calculateFinalPrice(basePrice, discountRate, formatExtra, weekend, matinee);

        // Bilet özetini yazdır
        System.out.println("\nSeçilen Gün: " + gunAdi);
        generateTicketInfo(basePrice, formatExtra, discountRate, finalPrice);

        sc.close();
    }
}
