drop table test_certificates;

CREATE TABLE `spinncast`.`test_cert_master` (
  `tc_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `tc_no` INTEGER UNSIGNED NOT NULL,
  `tc_date` DATETIME NOT NULL,
  `heat_batch_no` VARCHAR(45) NOT NULL,
  `heat_batch_date` DATETIME NOT NULL,
  `po_no` VARCHAR(45) NOT NULL,
  `po_date` DATETIME NOT NULL,
  `challan_no` VARCHAR(45) NOT NULL,
  `challan_date` DATETIME NOT NULL,
  `vendor_name` VARCHAR(45) NOT NULL,
  `grade` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `other_tests_remarks` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`tc_id`)
)
ENGINE = InnoDB;

CREATE TABLE `spinncast`.`test_certificate_actual_values` (
  `table_id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `tc_id` INTEGER UNSIGNED NOT NULL,
  `prop_name` VARCHAR(45) NOT NULL,
  `prop_type` varchar(2),
  `min_value` VARCHAR(45),
  `max_value` VARCHAR(45),
  `actual1` VARCHAR(45),
  `actual2` VARCHAR(45),
  `actual3` VARCHAR(45),
  PRIMARY KEY (`table_id`)
)
ENGINE = InnoDB;