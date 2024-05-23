-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[SP_PackageTour]
	@action varchar(100) = null,
	@tour_id int = null,
	@tour_name nvarchar(max) = null,
	@tour_description nvarchar(max) = null,
	@tour_duration int = null,
	@tour_price float = null,
	@tour_location nvarchar(255) = null

AS
BEGIN
	IF(@action='getAll_Tour')
	BEGIN 
		SELECT * FROM PackageTours;
	END
	ELSE IF(@action='getTourByID')
	BEGIN
		SELECT tour_id,tour_name,tour_description,tour_duration,tour_price,tour_location FROM PackageTours WHERE tour_id=@tour_id;
	END
	ELSE IF(@action='add_Tour')
	BEGIN
		INSERT INTO PackageTours (tour_name, tour_description, tour_duration, tour_price, tour_location) 
        VALUES (@tour_name, @tour_description, @tour_duration, @tour_price, @tour_location);
	END
	ELSE IF(@action='updateTour')
	BEGIN
		UPDATE PackageTours SET tour_name=@tour_name,tour_description=@tour_description,tour_price=@tour_price,tour_duration=@tour_duration,tour_location=@tour_location
		WHERE tour_id=@tour_id;
	END
	ELSE IF(@action='deleteTour')
	BEGIN
		DELETE FROM PackageTours WHERE tour_id = @tour_id;
	END
END


--==============================================================
--Customer==============================================================
CREATE PROCEDURE [dbo].[SP_Customer]
    @action varchar(100) = null,
    @customer_id int = null,
    @title nvarchar(5) = null,
    @first_name nvarchar(50) = null,
    @last_name nvarchar(50) = null,
    @customer_phone nvarchar(10) = null,
    @customer_email nvarchar(max) = null,
    @customer_from_country nvarchar(50) = null,
    @Date_of_department date = null

AS
BEGIN
    IF(@action='getAll_Customer')
    BEGIN
        SELECT * FROM Customer;
    END
    ELSE IF(@action='getCustomerByID')
    BEGIN
        SELECT customer_id,title,first_name,last_name,customer_phone,customer_email,customer_from_country,Date_of_department FROM Customer WHERE customer_id=@customer_id;
    END
    ELSE IF(@action='addCustomer')
    BEGIN
        INSERT INTO Customer (customer_id, title, first_name, last_name, customer_phone,customer_email,customer_from_country,Date_of_department) 
        VALUES (@customer_id, @title, @first_name, @last_name, @customer_phone,@customer_email,@customer_from_country,@Date_of_department);
    END
    ELSE IF(@action='updateCustomer')
    BEGIN
        UPDATE Customer 
        SET 
            title = @title,
            first_name = @first_name,
            last_name = @last_name,
            customer_phone = @customer_phone,
            customer_email = @customer_email,
            customer_from_country = @customer_from_country,
            Date_of_department = @Date_of_department
        WHERE 
            customer_id = @customer_id;
    END
    ELSE IF(@action='deleteCustomerID')
    BEGIN
        DELETE FROM Customer WHERE customer_id = @customer_id;
    END
END
--customer trigger==================================================================
CREATE TRIGGER trg_Customer
ON Customer
AFTER INSERT
AS
BEGIN
    DECLARE @CustomerID int;
    SELECT @CustomerID = customer_id FROM inserted;
END;

--Booking ========================================================
CREATE PROCEDURE [dbo].[SP_Booking]
    @action varchar(100) = null,
    @booking_id int = null,
    @tour_id int = null,
    @customer_id int = null,
    @booking_date date = null,
    @number_of_child int = null,
    @number_of_adult int = null,
    @total_price float = null
AS
BEGIN
    IF (@action = 'getAll_Booking')
    BEGIN
        SELECT * FROM Bookings;
    END
    ELSE IF (@action = 'getBookingByID')
    BEGIN
        SELECT * FROM Bookings b
		INNER JOIN Customer c
		ON c.customer_id = b.customer_id
		INNER JOIN Payments p
		ON p.booking_id = b.booking_id
		INNER JOIN PackageTours pc
		ON pc.tour_id = b.tour_id
		WHERE b.booking_id = @booking_id;
    END
    ELSE IF (@action = 'addBooking')
    BEGIN
        INSERT INTO Bookings (tour_id, customer_id, booking_date, number_of_child, number_of_adult, total_price) 
        VALUES (@tour_id, @customer_id, @booking_date, @number_of_child, @number_of_adult, @total_price);
    END
    ELSE IF (@action = 'updateBooking')
    BEGIN
        UPDATE Bookings
        SET 
            tour_id = @tour_id,
            customer_id = @customer_id,
            booking_date = @booking_date,
            number_of_child = @number_of_child,
            number_of_adult = @number_of_adult,
            total_price = @total_price
        WHERE 
            booking_id = @booking_id;
    END
    ELSE IF (@action = 'deleteBooking')
    BEGIN
        DELETE FROM Bookings WHERE booking_id = @booking_id;
    END
