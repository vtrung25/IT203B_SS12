CREATE DATABASE IF NOT EXISTS hospital;
USE hospital;

-- Bảng thuốc
CREATE TABLE medicines
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(100),
    price DOUBLE,
    stock INT
);

-- Bảng đơn thuốc
CREATE TABLE prescriptions
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    medicine_id   INT,
    quantity_sold INT,
    sale_date     DATE,
    FOREIGN KEY (medicine_id) REFERENCES medicines (id)
);

-- Dữ liệu mẫu
INSERT INTO medicines(name, price, stock)
VALUES ('Paracetamol', 10, 100),
       ('Aspirin', 20, 50);

INSERT INTO prescriptions(medicine_id, quantity_sold, sale_date)
VALUES (1, 5, '2026-03-20'),
       (2, 3, '2026-03-20');

-- Procedure 1
DELIMITER //

CREATE PROCEDURE CalculatePrescriptionTotal(
    IN p_id INT,
    OUT p_total DECIMAL(18, 2)
)
BEGIN
    SELECT m.price * p.quantity_sold
    INTO p_total
    FROM prescriptions p
             JOIN medicines m ON p.medicine_id = m.id
    WHERE p.id = p_id;
END //

-- Procedure 2
CREATE PROCEDURE GetDailyRevenue(
    IN p_date DATE,
    OUT p_revenue DECIMAL(18, 2)
)
BEGIN
    SELECT SUM(m.price * p.quantity_sold)
    INTO p_revenue
    FROM prescriptions p
             JOIN medicines m ON p.medicine_id = m.id
    WHERE p.sale_date = p_date;
END //

DELIMITER ;