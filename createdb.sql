CREATE TABLE ADDRESS
   (	"ADDRID" CHAR(10 BYTE) NOT NULL ENABLE, 
	"APTNUM" CHAR(20 BYTE), 
	"STREET_ADDRESS" CHAR(50 BYTE), 
	"CITY" CHAR(50 BYTE), 
	"STATE" CHAR(10 BYTE), 
	"ZIPCODE" CHAR(10 BYTE), 
	"MOBILEPHONENUM" CHAR(30 BYTE), 
	"VERTICE" SDO_GEOMETRY, 
	 PRIMARY KEY ("ADDRID")
	);
  insert into user_sdo_geom_metadata values('ADDRESS','VERTICE',
  SDO_DIM_ARRAY(SDO_DIM_ELEMENT('x',0,1000,1),SDO_DIM_ELEMENT('Y',0,1000,1)),NULL);
create index index_ADDRESS on ADDRESS(VERTICE) indextype is MDSYS.SPATIAL_INDEX;

 CREATE TABLE CUSTOMERS
 (	"EMAIL" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"FNAME" VARCHAR2(20 BYTE), 
	"LNAME" VARCHAR2(20 BYTE), 
	"BIRTHDAY" VARCHAR2(20 BYTE), 
	"NICKNAME" VARCHAR2(20 BYTE), 
	"GENDER" VARCHAR2(20 BYTE), 
	"ACCOUNT_NUM" VARCHAR2(50 BYTE), 
	"EXPIRED_DATE" VARCHAR2(15 BYTE), 
	"CSC" VARCHAR2(20 BYTE), 
	"JOIN_TIME" VARCHAR2(20 BYTE), 
	"ADDRID" CHAR(10 BYTE), 
	"PROFILE_PHOTO_ID" VARCHAR2(20 BYTE), 
	 PRIMARY KEY ("EMAIL"),
	 FOREIGN KEY ("ADDRID") REFERENCES ADDRESS  
  ) ;
  
     CREATE TABLE SELLERS
   (	"EMAIL" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"FNAME" VARCHAR2(15 BYTE), 
	"LNAME" VARCHAR2(15 BYTE), 
	"BIRTHDATE" VARCHAR2(20 BYTE), 
	"NICKNAME" VARCHAR2(15 BYTE), 
	"GENDER" VARCHAR2(1 BYTE), 
	"ACCOUNT_NUM" VARCHAR2(30 BYTE), 
	"EXPIRED_DATE" VARCHAR2(15 BYTE), 
	"CSC" VARCHAR2(10 BYTE), 
	"JOIN_TIME" VARCHAR2(20 BYTE), 
	"ADDRESS_ID" CHAR(10 BYTE), 
	"PROFILE_PHOTO_ID" VARCHAR2(10 BYTE), 
	PRIMARY KEY ("EMAIL"), 
	FOREIGN KEY ("ADDRESS_ID") REFERENCES ADDRESS
   );
    CREATE TABLE USERS
 (	"EMAIL" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"FNAME" VARCHAR2(20 BYTE), 
	"LNAME" VARCHAR2(20 BYTE), 
	"BIRTHDAY" VARCHAR2(20 BYTE),    
	"STRNO" CHAR(10 BYTE), 
  "STRADRESS" CHAR(30 BYTE),
  "CITY" char(10 byte),
  "STATE" char(10 byte),
  "ZIP" char(10 byte), 
	"PASSWD" VARCHAR2(50 BYTE),
	"LONGTITUDE" VARCHAR2(50 BYTE),
	"LATITUDE" VARCHAR2(50 BYTE),
	 PRIMARY KEY ("EMAIL")
  ) ;
      CREATE TABLE PRODUCTS 
   (	"PRODUCTID" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"CATEGORY" VARCHAR2(20 BYTE), 
	"NAME" VARCHAR2(80 BYTE), 
	"BRAND" VARCHAR2(40 BYTE), 
	"PRICE" FLOAT(126), 
	"STOCK_QUANTITY" NUMBER(*,0), 
	 PRIMARY KEY ("PRODUCTID") 
   ) ;
      CREATE TABLE ORDERS
   (	"ORDERID" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"CUSTOMER" VARCHAR2(30 BYTE), 
	"PROQTY" VARCHAR2(40 BYTE),
	"TOTALPRICE" FLOAT(126), 
	"PLACETIME" VARCHAR2(50 BYTE), 
	 PRIMARY KEY ("ORDERID") 
   );
     CREATE TABLE REVIEWS
   (	"REVIEWID" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"RATING" VARCHAR2(5 BYTE), 
	"CONTENT" VARCHAR2(100 BYTE), 
	"AUTHORID" VARCHAR2(50 BYTE),
	"PRODUCTID" VARCHAR2(20 BYTE),
	 PRIMARY KEY ("REVIEWID"),
	 FOREIGN KEY ("PRODUCTID") REFERENCES PRODUCTS 
  ); 
  CREATE TABLE LIKEREVIEW1
   (	"REVIEWID" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"CUSTOMER" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"TIME" VARCHAR2(20 BYTE), 
	 PRIMARY KEY ("REVIEWID", "CUSTOMER"),
	  FOREIGN KEY ("REVIEWID") REFERENCES REVIEWS 
   );
   