END
--Bookings==========================================
--CREATE TRIGGER trg_BookingAdded
--ON Bookings
--AFTER INSERT
--AS
--BEGIN
--    DECLARE @BookingID int;
--    SELECT @BookingID = booking_id FROM inserted;

--    -- Retrieve all fields for the newly inserted booking
--    SELECT * FROM Bookings WHERE booking_id = @BookingID;
--END;
--booking==============



--Payment=============================================
CREATE PROCEDURE [dbo].[SP_Payment]
    @action varchar(100) = null,
    @payment_id int = null,
    @booking_id int = null,
    @payment_date date = null,
    @amount_paid float = null,
    @payment_status bit = null
AS
BEGIN
    IF (@action = 'getAll_Payment')
    BEGIN
        SELECT * FROM Payments;
    END
    ELSE IF (@action = 'getPaymentByID')
    BEGIN
        SELECT * FROM Payments WHERE payment_id = @payment_id;
    END
    ELSE IF (@action = 'addPayment')
    BEGIN
        INSERT INTO Payments (booking_id, payment_date, amount_paid, payment_status) 
        VALUES (@booking_id, @payment_date, @amount_paid, @payment_status);
    END
    ELSE IF (@action = 'updatePayment')
    BEGIN
        UPDATE Payments
        SET 
            booking_id = @booking_id,
            payment_date = @payment_date,
            amount_paid = @amount_paid,
            payment_status = @payment_status
        WHERE 
            payment_id = @payment_id;
    END
	ELSE IF(@action='updateOnlyOneField')
	BEGIN
		UPDATE Payments 
		SET
			payment_status = @payment_status
		WHERE
			payment_id=@payment_id
	END
    ELSE IF (@action = 'deletePayment')
    BEGIN
        DELETE FROM Payments WHERE payment_id = @payment_id;
    END
	ELSE IF(@action='getCustomerPaymentPKTour')
	BEGIN
		SELECT *
		FROM Payments p
		INNER JOIN Bookings b
		ON p.booking_id = b.booking_id  
		INNER JOIN Customer c 
		ON c.customer_id = b.customer_id
		INNER JOIN PackageTours pkt
		ON pkt.tour_id = b.tour_id
	END
END

--Refunds=============================================
CREATE PROCEDURE [dbo].[SP_Refund]
    @action varchar(100) = null,
    @refund_id int = null,
    @booking_id int = null,
    @refund_date date = null,
    @refund_amount float = null,
    @refund_reason nvarchar(MAX) = null
AS
BEGIN
    IF (@action = 'getAll_Refund')
    BEGIN
        SELECT * FROM Refunds;
    END
    ELSE IF (@action = 'getRefundByID')
    BEGIN
        SELECT * FROM Refunds WHERE refund_id = @refund_id;
    END
    ELSE IF (@action = 'addRefund')
    BEGIN
        INSERT INTO Refunds (booking_id, refund_date, refund_amount, refund_reason) 
        VALUES (@booking_id, @refund_date, @refund_amount, @refund_reason);
    END
    ELSE IF (@action = 'updateRefund')
    BEGIN
        UPDATE Refunds
        SET 
            booking_id = @booking_id,
            refund_date = @refund_date,
            refund_amount = @refund_amount,
            refund_reason = @refund_reason
        WHERE 
            refund_id = @refund_id;
    END
    ELSE IF (@action = 'deleteRefund')
    BEGIN
        DELETE FROM Refunds WHERE refund_id = @refund_id;
    END
END

--Staffs================================================
CREATE PROCEDURE [dbo].[SP_Staff]
    @action varchar(100) = null,
    @staff_id int = null,
    @payment_id int = null,
    @staff_name nvarchar(255) = null,
    @staff_phone nvarchar(10) = null,
    @staff_address nvarchar(255) = null
AS
BEGIN
    IF (@action = 'getAll_Staff')
    BEGIN
        SELECT * FROM Staffs;
    END
    ELSE IF (@action = 'getStaffByID')
    BEGIN
        SELECT * FROM Staffs WHERE staff_id = @staff_id;
    END
    ELSE IF (@action = 'addStaff')
    BEGIN
		 -- Check if payment_id exists
        IF NOT EXISTS (SELECT 1 FROM Payments WHERE payment_id = @payment_id)
        BEGIN
            RAISERROR ('Invalid payment_id', 16, 1);
            RETURN;
        END

        INSERT INTO Staffs (payment_id, staff_name, staff_phone, staff_address) 
        VALUES (@payment_id, @staff_name, @staff_phone, @staff_address);
    END
    ELSE IF (@action = 'updateStaff')
    BEGIN
        UPDATE Staffs
        SET 
            payment_id = @payment_id,
            staff_name = @staff_name,
            staff_phone = @staff_phone,
            staff_address = @staff_address
        WHERE 
            staff_id = @staff_id;
    END
    ELSE IF (@action = 'deleteStaff')
    BEGIN
        DELETE FROM Staffs WHERE staff_id = @staff_id;
    END
END


