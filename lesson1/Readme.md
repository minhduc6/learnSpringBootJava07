Câu 1 :
Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : groupID, artifactID. Ý nghĩa các tham số này là gì?
-GroupId là một thành phần XML trong tệp POM.XML của dự án Maven chỉ định id của nhóm dự án.
-ArtifactId là một thành phần XML trong POM.XML của dự án Maven chỉ định id của dự án
-Sự khác biệt chính giữa groupId và artifactId trong Maven là groupId chỉ định id của nhóm dự án trong khi artifactId chỉ định id của dự án. Tóm lại, những yếu tố này giúp tổ chức các dự án của tổ chức
-
Câu 2 :
Tại sao phải đảo ngược tên miền trong <groupId>vn.techmaster</groupId>?
-Việc đảo ngược tên miền trong groupId giúp chúng ta nhóm các modul của dự án dễ dàng hơn.
Câu 3 : SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng? 
- Quản lý bằng maven sử dụng dependency trong file pom
- Quản lý thư viện bằng Gradle
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 4 File pom.xml có tác dụng gì?
-File pom.xml là nơi khai báo tất cả những gì liên quan đến dự án được cấu hình qua maven, như khai báo các dependency, version của dự án, tên dự án, repossitory …
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 5 :Trong file pom.xml có các thẻ dependency. Ý nghĩa của chúng là gì?
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
-Spring Boot Starter Web cung cấp tất cả các phụ thuộc và cấu hình tự động cần thiết để phát triển các ứng dụng web.
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 6 :Ý nghĩa của @Controllerlà gì?
-Một annotation @Controller được khai báo cùng với định nghĩa của lớp Controller .
Controller là nơi nhận request từ người dùng, xử lý request, xây dựng dữ liệu cho view (model) và chọn view để trả lại kết quả của cho người dùng
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 7 : Ý nghĩa của @RequestMapping là gì? Nó có những tham số gì ngoài value?
 - Annotation @RequestMapping ánh xạ các HTTP request tới các phương thức xử lý của MVC và REST controller.
 - Ngoài Value còn có method , param , produces
 example :  @RequestMapping(value = "/ex/foos", headers = "key=val", method = GET,produces = MediaType.APPLICATION_XML_VALUE)
 -------------------------------------------------------------------------------------------------------------------------------------------------------------
 Câu 8 : Ý nghĩa của @RequestResponse khi đặt trong hàm hứng request để làm gì?
 - @ResponseBody cho bộ điều khiển biết rằng đối tượng được trả về sẽ tự động được tuần tự hóa thành JSON và được chuyển trở lại đối tượng HttpResponse
 -------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 9: Hãy trả lời khi nào thì dùng @PathVariable và khi nào nên dùng @RequestParam
- @RequestParam và @PathVariable annotation đều được sử dụng để trích xuất dữ liệu từ request URL nhưng thật ra chúng có một điểm khác biệt rất lớn về cách sử dụng 
- @RequestParam được dùng để trích xuất dữ liệu từ request query và có 4 tham số hỗ trợ :
+)defaultValue : Đây là giá trị mặc định nếu như giá trị của parameters trên URL rỗng.
+)name : tên của parameters binding
+)required : Cho biết tham số này là có bắt buộc hay không, nếu "required=true" thì thiếu parameters đó request sẽ fail.
+)value : đây là alias cho tên của thuộc tính
- @PathVariable thì được dùng để trích xuất dữ liệu từ URL path.

- Sử dụng : @RequestParam hữu ích hơn trên một ứng dụng web truyền thống nơi dữ liệu chủ yếu được truyền trong các tham số truy vấn trong khi @PathVariable phù hợp hơn với các dịch vụ web RESTful nơi URL chứa các giá trị.
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 10 : Thứ tự các thành phần đường dẫn @PathVariable có thể hoán đổi được không?
-Không hoán đổi được phải theo thứ tự
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 11 : @GetMapping khác gì so với @PostMapping?
-@GetMapping là ánh xạ đến HTTP request có method GET
-@PostMapiing là ánh xạ đên HTTP request có method POST
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 12 : Trong các annotation @RequestMapping, @GetMapping, @PostMapping… có tham số produces = MediaType.XXXX ý nghĩa tham số này là gì?
-Định dạng Content-Type
--------------------
-Match the value của Content-Type header giá trị bạn cần gửi cho ánh xạ phụ thuộc vào những gì ứng dụng khách đặt trong header.
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 13 :
@PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Message echoMessage(@RequestBody Message message){
    return message;
}
-@Requestbody giúp ta gửi dữ liệu dưới dạng body Json lên sever ví dụ ở đậy là object Json Message
--------------------------------------------------------------------------------------------------------------------------------------------------------------
Câu 14 :Cổng mặc định ứng dụng SpringBoot là 8080. Hãy google cách để thay đổi cổng lắng nghe mặc định
- Vào file application.properties  thay đổi port ex : server.port=8081
--------------------------------------------------------------------------------------------------------------------------------------------------------------

Lập Trình
Câu 1:
![anh1](https://user-images.githubusercontent.com/72613060/159362998-46100a3c-0951-409e-b39f-1654293bcdb0.png)
![anh2](https://user-images.githubusercontent.com/72613060/159363032-6c3c3bba-639d-4298-b96e-e791d819df92.png)

Câu 2 :

![anh3](https://user-images.githubusercontent.com/72613060/159363066-52e64a7d-a1e7-43e8-9230-ce7667d2f2c3.png)
![anh4](https://user-images.githubusercontent.com/72613060/159363083-fad20976-2ee2-4ecc-be0c-ee83dfa8f110.png)

Câu 3 :
![anh5](https://user-images.githubusercontent.com/72613060/159363116-acde43cb-6e92-4d0a-95ce-1eae4e977eb7.png)
![Screenshot 2022-03-22 034439](https://user-images.githubusercontent.com/72613060/159363129-a7d315b5-01e9-4b49-bc3c-7c1f94125c73.png)

Câu 4 :

![model](https://user-images.githubusercontent.com/72613060/159363574-4229adec-6c24-4e55-9edc-db7974c87171.png)
![anh7](https://user-images.githubusercontent.com/72613060/159363178-99b66122-d2a0-497a-842c-4bbd4ef7bdcb.png)
![anh8](https://user-images.githubusercontent.com/72613060/159363219-ea7827ac-d207-479c-9a97-075994340801.png)
![anh9](https://user-images.githubusercontent.com/72613060/159363224-fbb99370-11fb-4986-b2c3-6fc3166f3b09.png)
![anh10](https://user-images.githubusercontent.com/72613060/159363591-1c1f89d6-b9c9-4328-961a-048694b65327.png)

