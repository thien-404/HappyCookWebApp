-- Create database
CREATE DATABASE test;
GO 
USE test;

-- Create Account table
CREATE TABLE Account (
    AccId INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(255) NOT NULL,
    Phone NVARCHAR(50),
    Email NVARCHAR(255) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    Address NVARCHAR(255),
    Status INT, -- 0: BLOCK, 1: ACTIVE
    Role NVARCHAR(50) -- 0: STAFF, 1: CUSS
);

-- Create Orders table
CREATE TABLE Orders (
    OrderId INT PRIMARY KEY IDENTITY(1,1),
    order_date DATETIME NOT NULL,
    TotalPrices DECIMAL(10, 2) NOT NULL,
    Status INT, --1: pending; 2: processing; 3: complete; 4: cancel
    AccId INT NOT NULL,
    FOREIGN KEY (AccId) REFERENCES Account(AccID)
);

-- Create Discount table
CREATE TABLE Discount (
    discountID INT PRIMARY KEY IDENTITY(0,1),
    DateStart DATE,
    DateEnd DATE,
    PercentSale DECIMAL(5, 2) NOT NULL
);

-- Create Meal table
CREATE TABLE Meal (
    MealID INT PRIMARY KEY IDENTITY(1,1),
    MealName NVARCHAR(255) NOT NULL,
    MealPrice DECIMAL(10, 2) NOT NULL,
    Content NVARCHAR(MAX),
    Description NVARCHAR(MAX),
    ImageUrl NVARCHAR(255),
    MealStatus INT NOT NULL,
    discountID INT,
    typeOfFood INT, -- 1: MẶN, 2: CHAY, 3: NƯỚC, 4: KHÁC
    FOREIGN KEY (discountID) REFERENCES Discount(discountID)
);

-- Create OrderMeal table
CREATE TABLE OrderMeal (
    OrderMealID INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    MealID INT,
    OrderId INT,
    Quantity INT,
    IsIngredient INT, -- 0: not ingredient purchase, 1: ingredient purchase
    FOREIGN KEY (OrderId) REFERENCES Orders (OrderId),
    FOREIGN KEY (MealID) REFERENCES Meal(MealID)
);

-- Create Ingredient table
CREATE TABLE Ingredient (
    IngredientID INT PRIMARY KEY IDENTITY(1,1),
    IngredientName NVARCHAR(255) NOT NULL,
    IngredientPrice DECIMAL(10, 2) NOT NULL,
    IngredientStatus INT,
    Unit NVARCHAR(50),
    Role NVARCHAR(50)
);

-- Create MealIngredient table
CREATE TABLE MealIngredient (
    MealID INT NOT NULL,
    IngredientID INT NOT NULL,
    Quantity DECIMAL(10, 0) NOT NULL,
    PRIMARY KEY (MealID, IngredientID),
    FOREIGN KEY (MealID) REFERENCES Meal(MealID),
    FOREIGN KEY (IngredientID) REFERENCES Ingredient(IngredientID)
);

-- Create WeeklyMenu table
CREATE TABLE WeeklyMenu (
    WeekMenuId INT PRIMARY KEY IDENTITY(1,1),
    weekDay NVARCHAR(50) NOT NULL,
    WeekNum INT NOT NULL,
    Date DATE NOT NULL
);

-- Create WeeklyMeal table
CREATE TABLE WeeklyMeal (
	WeeklyMealID int primary key IDENTITY(1,1),
    MealID INT NOT NULL,
    WeekMenuId INT NOT NULL,  -- Foreign key to WeeklyMenu
    mealTime NVARCHAR(50) NOT NULL,
    FOREIGN KEY (MealID) REFERENCES Meal(MealID),
    FOREIGN KEY (WeekMenuId) REFERENCES WeeklyMenu(WeekMenuId)
);

