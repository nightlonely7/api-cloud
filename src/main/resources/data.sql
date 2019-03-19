INSERT INTO Category(name) VALUES
('Phone'),('Tablet');

INSERT INTO Supplier(name, address, phone) VALUES
('Apple', 'American', '123'),
('Sony', 'Japan', '124'),
('Samsung', 'Korea', '125'),
('Oppo', 'China', '126');

ALTER TABLE Product
ALTER COLUMN description VARCHAR(MAX);
ALTER TABLE Product
ALTER COLUMN imgURL VARCHAR(MAX);

INSERT INTO Product(name, price, quantity, description, imgURL, category_id, supplier_id) VALUES
('iPhone 1', 1100, 100, 'This is iPhone 1','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWVXGYp0gvxnAsRjR04e5nQg2i7wOu_kRDiYQ1T8IbZBPMGChyhg', 1, 1),
('iPhone 2', 1200, 100, 'This is iPhone 2','https://st1.bgr.in/wp-content/uploads/2017/09/apple-iphone-8-plus-front.jpg', 1, 1),
('iPhone 3', 1300, 100, 'This is iPhone 3','https://cdn.tgdd.vn/Products/Images/42/78124/iphone-7-plus-32gb-gold-600x600-600x600.jpg', 1, 1),
('iPhone 4', 1400, 100, 'This is iPhone 4','https://pisces.bbystatic.com/image2/BestBuy_US/images/products/5872/5872535_sd.jpg;maxHeight=640;maxWidth=550', 1, 1),
('iPhone 5', 1500, 100, 'This is iPhone 5','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQryK3D0ZHMQyo8CsN-xph_eTlXjTHXmjJZU9qjL1P_PhZE0_4a', 1, 1),
('galaxy S1', 600, 100, 'This is galaxy S1','https://www.91-img.com/pictures/samsung-galaxy-s-duos-s7562-mobile-phone-large-1.jpg', 1, 2),
('galaxy S2', 700, 100, 'This is galaxy S2','https://cdn2.gsmarena.com/vv/bigpic/samsung-i9300-galaxy-s-iii-ofic.jpg', 1, 2),
('galaxy S3', 800, 100, 'This is galaxy S3','https://ss7.vzw.com/is/image/VerizonWireless/Samsung_Galaxy_S5_Black?$device-lg$', 1, 2),
('galaxy S4', 900, 100, 'This is galaxy S4','https://phuongtung.vn/pt/images/detailed/9/sieu-pham-galaxy-s-black.jpg', 1, 2),
('galaxy S5', 1000, 100, 'This is galaxy S5','https://www.credomobile.com/img/9/galaxy_s8_front_280x420.png', 1, 2);

INSERT INTO User(username, password, email, role) VALUES
('hoang', '$2a$11$JDbss487mfwgvAzx7g.6L.Y2hXwLh58861Q.wvLKIbfr0b9gDzh3u', 'hoang@gmail.com', 'ROLE_CUSTOMER'),
('vinh', '$2a$11$JDbss487mfwgvAzx7g.6L.Y2hXwLh58861Q.wvLKIbfr0b9gDzh3u', 'vinh@gmail.com', 'ROLE_CUSTOMER'),
('quang', '$2a$11$JDbss487mfwgvAzx7g.6L.Y2hXwLh58861Q.wvLKIbfr0b9gDzh3u', 'quang@gmail.com', 'ROLE_CUSTOMER'),
('admin', '$2a$11$JDbss487mfwgvAzx7g.6L.Y2hXwLh58861Q.wvLKIbfr0b9gDzh3u', 'admin@gmail.com', 'ROLE_ADMIN'),