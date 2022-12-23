# K-Means-Clustering

Aşağıdaki gibi bir ekran oluşturup bir csv dosyasından 2-boyutlu koordinatları okuyan, alınan
noktaların k-means clustering ile gruplandırılmasını yapmanızdır. Burada iterasyon sayısı ve Cluster sayısı (K) kullanıcıdan alınmaktadır. Dosyadan seç butonu ile bir csv dosyası seçilerek koordinatlar okunmaktadır. K-Means Clustering Butonuna basıldığında sınıflandırma yapılarak sınıf sayısına göre renklendirme yapılmaktadır. Burada K değeri 1 ile
10 arasında bir değer seçilebilir. İterasyon ve K değeri güncellenip K-Means Clustering Butona tekrar basıldığında yeni değerlere göre baştan sınıflandırma ve renklendirme yapmaktadır.

Bu sınıflandırmayı yapan bir ekran oluşturmanız istenmektedir.
1. Renkleri standart olarak tanımlayabilirsiniz.Yani k değerine göre sabit renkler tanımlayıp, bunlarla renk ayarlayabilirsiniz.
Noktaların boyutlarını ekranda görülecek standart bir boyutta ayarlayabilirsiniz.
2. Açık Mavi renkteki daha büyük noktalar Cluster Merkezlerini göstermektedir. Siz kendi belirlediğiniz bir renk
yapabilirsiniz.
3. Ekran boyutuna göre random merkezlerinizi oluşturabilirsiniz.
4. Örnek bir csv dosyası verilecektir.
5. Csv dosyası haricinde bir dosya seçildiğinde hata penceresi oluşturmalı ve işlem yapmamalıdır.
6. Dosya seçici proje dosyalarının olduğu dizinden (javanın ¸calıştığı yerden) başlamalıdır.
7. İterasyon ve K değeri yeniden girildiğinde K-Means Clustering butonuna bastıgında yeni değrlere göre hesaplama
yapmalıdır.
8. Programınız penceresi kapatılmadığı takdirde sonlandırılmamalıdır, tekrarlı kullanıma uygun olmalıdır.

K-Means Clustering: Başta girilen K değeri kadar Random Merkez belirlenir. Sınıflandırılacak her bir nokta bu K merkezden hangisine daha yakınsa o grupa dahil edilir. Her iterasyonda Grubun yeni merkezi hesaplanıp Merkezler güncellenir. Yeni merkezlere göre tekrar gruplandırma yapılır. İterasyon kadar Grup merkezleri güncellediğinde son durumdaki gruplandırmaya göre renklendirme yapılır.
