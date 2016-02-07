 alter table inv_hdr add column excise_duty_payable text;
 
 update inv_hdr set excise_duty_payable = 'Excise duty payable.';