-- Create ManageWeeklyMenu table
CREATE TABLE ManageWeeklyMenu (
    AccId INT NOT NULL,  -- Foreign key to Account
    WeekMenuId INT NOT NULL,  -- Foreign key to WeeklyMenu
    PRIMARY KEY (AccId, WeekMenuId),
    FOREIGN KEY (AccId) REFERENCES Account(AccId),
    FOREIGN KEY (WeekMenuId) REFERENCES WeeklyMenu(WeekMenuId)
);
go
-- Insert sample data into Account table
INSERT INTO Account (FullName, Phone, Email, Password, Address, Status, Role) VALUES
('Admin', '123-456-7890', 'admin@gmail.com', '123', N'Thủ Đức', 1, 'AD'),
('John Doe', '123-456-7890', 'johndoe@gmail.com', '111', N'Quận 9', 1, 'AD'),
('Alice Nguyen', '098-765-4321', 'alice@gmail.com', 'alice123', N'Quận 5', 1, 'US'),
('Bob Tran', '098-111-2222', 'bob@gmail.com', 'bob123', N'Quận 3', 1, 'US'),
('Carol Le', '098-333-4444', 'carol@gmail.com', 'carol123', N'Quận 1', 1, 'US'),
(N'Hà', '127-654-3210', 'ha@gmail.com', '1213', N'Quận 7', 1, 'US'),
(N'Hà Mi', '017-654-3210', 'mi@gmail.com', 'mcca123', N'Quận 2', 1, 'US'),
(N'Văn A', '127-354-3210', 'a@gmail.com', '123', N'Quận 10', 1, 'US'),
(N'Thiên', '912-054-3210', 'thien@gmail.com', '123', N'Quận 2', 1, 'US');
go

-- Insert sample data into Orders table

INSERT INTO Orders (order_date, TotalPrices, Status, AccId) VALUES
('2024-07-14', 100.00, 1, 4),
('2024-07-17', 100.00, 1, 6),
('2024-07-15', 150.00, 1, 2),
('2024-07-01', 200.00, 2, 5),
('2024-07-02', 250.00, 2, 5);
go
-- Insert sample data into Discount table
INSERT INTO Discount (PercentSale) VALUES(0);
INSERT INTO Discount (DateStart, DateEnd, PercentSale) VALUES
('2024-06-01', '2024-06-30', 10.00),
('2024-07-01', '2024-07-15', 15.00),
('2024-07-16', '2024-07-31', 20.00);
go
-- Insert sample data into Meal table
INSERT INTO Meal (MealName, MealPrice, Content, Description, ImageUrl, MealStatus, typeOfFood, discountID) VALUES
('Vegan Salad', 5.99, 'A healthy vegan salad with fresh vegetables.', 'Step 1. Wash all vegetables thoroughly. Step 2. Chop lettuce, tomatoes, and other vegetables. Step 3. Mix all ingredients in a large bowl. Step 4. Add your favorite dressing and toss the salad.', 'Vegan-Salad.jpg', 1, 2, 1),
('Beef Steak', 25.00, 'Juicy beef steak with garlic butter.', 'Step 1. Season the steak with salt and pepper. Step 2. Grill the steak to your desired doneness. Step 3. Serve with garlic butter on top.', 'Beef-Steak.jpg', 1, 1, 3),
('Vegan Burger', 9.50, 'Plant-based burger with lettuce and tomato.', 'Step 1. Cook the plant-based patty until heated through. Step 2. Toast the burger bun. Step 3. Assemble the burger with lettuce, tomato, and patty.', 'Vegan-burger.jpg', 1, 2, 0),
('Chicken Sandwich', 7.99, 'Grilled chicken sandwich with lettuce and tomato.', 'Step 1. Grill the chicken breast until fully cooked. Step 2. Toast the bread slices. Step 3. Assemble the sandwich with lettuce, tomato, and grilled chicken. Step 4. Add condiments as desired.', 'Chicken-sandwiche.jpg', 1, 2, 0),
('Gluten-Free Pasta', 12.99, 'Delicious gluten-free pasta with tomato sauce.', 'Step 1. Boil the gluten-free pasta according to the package instructions. Step 2. Prepare the tomato sauce by simmering tomatoes with garlic and herbs. Step 3. Mix the cooked pasta with the sauce. Step 4. Serve with a sprinkle of parmesan cheese.', 'Gluten-Free-Pasta.jpg', 1, 1, 2),
('Beef Burger', 10.99, 'Juicy beef burger with cheese and pickles.', 'Step 1. Grill the beef patty until it reaches your desired doneness. Step 2. Toast the burger bun. Step 3. Assemble the burger with cheese, pickles, and the beef patty. Step 4. Add condiments as desired.', 'Beef-Burger.jpeg', 1, 1, 0),
('Grilled Chicken', 12.50, 'Grilled chicken breast with herbs.', 'Step 1. Marinate the chicken breast with herbs and spices. Step 2. Grill the chicken until fully cooked. Step 3. Serve with a side of vegetables.', 'Grilled-Chicken.jpg', 1, 1, 2),
('Spaghetti Bolognese', 15.99, 'Traditional Italian pasta with meat sauce.', 'Step 1. Cook the spaghetti according to package instructions. Step 2. Prepare the bolognese sauce by simmering ground meat with tomatoes and herbs. Step 3. Mix the cooked pasta with the sauce. Step 4. Serve with a sprinkle of parmesan cheese.', 'Spaghetti-Bolognese.jpg', 1, 1, 2);
-- Insert sample data into OrderMeal table
go

