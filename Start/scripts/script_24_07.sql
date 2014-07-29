-- Removing not null constraint for PO

ALTER TABLE `spinncast`.`purchase_order` MODIFY COLUMN `customer_id` INTEGER UNSIGNED DEFAULT NULL;