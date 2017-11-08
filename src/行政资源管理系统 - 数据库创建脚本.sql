-- 删除arm数据库
DROP DATABASE IF EXISTS amr ;

-- 创建arm数据库
CREATE DATABASE amr CHARACTER SET UTF8 ;

-- 使用arm数据库
USE amr ;

-- 创建数据表
CREATE TABLE level (
   lid                  int 	AUTO_INCREMENT ,
   title                varchar(50),
   losal                double,
   hisal                double,
   CONSTRAINT pk_lid PRIMARY KEY (lid)
);

CREATE TABLE dept (
   did                  int AUTO_INCREMENT ,
   title                varchar(50),
   sflag                int,
   CONSTRAINT pk_did PRIMARY KEY (did)
);

CREATE TABLE groups (
   gid                  int  AUTO_INCREMENT,
   title                varchar(50),
   type                 varchar(50) ,
   CONSTRAINT pk_gid PRIMARY KEY (gid)
);

CREATE TABLE dept_groups (
   did                  int,
   gid                  int ,
   CONSTRAINT fk_did FOREIGN KEY(did) REFERENCES dept(did) ON DELETE CASCADE ,
   CONSTRAINT fk_gid FOREIGN KEY(gid) REFERENCES groups(gid) ON DELETE CASCADE 
);

CREATE TABLE action (
   actid                int AUTO_INCREMENT,
   gid                  int,
   title                varchar(50),
   url                  varchar(200),
   sflag                 int,
   CONSTRAINT pk_actid PRIMARY KEY (actid) ,
   CONSTRAINT fk_gid3 FOREIGN KEY(gid) REFERENCES groups(gid) ON DELETE CASCADE
);

CREATE TABLE emp (
   eid                  int ,
   did                  int,
   lid                  int,
   heid                 int,
   name                 varchar(50),
   password             varchar(32),
   phone                varchar(50),
   salary               double,
   note                 text,
   aflag                int,
   sex                  varchar(10) default '男',
   photo                varchar(200) ,
   CONSTRAINT pk_eid PRIMARY KEY (eid) ,
   CONSTRAINT fk_did2 FOREIGN KEY(did) REFERENCES dept(did) ,
   CONSTRAINT fk_lid2 FOREIGN KEY(lid) REFERENCES level(lid) ,
   CONSTRAINT fk_heid2 FOREIGN KEY(heid) REFERENCES emp(eid) 
);

CREATE TABLE type (
   tid                  int AUTO_INCREMENT,
   title                varchar(50),
   CONSTRAINT pk_tid PRIMARY KEY (tid)
);

CREATE TABLE subtype (
   stid                 int AUTO_INCREMENT,
   tid                  int,
   title                varchar(50),
   CONSTRAINT pk_stid PRIMARY KEY (stid) ,
   CONSTRAINT fk_tid2 FOREIGN KEY(tid) REFERENCES type(tid) ON DELETE CASCADE 
);


CREATE TABLE res (
   rid                  int  AUTO_INCREMENT,
   tid                  int,
   stid                 int,
   title                varchar(50),
   price                double,
   indate               datetime,
   photo                varchar(200),
   rflag                int,
   amount               int,
   CONSTRAINT pk_rid PRIMARY KEY (rid) ,
   CONSTRAINT fk_tid3 FOREIGN KEY(tid) REFERENCES type(tid) ,
   CONSTRAINT fk_stid3 FOREIGN KEY(stid) REFERENCES subtype(stid) 
);

CREATE TABLE take (
   tkid                 int  AUTO_INCREMENT,
   geid                 int,
   beid                 int,
   rid                  int,
   gdate                datetime,
   rdate                datetime,
   note                 text,
   amount               int ,
   status               int ,
   CONSTRAINT pk_tkid PRIMARY KEY (tkid) ,
   CONSTRAINT fk_geid4 FOREIGN KEY(geid) REFERENCES emp(eid) ON DELETE CASCADE,
   CONSTRAINT fk_beid4 FOREIGN KEY(beid) REFERENCES emp(eid) ON DELETE CASCADE 
);

CREATE TABLE purchase (
   pid                  int AUTO_INCREMENT,
   eid                  int,
   meid                 int,
   title                varchar(50),
   total                double,
   status               int,
   pubdate              datetime,
   note                 text,
   CONSTRAINT pk_pid PRIMARY KEY (pid) ,
   CONSTRAINT fk_eid5 FOREIGN KEY(eid) REFERENCES emp(eid) ON DELETE CASCADE ,
   CONSTRAINT fk_meid5 FOREIGN KEY(meid) REFERENCES emp(eid) ON DELETE CASCADE
);


