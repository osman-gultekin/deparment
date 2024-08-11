#Java version
Department mikroservis projesi java 21 sürümüyle çalışmaktadır.
#Nasıl çalışır
projeyi git üzerinden indirip bir eksik olup olmadığını kontrol ettikten sonra eğer sorun yoksa
java version ve maven version(3.x ve üzeri) kontrol edilmedilir.Daha sonra projeyi run edebilirsiniz.

_2024-08-11T15:52:45.655+03:00  INFO 25312 --- [department] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-08-11T15:52:45.666+03:00  INFO 25312 --- [department] [           main] c.e.department.DepartmentApplication     : Started DepartmentApplication in 5.471 seconds (process running for 6.095)_

Buna benzer bir log varsa proje başarı ile çalışmıştır.

#H2 veritabanı kullanılmaktadır
H2 veritabanıyla alakalı tüm ayarlar application.properties dosyası içerisindedir.
http://localhost:8080/h2-console üzerinden application properties dosyasında bulunan bilgiler ile giriş yapılıp veritabanı kontrol edilebilir.

