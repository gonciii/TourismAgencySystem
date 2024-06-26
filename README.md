
Turizm Acente Sistemi

Bu proje, Patika Turizm Acentesi için geliştirilen otel yönetim ve rezervasyon sistemi Java dilinde ve katmanlı mimari kullanılarak oluşturulmuştur.

------Proje Açıklaması--------

Patika Turizm Acentesi'nin otel rezervasyon işlemlerini dijital ortamda yönetmesini sağlayan bir yazılımdır. Proje, aşağıdaki katmanlar üzerinde organize edilmiştir: business, dao, entity, core ve view.

----Kullanılan Teknolojiler ve Araçlar-----

-Java SE 8: Ana programlama dili.

-PostgreSQL: Veritabanı yönetimi için kullanılmıştır.

-Java Swing: Kullanıcı arayüzü geliştirmek için tercih edilmiştir.

----Proje Katmanları------

Proje, aşağıdaki katmanlar üzerinde organize edilmiştir:

1.View Katmanı
Kullanıcı arayüzü bileşenlerini içerir. Java Swing ile geliştirilmiştir.

2.Business Katmanı
İş mantığı işlemlerinin yönetildiği katmandır. Servis sınıfları burada bulunur.

3.DAO (Data Access Object) Katmanı
Veritabanı işlemlerinin gerçekleştirildiği katmandır. 

4.Entity Katmanı
Veritabanı tablolarını temsil eden entity (varlık) sınıflarını içerir.

5.Core Katmanı
Temel yardımcı sınıfları, genel hizmetleri ve bağımsız araçları içerir.(DB,Helper,ComboItem..)

---Özellikler----


Admin
-Kullanıcı Yönetimi:
  Acente çalışanı listeleme, ekleme, silme, güncelleme
  Kullanıcı rolüne göre filtreleme (admin, personel)
  
Acente Çalışanı
-Otel Yönetimi :
   Otel listeleme, ekleme
   Pansiyon tipleri ve tesis özellikleri tanımlama
-Oda Yönetimi:
   Oda listeleme, ekleme
-Dönem Yönetimi
   Dönem listeleme, ekleme
   
-Fiyat Yönetimi

-Oda Arama ve Rezervasyon İşlemleri:
   Rezervasyon listeleme, ekleme, silme, güncelleme

   
------- Kurulum ------
 
   PostgreSQL Kurulumu: İlk olarak PostgreSQL veritabanınızı kurun ve bir veritabanı oluşturun.
   Veritabanı Yapısının Kurulumu: Proje içinde bulunan database.sql dosyasını kullanarak tabloları ve ilişkileri oluşturun.
   Uygulamanın Başlatılması: Proje içinde bulunan ana sınıfı çalıştırarak uygulamayı başlatın.

 -------Kullanım---------
 
Admin Girişi:
Admin olarak giriş yaparak kullanıcı yönetimi sekmesinden acente çalışanları ekleyebilir veya yönetebilirsiniz.

![Ekran Görüntüsü (45)](https://github.com/gonciii/TourismAgencySystem/assets/114026990/027c718a-f50f-45a3-b425-11f08f4fd469)

![Ekran Görüntüsü (46)](https://github.com/gonciii/TourismAgencySystem/assets/114026990/ae44fcc1-8882-456f-838e-878173b7a9a7)


Acente Çalışanı Girişi:
Acente çalışanı olarak giriş yaparak otel yönetimi, oda yönetimi, rezervasyon işlemleri gibi işlemleri gerçekleştirebilirsiniz.
   
![Ekran Görüntüsü (44)](https://github.com/gonciii/TourismAgencySystem/assets/114026990/3bc0ecb9-378d-4f82-a5fb-7af2fc0047b5)







   