CREATE TABLE details (
   did                  int  AUTO_INCREMENT,
   pid                  int,
   stid                 int,
   tid                  int,
   rid                  int,
   eid                  int,
   title                varchar(50),
   price                double,
   amount               int,
   photo                varchar(50),
   rflag                int,
   CONSTRAINT pk_did PRIMARY KEY (did) ,
   CONSTRAINT fk_pid6 FOREIGN KEY(pid) REFERENCES purchase(pid) ,
   CONSTRAINT fk_tid6 FOREIGN KEY(tid) REFERENCES type(tid) ,
   CONSTRAINT fk_stid6 FOREIGN KEY(stid) REFERENCES subtype(stid) ,
   CONSTRAINT fk_rid6 FOREIGN KEY(rid) REFERENCES res(rid) ,
   CONSTRAINT fk_eid6 FOREIGN KEY(eid) REFERENCES emp(eid)
);

-- 测试数据
-- 增加雇员级别数据
INSERT INTO level(title,losal,hisal) VALUES ('实习生',800.0,1500.0) ;
INSERT INTO level(title,losal,hisal) VALUES ('普通员工',1501.0,3500.0) ;
INSERT INTO level(title,losal,hisal) VALUES ('部门主管',3501.0,5000.0) ;
INSERT INTO level(title,losal,hisal) VALUES ('部门经理',5001.0,8000.0) ;
INSERT INTO level(title,losal,hisal) VALUES ('总监',8001.0,12000.0) ;
INSERT INTO level(title,losal,hisal) VALUES ('副总裁',12001.0,20000.0) ;
INSERT INTO level(title,losal,hisal) VALUES ('总裁',20001.0,99999.0) ;

-- 增加办公用品分类
INSERT INTO type(title) VALUES ('文件档案用品') ;
INSERT INTO type(title) VALUES ('桌面用品') ;
INSERT INTO type(title) VALUES ('办公设备') ;
INSERT INTO type(title) VALUES ('财务用品') ;
INSERT INTO type(title) VALUES ('办公耗材') ;


-- 增加子分类
INSERT INTO subtype(tid,title) VALUES (1,'报告夹') ;
INSERT INTO subtype(tid,title) VALUES (1,'分类文件夹') ;
INSERT INTO subtype(tid,title) VALUES (1,'电脑夹') ;
INSERT INTO subtype(tid,title) VALUES (1,'档案袋') ;
INSERT INTO subtype(tid,title) VALUES (1,'名片盒') ;
INSERT INTO subtype(tid,title) VALUES (1,'拉链袋') ;
INSERT INTO subtype(tid,title) VALUES (1,'资料夹') ;
INSERT INTO subtype(tid,title) VALUES (1,'文件蓝') ;
INSERT INTO subtype(tid,title) VALUES (1,'图纸夹') ;
INSERT INTO subtype(tid,title) VALUES (1,'挂劳夹') ;
INSERT INTO subtype(tid,title) VALUES (1,'文件套') ;
INSERT INTO subtype(tid,title) VALUES (1,'资料册') ;
INSERT INTO subtype(tid,title) VALUES (1,'票据夹') ;
INSERT INTO subtype(tid,title) VALUES (1,'公事包') ;

INSERT INTO subtype(tid,title) VALUES (2,'起钉器') ;
INSERT INTO subtype(tid,title) VALUES (2,'打孔器') ;
INSERT INTO subtype(tid,title) VALUES (2,'美工刀') ;
INSERT INTO subtype(tid,title) VALUES (2,'计算器') ;
INSERT INTO subtype(tid,title) VALUES (2,'订书机') ;
INSERT INTO subtype(tid,title) VALUES (2,'剪刀') ;
INSERT INTO subtype(tid,title) VALUES (2,'切纸刀') ;
INSERT INTO subtype(tid,title) VALUES (2,'胶水') ;
INSERT INTO subtype(tid,title) VALUES (2,'胶棒') ;
INSERT INTO subtype(tid,title) VALUES (2,'钉针系列') ;
INSERT INTO subtype(tid,title) VALUES (2,'壁纸刀') ;
INSERT INTO subtype(tid,title) VALUES (2,'票夹') ;
INSERT INTO subtype(tid,title) VALUES (2,'胶带') ;
INSERT INTO subtype(tid,title) VALUES (2,'仪尺') ;
INSERT INTO subtype(tid,title) VALUES (2,'胶带座') ;
INSERT INTO subtype(tid,title) VALUES (2,'圆规') ;

