DROP TABLE IF EXISTS `spinncast`.`purchase_order_new2`;
CREATE TABLE  `spinncast`.`purchase_order_new2` (
  `purchase_order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `purchase_order_no` varchar(100) NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  `purchase_order_date` datetime NOT NULL,
  PRIMARY KEY (`purchase_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `spinncast`.`purchase_order_lines`;
CREATE TABLE  `spinncast`.`purchase_order_lines` (
  `po_line_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `serial_no` int(10) unsigned NOT NULL,
  `part_id` int(10) unsigned NOT NULL,
  `quantity` decimal(10,0) NOT NULL,
  `rate` decimal(10,0) NOT NULL,
  `pending_quantity` decimal(10,0) NOT NULL,
  PRIMARY KEY (`po_line_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `spinncast`.`po_line_mapping`;
CREATE TABLE  `spinncast`.`po_line_mapping` (
  `po_mapping_od` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `po_id` int(10) unsigned NOT NULL,
  `po_line_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`po_mapping_od`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;