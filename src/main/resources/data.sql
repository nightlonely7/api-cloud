INSERT INTO Category(name) VALUES
('Phone'),('Tablet');

INSERT INTO Supplier(name, address, phone) VALUES
('Apple', 'American', '123'),
('Sony', 'Japan', '124'),
('Samsung', 'Korea', '125'),
('Oppo', 'China', '126');

INSERT INTO Product(name, price, quantity, description, category_id, supplier_id) VALUES
('iPhone 1', 1100, 100, 'This is iPhone 1', 1, 1),
('iPhone 2', 1200, 100, 'This is iPhone 2', 1, 1),
('iPhone 3', 1300, 100, 'This is iPhone 3', 1, 1),
('iPhone 4', 1400, 100, 'This is iPhone 4', 1, 1),
('iPhone 5', 1500, 100, 'This is iPhone 5', 1, 1),
('galaxy S1', 600, 100, 'This is galaxy S1', 1, 2),
('galaxy S2', 700, 100, 'This is galaxy S2', 1, 2),
('galaxy S3', 800, 100, 'This is galaxy S3', 1, 2),
('galaxy S4', 900, 100, 'This is galaxy S4', 1, 2),
('galaxy S5', 1000, 100, 'This is galaxy S5', 1, 2);

INSERT INTO User(username, password, email, role) VALUES
('hoang', '$2a$11$JDbss487mfwgvAzx7g.6L.Y2hXwLh58861Q.wvLKIbfr0b9gDzh3u', 'hoang@gmail.com', 'ROLE_CUSTOMER'),
('vinh', '$2a$11$JDbss487mfwgvAzx7g.6L.Y2hXwLh58861Q.wvLKIbfr0b9gDzh3u', 'vinh@gmail.com', 'ROLE_CUSTOMER'),
('quang', '$2a$11$JDbss487mfwgvAzx7g.6L.Y2hXwLh58861Q.wvLKIbfr0b9gDzh3u', 'quang@gmail.com', 'ROLE_CUSTOMER'),
('admin', '$2a$11$JDbss487mfwgvAzx7g.6L.Y2hXwLh58861Q.wvLKIbfr0b9gDzh3u', 'admin@gmail.com', 'ROLE_ADMIN'),