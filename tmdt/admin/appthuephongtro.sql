/*
 Navicat Premium Data Transfer

 Source Server         : db4free.net_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : db4free.net:3306
 Source Schema         : appthuephongtro

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 29/11/2019 23:09:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'ten dang nhap',
  `password` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'mat khau',
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'ten khach hang',
  `age` int(3) NULL DEFAULT NULL COMMENT 'ngay thang nam sinh',
  `wallet` int(11) NULL DEFAULT NULL COMMENT 'so tien hien co',
  `phone` varchar(13) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'dien thoai lien lac',
  `gender` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'gioi tinh',
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'email khach hang',
  `id_post` varchar(13) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'khoa ngoai cua bang bai dang',
  `rate` int(11) NULL DEFAULT NULL COMMENT 'danh gia chat luong user',
  `role` int(1) NULL DEFAULT NULL COMMENT 'vai tro user',
  `images` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'anh bai dang',
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `v`(`role`) USING BTREE,
  INDEX `b`(`wallet`) USING BTREE,
  INDEX `acpo`(`id_post`) USING BTREE,
  CONSTRAINT `acpo` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `acro` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('admin', '123', 'Admin', NULL, NULL, NULL, NULL, 'admin1998@gmail.com', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa chinh bang comment',
  `user_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa ngoại bang account',
  `id_post` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa ngoai bang post\r\n',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'noi dung ',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `saa`(`user_name`) USING BTREE,
  INDEX `sbb`(`id_post`) USING BTREE,
  CONSTRAINT `saa` FOREIGN KEY (`user_name`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sbb` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for favorite_room
-- ----------------------------
DROP TABLE IF EXISTS `favorite_room`;
CREATE TABLE `favorite_room`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id bang phong tro yeu thich',
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa ngoai cua bang account',
  `id_post` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa ngoai cua bang bai dang',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bmn`(`username`) USING BTREE,
  INDEX `bnm`(`id_post`) USING BTREE,
  CONSTRAINT `bmn` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bnm` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`  (
  `id` int(11) NOT NULL,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'duong link anh',
  `id_post` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa ngoai cua bang post',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `vxc`(`id_post`) USING BTREE,
  INDEX `link`(`link`) USING BTREE,
  CONSTRAINT `vxc` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES (2, 'https://static.thuephongtro.com/Uploads/Images/Thumb/20180427093102-FB_IMG_1499098068179.jpg', '001');
INSERT INTO `images` VALUES (3, 'https://static.thuephongtro.com/Uploads/Images/Thumb/20191101090038-IMG_20191101_075643.jpg', '002');
INSERT INTO `images` VALUES (4, 'https://static.thuephongtro.com/Uploads/Images/Thumb/20191014120207-IMG_0843.JPG', '003');
INSERT INTO `images` VALUES (5, 'https://static.thuephongtro.com/Uploads/Images/Thumb/20170107083221-IMG_1343.JPG', '004');
INSERT INTO `images` VALUES (6, 'https://static.thuephongtro.com/Uploads/Images/Thumb/20181119114239-1fb2c5fc234cc3129a5d.jpg', '005');
INSERT INTO `images` VALUES (7, 'https://static.thuephongtro.com/Uploads/Images/Thumb/20191106042745-96f21c48daa73cf965b6.jpg', '006');

-- ----------------------------
-- Table structure for manage_post
-- ----------------------------
DROP TABLE IF EXISTS `manage_post`;
CREATE TABLE `manage_post`  (
  `id` int(11) NOT NULL COMMENT 'id quan ly post',
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa phu cua bang account',
  `id_post` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa phu bai dang',
  `id_postpackage` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa phu goi tin',
  `start` timestamp(6) NOT NULL COMMENT 'ngay bat dau co goi tin co hieu luc',
  `end` timestamp(6) NOT NULL COMMENT 'hieu luc goi tin ket thuc',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mnu`(`username`) USING BTREE,
  INDEX `mnpo`(`id_post`) USING BTREE,
  INDEX `mnpa`(`id_postpackage`) USING BTREE,
  CONSTRAINT `mnpa` FOREIGN KEY (`id_postpackage`) REFERENCES `package` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mnpo` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mnu` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for package
-- ----------------------------
DROP TABLE IF EXISTS `package`;
CREATE TABLE `package`  (
  `id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'id goi tin',
  `package_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'ten goi tin',
  `package_price` int(7) NOT NULL COMMENT 'gia goi tin',
  `package_time` int(3) NOT NULL COMMENT 'thoi gian goi tin co hieu luc',
  `package_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'noi dung goi tin',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'id bai dang',
  `title` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tieu de',
  `created` timestamp(0) NULL DEFAULT NULL COMMENT 'ngay tao',
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'nguoi tao bai viet',
  `views` int(11) NULL DEFAULT NULL COMMENT 'luot xem',
  `amount` int(11) NULL DEFAULT NULL COMMENT 'gia tien',
  `area` int(11) NULL DEFAULT NULL COMMENT 'dien tich',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'thanh pho',
  `district` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'quan huyen',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'dia chi chi tiet',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'mo ta chi tiet',
  `id_postpacket` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'khoa ngoai bang goi tin',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'anh cua bai dang',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `zxvc`(`username`) USING BTREE,
  INDEX `poimg`(`image`) USING BTREE,
  INDEX `popa`(`id_postpacket`) USING BTREE,
  CONSTRAINT `poimg` FOREIGN KEY (`image`) REFERENCES `images` (`link`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `popa` FOREIGN KEY (`id_postpacket`) REFERENCES `manage_post` (`id_postpackage`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pous` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('001', 'PHÒNG TRỌ KHÔNG CHUNG CHỦ XVNT BÌNH THẠNH', '2019-11-22 09:42:18', 'admin', 1200, 2400000, 20, 'TPHCM', 'Bình Thạnh', 'Phòng trọ', 'Phòng trọ đường Xô Viết Nghệ Tĩnh, tolet trong phòng, lối đi riêng. Phòng thoáng mát, xe để trong nhà, điện nước tính theo hóa đơn nhà nước. khu vực không ngập gần bến xe miền đông.', NULL, NULL);
INSERT INTO `post` VALUES ('002', 'PHÒNG MỚI XÂY DỰNG THOÁNG MÁT,SẠCH SẼ, MẶT TIỀN ĐƯỜNG PHAN ĐÌNH PHÙNG TÂN PHÚ', '2019-11-22 09:44:37', 'admin', 3000, 2500000, 16, 'TPHCM', 'Tân Phú', 'Phòng trọ', 'Nhà mình nằm mặt tiền đường Phan đình Phùng phường Tân thành Q Tân phú. Nhà mới xây dựng, sạch sẽ, thoáng mát, cửa sổ banconl, toilet riêng trong phòng, wifi đường truyền ổn định. Cửa ra vào vân tay ghi hình đảm bảo an ninh. Giờ giấc tự do. Nhà mình gần C', NULL, NULL);
INSERT INTO `post` VALUES ('003', 'CĂN HỘ RẤT ĐẸP 50m2, có bếp, ban công, Phòng ngủ, phòng khách, đẹp y hình gần LOTTE Q.7', '2019-11-22 09:48:12', 'admin', 5000, 5000000, 50, 'TPHCM', 'Quận 7', 'Căn hộ', '- Cho thuê CĂN HỘ rất đẹp tại: 142/36b Nguyễn Thị Thập, Quận 7, kế bên siêu thị Lotte Mart Q7, gần nhiều trường đại học... Đi qua Quận 1, Q7, Q3, Q4, Q8, Q5, Nhà Bè đều rất gần.\r\n- Căn hộ rất rộng, sạch đẹp như hình, mới xây 1 năm, có cửa sổ và ban công r', NULL, NULL);
INSERT INTO `post` VALUES ('004', 'PHÒNG TRỌ MỚI XÂY RẤT ĐẸP, GIỜ GIẤC TỰ DO, GẦN LOTTE MART Q.7', '2019-11-22 09:51:10', 'admin', 2000, 3000000, 25, 'TPHCM', 'Quận 7', 'Phòng trọ', '- Cho thuê phòng trọ mới xây, kế bên siêu thị LOTTE MART Q.7, gần Cầu Nguyễn Văn Cừ, gần nhiều Trường Đại Học: Tôn Đức Thắng, TC Marketing, Công Nghệ SG, Khoa Học Tự Nhiên,.....\r\n- Phòng mới xây rất sạch sẽ, có toilet trong phòng, cửa sổ thoáng mát, riêng', NULL, NULL);
INSERT INTO `post` VALUES ('005', 'CHÍNH CHỦ CHO THUÊ PHÒNG 2PN, 45M2 TẠI 104/5A NHẤT CHI MAINGAY TÒA NHÀ ETOWN', '2019-11-22 11:03:45', 'admin', 2132, 4500000, 45, 'TPHCM', 'Tân Bình', 'Phòng trọ', 'chính chủ cho thuê phòng trọ tại 104/5A Nhất Chi Mai, P13, TB:\r\n- Diện tích phòng: 45m2 - rất rộng rãi - phòng đầu hồi thoáng mát.\r\n- Nội thất gồm: Tủ lạnh, giường, tủ quần áo, bộ bàn ăn, tủ kệ bếp, bồn rửa chén...\r\n- Phòng có ban công, giếng trời thoáng ', NULL, NULL);
INSERT INTO `post` VALUES ('006', 'Phòng trọ lối đi riêng đường Nguyễn Hữu Cảnh Bình Thạnh', '2019-11-22 10:52:33', 'admin', 2131, 1700000, 25, 'TPHCM', 'Bình Thạnh', 'Phòng trọ', ' Phòng có vệ sinh trong, lối đi riêng, giờ giấc tự do. sạch sẽ đường không ngập nước. Điện nước tính theo đồng hồ. Phòng chưa có nội thất nhé', NULL, NULL);

-- ----------------------------
-- Table structure for recharge_history
-- ----------------------------
DROP TABLE IF EXISTS `recharge_history`;
CREATE TABLE `recharge_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'khoa chinh bang lich su nap tien',
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa phu bang account',
  `create` timestamp(6) NOT NULL COMMENT 'ngay nap',
  `money` int(11) NOT NULL COMMENT 'so tien nap',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'trang thai nap',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `reus`(`username`) USING BTREE,
  CONSTRAINT `reus` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply_comment
-- ----------------------------
DROP TABLE IF EXISTS `reply_comment`;
CREATE TABLE `reply_comment`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'khoa chinh bang reply comment',
  `id_comment` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa ngoai bang comment',
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa ngoai bang account',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'noi dung',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `repus`(`username`) USING BTREE,
  INDEX `repco`(`id_comment`) USING BTREE,
  CONSTRAINT `repco` FOREIGN KEY (`id_comment`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `repus` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (3, 'nguoi dung');
INSERT INTO `role` VALUES (2, 'nhan vien');

-- ----------------------------
-- Table structure for transaction_history
-- ----------------------------
DROP TABLE IF EXISTS `transaction_history`;
CREATE TABLE `transaction_history`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'khoa chinh cua bang, tu tang',
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'foreign key cua bang account',
  `id_post` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'khoa ngoai bang post',
  `created` timestamp(0) NOT NULL COMMENT 'thoi gian giao dich',
  `money` int(11) NOT NULL COMMENT 'so tien giao dich',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tranus`(`username`) USING BTREE,
  INDEX `tranpo`(`id_post`) USING BTREE,
  CONSTRAINT `tranpo` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tranus` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
