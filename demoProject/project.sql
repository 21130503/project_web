-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 15, 2024 lúc 06:18 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `project`
--
SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `project`;
CREATE DATABASE `project`;
USE `project`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `album`
--

CREATE TABLE `album` (
  `idAlbum` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `isShow` varchar(50) NOT NULL,
  `deleted` char(10) NOT NULL DEFAULT 'false',
  `createdAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `album`
--

INSERT INTO `album` (`idAlbum`, `name`, `price`, `discount`, `isShow`, `deleted`, `createdAt`) VALUES
(13, 'Funny Anime', 100000, 20000, 'true', 'false', '2024-12-20'),
(14, 'Video Game', 200000, 15000, 'true', 'false', '2024-12-20'),
(15, 'Meme', 150000, 30000, 'true', 'false', '2024-12-20'),
(10, 'Chó cưng', 100000, 50000, 'true', 'false', '2023-12-12'),
(16, 'Hoa hồng1', 200000, 0, 'true', 'false', '2023-12-25'),
(20, 'Con người ', 100000, 5000, 'true', 'false', '2024-01-01'),
(21, 'Hoa 1', 30000, 10000, 'true', 'false', '2024-01-03'),
(22, 'Chó 1', 20000, 0, 'true', 'false', '2024-01-03'),
(23, 'Mèo 1', 20000, 1000, 'true', 'false', '2024-01-03'),
(24, 'Anime 1', 500000, 30000, 'true', 'false', '2024-01-05'),
(25, 'Album vũ trụ', 300000, 0, 'true', 'false', '2024-01-20'),
(26, 'heheheh', 1000, 0, 'false', 'false', '2024-01-20'),
(28, 'Vũ trụ 1', 20000, 0, 'true', 'false', '2024-01-27'),
(29, 'BST vũ trụ', 790300, 300, 'true', 'false', '2024-01-28'),
(30, 'Thiên nhiên 19/10', 50000, 0, 'true', 'false', '2024-10-19'),
(31, '19/10', 50000, 0, 'true', 'false', '2024-10-19'),
(32, '19/10-1', 5000, 0, 'true', 'false', '2024-10-19'),
(33, '19/10-2', 6000, 0, 'true', 'false', '2024-10-19');


CREATE TABLE `albumbelongtopic` (
  `idTopic` int(11) NOT NULL,
  `idAlbum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `albumbelongtopic` (`idTopic`, `idAlbum`) VALUES
  (2, 10),
  (1, 16),
  (3, 20),
  (1, 21),
  (2, 22),
  (5, 23),
  (6, 24),
  (7, 25),
  (10, 26),
  (4, 27),
  (7, 28),
  (7, 29),
  (11, 30),
  (11, 31),
  (11, 32),
  (10, 13),
  (10, 14),
  (10, 15),
  (11, 33);

CREATE TABLE `albumfeedback` (
  `idFeedBack` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idAlbum` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `star` varchar(11) DEFAULT NULL,
  `createdAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `albumorder` (
  `idOrder` int(11) NOT NULL,
  `idAlbum` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `purchareDate` date NOT NULL,
  `fileName` varchar(225) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `albumorder` (`idOrder`, `idAlbum`, `idUser`, `receiver`, `phoneNumber`, `quantity`, `totalPrice`, `status`, `address`, `purchareDate`, `fileName`, `createdAt`) VALUES
(29, 24, 10, 'Đặng Diễm Quyên', '0765607019', 1, 500000, 'Đã hủy', 'Bố Hạ 1,Phường Hiệp Sơn,Thị xã Kinh Môn,Tỉnh Hải Dương', '2024-12-04', 'E:\\TomCat\\apache-tomcat-9.0.82\\apache-tomcat-9.0.82\\webapps\\demoProject_war\\orders/order10f66da1-2b54-4a46-b16c-21ed9e1facf4.txt', '2024-12-04 00:00:52');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cartorder`
--

CREATE TABLE `cartorder` (
  `idOrder` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `idUser` int(11) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `purchareDate` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cartorder`
--

INSERT INTO `cartorder` (`idOrder`, `name`, `idUser`, `receiver`, `phoneNumber`, `quantity`, `totalPrice`, `status`, `address`, `purchareDate`) VALUES
(1, ',Vũ trụ-Hố đen,Hoa hồng,Tháng 4 lời nói dối của em', 1, 'Đặng Hữu Qúy ', '0765607019', 3, 630000, 'Đã hủy', 'Năng Tây 3,Xã Nghĩa Phương,Huyện Tư Nghĩa,Tỉnh Quảng Ngãi', '2024-01-11'),
(2, ',Vũ trụ-Hố đen,Hoa hồng,Tháng 4 lời nói dối của em', 1, 'Đặng Hữu Qúy ', '0765607019', 5, 1210000, 'Đã hủy', 'Năng Tây 3,Xã Nghĩa Phương,Huyện Tư Nghĩa,Tỉnh Quảng Ngãi', '2024-01-13'),
(3, ',Vũ trụ 2,Hoa hồng,Tháng 4 lời nói dối của em', 1, 'Đặng Hữu Qúy ', '0795716506', 3, 645000, 'Đã giao cho đơn vị vận chuyển', 'Năng Tây 3,Phường Linh Trung,Thành phố Thủ Đức,Thành phố Hồ Chí Minh', '2024-01-27'),
(4, ',Vũ trụ-Hố đen,Hoa hồng,Cún Lucky', 1, 'Đặng Hữu Qúy ', '0795716506', 3, 403500, 'Đang chuẩn bị', 'Năng Tây 3,Xã Trung Hưng,Huyện Yên Mỹ,Tỉnh Hưng Yên', '2024-01-21'),
(5, ',Vũ trụ-Hố đen,Cún Lucky', 2, 'Đặng Hữu Qúy ', '0765607019', 3, 365000, 'Đang chuẩn bị', 'Năng Tây 3,Xã Thanh Lương,Huyện Vĩnh Bảo,Thành phố Hải Phòng', '2024-01-27'),
(6, ',BST vũ trụ,Con người ', 2, 'Đặng Hữu Qúy ', '0765607019', 2, 915000, 'Đang chuẩn bị', 'Năng Tây 3,Xã Chiềng Ân,Huyện Mường La,Tỉnh Sơn La', '2024-01-28');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_album`
--

CREATE TABLE `ct_album` (
  `idAlbum` int(11) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_album`
--

INSERT INTO `ct_album` (`idAlbum`, `description`) VALUES
(6, 'TÃ´i yeu em'),
(8, 'Hoa hồng nào chẳng có gai'),
(9, 'Hoa hồng gai góc'),
(10, 'Chó là bạn'),
(11, 'Pokemon xyz'),
(12, 'abc xyz'),
(13, 'Các bức ảnh Funny xuất hiện trong các bộ Anime, phim Hoạt Hình.'),
(14, 'Video Game là nguồn cảm hứng bất tận cho các Meme của người dùng Internet.'),
(15, 'Các Meme huyền thoại xuất hiện hầu hết mọi nên trên INTERNET.'),
(16, '1'),
(20, 'update'),
(21, 'Hoa 1'),
(22, 'chó 1'),
(23, 'ec'),
(24, 'Anime 1 '),
(25, 'vũ trụ '),
(26, 'hehehe'),
(27, 'Động vật 11'),
(28, 'Vũ trụ'),
(29, 'BST về vũ trụ'),
(30, 'No description'),
(31, 'no'),
(32, '1'),
(33, 'no');
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_oddimage`
--

CREATE TABLE `ct_oddimage` (
  `idOddImage` int(11) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_oddimage`
--

INSERT INTO `ct_oddimage` (`idOddImage`, `description`) VALUES
(13, 'Hoa Hồng'),
(15, 'Vũ trụ ....'),
(16, 'Lucky là 1 chú chó đáng yêu\r\n-Thật tuyệt vời'),
(17, 'Hoa mà cũng là cún Lucky luôn thấy không'),
(18, 'Mèo'),
(19, 'Động vật 1'),
(20, 'Vũ trụ'),
(21, 'Vũ trụ '),
(22, 'Vũ trụ 2'),
(23, 'Mavel saler'),
(24, '123456'),
(27, 'Test'),
(28, 'Natural picture'),
(29, 'Nautural with watermark');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `discount`
--

CREATE TABLE `discount` (
  `code` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `discountValue` double NOT NULL,
  `count` int(11) NOT NULL,
  `expiryDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `discount`
--

INSERT INTO `discount` (`code`, `description`, `discountValue`, `count`, `expiryDate`) VALUES
(123, 'giảm giá 10%', 0.1, 2, '2024-01-12'),
(124, 'giảm giá 12%', 0.12, 1, '2024-02-11'),
(125, 'giảm giá 1%', 0.01, 0, '2034-01-22');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image`
--

CREATE TABLE `image` (
  `idImage` int(11) NOT NULL,
  `idAlbum` int(11) NOT NULL,
  `source` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `image`
--

INSERT INTO `image` (`idImage`, `idAlbum`, `source`) VALUES
(8, 10, '/images/cho3.png'),

(1, 13, '/images/meme1.png'),
(2, 13, '/images/meme2.png'),
(3, 13, '/images/meme3.png'),
(4, 14, '/images/meme4.png'),
(5, 14, '/images/meme5.png'),
(6, 14, '/images/meme6.png'),
(7, 15, '/images/meme7.png'),
(11, 15, '/images/meme8.png'),
(9, 15, '/images/meme9.png'),

(10, 12, '/images/lizardon.jpg'),
(34, 16, '/images/f160c4cb-cdf6-4469-892f-440ef326154a_logohq.png'),
(59, 20, '/images/connguoi1.png'),
(61, 20, '/images/connguoi3.png'),
(62, 20, '/images/connguoi4.png'),
(65, 21, '/images/hoa5.png'),
(66, 21, '/images/hoa2.png'),
(67, 22, '/images/cho3.png'),
(68, 22, '/images/cho4.png'),
(69, 22, '/images/cho5.png'),
(70, 23, '/images/meo1.png'),
(71, 23, '/images/meo3.png'),
(72, 23, '/images/meo4.png'),
(73, 24, '/images/hoathinh1.png'),
(74, 24, '/images/hoathinh2.png'),
(75, 25, '/images/vutru3.png'),
(76, 25, '/images/vutru5.jpg'),
(77, 25, '/images/vutru2.png'),
(78, 26, '/images/meo5.png'),
(79, 26, '/images/meo4.png'),
(80, 27, '/images/dongvat2.png'),
(82, 27, '/images/dongvat5.png'),
(83, 28, '/images/Login_bg.jpg'),
(84, 28, '/images/register-bg.jpg'),
(85, 28, '/images/lockscreen-bg.jpg'),
(86, 29, '/images/team.jpg'),
(87, 29, '/images/mainlogo.png'),
(88, 29, '/images/react-custom-hook.jpg'),
(89, 30, '/images/image (1).png'),
(90, 30, '/images/image (2).png'),
(91, 30, '/images/image (3).png'),
(92, 31, '/images/images (1).jfif'),
(93, 31, '/images/phan-loai-tai-nguyen-thien-nhien.jpg'),
(94, 32, '/images/images (1).jfif'),
(95, 32, '/images/phan-loai-tai-nguyen-thien-nhien.jpg'),
(96, 33, '/images/images (1).jfif'),
(97, 33, '/images/phan-loai-tai-nguyen-thien-nhien.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `type` varchar(225) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `notification`
--

INSERT INTO `notification` (`id`, `idUser`, `content`, `type`, `createdAt`) VALUES
(1, 10, 'Đơn hàng của bạn đã bị hủy vì có sự cố', 'order', '2024-12-03 00:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `oddimage`
--

CREATE TABLE `oddimage` (
  `idOddImage` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `source` longtext NOT NULL,
  `watermark` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `isShow` varchar(50) NOT NULL,
  `deleted` char(10) NOT NULL DEFAULT 'false',
  `createdAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `oddimage`
--

INSERT INTO `oddimage` (`idOddImage`, `name`, `source`, `watermark`, `price`, `discount`, `isShow`, `deleted`, `createdAt`) VALUES
(8, 'Noel Vui Vẻ Nhoa :>>', '/images/meme10.png', '', 500000, 20000, 'true', 'false', '2024-12-17'),
(9, 'Anh chàng thư giãn', '/images/meme11.png', '', 500000, 20000, 'true', 'false', '2024-12-17'),
(7, 'Coder 2025', '/images/meme12.png', '', 500000, 20000, 'true', 'false', '2024-12-17'),
(10, 'Nó ở đây này !', '/images/meme13.png', '', 500000, 20000, 'true', 'false', '2024-12-17'),
(11, 'Jojo Mèo Tom', '/images/meme14.png', '', 500000, 20000, 'true', 'false', '2024-12-17'),

(12, 'Tháng 4 lời nói dối của em', '/images/avatar.gif', '', 500000, 20000, 'false', 'false', '2024-12-17'),
(13, 'Hoa hồng', '/images/hoa4.png', '', 100000, 0, 'true', 'false', '2023-12-17'),
(15, 'Vũ trụ-Hố đen1', '/images/vutru1.png', '', 20000, 0, 'true', 'false', '2023-12-30'),
(16, 'Cún Lucky', '/images/cho4.png', '', 300000, 5000, 'false', 'false', '2023-12-30'),
(17, 'Hoa ', '/images/hoa2.png', '', 40000, 10000, 'true', 'false', '2023-12-30'),
(18, 'Mèo', '/images/meo3.png', '', 100000, 0, 'true', 'false', '2024-01-03'),
(19, 'Động vật ', '/images/dongvat2.png', '', 100000, 10000, 'true', 'false', '2024-01-03'),
(20, 'Vũ trụ ', '/images/vutru2.png', '', 100000, 0, 'true', 'false', '2024-01-03'),
(21, 'Vũ trụ 1', '/images/vutru4.png', '', 40000, 0, 'true', 'false', '2024-01-04'),
(22, 'Vũ trụ 2', '/images/vutru3.png', '', 45000, 10000, 'true', 'false', '2024-01-04'),
(23, 'Vũ trụ mavel', '/images/vutru5.jpg', '', 1000000, 100000, 'true', 'false', '2024-01-13'),
(24, 'Con người 221', '/images/login-bg.jpg', '', 200000, 10000, 'true', 'false', '2024-01-27'),
(26, 'Mèo 2001', '/images/slide4.jpg', '', 20000, 10000, 'true', 'false', '2024-01-28'),
(27, 'Tháng 4 lời nói dối của em_water', '/images/thobaymau.jpg', '', 120000, 0, 'true', 'false', '2024-09-30'),
(28, 'Natural', '/images/images.jfif', '/images-watermark/images.jfif', 100000, 1000, 'true', 'false', '2024-10-01'),
(29, 'Nautural-1', '/images/images.jfif', '/images-watermark/images.jfif', 50000, 0, 'true', 'false', '2024-10-01');


CREATE TABLE `oddimagebelongtopic` (
  `idTopic` int(11) NOT NULL,
  `idOddImage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `oddimagebelongtopic` (`idTopic`, `idOddImage`) VALUES
(1, 13),
(10, 7),
(10, 8),
(10, 9),
(10, 10),
(10, 11),
(7, 15),
(2, 16),
(1, 17),
(5, 18),
(4, 19),
(7, 20),
(7, 21),
(7, 22),
(7, 23),
(6, 24),
(6, 27),
(11, 28),
(11, 29);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `oddimagefeedback`
--

CREATE TABLE `oddimagefeedback` (
  `idFeedBack` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idOddImage` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `star` varchar(11) DEFAULT NULL,
  `createdAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `oddimagefeedback`
--

INSERT INTO `oddimagefeedback` (`idFeedBack`, `idUser`, `idOddImage`, `content`, `star`, `createdAt`) VALUES
(12, 1, 13, '', '5', '2023-12-23'),
(13, 1, 12, '', '5', '2024-01-26'),
(14, 1, 15, 'abc', '3', '2024-01-28');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `oddimageorder`
--

CREATE TABLE `oddimageorder` (
  `idOrder` int(11) NOT NULL,
  `idOddImage` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `purchareDate` date NOT NULL,
  `fileName` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `oddimageorder`
--

INSERT INTO `oddimageorder` (`idOrder`, `idOddImage`, `idUser`, `receiver`, `phoneNumber`, `quantity`, `totalPrice`, `address`, `status`, `purchareDate`, `fileName`) VALUES
(1, 13, 1, 'Đặng Hữu Qúy', '0707435918', 1, 90000, 'Năng Tây 3,Xã Nghĩa Phương,Huyện Tư Nghĩa,Tỉnh Quảng Ngãi', 'Đã hủy', '2023-12-23', ''),
(3, 13, 1, 'Đặng Hữu Qúy', '0707435918', 3, 270000, 'Bố Hạ 1,Xã Cư A Mung,Huyện Ea H\'leo,Tỉnh Đắk Lắk', 'Đã giao', '2023-12-23', '');


CREATE TABLE `user` (
  `idUser` int(11) PRIMARY KEY,
  `email` varchar(255) NOT NULL,
  `name` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `isVerifyEmail` varchar(50) DEFAULT NULL,
  `isActive` varchar(50) DEFAULT NULL,
  `isAdmin` varchar(50) DEFAULT NULL,
  `publicKey` varchar(255) DEFAULT NULL,
  `createdAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=UTF8MB4_GENERAL_CI;

INSERT INTO `user` (`idUser`, `email`, `name`, `password`, `isVerifyEmail`, `isActive`, `isAdmin`, `publicKey`, `createdAt`) VALUES
(9, 'gTK9nZy8Ovb8l2H7563TUeTcnaaDDnEt', 'pPp+CG4RHeCPjk5wRF6NMg==', '5d2d3ceb7abe552344276d47d36a8175b7aeb250a9bf0bf00e850cd23ecf2e43', 'true', 'true', 'true', '', '2024-10-25'),
(8, 'thankfat@gmail.com', 'Thanh Phát', 'thankfat123', 'false', 'true', 'true', '', '2024-10-25'),
(10, '6S4G1Y/pcjxMPK0IAulgMrAtsdWiT3uOWwmwAc/Y7uc=', '+Q8Nlz1fGmQ=', '39876dfc8ee27a85705754cb894a8cc550bdecee2eb9c5eb30a3480055bcee6f', 'true', 'true', 'false', '', '2024-11-19'),
(11, 'YdeySVhxGYvAM8Jl/p5o4sPexKzf6M5L', 'GkngkHnSzUG+CRunSC67FA==', '27ad6ca8621ae844454ffc8a42c5101d9ddd319c2492f08e74ecc5c2956d6adb', 'false', 'true', 'false', NULL, '2024-12-15'),
(12, 'YdeySVhxGYvGIec51kbLOVVmMKH48bF/', 'GkngkHnSzUG+CRunSC67FA==', 'cead5fd4f4533b450db387a0ff993ff9d31cb89daa4e919d344d078187245386', 'false', 'true', 'false', NULL, '2024-12-16');

CREATE TABLE `reportkeys` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `reportUserID` int(11) NOT NULL,
  `publicKeysId` INT NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME(6) NOT NULL,
  `reason` VARCHAR(255) NULL,
  CONSTRAINT fk_report_user FOREIGN KEY (`reportUserID`) REFERENCES `user`(`idUser`) ON DELETE CASCADE,
  CONSTRAINT fk_report_publicKey FOREIGN KEY (`publicKeysId`) REFERENCES `publickeys`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `reportkeys` (`id`, `reportUserID`, `publicKeysId`, `date`, `time`, `reason`) VALUES
(1, 8, 1,'2024-12-07', '02:19:00.000000', '');

CREATE TABLE `publickeys` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId int(11) NOT NULL,
    publicKey TEXT NOT NULL,
    createTime DATETIME NOT NULL,
    endTime DATETIME DEFAULT NULL,
    CONSTRAINT fk_user_publickeys FOREIGN KEY (userId) REFERENCES `user`(`idUser`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO `publickeys` (userId, publicKey, createTime, endTime) VALUES 
(8, 'ABCD1234EFGH5678...', '2024-12-17 10:00:00', '2025-12-17 10:00:00'),
(12, 'MIICXAIBADCCAjUGByqGSM44BAEwggIoAoIBAQCPeTXZuarpv6vtiHrPSVG28y7FnjuvNxjo6sSWHz79NgbnQ1GpxBgzObgJ58KuHFObp0dbhdARrbi0eYd1SYRpXKwOjxSzNggooi', '2024-12-02 10:00:00', '2025-12-05 10:00:00'),
(12, 'MIICXAIBADCCAjUGByqGSM44BAEwggIoAoIBAQCPeTXZuarpv6vtiHrPSVG28y7FnjuvNxjo6sSWHz79NgbnQ1GpxBgzObgJ58KuHFObp0dbhdARrbi0eYd1SYRpXKwOjxSzNggooi', '2024-12-17 10:00:00', '2025-12-20 08:00:00'),
(12, 'MIICXAIBADCCAjUGByqGSM44BAEwggIoAoIBAQCPeTXZuarpv6vtiHrPSVG28y7FnjuvNxjo6sSWHz79NgbnQ1GpxBgzObgJ58KuHFObp0dbhdARrbi0eYd1SYRpXKwOjxSzNggooi/6JxEKPWKpk0U0CaD+aWxGWPhL3SCBnDcJoBBXsZWtzQAjPbpUhLYpH51kjviDRIZ3l5zsBLQ0pqwudemYXeI9sCkvwRGMn/qdgYHnM423krcw17njSVkvaAmYchU5Feo9a4tGU8YzRY+AOzKkwuDycpAlbk4/ijsIOKHEUOThjBopo33fXqFD3ktm/wSQPtXPFiPhWNSHxgjpfyEc2B3KI8tuOAdl+CLjQr5ITAV2OTlgHNZnAh0AuvaWpoV499/e5/pnyXfHhe8ysjO65YDAvNVpXQKCAQAWplxYIEhQcE51AqOXVwQNNNo6NHjBVNTkpcAtJC7gT5bmHkvQkEq9rI837rHgnzGC0jyQQ8tkL4gAQWDt+coJsyB2p5wypifyRz6Rh5uixOdEvSCBVEy1W4AsNo0fqD7UielOD6BojjJCilx4xHjGjQUntxyaOrsLC+EsRGiWOefTznTbEBplqiuH9kxoJts+xy9LVZmDS7TtsC98kOmkltOlXVNb6/xF1PYZ9j897buHOSXC8iTgdzEpbaiH7B5HSPh++1/et1SEMWsiMt7lU92vAhErDR8C2jCXMiT+J67ai51LKSLZuovjntnhA6Y8UoELxoi34u1DFuHvF9veBB4CHCzX0HyVK0pyFxq5L/PJKjd+cM4nPGMMoaoMqBs=', '2024-12-20 10:00:00', null);

CREATE TABLE `topic` (
  `idTopic` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `isShow` varchar(50) NOT NULL,
  `interfaceImage` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `topic` (`idTopic`, `name`, `isShow`, `interfaceImage`) VALUES
(1, 'Hoa', 'true', '/images/hoa5.png'),
(2, 'Chó', 'false', '/images/cho1.png'),
(3, 'Con người', 'true', '/images/connguoi4.png'),
(4, 'Động Vật', 'true', '/images/dongvat3.png'),
(5, 'Mèo', 'true', '/images/meo3.png'),
(6, 'Anime', 'true', '/images/gekkuga.jpg'),
(7, 'Vũ trụ', 'true', '/images/vutru4.png'),
(8, 'Xe', 'true', '/images/xe2.png'),
(9, 'hhi', 'true', '/images/lizardon.jpg'),
(10, 'Giải Trí', 'true', '/images/meme0.png'),
(11, 'Thiên nhiên', 'true', '/images/thiennhien4.png'),
(12, 'Test', 'true', '/images/slide4.jpg');

CREATE TABLE `watermark` (
  `id` int(11) NOT NULL,
  `idAlbum` int(11) NOT NULL,
  `source` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `watermark` (`id`, `idAlbum`, `source`) VALUES
(1, 33, '/images-watermark/images (1).jfif'),
(2, 33, '/images-watermark/phan-loai-tai-nguyen-thien-nhien.jpg');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`idAlbum`);

--
-- Chỉ mục cho bảng `albumfeedback`
--
ALTER TABLE `albumfeedback`
  ADD PRIMARY KEY (`idFeedBack`);

--
-- Chỉ mục cho bảng `albumorder`
--
ALTER TABLE `albumorder`
  ADD PRIMARY KEY (`idOrder`);

--
-- Chỉ mục cho bảng `cartorder`
--
ALTER TABLE `cartorder`
  ADD PRIMARY KEY (`idOrder`);

--
-- Chỉ mục cho bảng `ct_oddimage`
--
ALTER TABLE `ct_oddimage`
  ADD PRIMARY KEY (`idOddImage`);

--
-- Chỉ mục cho bảng `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`code`);

--
-- Chỉ mục cho bảng `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`idImage`);

--
-- Chỉ mục cho bảng `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `oddimage`
--
ALTER TABLE `oddimage`
  ADD PRIMARY KEY (`idOddImage`);

--
-- Chỉ mục cho bảng `oddimagefeedback`
--
ALTER TABLE `oddimagefeedback`
  ADD PRIMARY KEY (`idFeedBack`);

--
-- Chỉ mục cho bảng `oddimageorder`
--
ALTER TABLE `oddimageorder`
  ADD PRIMARY KEY (`idOrder`);

--
-- Chỉ mục cho bảng `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`idTopic`);



--
-- Chỉ mục cho bảng `watermark`
--
ALTER TABLE `watermark`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `album`
--
ALTER TABLE `album`
  MODIFY `idAlbum` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT cho bảng `albumfeedback`
--
ALTER TABLE `albumfeedback`
  MODIFY `idFeedBack` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `albumorder`
--
ALTER TABLE `albumorder`
  MODIFY `idOrder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `cartorder`
--
ALTER TABLE `cartorder`
  MODIFY `idOrder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `ct_oddimage`
--
ALTER TABLE `ct_oddimage`
  MODIFY `idOddImage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `discount`
--
ALTER TABLE `discount`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=357116;

--
-- AUTO_INCREMENT cho bảng `image`
--
ALTER TABLE `image`
  MODIFY `idImage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT cho bảng `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `oddimage`
--
ALTER TABLE `oddimage`
  MODIFY `idOddImage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `oddimagefeedback`
--
ALTER TABLE `oddimagefeedback`
  MODIFY `idFeedBack` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `oddimageorder`
--
ALTER TABLE `oddimageorder`
  MODIFY `idOrder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `watermark`
--
ALTER TABLE `watermark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
