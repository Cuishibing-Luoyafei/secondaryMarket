create database secondaryMarket;

/*
 *
user
userId
userNackName
userPassword
userRealName
userTel
userQQ
userEmail
userSchool
userRole

 * */	

create table user (
	userId int primary key auto_increment,
	userNackName varchar(30) unique,
	userPassword varchar(30) not null,
	userRealName varchar(30),
	userTel varchar(20),
	userQQ varchar(20),
	userEmail varchar(20),
	userSchool varchar(30),
	userRole int default 1
);

insert into user values(null, 'diamond', 'luo', '���Ƿ�', '18888888888', '666666666', 'luo@foxmeil.com', '�����Ƽ���ѧ', 1);

/*
 * theme
themeId
themeTitle
themeContent
themeTime datetime
themeUserId
themeCommodityId
themeIsRead
 * 
 * 
 * */
create table theme (
	themeId int primary key auto_increment,
	themeTitle varchar(255) not null,
	themeContent text not null,
	themeTime datetime not null,
	themeUserId int references user(userId),
	themeCommodityId int references commodity(commodityId),
	themeIsRead int default 0
);
insert into theme values(null, 'hello', 'hello world', now(), 1, 1, 0);
/*
 * reply
replyId
replyThemeId
replyUserId
replyContent
replyTime
 * 
 */*/
create table reply (
	replyId int primary key auto_increment,
	replyThemeId int references theme(themeId),
	replyUserId int references user(userId),
	replyContent text not null,
	replyTime datetime not null
);


/*
 * commodity
commodityId int
commodityName String
commodityCategary int
commodityStatus int
commodityPicture String
commodityDescribe text
commodityCount  数量 int
commodityOldNewLevel int
commodityOldPrice  老价格 String
commodityNewPrice  新价格 String
commodityOwner  拥有者Id int
commodityDownDay 下架天数 int
 * 
 * */

create table commodity (
	commodityId int primary key auto_increment,
	commodityName varchar(30) not null,
	commodityCategary varchar(30) not null,
	commodityStatus int default -1,
	commodityPicture varchar(255) not null,
	commodityDescribe text not null,
	commodityCount int default 1,
	commodityOldNewLevel int,
	commodityOldPrice varchar(11),
	commodityNewPrice varchar(11),
	commodityOwner int references user(userId),
	commodityDownDay int
);
