USE [master]
GO
/****** Object:  Database [UserDB]    Script Date: 11/21/2019 12:38:40 PM ******/
CREATE DATABASE [UserDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'UserDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\UserDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'UserDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\UserDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [UserDB] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [UserDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [UserDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [UserDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [UserDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [UserDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [UserDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [UserDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [UserDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [UserDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [UserDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [UserDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [UserDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [UserDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [UserDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [UserDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [UserDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [UserDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [UserDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [UserDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [UserDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [UserDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [UserDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [UserDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [UserDB] SET RECOVERY FULL 
GO
ALTER DATABASE [UserDB] SET  MULTI_USER 
GO
ALTER DATABASE [UserDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [UserDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [UserDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [UserDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [UserDB] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'UserDB', N'ON'
GO
ALTER DATABASE [UserDB] SET QUERY_STORE = OFF
GO
USE [UserDB]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/21/2019 12:38:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[userID] [nvarchar](50) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](max) NULL,
	[email] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[photo] [nvarchar](max) NULL,
	[role] [int] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_Sub] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PromotionList]    Script Date: 11/21/2019 12:38:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PromotionList](
	[userID] [nvarchar](50) NOT NULL,
	[rank] [int] NULL,
	[DateOfCreate] [datetime] NULL,
 CONSTRAINT [PK_PromotionList] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 11/21/2019 12:38:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] NOT NULL,
	[Description] [nchar](10) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([userID], [username], [password], [email], [phone], [photo], [role], [status]) VALUES (N'admin', N'Van Sang q', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'shang2856@gmail.com', N'0775615772', N'person2.jpg', 0, 1)
INSERT [dbo].[Account] ([userID], [username], [password], [email], [phone], [photo], [role], [status]) VALUES (N'admin1', N'admin', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Admin@gmail.com', N'0775615772', N'blog-1.jpg', 0, 1)
INSERT [dbo].[Account] ([userID], [username], [password], [email], [phone], [photo], [role], [status]) VALUES (N'banhthibuoi', N'Buoi Buoi', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Admin@gmail.com', N'0775615772', N'person3.jpg', 1, 1)
INSERT [dbo].[Account] ([userID], [username], [password], [email], [phone], [photo], [role], [status]) VALUES (N'linh', N'Linh Ngoc Dam', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'linhlinh@gmail.com', N'0123456789', N'person3.jpg', 1, 1)
INSERT [dbo].[Account] ([userID], [username], [password], [email], [phone], [photo], [role], [status]) VALUES (N'quankun', N'Quan Kun Cute Qua ', N'ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad', N'kunkun@gmail.com', N'0775615772', N'person2.jpg', 1, 1)
INSERT [dbo].[Account] ([userID], [username], [password], [email], [phone], [photo], [role], [status]) VALUES (N'subne', N'sub sub ', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'shang2856@gmail.com', N'0775615772', N'person1.jpg', 1, 1)
INSERT [dbo].[PromotionList] ([userID], [rank], [DateOfCreate]) VALUES (N'admin', 3, CAST(N'2019-11-20T07:42:10.213' AS DateTime))
INSERT [dbo].[PromotionList] ([userID], [rank], [DateOfCreate]) VALUES (N'admin1', 10, CAST(N'2019-11-20T07:42:10.200' AS DateTime))
INSERT [dbo].[PromotionList] ([userID], [rank], [DateOfCreate]) VALUES (N'banhthibuoi', 4, CAST(N'2019-11-20T07:42:10.213' AS DateTime))
INSERT [dbo].[PromotionList] ([userID], [rank], [DateOfCreate]) VALUES (N'quankun', 5, CAST(N'2019-11-20T07:42:49.813' AS DateTime))
INSERT [dbo].[PromotionList] ([userID], [rank], [DateOfCreate]) VALUES (N'subne', 5, CAST(N'2019-11-20T07:42:10.213' AS DateTime))
INSERT [dbo].[Role] ([RoleID], [Description]) VALUES (0, N'Admin     ')
INSERT [dbo].[Role] ([RoleID], [Description]) VALUES (1, N'Sub       ')
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_Role]    Script Date: 11/21/2019 12:38:41 PM ******/
ALTER TABLE [dbo].[Role] ADD  CONSTRAINT [IX_Role] UNIQUE NONCLUSTERED 
(
	[Description] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [DF_Sub_status]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[PromotionList] ADD  CONSTRAINT [DF_PromotionList_DateOfCreate]  DEFAULT (getdate()) FOR [DateOfCreate]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([role])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[PromotionList]  WITH CHECK ADD  CONSTRAINT [FK_PromotionList_Account] FOREIGN KEY([userID])
REFERENCES [dbo].[Account] ([userID])
GO
ALTER TABLE [dbo].[PromotionList] CHECK CONSTRAINT [FK_PromotionList_Account]
GO
USE [master]
GO
ALTER DATABASE [UserDB] SET  READ_WRITE 
GO