INSERT INTO subtype(tid,title) VALUES (3,'笔记本电脑') ;
INSERT INTO subtype(tid,title) VALUES (3,'碎纸机') ;
INSERT INTO subtype(tid,title) VALUES (3,'考勤机') ;
INSERT INTO subtype(tid,title) VALUES (3,'过塑机') ;
INSERT INTO subtype(tid,title) VALUES (3,'电话机') ;
INSERT INTO subtype(tid,title) VALUES (3,'加湿器') ;
INSERT INTO subtype(tid,title) VALUES (3,'饮水机') ;
INSERT INTO subtype(tid,title) VALUES (3,'电风扇') ;
INSERT INTO subtype(tid,title) VALUES (3,'吸尘器') ;
INSERT INTO subtype(tid,title) VALUES (3,'投影仪') ;
INSERT INTO subtype(tid,title) VALUES (3,'打印机') ;
INSERT INTO subtype(tid,title) VALUES (3,'扫描仪') ;

INSERT INTO subtype(tid,title) VALUES (4,'账本') ;
INSERT INTO subtype(tid,title) VALUES (4,'记事本') ;
INSERT INTO subtype(tid,title) VALUES (4,'算盘') ;
INSERT INTO subtype(tid,title) VALUES (4,'墨水') ;
INSERT INTO subtype(tid,title) VALUES (4,'报表') ;
INSERT INTO subtype(tid,title) VALUES (4,'凭证') ;
INSERT INTO subtype(tid,title) VALUES (4,'钢笔') ;
INSERT INTO subtype(tid,title) VALUES (4,'印泥') ;
INSERT INTO subtype(tid,title) VALUES (4,'科目章') ;
INSERT INTO subtype(tid,title) VALUES (4,'橡皮筋') ;
INSERT INTO subtype(tid,title) VALUES (4,'大头针') ;
INSERT INTO subtype(tid,title) VALUES (4,'回形针') ;

INSERT INTO subtype(tid,title) VALUES (5,'硒鼓') ;
INSERT INTO subtype(tid,title) VALUES (5,'墨盒') ;
INSERT INTO subtype(tid,title) VALUES (5,'色带') ;
INSERT INTO subtype(tid,title) VALUES (5,'粉盒') ;
INSERT INTO subtype(tid,title) VALUES (5,'组件') ;
INSERT INTO subtype(tid,title) VALUES (5,'皮纹纸') ;
INSERT INTO subtype(tid,title) VALUES (5,'装订透片') ;
INSERT INTO subtype(tid,title) VALUES (5,'装订胶圈') ;
INSERT INTO subtype(tid,title) VALUES (5,'彩色卡纸') ;
INSERT INTO subtype(tid,title) VALUES (5,'不干胶打印纸') ;
INSERT INTO subtype(tid,title) VALUES (5,'网线转换接头') ;
INSERT INTO subtype(tid,title) VALUES (5,'电脑打印纸') ;
INSERT INTO subtype(tid,title) VALUES (5,'电源线') ;
INSERT INTO subtype(tid,title) VALUES (5,'网线') ;

-- 增加部门数据，sflag=1表示特殊部门，不列表显示
INSERT INTO dept(title,sflag) VALUES ('管理部',1) ;
INSERT INTO dept(title,sflag) VALUES ('人事部',0) ;
INSERT INTO dept(title,sflag) VALUES ('行政部',0) ;
INSERT INTO dept(title,sflag) VALUES ('市场部',0) ;
INSERT INTO dept(title,sflag) VALUES ('财务部',0) ;

-- 增加权限组，sflag = 1表示特殊部门权限组
INSERT INTO groups(title,type) VALUES ('权限管理','管理部-权限组') ;
INSERT INTO groups(title,type) VALUES ('人事管理','管理部-权限组') ;
INSERT INTO groups(title,type) VALUES ('办公用品','管理部-权限组') ;
INSERT INTO groups(title,type) VALUES ('人事管理','人事部-权限组') ;
INSERT INTO groups(title,type) VALUES ('办公用品','非行政部-权限组') ;
INSERT INTO groups(title,type) VALUES ('办公用品','行政部-权限组') ;
INSERT INTO groups(title,type) VALUES ('办公用品','财务部-权限组') ;

