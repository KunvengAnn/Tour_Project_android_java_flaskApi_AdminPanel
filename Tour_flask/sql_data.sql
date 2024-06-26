
/****** Object:  Database [DBTour]    Script Date: 6/13/2024 10:48:09 PM ******/
CREATE DATABASE [DBTour]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DBTour', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\DBTour.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DBTour_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\DBTour_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [DBTour] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DBTour].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DBTour] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DBTour] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DBTour] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DBTour] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DBTour] SET ARITHABORT OFF 
GO
ALTER DATABASE [DBTour] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DBTour] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DBTour] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DBTour] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DBTour] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DBTour] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DBTour] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DBTour] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DBTour] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DBTour] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DBTour] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DBTour] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DBTour] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DBTour] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DBTour] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DBTour] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DBTour] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DBTour] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DBTour] SET  MULTI_USER 
GO
ALTER DATABASE [DBTour] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DBTour] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DBTour] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DBTour] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DBTour] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DBTour] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [DBTour] SET QUERY_STORE = ON
GO
ALTER DATABASE [DBTour] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [DBTour]
GO
/****** Object:  Table [dbo].[Bookings]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bookings](
	[booking_id] [int] IDENTITY(1,1) NOT NULL,
	[tour_id] [int] NOT NULL,
	[customer_id] [int] NOT NULL,
	[booking_date] [date] NOT NULL,
	[number_of_child] [int] NOT NULL,
	[number_of_adult] [int] NOT NULL,
	[total_price] [float] NOT NULL,
 CONSTRAINT [PK_Bookings] PRIMARY KEY CLUSTERED 
(
	[booking_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[customer_id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](5) NOT NULL,
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[customer_phone] [nvarchar](10) NOT NULL,
	[customer_email] [nvarchar](max) NOT NULL,
	[customer_from_country] [nvarchar](50) NOT NULL,
	[Date_of_department] [date] NOT NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PackageTours]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PackageTours](
	[tour_id] [int] IDENTITY(1,1) NOT NULL,
	[tour_name] [nvarchar](max) NOT NULL,
	[tour_description] [nvarchar](max) NOT NULL,
	[tour_duration] [int] NOT NULL,
	[tour_price] [float] NOT NULL,
	[tour_location] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_PackageTours] PRIMARY KEY CLUSTERED 
(
	[tour_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payments]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payments](
	[payment_id] [int] IDENTITY(1,1) NOT NULL,
	[booking_id] [int] NOT NULL,
	[payment_date] [date] NOT NULL,
	[amount_paid] [float] NOT NULL,
	[payment_status] [bit] NOT NULL,
 CONSTRAINT [PK_Payments] PRIMARY KEY CLUSTERED 
(
	[payment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Refunds]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Refunds](
	[refund_id] [int] NOT NULL,
	[booking_id] [int] NOT NULL,
	[refund_date] [date] NOT NULL,
	[refund_amount] [float] NOT NULL,
	[refund_reason] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Refunds] PRIMARY KEY CLUSTERED 
(
	[refund_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Staffs]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Staffs](
	[staff_id] [int] IDENTITY(1,1) NOT NULL,
	[payment_id] [int] NOT NULL,
	[staff_name] [nvarchar](255) NOT NULL,
	[staff_phone] [nvarchar](10) NOT NULL,
	[staff_address] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Staffs] PRIMARY KEY CLUSTERED 
(
	[staff_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Bookings] ON 

INSERT [dbo].[Bookings] ([booking_id], [tour_id], [customer_id], [booking_date], [number_of_child], [number_of_adult], [total_price]) VALUES (2, 2, 2, CAST(N'2024-05-17' AS Date), 0, 1, 30)
INSERT [dbo].[Bookings] ([booking_id], [tour_id], [customer_id], [booking_date], [number_of_child], [number_of_adult], [total_price]) VALUES (3, 1, 3, CAST(N'2024-05-17' AS Date), 0, 1, 99.99)
INSERT [dbo].[Bookings] ([booking_id], [tour_id], [customer_id], [booking_date], [number_of_child], [number_of_adult], [total_price]) VALUES (4, 1, 4, CAST(N'2024-05-22' AS Date), 0, 1, 99.99)
INSERT [dbo].[Bookings] ([booking_id], [tour_id], [customer_id], [booking_date], [number_of_child], [number_of_adult], [total_price]) VALUES (5, 1, 5, CAST(N'2024-05-23' AS Date), 0, 2, 199.98)
SET IDENTITY_INSERT [dbo].[Bookings] OFF
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([customer_id], [title], [first_name], [last_name], [customer_phone], [customer_email], [customer_from_country], [Date_of_department]) VALUES (2, N'Mr', N'Nguyen', N'van aa', N'09778', N'aa@gmail.com', N'cn', CAST(N'2024-05-17' AS Date))
INSERT [dbo].[Customer] ([customer_id], [title], [first_name], [last_name], [customer_phone], [customer_email], [customer_from_country], [Date_of_department]) VALUES (3, N'Mr', N'veng', N'ann', N'08984', N'venganncoco@gmail.com', N'Cambodia', CAST(N'2024-05-17' AS Date))
INSERT [dbo].[Customer] ([customer_id], [title], [first_name], [last_name], [customer_phone], [customer_email], [customer_from_country], [Date_of_department]) VALUES (4, N'Mr', N'veng', N'ann', N'05854', N'venganncoco@gmail.com', N'cn', CAST(N'2024-05-22' AS Date))
INSERT [dbo].[Customer] ([customer_id], [title], [first_name], [last_name], [customer_phone], [customer_email], [customer_from_country], [Date_of_department]) VALUES (5, N'Mr', N'SorakpheaNukma', N'Mao', N'0904176790', N'maosorakpheanukma@gmail.com', N'cambodia', CAST(N'2024-05-24' AS Date))
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[PackageTours] ON 

INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (1, N'Siem Reap Evening Food Tour - Inclusive 10 Local Tastings', N'There are plenty of opportunities for adventures with street food - make sure yours is memorable for the right reasons!  On this tour, your guide shows you the highlights of Siem Reap’s night markets.  Taste authentic Cambodian BBQ and spring rolls and, if you feel adventurous, BBQ snails and other insect dishes. Finish up with a nightcap at the Sombai Siem Reap Liqueur House, where you''ll see how Cambodian rice whiskey is made and infused.', 5, 99.99, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (2, N'Bike the Siem Reap Countryside with Local Expert', N'Escape the crazy of Siem Reap for the serenity of rural Cambodian life on this intimate mountain bike tour, limited to just eight travelers. Your guide will lead you along the sorts of dirt roads only locals know to enjoy a close encounter with real Cambodians. Stop at rice fields, a local market, a mushroom farm, a lotus farm, and handicraft workshops, and learn how rice wine is distilled.', 2, 30, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (3, N'Siem Reap Countryside Sunset Ride', N'Siem Reap is at its most atmospheric at sunset and this bike tour lets you explore the scenic countryside. Leave the busy streets of Siem Reap behind and cycle along dirt roads and country lanes, past palm forests and lush rice paddies. Stop along the way to meet the locals at a traditional village, then continue to a viewpoint to watch the magical sunset.', 5, 35, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (4, N'Angkor Sunrise Bike Tour with Breakfast and Lunch Included', N'Experience the magic of sunrise over Angkor Wat with this early riser tour. After watching the sunrise, set out to explore the Angkor Temples by bike, allowing you to escape the crowds and admire the scenery as you follow traffic-free trails around the ruins. Take in highlights including Bayon, Preah Khan, Ta Prohm, and Angkor Thom, and enjoy a tasty Cambodian-style breakfast and lunch by the temples.', 9, 79, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (5, N'Cycle the Angkor Backroads - Inclusive lunch at local house', N'Pedal to several temples in the Angkor Archaeological Park on a bike tour that offers a different perspective on Siem Reap''s major attraction. Your guide shares commentary about the UNESCO World Heritage Site as you explore Angkor Wat, Angkor Thom, Bayon, and Ta Prohm. Take in the majesty of the Angkor complex as you ride along dirt lanes and paved roads. Suitable for reasonably fit people who are comfortable biking.', 7, 55, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (6, N'Angkor Bike & Gondola Ride at Twilight', N'Cycle through Siem Reap and the surrounding Angkor countryside and see the temples bathed in a warm, golden light.Explore Angkor Thom, Bayon, and Angkor jungle trails at dusk, and learn more about this temple complex’s rich history. Later, take a tranquil dragon boat ride on the moat of Angkor Thom, enjoying local delicacies and refreshing cold beverages available on board, including soft drinks and some beers. This is the perfect way to enjoy an unforgettable sunset tour.', 4, 65, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (7, N'Siem Reap Morning City Bike Tour', N'Break away from the busy Siem Reap and head into the Cambodian countryside for the day on this cycling tour around some of the region’s many rural villages. Hop on your mountain bike, provided for the tour, and cycle through the Cambodian jungle to highlights like a secluded Buddhist temple and the West Baray reservoir. Visit village artisans and learn how they ply their trade, whether they’re weaving baskets, distilling rice wine or making Khmer noodles.', 5, 45, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (8, N'Floating Village Bike & Boat - Inclusive Lunch & Boat Ticket', N'Travel like a local through the countryside around Siem Reap on this intimate bike tour with vehicle support—including door-to-door round-trip transfers. Pedal around 20 miles (32 kilometers) through the countryside, mainly on the flat, with a guide and a group no larger than eight, then explore the floating villages of Kompong Phluk on a sunset Tonlé Sap cruise. Along the way, enjoy lunch and snacks, with ample hydration.', 10, 75, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (9, N'Angkor Sunrise Small Group Tour Inclusive Breakfast and lunch', N'Discover the ancient secrets of the Angkor Temples by bicycle, following the spiritual adventure of the World Heritage. Step back in time as you weave your way through jungle-clad ruins, treading in the footsteps of Hindu holy men, Buddhist rulers, and warrior kings of days gone by. Get off the beaten path and unlock the hidden mysteries of this sacred city through this private tour.', 8, 65, N'SiemReap')
INSERT [dbo].[PackageTours] ([tour_id], [tour_name], [tour_description], [tour_duration], [tour_price], [tour_location]) VALUES (10, N'Angkor Wat Small Group Tour Inclusive lunch', N'A full-day trip to the largest religious monument in the world at the 400-acre Angkor Wat complex, deep in the jungles of Cambodia. Stroll through the intricately carved hallways and take your time to see every part of this incredible complex. See the ancient city area of Angkor Thom, with its carved-stone faces standing as silent witnesses to former days of glory, and Ta Prohm, a temple complex slowly being reclaimed by the jungle.This is an awe-inspiring and spiritual experience awaits you as you explore this incredible site with the professional historian guide.', 7, 49, N'SiemReap')
SET IDENTITY_INSERT [dbo].[PackageTours] OFF
GO
SET IDENTITY_INSERT [dbo].[Payments] ON 

INSERT [dbo].[Payments] ([payment_id], [booking_id], [payment_date], [amount_paid], [payment_status]) VALUES (2, 2, CAST(N'2024-05-17' AS Date), 30, 1)
INSERT [dbo].[Payments] ([payment_id], [booking_id], [payment_date], [amount_paid], [payment_status]) VALUES (3, 3, CAST(N'2024-05-17' AS Date), 99.99, 1)
INSERT [dbo].[Payments] ([payment_id], [booking_id], [payment_date], [amount_paid], [payment_status]) VALUES (4, 4, CAST(N'2024-05-22' AS Date), 99.99, 1)
INSERT [dbo].[Payments] ([payment_id], [booking_id], [payment_date], [amount_paid], [payment_status]) VALUES (5, 5, CAST(N'2024-05-23' AS Date), 199.98, 1)
SET IDENTITY_INSERT [dbo].[Payments] OFF
GO
SET IDENTITY_INSERT [dbo].[Staffs] ON 

INSERT [dbo].[Staffs] ([staff_id], [payment_id], [staff_name], [staff_phone], [staff_address]) VALUES (6, 2, N'Nguyen van A', N'03495766', N'hanoi')
INSERT [dbo].[Staffs] ([staff_id], [payment_id], [staff_name], [staff_phone], [staff_address]) VALUES (7, 3, N'Nguyen van BB', N'034957665', N'thai nguyen')
INSERT [dbo].[Staffs] ([staff_id], [payment_id], [staff_name], [staff_phone], [staff_address]) VALUES (8, 4, N'Nguyen van A', N'0567575', N'PP')
INSERT [dbo].[Staffs] ([staff_id], [payment_id], [staff_name], [staff_phone], [staff_address]) VALUES (9, 5, N'Nguyen van BB', N'0567575dfs', N'PP')
SET IDENTITY_INSERT [dbo].[Staffs] OFF
GO
ALTER TABLE [dbo].[Bookings]  WITH CHECK ADD  CONSTRAINT [FK_Bookings_Customer] FOREIGN KEY([customer_id])
REFERENCES [dbo].[Customer] ([customer_id])
GO
ALTER TABLE [dbo].[Bookings] CHECK CONSTRAINT [FK_Bookings_Customer]
GO
ALTER TABLE [dbo].[Bookings]  WITH CHECK ADD  CONSTRAINT [FK_Bookings_PackageTours] FOREIGN KEY([tour_id])
REFERENCES [dbo].[PackageTours] ([tour_id])
GO
ALTER TABLE [dbo].[Bookings] CHECK CONSTRAINT [FK_Bookings_PackageTours]
GO
ALTER TABLE [dbo].[Payments]  WITH CHECK ADD  CONSTRAINT [FK_Payments_Bookings] FOREIGN KEY([booking_id])
REFERENCES [dbo].[Bookings] ([booking_id])
GO
ALTER TABLE [dbo].[Payments] CHECK CONSTRAINT [FK_Payments_Bookings]
GO
ALTER TABLE [dbo].[Refunds]  WITH CHECK ADD  CONSTRAINT [FK_Refunds_Bookings] FOREIGN KEY([booking_id])
REFERENCES [dbo].[Bookings] ([booking_id])
GO
ALTER TABLE [dbo].[Refunds] CHECK CONSTRAINT [FK_Refunds_Bookings]
GO
ALTER TABLE [dbo].[Staffs]  WITH CHECK ADD  CONSTRAINT [FK_Staffs_Payments] FOREIGN KEY([payment_id])
REFERENCES [dbo].[Payments] ([payment_id])
GO
ALTER TABLE [dbo].[Staffs] CHECK CONSTRAINT [FK_Staffs_Payments]
GO
/****** Object:  StoredProcedure [dbo].[SP_Booking]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  StoredProcedure [dbo].[SP_Customer]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_Customer]
    @action varchar(100) = null,
    @customer_id int = null,
    @title nvarchar(5) = null,
    @first_name nvarchar(50) = null,
    @last_name nvarchar(50) = null,
    @customer_phone nvarchar(10) = null,
    @customer_email nvarchar(max) = null,
    @customer_from_country nvarchar(50) = null,
    @date_of_department date = null

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
        INSERT INTO Customer (title, first_name, last_name, customer_phone,customer_email,customer_from_country,Date_of_department) 
        VALUES (@title, @first_name, @last_name, @customer_phone,@customer_email,@customer_from_country,@Date_of_department);
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
GO
/****** Object:  StoredProcedure [dbo].[SP_PackageTour]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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

GO
/****** Object:  StoredProcedure [dbo].[SP_Payment]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  StoredProcedure [dbo].[SP_Refund]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
/****** Object:  StoredProcedure [dbo].[SP_Staff]    Script Date: 6/13/2024 10:48:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
GO
USE [master]
GO
ALTER DATABASE [DBTour] SET  READ_WRITE 
GO
