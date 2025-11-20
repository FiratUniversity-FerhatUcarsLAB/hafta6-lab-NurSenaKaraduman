/**
 * Ad Soyad: Nursena Karaduman
 * Öğrenci No: 250541046
 * Proje: Restoran Hesap / İndirim Sistemi (Proje 3)
 * Tarih: 19.11.2025
 * güncelleme: 20.11.2025
 */

import java.util.Scanner;

public class RestaurantDiscountSystem
 {

    // İçeride kullanmak için içecek toplamını static değişkende tutacağız
    static double drinkTotal = 0.0;

    // 1) Ana yemek fiyatı
    public static double getMainDishPrice(int secim) {
        double fiyat;
        switch (secim) {
            case 1: // Izgara Tavuk
                fiyat = 85.0;
                break;
            case 2: // Adana Kebap
                fiyat = 120.0;
                break;
            case 3: // Levrek
                fiyat = 110.0;
                break;
            case 4: // Mantı
                fiyat = 65.0;
                break;
            default:
                fiyat = 0.0; // Geçersiz veya 0 = seçilmedi
        }
        return fiyat;
    }

    // 2) Başlangıç fiyatı
    public static double getAppetizerPrice(int secim) {
        double fiyat;
        switch (secim) {
            case 1: // Çorba
                fiyat = 25.0;
                break;
            case 2: // Humus
                fiyat = 45.0;
                break;
            case 3: // Sigara Böreği
                fiyat = 55.0;
                break;
            default:
                fiyat = 0.0;
        }
        return fiyat;
    }

    // 3) İçecek fiyatı
    public static double getDrinkPrice(int secim) {
        double fiyat;
        switch (secim) {
            case 1: // Kola
                fiyat = 15.0;
                break;
            case 2: // Ayran
                fiyat = 12.0;
                break;
            case 3: // Meyve Suyu
                fiyat = 35.0;
                break;
            case 4: // Limonata
                fiyat = 25.0;
                break;
            default:
                fiyat = 0.0;
        }
        return fiyat;
    }

    // 4) Tatlı fiyatı
    public static double getDessertPrice(int secim) {
        double fiyat;
        switch (secim) {
            case 1: // Künefe
                fiyat = 65.0;
                break;
            case 2: // Baklava
                fiyat = 55.0;
                break;
            case 3: // Sütlaç
                fiyat = 35.0;
                break;
            default:
                fiyat = 0.0;
        }
        return fiyat;
    }

    // 5) Combo kontrolü (Ana + İçecek + Tatlı var mı?)
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6) Happy Hour kontrolü (14:00 - 17:00 arası)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) Toplam indirim hesaplama
    // - combo: Ana+İçecek+Tatlı varsa %15
    // - 200 TL üzeri: %10
    // - Happy Hour: içeceklerde %20
    // - Öğrenci (hafta içi): ekstra %10
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenciHaftaIci, int saat) {
        double indirim = 0.0;

        // Combo indirim (%15, toplam tutar üzerinden)
        if (combo) {
            indirim += tutar * 0.15;
        }

        // 200 TL üzeri indirim (%10, toplam tutar üzerinden)
        if (tutar > 200) {
            indirim += tutar * 0.10;
        }

        // Happy Hour: içeceklerden %20
        if (isHappyHour(saat) && drinkTotal > 0) {
            indirim += drinkTotal * 0.20;
        }

        // Öğrenci (hafta içi): ekstra %10
        if (ogrenciHaftaIci) {
            indirim += tutar * 0.10;
        }

        return indirim;
    }

    // 8) Bahşiş önerisi: %10
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // Küçük yardımcı: 2 basamak yuvarlama (isteğe bağlı)
    public static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Akıllı Restoran Sipariş Sistemi ===\n");

        // Gün ve saat bilgisi
        System.out.print("Gün (1=Pzt, 2=Salı, 3=Çarş, 4=Perş, 5=Cuma, 6=Ctesi, 7=Pazar): ");
        int gun = sc.nextInt();

        System.out.print("Saat (0-23): ");
        int saat = sc.nextInt();

        System.out.print("Öğrenci misiniz? (1=Evet, 2=Hayır): ");
        int ogrSecim = sc.nextInt();
        boolean isStudent = (ogrSecim == 1);
        boolean isWeekday = (gun >= 1 && gun <= 5);
        boolean ogrenciHaftaIci = isStudent && isWeekday;

        System.out.println("\n--- Menü ---");

        // ANA YEMEK
        System.out.println("Ana Yemekler:");
        System.out.println("1 - Izgara Tavuk (85₺)");
        System.out.println("2 - Adana Kebap (120₺)");
        System.out.println("3 - Levrek (110₺)");
        System.out.println("4 - Mantı (65₺)");
        System.out.println("0 - Ana Yemek Yok");
        System.out.print("Seçiminiz: ");
        int anaSecim = sc.nextInt();
        double anaFiyat = getMainDishPrice(anaSecim);
        boolean anaVar = (anaFiyat > 0);

        // BAŞLANGIÇ
        System.out.println("\nBaşlangıçlar:");
        System.out.println("1 - Çorba (25₺)");
        System.out.println("2 - Humus (45₺)");
        System.out.println("3 - Sigara Böreği (55₺)");
        System.out.println("0 - Başlangıç Yok");
        System.out.print("Seçiminiz: ");
        int baslangicSecim = sc.nextInt();
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);

        // İÇECEK
        System.out.println("\nİçecekler:");
        System.out.println("1 - Kola (15₺)");
        System.out.println("2 - Ayran (12₺)");
        System.out.println("3 - Meyve Suyu (35₺)");
        System.out.println("4 - Limonata (25₺)");
        System.out.println("0 - İçecek Yok");
        System.out.print("Seçiminiz: ");
        int icecekSecim = sc.nextInt();
        double icecekFiyat = getDrinkPrice(icecekSecim);
        boolean icecekVar = (icecekFiyat > 0);
        drinkTotal = icecekFiyat; // Happy Hour indirimi için içecek toplamını kaydediyoruz

        // TATLI
        System.out.println("\nTatlılar:");
        System.out.println("1 - Künefe (65₺)");
        System.out.println("2 - Baklava (55₺)");
        System.out.println("3 - Sütlaç (35₺)");
        System.out.println("0 - Tatlı Yok");
        System.out.print("Seçiminiz: ");
        int tatliSecim = sc.nextInt();
        double tatliFiyat = getDessertPrice(tatliSecim);
        boolean tatliVar = (tatliFiyat > 0);

        // Ara toplam hesaplama
        double araToplam = anaFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        // Combo var mı?
        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        // Toplam indirim
        double toplamIndirim = calculateDiscount(araToplam, combo, ogrenciHaftaIci, saat);

        // Ödenecek tutar
        double odenecek = araToplam - toplamIndirim;

        // Bahşiş önerisi
        double bahsisOnerisi = calculateServiceTip(odenecek);

        // Sonuçları yuvarlayalım (istersen)
        araToplam = round2(araToplam);
        toplamIndirim = round2(toplamIndirim);
        odenecek = round2(odenecek);
        bahsisOnerisi = round2(bahsisOnerisi);

        System.out.println("\n--- Hesap Özeti ---");
        System.out.printf("Ara Toplam: %.2f₺%n", araToplam);
        System.out.printf("Toplam İndirim: %.2f₺%n", toplamIndirim);
        System.out.printf("Ödenecek Tutar: %.2f₺%n", odenecek);
        System.out.printf("Bahşiş Önerisi (%%10): %.2f₺%n", bahsisOnerisi);

        sc.close();
    }
}