CREATE TABLE CONTAIN
   (	"ORDERID" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"PRODUCTID" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"QTY" VARCHAR2(5 BYTE), 
	PRIMARY KEY ("ORDERID", "PRODUCTID"),
	 FOREIGN KEY ("ORDERID") REFERENCES ORDERS, 
	FOREIGN KEY ("PRODUCTID") REFERENCES PRODUCTS 
   ) ;

create or replace trigger total
before insert on orders
FOR EACH ROW
begin
  update orders set totalprice=(
  select sum(c1.qty*p1.price)
  from products p1, contain c1
  where p1.PRODUCTID=c1.PRODUCTID and c1.ORDERID=orders.orderid);
end;
/
   



 SET DEFINE OFF;



Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A1        ','655                 ','W Jefferson                                       ','Los Angeles                                       ','CA        ','90007     ','1-213-995-2968                ',SDO_GEOMETRY(2001,null, sdo_point_type(460, 434, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A2        ','124-02              ','Roosevelt AVE                                     ','Flushing                                          ','NY        ','11368     ','1-299-995-3009                ',SDO_GEOMETRY(2001,null, sdo_point_type(165, 195, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A3        ','8446                ','Melrose Pl                                        ','Los Angeles                                       ','CA        ','90069     ','1-213-995-9700                ',SDO_GEOMETRY(2001,null, sdo_point_type(770, 165, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A4        ','1300                ','E Main St                                         ','Alhambra                                          ','CA        ','91801     ','1-213-995-3131                ',SDO_GEOMETRY(2001,null, sdo_point_type(450, 455, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A5        ','3335                ','S Figueroa St.                                    ','Los Angeles                                       ','CA        ','90007     ','1-213-995-6183                ',SDO_GEOMETRY(2001,null, sdo_point_type(310, 277, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A6        ','2400                ','Broadway                                          ','Santa Monica                                      ','CA        ','90404     ','1-213-995-2220                ',SDO_GEOMETRY(2001,null, sdo_point_type(333, 157, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A7        ','1445                ','South Lemon Street                                ','Fullerton                                         ','CA        ','92832     ','1-213-995-6210                ',SDO_GEOMETRY(2001,null, sdo_point_type(470, 430, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A8        ','2300                ','Marine Drive                                      ','San Francisco                                     ','CA        ','94129     ','1-213-995-3041                ',SDO_GEOMETRY(2001,null, sdo_point_type(45, 407, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A9        ','1                   ','Statue of Liberty National Monumentmore           ','Ney York                                          ','NY        ','10004     ','1-299-995-4796                ',SDO_GEOMETRY(2001,null, sdo_point_type(530, 345, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A10       ','2                   ','Griffith Park                                     ','Los Angeles                                       ','CA        ','90027     ','1-213-995-6600                ',SDO_GEOMETRY(2001,null, sdo_point_type(128, 246, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A11       ','26101               ','Magic Mountain Parkway                            ','Valencia                                          ','CA        ','91355     ','1-213-995-9960                ',SDO_GEOMETRY(2001,null, sdo_point_type(372, 334, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A12       ','1350                ','Massachusetts Ave                                 ','Cambridge                                         ','MA        ','2138      ','1-211-995-6126                ',SDO_GEOMETRY(2001,null, sdo_point_type(429, 280, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A13       ','405                 ','Hilgard Avenue                                    ','Los Angeles                                       ','CA        ','90095     ','1-213-995-6633                ',SDO_GEOMETRY(2001,null, sdo_point_type(22, 368, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A14       ','77                  ','Massachusetts Avenue                              ','Cambridge                                         ','MA        ','2139      ','1-211-995-9980                ',SDO_GEOMETRY(2001,null, sdo_point_type(129, 259, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A15       ','3607                ','Trousdale Pkwy                                    ','Los Angeles                                       ','CA        ','90089     ','1-213-995-6268                ',SDO_GEOMETRY(2001,null, sdo_point_type(725, 339, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A16       ','5757                ','Wilshire Blvd                                     ','Los Angeles                                       ','CA        ','90036     ','1-213-995-6640                ',SDO_GEOMETRY(2001,null, sdo_point_type(625, 273, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A17       ','1133                ',' N La Brea Ave                                    ','West Hollywood                                    ','CA        ','90038     ','1-213-995-6205                ',SDO_GEOMETRY(2001,null, sdo_point_type(11, 135, null), null, null));
Insert into ADDRESS (ADDRID,APTNUM,STREET_ADDRESS,CITY,STATE,ZIPCODE,MOBILEPHONENUM,VERTICE) values ('A18       ','1890                ','Shattuck Avenue                                   ','Berkeley                                          ','CA        ','94709     ','1-213-995-8792                ',SDO_GEOMETRY(2001,null, sdo_point_type(502, 288, null), null, null));

Insert into   SELLERS (EMAIL,FNAME,LNAME,BIRTHDATE,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRESS_ID,PROFILE_PHOTO_ID) values ('al@csci585.edu','Alfredo','Pacino','04/25/1970',null,null,null,null,null,null,'A1        ',null);
Insert into   SELLERS (EMAIL,FNAME,LNAME,BIRTHDATE,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRESS_ID,PROFILE_PHOTO_ID) values ('steve@csci585.edu','Steve','Jobs','02/24/1955',null,null,null,null,null,null,'A2        ',null);
Insert into   SELLERS (EMAIL,FNAME,LNAME,BIRTHDATE,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRESS_ID,PROFILE_PHOTO_ID) values ('bill@csci585.edu','Bill','Gates','10/28/1955',null,null,null,null,null,null,'A3        ',null);
Insert into    SELLERS (EMAIL,FNAME,LNAME,BIRTHDATE,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRESS_ID,PROFILE_PHOTO_ID) values ('miley@csci585.edu','Miley','Cyrus','11/23/1992',null,null,null,null,null,null,'A4        ',null);




Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('bill@csci585.edu','Bill','Gates','10/28/1955',null,null,null,null,null,null,'A3        ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('miley@csci585.edu','Miley','Cyrus','11/23/1992',null,null,null,null,null,null,'A4        ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('angelina@csci585.edu','Angelina','Jolie','06/04/1975',null,null,null,null,null,null,'A5        ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('brad@csci585.edu','Brad','Pitt','12/18/1963',null,null,null,null,null,null,'A6        ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('hope@csci585.edu','Hope','Solo','07/20/1981',null,null,null,null,null,null,'A7        ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('andre@csci585.edu','Andre','Agassi','04/29/1970',null,null,null,null,null,null,'A8        ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('nelle@csci585.edu','Nelle','Lee','09/12/1955',null,null,null,null,null,null,'A9        ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('jen@csci585.edu','Jennifer','Aniston','02/11/1969',null,null,null,null,null,null,'A10       ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('pit@csci585.edu','Pit','Sampras','08/12/1971',null,null,null,null,null,null,'A11       ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('josh@csci585.edu','Josh','Radnor','07/29/1974',null,null,null,null,null,null,'A12       ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('tom@csci585.edu','Tom','Cruise','07/03/1962',null,null,null,null,null,null,'A13       ',null);
Insert into  CUSTOMERS (EMAIL,FNAME,LNAME,BIRTHDAY,NICKNAME,GENDER,ACCOUNT_NUM,EXPIRED_DATE,CSC,JOIN_TIME,ADDRID,PROFILE_PHOTO_ID) values ('serina@usc.edu','Serena','Williams','09/26/1981',null,null,null,null,null,null,'A14       ',null);

Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('bill@csci585.edu','Bill','Gates','10/28/1955','1         ','S1                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('miley@csci585.edu','Miley','Cyrus','11/23/1992','1         ','S1                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('angelina@csci585.edu','Angelina','Jolie','06/04/1975','1         ','S2                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('brad@csci585.edu','Brad','Pitt','12/18/1963','1         ','S3                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('hope@csci585.edu','Hope','Solo','07/20/1981','1         ','S4                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('andre@csci585.edu','Andre','Agassi','04/29/1970','1         ','S5                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('nelle@csci585.edu','Nelle','Lee','09/12/1955','1         ','S6                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('jen@csci585.edu','Jennifer','Aniston','02/11/1969','1         ','S7                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('pit@csci585.edu','Pit','Sampras','08/12/1971','1         ','S8                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('josh@csci585.edu','Josh','Radnor','07/29/1974','1         ','S9                            ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('tom@csci585.edu','Tom','Cruise','07/03/1962','1         ','S10                           ','SD        ','CA        ','90007     ','1',null,null);
Insert into  USERS (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD,LONGTITUDE,LATITUDE) values ('serina@usc.edu','Serena','Williams','09/26/1981','1         ','S11                           ','SD        ','CA        ','90007     ','1',null,null);


Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro1','Clothing','Tommy Bahama ''Sail Hawaii'' Cover-Up T-Shirt Dress','Tommy Bahama',88,98);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro2','Clothing','Nike ''Pro Hyperwarm'' Training Top','Nike',65,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro3','Clothing','Olive & Oak Faux Leather Sleeve Collarless Jacket','Olive & Oak',102,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro4','Clothing','Hard Tail ''Frolic'' Asymmetric Top','Hard Tail',78,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro5','Clothing','Gallery Turnkey Quilted Jacket？','Gallery',116,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro6','Beauty','butter LONDON WINK Mascara','butter LONDON',20,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro7','Beauty','Tarte LipSurgence Natural Matte Lip Tint','butter LONDON',24,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro8','Beauty','Dramatically Different Moisturizing Gel','Clinique',26,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro9','Beauty','Liquid Facial Soap','Clinique',17,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro10','Beauty','Advanced Night Repair Synchronized Recovery Complex II Collection','Estee Lauder',120,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro11','Home & Kitchen','Sparkle Paper Towels, Print, 12 Large Rolls','Sparkle',12,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro12','Home & Kitchen','Duster Brush','Sparkle',9,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro13','Home & Kitchen','Lasko 5624 Low Profile Silent Room Heater, Black','Lasko',52,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro14','Home & Kitchen','Lasko 2004W 2-Speed Clip Fan, 6-Inch, White','Lasko',15,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro15','Home & Kitchen','Coaster Futon Sofa Bed with Removable Arm Rests, Brown Vinyl','Coaster Home Furnishings',330,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro16','Electronics','Dell Inspiron i660s-1231BK Desktop','Dell',120,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro17','Electronics','Apple Wireless Keyboard MC184LL/B','Apple',65,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro18','Electronics','Sony HDRCX240/L Video Camera with 2.7-Inch LCD','Sony',228,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro19','Electronics','Sony SmartWatch US version 1 Android Bluetooth USB Retail Box','Sony',75,100);
Insert into  PRODUCTS (PRODUCTID,CATEGORY,NAME,BRAND,PRICE,STOCK_QUANTITY) values ('Pro20','Electronics','Apple iPod touch 16GB White','Apple',215,100);

Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O1','bill@csci585.edu','(Pro1, 3), (Pro2, 1)',null,'06/06/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O2','bill@csci585.edu','(Pro2, 2), (Pro19, 1)',null,'06/07/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O3','bill@csci585.edu','(Pro3, 1), (Pro5, 2)',null,'06/08/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O4','bill@csci585.edu','(Pro4, 2)',null,'06/09/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O5','bill@csci585.edu','(Pro16, 3), (Pro17, 1) ',null,'06/10/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O6','miley@csci585.edu','(Pro1, 3), (Pro5, 4)',null,'06/11/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O7','miley@csci585.edu','(Pro3, 1), (Pro6, 2)',null,'06/12/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O8','miley@csci585.edu','(Pro4, 2), (Pro6, 1)',null,'06/13/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O9','miley@csci585.edu','(Pro10, 2)',null,'06/14/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O10','miley@csci585.edu','(Pro7, 1), (Pro9, 2)',null,'06/15/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O11','angelina@csci585.edu','(Pro8, 5), (Pro9, 5)',null,'06/16/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O12','angelina@csci585.edu','(Pro1,2), (Pro3,2)',null,'06/17/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O13','angelina@csci585.edu','(Pro10, 5)',null,'06/18/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O14','angelina@csci585.edu','(Pro8, 5), (Pro9, 5)',null,'06/19/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O15','pit@csci585.edu','(Pro18, 2)',null,'06/20/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O16','brad@csci585.edu','(Pro18, 1)',null,'06/21/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O17','brad@csci585.edu','(Pro19, 2)',null,'06/22/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O18','brad@csci585.edu','(Pro20, 2)',null,'06/23/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O19','brad@csci585.edu','(Pro18, 1)',null,'06/24/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O20','brad@csci585.edu','(Pro17, 2)',null,'06/25/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O21','hope@csci585.edu','(Pro8, 5), (Pro9, 5)',null,'06/26/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O22','hope@csci585.edu','(Pro16,3), (Pro17,1)',null,'06/27/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O23','hope@csci585.edu','(Pro6, 3)',null,'06/28/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O24','hope@csci585.edu','(Pro7, 1), (Pro9, 2)',null,'06/29/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O25','hope@csci585.edu','(Pro8, 5)',null,'06/30/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O26','hope@csci585.edu','(Pro1,2), (Pro2,2)',null,'07/01/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O27','hope@csci585.edu','(Pro3,2), (Pro4,2)',null,'07/02/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O28','hope@csci585.edu','(Pro4,2), (Pro5,2)',null,'07/03/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O29','hope@csci585.edu','(Pro9, 5)',null,'07/04/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O30','andre@csci585.edu','(Pro1, 5), (Pro2, 2)',null,'07/05/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O31','andre@csci585.edu','(Pro8, 1), (Pro9, 1)',null,'07/06/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O32','nelle@csci585.edu','(Pro4, 5), (Pro5, 5)',null,'07/07/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O33','nelle@csci585.edu','(Pro8, 2), (Pro11, 10)',null,'07/08/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O34','jen@csci585.edu','(Pro11, 2), (Pro12, 2)',null,'07/09/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O35','jen@csci585.edu','(Pro8, 5)',null,'07/10/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O36','jen@csci585.edu','(Pro7, 2)',null,'07/11/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O37','pit@csci585.edu','(Pro18, 5)',null,'07/12/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O38','pit@csci585.edu','(Pro16, 1)',null,'07/13/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O39','pit@csci585.edu','(Pro19, 3)',null,'07/14/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O40','pit@csci585.edu','(Pro12, 1)',null,'07/15/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O41','josh@csci585.edu','(Pro18, 1)',null,'07/16/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O42','josh@csci585.edu','(Pro17, 2)',null,'07/17/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O43','josh@csci585.edu','(Pro19, 1)',null,'07/18/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O44','tom@csci585.edu','(Pro11, 2)',null,'07/19/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O45','tom@csci585.edu','(Pro12, 2)',null,'07/20/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O46','serina@usc.edu','(Pro16, 1), (Pro17, 2)',null,'07/21/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O47','serina@usc.edu','(Pro15,1)',null,'07/22/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O48','serina@usc.edu','(Pro14,1)',null,'07/23/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O49','serina@usc.edu','(Pro16, 1), (Pro19, 2)',null,'07/24/2013');
Insert into  ORDERS (ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values ('O50','serina@usc.edu','(Pro20, 1)',null,'07/25/2013');


Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R1','5','A good product','angelina@csci585.edu','Pro1');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R2','2','Fine','jen@csci585.edu','Pro6');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R3','3','Reasonable','angelina@csci585.edu','Pro3');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R4','4','Good quanlity and cheap price','brad@csci585.edu','Pro12');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R5','5','A great product','andre@csci585.edu','Pro13');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R6','4','A good product','jen@csci585.edu','Pro7');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R7','2','Just so so','angelina@csci585.edu','Pro14');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R8','3','That''s OK','andre@csci585.edu','Pro2');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R9','4','A good product','jen@csci585.edu','Pro16');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R10','3','fine','brad@csci585.edu','Pro4');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R11','2','Just so so','angelina@csci585.edu','Pro17');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R12','5','A wonderful product with cheap price','brad@csci585.edu','Pro8');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R13','3','It works fine','brad@csci585.edu','Pro5');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R14','4','It is great!','miley@csci585.edu','Pro15');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R15','5','A great product','josh@csci585.edu','Pro1');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R16','4','good','hope@csci585.edu','Pro3');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R17','2','Just so so','angelina@csci585.edu','Pro19');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R18','3','That''s OK','brad@csci585.edu','Pro18');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R19','4','Good quanlity and cheap price','hope@csci585.edu','Pro4');
Insert into  REVIEWS (REVIEWID,RATING,CONTENT,AUTHORID,PRODUCTID) values ('R20','5','A wonderful product with cheap price','angelina@csci585.edu','Pro4');

Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R1','bill@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R2','brad@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R2','nelle@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R2','jen@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R2','tom@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R3','miley@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R4','bill@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R4','brad@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R5','miley@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R7','hope@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R8','angelina@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R8','andre@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R9','bill@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R9','jen@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R9','josh@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R9','serina@usc.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R10','serina@usc.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R10','angelina@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R10','nelle@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R10','pit@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R11','tom@csci585.edu',null);
Insert into  LIKEREVIEW1 (REVIEWID,CUSTOMER,TIME) values ('R15','andre@csci585.edu',null);
