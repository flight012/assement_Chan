CREATE SCHEMA assmt;
DROP TABLE IF EXISTS assmt.tbl_assmt_trx_his;

CREATE TABLE IF NOT EXISTS assmt.tbl_assmt_trx_his(
 id varchar(30) NOT NULL PRIMARY KEY,
 acct_no varchar(50) DEFAULT NULL,
 trx_amt DECIMAL(15,2), 
 description varchar(1000) DEFAULT NULL,
 trx_date varchar(30) DEFAULT NULL,
 trx_time varchar(30) DEFAULT NULL,
 cus_no varchar(30) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP INDEX IF EXISTS idx_tbl_assmt_trx_his_in3 ON assmt.tbl_assmt_trx_his;
CREATE INDEX IF NOT EXISTS idx_tbl_assmt_trx_his_in3 ON assmt.tbl_assmt_trx_his (acct_no,cus_no,description);

DROP INDEX idx_tbl_assmt_trx_his_acctNo ON assmt.tbl_assmt_trx_his;
CREATE INDEX IF NOT EXISTS idx_tbl_assmt_trx_his_acctNo ON assmt.tbl_assmt_trx_his (acct_no);

DROP INDEX idx_tbl_assmt_trx_his_cusNo ON assmt.tbl_assmt_trx_his;
CREATE INDEX IF NOT EXISTS idx_tbl_assmt_trx_his_cusNo ON assmt.tbl_assmt_trx_his (cus_no);

DROP INDEX idx_tbl_assmt_trx_his_desc ON assmt.tbl_assmt_trx_his;
CREATE INDEX IF NOT EXISTS idx_tbl_assmt_trx_his_desc ON assmt.tbl_assmt_trx_his (description);