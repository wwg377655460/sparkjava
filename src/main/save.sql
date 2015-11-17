CREATE database save_l CHARACTER SET utf8;

use save_l

CREATE TABLE users(
  id INT PRIMARY KEY auto_increment,
  username VARCHAR(100) NOT NULL ,
  password VARCHAR(100) NOT NULL ,
  password_sec VARCHAR(100) NOT NULL
);



CREATE TABLE type(
  id INT PRIMARY KEY auto_increment,
  name VARCHAR(50) NOT NULL ,
  user_id INT DEFAULT 1,
  FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE bill(
  id INT PRIMARY KEY auto_increment,
  money DOUBLE NOT NULL ,
  type_id INT NOT NULL ,
  update_time VARCHAR(100) NOT NULL ,
  remark VARCHAR(100) NOT NULL ,
  user_id INT NOT NULL ,
  FOREIGN KEY(type_id) REFERENCES type(id),
  FOREIGN KEY(user_id) REFERENCES users(id)
);


#按时间建表

CREATE TABLE user_act_p(
  id INT PRIMARY KEY auto_increment,
  user_id INT NOT NULL ,
  type_id INT NOT NULL ,
  time_e INT NOT NULL ,
  update_time TIME NOT NULL ,
  choose INT DEFAULT 50,
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(type_id) REFERENCES type(id)
);


#预定表
CREATE TABLE user_act_m(
  id INT PRIMARY KEY auto_increment,
  time_e INT NOT NULL ,
  type_id INT NOT NULL ,
  choose INT DEFAULT 50,
  FOREIGN KEY(type_id) REFERENCES type(id)
);

#插入一条默认用户
INSERT INTO users VALUES (0, "wer", "123", "123");

#插入预先设定好的类型
 /*
    1、个人收入 2、早午晚餐 3、公共交通 4、水果零食 5、衣服鞋帽 6、日常用品 7、休闲聚餐 8、运动健身 9、网上购物 10、打车租车 11、交流通讯 12、旅游度假 13、化妆饰品 14、数码设备 15、通讯上网 16、邮寄快递 17、宠物宝贝 18、水电煤气 19、学习培训 20、其他杂项
     */
insert INTO type (name) VALUES ("个人收入");
insert INTO type (name) VALUES ("早午晚餐");
insert INTO type (name) VALUES ("公共交通");
insert INTO type (name) VALUES ("水果零食");
insert INTO type (name) VALUES ("衣服鞋帽");
insert INTO type (name) VALUES ("日常用品");
insert INTO type (name) VALUES ("休闲聚餐");
insert INTO type (name) VALUES ("运动健身");
insert INTO type (name) VALUES ("网上购物");
insert INTO type (name) VALUES ("打车租车");
insert INTO type (name) VALUES ("交流通讯");
insert INTO type (name) VALUES ("旅游度假");
insert INTO type (name) VALUES ("化妆饰品");
insert INTO type (name) VALUES ("数码设备");
insert INTO type (name) VALUES ("通讯上网");
insert INTO type (name) VALUES ("邮寄快递");
insert INTO type (name) VALUES ("宠物宝贝");
insert INTO type (name) VALUES ("水电煤气");
insert INTO type (name) VALUES ("学习培训");
insert INTO type (name) VALUES ("其他杂项");


#添加默认数据
insert into user_act_m (time_e, type_id) values (0,1);
insert into user_act_m (time_e, type_id) values (0,2);
insert into user_act_m (time_e, type_id) values (0,3);
insert into user_act_m (time_e, type_id) values (0,4);
insert into user_act_m (time_e, type_id) values (0,5);
insert into user_act_m (time_e, type_id) values (0,6);
insert into user_act_m (time_e, type_id) values (0,7);
insert into user_act_m (time_e, type_id) values (0,8);
insert into user_act_m (time_e, type_id) values (0,9);
insert into user_act_m (time_e, type_id) values (0,10);
insert into user_act_m (time_e, type_id) values (0,11);
insert into user_act_m (time_e, type_id) values (0,12);
insert into user_act_m (time_e, type_id) values (0,13);
insert into user_act_m (time_e, type_id) values (0,14);
insert into user_act_m (time_e, type_id) values (0,15);
insert into user_act_m (time_e, type_id) values (0,16);
insert into user_act_m (time_e, type_id) values (0,17);
insert into user_act_m (time_e, type_id) values (0,18);
insert into user_act_m (time_e, type_id) values (0,19);
insert into user_act_m (time_e, type_id) values (0,20);
insert into user_act_m (time_e, type_id) values (1,1);
insert into user_act_m (time_e, type_id) values (1,2);
insert into user_act_m (time_e, type_id) values (1,3);
insert into user_act_m (time_e, type_id) values (1,4);
insert into user_act_m (time_e, type_id) values (1,5);
insert into user_act_m (time_e, type_id) values (1,6);
insert into user_act_m (time_e, type_id) values (1,7);
insert into user_act_m (time_e, type_id) values (1,8);
insert into user_act_m (time_e, type_id) values (1,9);
insert into user_act_m (time_e, type_id) values (1,10);
insert into user_act_m (time_e, type_id) values (1,11);
insert into user_act_m (time_e, type_id) values (1,12);
insert into user_act_m (time_e, type_id) values (1,13);
insert into user_act_m (time_e, type_id) values (1,14);
insert into user_act_m (time_e, type_id) values (1,15);
insert into user_act_m (time_e, type_id) values (1,16);
insert into user_act_m (time_e, type_id) values (1,17);
insert into user_act_m (time_e, type_id) values (1,18);
insert into user_act_m (time_e, type_id) values (1,19);
insert into user_act_m (time_e, type_id) values (1,20);
insert into user_act_m (time_e, type_id) values (2,1);
insert into user_act_m (time_e, type_id) values (2,2);
insert into user_act_m (time_e, type_id) values (2,3);
insert into user_act_m (time_e, type_id) values (2,4);
insert into user_act_m (time_e, type_id) values (2,5);
insert into user_act_m (time_e, type_id) values (2,6);
insert into user_act_m (time_e, type_id) values (2,7);
insert into user_act_m (time_e, type_id) values (2,8);
insert into user_act_m (time_e, type_id) values (2,9);
insert into user_act_m (time_e, type_id) values (2,10);
insert into user_act_m (time_e, type_id) values (2,11);
insert into user_act_m (time_e, type_id) values (2,12);
insert into user_act_m (time_e, type_id) values (2,13);
insert into user_act_m (time_e, type_id) values (2,14);
insert into user_act_m (time_e, type_id) values (2,15);
insert into user_act_m (time_e, type_id) values (2,16);
insert into user_act_m (time_e, type_id) values (2,17);
insert into user_act_m (time_e, type_id) values (2,18);
insert into user_act_m (time_e, type_id) values (2,19);
insert into user_act_m (time_e, type_id) values (2,20);
insert into user_act_m (time_e, type_id) values (3,1);
insert into user_act_m (time_e, type_id) values (3,2);
insert into user_act_m (time_e, type_id) values (3,3);
insert into user_act_m (time_e, type_id) values (3,4);
insert into user_act_m (time_e, type_id) values (3,5);
insert into user_act_m (time_e, type_id) values (3,6);
insert into user_act_m (time_e, type_id) values (3,7);
insert into user_act_m (time_e, type_id) values (3,8);
insert into user_act_m (time_e, type_id) values (3,9);
insert into user_act_m (time_e, type_id) values (3,10);
insert into user_act_m (time_e, type_id) values (3,11);
insert into user_act_m (time_e, type_id) values (3,12);
insert into user_act_m (time_e, type_id) values (3,13);
insert into user_act_m (time_e, type_id) values (3,14);
insert into user_act_m (time_e, type_id) values (3,15);
insert into user_act_m (time_e, type_id) values (3,16);
insert into user_act_m (time_e, type_id) values (3,17);
insert into user_act_m (time_e, type_id) values (3,18);
insert into user_act_m (time_e, type_id) values (3,19);
insert into user_act_m (time_e, type_id) values (3,20);
insert into user_act_m (time_e, type_id) values (4,1);
insert into user_act_m (time_e, type_id) values (4,2);
insert into user_act_m (time_e, type_id) values (4,3);
insert into user_act_m (time_e, type_id) values (4,4);
insert into user_act_m (time_e, type_id) values (4,5);
insert into user_act_m (time_e, type_id) values (4,6);
insert into user_act_m (time_e, type_id) values (4,7);
insert into user_act_m (time_e, type_id) values (4,8);
insert into user_act_m (time_e, type_id) values (4,9);
insert into user_act_m (time_e, type_id) values (4,10);
insert into user_act_m (time_e, type_id) values (4,11);
insert into user_act_m (time_e, type_id) values (4,12);
insert into user_act_m (time_e, type_id) values (4,13);
insert into user_act_m (time_e, type_id) values (4,14);
insert into user_act_m (time_e, type_id) values (4,15);
insert into user_act_m (time_e, type_id) values (4,16);
insert into user_act_m (time_e, type_id) values (4,17);
insert into user_act_m (time_e, type_id) values (4,18);
insert into user_act_m (time_e, type_id) values (4,19);
insert into user_act_m (time_e, type_id) values (4,20);
insert into user_act_m (time_e, type_id) values (5,1);
insert into user_act_m (time_e, type_id) values (5,2);
insert into user_act_m (time_e, type_id) values (5,3);
insert into user_act_m (time_e, type_id) values (5,4);
insert into user_act_m (time_e, type_id) values (5,5);
insert into user_act_m (time_e, type_id) values (5,6);
insert into user_act_m (time_e, type_id) values (5,7);
insert into user_act_m (time_e, type_id) values (5,8);
insert into user_act_m (time_e, type_id) values (5,9);
insert into user_act_m (time_e, type_id) values (5,10);
insert into user_act_m (time_e, type_id) values (5,11);
insert into user_act_m (time_e, type_id) values (5,12);
insert into user_act_m (time_e, type_id) values (5,13);
insert into user_act_m (time_e, type_id) values (5,14);
insert into user_act_m (time_e, type_id) values (5,15);
insert into user_act_m (time_e, type_id) values (5,16);
insert into user_act_m (time_e, type_id) values (5,17);
insert into user_act_m (time_e, type_id) values (5,18);
insert into user_act_m (time_e, type_id) values (5,19);
insert into user_act_m (time_e, type_id) values (5,20);
insert into user_act_m (time_e, type_id) values (6,1);
insert into user_act_m (time_e, type_id) values (6,2);
insert into user_act_m (time_e, type_id) values (6,3);
insert into user_act_m (time_e, type_id) values (6,4);
insert into user_act_m (time_e, type_id) values (6,5);
insert into user_act_m (time_e, type_id) values (6,6);
insert into user_act_m (time_e, type_id) values (6,7);
insert into user_act_m (time_e, type_id) values (6,8);
insert into user_act_m (time_e, type_id) values (6,9);
insert into user_act_m (time_e, type_id) values (6,10);
insert into user_act_m (time_e, type_id) values (6,11);
insert into user_act_m (time_e, type_id) values (6,12);
insert into user_act_m (time_e, type_id) values (6,13);
insert into user_act_m (time_e, type_id) values (6,14);
insert into user_act_m (time_e, type_id) values (6,15);
insert into user_act_m (time_e, type_id) values (6,16);
insert into user_act_m (time_e, type_id) values (6,17);
insert into user_act_m (time_e, type_id) values (6,18);
insert into user_act_m (time_e, type_id) values (6,19);
insert into user_act_m (time_e, type_id) values (6,20);
insert into user_act_m (time_e, type_id) values (7,1);
insert into user_act_m (time_e, type_id) values (7,2);
insert into user_act_m (time_e, type_id) values (7,3);
insert into user_act_m (time_e, type_id) values (7,4);
insert into user_act_m (time_e, type_id) values (7,5);
insert into user_act_m (time_e, type_id) values (7,6);
insert into user_act_m (time_e, type_id) values (7,7);
insert into user_act_m (time_e, type_id) values (7,8);
insert into user_act_m (time_e, type_id) values (7,9);
insert into user_act_m (time_e, type_id) values (7,10);
insert into user_act_m (time_e, type_id) values (7,11);
insert into user_act_m (time_e, type_id) values (7,12);
insert into user_act_m (time_e, type_id) values (7,13);
insert into user_act_m (time_e, type_id) values (7,14);
insert into user_act_m (time_e, type_id) values (7,15);
insert into user_act_m (time_e, type_id) values (7,16);
insert into user_act_m (time_e, type_id) values (7,17);
insert into user_act_m (time_e, type_id) values (7,18);
insert into user_act_m (time_e, type_id) values (7,19);
insert into user_act_m (time_e, type_id) values (7,20);
insert into user_act_m (time_e, type_id) values (8,1);
insert into user_act_m (time_e, type_id) values (8,2);
insert into user_act_m (time_e, type_id) values (8,3);
insert into user_act_m (time_e, type_id) values (8,4);
insert into user_act_m (time_e, type_id) values (8,5);
insert into user_act_m (time_e, type_id) values (8,6);
insert into user_act_m (time_e, type_id) values (8,7);
insert into user_act_m (time_e, type_id) values (8,8);
insert into user_act_m (time_e, type_id) values (8,9);
insert into user_act_m (time_e, type_id) values (8,10);
insert into user_act_m (time_e, type_id) values (8,11);
insert into user_act_m (time_e, type_id) values (8,12);
insert into user_act_m (time_e, type_id) values (8,13);
insert into user_act_m (time_e, type_id) values (8,14);
insert into user_act_m (time_e, type_id) values (8,15);
insert into user_act_m (time_e, type_id) values (8,16);
insert into user_act_m (time_e, type_id) values (8,17);
insert into user_act_m (time_e, type_id) values (8,18);
insert into user_act_m (time_e, type_id) values (8,19);
insert into user_act_m (time_e, type_id) values (8,20);
insert into user_act_m (time_e, type_id) values (9,1);
insert into user_act_m (time_e, type_id) values (9,2);
insert into user_act_m (time_e, type_id) values (9,3);
insert into user_act_m (time_e, type_id) values (9,4);
insert into user_act_m (time_e, type_id) values (9,5);
insert into user_act_m (time_e, type_id) values (9,6);
insert into user_act_m (time_e, type_id) values (9,7);
insert into user_act_m (time_e, type_id) values (9,8);
insert into user_act_m (time_e, type_id) values (9,9);
insert into user_act_m (time_e, type_id) values (9,10);
insert into user_act_m (time_e, type_id) values (9,11);
insert into user_act_m (time_e, type_id) values (9,12);
insert into user_act_m (time_e, type_id) values (9,13);
insert into user_act_m (time_e, type_id) values (9,14);
insert into user_act_m (time_e, type_id) values (9,15);
insert into user_act_m (time_e, type_id) values (9,16);
insert into user_act_m (time_e, type_id) values (9,17);
insert into user_act_m (time_e, type_id) values (9,18);
insert into user_act_m (time_e, type_id) values (9,19);
insert into user_act_m (time_e, type_id) values (9,20);
insert into user_act_m (time_e, type_id) values (10,1);
insert into user_act_m (time_e, type_id) values (10,2);
insert into user_act_m (time_e, type_id) values (10,3);
insert into user_act_m (time_e, type_id) values (10,4);
insert into user_act_m (time_e, type_id) values (10,5);
insert into user_act_m (time_e, type_id) values (10,6);
insert into user_act_m (time_e, type_id) values (10,7);
insert into user_act_m (time_e, type_id) values (10,8);
insert into user_act_m (time_e, type_id) values (10,9);
insert into user_act_m (time_e, type_id) values (10,10);
insert into user_act_m (time_e, type_id) values (10,11);
insert into user_act_m (time_e, type_id) values (10,12);
insert into user_act_m (time_e, type_id) values (10,13);
insert into user_act_m (time_e, type_id) values (10,14);
insert into user_act_m (time_e, type_id) values (10,15);
insert into user_act_m (time_e, type_id) values (10,16);
insert into user_act_m (time_e, type_id) values (10,17);
insert into user_act_m (time_e, type_id) values (10,18);
insert into user_act_m (time_e, type_id) values (10,19);
insert into user_act_m (time_e, type_id) values (10,20);
insert into user_act_m (time_e, type_id) values (11,1);
insert into user_act_m (time_e, type_id) values (11,2);
insert into user_act_m (time_e, type_id) values (11,3);
insert into user_act_m (time_e, type_id) values (11,4);
insert into user_act_m (time_e, type_id) values (11,5);
insert into user_act_m (time_e, type_id) values (11,6);
insert into user_act_m (time_e, type_id) values (11,7);
insert into user_act_m (time_e, type_id) values (11,8);
insert into user_act_m (time_e, type_id) values (11,9);
insert into user_act_m (time_e, type_id) values (11,10);
insert into user_act_m (time_e, type_id) values (11,11);
insert into user_act_m (time_e, type_id) values (11,12);
insert into user_act_m (time_e, type_id) values (11,13);
insert into user_act_m (time_e, type_id) values (11,14);
insert into user_act_m (time_e, type_id) values (11,15);
insert into user_act_m (time_e, type_id) values (11,16);
insert into user_act_m (time_e, type_id) values (11,17);
insert into user_act_m (time_e, type_id) values (11,18);
insert into user_act_m (time_e, type_id) values (11,19);
insert into user_act_m (time_e, type_id) values (11,20);
insert into user_act_m (time_e, type_id) values (12,1);
insert into user_act_m (time_e, type_id) values (12,2);
insert into user_act_m (time_e, type_id) values (12,3);
insert into user_act_m (time_e, type_id) values (12,4);
insert into user_act_m (time_e, type_id) values (12,5);
insert into user_act_m (time_e, type_id) values (12,6);
insert into user_act_m (time_e, type_id) values (12,7);
insert into user_act_m (time_e, type_id) values (12,8);
insert into user_act_m (time_e, type_id) values (12,9);
insert into user_act_m (time_e, type_id) values (12,10);
insert into user_act_m (time_e, type_id) values (12,11);
insert into user_act_m (time_e, type_id) values (12,12);
insert into user_act_m (time_e, type_id) values (12,13);
insert into user_act_m (time_e, type_id) values (12,14);
insert into user_act_m (time_e, type_id) values (12,15);
insert into user_act_m (time_e, type_id) values (12,16);
insert into user_act_m (time_e, type_id) values (12,17);
insert into user_act_m (time_e, type_id) values (12,18);
insert into user_act_m (time_e, type_id) values (12,19);
insert into user_act_m (time_e, type_id) values (12,20);
