USE [master]
GO
/****** Object:  Database [QuanLiThuVien]    Script Date: 5/5/2019 10:05:45 PM ******/
CREATE DATABASE [QuanLiThuVien]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLiThuVien', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\QuanLiThuVien.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLiThuVien_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\QuanLiThuVien_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [QuanLiThuVien] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLiThuVien].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLiThuVien] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLiThuVien] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLiThuVien] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLiThuVien] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLiThuVien] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLiThuVien] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLiThuVien] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLiThuVien] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLiThuVien] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLiThuVien] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLiThuVien] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLiThuVien] SET QUERY_STORE = OFF
GO
USE [QuanLiThuVien]
GO
/****** Object:  Table [dbo].[DICHVU]    Script Date: 5/5/2019 10:05:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DICHVU](
	[MaDichVu] [int] NOT NULL,
	[TenDichVu] [nvarchar](50) NULL,
	[GiaTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DOCGIA]    Script Date: 5/5/2019 10:05:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DOCGIA](
	[MaDocGia] [int] NOT NULL,
	[TenDocGia] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[SDT] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDocGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MUON]    Script Date: 5/5/2019 10:05:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MUON](
	[MaPhieuMuon] [int] NOT NULL,
	[MaDocGia] [int] NULL,
	[MaSach] [int] NULL,
	[NgayMuon] [date] NULL,
	[MaDichVu] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaPhieuMuon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 5/5/2019 10:05:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MaNhanVien] [int] NOT NULL,
	[TenNhanVien] [nvarchar](50) NULL,
	[ChucVu] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHAXUATBAN]    Script Date: 5/5/2019 10:05:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHAXUATBAN](
	[MaNXB] [int] NOT NULL,
	[TenNXB] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[NguoiDaiDien] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNXB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SACH]    Script Date: 5/5/2019 10:05:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SACH](
	[MaSach] [int] NOT NULL,
	[TenSach] [nvarchar](50) NULL,
	[TheLoai] [nvarchar](50) NULL,
	[MaTacGia] [int] NULL,
	[MaNXB] [int] NULL,
	[TinhTrang] [nvarchar](50) NULL,
	[GiaTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TACGIA]    Script Date: 5/5/2019 10:05:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TACGIA](
	[MaTacGia] [int] NOT NULL,
	[TenTacGia] [nvarchar](50) NULL,
	[website] [nvarchar](50) NULL,
	[GhiChu] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTacGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThongTinTraSach]    Script Date: 5/5/2019 10:05:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThongTinTraSach](
	[MaTraSach] [int] NOT NULL,
	[NgayTraSach] [date] NULL,
	[GiaTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTraSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[DICHVU] ([MaDichVu], [TenDichVu], [GiaTien]) VALUES (1, N'PepSi', 10000)
INSERT [dbo].[DICHVU] ([MaDichVu], [TenDichVu], [GiaTien]) VALUES (2, N'C2', 10000)
INSERT [dbo].[DICHVU] ([MaDichVu], [TenDichVu], [GiaTien]) VALUES (3, N'Trà Đào', 100000)
INSERT [dbo].[DICHVU] ([MaDichVu], [TenDichVu], [GiaTien]) VALUES (4, N'C2', 10000)
INSERT [dbo].[DICHVU] ([MaDichVu], [TenDichVu], [GiaTien]) VALUES (5, N'C2', 10000)
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (1, N'Võ Quốc Huy', N'KTX Khu B ĐHQG TPHCM', N'voquochuy798@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (2, N'Nguyễn Văn Hội', N'KTX Khu B ĐHQG TPHCM', N'voquochuy798@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (3, N'Lê Mai Văn Khánh', N'KTX Khu A ĐHQG TPHCM', N'lemaivankhanh123@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (4, N'Ngô Đức Hòa', N'Thủ Đức TPHCM', N'ngoduchoa88@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (5, N'Phạm Nhật Trường', N'Quận 1 TPHCM', N'phamnhattruong321@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (6, N'Nguyễn Minh Hiếu', N'Quận 2 TPHCM', N'nguyenminhieu898@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (7, N'Phạm Văn Cơ', N'Suối Tiên TPHCM', N'phamvanco8@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (8, N'Nguyễn Minh Hiếu', N'Quận 2 TPHCM', N'nguyenminhieu898@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (9, N'Nguyễn Trung Nguyên', N'Quận 8 TPHCM', N'nguyen748@gmail.com', N'0934833253')
INSERT [dbo].[DOCGIA] ([MaDocGia], [TenDocGia], [DiaChi], [Email], [SDT]) VALUES (10, N'Nguyễn Trung Nguyên', N'Quận 8 TPHCM', N'nguyen748@gmail.com', N'0934833253')
INSERT [dbo].[MUON] ([MaPhieuMuon], [MaDocGia], [MaSach], [NgayMuon], [MaDichVu]) VALUES (1, 1, 1, CAST(N'2019-04-15' AS Date), NULL)
INSERT [dbo].[MUON] ([MaPhieuMuon], [MaDocGia], [MaSach], [NgayMuon], [MaDichVu]) VALUES (2, 2, 2, CAST(N'2019-05-19' AS Date), NULL)
INSERT [dbo].[NHANVIEN] ([MaNhanVien], [TenNhanVien], [ChucVu]) VALUES (1, N'Lê Thị Thơ', N'Thủ Qũy')
INSERT [dbo].[NHANVIEN] ([MaNhanVien], [TenNhanVien], [ChucVu]) VALUES (2, N'Võ Quốc Huy', N'Bảo vệ')
INSERT [dbo].[NHANVIEN] ([MaNhanVien], [TenNhanVien], [ChucVu]) VALUES (3, N'Nguyễn Văn Hội', N'Trực bàn')
INSERT [dbo].[NHANVIEN] ([MaNhanVien], [TenNhanVien], [ChucVu]) VALUES (4, N'Lê Mai Văn Khánh', N'Bảo vệ')
INSERT [dbo].[NHANVIEN] ([MaNhanVien], [TenNhanVien], [ChucVu]) VALUES (5, N'Lê Thị Một', N'Nhân viên trực mail')
INSERT [dbo].[NHANVIEN] ([MaNhanVien], [TenNhanVien], [ChucVu]) VALUES (6, N'Lê Thị Một', N'Nhân viên trực mail')
INSERT [dbo].[NHAXUATBAN] ([MaNXB], [TenNXB], [DiaChi], [Email], [NguoiDaiDien]) VALUES (1, N'Sai Gon Book', N'Quận 1 TPHCM', N'saigonbook.com', N'Lê Hữu Thiên')
INSERT [dbo].[NHAXUATBAN] ([MaNXB], [TenNXB], [DiaChi], [Email], [NguoiDaiDien]) VALUES (2, N'Kim Đồng', N'Quận 2 TPHCM', N'kimdong.com', N'Nguyễn Văn Việt')
INSERT [dbo].[NHAXUATBAN] ([MaNXB], [TenNXB], [DiaChi], [Email], [NguoiDaiDien]) VALUES (3, N'Trường Đh CNTT TPHCM', N'Quận 3 TPHCM', N'uit.edu.vn', N'Nguyễn Hùng')
INSERT [dbo].[NHAXUATBAN] ([MaNXB], [TenNXB], [DiaChi], [Email], [NguoiDaiDien]) VALUES (4, N'Tổng hợp', N'Quận Thủ Đức TPHCM', N'tonghopvn.com', N'Võ Thị Mai')
INSERT [dbo].[NHAXUATBAN] ([MaNXB], [TenNXB], [DiaChi], [Email], [NguoiDaiDien]) VALUES (5, N'Chính trị quốc gia 1', N'Quận Bình Chánh TPHCM', N'chinhtriquocgia.com', N'Phạm Văn Đồng')
INSERT [dbo].[NHAXUATBAN] ([MaNXB], [TenNXB], [DiaChi], [Email], [NguoiDaiDien]) VALUES (6, N'Hội Nhà Văn', N'Quận 3 TPHCM', N'hoivanhoc.com', N'Lê Quốc Huy')
INSERT [dbo].[NHAXUATBAN] ([MaNXB], [TenNXB], [DiaChi], [Email], [NguoiDaiDien]) VALUES (7, N'Hội Nhà Văn', N'Quận 3 TPHCM', N'hoivanhoc.com', N'Lê Quốc Huy')
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (1, N'Nhập môn lập trình', N'Giáo trình', 1, 2, N'Chưa cho thuê', 50000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (2, N'Nhập môn lập trình', N'Giáo trình', 1, 2, N'Chưa cho thuê', 50000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (3, N'Nhập môn lập trình', N'Giáo trình', 1, 2, N'Chưa cho thuê', 50000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (4, N'Nhập môn lập trình', N'Giáo trình', 1, 2, N'Chưa cho thuê', 50000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (5, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (6, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Đã cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (7, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (8, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (9, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (10, N'Nhập môn lập trình', N'Giáo trình', 1, 2, N'Chưa cho thuê', 50000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (11, N'Nhập môn lập trình', N'Giáo trình', 1, 2, N'Chưa cho thuê', 50000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (12, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (13, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (14, N'12', N'12', 1, 1, N'Đã cho thuê', 1000000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (15, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (16, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (17, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (18, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (19, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (20, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (21, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Đã cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (22, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (23, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Chưa cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (24, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Đã cho thuê', 60000)
INSERT [dbo].[SACH] ([MaSach], [TenSach], [TheLoai], [MaTacGia], [MaNXB], [TinhTrang], [GiaTien]) VALUES (25, N'Lập trình hướng đối tượng', N'Giáo trình', 2, 3, N'Đã cho thuê', 60000)
INSERT [dbo].[TACGIA] ([MaTacGia], [TenTacGia], [website], [GhiChu]) VALUES (1, N'Nguyễn Đức Chinh', N'saigonbook.com', N'')
INSERT [dbo].[TACGIA] ([MaTacGia], [TenTacGia], [website], [GhiChu]) VALUES (2, N'Nguyễn Anh Dũng', N'daa.uit.edu.com', N'')
INSERT [dbo].[TACGIA] ([MaTacGia], [TenTacGia], [website], [GhiChu]) VALUES (3, N'Nguyễn Trọng Chỉnh', N'hanoibook.com', N'')
INSERT [dbo].[TACGIA] ([MaTacGia], [TenTacGia], [website], [GhiChu]) VALUES (4, N'Lê Thanh Trọng', N'kimdong.com', N'')
INSERT [dbo].[TACGIA] ([MaTacGia], [TenTacGia], [website], [GhiChu]) VALUES (5, N'Nguyễn Công Hoan', N'vanhoaviet.com', N'')
INSERT [dbo].[TACGIA] ([MaTacGia], [TenTacGia], [website], [GhiChu]) VALUES (6, N'Nguyễn Văn Dũng', N'xahoinhanvan.com', N'')
INSERT [dbo].[TACGIA] ([MaTacGia], [TenTacGia], [website], [GhiChu]) VALUES (7, N'Lê Thanh Trọng', N'kimdong.com', N'')
INSERT [dbo].[TACGIA] ([MaTacGia], [TenTacGia], [website], [GhiChu]) VALUES (8, N'Lê Thanh Trọng', N'kimdong.com', N'')
INSERT [dbo].[ThongTinTraSach] ([MaTraSach], [NgayTraSach], [GiaTien]) VALUES (1, CAST(N'2019-05-05' AS Date), 50000)
ALTER TABLE [dbo].[MUON]  WITH CHECK ADD FOREIGN KEY([MaDichVu])
REFERENCES [dbo].[DICHVU] ([MaDichVu])
GO
ALTER TABLE [dbo].[MUON]  WITH CHECK ADD FOREIGN KEY([MaDocGia])
REFERENCES [dbo].[DOCGIA] ([MaDocGia])
GO
ALTER TABLE [dbo].[MUON]  WITH CHECK ADD FOREIGN KEY([MaSach])
REFERENCES [dbo].[SACH] ([MaSach])
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD FOREIGN KEY([MaNXB])
REFERENCES [dbo].[NHAXUATBAN] ([MaNXB])
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD FOREIGN KEY([MaNXB])
REFERENCES [dbo].[NHAXUATBAN] ([MaNXB])
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD FOREIGN KEY([MaTacGia])
REFERENCES [dbo].[TACGIA] ([MaTacGia])
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD FOREIGN KEY([MaTacGia])
REFERENCES [dbo].[TACGIA] ([MaTacGia])
GO
/****** Object:  StoredProcedure [dbo].[spInsertSach]    Script Date: 5/5/2019 10:05:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertSach]
	@TenSach NVARCHAR(50),
	@TheLoai nvarchar(50),
	@MaTacGia int,
	@MaNXB int,
	@TinhTrang nvarchar(50),
	@GiaTien float
AS
	BEGIN TRAN
		BEGIN
			DECLARE @MaSach INT
			SET @MaSach = 1
			WHILE((SELECT COUNT(*) FROM SACH WHERE MaSach = @MaSach) != 0)
			BEGIN
				SET @MaSach = @MaSach + 1
			END
			INSERT INTO SACH VALUES(@MaSach, @TenSach, @TheLoai, @MaTacGia,@MaNXB,@TinhTrang,@GiaTien)
			IF @@ERROR<>0
				BEGIN
					  IF @@TRANCOUNT<>0
						ROLLBACK TRAN
					  GOTO Error
				END
		END
	COMMIT TRAN
Error:

GO
USE [master]
GO
ALTER DATABASE [QuanLiThuVien] SET  READ_WRITE 
GO
