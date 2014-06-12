CREATE TABLE `spinncast`.`user_master` (
  `user_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(50) NOT NULL unique,
  `password` VARCHAR(500) NOT NULL,
  `email_id` VARCHAR(200) NOT NULL,
  `user_role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`)
)
ENGINE = InnoDB;

-- Default entry for user admin

insert into user_master(user_id,userName,password,email_id,user_role) values(1,'admin','eae889ceda1452b34555b2b52b9f05d28a1e8ed8d5dc8c62362b90ee49746af1b99bf53cb3e58323d29c1dcc5b1203e45f824d10d87b1a63b9d6eec59a2f6740'
,'test@test.com','admin');

commit;