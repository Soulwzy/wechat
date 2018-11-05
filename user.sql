# Host: localhost  (Version: 5.5.27)
# Date: 2018-11-04 18:07:18
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `mcode` varchar(30) DEFAULT NULL,
  `head_pic_path` varchar(32) DEFAULT NULL,
  `friend_pic_path` varchar(32) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `motto` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'张三','zhangsan','asd','asd','山西','太原','千山鸟飞绝','1','1',1),(2,'李四','lisi','bggt','bgt','山西','临汾','万径人踪灭','2','2',0),(3,'王五','wangwu',NULL,NULL,'山西','运城','孤舟蓑笠翁','3','3',1),(4,'赵六','zhaoliu',NULL,NULL,'山西','吕梁','独钓寒江雪','4','4',0),(5,'钱七','qianqi',NULL,NULL,'山西','晋中','一去二三里','5','5',1),(6,'马八','maba',NULL,NULL,'山西','长治','烟村四五家','6','6',0),(7,'董九','dongjiu',NULL,NULL,'山西','汾阳','亭台六七座','7','7',1),(8,'刘十','liushi',NULL,NULL,'山西','孝义','八九十只花','8','123456',1),(9,'吕二','lver',NULL,NULL,'山西','高平','哇哈哈哈哈','19999999999','123456',1),(10,'子华星','xing',NULL,NULL,'福建','厦门','你还怕大雨吗','15312345678','123456',0),(11,'桐宇','tong',NULL,NULL,'山西','大同','短发','13364738854','123456',0),(12,'紫夏','xia',NULL,NULL,'陕西','西安','夏天夏天悄悄过去留下小秘密','15234342453','123456',0),(13,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'16923455322','123456',0),(14,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15123680766','123456',0),(15,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13567885678','123456',0),(16,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13382635772','123456',0),(17,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13725782921','123456',0),(18,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15836367891','123456',0),(19,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13472628971','123456',0),(20,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'13587281913','123456',0),(21,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','123456',0),(22,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(23,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(24,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(25,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(26,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0),(27,'张三',NULL,NULL,NULL,NULL,NULL,NULL,'15312345678','password',0);