INSERT INTO OrderMeal (MealID, OrderId, Quantity, IsIngredient) VALUES
(1, 1, 2, 1),
(2, 2, 1, 0),
(1, 3, 2, 1),
(2, 4, 1, 0),
(3, 5, 1, 0);
go
-- Insert sample data into Ingredient table
INSERT INTO Ingredient (IngredientName, IngredientPrice, IngredientStatus, Unit, Role) VALUES
('Lettuce', 0.50, 1, 'head', 'vegetable'),
('Pasta', 1.50, 1, 'package', 'carb'),
('Beef Patty', 3.00, 1, 'piece', 'protein'),
('Carrot', 0.60, 1, 'piece', 'vegetable'),
('Bell Pepper', 0.70, 1, 'piece', 'vegetable'),
('Ground Beef', 4.00, 1, 'kg', 'protein'),
('Lettuce', 0.50, 1, 'head', 'vegetable'),
('Tomato', 0.30, 1, 'piece', 'vegetable'),
('Chicken Breast', 3.50, 1, 'kg', 'protein'),
('Plant-based Patty', 3.00, 1, 'piece', 'protein');
go
-- Insert sample data into MealIngredient table
INSERT INTO MealIngredient (MealID, IngredientID, Quantity) VALUES
-- Vegan Salad
(1, 1, 1),  -- Lettuce
(1, 8, 1),  -- Tomato
(1, 4, 1),  -- Carrot
-- Beef Steak
(2, 6, 1),  -- Ground Beef
-- Vegan Burger
(3, 9, 1),  -- Plant-based Patty
(3, 1, 1),  -- Lettuce
(3, 8, 1),  -- Tomato

-- Chicken Sandwich
(4, 9, 1),  -- Chicken Breast
(4, 1, 1),  -- Lettuce
(4, 8, 1),  -- Tomato
-- Gluten-Free Pasta
(5, 2, 1),  -- Pasta
(5, 6, 1),  -- Ground Beef
-- Beef Burger
(6, 3, 1),  -- Beef Patty
(6, 1, 1),  -- Lettuce
(6, 8, 1),  -- Tomato
-- Grilled Chicken
(7, 9, 1),  -- Chicken Breast
-- Spaghetti Bolognese
(8, 2, 1),  -- Pasta
(8, 6, 1);  -- Ground Beef
-- Insert sample data into WeeklyMenu table
go
INSERT INTO WeeklyMenu (weekDay, WeekNum, Date) VALUES
('Monday', 1, '2024-06-17'),
('Tuesday', 1, '2024-06-18'),
('Wednesday', 1, '2024-06-19'),
('Thursday', 1, '2024-06-20'),
('Friday', 1, '2024-06-21'),
('Saturday', 1, '2024-06-22');
go

-- Insert sample data into WeeklyMeal table
INSERT INTO WeeklyMeal (MealID, WeekMenuId, mealTime) VALUES
(1, 1, 'Lunch'),
(2, 2, 'Dinner'),
(3, 3, 'Lunch'),
(4, 4, 'Dinner'),
(5, 5, 'Lunch');
go

-- Insert sample data into ManageWeeklyMenu table
INSERT INTO ManageWeeklyMenu (AccId, WeekMenuId) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5);

