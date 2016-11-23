CREATE TABLE chat.userinfo (
	id INT NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL,
	password varchar(100) NOT NULL,
	shortname varchar(100) NOT NULL COMMENT '昵称',
	registdate DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lastlogindate DATETIME NOT NULL,
	status INT NOT NULL DEFAULT 1 COMMENT '1未验证，2状态正常，3禁止登录',
	adduser INT NOT  NULL DEFAULT 1 COMMENT '1不验证添加，2需要同意才添加，3拒绝添加',
	token VARCHAR (200) DEFAULT '' COMMENT '安全令牌',
	CONSTRAINT userinfo_PK PRIMARY KEY (id),
	CONSTRAINT userinfo_PK UNIQUE KEY (username)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;


CREATE TABLE chat.friend (
	id INT NOT NULL AUTO_INCREMENT,
	userid INT NOT NULL,
	friendid INT NOT NULL,
	friendshortname varchar(100) NOT NULL,
	status INT NOT NULL DEFAULT 1 COMMENT '1是好友了，2验证中，3已拒绝,4已删除',
	modifydate DATETIME DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT friends_PK PRIMARY KEY (id),
	CONSTRAINT friends_PK UNIQUE KEY (userid,friendid)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;