-- 增加权限信息，sflag = 1表示显示在默认菜单中，如果设置为0表示为页面内权限
INSERT INTO action(gid,title,url,sflag) VALUES (1,'增加管理员','/pages/admin/addPre.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (1,'增加管理员','/pages/admin/add.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (1,'管理员列表','/pages/admin/list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (2,'部门列表','/pages/dept/list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (3,'用品分类','/pages/type/list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (2,'查看部门权限','/pages/groups/list.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (2,'部门修改','/pages/dept/edit.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (3,'查看子分类','/pages/type/listSubtype.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (3,'分类修改','/pages/type/edit.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (3,'子分类修改','/pages/type/editSubtype.action',0) ;


INSERT INTO action(gid,title,url,sflag) VALUES (4,'增加员工','/pages/emp/addPre.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (4,'增加员工','/pages/emp/add.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (4,'员工列表','/pages/emp/list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (4,'编辑员工','/pages/emp/editPre.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (4,'编辑员工','/pages/emp/edit.action',0) ;

INSERT INTO action(gid,title,url,sflag) VALUES (5,'办公用品列表','/pages/res/list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (5,'领取记录','/pages/res/emp_list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (5,'待领清单','/pages/res/preget.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (5,'加入领取清单','/pages/take/add.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (5,'修改领取清单','/pages/take/edit.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (5,'删除领取清单','/pages/take/rm.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (5,'领取申请','/pages/take/get.action',0) ;


INSERT INTO action(gid,title,url,sflag) VALUES (6,'办公用品列表','/pages/res/list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'领取记录','/pages/res/emp_list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'待购清单','/pages/res/prebuy.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'待领清单','/pages/res/preget.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'我的申请','/pages/purchase/apply.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'领取审批','/pages/res/audit.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'购入申请','/pages/purchase/list.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'提交购买申请','/pages/purchase/add.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'加入领取清单','/pages/take/add.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'修改领取清单','/pages/take/edit.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'删除领取清单','/pages/take/rm.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (6,'领取申请','/pages/take/get.action',0) ;

INSERT INTO action(gid,title,url,sflag) VALUES (7,'办公用品列表','/pages/res/list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (7,'领取记录','/pages/res/emp_list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (7,'加入领取清单','/pages/take/add.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (7,'修改领取清单','/pages/take/edit.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (7,'删除领取清单','/pages/take/rm.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (7,'领取申请','/pages/take/get.action',0) ;
INSERT INTO action(gid,title,url,sflag) VALUES (7,'购入申请','/pages/purchase/list.action',1) ;
INSERT INTO action(gid,title,url,sflag) VALUES (7,'购入审核','/pages/purchase/audit.action',0) ;

INSERT INTO emp(eid,did,name,password,phone,aflag,photo) VALUES (1000,1,'魔乐科技','5D41402ABC4B2A76B9719D911017C592',110,1,'nophoto.png') ;
INSERT INTO emp(eid,did,name,password,phone,aflag,lid,salary,photo) VALUES (2001,2,'隔壁老王','5D41402ABC4B2A76B9719D911017C592',120,0,4,7000.0,'nophoto.png') ;
INSERT INTO emp(eid,did,name,password,phone,aflag,lid,salary,photo) VALUES (3001,3,'老孙','5D41402ABC4B2A76B9719D911017C592',130,0,4,7500.0,'nophoto.png') ;
INSERT INTO emp(eid,did,name,password,phone,aflag,lid,salary,photo) VALUES (4001,4,'老花','5D41402ABC4B2A76B9719D911017C592',130,0,4,7500.0,'nophoto.png') ;
INSERT INTO emp(eid,did,name,password,phone,aflag,lid,salary,photo) VALUES (5001,5,'老田','5D41402ABC4B2A76B9719D911017C592',130,0,4,7500.0,'nophoto.png') ;

INSERT INTO dept_groups(did,gid) VALUES (1,1) ;
INSERT INTO dept_groups(did,gid) VALUES (1,2) ;
INSERT INTO dept_groups(did,gid) VALUES (1,3) ;
INSERT INTO dept_groups(did,gid) VALUES (2,4) ;
INSERT INTO dept_groups(did,gid) VALUES (2,5) ;
INSERT INTO dept_groups(did,gid) VALUES (3,6) ;
INSERT INTO dept_groups(did,gid) VALUES (4,5) ;
INSERT INTO dept_groups(did,gid) VALUES (5,7) ;
