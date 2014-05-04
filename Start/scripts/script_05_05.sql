DROP TABLE IF EXISTS `spinncast`.`purchase_order_new2`;
DROP TABLE IF EXISTS `spinncast`.`purchase_order`;

CREATE TABLE  `spinncast`.`purchase_order` (
  `purchase_order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `purchase_order_no` varchar(100) NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  `purchase_order_date` datetime NOT NULL,
  PRIMARY KEY (`purchase_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `spinncast`.`purchase_order_lines`;
CREATE TABLE  `spinncast`.`purchase_order_lines` (
  `po_line_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `po_id` int (10) unsigned NOT NULL,
  `serial_no` int(10) unsigned NOT NULL,
  `part_id` int(10) unsigned NOT NULL,
  `quantity` decimal(10,0) NOT NULL,
  `rate` decimal(10,0) NOT NULL,
  `pending_quantity` decimal(10,0) NOT NULL,
  PRIMARY KEY (`po_line_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `spinncast`.`po_line_mapping`;