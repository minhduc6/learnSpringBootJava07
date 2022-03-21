
Câu 1: Hãy trả lời khi nào thì dùng @PathVariable và khi nào nên dùng @RequestParam
- @RequestParam và @PathVariable annotation đều được sử dụng để trích xuất dữ liệu từ request URL nhưng thật ra chúng có một điểm khác biệt rất lớn về cách sử dụng 
- @RequestParam được dùng để trích xuất dữ liệu từ request query và có 4 tham số hỗ trợ :
+)defaultValue : Đây là giá trị mặc định nếu như giá trị của parameters trên URL rỗng.
+)name : tên của parameters binding
+)required : Cho biết tham số này là có bắt buộc hay không, nếu "required=true" thì thiếu parameters đó request sẽ fail.
+)value : đây là alias cho tên của thuộc tính
- @PathVariable thì được dùng để trích xuất dữ liệu từ URL path.

- Sử dụng : @RequestParam hữu ích hơn trên một ứng dụng web truyền thống nơi dữ liệu chủ yếu được truyền trong các tham số truy vấn trong khi @PathVariable phù hợp hơn với các dịch vụ web RESTful nơi URL chứa các giá trị.

Câu 2:
![anh1](https://user-images.githubusercontent.com/72613060/159362998-46100a3c-0951-409e-b39f-1654293bcdb0.png)
![anh2](https://user-images.githubusercontent.com/72613060/159363032-6c3c3bba-639d-4298-b96e-e791d819df92.png)

Câu 3 :

![anh3](https://user-images.githubusercontent.com/72613060/159363066-52e64a7d-a1e7-43e8-9230-ce7667d2f2c3.png)
![anh4](https://user-images.githubusercontent.com/72613060/159363083-fad20976-2ee2-4ecc-be0c-ee83dfa8f110.png)

Câu 4 :
![anh5](https://user-images.githubusercontent.com/72613060/159363116-acde43cb-6e92-4d0a-95ce-1eae4e977eb7.png)
![Screenshot 2022-03-22 034439](https://user-images.githubusercontent.com/72613060/159363129-a7d315b5-01e9-4b49-bc3c-7c1f94125c73.png)

Câu 5 :

![model](https://user-images.githubusercontent.com/72613060/159363574-4229adec-6c24-4e55-9edc-db7974c87171.png)
![anh7](https://user-images.githubusercontent.com/72613060/159363178-99b66122-d2a0-497a-842c-4bbd4ef7bdcb.png)
![anh8](https://user-images.githubusercontent.com/72613060/159363219-ea7827ac-d207-479c-9a97-075994340801.png)
![anh9](https://user-images.githubusercontent.com/72613060/159363224-fbb99370-11fb-4986-b2c3-6fc3166f3b09.png)
![anh10](https://user-images.githubusercontent.com/72613060/159363591-1c1f89d6-b9c9-4328-961a-048694b65327.png)

